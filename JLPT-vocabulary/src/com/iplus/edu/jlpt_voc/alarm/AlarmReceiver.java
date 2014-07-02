
package com.iplus.edu.jlpt_voc.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, VocAlarmService.class);
        context.startService(service);
    }

}
