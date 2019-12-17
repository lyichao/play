package com.lyichao.play.commom.http;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.lyichao.play.commom.Constant;
import com.lyichao.play.commom.util.DensityUtil;
import com.lyichao.play.commom.util.DeviceUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class CommonParamsInterceptor implements Interceptor {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private Gson mGson;
    private Context mContext;

    public CommonParamsInterceptor(Context context, Gson gson) {
        this.mGson = gson;
        this.mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        //获取原始请求数据request   http://112.124.22.238:8081/course_api/cniaoplay/featured?p={'page':0}
        Request request = chain.request();

        try {
            //获取请求方式
            String method = request.method();

            //创建Map对象封装公共参数
            HashMap<String,Object> commomParamsMap = new HashMap<>();
//        commomParamsMap.put(Constant.IMEI, DeviceUtils.getIMEI(mContext));
            commomParamsMap.put(Constant.MODEL,DeviceUtils.getModel());
            commomParamsMap.put(Constant.LANGUAGE,DeviceUtils.getLanguage());
            commomParamsMap.put(Constant.os,DeviceUtils.getBuildVersionIncremental());
            commomParamsMap.put(Constant.RESOLUTION, DensityUtil.getScreenW(mContext)+"*" + DensityUtil.getScreenH(mContext));
            commomParamsMap.put(Constant.SDK,DeviceUtils.getBuildVersionSDK()+"");
            commomParamsMap.put(Constant.DENSITY_SCALE_FACTOR,mContext.getResources().getDisplayMetrics().density+"");

            if (method.equals("GET")){
                //获取请求url
                HttpUrl httpUrl = request.url();

                //创建Map对象
                HashMap<String,Object> rootMap = new HashMap<>();

                //获取url的所有参数的名字
                Set<String> paramNames = httpUrl.queryParameterNames();

                //循环遍历所有参数名字
                for (String key: paramNames) {

                    //如果参数名字存在p
                    if (Constant.PARAM.equals(key)){
                        //获取原始参数{'page':0}
                        String oldParamJson = httpUrl.queryParameter(Constant.PARAM);
                        if (oldParamJson != null){
                            //解析原始参数成Map对象，并转化成Json格式
                            HashMap<String,Object> p  = mGson.fromJson(oldParamJson,HashMap.class);
                            if (p != null){
                                for (Map.Entry<String, Object> entry : p.entrySet()){
                                    rootMap.put(entry.getKey(),entry.getValue());
                                }
                            }
                        }
                    }
                    else {
                        rootMap.put(key,httpUrl.queryParameter(key));
                    }
                }

                //重新组装好的参数（包含公共参数）
                rootMap.put("publicParams",commomParamsMap);
                //将组装好的Map对象转换成新的字符串  {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}
                String newJsonParams = mGson.toJson(rootMap);
                String url = httpUrl.toString();

                int index = url.indexOf("?");
                if (index > 0){
                    //截取url，截取从0开始到？结束
                    url = url.substring(0,index);
                }
                //拼接从最后开始，添加?p={}
                //http://112.124.22.238:8081/course_api/cniaoplay/featured?p= {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}
                url = url + "?" + Constant.PARAM + "=" + newJsonParams;
                //重新构建请求url
                request = request.newBuilder()
                        .url(url)
                        .build();



            }else if (method.equals("POST")){

                RequestBody body = request.body();

                HashMap<String,Object> rootMap = new HashMap<>();

                if (body instanceof FormBody){
                    for (int i = 0; i < ((FormBody) body).size(); i++) {
                        rootMap.put(((FormBody) body).encodedName(i),((FormBody) body).encodedValue(i));

                    }
                }else {
                    Buffer buffer = new Buffer();

                    body.writeTo(buffer);

                    String oldJsonParams = buffer.readUtf8();

                    rootMap = mGson.fromJson(oldJsonParams,HashMap.class);
                    rootMap.put("publicParams",commomParamsMap);
                    String newJsonParams = mGson.toJson(rootMap);

                    request = request.newBuilder()
                            .post(RequestBody.create(JSON,newJsonParams))
                            .build();


                }


            }
        }catch (JsonParseException e){
            e.printStackTrace();
        }



        return chain.proceed(request);
    }
}
