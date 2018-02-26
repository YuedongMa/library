package com.example.yuedong.library.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 *
 */

public class OriginJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    OriginJsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override public T convert(ResponseBody value) throws IOException {
        T t = null;
        try {
            String json = value.string();
            JSONObject object = new JSONObject(json);
            Log.i("Server==","Server json：" + object.toString());
            if (object.has("code")){
                if (object.getInt("code") == 200){// http success
                    if (object.has("result")){
                        JSONObject objectResult = object.getJSONObject("result");
                        int code = objectResult.getInt("code");
                        if (objectResult.isNull("cause")){
                            objectResult.put("cause", "");
                        }
                        if (code == 0){
                            // 请求成功
                            ByteArrayInputStream inputStream;
                            inputStream = new ByteArrayInputStream(object.toString().getBytes());
                            // 字节流转换成字符流
                            Reader reader = new InputStreamReader(inputStream);
                            JsonReader jsonReader = gson.newJsonReader(reader);
                            // 返回 api 包下面每个接口中每个请求所定义的返回结果
                            return adapter.read(jsonReader);
                        } else if (code == 409 || code == 408 || code == 405 || code == 450){
                            // token 异常

                        } else {
//                            BaseResponse baseResponse = new BaseResponse();
//                            ResponseData responseData = new ResponseData();
//                            responseData.setCode(code);
//                            responseData.setMsg(objectResult.getString("msg"));
//                            baseResponse.setCode(200);
//                            baseResponse.setResult(responseData);
//                            t = (T) baseResponse;
                        }
                    }
                } else {// http fail

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            value.close();
        }
        return t;
    }
}
