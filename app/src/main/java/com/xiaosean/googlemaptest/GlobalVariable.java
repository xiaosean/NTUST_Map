package com.xiaosean.googlemaptest;

import android.app.Application;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Xiao on 2016/8/23.
 * for global variable
 * GlobalVariable globalVariable = ((GlobalVariable)getApplicationContext());
 * globalVariable.int_test=12;
 * ((GlobalVariable)getApplicationContext()).setSale(false);
 * <p>
 * // set
 * ((GlobalVariable)getApplicationContext()).setSomeVariable("foo");
 * <p>
 * // get
 * String s =  ((GlobalVariable)getApplicationContext()).getSomeVariable();
 */

public class GlobalVariable extends Application {
    public List siteInfoList = new ArrayList<List>();
    private final String SUCCESS = "達成", FAILED = "尚未達成";

//    public enum SITE{
//        NAME(0), CONTENT, FLAG, POS_ID, FILE_ID, LAT, LNG
//    }
    public void setDefaultData(){
        siteInfoList.add(Arrays.asList("大砲池", "我就是愛嘴砲", SUCCESS, 0, R.raw.bomb, 25.013554, 121.540795));
        siteInfoList.add(Arrays.asList("烏龜池", "我就是愛玩水", SUCCESS, 1, R.raw.turtle1, 25.014100, 121.541816));
        siteInfoList.add(Arrays.asList("生態池", "我就是愛吃菜", FAILED, 2,R.raw.turtle2, 25.013396, 121.542155));

    }
    public List getSiteInfoList() {
        return siteInfoList;
    }
    public List getSiteInfoList(int i) {
        return (List)siteInfoList.get(i);
    }


}