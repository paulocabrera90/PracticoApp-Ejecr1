package com.example.practicoapp_ejecr1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

public class MensajeRecibido extends BroadcastReceiver {
    private static Boolean flagLlamada = true;

    @Override
    public void onReceive(Context context, Intent intent) {
     //   Toast.makeText(context, "Usted ha recibido un mensaje", Toast.LENGTH_LONG).show();
        String phoneNo = "911";

        if (intent.getExtras().getBoolean("connected") && flagLlamada) {
            String dial = "tel:" + phoneNo;
            flagLlamada = false;
            Intent i = new Intent(android.content.Intent.ACTION_CALL,
                    Uri.parse(dial));
            context.startActivity(i);
        }else{
            flagLlamada = true;
        }
    }
}
