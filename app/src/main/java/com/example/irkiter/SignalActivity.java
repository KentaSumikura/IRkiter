package com.example.irkiter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.getirkit.irkit.IRKit;
import com.getirkit.irkit.IRSignals;

public class SignalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signal);

        // 保存済みの赤外線信号一覧を取得
        IRSignals signals = IRKit.sharedInstance().signals;
    }
}
