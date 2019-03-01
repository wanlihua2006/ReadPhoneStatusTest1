package com.qiku.readphonestatustest;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button get ;
    private TextView deviceid;
    private String mDeviceId;
    Context mContext = MainActivity.this;
    final int REQUEST_READ_PHONE_STATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mContext = MainActivity.this;
        final TelephonyManager tm = (TelephonyManager)/*mContext.*/getSystemService(
                Context.TELEPHONY_SERVICE);
        deviceid = findViewById(R.id.deviceid);
        get = findViewById(R.id.getdeviceid);
        get.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (tm == null) {
                    mDeviceId = " ";
                    Log.d("MainActivity","wanlihua debug tm is null");
                }else {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        int permissionCheck = ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE);
                        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
                            Log.d("MainActivity","wanlihua debug request permission : " + REQUEST_READ_PHONE_STATE);
                        } else {
                            //TODO
                            mDeviceId = tm.getDeviceId();
                            Log.d("MainActivity","wanlihua debug mDeviceId: " + mDeviceId);
                            deviceid.setText(mDeviceId);
                        }
;                   }else{
                        mDeviceId = tm.getDeviceId();
                        Log.d("MainActivity","wanlihua debug M before mDeviceId: " + mDeviceId);
                        deviceid.setText(mDeviceId);
                    }
                }
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_PHONE_STATE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //TODO
                    Log.d("MainActivity","wanlihua debug PERMISSION_GRANTED");
                }
                break;

            default:
                break;
        }
    }
}
