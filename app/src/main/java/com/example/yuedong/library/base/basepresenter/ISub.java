package com.example.yuedong.library.base.basepresenter;

import io.reactivex.disposables.Disposable;

/**
 * 请求回调
 *
 * Created by Administrator on 2017/8/15.
 */

public interface ISub<T> {

	void doOnSubscribe(Disposable d);

	void responseSuccess(T t, int code);

	void responseTokenException(T tokenResponse, String exMsg);

	void responseError(String errorMsg);

}
