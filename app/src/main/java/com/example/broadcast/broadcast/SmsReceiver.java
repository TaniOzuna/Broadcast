package com.example.broadcast.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.security.Permission;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("RECEIVER-ANDROID", "recieved");
        Log.d("RECEIVER-ANDROID", intent.getAction().toString());

        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Log.d("RECEIVER-ANDROID", "Just received sms");

            final Bundle bundle = intent.getExtras();
            Log.d("RECEIVER-ANDROID", bundle.get("pdus").toString());
            final Object[] pdusObj = (Object[]) bundle.get("pdus");

            for (int i = 0; i < pdusObj.length; i++) {

                SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                String message = currentMessage.getDisplayMessageBody();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
                context.startActivity(browserIntent);




                Log.d("RECEIVER-ANDROID", "Phone Number: " + phoneNumber + "Message: " + message);
            }


        }
    }
}
