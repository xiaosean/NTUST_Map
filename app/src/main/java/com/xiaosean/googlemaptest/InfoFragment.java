package com.xiaosean.googlemaptest;

/**
 * Created by Xiao on 2016/12/25.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

import java.util.List;


public class InfoFragment extends Fragment {
    private View rootView;
    private LinearLayout myContainer;
    private static int POLICY_SWIPE_LAYOUT_ID = 3000;
    private static int POLICY_DIVIDER_ID = 5000;
    private Button clearBtn;
    private boolean lock = false;
    private final String SUCCESS = "達成", FAILED = "尚未達成";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }

        try {
            rootView = inflater.inflate(R.layout.fragment_info, container, false);
//            rootView.scrollTo(0,0);
        } catch (InflateException e) {
            //already created, just return
//            Log.d("inflateFail", "inflate Failed");
        }
        processViews(rootView);
        processControllers(rootView);
        return rootView;
    }

    private void processViews(View view) {
        myContainer = (LinearLayout) view.findViewById(R.id.fragment_info_container);
//        clearBtn = (Button) view.findViewById(R.id.fragment_info_clear_btn);
    }

    private void processControllers(View view) {
//        clearBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                resetAllSite();
//
//            }
//        });
        myContainer.addView(newDivider(0));
        List siteList =((GlobalVariable) getActivity().getApplication()).getSiteInfoList();
        for(Object eachList: siteList){
            List list = (List) eachList;
            addSite((String)list.get(0), (String)list.get(2), (int)list.get(3));
        }


    }

    private void addSite(final String type, String value, final int siteId) {
        final SwipeLayout swipeLayout = newCustomSwipeLayout(type, value, siteId);
//        swipeLayout.setDrawingCacheBackgroundColor(getResources().getColor(R.color.inner_gray));
//        if(value.equals(SUCCESS))
//            swipeLayout.setDrawingCacheBackgroundColor(getResources().getColor(R.color.background_green_gradient_start));
        final View div = newDivider(siteId);
        myContainer.addView(swipeLayout);
        myContainer.addView(div);
        swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        TextView delete = (TextView) swipeLayout.findViewById(R.id.swipe_del_button);
        final TextView swipeValue = (TextView) swipeLayout.findViewById(R.id.swipe_value);
//        LinearLayout bar = (LinearLayout) swipeLayout.findViewById(R.id.swipe_layout);
        swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {
                Thread th = new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        lock = true;
                        try {
                            sleep(1000);
                        } catch (Exception e) {
                        }
                        lock = false;
                    }
                };
                th.start();
            }

            @Override
            public void onOpen(SwipeLayout layout) {

            }

            @Override
            public void onStartClose(SwipeLayout layout) {
                Thread th = new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        lock = true;
                        try {
                            sleep(1000);
                        } catch (Exception e) {
                        }
                        lock = false;
                    }
                };
                th.start();
            }

            @Override
            public void onClose(SwipeLayout layout) {

            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        });
        swipeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("test swipeLayout", String.valueOf(siteId));
                //Can watch video
                if (swipeValue.getText().toString().equals(SUCCESS))
                    if (!lock) {
                        Intent intent = new Intent(getActivity(), SiteDetailActivity.class);
                        intent.putExtra("siteId", siteId);
                        startActivity(intent);
                    }

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("click", "del");
                ((GlobalVariable) getActivity().getApplication()).setFAILED(siteId);
                swipeValue.setText(FAILED);
                swipeLayout.setBackgroundColor(getResources().getColor(R.color.inner_gray));

            }
        });
    }

    private void resetAllSite() {
        try {
            addSite("大砲池", "尚未達成", 0);
            addSite("烏龜池", "尚未達成", 1);
            addSite("生態池", "尚未達成", 2);
        } catch (Exception e) {

        }
    }

    private View newDivider(int siteId) {
        float scale = getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (1 * scale + 0.5f);
        View div = new View(getContext());
        div.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dpAsPixels));
        div.setBackgroundColor(getResources().getColor(R.color.member_detail_divider));
        div.setId(POLICY_DIVIDER_ID + siteId);
        return div;
    }

    private SwipeLayout newCustomSwipeLayout(String type, String value, int siteId) {
        float scale = getResources().getDisplayMetrics().density;
        int dpAsPixels;

        Context context = getContext();
        SwipeLayout swipe = new SwipeLayout(context);
        dpAsPixels = (int) (46 * scale + 0.5f);
        swipe.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dpAsPixels));
        swipe.setId(POLICY_SWIPE_LAYOUT_ID + siteId);
        //bottom
        LinearLayout bottom = new LinearLayout(context);
        bottom.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        bottom.setId(R.id.swipe_layout);
        TextView delete = new TextView(context);
        delete.setText("Reset");
        delete.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
        dpAsPixels = (int) (8 * scale + 0.5f);
        delete.setPadding(dpAsPixels, 0, dpAsPixels, 0);
        delete.setGravity(Gravity.CENTER);
        delete.setBackgroundColor(getResources().getColor(R.color.delete_button_color));
        delete.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        delete.setId(R.id.swipe_del_button);
        bottom.addView(delete);
        swipe.addView(bottom);

        //surface
        LinearLayout surface = new LinearLayout(context);
        surface.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        TextView iType = new TextView(context);
        iType.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        iType.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
        dpAsPixels = (int) (16 * scale + 0.5f);
        iType.setPadding(dpAsPixels, 0, 0, 0);
        iType.setText(type);
        iType.setTextColor(getResources().getColor(R.color.member_detail_textView));
        iType.setTextSize(14);
        iType.setBackgroundColor(getResources().getColor(R.color.white_background));
        surface.addView(iType);

        TextView iValue = new TextView(context);
        iValue.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        iValue.setGravity(Gravity.CENTER_VERTICAL | Gravity.END);
        iValue.setPadding(0, 0, dpAsPixels, 0);
        iValue.setText(value);
        iValue.setTextColor(getResources().getColor(R.color.member_detail_textView));
        iValue.setTextSize(20);
        iValue.setBackgroundColor(getResources().getColor(R.color.white_background));
        iValue.setId(R.id.swipe_value);
        surface.addView(iValue);
        swipe.addView(surface);
        if(value.equals(FAILED))
            swipe.setEnabled(false);
        return swipe;
    }

}
