package com.example.yuedong.library.api;

import com.example.yuedong.library.base.basepresenter.BaseResponse;
import com.example.yuedong.library.models.base.BaseModel;
import com.example.yuedong.library.models.request.RequestJson;
import com.example.yuedong.library.models.UpdVersionModule;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by mayuedong on 2017/0/30.
 */

public interface HomeApi {

    @POST
    Observable<BaseResponse<BaseModel<UpdVersionModule>>> updVersion(@Url String url, @Body RequestJson requestJson);
    // 下载 apk
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);
}
