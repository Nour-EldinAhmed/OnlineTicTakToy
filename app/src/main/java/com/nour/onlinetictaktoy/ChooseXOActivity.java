package com.nour.onlinetictaktoy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.nour.onlinetictaktoy.databinding.ActivityChooseBinding;

import java.util.Timer;
import java.util.TimerTask;

public class ChooseXOActivity extends AppCompatActivity {

    CharSequence player1 = "Player 1";
    CharSequence player2 = "Player 2";
    public boolean SelecteChoosePlayer;
    // private AdView mAdView;
    ActivityChooseBinding binding;
    boolean player1ax;
    String choosePlayer;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        MobileAds.initialize(this, "ca-app-pub-7446594918605317~6259231919");
        AdLoader adLoader = new AdLoader.Builder(this, "ca-app-pub-7446594918605317/2667734599")
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        NativeTemplateStyle styles = new
                                NativeTemplateStyle.Builder().build();

                        TemplateView template = findViewById(R.id.my_template);
                        template.setStyles(styles);
                        template.setNativeAd(unifiedNativeAd);

                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        choosePlayer = getResources().getString(R.string.pick_your_side);


        SelecteChoosePlayer = getIntent().getBooleanExtra("selectedsingleplayer", true);
        if (!SelecteChoosePlayer) {

            binding.txtChoose.setText(player1 + " : " + choosePlayer);

        }

        // to take color and put into image
        binding.imgX.setColorFilter(getApplicationContext().getResources().getColor(R.color.purple_500));
        binding.imgO.setColorFilter(getApplicationContext().getResources().getColor(R.color.purple_500));


        final int textColor = Color.parseColor("#e5e9ea");
        final int textColor_O = Color.parseColor("#515DC7");
        final int textColor_X = Color.parseColor("#E84949");

        binding.playerO.post(new Runnable() {
            @Override
            public void run() {
                if (binding.playerO.isChecked()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        binding.playerO.setButtonTintList(ColorStateList.valueOf(textColor_O));
                    }


                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        binding.playerO.setButtonTintList(ColorStateList.valueOf(textColor));
                    }
                }
                binding.playerO.postDelayed(this, 10);
            }
        });

        binding.playerX.post(new Runnable() {
            @Override
            public void run() {
                if (binding.playerX.isChecked()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        binding.playerX.setButtonTintList(ColorStateList.valueOf(textColor_X));
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        binding.playerX.setButtonTintList(ColorStateList.valueOf(textColor));
                    }

                }
                binding.playerX.postDelayed(this, 10);
            }
        });

        binding.imgO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.playerX.setChecked(false);
                binding.playerO.setChecked(true);

                binding.imgO.setColorFilter(getApplicationContext().getResources().getColor(R.color.tint_o));
                player1ax = false;
                binding.imgO.setImageResource(R.drawable.choose_o);
                binding.imgX.setImageResource(R.drawable.choose_x);
                binding.imgX.setColorFilter(getApplicationContext().getResources().getColor(R.color.transparent));


            }
        });

        binding.imgX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.playerO.setChecked(false);
                binding.playerX.setChecked(true);
                player1ax = true;
                binding.imgX.setColorFilter(getApplicationContext().getResources().getColor(R.color.tint2));
                binding.imgX.setImageResource(R.drawable.choose_o);
                binding.imgX.setImageResource(R.drawable.choose_x);
                binding.imgO.setColorFilter(getApplicationContext().getResources().getColor(R.color.transparent));

            }
        });

        binding.playerO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.playerX.setChecked(false);
                player1ax = false;
                binding.imgO.setImageResource(R.drawable.choose_o);
                binding.imgX.setImageResource(R.drawable.choose_x);
                binding.imgO.setColorFilter(getApplicationContext().getResources().getColor(R.color.tint_o));
                binding.imgX.setColorFilter(getApplicationContext().getResources().getColor(R.color.transparent));


            }
        });

        binding.playerX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.playerO.setChecked(false);
                player1ax = true;
                binding.imgO.setImageResource(R.drawable.choose_o);
                binding.imgX.setImageResource(R.drawable.choose_x);
                binding.imgX.setColorFilter(getApplicationContext().getResources().getColor(R.color.tint2));
                binding.imgO.setColorFilter(getApplicationContext().getResources().getColor(R.color.transparent));

            }
        });


        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {


                if (binding.playerX.isChecked() || binding.playerO.isChecked()) {

                    binding.btnContinue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent i = new Intent(ChooseXOActivity.this, BoardGame.class);
                            CharSequence[] players = {player1, player2};
                            i.putExtra("playersnames", players);
                            i.putExtra("player1ax", player1ax);
                            i.putExtra("selectedsingleplayer", SelecteChoosePlayer);
                            startActivity(i);
                        }
                    });
                }
            }


        }, 0, 20);


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ChooseXOActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
