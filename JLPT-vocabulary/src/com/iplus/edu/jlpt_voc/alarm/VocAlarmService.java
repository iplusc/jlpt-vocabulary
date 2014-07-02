
package com.iplus.edu.jlpt_voc.alarm;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.iplus.edu.jlpt_voc.Main;
import com.iplus.edu.jlpt_voc.R;

public class VocAlarmService extends Service {
    private NotificationManager mManager;

    @Override
    public IBinder onBind(Intent arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate()
    {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);

        this.getApplicationContext();
        mManager = (NotificationManager) this.getApplicationContext().getSystemService(
                Context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(this.getApplicationContext(), Main.class);

        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(
                this.getApplicationContext(), 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification noti = new Notification.Builder(this.getApplicationContext())
                .setContentTitle("New Voc")
                .setContentText("This is a test message!")
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pendingNotificationIntent)
                .build();

        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        noti.flags |= Notification.FLAG_AUTO_CANCEL;
        mManager.notify(0, noti);
    }

    @Override
    public void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}
