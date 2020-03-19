package com.example.asaram.smallGame;


import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;


public class MixedCupWinner extends AppCompatActivity {
    DatabaseHandler db1;
    static MediaPlayer ring,ring2;
    static Typeface custom_font;
    TextView tv6,tv7,tv28,tv30;
    static String str1,str2,str3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mc_winner);
        //int drid=getResources().getIdentifier(getIntent().getStringExtra("winner").toLowerCase()+"_logos", "drawable", getPackageName());
        ImageView img=(ImageView)findViewById(R.id.imageView10);
        //img.setImageResource(drid);
        ring= MediaPlayer.create(this,R.raw.champion_cheers);
        ring2= MediaPlayer.create(this,R.raw.bullet_sound);
        ring.start();
        DatabaseHelper dbHelper = new DatabaseHelper(this, getFilesDir().getAbsolutePath());

        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.d("ADD_EXPENSES:", e.getMessage());
        }
        db1 = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
        String data[]=new String[4];
        data = db1.getPlayersStatsBat(0,"mc_stats");;
        str2=data[0];
        data = db1.getPlayersStatsBowl(0,"mc_stats");
        str3=data[0];
        str1=db1.giveMCWinner();
        custom_font = Typeface.createFromAsset(getAssets(), "cricket-bold.ttf");
        tv28=(TextView)findViewById(R.id.textView29);
        tv28.setTypeface(custom_font);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_up);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ring2.start();
                tv30=(TextView)findViewById(R.id.textView32);
                tv30.setVisibility(View.VISIBLE);
                tv30.setText(str2);
                tv30.setTypeface(custom_font);
                Animation anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                anim2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        ring2.start();
                        tv30=(TextView)findViewById(R.id.textView33);
                        tv30.setVisibility(View.VISIBLE);
                        tv30.setTypeface(custom_font);
                        tv30.setText(str3);
                        Animation anim3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                        tv30.startAnimation(anim3);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                tv30.startAnimation(anim2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        img.startAnimation(animation);
        tv30=(TextView)findViewById(R.id.textView30);
        tv30.setVisibility(View.VISIBLE);
        tv30.setTypeface(custom_font);
        tv30.setText(str1);
        Animation anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        tv30.startAnimation(anim2);
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();ring.stop();
    }
}


