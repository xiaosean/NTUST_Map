package com.xiaosean.googlemaptest;

/**
 * Created by Xiao on 2016/12/25.
 */

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class NTUST_MAP_Fragment extends Fragment {
    private View rootView;
    TextView txtGoal;
    Button btnHome, btnVin;
    ShowMap mapFrag;
    //    String phoneNo = "0919451088";
    String[] ntustGPS = {"25.013310&&121.539263",
            "25.015941&&121.542484",
            "25.012537&&121.545178",
            "25.010272&&121.541745"};
    double[] ntustCenter = {25.013421, 121.541785};
    double outCampustDist = 0;
    int lastStatus = 1, currStatus = 1;
    Location mostRecentLocation = null;

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
            rootView = inflater.inflate(R.layout.fragment_ntust_map, container, false);
//            rootView.scrollTo(0,0);
        } catch (InflateException e) {
            //already created, just return
//            Log.d("inflateFail", "inflate Failed");
        }
        processViews(rootView);
        processControllers(rootView);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flMap, mapFrag).commit();

        return rootView;
    }

    private void processViews(View view) {
        txtGoal = (TextView) view.findViewById(R.id.fragment_ntust_map_goal);
        btnVin = (Button) view.findViewById(R.id.btnVin);
        mapFrag = ShowMap.newInstance(ntustCenter[0], ntustCenter[1]);

    }

    private void processControllers(View view) {
        btnVin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ShowMap) mapFrag).updataePlaces();
            }
        });
    }
}
