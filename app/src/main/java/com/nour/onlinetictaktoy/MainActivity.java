package com.nour.onlinetictaktoy;

import androidx.annotation.NonNull;
import androidx.appcompat.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nour.onlinetictaktoy.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private boolean Vibration;

    private FirebaseAnalytics mFirebaseAnalytics;


    private static final String PREFS_NAME = "vibration";
    private static final String PREF_VIBRATION = "TicVib";

    ActivityMainBinding binding;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
/*
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
*/
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "das");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Dfasf");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        mFirebaseAnalytics.setUserProperty("favor", "Das");

        mFirebaseAnalytics.setCurrentScreen(this, "MainActivity", null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        binding.btnAI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseXOActivity.class);
                startActivity(intent);

            }

        });

        binding.btnOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OnlineGame.class);
                startActivity(intent);
            }
        });

        binding.imgSettingMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                /*  final Animation myRotation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotete_anim);
                myRotation.setRepeatCount(0);
                binding.imgSettingMain.startAnimation(myRotation);
 */
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presentActivity(v);

                    }
                }, 900);


            }
        });
    }


    public void presentActivity(View view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, view, "transition");

        int revealX = (int) (view.getX() + view.getWidth() / 2);

        int revealY = (int) (view.getY() + view.getHeight() / 2);
        Intent intent =
                new Intent(this, SettingsActivity.class);

        intent.putExtra(SettingsActivity.
                EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(SettingsActivity.
                EXTRA_CIRCULAR_REVEAL_Y, revealY);
        ActivityCompat.
                startActivity(this, intent, options.toBundle());
    }


    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

}
