package com.xiaosean.googlemaptest;

import android.content.Intent;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
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

import com.afollestad.easyvideoplayer.EasyVideoCallback;
import com.afollestad.easyvideoplayer.EasyVideoPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SiteDetailActivity extends AppCompatActivity  implements EasyVideoCallback {

    private ActionBar actionBar;
    private Button button;
    private TextView actionBarTitle, title, content, date;
    private RelativeLayout layout;
    private List<List<String>> siteDict;
    private EasyVideoPlayer vPlayer;

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
        processViews();
        processControllers();
    }
    private void processViews() {
        title = (TextView) findViewById(R.id.site_detail_title);
        vPlayer = (EasyVideoPlayer) findViewById(R.id.site_detail_video);
        content = (TextView) findViewById(R.id.site_detail_content);
    }

    private void processControllers() {
        // set relative layout height
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        // get action bar height
        // Sets the callback to this Activity, since it inherits EasyVideoCallback
        vPlayer.setBackgroundColor(getResources().getColor(R.color.white_background));
        vPlayer.setCallback(this);
        // set video title/conten/video
        setData();
    }
    private void setDict() {
        siteDict = new ArrayList<>();
        //index 0
        siteDict.add(new ArrayList<String>(
                Arrays.asList("大砲池", "我就是愛嘴砲", String.valueOf(R.raw.bomb))));
        //index 1
        siteDict.add(new ArrayList<String>(
                Arrays.asList("烏龜池", "我就是愛玩水", String.valueOf(R.raw.turtle1))));
        //index 2
        siteDict.add(new ArrayList<String>(
                Arrays.asList("生態池", "我就是愛吃菜", String.valueOf(R.raw.turtle2))));
    }
    private void setData() {
        //get each site title/content/video_id
        setDict();

        Intent intent = getIntent();
        int siteId = intent.getIntExtra("siteId", 0);
        title.setText(siteDict.get(siteId).get(0));
        content.setText(siteDict.get(siteId).get(1));
// Sets the source to the HTTP URL held in the TEST_URL variable.
        // To play files, you can use Uri.fromFile(new File("..."))
        int videoId = Integer.valueOf(siteDict.get(siteId).get(2)); //videoId
        String path = "android.resource://" + getPackageName() + "/" + videoId;
        vPlayer.setSource(Uri.parse(path));
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
    @Override
    public void onPause() {
        super.onPause();
        // Make sure the player stops playing if the user presses the home button.
        vPlayer.pause();
    }

    // Methods for the implemented EasyVideoCallback

    @Override
    public void onPreparing(EasyVideoPlayer player) {
        // TODO handle if needed
    }

    @Override
    public void onPrepared(EasyVideoPlayer player) {
        // TODO handle
    }

    @Override
    public void onBuffering(int percent) {
        // TODO handle if needed
    }

    @Override
    public void onError(EasyVideoPlayer player, Exception e) {
        // TODO handle
    }

    @Override
    public void onCompletion(EasyVideoPlayer player) {
        // TODO handle if needed
    }

    @Override
    public void onRetry(EasyVideoPlayer player, Uri source) {
        // TODO handle if used
    }

    @Override
    public void onSubmit(EasyVideoPlayer player, Uri source) {
        // TODO handle if used
    }

    @Override
    public void onStarted(EasyVideoPlayer player) {
        // TODO handle if needed
    }

    @Override
    public void onPaused(EasyVideoPlayer player) {
        // TODO handle if needed
    }
}
