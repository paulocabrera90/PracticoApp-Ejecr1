package com.example.practicoapp_ejecr1;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MensajeRecibido mr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1000);
        }

      IntentFilter filter = new IntentFilter();
      // filter.addAction("android.hardware.usb.action.USB_STATE");

     //  IntentFilter filter = new IntentFilter();
       filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
       registerReceiver(mr, filter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mr = new MensajeRecibido();
       registerReceiver(mr, new IntentFilter("android.hardware.usb.action.USB_STATE"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mr);
    }

}