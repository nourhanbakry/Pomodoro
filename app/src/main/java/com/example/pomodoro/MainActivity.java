package com.example.pomodoro;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pomodoro.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.bottomNavView.setBackground(null);
        binding.bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id==R.id.pomodoro_item){
                    replaceFragment( new Pomodoro());
                } else if (id == R.id.stopwatch_item) {
                    replaceFragment(new StopWatch());
                }
                return true;
            }
        });
        if (savedInstanceState == null) {
            replaceFragment(new Pomodoro());
            binding.bottomNavView.setSelectedItemId(R.id.pomodoro_item);
        } else {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frgamnent_contianer);
            if (currentFragment == null) {
                replaceFragment(new Pomodoro());
                binding.bottomNavView.setSelectedItemId(R.id.pomodoro_item);
            }
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frgamnent_contianer,fragment);
        fragmentTransaction.commit();
    }
}

