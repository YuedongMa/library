package com.example.yuedong.library.utils;

import android.content.Context;

import com.example.yuedong.library.config.AppConfig;
import com.example.yuedong.library.models.JsonBean;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

import io.paperdb.Paper;

import static io.paperdb.Paper.book;

/**
 * Created by mayuedong on 2017/10/30.
 */

public class PaperUtils {

    static ArrayList<ArrayList<String>> options2Items;
    static ArrayList<ArrayList<ArrayList<String>>> options3Items;

    public  static void initCity(Context context) {
        options2Items = new ArrayList<>();
        options3Items = new ArrayList<>();
        String json = FileUtils.readAssetsFile(context, "province.json");
        ArrayList<JsonBean> jsonbean = parseJson(json);
        saveCityOptionOne(jsonbean);
        for (int i = 0; i < jsonbean.size(); i++) {
            ArrayList<ArrayList<String>> pro_AreaList = new ArrayList<>();
            ArrayList<String> cityList = new ArrayList<>();
            for (int j = 0; j < jsonbean.get(i).getCity().size(); j++) {
                cityList.add(jsonbean.get(i).getCity().get(j).getName());
                ArrayList<String> city_AreaList = new ArrayList<>();
                for (int k = 0; k < jsonbean.get(i).getCity().get(j).getArea().size(); k++) {
                    city_AreaList.add(jsonbean.get(i).getCity().get(j).getArea().get(k));
                }
                pro_AreaList.add(city_AreaList);
            }
            options2Items.add(cityList);
            options3Items.add(pro_AreaList);
        }
        saveCityOptionTwo(options2Items);
        saveCityOptionThree(options3Items);
        jsonbean.clear();
        jsonbean=null;
    }

    private static  ArrayList<JsonBean> parseJson(String json) {
        ArrayList<JsonBean> list = new ArrayList<>();
        try {
            JSONArray arr = new JSONArray(json);
            Gson gson = new Gson();
            for (int i = 0; i < arr.length(); i++) {
                JsonBean jsonBean = gson.fromJson(arr.getJSONObject(i).toString(), JsonBean.class);
                list.add(jsonBean);
            }
        } catch (Exception e) {

        }

        return list;
    }

    public static void saveCityOptionOne(ArrayList<JsonBean> list) {
   Paper.book(AppConfig.KEY_CITY_OPTION).write(AppConfig.KEY_CITY_OPTION_ONE,list);
    }

    public static void saveCityOptionTwo(ArrayList<ArrayList<String>> list) {
        book(AppConfig.KEY_CITY_OPTION).write(AppConfig.KEY_CITY_OPTION_TWO,list);
    }

    public static void saveCityOptionThree(ArrayList<ArrayList<ArrayList<String>>> list) {
        book(AppConfig.KEY_CITY_OPTION).write(AppConfig.KEY_CITY_OPTION_THREE,list);
    }

    public static ArrayList<JsonBean> getCityOptionOne() {
        return (ArrayList<JsonBean>)  book(AppConfig.KEY_CITY_OPTION).read(AppConfig.KEY_CITY_OPTION_ONE);
    }
    public static ArrayList<ArrayList<String>> getCityOptionTwo() {
        return (ArrayList<ArrayList<String>>) book(AppConfig.KEY_CITY_OPTION).read(AppConfig.KEY_CITY_OPTION_TWO);
    }
    public static ArrayList<ArrayList<ArrayList<String>>> getCityOptionThree() {
        return (ArrayList<ArrayList<ArrayList<String>>>)  book(AppConfig.KEY_CITY_OPTION).read(AppConfig.KEY_CITY_OPTION_THREE);
    }
    //***********************************CityOption END **********************************************
}
