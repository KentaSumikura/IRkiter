package com.example.irkiter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.getirkit.irkit.IRKit;
import com.getirkit.irkit.IRPeripherals;

public class IRkitListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irkit_list);

        // 保存済みのIRKitデバイス一覧を取得
        IRPeripherals peripherals = IRKit.sharedInstance().peripherals;

    }


}
