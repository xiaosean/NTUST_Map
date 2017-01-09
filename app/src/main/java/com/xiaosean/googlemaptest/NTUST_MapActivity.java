package com.xiaosean.googlemaptest;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;


import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

/**
 * Created by Xiao on 2016/12/25.
 */

public class NTUST_MapActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private BottomBar bottomBar;
    private NTUST_MAP_Fragment map_fragment = new NTUST_MAP_Fragment();
    private InfoFragment info_fragment = new InfoFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ntust_map);
        processViews();
        processControllers();
//        Intent intent = new Intent(this, MyFirebaseMessagingService.class);
//        //开启关闭Service
//        startService(intent);
//        }

    }

    private void processViews() {
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);

    }

    private void processControllers() {
        setBottomBar();

    }

    public void checkdistance(Location loc){
        map_fragment.checkLocation(loc);
    }

    private void setBottomBar() {
        fragmentManager = getSupportFragmentManager();
        bottomBar.animate().cancel();
        //bottom bar listener
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
//                try {
                    if (tabId == R.id.tab_map) {
                        fragmentManager.beginTransaction().replace(R.id.activity_ntust_map_fragment_container, map_fragment, "NTUST_MAP").commit();
                    } else if (tabId == R.id.tab_info) {
                        fragmentManager.beginTransaction().replace(R.id.activity_ntust_map_fragment_container, info_fragment, "INFO").commit();
                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        });

    }
}
