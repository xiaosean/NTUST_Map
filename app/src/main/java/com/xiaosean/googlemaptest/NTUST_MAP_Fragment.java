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
    TextView txtcanon, txtturtle, txtnature;
    Button btnHome, btnVin;
    ShowMap mapFrag;
    //    String phoneNo = "0919451088";
    String[] ntustGPS = {"25.013310&&121.539263",
            "25.015941&&121.542484",
            "25.012537&&121.545178",
            "25.010272&&121.541745"};
    double[][] pool_locations = {{25.013554,121.540795},{25.014100,121.541816},{25.013396,121.542155}};
    double[] distance = {0, 0, 0};
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
            txtcanon = (TextView) rootView.findViewById(R.id.canon);
            txtturtle = (TextView) rootView.findViewById(R.id.turtle);
            txtnature = (TextView) rootView.findViewById(R.id.nature);
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

    public void checkLocation(Location loc) {
        mostRecentLocation = loc;
        //String temp = "緯度: " + mostRecentLocation.getLatitude();
        //txtLat.setText(temp);
        //temp = "經度: " + mostRecentLocation.getLongitude();
        //txtLon.setText(temp);
        checkDistance();
    }

    private void checkDistance() {
        String currPoint = mostRecentLocation.getLatitude() + "&&" + mostRecentLocation.getLongitude();

        double[] point1 = new double[]{mostRecentLocation.getLatitude(), mostRecentLocation.getLongitude()};

        for (int i = 0; i < 3; i++) {

            double[] point2 = new double[]{(double) pool_locations[i][0], (double) pool_locations[i][1]};
            distance[i] = distHaversine(point1, point2);
        }
        txtcanon.setText(String.format("%.4f", distance[0]));
        txtturtle.setText(String.format("%.4f", distance[1]));
        txtnature.setText(String.format("%.4f", distance[2]));
    }

    private double distHaversine(double[] p1, double[] p2) {
        double radius = (6356.752 + 6378.137) / 2;
        if (p1[0] > -23.5 && p1[0] < 23.5 && p2[0] > -23.5 && p2[0] < 23.5)
            radius = 6378.137;
        else if ((p1[0] < -66.5 && p2[0] < -66.5) || (p1[0] > 66.5 && p2[0] > 66.5))
            radius = 6356.752;
        double distLat = rad2deg(p2[0] - p1[0]);
        double distLon = rad2deg(p2[1] - p1[1]);
        double a = Math.sin(distLat / 2) * Math.sin(distLat / 2) + Math.cos(rad2deg(p1[0])) * Math.cos(rad2deg(p2[0])) *
                Math.sin(distLon / 2) * Math.sin(distLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return radius * c;
    }

    private double rad2deg(double ran) {
        return ran * Math.PI / 180;
    }

    private double areaCal(String loc1, String loc2, String loc0) {
        String[] point1, point2, point0;
        point1 = loc1.split("&&");
        point2 = loc2.split("&&");
        point0 = loc0.split("&&");
        double x1 = Double.parseDouble(point1[0]);
        double y1 = Double.parseDouble(point1[1]);
        double x2 = Double.parseDouble(point2[0]);
        double y2 = Double.parseDouble(point2[1]);
        double x0 = Double.parseDouble(point0[0]);
        double y0 = Double.parseDouble(point0[1]);
        return (x1 * y0 + x0 * y2 + x2 * y1) - (x0 * y1 + x2 * y0 + x1 * y2);
    }
}
