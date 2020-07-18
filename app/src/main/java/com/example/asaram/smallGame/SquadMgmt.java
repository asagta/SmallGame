package com.example.asaram.smallGame;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class SquadMgmt extends AppCompatActivity {
    DatabaseHandler db1;
    public static TextView tva, mainHead;
    static TextView tv,pl1,pl2,match,runs,fifty,fuur,hs,srate,avg;
    static TextView t2,t3,t4,t5,t6,vt,inns;
    public static Button b5, prev, next, b26,vt0,vt01;
    public static String tour_flag = "N";
    public static final int PICKFILE_RESULT_CODE = 1;
    static int res[];
    static int z,z1,fmt;
    static String[] format,tms,tms2,tables;
    //public List<String> teams;
    static Spinner teamId, bowl;
    static EditText pname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.squad_mgmt);
        DatabaseHelper dbHelper = new DatabaseHelper(this, getFilesDir().getAbsolutePath());
        format=new String[]{"TEST","ODI","T20"};
        tables=new String[]{"players_test","players_odi","players_t20"};
        tms=new String[]{"IND","ENG","PAK","AUS","WIN","SAF","NZL","BAN","SLK","AFG"};
        tms2=new String[]{"INDIA","ENGLAND","PAKISTAN","AUSTRALIA","WEST INDIES","SOUTH AFRICA","NEW ZEALAND","BANGLADESH","SRI LANKA","AFGHANISTAN"};
        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.d("ADD_EXPENSES:", e.getMessage());
        }
        db1 = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
        showFormats();
        showSquadOnly(0);
        res = new int[2];
        setClickonSwap();
        setClickonAddMore();
        showOtherTeams();
    }
 public void showFormats()
    {
        Button next=(Button) findViewById(R.id.btnnext);
        Button prev=(Button) findViewById(R.id.btnprev);
        final TextView pd1 = ((TextView) findViewById(R.id.textView35));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fmt=fmt+1;
                if(fmt==3) fmt=0;
                pd1.setText(""+format[fmt]);
                showSquadOnly(0);
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fmt=fmt-1;
                if(fmt==-1) fmt=2;
                pd1.setText(""+format[fmt]);
                showSquadOnly(0);
            }
        });
    }
