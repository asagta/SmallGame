package com.example.asaram.smallGame;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class StatsBatting extends AppCompatActivity {
    DatabaseHandler db1;
    private TextView b1,stat1,stat2,head1,head2,mainHead;
    public Button b4,b7,next,prev;
    //public static int tms[];
    private Spinner add_desc,add_desc2;
    static int z,index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_bat);
        index++;
        DatabaseHelper dbHelper = new DatabaseHelper(this, getFilesDir().getAbsolutePath());

        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.d("ADD_EXPENSES:", e.getMessage());
        }
        db1 = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
        setStats();
        stat1=(TextView)findViewById(R.id.vt1);
        prev=(Button)findViewById(R.id.vt0);
        next=(Button)findViewById(R.id.vt01);
        head1=(TextView)findViewById(R.id.vt23);
        head2=(TextView)findViewById(R.id.vt24);
        mainHead=(TextView)findViewById(R.id.vt1);
        setClickOnNext(prev,"M");
        setClickOnNext(next,"P");
    }
 public void setStats()
 {
     TextView tv,tv1,tv2;
     // teamName.setText(match[0]);
     int j=3,k=1,kk=0,ii=1,i=0;
     String data[]=new String[4];
     while(i<11) {
         if(k==5)
         {j=j+1;k=1;kk=0;ii=ii+1;i=i+1;}
         data = db1.getPlayersStatsBat(i,"players");
         String tvID = "vt" + j + ""+k;
         Log.d("PLAYERS TEXT::",tvID);
         int resID = getResources().getIdentifier(tvID, "id", getPackageName());
         tv = ((TextView)findViewById(resID));
         tv.setText(""+data[kk]);
         k=k+1;
         kk=kk+1;
     }
 }
 public void setStatsBowl()
    {
        TextView tv,tv1,tv2;
        // teamName.setText(match[0]);
        int j=3,k=1,kk=0,ii=1,i=0;
        String data[]=new String[4];
        while(i<11) {
            if(k==5)
            {j=j+1;k=1;kk=0;ii=ii+1;i=i+1;}
            data = db1.getPlayersStatsBowl(i,"players");
            String tvID = "vt" + j + ""+k;
            Log.d("PLAYERS TEXT::",tvID);
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            tv = ((TextView)findViewById(resID));
            tv.setText(""+data[kk]);
            k=k+1;
            kk=kk+1;
        }
    }

    public void setBatRatings()
    {
        TextView tv,tv1,tv2;
        // teamName.setText(match[0]);
        int j=3,k=1,kk=0,ii=1,i=0;
        String data[]=new String[4];
        while(i<11) {
            if(k==5)
            {j=j+1;k=1;kk=0;ii=ii+1;i=i+1;}
            data = db1.getBatRatings(i);
            String tvID = "vt" + j + ""+k;
            Log.d("PLAYERS TEXT::",tvID);
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            tv = ((TextView)findViewById(resID));
            tv.setText(""+data[kk]);
            k=k+1;
            kk=kk+1;
        }
    }
 public void setClickOnNext(Button t,final String call) {
     Log.d("INSIDE::", "TEXTVIEW");

     t.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             if(call.equals("P"))
             {   index++; if(index==4){index=1;}}
             else
             {  index--; if(index==0){index=3;}}
          /*   if (z == 0) {
                 z = 1;
                 setStatsBowl();
                 head1.setText("Dots");
                 head2.setText("Wickets");
                 mainHead.setText("Most Wickets");
             } else {
                 z = 0;
                 setStats();
                 head1.setText("Matches");
                 head2.setText("Runs");
                 mainHead.setText("Most Runs");
             }*/
          if(index==1)
          {setStats();
              head1.setText("Matches");
              head2.setText("Runs");
              mainHead.setText("Most Runs");
          }
          else if(index==2)
          {
              setStatsBowl();
              head1.setText("Dots");
              head2.setText("Wickets");
              mainHead.setText("Most Wickets");
          }
          else
          {
              setBatRatings();
              head1.setText("Runs");
              head2.setText("Points");
              mainHead.setText("BATSMEN RATINGS");
          }


         }
     });
 }
}

