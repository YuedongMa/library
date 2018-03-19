package com.example.yuedong.library.presenter;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yuedong.library.R;
import com.example.yuedong.library.api.HomeApi;
import com.example.yuedong.library.base.SupperActivity;
import com.example.yuedong.library.base.basepresenter.MPresenter;
import com.example.yuedong.library.http.MTransformer;
import com.example.yuedong.library.http.download.DownloadObserver;
import com.example.yuedong.library.http.download.DownloadRetrofit;
import com.example.yuedong.library.models.UpdVersionModule;
import com.example.yuedong.library.presenter.contract.UpdateContract;
import com.example.yuedong.library.presenter.interactor.location.AsyncLocationManagerResult;
import com.example.yuedong.library.presenter.interactor.location.IPresenterLocationInteractor;
import com.example.yuedong.library.presenter.interactor.location.PresenterLocationInteractorImpl;
import com.example.yuedong.library.presenter.interactor.remote.AsyncRemoteManagerResult;
import com.example.yuedong.library.presenter.interactor.remote.IPresenterRemoteInteractor;
import com.example.yuedong.library.presenter.interactor.remote.PresenterRemoteInteractorImpl;
import com.example.yuedong.library.utils.RxUtils;
import com.example.yuedong.library.widget.dialog.CommentDialog;
import com.vondear.rxtools.SAppTool;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * 管理视图层的状态，响应用户行为
 * Created by YuedongMa on 2018/2/22.
 */

public class UpdatePresenter extends MPresenter<UpdateContract.View> implements UpdateContract.Presenter, IPresenterRemoteInteractor.onRemoteEventFinishListener, IPresenterLocationInteractor.onLocaionEventFinishListener {
    private IPresenterRemoteInteractor remoteInteractor;
    private IPresenterLocationInteractor locationInteractor;
    private List<Disposable> mDisposables;
    private UpdVersionModule updVersionModule;
    private CommentDialog dialog;

    public UpdatePresenter() {
        remoteInteractor = new PresenterRemoteInteractorImpl();
        locationInteractor = new PresenterLocationInteractorImpl();
    }

    @Override
    public void updVersion() {
        remoteInteractor.getVersion(this);
    }

    @Override
    public void getDisposable(Disposable d) {
        if (mDisposables == null) {
            mDisposables = new ArrayList<>();
        }
        mDisposables.add(d);
    }

    @Override
    public void clearDisposable() {
        RxUtils.clearDisposables(mDisposables);
        locationInteractor = null;
        remoteInteractor = null;
    }

    @Override
    public void checkAndUpdateApk(final Context context) {
        if (updVersionModule.getUpgrade() == 1) {
            dialog = ((SupperActivity) context).$.showCommentDilog(context, R.layout.dialog_download);
            dialog.findViewById(R.id.tvUpdate).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    downFile(context, updVersionModule.getDownloadUrl());
                    dialog.findViewById(R.id.lshowUpdate).setVisibility(View.GONE);
                    dialog.findViewById(R.id.lToDown).setVisibility(View.VISIBLE);
                }
            });
            dialog.findViewById(R.id.tvQuit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }
    }

    @Override
    public void onCitySelected(Context context) {
        locationInteractor.onCitySelected(context, this);
    }

    @Override
    public void onTimeSeleced(Context context) {
        locationInteractor.onTimeSelected(context, this);
    }

    @Override
    public void showCommentDialog(Context context) {
        locationInteractor.showCommentDialog(context, "内容区域", "dialog提示", this);
    }

    /**
     * 远程数据统一分发
     * 暂时根据方法名进行分发
     *
     * @param result
     */
    @Override
    public void onInerractorFinish(AsyncRemoteManagerResult result) {
        if (result.isSuccess()) {
            if (result.getMethod().equals(result.KEY_CHECKCLIENTUPDATE)) {
                updVersionModule = result.getResult(UpdVersionModule.class);
                mView.getVersionSuccess();
            }
        } else {
            mView.getVersionFail(result.getMessage(), false);
        }
    }

    /**
     * 本地事件统一分发
     *
     * @param result
     */
    @Override
    public void onLocationEventFinish(AsyncLocationManagerResult result, int eventType) {
        if (eventType == result.KEY_ONCITYSELECTED) {
            mView.onCitySelectedResult(result.getResult(String.class));
        } else if (eventType == result.KEY_ONTIMESELECTED) {
            mView.onTimeSelecedResult(result.getResult(Date.class));
        }else if(eventType==result.KEY_SHOW_COMMENT_DIALOG){
            CommentDialog commentDialog=result.getResult(CommentDialog.class);
            commentDialog=null;
            mView.showCommentDialogFinish();
        }


    }

    void downFile(final Context context, String path) {
        DownloadRetrofit.getInstance().getRetrofit().create(HomeApi.class).downloadFile(path).compose(MTransformer.<ResponseBody>switchSchedulers()).subscribe(new DownloadObserver(context, "project.apk") {
            @Override
            public void getDisposable(Disposable d) {
                mDisposables.add(d);

            }

            @Override
            public void onError(String errorMsg) {
                ((SupperActivity) context).$.showToast(errorMsg);
            }

            @Override
            public void onSuccess(long bytesRead, long contentLength, float progress, boolean done, String filePath) {
                ((ProgressBar) dialog.findViewById(R.id.progressBar)).setProgress((int) progress);
                ((TextView) dialog.findViewById(R.id.tvPro)).setText(progress + "%");
                if (progress == 100) {
                    dialog.dismiss();
                    SAppTool.installApp(context, filePath);
                }


            }
        });
    }
}
