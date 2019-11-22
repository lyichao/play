package presenter.contract;

import com.lyichao.play.ui.BaseView;

import bean.AppInfo;
import presenter.BasePresenter;
import java.util.List;

public interface RecommendContract {

    interface View extends BaseView{

        //显示结果
        void showResult(List<AppInfo> datas);
        //无数据
        void showNoDatas();
        //报错、异常
        void showError(String msg);

    }

    interface Presenter extends BasePresenter{

        public void requestDatas();

    }


}
