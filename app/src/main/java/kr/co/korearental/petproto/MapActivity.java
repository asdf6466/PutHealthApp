package kr.co.korearental.petproto;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.skt.Tmap.TMapCircle;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapData.FindAroundNamePOIListenerCallback;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;


public class MapActivity extends AppCompatActivity implements TMapGpsManager.onLocationChangedCallback {

    TMapPoint current;
    TMapPoint tempCurrent;
    ProgressDialog customProgressDialog;

    private double currentLatitude;
    private double currentLongitude;
    private EditText editText;
    String preText;
    int num;
    String API_Key = "l7xxea3a0e6f29234a6592f485019ed2a994";

    TMapView tMapView = null;
    TMapGpsManager tMapGPS = null;
    DecimalFormat form = new DecimalFormat("#.##");





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        customProgressDialog = new ProgressDialog(this);
        customProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customProgressDialog.setCancelable(false);

        customProgressDialog.show();



        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey(API_Key);
        tMapView.setZoomLevel(15);
        tMapView.setIconVisibility(true);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        LinearLayout linearLayoutTmap = (LinearLayout)findViewById(R.id.linearLayoutTmap);
        linearLayoutTmap.addView(tMapView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        tMapGPS = new TMapGpsManager(this);

        //tMapGPS.setMinTime(1000);
        tMapGPS.setMinDistance(999999999);
        tMapGPS.setProvider(tMapGPS.NETWORK_PROVIDER);
        tMapGPS.setProvider(tMapGPS.GPS_PROVIDER);

        tMapGPS.OpenGps();

    }

    @Override
    public void onLocationChange(Location location) {
        Intent intent = getIntent();
        final ArrayList<String> list = intent.getExtras().getStringArrayList("route");
        final ArrayList<String> list2 = intent.getExtras().getStringArrayList("route2");
        int count = intent.getExtras().getInt("count");
        int count2 = intent.getExtras().getInt("count2");
        //Toast.makeText(MapActivity.this,list.toString(),Toast.LENGTH_SHORT).show();
        final ArrayList<TMapPoint> arrTMapPoint = new ArrayList<>();
        final ArrayList<TMapPoint> arrTMapPoint1 = new ArrayList<>();
        final ArrayList<TMapPoint> arrTMapPoint2 = new ArrayList<>();
        final ArrayList<TMapPoint> arrTMapPoint3 = new ArrayList<>();
        final ArrayList<TMapPoint> arrTMapPoint4 = new ArrayList<>();
        final ArrayList<String> arrTitle = new ArrayList<>();
        final ArrayList<String> arrTitle1 = new ArrayList<>();
        final ArrayList<String> arrTitle2 = new ArrayList<>();
        final ArrayList<String> arrTitle3 = new ArrayList<>();
        final ArrayList<String> arrTitle4 = new ArrayList<>();
        final ArrayList<String> arrAddress = new ArrayList<>();
        final ArrayList<String> arrAddress1 = new ArrayList<>();
        final ArrayList<String> arrAddress2 = new ArrayList<>();
        final ArrayList<String> arrAddress3 = new ArrayList<>();
        final ArrayList<String> arrAddress4 = new ArrayList<>();

        arrTMapPoint1.clear();
        arrTMapPoint2.clear();
        arrTMapPoint3.clear();
        arrTMapPoint4.clear();
        arrTitle1.clear();
        arrTitle2.clear();
        arrTitle3.clear();
        arrTitle4.clear();
        arrAddress1.clear();
        arrAddress2.clear();
        arrAddress3.clear();
        arrAddress4.clear();
        tMapView.removeAllMarkerItem();
        tMapView.removeAllTMapPOIItem();

        final Intent intent2 = new Intent(getApplicationContext(), MapchoiceActivity.class);
        final Toast myToast = Toast.makeText(this.getApplicationContext(),"주변에 장소가 없습니다.",Toast.LENGTH_SHORT);
        String temp;

        intent2.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent2.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);

        tMapView.setLocationPoint(location.getLongitude(), location.getLatitude());
        tMapView.setCenterPoint(location.getLongitude(), location.getLatitude());
        final TMapData mapData = new TMapData();

        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();

        TMapPoint tMapPoint = new TMapPoint(currentLatitude, currentLongitude);
        tempCurrent = tMapGPS.getLocation();
        current = tempCurrent;
        tMapView.setZoomLevel(15);


        TMapCircle tMapCircle = new TMapCircle();
        tMapCircle.setCenterPoint(tMapPoint);
        tMapCircle.setRadius(1000);
        tMapCircle.setCircleWidth(2);
        tMapCircle.setLineColor(Color.BLUE);
        tMapCircle.setAreaColor(Color.GRAY);
        tMapCircle.setAreaAlpha(100);
        tMapView.addTMapCircle("circle1", tMapCircle);

