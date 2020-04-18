package com.example.asaram.smallGame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class PlayersSelect_Test extends AppCompatActivity {
    DatabaseHandler db1;
    private TextView t1[];
    static TextView tv,pl1,pl2,match,runs,fifty,fuur,hs,srate,avg,inns;
    static TextView t2,t3,t4,t5,t6;
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
        setContentView(R.layout.select_players);
        DatabaseHelper dbHelper = new DatabaseHelper(this, getFilesDir().getAbsolutePath());

        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.d("ADD_EXPENSES:", e.getMessage());
        }
        db1 = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
        loadPlayers(0);
        res=new int[2];
        initialize();
        setClickonButton();
        clickOnNext();
        Log.d("TOUR_fLAG::",TournamentCentral.tour_flag);
    }
   void loadPlayers(int a)
   {

       int i,k=0;
       String teamName;
       teamName=db1.getCurrTeamName(a);
       Log.d("CURRENT TEAMS::",db1.getCurrTeamName(0)+" "+db1.getCurrTeamName(1));
       for(i=1;i<17;i++)
       {
           String tvID = "p" + i;
           int resID = getResources().getIdentifier(tvID, "id", getPackageName());
           tv = ((TextView)findViewById(resID));
           tv.setText(db1.getPlayerNameTest(teamName,k));
           k++;
       }
   }
    void setOnClicktoButton(final TextView pd)
    {
        int i,l=0;final int k;
    pd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   Log.d("THe value in res::",""+res[0]+" "+res[1] );
                    if(pd.getId()==res[0])
                    {
                        res[0]=0;
                        pd.setBackgroundResource(R.drawable.trape2);
                    }
                    else{
                        pd.setBackgroundResource(R.color.greenTransparent);
                        if (res[0] == 0 ) {
                            res[0] = pd.getId();
                            updateStats((String)pd.getText());
                        } else if (res[1] == 0) {
                            res[1] = pd.getId();
                            updateStats((String)pd.getText());
                        } else {
                            TextView pd1 = ((TextView) findViewById(res[1]));
                            pd1.setBackgroundResource(R.drawable.trape);
                            res[1] = pd.getId();
                            updateStats((String)pd.getText());
                            //stats.setText()
                        }

                    }
                }
            });
        //add_desc.setOnItemSelectedListener();
    }
     void initialize()
     {
         pid = new TextView[20];
         int k=0;
         for(int i=1;i<17;i++) {
             String tvID = "p" + i;
             int resID = getResources().getIdentifier(tvID, "id", getPackageName());
             pid[k] = ((TextView) findViewById(resID));
             setOnClicktoButton(pid[k]);
             k++;
         }
     }
     void setClickonButton(){
         bt=(Button)findViewById(R.id.button2);
         Log.d("THe value in res::",""+res[0]+" "+res[1] );
         pl1=(TextView) findViewById(res[0]);
         pl2=(TextView) findViewById(res[1]);
         //add_desc.setOnItemSelectedListener();
         bt.setOnClickListener(new View.OnClickListener(){

             @Override
             public void onClick(View view) {
                 if(res[0]==0 && res[1]==0)
                 {

                 }
                 else {
                     Log.d("THvalueres when clickd:", "" + res[0] + " " + res[1]);
                     pl1 = (TextView) findViewById(res[0]);
                     pl2 = (TextView) findViewById(res[1]);
                     db1.updatePlayerID((String)pl1.getText(),(String)pl2.getText());
                     tmp = (String) pl1.getText();
                     pl1.setText(pl2.getText());
                     pl2.setText(tmp);

                     pl1.setBackgroundResource(R.drawable.trape);
                     pl2.setBackgroundResource(R.drawable.trape2);
                     res[0] = 0;
                     res[1] = 0;
                 }
             }});
     }

    void clickOnNext(){
        Button bt1=(Button)findViewById(R.id.button3);
        //add_desc.setOnItemSelectedListener();
        bt1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //Log.d("Foreign::",""+checkForeignCount());
                if(IPLCentral.tour_flag.equalsIgnoreCase("I") && checkForeignCount()>4)
                {
                    Toast.makeText(getApplicationContext(),"You can select max of 4 foreign Players",Toast.LENGTH_SHORT).show();
                }
                else
                {
                if(flag==0) {
                    if(checkBowlersCount()<6)
                    {
                        Toast.makeText(getApplicationContext(),"You can select a min of 6 Bowlers!!",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //insert players into curr_match
                        db1.deleteCurrPlayers();
                        db1.deleteCurrPlayersTest();
                        for (int z = 0; z < 11; z++) {
                            if(QuickPlay.testFlag==1)
                            {

                                db1.insertCurrTestPlayers(db1.getCurrTeamName(0), (String) pid[z].getText(), z + 1, db1.getCanBowlStatus((String) pid[z].getText()), db1.getLimitSix((String) pid[z].getText()));
                            }
                            else {
                                db1.insertCurrPlayers(db1.getCurrTeamName(0), (String) pid[z].getText(), z + 1, db1.getCanBowlStatus((String) pid[z].getText()), db1.getLimitSix((String) pid[z].getText()));
                            }
                        }
                        res[0] = 0;
                        res[1] = 0;
                        flag = flag + 1;
                        if(QuickPlay.testFlag==1)
                            db1.initializeScoresTest(db1.getCurrTeamName(0), db1.getCurrTeamName(1));
                        else
                            db1.initializeScores(db1.getCurrTeamName(0), db1.getCurrTeamName(1));
                        loadPlayers(flag);
                    }
                }
                else
                {
                    if(checkBowlersCount()<6)
                    {
                        Toast.makeText(getApplicationContext(),"You can select a min of 6 Bowlers!!",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        for (int z = 0; z < 11; z++) {
                            if(QuickPlay.testFlag==1)//if it is a test match
                            {
                                db1.insertCurrTestPlayers(db1.getCurrTeamName(1), (String) pid[z].getText(), z + 1, db1.getCanBowlStatus((String) pid[z].getText()),db1.getLimitSix((String) pid[z].getText()));
                            }
                            else
                              db1.insertCurrPlayers(db1.getCurrTeamName(1), (String) pid[z].getText(), z + 1, db1.getCanBowlStatus((String) pid[z].getText()),db1.getLimitSix((String) pid[z].getText()));
                        }
                        flag = 0;
                        startActivity(new Intent(PlayersSelect_Test.this, PlayersFaces.class));
                    }
                }
                }
            }});
    }
    public int checkForeignCount()
    {
        int i=0;
        for(int z=0;z<11;z++)
        {
            if(db1.getIsForeign((String)pid[z].getText()).equalsIgnoreCase("Y"))
                i=i+1;
        }
        return i;
    }
    public int checkBowlersCount()
    {
        int i=0;
        for(int z=0;z<11;z++)
        {
            if(db1.getIsBowler((String)pid[z].getText()).equalsIgnoreCase("Y"))
                i=i+1;
        }
        return i;
    }
 public void updateStats(String pName)
 {
     match = ((TextView) findViewById(R.id.p31));
     match.setText(""+db1.getStatsTest("Matches",pName));
     //runs,fifty,fuur,hs,srate,avg;
     inns = ((TextView) findViewById(R.id.p32));
     inns.setText(""+db1.getStatsTest("Innings",pName));
     runs = ((TextView) findViewById(R.id.p33));
     runs.setText(""+db1.getStatsTest("Runs",pName));
     avg = ((TextView) findViewById(R.id.p34));
     t2 = ((TextView) findViewById(R.id.p40));t3 = ((TextView) findViewById(R.id.p50));t4 = ((TextView) findViewById(R.id.p60));t5 = ((TextView) findViewById(R.id.p70));t6 = ((TextView) findViewById(R.id.p80));
     t2.setText("Innings");t3.setText("Runs");t4.setText("Avg");t5.setText("50/100/200");t6.setText("Highest");
     int m=db1.getStatsTest("Innings",pName);int r=db1.getStatsTest("Runs",pName);
     int no=db1.getStatsTest("nouts",pName);
     float averag=Math.abs((float)r/(m-no));
     avg.setText(""+averag);
     fifty = ((TextView)findViewById(R.id.p35));
     int rew[]=new int[3];
     rew=db1.getMatchRewTest(pName);
     fifty.setText(""+rew[0]+"/"+rew[1]+"/"+rew[2]);
     hs = ((TextView) findViewById(R.id.p36));
     hs.setText(""+db1.getStatsTest("Highest",pName));
     /*srate = ((TextView) findViewById(R.id.p36));
     int bls=db1.getMatchBalls(pName);
     float strRate=(float)r/bls;strRate=strRate*100;
     srate.setText(""+strRate);*/
     updateLast5(pName);
     prev=(Button)findViewById(R.id.vt0);next=(Button)findViewById(R.id.vt01);
     updateBowlStats(prev,pName);updateBowlStats(next,pName);
 }
 public void updateBowlStats(final Button b, final String pName)
 {
     b.setOnClickListener(new View.OnClickListener(){

         @Override
         public void onClick(View view) {
             if(z==0)
             {
                 z=1;
                 TextView vt = ((TextView) findViewById(R.id.vt1));
                 vt.setText("BOWLING");
                 t2.setText("Wickets");t3.setText("4WI");t4.setText("5WI");t5.setText("Best");t6.setText("Economy");
                 String str[]=db1.getBowlStatsPlayerTest(pName);
                 inns.setText(str[0]);
                 runs.setText(str[1]);
                 avg.setText(str[2]);
                 fifty.setText(str[3]);
                 int bovers=db1.getStatsTest("bovers",pName);
                 int bruns=db1.getStatsTest("bruns",pName);
                 float res=(float)(bruns*6)/(bovers);
                 double rrr = Math.round(res * 100.0) / 100.0;
                 hs.setText(""+rrr);
             }
             else
             {
                 z=0;
                 TextView vt = ((TextView) findViewById(R.id.vt1));
                 vt.setText("BATTING");
                 updateStats(pName);
             }
         }});
 }
 public void updateLast5(String pname) {
     TextView vs;
     TextView bat;
     TextView bowl;
     String[] str=new String[5];
     int z=3,i1=0;
     int max = db1.getLast5MatchTest(pname);
     if (max == 0) {//print - everywhere
     for(int i=1;i<6;i++)
     {
         for(int j=0;j<3;j++) {
             String tvID = "p" + z + "" + i1+"1";
             int resID = getResources().getIdentifier(tvID, "id", getPackageName());
             vs = ((TextView) findViewById(resID));
             vs.setText("-");
             i1=i1+1;
         }
         i1=0;
         z=z+1;
     }
     }
     else{
         for(int i=1;i<=5;i++)
         {
             str=db1.getLast5MatchDataTest(pname,i);
             int j1=0;
             for(int j=0;j<3;j++) {
                 String tvID = "p" + z + "" + i1+"1";
                 int resID = getResources().getIdentifier(tvID, "id", getPackageName());
                 vs = ((TextView) findViewById(resID));
                 if(j1==0) {
                     vs.setText(str[j]);
                     j1=j1+1;
                 }
                 else {
                     vs.setText(str[j1] + "&" + str[j1 + 1]);
                     j1 = j1 + 2;
                 }
                 i1=i1+1;
             }
             i1=0;j1=0;
             z=z+1;
         }
     }
     }
 }



