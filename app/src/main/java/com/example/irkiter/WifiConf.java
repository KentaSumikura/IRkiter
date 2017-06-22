package com.example.irkiter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class WifiConf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_conf);

        getwifissid();

    }

    public void getwifissid()
    {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo w_info = wifiManager.getConnectionInfo();
        Toast.makeText(this, w_info.getSSID(), Toast.LENGTH_LONG).show();
    }

}
