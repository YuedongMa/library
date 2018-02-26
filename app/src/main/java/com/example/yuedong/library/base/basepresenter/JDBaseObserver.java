package com.example.yuedong.library.base.basepresenter;


import com.example.yuedong.library.MainApplication;
import com.example.yuedong.library.exception.JDExceptionManager;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 对请求数据，先做一步处理
 *
 *
 * @author Administrator
 * @date 2017/9/6
 */

public abstract class JDBaseObserver<T> implements Observer<BaseResponse<T>>, ISub<T> {

	@Override
	public void onSubscribe(@NonNull Disposable d) {
        doOnSubscribe(d);
	}

	@Override
	public void onNext(@NonNull BaseResponse<T> tBaseResponse) {//根据返回待完善
if(tBaseResponse.isSuccess()){
	if(tBaseResponse.isSuccess()){
		responseSuccess(tBaseResponse.getResult().getData(),0);
	}
}
	}

	@Override
	public void onError(@NonNull Throwable e) {
		responseError(e.getMessage());
		JDExceptionManager.handleException(e);
	}

	@Override
	public void onComplete() {

	}

}
