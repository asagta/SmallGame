package com.example.asaram.smallGame;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SeriesStart extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;
    public static Button b5,prev,next,b26;
    public static TextView tv,mainHead;
    public static int max_overs,z,za,gree;
    static ImageView ims;
    private TextView tv28;
    DatabaseHandler db1;
    public static String tour_flag="N";
    public static List<String> teams;
    public static String[] tms={"IND","ENG","AUS","NZL","PAK","SLK","WIN","SAF","BAN","AFG"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHelper dbHelper = new DatabaseHelper(this, getFilesDir().getAbsolutePath());
        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.d("ADD_EXPENSES:", e.getMessage());
        }
        db1 = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
        if(getIntent().hasExtra("resume"))
        {
            int gre=db1.getSeriesMatchValue("maxteams");
            int c=(((gre-1)*gre)/2)*3;
            int tm=c+1;
            if(db1.getSeriesMatchValue("mno")==tm)
            {
                Intent intent = new Intent(getBaseContext(), RoundRobinWinner.class);
                intent.putExtra("winner", db1.giveSeriesWinner(tm));
                startActivity(intent);
            }
            else {
                startSeriesHome();
                showStats();
                showStandings();
                showFixtures();
            }
        }
        else {
                setContentView(R.layout.series_select);
                selectTeams();
                selectOvers();
                clickGo();
                teams = new ArrayList<>();
        }
    }
    public void showStandings()
    {
        b5=(Button) findViewById(R.id.button27);
        //add_desc.setOnItemSelectedListener();
        b5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d("CLicked the button: ", "Yes its not null");
                setContentView(R.layout.rr_standings);
                TextView tv,tv1,tv2;
                // teamName.setText(match[0]);
                int j=3,k=1,kk=0,ii=1,i=0;
                String data[]=new String[6];
                while(i<8) {
                    if(k==7)
                    {j=j+1;k=1;kk=0;ii=ii+1;i=i+1;}
                    data = db1.getSeriesStandings(i);
                    String tvID = "vt" + j + ""+k;
                    Log.d("PLAYERS TEXT::",tvID);
                    int resID = getResources().getIdentifier(tvID, "id", getPackageName());
                    tv = ((TextView)findViewById(resID));
                    tv.setText(""+data[kk]);
                    k=k+1;
                    kk=kk+1;
                    if(j==10&&k==7)break;
                }

            }});

        //b1.setText("GoTHere");
    }
    public void showFixturesOnly()
    {
        int i=0,j=0;
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(160,160);
        TableLayout table = (TableLayout) findViewById(R.id.tableLayout1);
        table.removeAllViews();
        int gre=db1.getSeriesMatchValue("maxteams");
        int c=(((gre-1)*gre)/2)*3;
        TableRow[] row = new TableRow[c];
        TextView[] col = new TextView[c];
        String fix[]=new String[3];
        while (i < c)
        {
            fix=db1.getSeriesFixtures(i);
            row[i] = new TableRow(SeriesStart.this);
            params.setMargins(10, 27, 10, 10);
            row[i].setLayoutParams(params);
            while(j<3)
            {
                col[j] = new TextView(SeriesStart.this);
                col[j].setText(fix[j]);
                col[j].setHeight(140);
                col[j].setWidth(550);
                col[j].setGravity(Gravity.CENTER);
                col[j].setTextSize(14);
                col[j].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                row[i].addView(col[j]);
                j++;
            }
            j=0;
            table.addView(row[i]);
            i++;
            // row[k].addView(spacerColumn, new TableRow.LayoutParams(12, 12));
        }
    }
    public void showFixtures()
    {
        b5=(Button) findViewById(R.id.button23);
        //add_desc.setOnItemSelectedListener();
        b5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d("CLicked the button: ", "Yes its not null");
                setContentView(R.layout.rr_fixtures);
                showFixturesOnly();
                prev=(Button)findViewById(R.id.vt0);
                next=(Button)findViewById(R.id.vt01);
                setClickOnNext(prev);
                setClickOnNext(next);
            }});

        //b1.setText("GoTHere");

    }
    public void setClickOnNext(Button t) {
        Log.d("INSIDE::", "TEXTVIEW");
        mainHead=(TextView)findViewById(R.id.vt1);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("CLICKED::", "TEXTVIEW");
                if (z == 0) {
                    z = 1;
                    //setKnockOutMatches();
                    mainHead.setText("Play Offs");
                } else {
                    z = 0;
                    showFixturesOnly();
                    mainHead.setText("Fixtures");
                }

            }
        });
    }
    public void selectTeams()
    {
        int j=1;
        for(int i=0;i<10;i++)
        {
            String tvID = "im" + j;
            Log.d("PLAYERS TEXT::", tvID);
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            ims = ((ImageView)findViewById(resID));
            ims.setTag("removed:"+tms[i]);
            setOnClickTo(ims);
            j++;
        }
        //i=0;
    }
    public void selectOvers()
    {
        Button t20=(Button)findViewById(R.id.button4);
        Button t50=(Button)findViewById(R.id.button24);
        t20.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               max_overs=20;
            }
        });
        t50.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                max_overs=50;
            }
        });
    }
    public void clickGo()
    {
        Button go=(Button)findViewById(R.id.button25);
        go.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //max_overs,max_team(gee) and mno(0) will be sent to series_vars table in db
                //fixtures will be generated as per max team and teams array
                if(max_overs==0)
                    Toast.makeText(getApplicationContext(),"Please select Overs",Toast.LENGTH_SHORT).show();
                else
                    InitSeriesData();
            }
        });
    }
    public void setOnClickTo(final ImageView im)
    {
        im.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("CLicked the button: ", ""+im.getId());
                    if (im.getTag().toString().contains("loaded")) {
                        im.setBackgroundResource(0);
                        gree=gree-1;
                        String str[]=im.getTag().toString().split(":");
                        im.setTag("removed:"+str[1]);
                        teams.remove(db1.getTeamNameFromId(str[1]));
                        Log.d("UNCHECKED",str[1]);
                    }
                    else {
                        if(gree==6)
                        {
                            Toast.makeText(getApplicationContext(),"Limit Reached",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            im.setBackgroundResource(R.drawable.is_select);
                            gree = gree + 1;
                            String str[]=im.getTag().toString().split(":");
                            im.setTag("loaded:"+str[1]);
                            teams.add(db1.getTeamNameFromId(str[1]));
                            Log.d("CHECKED",str[1]);
                        }
                    }
                }
            });
    }
    public void InitSeriesData() {
        int b=(((gree-1)*gree)/2)*3;
        Log.d("B is:",""+b);
        int tms[]=new int[b];
        tms=TeamSelect.randomNoReps(b);
        String teams1[]=new String[b];
        String teams2[]=new String[b];
        int i = 0,j=1,j1=0,jz=0;
        while(j1<b) {
            for (i = 0; i < gree; i++) {
                for (j = i + 1; j < gree; j++) {
                    teams1[j1] = "" + i + "" + j;
                    j1++;
                }
            }// j1++;
        }
        i=0;
        while(i<b)
        {
            Log.d("TMS::",""+teams1[i]);
            teams2[i]=teams1[tms[i]];
            i++;
        }
        i=0;
        while (i < b) {
            Log.d("MC team::",""+teams2[i]);
            Log.d("MC_Team1&2",teams.get(Character.getNumericValue(teams2[i].charAt(0)))+" "+teams.get(Character.getNumericValue(teams2[i].charAt(1))));
            db1.addTourTeamsSeries(teams.get(Character.getNumericValue(teams2[i].charAt(0))),teams.get(Character.getNumericValue(teams2[i].charAt(1))),i+1);
            i++;
        }
        db1.addTourTeamsSeries("TOP1","TOP2",i+1);
        i=0;
        while(i<gree)
        {
            db1.setRankingsSeries(db1.getTeamId(teams.get(i)));
            i++;
        }
        if(max_overs==20)
            db1.setSeriesStats("players_t20");
        else
            db1.setSeriesStats("players_odi");
        db1.setSeriesVars(0);
        startSeriesHome();
    }
    public void startSeriesHome()
    {
        setContentView(R.layout.round_robin);
        showStats();
        showStandings();
        showFixtures();
        int i= db1.getSeriesMatchValue("mno");
        if(i==960)
        {
            Intent intent = new Intent(getBaseContext(), IPLWinner.class);
            intent.putExtra("winner", db1.giveIPLWinner());
            startActivity(intent);
        }
        else {
            String[] fix = db1.getSeriesFixtures(i);
            Log.d("FIXERS", fix[0].toLowerCase() + "_logos");
            int drid = getResources().getIdentifier(db1.getTeamId(fix[0]).toLowerCase() + "_logos", "drawable", getPackageName());
            int drid2 = getResources().getIdentifier(db1.getTeamId(fix[1]).toLowerCase() + "_logos", "drawable", getPackageName());
            ImageView img = (ImageView) findViewById(R.id.imageView5);
            img.setImageResource(drid);
            ImageView img2 = (ImageView) findViewById(R.id.imageView7);
            img2.setImageResource(drid2);
            startMatch();
            //setOnClicktoButton();
        }
    }
    public void startMatch()
    {
        b5=(Button) findViewById(R.id.button22);
        //add_desc.setOnItemSelectedListener();
        b5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String[] values=new String[3];
                db1.setSeriesVars(1);//increment the match Sno by 1
                values=db1.getSeriesFixtures(db1.getSeriesMatchValue("mno")-1);
                Log.d("CURRENT-MATCH::",values[0]+" "+values[1]);
                tour_flag="S";
                //db1.insertCurrMatch(value1,value2,"N",maxovers);
                QuickPlay.maxovers=db1.getSeriesMatchValue("maxovers");
                db1.insertCurrMatch(values[0],values[1],tour_flag,QuickPlay.maxovers);
                startActivity(new Intent(SeriesStart.this, PlayersSelect.class));
            }});
    }
    public void showStats() {
        b26=(Button)findViewById(R.id.button26);
        b26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SeriesStart.this, RoundRobinStats.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
        startSeriesHome();
    }
}
