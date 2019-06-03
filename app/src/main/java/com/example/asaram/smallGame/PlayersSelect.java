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

public class PlayersSelect extends AppCompatActivity {
    DatabaseHandler db1;
    private TextView t1[];
    private TextView tv,pl1,pl2,match,runs,fifty,fuur,hs,srate,avg;
    private Button bt;
    static TextView pid[];
    static int res[];
    static String tmp;
    static int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.players_select);
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
           if(IPLCentral.tour_flag.equals("I"))
             tv.setText(db1.getPlayerNameIPL(teamName,k));
           else
              tv.setText(db1.getPlayerName(teamName,k));
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
                        pd.setBackgroundResource(0);
                    }
                    else{
                        pd.setBackgroundResource(R.color.goldy);
                        if (res[0] == 0 ) {
                            res[0] = pd.getId();
                            updateStats((String)pd.getText());
                        } else if (res[1] == 0) {
                            res[1] = pd.getId();
                            updateStats((String)pd.getText());
                        } else {
                            TextView pd1 = ((TextView) findViewById(res[1]));
                            pd1.setBackgroundResource(0);
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

                     pl1.setBackgroundResource(0);
                     pl2.setBackgroundResource(0);
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
                Log.d("Foreign::",""+checkForeignCount());
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
                        for (int z = 0; z < 11; z++) {
                            db1.insertCurrPlayers(db1.getCurrTeamName(0), (String) pid[z].getText(), z + 1, db1.getCanBowlStatus((String) pid[z].getText()));
                        }
                        res[0] = 0;
                        res[1] = 0;
                        flag = flag + 1;
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
                            db1.insertCurrPlayers(db1.getCurrTeamName(1), (String) pid[z].getText(), z + 1, db1.getCanBowlStatus((String) pid[z].getText()));
                        }
                        flag = 0;
                        startActivity(new Intent(PlayersSelect.this, TossTeams.class));
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
     match.setText(""+db1.getMatches(pName));
     //runs,fifty,fuur,hs,srate,avg;
     runs = ((TextView) findViewById(R.id.p32));
     runs.setText(""+db1.getMatchRuns(pName));
     avg = ((TextView) findViewById(R.id.p33));
     int m=db1.getMatches(pName);int r=db1.getMatchRuns(pName);
     int no=db1.getNotOuts(pName);
     float averag=(float)r/(m-no);
     avg.setText(""+averag);
     fifty = ((TextView) findViewById(R.id.p34));
     int rew[]=new int[3];
     rew=db1.getMatchRew(pName);
     fifty.setText(""+rew[0]+"/"+rew[1]+"/"+rew[2]);
     hs = ((TextView) findViewById(R.id.p35));
     hs.setText(""+db1.getMatchHS(pName));
     fuur = ((TextView) findViewById(R.id.p36));
     int fsix[]=new int[2];
     fsix=db1.getMatchFsix(pName);
     fuur.setText(""+fsix[0]+"/"+fsix[1]);
     srate = ((TextView) findViewById(R.id.p37));
     int bls=db1.getMatchBalls(pName);
     float strRate=(float)r/bls;strRate=strRate*100;
     srate.setText(""+strRate);
 }
}

