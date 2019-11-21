package http;

import bean.AppInfo;
import bean.PageBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {


    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";




    @GET("featured")
    public Call<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);


}
