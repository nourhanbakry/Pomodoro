package com.example.pomodoro;

import static com.example.pomodoro.MyCounter.countDownInterval;
import static com.example.pomodoro.MyCounter.millisInFuture;
import static com.example.pomodoro.MyCounter.timeLeft;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pomodoro.databinding.FragmentPomodoroBinding;

public class Pomodoro extends Fragment {
    Pomodoro(){}
    FragmentPomodoroBinding binding;
    MyCounter myCounter;
    private static boolean isTimeReset = false;
    public static final String activity = "TAAGG";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     binding= FragmentPomodoroBinding.inflate(inflater,container,false);
     View view = binding.getRoot();
     binding.startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer(millisInFuture);
                Log.d(activity,"Timer started");

            }
        });

     binding.resetTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timeLeft ==0){
                    Log.d(activity,"time left in reset text while cheked     "+timeLeft);
                    Toast.makeText(getActivity(),"Time Already Reseated",Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(activity,"time left in reset text whihle checked not equal zero       "+timeLeft);
                    resetTimer();
                }

            }
        });

    if (savedInstanceState!=null){
        timeLeft=savedInstanceState.getLong("TimeLeft");
        if(timeLeft > 0 && !isTimeReset) startTimer(timeLeft);
    }
    return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(activity,"OnSaved Instance State");

        if (isTimeReset){
            outState.putLong("TimeLeft",0);
        } else{
            outState.putLong("TimeLeft",timeLeft);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(activity,"OnDestroy");
        if(myCounter !=null){
            myCounter.cancel();
            myCounter=null;
        }
    }
    private void startTimer(long startTime) {
        isTimeReset= false;
        myCounter =new MyCounter(startTime,countDownInterval,binding.timeTextView,binding.titleTextView,binding.startBtn,binding.prograssBar,getActivity());
        myCounter.start();
        myCounter.onTick(millisInFuture-countDownInterval);
        binding.titleTextView.setText(R.string.keep_going);
        binding.startBtn.setEnabled(false);
    }
    private void resetTimer(){
        isTimeReset = true;
        myCounter.cancel();
        myCounter=null;
        binding.timeTextView.setText(R.string.time25min);
        binding.titleTextView.setText(R.string.take_a_pomodoro);
        binding.startBtn.setEnabled(true);
        binding.prograssBar.setProgress(100);
    }
}