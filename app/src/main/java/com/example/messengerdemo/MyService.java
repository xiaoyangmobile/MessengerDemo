package com.example.messengerdemo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class MyService extends Service {
    public static final int MSG_SAY_HELLO = 1;

    public MyService() {
    }

    static class MyHandler extends Handler{
        private Context applicationContext;

        public MyHandler(Context context) {
            applicationContext = context.getApplicationContext();
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Toast.makeText(this.applicationContext, "hello", Toast.LENGTH_SHORT);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    Messenger mMessenger;

    @Override
    public IBinder onBind(Intent intent) {
        mMessenger = new Messenger(new MyHandler(this));
        return mMessenger.getBinder();
    }
}
