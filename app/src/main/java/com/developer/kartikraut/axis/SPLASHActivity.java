package com.developer.kartikraut.axis;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.developer.kartikraut.axis.Axis_Home.Home;



public class SPLASHActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    ImageView logo,typo;
    TextView tv_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

       logo = (ImageView)findViewById(R.id.imgLogo);
       typo = (ImageView)findViewById(R.id.typo);
        Glide.with(getApplicationContext()).load(R.drawable.composite311_white1)
                .dontAnimate()
                .into(typo);

        Animation logofade = AnimationUtils.loadAnimation(getBaseContext(), R.anim.logofade);
        final Animation fade = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade);

        final Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(1000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    Intent start = new Intent(SPLASHActivity.this, Home.class);
                    startActivity(start);
                    finish();
                    overridePendingTransition(R.anim.fadefast,R.anim.fadefast);
                }
            }
        };

        logo.startAnimation(logofade);
        logofade.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                logo.setVisibility(View.VISIBLE);
                typo.startAnimation(fade);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fade.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                typo.setVisibility(View.VISIBLE);
                timer.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    /*    logo = (ImageView)findViewById(R.id.imgLogo);
        Animation logofade = AnimationUtils.loadAnimation(getBaseContext(), R.anim.logofade);
        final Animation fade = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SPLASHActivity.this, Home.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);  */
    }
}

