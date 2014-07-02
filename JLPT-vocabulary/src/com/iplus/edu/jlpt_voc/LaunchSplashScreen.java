
package com.iplus.edu.jlpt_voc;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

import com.iplus.edu.jlpt_voc.alarm.AlarmReceiver;

public class LaunchSplashScreen extends Activity {
    private final int SPLASH_DISPLAY_LENGHT = 2000;
    private PendingIntent pendingIntent;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lanuch_splash_screen);

        /*
         * New Handler to start the Menu-Activity and close this Splash-Screen
         * after some seconds.
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // showSplashInterstitial();
                setAlarm();
                startMenuActivity();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }

    /**
     * Starts the application's {@link MainActivity}.
     */
    private void startMenuActivity() {
        /* Create an Intent that will start the FeatureList-Activity. */
        Intent intent = new Intent(LaunchSplashScreen.this, Menu.class);
        this.startActivity(intent);
        this.finish();
    }

    private void setAlarm() {
        Intent myIntent = new Intent(LaunchSplashScreen.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(LaunchSplashScreen.this, 0, myIntent, 0);

        Calendar calendar = Calendar.getInstance(); // Calendar取得
        calendar.setTimeInMillis(System.currentTimeMillis()); // 現在時刻を取得
        calendar.add(Calendar.SECOND, 30); // 現時刻より30秒後を設定

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        // alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
        // pendingIntent);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(), 100 * 1000, pendingIntent);
    }
}
