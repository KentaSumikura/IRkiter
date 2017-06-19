package com.example.irkiter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.getirkit.irkit.IRKit;
import com.getirkit.irkit.IRSignal;
import com.getirkit.irkit.activity.IRKitSetupActivity;
import com.getirkit.irkit.activity.WaitSignalActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IRKIT_SETUP   = 1;
    private static final int REQUEST_SIGNAL_DETAIL = 2;
    private static final int REQUEST_WAIT_SIGNAL   = 3;
    private static final int REQUEST_DEVICE_DETAIL = 4;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //つるだこうしんあああ   2017/6/19
        //のだ
        //セットアップボタンつるだああ
        final Button button = (Button)findViewById(R.id.buttonSetup);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == button) {
                    setupbtn();
                }
            }
        });

        //信号登録ボタン
        final Button button2 = (Button)findViewById(R.id.buttonSign);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == button2) {
                    signalbtn();
                }
            }
        });

                // ContextをセットしてSDKを初期化する。すでに初期化済みの場合は
                // Contextのセットのみ行われる。
                IRKit.sharedInstance().init(getApplicationContext());
    }



    @Override
    protected void onResume() {
        super.onResume();

        IRKit irkit = IRKit.sharedInstance();

        // ローカルネットワーク内のIRKit検索を開始
        irkit.startServiceDiscovery();

        // Wi-Fi接続状態の変化を監視して、Wi-Fiが有効になった際に
        // IRKit検索を開始し、Wi-Fiが無効になった際に検索を停止する
        irkit.registerWifiStateChangeListener();

        // clientkeyを取得していない場合は取得する
        irkit.registerClient();
    }

    @Override
    protected void onPause() {
        super.onPause();

        IRKit irkit = IRKit.sharedInstance();

        // ローカルネットワーク内のIRKit検索を停止
        irkit.stopServiceDiscovery();

        // Wi-Fi状態の変化の監視をやめる
        irkit.unregisterWifiStateChangeListener();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_IRKIT_SETUP && resultCode == RESULT_OK) {
            // セットアップが完了した

        }

        if (requestCode == REQUEST_WAIT_SIGNAL && resultCode == RESULT_OK) {
            Bundle args = data.getExtras();
            IRSignal signal = args.getParcelable("signal"); // 受信した信号
            IRKit irkit = IRKit.sharedInstance();
            signal.setId(irkit.signals.getNewId()); // ランダムなidを割り当てる

            if (signal.hasBitmapImage()) { // アイコンに写真が指定された
                // signal.renameToSuggestedImageFilename() は setId() より後に呼ぶ
                if (!signal.renameToSuggestedImageFilename(this)) {
                    // ファイル名変更失敗
                }
            } else { // アイコンリストから選択された
                // signalのimageResourceIdを元にimageResourceNameを更新する
                signal.onUpdateImageResourceId(getResources());
            }

            // ボタンのリストに追加する
            irkit.signals.add(signal);
            irkit.signals.save();
        }
    }


    public  void setupbtn(){

        Intent intent = new Intent(this, IRKitSetupActivity.class);
        startActivityForResult(intent, REQUEST_IRKIT_SETUP);

    }

    public  void signalbtn(){

        Intent intent = new Intent(this, WaitSignalActivity.class);
        startActivityForResult(intent, REQUEST_WAIT_SIGNAL);

    }

}
