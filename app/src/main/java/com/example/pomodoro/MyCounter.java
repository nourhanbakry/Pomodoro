package com.example.pomodoro;

import android.content.Context;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

class MyCounter extends CountDownTimer {
    public static long millisInFuture =25*60*1000;
    public static long countDownInterval = 1000;
    private final TextView timeTextView;
    private final TextView titleTextView;
    private final Button startBTN;
    private final Context context;
    private final ProgressBar progressBar;
    public static long timeLeft;

    public MyCounter(long millisInFuture, long countDownInterval, TextView timeTextView, TextView titleTextView, Button startBTN, ProgressBar progressBar, Context context) {
        super(millisInFuture, countDownInterval);
        this.timeTextView=timeTextView;
        this.context = context;
        this.titleTextView = titleTextView;
        this.startBTN = startBTN;
        this.progressBar= progressBar;
    }



    @Override
    public void onFinish() {
        Toast.makeText(context, "Great Job", Toast.LENGTH_SHORT).show();
        startBTN.setEnabled(true);
        titleTextView.setText(R.string.take_a_pomodoro);
        timeTextView.setText(R.string.time25min);
        timeLeft = 0;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        timeLeft = millisUntilFinished;
        int minutes = (int) (millisUntilFinished / (1000*60)) %60 ;
        int seconds = (int) (millisUntilFinished / 1000) % 60;
        String  timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timeTextView.setText(timeLeftFormatted);
        int progress = (int) ((double) millisUntilFinished / millisInFuture * 100);
        progressBar.setProgress(progress);

    }

}