package com.lyichao.play.commom.rx;

import com.lyichao.play.bean.BaseBean;
import com.lyichao.play.commom.exception.ApiException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxHttpResponseCompat {

    public static  <T>Observable.Transformer<BaseBean<T>,T> compatResult(){

        return new Observable.Transformer<BaseBean<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseBean<T>> baseBeanObservable) {
                return baseBeanObservable.flatMap(new Func1<BaseBean<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseBean<T> tBaseBean) {

                        if(tBaseBean.success()){

                            return Observable.create(new Observable.OnSubscribe<T>() {
                                @Override
                                public void call(Subscriber<? super T> subscriber) {
                                    try {
                                        subscriber.onNext(tBaseBean.getData());
                                        subscriber.onCompleted();
                                    }catch (Exception e){
                                        subscriber.onError(e);
                                    }
                                }
                            });

                        }else {
                            return  Observable.error(new ApiException(tBaseBean.getStatus(),tBaseBean.getMessage()));
                        }

                    }
                }).subscribeOn(Schedulers.io())   //被订阅者切换到io线程中，处理网络请求（耗时操作）
                 .observeOn(AndroidSchedulers.mainThread());   //订阅者切换到主线程中更新数据
            }
        };
    }
}
