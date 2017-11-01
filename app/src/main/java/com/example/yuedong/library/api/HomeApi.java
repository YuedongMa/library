package com.example.yuedong.library.api;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by mayuedong on 2017/0/30.
 */

public interface HomeApi {

    @POST
    Call<JSONObject> getOrderNum(@Url String url, @Body HashMap map);

}
