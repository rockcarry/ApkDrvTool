package com.apical.apkdrvtool;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import java.io.*;

public class ApkDrvToolActivity extends Activity {
    private static final String TAG = "ApkDrvToolActivity";

    private Button   mBtnRefresh;
    private Button   mBtnWrite;
    private Button   mBtnGpsPwr0;
    private Button   mBtnGpsPwr1;
    private Button   mBtnAvinEn0;
    private Button   mBtnAvinEn1;
    private Button   mBtnAvinSw0;
    private Button   mBtnAvinSw1;
    private Button   mBtnTmcPwr0;
    private Button   mBtnTmcPwr1;
    private Button   mBtnOtgSw0;
    private Button   mBtnOtgSw1;
    private EditText mWriteData;
    private EditText mApkDrvData;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mBtnRefresh = (Button)findViewById(R.id.btn_refresh );
        mBtnWrite   = (Button)findViewById(R.id.btn_write   );
        mBtnGpsPwr0 = (Button)findViewById(R.id.btn_gpspwr_0);
        mBtnGpsPwr1 = (Button)findViewById(R.id.btn_gpspwr_1);
        mBtnAvinEn0 = (Button)findViewById(R.id.btn_avinen_0);
        mBtnAvinEn1 = (Button)findViewById(R.id.btn_avinen_1);
        mBtnAvinSw0 = (Button)findViewById(R.id.btn_avinsw_0);
        mBtnAvinSw1 = (Button)findViewById(R.id.btn_avinsw_1);
        mBtnTmcPwr0 = (Button)findViewById(R.id.btn_tmcpwr_0);
        mBtnTmcPwr1 = (Button)findViewById(R.id.btn_tmcpwr_1);
        mBtnOtgSw0  = (Button)findViewById(R.id.btn_otgsw_0 );
        mBtnOtgSw1  = (Button)findViewById(R.id.btn_otgsw_1 );

        mBtnRefresh.setOnClickListener(mOnClickListener);
        mBtnWrite  .setOnClickListener(mOnClickListener);
        mBtnGpsPwr0.setOnClickListener(mOnClickListener);
        mBtnGpsPwr1.setOnClickListener(mOnClickListener);
        mBtnAvinEn0.setOnClickListener(mOnClickListener);
        mBtnAvinEn1.setOnClickListener(mOnClickListener);
        mBtnAvinSw0.setOnClickListener(mOnClickListener);
        mBtnAvinSw1.setOnClickListener(mOnClickListener);
        mBtnTmcPwr0.setOnClickListener(mOnClickListener);
        mBtnTmcPwr1.setOnClickListener(mOnClickListener);
        mBtnOtgSw0 .setOnClickListener(mOnClickListener);
        mBtnOtgSw1 .setOnClickListener(mOnClickListener);

        mWriteData  = (EditText)findViewById(R.id.edt_write      );
        mApkDrvData = (EditText)findViewById(R.id.txt_apkdrv_data);
        mApkDrvData.setTypeface(Typeface.MONOSPACE, Typeface.NORMAL);
        mApkDrvData.setText(readFile("/dev/apical"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            case R.id.btn_refresh:
//              mApkDrvData.setText(readFile("/dev/apical"));
                break;
            case R.id.btn_write:
                Toast.makeText(ApkDrvToolActivity.this, "write /dev/apical " + mWriteData.getText().toString(), Toast.LENGTH_SHORT).show();
                writeFile("/dev/apical", mWriteData.getText().toString());
                break;
            case R.id.btn_gpspwr_0:
            case R.id.btn_gpspwr_1:
            case R.id.btn_avinen_0:
            case R.id.btn_avinen_1:
            case R.id.btn_avinsw_0:
            case R.id.btn_avinsw_1:
            case R.id.btn_tmcpwr_0:
            case R.id.btn_tmcpwr_1:
            case R.id.btn_otgsw_0 :
            case R.id.btn_otgsw_1 :
                Toast.makeText(ApkDrvToolActivity.this, "write /dev/apical " + ((Button)v).getText(), Toast.LENGTH_SHORT).show();
                writeFile("/dev/apical", ((Button)v).getText().toString());
                break;
            }

            // auto refresh apkdrv data
            mApkDrvData.setText(readFile("/dev/apical"));
        }
    };

    private static void writeFile(String file, String text) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file, false);
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) { e.printStackTrace(); }
        }
    }
    
    private static String readFile(String file) {
        FileReader reader = null;
        char[]     buf    = new char[512];
        try {
            reader = new FileReader(file);
            reader.read(buf);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) { e.printStackTrace(); }
        }
        return String.valueOf(buf);
    }
}
