package com.example.yuedong.library.models;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mayuedong on 2017/10/26.
 */

public class JsonBean implements IPickerViewData,Serializable{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }

    private String name;
    private List<City> city;

    @Override
    public String getPickerViewText() {
        return this.name;
    }

  public static   class City {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getArea() {
            return area;
        }

        public void setArea(List<String> area) {
            this.area = area;
        }

        private List<String> area;
    }
}
