package com.thanu.dietreminder;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/** One screen: today's meals, browse days, test & reset buttons. */
public class MainActivity extends Activity {
    private static final int GREEN = Color.parseColor("#2E7D32");
    private int shownDay;
    private LinearLayout list;
    private TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Scheduler.createChannel(this);
        if (Build.VERSION.SDK_INT >= 33 &&
                checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
        }
        Scheduler.scheduleAll(this);
        shownDay = Scheduler.todayIndex(this);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(16), dp(24), dp(16), dp(16));

        TextView title = new TextView(this);
        title.setText("Diet Reminder");
        title.setTextSize(24);
        title.setTypeface(null, Typeface.BOLD);
        title.setTextColor(GREEN);
        root.addView(title);

        TextView warn = new TextView(this);
        warn.setText("No smoking · No alcohol · 3 L water · 30–40 min walk\nQuit-line: 1800-11-2356");
        warn.setTextColor(Color.parseColor("#C62828"));
        warn.setPadding(0, dp(6), 0, dp(10));
        root.addView(warn);

        LinearLayout nav = new LinearLayout(this);
        nav.setGravity(Gravity.CENTER_VERTICAL);
        Button prev = new Button(this); prev.setText("◀");
        Button next = new Button(this); next.setText("▶");
        header = new TextView(this);
        header.setTextSize(18);
        header.setTypeface(null, Typeface.BOLD);
        header.setGravity(Gravity.CENTER);
        nav.addView(prev);
        nav.addView(header, new LinearLayout.LayoutParams(0, -2, 1f));
        nav.addView(next);
        root.addView(nav);
        prev.setOnClickListener(v -> { shownDay = (shownDay + 29) % 30; render(); });
        next.setOnClickListener(v -> { shownDay = (shownDay + 1) % 30; render(); });

        list = new LinearLayout(this);
        list.setOrientation(LinearLayout.VERTICAL);
        root.addView(list);

        Button test = new Button(this);
        test.setText("Send test notification");
        test.setOnClickListener(v -> ReminderReceiver.show(this, 1));
        root.addView(test);

        Button reset = new Button(this);
        reset.setText("Restart plan from Day 1 today");
        reset.setOnClickListener(v -> {
            Scheduler.resetStart(this);
            shownDay = 0;
            render();
            Toast.makeText(this, "Plan restarted: today is Day 1", Toast.LENGTH_SHORT).show();
        });
        root.addView(reset);

        ScrollView sv = new ScrollView(this);
        sv.addView(root);
        setContentView(sv);
        render();
    }

    private void render() {
        int today = Scheduler.todayIndex(this);
        header.setText("Day " + (shownDay + 1) + (shownDay == today ? " (Today)" : ""));
        list.removeAllViews();
        for (int s = 0; s < MealPlan.TIMES.length; s++) {
            int h = MealPlan.TIMES[s][0], m = MealPlan.TIMES[s][1];
            String time = String.format("%d:%02d %s", (h % 12 == 0) ? 12 : h % 12, m, h < 12 ? "AM" : "PM");

            TextView t = new TextView(this);
            t.setText(time + "  ·  " + MealPlan.TITLES[s]);
            t.setTypeface(null, Typeface.BOLD);
            t.setTextColor(GREEN);
            t.setPadding(0, dp(10), 0, 0);
            list.addView(t);

            TextView d = new TextView(this);
            d.setText(MealPlan.textFor(s, shownDay));
            d.setTextSize(15);
            d.setBackgroundColor(Color.parseColor("#E8F5E9"));
            d.setPadding(dp(10), dp(8), dp(10), dp(8));
            list.addView(d);
        }
    }

    private int dp(int v) {
        return Math.round(v * getResources().getDisplayMetrics().density);
    }
}
