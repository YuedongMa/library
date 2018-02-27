package com.example.yuedong.library.presenter.interactor.remote;

import com.example.yuedong.library.MainApplication;
import com.example.yuedong.library.api.HomeApi;
import com.example.yuedong.library.base.basepresenter.BaseResponse;
import com.example.yuedong.library.base.basepresenter.JDBaseObserver;
import com.example.yuedong.library.http.JDHttp;
import com.example.yuedong.library.http.JDTransformer;
import com.example.yuedong.library.models.UpdVersionModule;
import com.example.yuedong.library.models.base.BaseModel;
import com.example.yuedong.library.models.body.UpdVersionBody;
import com.example.yuedong.library.models.request.RequestJson;
import com.vondear.rxtools.SDeviceTool;

import io.reactivex.disposables.Disposable;

/**业务交互类
 * Created by YuedongMa on 2018/2/26.
 */

public class PresenterRemoteInteractorImpl extends BaseRemoteInteractorCallBack implements IPresenterRemoteInteractor {
    HomeApi homeApi;
    public PresenterRemoteInteractorImpl() {
        homeApi = JDHttp.createApi(HomeApi.class);
    }

    @Override
    public void updVersion(final onRemoteEventFinishListener listener) {
        RequestJson<UpdVersionBody> requestJson = new RequestJson<>(this);
        requestJson.setToken("");
        requestJson.setMethod("checkClientUpdate");
        requestJson.setVersion("4.0");
        UpdVersionBody versionBody = new UpdVersionBody();
        versionBody.setAppName("4");
        versionBody.setVersion(SDeviceTool.getAppVersionName(MainApplication.getContext()));
        versionBody.setMachineId(SDeviceTool.getIMEI(MainApplication.getContext()));
        versionBody.setPhoneType(2);
        requestJson.setDataBody(versionBody);
        homeApi.updVersion("http://118.190.162.163/bdbgapi/bdbCommonGateway/api/", requestJson)
                .compose(JDTransformer.<BaseResponse<BaseModel<UpdVersionModule>>>switchSchedulers())
                .subscribe(new JDBaseObserver<BaseModel<UpdVersionModule>>() {
                    @Override
                    public void doOnSubscribe(Disposable d) {
                        listener.getDisposable(d);

                    }

                    @Override
                    public void responseSuccess(BaseModel<UpdVersionModule> versionModule, int code) {
                        if(versionModule.getData()!=null) {
                           callBackSuccessResult(listener,versionModule.getData());
                        }else {
                            callBackSuccessResult(listener,new UpdVersionModule());
                        }
                    }

                    @Override
                    public void responseTokenException(BaseModel<UpdVersionModule> tokenResponse, String exMsg) {
                      callBackError(listener,exMsg);
                    }

                    @Override
                    public void responseError(String errorMsg) {
                        callBackError(listener,errorMsg);
                    }
                });
    }
}
