
package com.iplus.edu.jlpt_voc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LaunchSplashScreen extends Activity {
    private final int SPLASH_DISPLAY_LENGHT = 2000;

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
}
