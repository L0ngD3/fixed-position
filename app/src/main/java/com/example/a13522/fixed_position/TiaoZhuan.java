package com.example.a13522.fixed_position;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class TiaoZhuan extends AppCompatActivity {

    public LocationClient mlocationClient;
    public TextView positionText;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mlocationClient = new LocationClient(getApplicationContext());
        mlocationClient.registerLocationListener(new MyLocationListener());
        setContentView(R.layout.content_tiao_zhuan);
        positionText = (TextView) findViewById(R.id.tv);

            requestLocation();

    }
    private void requestLocation() {
        initLocation();
        mlocationClient.start();
    }
    private void initLocation(){
        LocationClientOption option =new LocationClientOption();
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        mlocationClient.setLocOption(option);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mlocationClient.stop();
    }

    public class MyLocationListener implements BDLocationListener{
        @Override
        public void onReceiveLocation(final BDLocation Location) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    StringBuilder currentPosition = new StringBuilder();
                    currentPosition.append("纬度：").append(Location.getLatitude()).append("\n");
                    currentPosition.append("经度：").append(Location.getLongitude()).append("\n");
                    currentPosition.append("国：").append(Location.getCountry()).append("\n");
                    currentPosition.append("省：").append(Location.getProvince()).append("\n ");
                    currentPosition.append("市：").append(Location.getCountry()).append("\n");
                    currentPosition.append("区：").append(Location.getDistrict()).append("\n");
                    currentPosition.append("街道：").append(Location.getStreet()).append("\n ");

                    currentPosition.append("定位方式：\n");
                    if (Location.getLocType() == BDLocation.TypeGpsLocation) {
                        currentPosition.append("GPS");
                    } else if (Location.getLocType() == BDLocation.TypeNetWorkLocation) {
                        currentPosition.append("网络");
                    }
                    positionText.setText(currentPosition);
                }
            });

        }
        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }

}
