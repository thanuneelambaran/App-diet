package com.thanu.dietreminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/** Fires at each meal time: shows today's dish, then schedules tomorrow. */
public class ReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context ctx, Intent intent) {
        int slot = intent.getIntExtra("slot", 0);
        show(ctx, slot);
        Scheduler.scheduleSlot(ctx, slot); // repeat tomorrow
    }

    public static void show(Context ctx, int slot) {
        Scheduler.createChannel(ctx);
        int day = Scheduler.todayIndex(ctx);
        String title = MealPlan.TITLES[slot] + " · Day " + (day + 1);
        String text = MealPlan.textFor(slot, day);

        PendingIntent open = PendingIntent.getActivity(ctx, 0,
                new Intent(ctx, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Notification.Builder b = new Notification.Builder(ctx, Scheduler.CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_popup_reminder)
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(new Notification.BigTextStyle().bigText(text))
                .setContentIntent(open)
                .setAutoCancel(true);

        NotificationManager nm = ctx.getSystemService(NotificationManager.class);
        nm.notify(200 + slot, b.build());
    }
}
