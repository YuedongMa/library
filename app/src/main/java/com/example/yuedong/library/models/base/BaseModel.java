package com.example.yuedong.library.models.base;

/**
 * Created by YuedongMa on 2018/2/22.
 */

public class BaseModel<T> {
    private T data;

    @Override
    public String toString() {
        return "BaseModel{" +
                "data=" + data +
                '}';
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
