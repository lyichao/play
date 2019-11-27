package di;


import android.app.ProgressDialog;
import android.content.Context;

import com.lyichao.play.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;
import data.RecommendModel;
import presenter.RecommendPresenter;
import presenter.contract.RecommendContract;

@Module
public class RecommendModule {

    private RecommendContract.View mView;

    public RecommendModule(RecommendContract.View view){

        this.mView = view;
    }

    @Provides
    public RecommendContract.Presenter providesPresenter(RecommendContract.View view,RecommendModel model){

        return new RecommendPresenter(view,model);

    }

    @Provides
    public RecommendContract.View providesView(){

        return mView;
    }

    @Provides
    public RecommendModel providesModel(){
        return new RecommendModel();
    }

    @Provides
    public ProgressDialog providesProgressDialog(RecommendContract.View view){
        return new ProgressDialog(((RecommendFragment)view).getActivity());    }

}
