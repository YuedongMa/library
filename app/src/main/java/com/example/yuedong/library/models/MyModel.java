package com.example.yuedong.library.models;

/**
 * Created by mayuedong on 2017/10/20.
 */

public class MyModel   {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyModel(String name) {
        this.name = name;
    }

    private String name;
}
