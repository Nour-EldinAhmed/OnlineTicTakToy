package com.nour.onlinetictaktoy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.nour.onlinetictaktoy.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        // opacity of Views is disappear
        binding.txtTictactoe.setAlpha(0f);
        binding.txtversion.setAlpha(0f);


        binding.txtTictactoe.animate()
                .translationY(binding.txtTictactoe.getHeight())
                .alpha(1f)
                .setStartDelay(1000)
                .setDuration(1200);

        binding.txtversion.animate()
                .translationY(binding.txtTictactoe.getHeight())
                .alpha(1f)
                .setStartDelay(1500)
                .setDuration(1000);


        binding.imgTictactoe.setAlpha(0f);
        binding.imgTictactoe.animate()
                .translationY(binding.txtTictactoe.getHeight())
                .alpha(1f)
                .setDuration(800);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
            }
        }, 4000);

    }
}
