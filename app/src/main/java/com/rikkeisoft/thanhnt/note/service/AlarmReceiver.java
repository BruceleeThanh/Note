package com.rikkeisoft.thanhnt.note.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.rikkeisoft.thanhnt.note.R;

/**
 * Created by ThanhNT on 16-Aug-17.
 */

public class AlarmReceiver extends BroadcastReceiver {

    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        String receiverAction = intent.getAction();
        String appAction = context.getResources().getString(R.string.intent_action_broadcast_receiver);
        if(receiverAction.equals(appAction)){
            int id = intent.getExtras().getInt("id");
            String message = intent.getExtras().getString("message");
            showNotification(id, message);
        }
    }

    // chưa cancel pending intent của alarm

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void showNotification(int id, String message) {
        Intent intent = new Intent();
        int idIcon = R.mipmap.ic_launcher;
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(id, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(idIcon)
                .setAutoCancel(true)
                .setContentTitle(context.getResources().getString(R.string.app_name))
                .setContentText(message)
                .setContentIntent(pendingIntent);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(sound);
        builder.setVibrate(new long[]{100, 100});
        builder.setLights(Color.CYAN, 1000, 10000);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(id, builder.build());
    }
}
