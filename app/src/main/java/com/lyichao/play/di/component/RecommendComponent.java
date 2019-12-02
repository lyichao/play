package com.lyichao.play.di.component;

import com.lyichao.play.ui.fragment.RecommendFragment;

import dagger.Component;
import com.lyichao.play.di.FragmentScope;
import com.lyichao.play.di.component.AppComponent;
import com.lyichao.play.di.module.RecommendModule;

@FragmentScope
@Component(modules = RecommendModule.class,dependencies = AppComponent.class)
public interface RecommendComponent {

    void inject(RecommendFragment fragment);
}