        try {
            if (count == 1&&count2==0) {
                mapData.findAroundNamePOI(current, list.get(0), 1, 100, new FindAroundNamePOIListenerCallback() {

                    @Override
                    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItems) {
                        //Log.v("tag", "안됨");
                        if(poiItems==null){
                            myToast.setText(list.get(0)+"이(가) 없습니다.");
                            myToast.show();
                            //Log.v("tag", "안됨2");
                            customProgressDialog.dismiss();
                            finishAffinity();
                            startActivity(intent2);
                        }
                        else {
                            //Log.v("tag", "안됨3");
                            for (int i = 0; i < poiItems.size(); i++) {
                                TMapPOIItem item = poiItems.get(i);
                                arrTMapPoint1.add(item.getPOIPoint());
                                arrTitle1.add(item.getPOIName());
                                arrAddress1.add(item.upperAddrName + " " +
                                        item.middleAddrName + " " + item.lowerAddrName);
                            }
                            setMultiMarkers(arrTMapPoint1, arrTitle1, arrAddress1, list.get(0));
                            findPath(arrTMapPoint1);
                            customProgressDialog.dismiss();
                        }
                    }
                });

            } else if (count == 2&&count2==0) {
                mapData.findAroundNamePOI(current, list.get(0).toString(), 1, 100, new FindAroundNamePOIListenerCallback() {
                    @Override
                    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems==null){
                            myToast.setText(list.get(0)+"이(가) 없습니다.");
                            myToast.show();
                            customProgressDialog.dismiss();
                            finishAffinity();
                            startActivity(intent2);

                        }
                        else {
                            for (int i = 0; i < poiItems.size(); i++) {
                                TMapPOIItem item = poiItems.get(i);
                                //arrTMapPoint.add(item.getPOIPoint());
                                arrTMapPoint1.add(item.getPOIPoint());
                                arrTitle1.add(item.getPOIName());
                                arrAddress1.add(item.upperAddrName + " " +
                                        item.middleAddrName + " " + item.lowerAddrName);
                            }
                            setMultiMarkers(arrTMapPoint1, arrTitle1, arrAddress1, list.get(0));
                        }
                    }
                });

                mapData.findAroundNamePOI(current, list.get(1).toString(), 1, 100, new FindAroundNamePOIListenerCallback() {
                    @Override
                    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems==null){
                            myToast.setText(list.get(1)+"이(가) 없습니다.");
                            myToast.show();
                            customProgressDialog.dismiss();
                            finishAffinity();
                            startActivity(intent2);

                        }
                        else {
                            for (int i = 0; i < poiItems.size(); i++) {
                                TMapPOIItem item = poiItems.get(i);
                                //arrTMapPoint.add(item.getPOIPoint());
                                arrTMapPoint2.add(item.getPOIPoint());
                                arrTitle2.add(item.getPOIName());
                                arrAddress2.add(item.upperAddrName + " " +
                                        item.middleAddrName + " " + item.lowerAddrName);
                            }
                            Log.v("tag", "처리됨");
                            setMultiMarkers(arrTMapPoint2, arrTitle2, arrAddress2, list.get(1));
                            findPath(arrTMapPoint1, arrTMapPoint2);
                            customProgressDialog.dismiss();
                        }

                    }
                });

            } else if (count == 3&&count2==0) {
                mapData.findAroundNamePOI(current, list.get(0).toString(), 1, 100, new FindAroundNamePOIListenerCallback() {
                    @Override
                    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems==null){
                            myToast.setText(list.get(0)+"이(가) 없습니다.");
                            myToast.show();
                            customProgressDialog.dismiss();
                            finishAffinity();
                            startActivity(intent2);

                        }
                        else {
                            for (int i = 0; i < poiItems.size(); i++) {
                                TMapPOIItem item = poiItems.get(i);
                                //arrTMapPoint.add(item.getPOIPoint());
                                arrTMapPoint1.add(item.getPOIPoint());
                                arrTitle1.add(item.getPOIName());
                                arrAddress1.add(item.upperAddrName + " " +
                                        item.middleAddrName + " " + item.lowerAddrName);

                            }

                            setMultiMarkers(arrTMapPoint1, arrTitle1, arrAddress1, list.get(0));
                        }

                    }
                });

                mapData.findAroundNamePOI(current, list.get(1).toString(), 1, 100, new FindAroundNamePOIListenerCallback() {
                    @Override
                    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems==null){
                            myToast.setText(list.get(1)+"이(가) 없습니다.");
                            myToast.show();
                            customProgressDialog.dismiss();
                            finishAffinity();
                            startActivity(intent2);

                        }
                        else {
                            for (int i = 0; i < poiItems.size(); i++) {
                                TMapPOIItem item = poiItems.get(i);
                                //arrTMapPoint.add(item.getPOIPoint());
                                arrTMapPoint2.add(item.getPOIPoint());
                                arrTitle2.add(item.getPOIName());
                                arrAddress2.add(item.upperAddrName + " " +
                                        item.middleAddrName + " " + item.lowerAddrName);
                            }

                            setMultiMarkers(arrTMapPoint2, arrTitle2, arrAddress2, list.get(1));
                        }

                    }
                });

                mapData.findAroundNamePOI(current, list.get(2).toString(), 1, 100, new FindAroundNamePOIListenerCallback() {
                    @Override
                    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems==null){
                            myToast.setText(list.get(2)+"이(가) 없습니다.");
                            myToast.show();
                            customProgressDialog.dismiss();
                            finishAffinity();
                            startActivity(intent2);


                        }
                        else {
                            for (int i = 0; i < poiItems.size(); i++) {
                                TMapPOIItem item = poiItems.get(i);
                                //arrTMapPoint.add(item.getPOIPoint());
                                arrTMapPoint3.add(item.getPOIPoint());
                                arrTitle3.add(item.getPOIName());
                                arrAddress3.add(item.upperAddrName + " " +
                                        item.middleAddrName + " " + item.lowerAddrName);
                            }

                            setMultiMarkers(arrTMapPoint3, arrTitle3, arrAddress3, list.get(2));
                            findPath(arrTMapPoint1, arrTMapPoint2, arrTMapPoint3);
                            customProgressDialog.dismiss();
                        }

                    }
                });
            } else if (count == 4&&count2==0) {
                mapData.findAroundNamePOI(current, list.get(0).toString(), 1, 100, new FindAroundNamePOIListenerCallback() {
                    @Override
                    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems==null){
                            myToast.setText(list.get(0)+"이(가) 없습니다.");
                            myToast.show();
                            customProgressDialog.dismiss();
                            finishAffinity();
                            startActivity(intent2);


                        }
                        else {
                            for (int i = 0; i < poiItems.size(); i++) {
                                TMapPOIItem item = poiItems.get(i);
                                //arrTMapPoint.add(item.getPOIPoint());
                                arrTMapPoint1.add(item.getPOIPoint());
                                arrTitle1.add(item.getPOIName());
                                arrAddress1.add(item.upperAddrName + " " +
                                        item.middleAddrName + " " + item.lowerAddrName);
                            }

                            setMultiMarkers(arrTMapPoint1, arrTitle1, arrAddress1, list.get(0));
                        }

                    }
                });

                mapData.findAroundNamePOI(current, list.get(1).toString(), 1, 100, new FindAroundNamePOIListenerCallback() {
                    @Override
                    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems==null){
                            myToast.setText(list.get(1)+"이(가) 없습니다.");
                            myToast.show();
                            customProgressDialog.dismiss();
                            finishAffinity();
                            startActivity(intent2);


                        }
                        else {
                            for (int i = 0; i < poiItems.size(); i++) {
                                TMapPOIItem item = poiItems.get(i);
                                //arrTMapPoint.add(item.getPOIPoint());
                                arrTMapPoint2.add(item.getPOIPoint());
                                arrTitle2.add(item.getPOIName());
                                arrAddress2.add(item.upperAddrName + " " +
                                        item.middleAddrName + " " + item.lowerAddrName);
                            }

                            setMultiMarkers(arrTMapPoint2, arrTitle2, arrAddress2, list.get(1));
                        }

                    }
                });

                mapData.findAroundNamePOI(current, list.get(2).toString(), 1, 100, new FindAroundNamePOIListenerCallback() {
                    @Override
                    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems==null){
                            myToast.setText(list.get(2)+"이(가) 없습니다.");
                            myToast.show();
                            customProgressDialog.dismiss();
                            finishAffinity();
                            startActivity(intent2);


                        }
                        else {
                            for (int i = 0; i < poiItems.size(); i++) {
                                TMapPOIItem item = poiItems.get(i);
                                //arrTMapPoint.add(item.getPOIPoint());
                                arrTMapPoint3.add(item.getPOIPoint());
                                arrTitle3.add(item.getPOIName());
                                arrAddress3.add(item.upperAddrName + " " +
                                        item.middleAddrName + " " + item.lowerAddrName);
                            }

                            setMultiMarkers(arrTMapPoint3, arrTitle3, arrAddress3, list.get(2));
                        }

                    }
                });

                mapData.findAroundNamePOI(current, list.get(3).toString(), 1, 100, new FindAroundNamePOIListenerCallback() {
                    @Override
                    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems==null){
                            myToast.setText(list.get(3)+"이(가) 없습니다.");
                            myToast.show();
                            customProgressDialog.dismiss();
                            finishAffinity();
                            startActivity(intent2);


                        }
                        else {
                            for (int i = 0; i < poiItems.size(); i++) {
                                TMapPOIItem item = poiItems.get(i);
                                //arrTMapPoint.add(item.getPOIPoint());
                                arrTMapPoint4.add(item.getPOIPoint());
                                arrTitle4.add(item.getPOIName());
                                arrAddress4.add(item.upperAddrName + " " +
                                        item.middleAddrName + " " + item.lowerAddrName);
                            }

                            setMultiMarkers(arrTMapPoint4, arrTitle4, arrAddress4, list.get(3));
                            findPath(arrTMapPoint1, arrTMapPoint2, arrTMapPoint3, arrTMapPoint4);
                            customProgressDialog.dismiss();
                        }

                    }
                });



            }

            else if (count2==1&&count==0){
                /*mapData.findTitlePOI(list2.get(0), new TMapData.FindTitlePOIListenerCallback() {
                    @Override
                    public void onFindTitlePOI(ArrayList<TMapPOIItem> poiItems) {
                        for(int i=0;i<poiItems.size();i++){
                            TMapPOIItem item = poiItems.get(i);
                            arrTMapPoint1.add(item.getPOIPoint());
                            arrTitle1.add(item.getPOIName());
                            arrAddress1.add(item.upperAddrName + " " +
                                    item.middleAddrName + " " + item.lowerAddrName);
                        }
                        setMultiMarkers(arrTMapPoint1, arrTitle1, arrAddress1, list2.get(0));
                        findPath(arrTMapPoint1);
                        customProgressDialog.dismiss();
                    }
                });*/

                mapData.findAroundKeywordPOI(current, list2.get(0), 1, 10, new TMapData.FindAroundKeywordPOIListenerCallback() {

                    @Override
                    public void onFindAroundKeywordPOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems.size()==0){
                            myToast.setText(list2.get(0)+"이(가) 없습니다.");
                            myToast.show();
                            customProgressDialog.dismiss();
                            finishAffinity();
                            startActivity(intent2);


                        }
                        else {
                            for (int i = 0; i < poiItems.size(); i++) {
                                TMapPOIItem item = poiItems.get(i);
                                arrTMapPoint1.add(item.getPOIPoint());
                                arrTitle1.add(item.getPOIName());
                                arrAddress1.add(item.upperAddrName + " " +
                                        item.middleAddrName + " " + item.lowerAddrName);
                            }
                            setMultiMarkers(arrTMapPoint1, arrTitle1, arrAddress1, list2.get(0));
                            findPath(arrTMapPoint1);
                            customProgressDialog.dismiss();
                        }
                    }
                });
            }

            else if(count2==1&&count==1){
                /*mapData.findTitlePOI(list2.get(0), new TMapData.FindTitlePOIListenerCallback() {
                    @Override
                    public void onFindTitlePOI(ArrayList<TMapPOIItem> poiItems) {
                        for(int i=0;i<poiItems.size();i++){
                            TMapPOIItem item = poiItems.get(i);
                            arrTMapPoint1.add(item.getPOIPoint());
                            arrTitle1.add(item.getPOIName());
                            arrAddress1.add(item.upperAddrName + " " +
                                    item.middleAddrName + " " + item.lowerAddrName);
                        }
                        setMultiMarkers(arrTMapPoint1, arrTitle1, arrAddress1, list2.get(0));

                    }
                });*/

                mapData.findAroundKeywordPOI(current, list2.get(0), 1, 10, new TMapData.FindAroundKeywordPOIListenerCallback() {

                    @Override
                    public void onFindAroundKeywordPOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems.size()==0){
                            myToast.setText(list2.get(0)+"이(가) 없습니다.");
                            myToast.show();
                            customProgressDialog.dismiss();
                            finishAffinity();
                            startActivity(intent2);


                        }
                        else {
                            for (int i = 0; i < poiItems.size(); i++) {
                                TMapPOIItem item = poiItems.get(i);
                                arrTMapPoint1.add(item.getPOIPoint());
                                arrTitle1.add(item.getPOIName());
                                arrAddress1.add(item.upperAddrName + " " +
                                        item.middleAddrName + " " + item.lowerAddrName);
                            }
                            setMultiMarkers(arrTMapPoint1, arrTitle1, arrAddress1, list2.get(0));
                        }
                    }
                });

                mapData.findAroundNamePOI(current, list.get(0).toString(), 1, 100, new FindAroundNamePOIListenerCallback() {
                    @Override
                    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems==null){
                            myToast.setText(list.get(0)+"이(가) 없습니다.");
                            myToast.show();
                            customProgressDialog.dismiss();
                            finishAffinity();
                            startActivity(intent2);


                        }
                        else {
                            for (int i = 0; i < poiItems.size(); i++) {
                                TMapPOIItem item = poiItems.get(i);
                                //arrTMapPoint.add(item.getPOIPoint());
                                arrTMapPoint2.add(item.getPOIPoint());
                                arrTitle2.add(item.getPOIName());
                                arrAddress2.add(item.upperAddrName + " " +
                                        item.middleAddrName + " " + item.lowerAddrName);
                            }

                            setMultiMarkers(arrTMapPoint2, arrTitle2, arrAddress2, list.get(0));
                            findPath(arrTMapPoint1,arrTMapPoint2);
                            customProgressDialog.dismiss();
                        }

                    }
                });

            }
            else if(count2==1&&count==2){

                /*mapData.findTitlePOI(list2.get(0), new TMapData.FindTitlePOIListenerCallback() {
                    @Override
                    public void onFindTitlePOI(ArrayList<TMapPOIItem> poiItems) {
                        for(int i=0;i<poiItems.size();i++){
                            TMapPOIItem item = poiItems.get(i);
                            arrTMapPoint1.add(item.getPOIPoint());
                            arrTitle1.add(item.getPOIName());
                            arrAddress1.add(item.upperAddrName + " " +
                                    item.middleAddrName + " " + item.lowerAddrName);
                        }
                        setMultiMarkers(arrTMapPoint1, arrTitle1, arrAddress1, list2.get(0));

                    }
                });*/
                mapData.findAroundKeywordPOI(current, list2.get(0), 1, 10, new TMapData.FindAroundKeywordPOIListenerCallback() {

                    @Override
                    public void onFindAroundKeywordPOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems.size()==0){
                            myToast.setText(list2.get(0)+"이(가) 없습니다.");
                            myToast.show();
                            customProgressDialog.dismiss();
                            finishAffinity();
                            startActivity(intent2);


                        }
                        else {
                            for (int i = 0; i < poiItems.size(); i++) {
                                TMapPOIItem item = poiItems.get(i);
                                arrTMapPoint1.add(item.getPOIPoint());
                                arrTitle1.add(item.getPOIName());
                                arrAddress1.add(item.upperAddrName + " " +
                                        item.middleAddrName + " " + item.lowerAddrName);
                            }
                            setMultiMarkers(arrTMapPoint1, arrTitle1, arrAddress1, list2.get(0));
                        }
                    }
                });

                mapData.findAroundNamePOI(current, list.get(0).toString(), 1, 100, new FindAroundNamePOIListenerCallback() {
                    @Override
                    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems==null){
                            myToast.setText(list.get(0)+"이(가) 없습니다.");
                            myToast.show();
                            customProgressDialog.dismiss();
                            finishAffinity();
                            startActivity(intent2);


                        }
                        else {
                            for (int i = 0; i < poiItems.size(); i++) {
                                TMapPOIItem item = poiItems.get(i);
                                //arrTMapPoint.add(item.getPOIPoint());
                                arrTMapPoint2.add(item.getPOIPoint());
                                arrTitle2.add(item.getPOIName());
                                arrAddress2.add(item.upperAddrName + " " +
                                        item.middleAddrName + " " + item.lowerAddrName);
                            }

                            setMultiMarkers(arrTMapPoint2, arrTitle2, arrAddress2, list.get(0));
                        }

                    }
                });

                mapData.findAroundNamePOI(current, list.get(1).toString(), 1, 100, new FindAroundNamePOIListenerCallback() {
                    @Override
                    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems==null){
                            myToast.setText(list.get(1)+"이(가) 없습니다.");
                            myToast.show();
                            customProgressDialog.dismiss();
                            finishAffinity();
                            startActivity(intent2);


                        }
                        else {
                            for (int i = 0; i < poiItems.size(); i++) {
                                TMapPOIItem item = poiItems.get(i);
                                //arrTMapPoint.add(item.getPOIPoint());
                                arrTMapPoint3.add(item.getPOIPoint());
                                arrTitle3.add(item.getPOIName());
                                arrAddress3.add(item.upperAddrName + " " +
                                        item.middleAddrName + " " + item.lowerAddrName);
                            }

                            setMultiMarkers(arrTMapPoint3, arrTitle3, arrAddress3, list.get(1));
                            findPath(arrTMapPoint1,arrTMapPoint2,arrTMapPoint3);
                            customProgressDialog.dismiss();
                        }

                    }
                });

            }
            else if(count2==1&&count==3){

                /*mapData.findTitlePOI(list2.get(0), new TMapData.FindTitlePOIListenerCallback() {
                    @Override
                    public void onFindTitlePOI(ArrayList<TMapPOIItem> poiItems) {
                        for(int i=0;i<poiItems.size();i++){
                            TMapPOIItem item = poiItems.get(i);
                            arrTMapPoint1.add(item.getPOIPoint());
                            arrTitle1.add(item.getPOIName());
                            arrAddress1.add(item.upperAddrName + " " +
                                    item.middleAddrName + " " + item.lowerAddrName);
                        }
                        setMultiMarkers(arrTMapPoint1, arrTitle1, arrAddress1, list2.get(0));

                    }
                });*/

                mapData.findAroundKeywordPOI(current, list2.get(0), 1, 20, new TMapData.FindAroundKeywordPOIListenerCallback() {

                    @Override
                    public void onFindAroundKeywordPOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems.size()==0){
                            myToast.setText(list2.get(0)+"이(가) 없습니다.");
                            myToast.show();
                            customProgressDialog.dismiss();
                            finishAffinity();
                            startActivity(intent2);


                        }
                        else {
                            for (int i = 0; i < poiItems.size(); i++) {
                                TMapPOIItem item = poiItems.get(i);
                                arrTMapPoint1.add(item.getPOIPoint());
                                arrTitle1.add(item.getPOIName());
                                arrAddress1.add(item.upperAddrName + " " +
                                        item.middleAddrName + " " + item.lowerAddrName);
                            }
                            setMultiMarkers(arrTMapPoint1, arrTitle1, arrAddress1, list2.get(0));
                        }
                    }
                });

                mapData.findAroundNamePOI(current, list.get(0).toString(), 1, 100, new FindAroundNamePOIListenerCallback() {
                    @Override
                    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems==null){
                            myToast.setText(list.get(0)+"이(가) 없습니다.");
                            myToast.show();
                            customProgressDialog.dismiss();
                            finishAffinity();
                            startActivity(intent2);


                        }
                        else {
                            for (int i = 0; i < poiItems.size(); i++) {
                                TMapPOIItem item = poiItems.get(i);
                                //arrTMapPoint.add(item.getPOIPoint());
                                arrTMapPoint2.add(item.getPOIPoint());
                                arrTitle2.add(item.getPOIName());
                                arrAddress2.add(item.upperAddrName + " " +
                                        item.middleAddrName + " " + item.lowerAddrName);
                            }

                            setMultiMarkers(arrTMapPoint2, arrTitle2, arrAddress2, list.get(0));
                        }

                    }
                });

                mapData.findAroundNamePOI(current, list.get(1).toString(), 1, 100, new FindAroundNamePOIListenerCallback() {
                    @Override
                    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems==null){
                            myToast.setText(list.get(1)+"이(가) 없습니다.");
                            myToast.show();
                            customProgressDialog.dismiss();
                            finishAffinity();
                            startActivity(intent2);


                        }
                        else {
                            for (int i = 0; i < poiItems.size(); i++) {
                                TMapPOIItem item = poiItems.get(i);
                                //arrTMapPoint.add(item.getPOIPoint());
                                arrTMapPoint3.add(item.getPOIPoint());
                                arrTitle3.add(item.getPOIName());
                                arrAddress3.add(item.upperAddrName + " " +
                                        item.middleAddrName + " " + item.lowerAddrName);
                            }

                            setMultiMarkers(arrTMapPoint3, arrTitle3, arrAddress3, list.get(1));
                        }

                    }
                });

                mapData.findAroundNamePOI(current, list.get(2).toString(), 1, 100, new FindAroundNamePOIListenerCallback() {
                    @Override
                    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItems) {
                        if (poiItems==null){
                            myToast.setText(list.get(2)+"이(가) 없습니다.");
                            myToast.show();
                            customProgressDialog.dismiss();
                            finishAffinity();
                            startActivity(intent2);


                        }
                        else {
                            for (int i = 0; i < poiItems.size(); i++) {
                                TMapPOIItem item = poiItems.get(i);
                                //arrTMapPoint.add(item.getPOIPoint());
                                arrTMapPoint4.add(item.getPOIPoint());
                                arrTitle4.add(item.getPOIName());
                                arrAddress4.add(item.upperAddrName + " " +
                                        item.middleAddrName + " " + item.lowerAddrName);
                            }

                            setMultiMarkers(arrTMapPoint4, arrTitle4, arrAddress4, list.get(2));
                            findPath(arrTMapPoint1,arrTMapPoint2,arrTMapPoint3,arrTMapPoint4);
                            customProgressDialog.dismiss();
                        }

                    }
                });

            }

            else {
                Toast.makeText(MapActivity.this,"뜨면 안됨",Toast.LENGTH_SHORT).show();
            }

        }
        catch (NullPointerException e){
            //Toast.makeText(MapActivity.this,"뜨면 안됨",Toast.LENGTH_SHORT).show();
            finishAffinity();
            startActivity(intent2);
        }

        arrTMapPoint1.clear();
        arrTMapPoint2.clear();
        arrTMapPoint3.clear();
        arrTMapPoint4.clear();
        arrTitle1.clear();
        arrTitle2.clear();
        arrTitle3.clear();
        arrTitle4.clear();
        arrAddress1.clear();
        arrAddress2.clear();
        arrAddress3.clear();
        arrAddress4.clear();
    }

    private void setMultiMarkers(ArrayList<TMapPoint> arrTPoint, ArrayList<String> arrTitle,
                                 ArrayList<String> arrAddress,String color)
    {


        for( int i = 0; i < arrTPoint.size(); i++ )
        {
            if(color.contains("애완용품점")) {
                Bitmap bitmapIcon = createMarkerIcon(R.drawable.pet_store);
                TMapMarkerItem tMapMarkerItem = new TMapMarkerItem();
                tMapMarkerItem.setIcon(bitmapIcon);

                tMapMarkerItem.setTMapPoint(arrTPoint.get(i));

                tMapView.addMarkerItem("pet_store" + i, tMapMarkerItem);

                setBalloonView(tMapMarkerItem, arrTitle.get(i), arrAddress.get(i));
            }
            else if (color.contains("은행")){
                Bitmap bitmapIcon = createMarkerIcon(R.drawable.bank);
                TMapMarkerItem tMapMarkerItem = new TMapMarkerItem();
                tMapMarkerItem.setIcon(bitmapIcon);

                tMapMarkerItem.setTMapPoint(arrTPoint.get(i));

                tMapView.addMarkerItem("bank" + i, tMapMarkerItem);

                setBalloonView(tMapMarkerItem, arrTitle.get(i), arrAddress.get(i));
            }
            else if (color.contains("편의점")){
                Bitmap bitmapIcon = createMarkerIcon(R.drawable.convenience);
                TMapMarkerItem tMapMarkerItem = new TMapMarkerItem();
                tMapMarkerItem.setIcon(bitmapIcon);

                tMapMarkerItem.setTMapPoint(arrTPoint.get(i));

                tMapView.addMarkerItem("convenience" + i, tMapMarkerItem);

                setBalloonView(tMapMarkerItem, arrTitle.get(i), arrAddress.get(i));
            }
            else if (color.contains("공원")){
                Bitmap bitmapIcon = createMarkerIcon(R.drawable.park);
                TMapMarkerItem tMapMarkerItem = new TMapMarkerItem();
                tMapMarkerItem.setIcon(bitmapIcon);

                tMapMarkerItem.setTMapPoint(arrTPoint.get(i));

                tMapView.addMarkerItem("park" + i, tMapMarkerItem);

                setBalloonView(tMapMarkerItem, arrTitle.get(i), arrAddress.get(i));
            }

            else if (color.contains("애견카페")){
                Bitmap bitmapIcon = createMarkerIcon(R.drawable.pet_cafe);
                TMapMarkerItem tMapMarkerItem = new TMapMarkerItem();
                tMapMarkerItem.setIcon(bitmapIcon);

                tMapMarkerItem.setTMapPoint(arrTPoint.get(i));

                tMapView.addMarkerItem("pet_cafe" + i, tMapMarkerItem);

                setBalloonView(tMapMarkerItem, arrTitle.get(i), arrAddress.get(i));
            }

            else if (color.contains("동물병원")){
                Bitmap bitmapIcon = createMarkerIcon(R.drawable.pet_hospital);
                TMapMarkerItem tMapMarkerItem = new TMapMarkerItem();
                tMapMarkerItem.setIcon(bitmapIcon);

                tMapMarkerItem.setTMapPoint(arrTPoint.get(i));

                tMapView.addMarkerItem("pet_hospital" + i, tMapMarkerItem);

                setBalloonView(tMapMarkerItem, arrTitle.get(i), arrAddress.get(i));
            }

            else if (color.contains("애견호텔")){
                Bitmap bitmapIcon = createMarkerIcon(R.drawable.pet_hotel);
                TMapMarkerItem tMapMarkerItem = new TMapMarkerItem();
                tMapMarkerItem.setIcon(bitmapIcon);

                tMapMarkerItem.setTMapPoint(arrTPoint.get(i));

                tMapView.addMarkerItem("pet_hotel" + i, tMapMarkerItem);

                setBalloonView(tMapMarkerItem, arrTitle.get(i), arrAddress.get(i));
            }

            else if (color.contains("약국")){
                Bitmap bitmapIcon = createMarkerIcon(R.drawable.pharmacy);
                TMapMarkerItem tMapMarkerItem = new TMapMarkerItem();
                tMapMarkerItem.setIcon(bitmapIcon);

                tMapMarkerItem.setTMapPoint(arrTPoint.get(i));

                tMapView.addMarkerItem("pharmacy" + i, tMapMarkerItem);

                setBalloonView(tMapMarkerItem, arrTitle.get(i), arrAddress.get(i));
            }

            else if (color.contains("역")){
                Bitmap bitmapIcon = createMarkerIcon(R.drawable.subway);
                TMapMarkerItem tMapMarkerItem = new TMapMarkerItem();
                tMapMarkerItem.setIcon(bitmapIcon);

                tMapMarkerItem.setTMapPoint(arrTPoint.get(i));

                tMapView.addMarkerItem("subway" + i, tMapMarkerItem);

                setBalloonView(tMapMarkerItem, arrTitle.get(i), arrAddress.get(i));
            }

            else {
                Bitmap bitmapIcon = createMarkerIcon(R.drawable.p1);
                TMapMarkerItem tMapMarkerItem = new TMapMarkerItem();
                tMapMarkerItem.setIcon(bitmapIcon);

                tMapMarkerItem.setTMapPoint(arrTPoint.get(i));

                tMapView.addMarkerItem("default" + i, tMapMarkerItem);

                setBalloonView(tMapMarkerItem, arrTitle.get(i), arrAddress.get(i));


            }

            /*TMapMarkerItem tMapMarkerItem = new TMapMarkerItem();
            tMapMarkerItem.setIcon(bitmapIcon);

            tMapMarkerItem.setTMapPoint(arrTPoint.get(i));

            tMapView.addMarkerItem("markerItem" + i, tMapMarkerItem);

            setBalloonView(tMapMarkerItem, arrTitle.get(i), arrAddress.get(i));*/
        }
    }

    private void setBalloonView(TMapMarkerItem marker, String title, String address)
    {
        marker.setCanShowCallout(true);

        if( marker.getCanShowCallout() )
        {
            marker.setCalloutTitle(title);
            marker.setCalloutSubTitle(address);

            Bitmap bitmap = createMarkerIcon(R.drawable.p8);
            marker.setCalloutRightButtonImage(bitmap);
        }
    }

    private Bitmap createMarkerIcon(int image)
    {
        Log.e("MapViewActivity", "(F)   createMarkerIcon()");

        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                image);
        bitmap = Bitmap.createScaledBitmap(bitmap, 50, 50,false);

        return bitmap;
    }

    /*public TMapPoint getPoint(ArrayList<TMapPoint> arrTPoint){
        TMapPoint point = new TMapPoint(0,0);

        point.setLatitude(arrTPoint.get(1).getLatitude());
        point.setLongitude(arrTPoint.get(1).getLongitude());

        return point;

    }*/

    public void findPath(ArrayList<TMapPoint> arrTPoint1, ArrayList<TMapPoint> arrTPoint2,ArrayList<TMapPoint> arrTPoint3, ArrayList<TMapPoint> arrTPoint4){
        TMapData mapData = new TMapData();
        TMapPoint tMapPoint = new TMapPoint(currentLatitude, currentLongitude);
        ArrayList<TMapPoint> passList = new ArrayList<>();
        final TextView text = (TextView) findViewById(R.id.text_dog_weight_result_text2);

        if(arrTPoint1.isEmpty()){
            tMapView.removeAllMarkerItem();

        }
        else if(arrTPoint2.isEmpty()){
            tMapView.removeAllMarkerItem();

        }
        else if(arrTPoint3.isEmpty()){
            tMapView.removeAllMarkerItem();

        }
        else if(arrTPoint4.isEmpty()){
            tMapView.removeAllMarkerItem();
        }

        else {
            Random randomnum1 = new Random();

            int num1, num2, num3, num4;
            Log.v("tag", "랜덤숫자들");

            num1 = randomnum1.nextInt(arrTPoint1.size());
            num2 = randomnum1.nextInt(arrTPoint2.size());
            num3 = randomnum1.nextInt(arrTPoint3.size());
            num4 = randomnum1.nextInt(arrTPoint4.size());

            passList.add(arrTPoint1.get(num1));
            passList.add(arrTPoint2.get(num2));
            passList.add(arrTPoint3.get(num3));
            passList.add(arrTPoint4.get(num4));

            mapData.findPathDataWithType(TMapData.TMapPathType.PEDESTRIAN_PATH, tMapPoint, tMapPoint, passList, 0, new TMapData.FindPathDataListenerCallback() {
                @Override
                public void onFindPathData(TMapPolyLine tMapPolyLine) {
                    tMapPolyLine.setLineColor(Color.RED);
                    tMapPolyLine.setLineWidth(10);
                    double distance = tMapPolyLine.getDistance();
                    form.format(distance);
                    //String s = Double.toString(distance);
                    text.setTextColor(Color.parseColor("#000000"));
                    text.setText(form.format(distance) + "m");
                    tMapView.addTMapPath(tMapPolyLine);
                }
            });
        }

    }

    public void findPath(ArrayList<TMapPoint> arrTPoint1, ArrayList<TMapPoint> arrTPoint2,ArrayList<TMapPoint> arrTPoint3){
        TMapData mapData = new TMapData();
        TMapPoint tMapPoint = new TMapPoint(currentLatitude, currentLongitude);
        ArrayList<TMapPoint> passList = new ArrayList<>();
        final TextView text = (TextView) findViewById(R.id.text_dog_weight_result_text2);

        if(arrTPoint1.isEmpty()){
            tMapView.removeAllMarkerItem();
        }
        else if(arrTPoint2.isEmpty()){
            tMapView.removeAllMarkerItem();
        }
        else if(arrTPoint3.isEmpty()){
            tMapView.removeAllMarkerItem();
        }

        else {

            Random randomnum1 = new Random();
            //Random randomnum2 = new Random();
            //Random randomnum3 = new Random();


            int num1, num2, num3;

            num1 = randomnum1.nextInt(arrTPoint1.size());
            num2 = randomnum1.nextInt(arrTPoint2.size());
            num3 = randomnum1.nextInt(arrTPoint3.size());

            passList.add(arrTPoint1.get(num1));
            passList.add(arrTPoint2.get(num2));
            passList.add(arrTPoint3.get(num3));

            mapData.findPathDataWithType(TMapData.TMapPathType.PEDESTRIAN_PATH, tMapPoint, tMapPoint, passList, 0, new TMapData.FindPathDataListenerCallback() {
                @Override
                public void onFindPathData(TMapPolyLine tMapPolyLine) {
                    tMapPolyLine.setLineColor(Color.RED);
                    tMapPolyLine.setLineWidth(10);
                    double distance = tMapPolyLine.getDistance();
                    //double distance2 = form.format(distance);
                    //String s = Double.toString(distance);
                    text.setTextColor(Color.parseColor("#000000"));
                    text.setText(form.format(distance) + "m");
                    tMapView.addTMapPath(tMapPolyLine);
                }
            });
        }

    }

    public void findPath(ArrayList<TMapPoint> arrTPoint1, ArrayList<TMapPoint> arrTPoint2){
        TMapData mapData = new TMapData();
        TMapPoint tMapPoint = new TMapPoint(currentLatitude, currentLongitude);
        ArrayList<TMapPoint> passList = new ArrayList<>();
        final TextView text = (TextView) findViewById(R.id.text_dog_weight_result_text2);
        text.setTextColor(Color.parseColor("#000000"));

        if(arrTPoint1.isEmpty()){
            tMapView.removeAllMarkerItem();

        }
        else if(arrTPoint2.isEmpty()){
            tMapView.removeAllMarkerItem();

        }


        else {

            Random randomnum1 = new Random();
            //Random randomnum2 = new Random();

            int num1, num2;

            num1 = randomnum1.nextInt(arrTPoint1.size());
            num2 = randomnum1.nextInt(arrTPoint2.size());

            passList.add(arrTPoint1.get(num1));
            passList.add(arrTPoint2.get(num2));

            mapData.findPathDataWithType(TMapData.TMapPathType.PEDESTRIAN_PATH, tMapPoint, tMapPoint, passList, 0, new TMapData.FindPathDataListenerCallback() {
                @Override
                public void onFindPathData(TMapPolyLine tMapPolyLine) {
                    tMapPolyLine.setLineColor(Color.RED);
                    tMapPolyLine.setLineWidth(10);
                    double distance = tMapPolyLine.getDistance();
                    form.format(distance);
                    //String s = Double.toString(distance);
                    text.setTextColor(Color.parseColor("#000000"));
                    text.setText(form.format(distance) + "m");
                    tMapView.addTMapPath(tMapPolyLine);
                }
            });
        }

    }

    public void findPath(ArrayList<TMapPoint> arrTPoint1){
        TMapData mapData = new TMapData();
        TMapPoint tMapPoint = new TMapPoint(currentLatitude, currentLongitude);
        ArrayList<TMapPoint> passList = new ArrayList<>();
        final TextView text = (TextView) findViewById(R.id.text_dog_weight_result_text2);

        if(arrTPoint1.isEmpty()){
            tMapView.removeAllMarkerItem();

        }

        else {

            Random randomnum1 = new Random();

            int num1;

            num1 = randomnum1.nextInt(arrTPoint1.size());

            passList.add(arrTPoint1.get(num1));

            mapData.findPathDataWithType(TMapData.TMapPathType.PEDESTRIAN_PATH, tMapPoint, tMapPoint, passList, 0, new TMapData.FindPathDataListenerCallback() {
                @Override
                public void onFindPathData(TMapPolyLine tMapPolyLine) {
                    tMapPolyLine.setLineColor(Color.RED);
                    tMapPolyLine.setLineWidth(10);
                    double distance = tMapPolyLine.getDistance();
                    form.format(distance);
                    //String s = Double.toString(distance);
                    text.setTextColor(Color.parseColor("#000000"));
                    text.setText(form.format(distance) + "m");
                    tMapView.addTMapPath(tMapPolyLine);
                }
            });
        }

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), MapchoiceActivity.class);
        startActivity(intent);
        super.onBackPressed();

    }
}
