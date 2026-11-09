package com.thanu.dietreminder;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import java.util.Calendar;

/** Schedules the daily reminders and tracks which plan day it is. */
public final class Scheduler {
    private Scheduler() {}

    public static final String CHANNEL_ID = "meal_reminders";
    private static final String PREFS = "diet_prefs";
    private static final String KEY_START = "start_millis";

    public static void createChannel(Context ctx) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel ch = new NotificationChannel(
                    CHANNEL_ID, "Meal reminders", NotificationManager.IMPORTANCE_HIGH);
            ch.setDescription("Daily diet plan reminders");
            ctx.getSystemService(NotificationManager.class).createNotificationChannel(ch);
        }
    }

    private static long midnight(long millis) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    /** Day 1 = first day the app was opened (or after reset). */
    public static long getStart(Context ctx) {
        SharedPreferences p = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        long start = p.getLong(KEY_START, 0);
        if (start == 0) {
            start = midnight(System.currentTimeMillis());
            p.edit().putLong(KEY_START, start).apply();
        }
        return start;
    }

    public static void resetStart(Context ctx) {
        ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit()
                .putLong(KEY_START, midnight(System.currentTimeMillis())).apply();
    }

    /** 0-based plan day for today; loops after day 30. */
    public static int todayIndex(Context ctx) {
        long days = (midnight(System.currentTimeMillis()) - getStart(ctx)) / (24L * 60 * 60 * 1000);
        if (days < 0) days = 0;
        return (int) (days % 30);
    }

    private static PendingIntent pending(Context ctx, int slot) {
        Intent i = new Intent(ctx, ReminderReceiver.class);
        i.putExtra("slot", slot);
        return PendingIntent.getBroadcast(ctx, 100 + slot, i,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
    }

    /** Schedule one slot at its next occurrence. */
    public static void scheduleSlot(Context ctx, int slot) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, MealPlan.TIMES[slot][0]);
        c.set(Calendar.MINUTE, MealPlan.TIMES[slot][1]);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        if (c.getTimeInMillis() <= System.currentTimeMillis() + 1000) {
            c.add(Calendar.DAY_OF_YEAR, 1);
        }
        AlarmManager am = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        // Inexact-while-idle: fires even in Doze; may be a few minutes late. Needs no special permission.
        am.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pending(ctx, slot));
    }

    public static void scheduleAll(Context ctx) {
        for (int s = 0; s < MealPlan.TIMES.length; s++) scheduleSlot(ctx, s);
    }
}
