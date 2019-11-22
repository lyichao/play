package data;

import android.util.Log;

import java.util.List;

import bean.AppInfo;
import bean.PageBean;
import http.ApiService;
import http.HttpManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class RecommendModel {

    public void  getApps(Callback<PageBean<AppInfo>> callback){

        HttpManager manager = new HttpManager();

        ApiService apiService =manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);


        apiService.getApps("{'page':0}").enqueue(callback);
    }
}
