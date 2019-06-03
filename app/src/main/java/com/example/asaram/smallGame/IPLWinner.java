package com.example.asaram.smallGame;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;


public class IPLWinner extends AppCompatActivity {
    DatabaseHandler db1;
    TextView tv6,tv7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rr_winner);
        int drid=getResources().getIdentifier(getIntent().getStringExtra("winner").toLowerCase()+"_logos", "drawable", getPackageName());
        ImageView img=(ImageView)findViewById(R.id.ImageView3);
        img.setImageResource(drid);
        DatabaseHelper dbHelper = new DatabaseHelper(this, getFilesDir().getAbsolutePath());

        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.d("ADD_EXPENSES:", e.getMessage());
        }
        db1 = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
        String data[]=new String[4];
        data = db1.getPlayersStatsBat(0,"ipl_stats");
        tv6=(TextView)findViewById(R.id.textView6);
        tv7=(TextView)findViewById(R.id.textView9);
        tv6.setText(data[0]);
        data = db1.getPlayersStatsBowl(0,"ipl_stats");
        tv7.setText(data[0]);
    }
}


