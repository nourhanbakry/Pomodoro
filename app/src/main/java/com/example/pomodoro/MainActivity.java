package com.example.pomodoro;

import static com.example.pomodoro.MyCounter.countDownInterval;
import static com.example.pomodoro.MyCounter.millisInFuture;
import static com.example.pomodoro.MyCounter.timeLeft;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pomodoro.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MyCounter myCounter;
    private static boolean isTimeReset = false;
    public static final String activity = "TAAGG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(activity,"OnCreate");

        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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
                Toast.makeText(MainActivity.this,"Time Already Reseated",Toast.LENGTH_SHORT).show();
            } else {
                Log.d(activity,"time left in reset text whihle checked not equal zero       "+timeLeft);
                resetTimer();
            }

        }
    });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d(activity,"OnSaved Instance State");

        if (isTimeReset){
          outState.putLong("TimeLeft",0);
       } else{
         outState.putLong("TimeLeft",timeLeft);
    } }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(activity,"OnRestore");
        savedInstanceState.getLong("TimeLeft");
        Log.d(activity,"time left in on restore insatnce   "+timeLeft);
        Log.d(activity,"Is Reset Timer   "+isTimeReset);

        if(timeLeft > 0 && !isTimeReset)
            startTimer(timeLeft);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(activity,"OnDestroy");
        if(myCounter !=null){
            myCounter.cancel();
            myCounter=null;
        }
    }

    private void startTimer(long startTime) {
        isTimeReset= false;
        myCounter =new MyCounter(startTime,countDownInterval,binding.timeTextView,binding.titleTextView,binding.startBtn,binding.prograssBar,MainActivity.this);
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

