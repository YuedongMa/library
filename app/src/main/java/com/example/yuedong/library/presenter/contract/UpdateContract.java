package com.example.yuedong.library.presenter.contract;

import android.content.Context;

import com.example.yuedong.library.base.basepresenter.BasePresenter;
import com.example.yuedong.library.base.basepresenter.BaseView;
import com.example.yuedong.library.models.UpdVersionModule;

import java.util.Date;

import io.reactivex.disposables.Disposable;

/**
 * Created by YuedongMa on 2018/2/22.
 */

public class UpdateContract {
    public interface View extends BaseView {
        void updVersionSuccess(UpdVersionModule versionModule);

        void updVersionFail(String errorMsg, boolean isTokenEP);

        void onCitySelectedResult(String msg);

        void onTimeSelecedResult(Date date);
    }

    public interface Presenter extends BasePresenter<View> {
        void updVersion();

        void clearDisposable();

        void onCitySelected(Context context);

        void onTimeSeleced(Context context);
    }
}
