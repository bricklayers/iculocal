package com.iculocal;

import android.graphics.Color;
import android.icu.text.CompactDecimalFormat;
import android.icu.text.DateFormat;
import android.icu.text.DateIntervalFormat;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.DateInterval;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.FieldPosition;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String[] COLORS = {"#FFFFFFFF", "#0B030303",};
    private LinearLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init() {
        parentLayout = findViewById(R.id.parent_layout);
        // 中文输出：3月16日
        // 英文输出：Mar 16
        setText(DateFormat.getInstanceForSkeleton(DateFormat.ABBR_MONTH_DAY, Locale.getDefault()).format(new Date()));
        setText(DateFormat.getInstanceForSkeleton(DateFormat.ABBR_MONTH_DAY, Locale.ENGLISH).format(new Date()));

        // 3月16日星期四
        // Thursday,March 16
        setText(DateFormat.getInstanceForSkeleton(DateFormat.MONTH_WEEKDAY_DAY, Locale.getDefault()).format(new Date()));
        setText(DateFormat.getInstanceForSkeleton(DateFormat.MONTH_WEEKDAY_DAY, Locale.ENGLISH).format(new Date()));

        // 3月16日星期四
        // Thursday,Mar 16
        setText(DateFormat.getInstanceForSkeleton("MMMEEEEd", Locale.getDefault()).format(new Date()));
        setText(DateFormat.getInstanceForSkeleton("MMMEEEEd", Locale.ENGLISH).format(new Date()));

        // 2023年3月16
        // March 16,2023
        setText(DateFormat.getInstanceForSkeleton(DateFormat.YEAR_MONTH_DAY, Locale.getDefault()).format(new Date()));
        setText(DateFormat.getInstanceForSkeleton(DateFormat.YEAR_MONTH_DAY, Locale.ENGLISH).format(new Date()));

        // 2023/03/16
        // 03/16/2023
        setText(DateFormat.getInstanceForSkeleton("yyyyMMdd", Locale.getDefault()).format(new Date()));
        setText(DateFormat.getInstanceForSkeleton("yyyyMMdd", Locale.ENGLISH).format(new Date()));

        DateInterval dateInterval = new DateInterval(Date.parse("2023/03/16"), Date.parse("2023/07/16"));
        setText(DateIntervalFormat.getInstance(DateFormat.YEAR_ABBR_MONTH_DAY, Locale.getDefault()).format(dateInterval, new StringBuffer(""), new FieldPosition(0)).toString());
        setText(DateIntervalFormat.getInstance(DateFormat.YEAR_ABBR_MONTH_DAY, Locale.ENGLISH).format(dateInterval, new StringBuffer(""), new FieldPosition(0)).toString());

        // 100,000.89
        // 100,000.89
        setText(NumberFormat.getInstance(Locale.getDefault()).format(10000.89));
        setText(NumberFormat.getInstance(Locale.ENGLISH).format(10000.89));

        // 53%
        // 53%
        setText(DecimalFormat.getPercentInstance(Locale.getDefault()).format(0.53));
        setText(DecimalFormat.getPercentInstance(Locale.ENGLISH).format(0.53));

        // 10万
        // 100K
        setText(CompactDecimalFormat.getInstance(Locale.getDefault(), CompactDecimalFormat.CompactStyle.SHORT).format(100000));
        setText(CompactDecimalFormat.getInstance(Locale.ENGLISH, CompactDecimalFormat.CompactStyle.SHORT).format(100000));

        // 15.35KB
        setText(Formatter.formatFileSize(this, 15345));
        // 16MB
        setText(Formatter.formatShortFileSize(this, 15612524));

    }

    private void setText(String formatText) {
        TextView textView = new TextView(getApplicationContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_VERTICAL;
        textView.setText(formatText);
        textView.setPadding(24, 46, 24, 46);
        textView.setBackgroundColor(Color.parseColor(COLORS[parentLayout.getChildCount() % 2]));
        textView.setTextColor(Color.parseColor("#00796B"));
        textView.setTextSize(14);
        textView.setLayoutParams(params);
        parentLayout.addView(textView);
    }
}