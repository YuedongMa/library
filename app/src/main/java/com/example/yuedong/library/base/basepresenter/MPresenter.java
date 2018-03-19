package com.example.yuedong.library.base.basepresenter;

/**
 *
 *  Created by mayuedong on 2017/11/1.
 */

public class MPresenter<T extends BaseView> implements BasePresenter<T> {

    protected T mView;

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
