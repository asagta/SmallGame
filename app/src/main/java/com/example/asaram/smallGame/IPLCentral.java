package com.example.asaram.smallGame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class IPLCentral extends AppCompatActivity {
    DatabaseHandler db1;
    public static TextView tv,mainHead;
    public static Button b5,prev,next,b26;
    public static String tour_flag="N";
    static int z;
    //public List<String> teams;
    private Spinner add_desc, add_desc2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.round_robin);
        DatabaseHelper dbHelper = new DatabaseHelper(this, getFilesDir().getAbsolutePath());
        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.d("ADD_EXPENSES:", e.getMessage());
        }
        db1 = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
        showFixtures();
        showStandings();
        int i= db1.getTourMatchSno();
        if(i==60)
        {
            Intent intent = new Intent(getBaseContext(), IPLWinner.class);
            intent.putExtra("winner", db1.giveIPLWinner());
            startActivity(intent);
        }
else {
            String[] fix = db1.getIPLFixtures(i);
          Log.d("FIXERS", fix[0].toLowerCase() + "_logos");
            int drid = getResources().getIdentifier(db1.getFranchiseId(fix[0]).toLowerCase() + "_logos", "drawable", getPackageName());
            int drid2 = getResources().getIdentifier(db1.getFranchiseId(fix[1]).toLowerCase() + "_logos", "drawable", getPackageName());
            ImageView img = (ImageView) findViewById(R.id.imageView5);
            img.setImageResource(drid);
            ImageView img2 = (ImageView) findViewById(R.id.imageView7);
            img2.setImageResource(drid2);
            startMatch();
            setOnClicktoButton();
        }
        //setOnClicktoResume();
    }

    public void setOnClicktoButton() {
        b26=(Button)findViewById(R.id.button26);
        b26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IPLCentral.this, IPLStats.class));
            }
        });
    }
    public void loadSpinnerData() {
        // database handler
        // Spinner Drop down elements
        List<String> teams = db1.getAllTeams();
        int i = 0,j=1;
        while (i < 8) {
            String tvID = "t" + i;
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            tv = ((TextView) findViewById(resID));
            tv.setText(""+db1.getTourTeamName(i));
            i++;
        }
        i=10;int sno=1;
        while(i<16)
        {
            String tvID = "tv" + i;
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            tv = ((TextView) findViewById(resID));
            tv.setText(""+db1.getTourMatchWinner(sno));
            sno=sno+1;
            i++;
        }
        //db1.addtourMatches("T1",teams.get(tms[0]).toString(),1);
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
                    setKnockOutMatches();
                    mainHead.setText("Play Offs");
                } else {
                    z = 0;
                    showFixturesOnly();
                    mainHead.setText("Fixtures");
                }

            }
        });
    }
    public void showFixturesOnly()
    {int i=0,j=0;
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(160,160);
        TableLayout table = (TableLayout) findViewById(R.id.tableLayout1);
        table.removeAllViews();
        TableRow[] row = new TableRow[56];
        TextView[] col = new TextView[56];
        String fix[]=new String[3];
        while (i < 56)
        {
            fix=db1.getIPLFixtures(i);
            row[i] = new TableRow(IPLCentral.this);
            params.setMargins(10, 27, 10, 10);
            row[i].setLayoutParams(params);
            while(j<3)
            {
                col[j] = new TextView(IPLCentral.this);
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
    public void setKnockOutMatches()
    {int i=56,j=0;
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(160,160);
        TableLayout table = (TableLayout) findViewById(R.id.tableLayout1);
        table.removeAllViews();
        TableRow[] row = new TableRow[4];
        TextView[] col = new TextView[4];
        String fix[]=new String[4]; int i2=0;
        while (i < 60)
        {
            fix=db1.getIPLFixtures(i);
            row[i2] = new TableRow(IPLCentral.this);
            params.setMargins(10, 27, 10, 10);
            row[i2].setLayoutParams(params);
            while(j<3)
            {
                col[j] = new TextView(IPLCentral.this);
                col[j].setText(fix[j]);
                col[j].setHeight(140);
                col[j].setWidth(550);
                col[j].setGravity(Gravity.CENTER);
                col[j].setTextSize(14);
                col[j].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                row[i2].addView(col[j]);
                j++;
            }
            j=0;
            table.addView(row[i2]);
            i++;i2++;
            // row[k].addView(spacerColumn, new TableRow.LayoutParams(12, 12));

        }
    }
    public void startMatch()
    {
        b5=(Button) findViewById(R.id.button22);
        //add_desc.setOnItemSelectedListener();
        b5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d("CLicked the button: ", "Yes its not null");

                String[] values=new String[3];
                db1.setTourMatchSno(1);//increment the match Sno by 1
                values=db1.getIPLFixtures(db1.getTourMatchSno()-1);
                Log.d("CURRENT-MATCH::",values[0]+" "+values[1]);
                tour_flag="I";
                //db1.insertCurrMatch(value1,value2,"N",maxovers);
                db1.insertCurrMatch(values[0],values[1],tour_flag,20);
                startActivity(new Intent(IPLCentral.this, PlayersSelect.class));
            }});

        //b1.setText("GoTHere");

    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(IPLCentral.this, IPLCentral.class));
        finish();

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
                    data = db1.getIPLStandings(i);
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
}


