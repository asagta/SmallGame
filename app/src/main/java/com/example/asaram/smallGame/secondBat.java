package com.example.asaram.smallGame;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class secondBat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity ma=new MainActivity();
        setContentView(R.layout.second_batting);
        InningsBreak();
    }

    void InningsBreak()
    {
        /*final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.second_batting);
        dialog.setTitle("INNINGS BREAK");
        android.view.Window window = dialog.getWindow();
        window.setLayout(LayoutParams.WRAP_CONTENT,1500);*/
        //code for initializing the components in dialog
        setContentView(R.layout.second_batting);
        //TextView scoreLine=(TextView)findViewById(R.id.textView7);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "Shusha02.ttf");
        //scoreLine.setTypeface(custom_font);
        //scoreLine.setTextSize(34);
        Button start2=(Button)findViewById(R.id.button8);
        Log.d("IND VARIABLE IS: ",""+MainActivity.ind);
        Log.d("RUNS VARIABLE IS: ",""+MainActivity.runs);
        Log.d("WICK VARIABLE IS: ",""+MainActivity.wick);
        Log.d("AUS VARIABLE IS: ",""+MainActivity.aus);
        Log.d("FlagBat VARIABLE IS: ",""+MainActivity.flagBat);
        Log.d("Over VARIABLE IS: ",""+MainActivity.over);
        Log.d("Ball VARIABLE IS: ",""+MainActivity.ball);
        MainActivity.ind2=MainActivity.runs+1;
        //scoreLine.setText("jaIt ko ilayao " + MainActivity.ind2 +" rna caaihyao");
        MainActivity.ind=MainActivity.runs;
        MainActivity.runs=0;
        MainActivity.wick=0;//flagBat=1;
        MainActivity.over=0;MainActivity.ball=0;MainActivity.runsLeft=MainActivity.ind+1;

        start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setContentView(R.layout.activity_main);
                Intent myIntent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(myIntent);
                //score.setText(""+MainActivity.runs+"-"+MainActivity.wick);
                //result.setText("NEW");
                //Log.d("IND VARIABLE IS: ",""+ind);

                //dialog.dismiss();  //australia dialog exits
            }
        });
        //dialog.show();
    }
}