public void showOtherTeams()
{
    next=(Button) findViewById(R.id.btnnext2);
    prev=(Button) findViewById(R.id.btnprev2);
    final TextView pd1 = ((TextView) findViewById(R.id.textView36));
    next.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            z=z+1;
            if(z==10) z=0;
            pd1.setText(""+tms2[z]);
            showSquadOnly(0);
        }
    });
    prev.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            z=z-1;
            if(z==-1) z=9;
            pd1.setText(""+tms2[z]);
            showSquadOnly(0);
        }
    });
}

    public void showSquadOnly(int i1) {
        int i, j = 0, ii = +1;
        TableLayout table = (TableLayout) findViewById(R.id.tableLayout3);
        table.removeAllViews();
        String pl = new String();
        int tplay;
        if (fmt == 0)
            tplay = db1.getTotalPlayers(tms[z], "players_test");
        else if (fmt == 1)
            tplay = db1.getTotalPlayers(tms[z], "players_odi");
        else
            tplay = db1.getTotalPlayers(tms[z], "players_t20");
        TableRow[] row = new TableRow[tplay];
        TextView[] col = new TextView[tplay];
        int k = 0;
        String teamName;
        teamName = tms[z];
        for (i = 1; i < 19; i++) {
            String tvID = "p" + i;
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            tv = ((TextView) findViewById(resID));
            tv.setBackgroundResource(R.drawable.trape);
            setOnClicktoButton(tv);
            if (fmt == 0)
                tv.setText(db1.getPlayerName(teamName, k, "players_test"));
            else if (fmt == 1)
                tv.setText(db1.getPlayerName(teamName, k, "players_odi"));
            else
                tv.setText(db1.getPlayerName(teamName, k, "players_t20"));
            k++;
        }
     int w=table.getWidth();int h=table.getHeight();
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(510,h);
     i=0;
     while (k < tplay) {
            pl = db1.getPlayerName(teamName, k, tables[fmt]);
            row[i] = new TableRow(SquadMgmt.this);
             //params.setMargins(0, 5, 8, 8);
            row[i].setLayoutParams(params);
            col[i] = new TextView(SquadMgmt.this);
            col[i].setBackgroundResource(R.drawable.trape2);
            col[i].setText(pl);
            col[i].setHeight(60);
            col[i].setWidth(510);
            col[i].setGravity(Gravity.CENTER);
            col[i].setBackgroundResource(R.drawable.trape2);
            col[i].setTextSize(15);
            col[i].setElevation(22);
            col[i].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            col[i].setId(k);
            setOnClicktoButton(col[i]);
            row[i].addView(col[i]);
            table.addView(row[i]);
            Log.d("k nd w",""+k+" "+w);
            //Log.d("JJ:", "" + jj);
            k++;
            i++;
            //jj++;
            // row[k].addView(spacerColumn, new TableRow.LayoutParams(12, 12));
        }
    }

    void setOnClicktoButton(final TextView pd) {
        int i, l = 0;
        final int k;
        pd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("THe value in res::", "" + res[0] + " " + res[1]);
                if (pd.getId() == res[0]) {
                    res[0] = 0;
                    pd.setBackgroundResource(R.drawable.trape2);
                } else {
                    pd.setBackgroundResource(R.color.greenTransparent);
                    if (res[0] == 0) {
                        res[0] = pd.getId();
                        //pd.setBackgroundResource(R.drawable.trape2);
                        updateStats((String)pd.getText(),tables[fmt]);
                    } else if (res[1] == 0) {
                        //pd.setBackgroundResource(R.color.greenTransparent);
                        res[1] = pd.getId();
                        updateStats((String)pd.getText(),tables[fmt]);
                    } else {
                        TextView pd1 = ((TextView) findViewById(res[1]));
                        pd1.setBackgroundResource(R.drawable.trape);
                        res[1] = pd.getId();
                        updateStats((String)pd.getText(),tables[fmt]);
                        //stats.setText()
                    }
                }
                //pd.setBackgroundResource(R.color.goldy);
            }
        });
        //add_desc.setOnItemSelectedListener();
    }
    public void updateStats(String pName,String tabname)
    {
        ImageView im = (ImageView) findViewById(R.id.foto);
        PlayersFaces pf = new PlayersFaces();
        String conv_pname = pf.convertPlayer(pName);
        String filepath = Environment.getExternalStorageDirectory() + "/" + "HomeCric/Players/" + conv_pname + ".png";
        Bitmap bitmap = BitmapFactory.decodeFile(filepath);
        im.setImageBitmap(bitmap);
        if(tabname.equals("players_test"))
        {
            updateStatsTest(pName);
        }
        else {
            prev = (Button) findViewById(R.id.vt0);
            next = (Button) findViewById(R.id.vt01);
            vt = (TextView) findViewById(R.id.vt1);
            vt.setText("BATTING");
            updateBowlStats(prev, pName, tabname);
            updateBowlStats(next, pName, tabname);
            match = ((TextView) findViewById(R.id.p31));
            match.setText("" + db1.getStatsIntl("Matches", pName, tabname));
            //runs,fifty,fuur,hs,srate,avg;
            runs = ((TextView) findViewById(R.id.p32));
            runs.setText("" + db1.getStatsIntl("Runs", pName, tabname));
            avg = ((TextView) findViewById(R.id.p33));
            t2 = ((TextView) findViewById(R.id.p40));
            t3 = ((TextView) findViewById(R.id.p50));
            t4 = ((TextView) findViewById(R.id.p60));
            t5 = ((TextView) findViewById(R.id.p70));
            t6 = ((TextView) findViewById(R.id.p80));
            t2.setText("Runs");
            t3.setText("Average");
            t4.setText("30/50/100");
            t5.setText("Highest");
            t6.setText("StrikeRate");
            int m = db1.getStatsIntl("Matches", pName, tabname);
            int r = db1.getStatsIntl("Runs", pName, tabname);
            int no = db1.getStatsIntl("nouts", pName, tabname);
            float averag = (float) r / (m - no);
            avg.setText("" + averag);
            fifty = ((TextView) findViewById(R.id.p34));
            int rew[] = new int[3];
            rew = db1.getMatchRew(pName, tabname);
            fifty.setText("" + rew[0] + "/" + rew[1] + "/" + rew[2]);
            hs = ((TextView) findViewById(R.id.p35));
            hs.setText("" + db1.getStatsIntl("Highest", pName, tabname));
     /*fuur = ((TextView) findViewById(R.id.p36));
     int fsix[]=new int[2];
     fsix=db1.getMatchFsix(pName);
     fuur.setText(""+fsix[0]+"/"+fsix[1]);*/
            srate = ((TextView) findViewById(R.id.p36));
            int bls = db1.getStatsIntl("Balls", pName, tabname);
            float strRate = (float) r / bls;
            strRate = strRate * 100;
            srate.setText("" + strRate);
        }
    }
    public void updateBowlStats(final Button b, final String pName,final String tabname)
    {
        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(z==0)
                {
                    z=1;
                    vt = ((TextView) findViewById(R.id.vt1));
                    vt.setText("BOWLING");
                    t2.setText("Wickets");t3.setText("4WI");t4.setText("5WI");t5.setText("Best");t6.setText(" ");
                    String str[]=db1.getBowlStatsPlayer(pName,tabname);
                    runs.setText(str[0]);
                    srate.setText("");
                    fifty.setText(str[2]);
                    hs.setText(str[3]);
                    avg.setText(str[1]);
                }
                else
                {
                    z=0;
                    TextView vt = ((TextView) findViewById(R.id.vt1));
                    vt.setText("BATTING");
                    updateStats(pName,tabname);
                }
            }});
    }
    public void updateStatsTest(String pName)
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
        prev=(Button)findViewById(R.id.vt0);next=(Button)findViewById(R.id.vt01);
        updateBowlStatsTest(prev,pName);updateBowlStatsTest(next,pName);
    }
    public void updateBowlStatsTest(final Button b, final String pName)
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
                    updateStatsTest(pName);
                }
            }});
    }
    void setClickonSwap() {
        Button bt = (Button) findViewById(R.id.btswap);
        Log.d("THe value in res::", "" + res[0] + " " + res[1]);
        pl1 = (TextView) findViewById(res[0]);
        pl2 = (TextView) findViewById(res[1]);

        //add_desc.setOnItemSelectedListener();
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (res[0] == 0 && res[1] == 0) {

                } else {
                    Log.d("THvalueres when clickd:", "" + res[0] + " " + res[1]);
                    pl1 = (TextView) findViewById(res[0]);
                    pl2 = (TextView) findViewById(res[1]);
                    db1.updatePlayerID((String) pl1.getText(), (String) pl2.getText(),tables[fmt]);
                    //db1.updatePlayerIDTest((String) pl1.getText(), (String) pl2.getText());
                    String tmp = (String) pl1.getText();
                    pl1.setText(pl2.getText());
                    pl2.setText(tmp);
                    pl1.setBackgroundResource(0);
                    pl2.setBackgroundResource(0);
                    res[0] = 0;
                    res[1] = 0;
                }
            }
        });
    }
    public void setClickonAddMore() {
        Button bt = (Button) findViewById(R.id.btnadd);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Bundle args = new Bundle();
                //args.putParcelable("context", getBaseContext());
                Log.d("BUTTON","CLICKED");
                FilePicker fragment = new FilePicker();
                Bundle bundle = new Bundle();
                bundle.putBoolean("notAlertDialog", true);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                fragment.show(transaction,"dialog");
            }
    });
    }

    public void AddToTeam(String a,String b,String c) {
                    db1.addPlayer(a,b,c);
                    Toast.makeText(getApplicationContext(),a+" is ADDED",Toast.LENGTH_SHORT).show();
                    //pname.setText("");
                }
}

