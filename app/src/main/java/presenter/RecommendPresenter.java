package presenter;

import java.util.List;

import bean.AppInfo;
import bean.PageBean;
import data.RecommendModel;
import presenter.contract.RecommendContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendPresenter implements RecommendContract.Presenter{

    private RecommendModel mModel;

    private RecommendContract.View mView;

    public RecommendPresenter(RecommendContract.View view) {
        this.mView = view;
        mModel = new RecommendModel();
    }

    @Override
    public void requestDatas() {

        mView.showLoading();

        mModel.getApps(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {

                if(response != null){
                    mView.showResult((List<AppInfo>) response.body().getDatas());
                }else {
                    mView.showNoDatas();
                }
                mView.dimissLoading();

            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {

                mView.dimissLoading();
                mView.showError(t.getMessage());
            }
        });
    }
}
