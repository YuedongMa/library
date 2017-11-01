package com.example.yuedong.library.base;

/**
 * Created by mayuedong on 2017/11/1.
 */

public interface BasePresenter <T extends BaseView> {

    void attachView(T view);

    void detachView();

}

