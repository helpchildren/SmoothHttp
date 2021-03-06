package com.sesxh.smoothhttp.transformer;

import com.sesxh.smoothhttp.ApiException;
import com.sesxh.smoothhttp.SmoothHttp;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * @author LYH
 * @date 2021/1/5
 * @time 15:34
 * @desc RxJava线程调度
 */


public class RxScheduler {

    /**
     * 网络请求过程线程转换器，io 线程发射 ui 线程观察，且自带重试机制
     *
     * @param <T> 数据类型
     * @return 指定了在 io 线程执行，UI 线程观察结果的观察对象
     */
    public static <T> ObservableTransformer<T, T> retrySync() {
        if(SmoothHttp.getInstance().globalConfig().getRetryCount()>0) {
            return autoRetrysync();
        }
        return sync();
    }


    /**
     * 网络请求过程线程转换器，io 线程发射 ui 线程观察
     *
     * @param <T> 数据类型
     * @return 指定了在 io 线程执行，UI 线程观察结果的观察对象
     */
    public static <T> ObservableTransformer<T, T> sync() {
        return upstream -> upstream
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new ResponseErrFunc<>());

    }

    /**
     * 网络请求过程线程转换器，io 线程发射 ui 线程观察，且自带重试机制
     *
     * @param <T> 数据类型
     * @return 指定了在 io 线程执行，UI 线程观察结果的观察对象
     */
    public static <T> ObservableTransformer<T, T> autoRetrysync() {
        return upstream -> upstream
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryFunc(SmoothHttp.getInstance().globalConfig().getRetryCount(),
                        SmoothHttp.getInstance().globalConfig().getRetryDelayMillis()))
                .onErrorResumeNext(new ResponseErrFunc<>());
    }


    /**
     * 指定 io 线程
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> io() {
        return upstream -> upstream
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }


    /**
     * 调用结束后自动解除观察
     *
     * @param <T> 数据流对象
     * @return
     */
    public static <T> ObservableTransformer<T, T> autoDispose() {
        final Disposable[] disposables = new Disposable[1];
        return upstream -> upstream
                .doOnSubscribe(disposable -> disposables[0] = disposable)
                .doOnComplete((Action) () -> {
                    if (disposables[0] != null) {
                        disposables[0].dispose();
                    }
                });
    }


    /**
     * 网络请求出错
      * @param <T>
     */
    private static class ResponseErrFunc<T> implements Function<Throwable, Observable<T>> {
        @Override
        public Observable<T> apply(Throwable t) {
            return Observable.error(ApiException.handleException(t));
        }
    }

}
