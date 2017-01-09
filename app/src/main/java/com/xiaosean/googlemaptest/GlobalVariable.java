package com.xiaosean.googlemaptest;

import android.app.Application;
import android.content.SharedPreferences;

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
    public List siteInfoList = new ArrayList<ArrayList>();
    private final String SUCCESS = "達成", FAILED = "尚未達成";

//    public enum SITE{
//        NAME(0), CONTENT, FLAG, POS_ID, FILE_ID, LAT, LNG
//    }
    public void setDefaultData(){
        siteInfoList.clear();
        siteInfoList.add(Arrays.asList("大砲池", "我就是愛嘴砲", FAILED, 0, R.raw.bomb, 25.013554, 121.540795));
        siteInfoList.add(Arrays.asList("烏龜池", "我就是愛玩水", FAILED, 1, R.raw.turtle1, 25.014100, 121.541816));
        siteInfoList.add(Arrays.asList("生態池", "我就是愛吃菜", FAILED, 2,R.raw.turtle2, 25.013396, 121.542155));
        SharedPreferences settings = getSharedPreferences("INFO", 0);

        for(int i = 0; i < siteInfoList.size(); ++i){
            List list = (List) siteInfoList.get(i);
            String siteStatus = settings.getString(((String)list.get(0)), getString(R.string.FAILED));
            list.set(2, siteStatus);

        }
    }
    public int findSiteIdByName(String siteName){
        for(int i = 0; i < siteInfoList.size(); ++i){
            List list = (List) siteInfoList.get(i);
            if(((String)list.get(i)).contains(siteName))
                return i;
        }
        return 0;
    }

    public void setSuccess(String pool) {
        for (Object place : siteInfoList) {
            List places = (List) place;
            if (places.get(0).equals(pool)) {
                SharedPreferences settings = getSharedPreferences("INFO", 0);
                SharedPreferences.Editor PE = settings.edit();
                PE.putString(pool, getString(R.string.SUCCESS));
                PE.commit();
                places.set(2, SUCCESS);
            }
        }
    }
    public void setFAILED(String pool) {
        for (Object place : siteInfoList) {
            List places = (List) place;
            if (places.get(0).equals(pool)) {
                SharedPreferences settings = getSharedPreferences("INFO", 0);
                SharedPreferences.Editor PE = settings.edit();
                PE.putString(pool, getString(R.string.FAILED));
                PE.commit();
                places.set(2, FAILED);
            }
        }
    }
    public void setFAILED(int siteId) {
        List place = (List) siteInfoList.get(siteId);
        SharedPreferences settings = getSharedPreferences("INFO", 0);
        SharedPreferences.Editor PE = settings.edit();
        PE.putString(place.get(0).toString(), getString(R.string.FAILED));
        PE.commit();
        place.set(2, FAILED);

    }
    public List getSiteInfoList() {
        return siteInfoList;
    }
    public List getSiteInfoList(int i) {
        return (List)siteInfoList.get(i);
    }


}