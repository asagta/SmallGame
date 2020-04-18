package com.example.asaram.smallGame;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;


public class TossDecision extends AppCompatActivity {
 static int s1,s2,choose,rno;
    private TextView tv,tv2,tv3;
    private Button b1,b2,b3;
    DatabaseHandler db1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toss_last);
        DatabaseHelper dbHelper = new DatabaseHelper(this, getFilesDir().getAbsolutePath());
        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.d("ADD_EXPENSES:", e.getMessage());
        }
        db1 = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
        Random rand = new Random();
        tv=(TextView)findViewById(R.id.textView4);
        tv2=(TextView)findViewById(R.id.textView3);
        tv3=(TextView)findViewById(R.id.textView7);
        s1 = rand.nextInt(2) + 1;
        rno=rand.nextInt(19) + 1;
        Log.d("TOSS_TAKEN",""+TossTeams.res);
        Log.d("TOSS_GOT",""+s1);
        b1=(Button)findViewById(R.id.button19);
        b2=(Button)findViewById(R.id.button20);
        b3=(Button)findViewById(R.id.button21);
        b3.setEnabled(false);
       if(TossTeams.res==s1)
       {
           tv2.setText(db1.getCurrTeamName(0));
           tv.setText("WON THE TOSS");
           chooseBat();
           chooseBowl();
       }
       else
       {
           tv2.setText(db1.getCurrTeamName(0));
           tv.setText("LOST THE TOSS");

           //tv2.setText(db1.getCurrTeamName(1));
           Random rand2 = new Random();
           s2=rand2.nextInt(2)+1;
          b1.setVisibility(View.GONE);b2.setVisibility(View.GONE);
           if(s2==1) {
               tv3.setText(db1.getCurrTeamName(1)+" ARE BATTING");
               choose=2;
           }
           else
           {
               tv3.setText(db1.getCurrTeamName(1)+" ARE BOWLING");
           }
           b3.setEnabled(true);
       }
       startMatch();
    }
    public void chooseBat()
    {
        b1 = (Button) findViewById(R.id.button19);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose=1;
                b3.setEnabled(true);
            }
        });
    }
    public void chooseBowl()
    {
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose=2;
                b3.setEnabled(true);

            }
        });
    }
    public void startMatch()
    {
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CHOOSE",""+choose);
                if(choose==2)
                {
                    if(QuickPlay.testFlag==1) {
                        db1.setTossTest(1);
                        startActivity(new Intent(TossDecision.this, Bowling_Test.class));
                    }
                    else
                    {
                        db1.reverseTeams();
                        db1.setToss(2);
                        startActivity(new Intent(TossDecision.this, Bowling.class));
                    }

                }
                else{
                    db1.setToss(1);
                    if(QuickPlay.testFlag==1) {
                        db1.setTossTest(0);
                        startActivity(new Intent(TossDecision.this, MainActivity_Test.class));
                    }
                        else
                        startActivity(new Intent(TossDecision.this, MainActivity.class));
                }
                b3.setEnabled(true);
            }
        });
    }
}


