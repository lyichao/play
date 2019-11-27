package di;

import com.lyichao.play.ui.fragment.RecommendFragment;

import dagger.Component;

@Component(modules = RecommendModule.class)
public interface RecommendComponent {

    void inject(RecommendFragment fragment);
}
