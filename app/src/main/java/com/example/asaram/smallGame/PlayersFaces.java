package com.example.asaram.smallGame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PlayersFaces extends AppCompatActivity {
    DatabaseHandler db1;
    private TextView t1[];
    static TextView tv,pl1,pl2,match,runs,fifty,fuur,hs,srate,avg;
    static TextView t2,t3,t4,t5,t6,vt,tm1,tm2;
    static ImageView im;
    private Button bt;
    static Button next,prev;
    static TextView pid[];
    static int res[];
    static String tmp;
    static int flag,z;
    static ScrollView scroll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_faces);
        DatabaseHelper dbHelper = new DatabaseHelper(this, getFilesDir().getAbsolutePath());

        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.d("ADD_EXPENSES:", e.getMessage());
        }
        db1 = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
        initializeTeamIcons();

        /*final Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            public void run() {
                timer2.cancel(); //this will cancel the timer of the system
            }
        }, 10000);*/
        loadPlayersImages(flag);
        //while(z!=1){}
        //loadPlayersImages(1);
        res=new int[2];
        Log.d("TOUR_fLAG::",TournamentCentral.tour_flag);
        //vt = ((TextView) findViewById(R.id.vt1));
    }
    void runAsync()
    {
        final Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            public void run() {
                loadPlayersImages(0);
                timer2.cancel(); //this will cancel the timer of the system
            }
        }, 10000);


    }
   void loadPlayersImages(int a)
   {
       ConstraintLayout l1 = (ConstraintLayout) findViewById(R.id.f1);
       String flid;
       if(IPLCentral.tour_flag.equals("I"))
         flid = db1.getCurrTeamName(a).toLowerCase()+"_logos";
       else
           flid = db1.getCurrTeamName(a).toLowerCase()+"_flag";
       int lid = getResources().getIdentifier(flid, "drawable", getPackageName());
       l1.setBackgroundResource(lid);
       int i,k=0;
       String teamName;String conv_pname;
       teamName=db1.getCurrTeamName(a);
       //Log.d("CURRENT TEAMS::",db1.getCurrTeamName(0)+" "+db1.getCurrTeamName(1));
       for(i=1;i<12;i++)
       {
           String tvID = "pl" + i;
           String tvID2 = "pln" + i;
           int resID = getResources().getIdentifier(tvID, "id", getPackageName());
           int resID2 = getResources().getIdentifier(tvID2, "id", getPackageName());
           im=(ImageView)findViewById(resID);
           tv = ((TextView)findViewById(resID2));
           //tv.setText(db1.getPlayerName(teamName,k));
           if(IPLCentral.tour_flag.equals("I")) {
               conv_pname = convertPlayer(db1.getPlayerNameIPL(teamName, k));
               tv.setText(db1.getPlayerNameIPL(teamName,k));
           }else {
               if(QuickPlay.testFlag==1) {
                   conv_pname = convertPlayer(db1.getPlayerNameFaces(teamName, k, "curr_players_test"));
                   tv.setText(db1.getPlayerNameFaces(teamName,k,"curr_players_test"));
               }else {
                   conv_pname = convertPlayer(db1.getPlayerNameFaces(teamName, k, "curr_players"));
                   tv.setText(db1.getPlayerNameFaces(teamName,k,"curr_players"));
               }
           }
               //int resID3=getResources().getIdentifier(conv_pname, "drawable", getPackageName());
           String filepath= Environment.getExternalStorageDirectory()+"/"+"HomeCric/Players/"+conv_pname+".png";
           Bitmap bitmap = BitmapFactory.decodeFile(filepath);
           //im.setImageResource(resID3);
           im.setImageBitmap(bitmap);
           k++;
       }
       flag++;
       //timer to remain as it for 10 seconds
       final Timer timer2 = new Timer();
       timer2.schedule(new TimerTask() {
           public void run() {
               z=1;
               if(flag==1)
                startActivity(new Intent(PlayersFaces.this, PlayersFaces.class));
               else {
                   startActivity(new Intent(PlayersFaces.this, TossTeams.class));
                   flag = 0;
               }
               timer2.cancel(); //this will cancel the timer of the system
           }
       }, 10000);
     //  if(flag==1) loadPlayersImages(1);
       if(flag==2)
       {
           flag=0;
           //startActivity(new Intent(PlayersFaces.this, TossTeams.class));
       }
   }
   public static String convertPlayer(String pl)
   {
      String tmp=pl.toLowerCase();
      tmp=tmp.replace(".","_");tmp=tmp.replace(" ","_");
      return tmp;
   }
     void initializeTeamIcons()
     {
         tm1=(TextView) findViewById(R.id.tm1);
         tm2=(TextView) findViewById(R.id.tm2);
         tm1.setText(db1.getCurrTeamName(0));
         tm2.setText(db1.getCurrTeamName(1));
     }


 }



