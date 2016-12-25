package com.xiaosean.googlemaptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button settingBtn, startBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        設定元件
        processViews();
//        設定事件
        processControllers();
//        disable actionBar
        super.getSupportActionBar().hide();
    }

    private void processViews() {
        //hide action bar
        getSupportActionBar().hide();
//        將元件給至對應的變數
        startBtn = (Button) findViewById(R.id.start_button);
        settingBtn = (Button) findViewById(R.id.setting_button);

    }

    private void processControllers() {
//        設定監聽
        startBtn.setOnClickListener(new click_listener());
        settingBtn.setOnClickListener(new click_listener());
    }

    private class click_listener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.start_button) {
                startActivity(new Intent(MainActivity.this, NTUST_MapActivity.class));
            } else if (id == R.id.setting_button) {
                finish();
            }
        }
    }
}
