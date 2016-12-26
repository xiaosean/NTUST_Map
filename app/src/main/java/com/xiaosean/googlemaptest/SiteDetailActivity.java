package com.xiaosean.googlemaptest;

import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SiteDetailActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private Button button;
    private TextView actionBarTitle, title, content, date;
    private RelativeLayout layout;
    private List<List<String>> siteDict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_detail);

        actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.activity_detail_actionbar);
        actionBarTitle = (TextView) actionBar.getCustomView().findViewById(R.id.actionbar_textview);
//        final Drawable upArrow = getResources().getDrawable(R.drawable.icon_back);
//        actionBar.setHomeAsUpIndicator(upArrow);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setElevation(2);
        setDict();
        processViews();
        processControllers();
    }
    private void setDict() {
        siteDict = new ArrayList<>();
        siteDict.add(new ArrayList<String>(
                Arrays.asList("烏龜池", "我就是愛玩水")));
        siteDict.add(new ArrayList<String>(
                Arrays.asList("大砲池", "我就是愛嘴砲")));
        siteDict.add(new ArrayList<String>(
                Arrays.asList("生態池", "我就是愛吃菜")));
    }
    private void processViews() {
        layout = (RelativeLayout) findViewById(R.id.site_detail_layout);
        button = (Button) findViewById(R.id.site_detail_button);
        title = (TextView) findViewById(R.id.site_detail_title);
        content = (TextView) findViewById(R.id.site_detail_content);
    }

    private void processControllers() {
        // set relative layout height
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        // get action bar height
        final TypedArray styledAttributes = getApplicationContext().getTheme().obtainStyledAttributes(
                new int[]{android.R.attr.actionBarSize});
        int mActionBarSize = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
        Log.d("actionBarHeight", String.valueOf(mActionBarSize));
        layout.getLayoutParams().height = Math.round(displayMetrics.heightPixels - mActionBarSize - 32 * displayMetrics.density);
        setData();
    }

    private void setData() {
        Intent intent = getIntent();
        int siteId = intent.getIntExtra("siteId", 0);
        title.setText(siteDict.get(siteId).get(0));
        content.setText(siteDict.get(siteId).get(1));

        button.setVisibility(View.INVISIBLE);

//        if (link.equals("")) {
//            button.setVisibility(View.INVISIBLE);
//        } else {
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    goToUrl(link);
//                }
//            });
//        }
    }

    private void goToUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // this takes the user 'back', as if they pressed the left-facing triangle icon on the main android toolbar.
                // if this doesn't work as desired, another possibility is to call `finish()` here.
                SiteDetailActivity.this.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
