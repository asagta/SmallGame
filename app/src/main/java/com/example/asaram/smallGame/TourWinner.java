package com.example.asaram.smallGame;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ImageView;


public class TourWinner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winner_tour1);
        int drid=getResources().getIdentifier(MainActivity.winner.toLowerCase()+"_logos", "drawable", getPackageName());
        ImageView img=(ImageView)findViewById(R.id.ImageView3);
        img.setImageResource(drid);
    }
}


