package com.example.thoitrang;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView img_bong_giay;
    TextView textViewWelcome, textTTT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        img_bong_giay = findViewById(R.id.img_bong_giay);
        textViewWelcome = findViewById(R.id.textViewWelcome);
        textTTT = findViewById(R.id.textTTT);


        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_bong_giay);
        img_bong_giay.setAnimation(animation);


        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        textViewWelcome.setAnimation(fadeIn);
        textTTT.setAnimation(fadeIn);


        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 4500);
    }
}
