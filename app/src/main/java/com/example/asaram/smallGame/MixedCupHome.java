package com.example.asaram.smallGame;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.IOException;

public class MixedCupHome extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;
    public static Button b5,prev,next,b26;
    public static TextView tv,mainHead;
    static int match_over,z,za;
    private TextView tv28;
    DatabaseHandler db1;
    public static String tour_flag="N";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mixed_cup);
      //  getSupportActionBar().hide();
        // init constraintLayout
        constraintLayout = (ConstraintLayout) findViewById(R.id.constr);
        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        DatabaseHelper dbHelper = new DatabaseHelper(this, getFilesDir().getAbsolutePath());
        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.d("ADD_EXPENSES:", e.getMessage());
        }
        db1 = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
        int i= db1.getTourMatchSno();
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "cricket-bold.ttf");
        tv28=(TextView)findViewById(R.id.textView28);
        tv28.setTypeface(custom_font);
        if(i>44 && i<60) tv28.setText("Super6 : ODI");
        else if(i==60) tv28.setText("SemiFinal 1");
        else if(i==61) tv28.setText("SemiFinal 2");
        else if(i==62) tv28.setText("THE FINAL");
        else if(i==63||i==64) {
            Intent intent=new Intent(this,MixedCupWinner.class);
            startActivity(intent);
        }
        else tv28.setText("Super10 : T20 Match");
        if(i==64||i==63)
        {

        }
        else {
            String[] fix = db1.getMCFixtures(i);
            Log.d("FIXERS", fix[0].toLowerCase() + "_logos");
            int drid = getResources().getIdentifier(db1.getTeamId(fix[0]).toLowerCase() + "_logos", "drawable", getPackageName());
            int drid2 = getResources().getIdentifier(db1.getTeamId(fix[1]).toLowerCase() + "_logos", "drawable", getPackageName());
            ImageView img = (ImageView) findViewById(R.id.imageView8);
            img.setImageResource(drid);
            ImageView img2 = (ImageView) findViewById(R.id.imageView9);
            img2.setImageResource(drid2);
            startMatch();
            showFixtures();
            showStandings();
            showStats();
        }
    }
    public void showFixtures()
    {
        b5=(Button) findViewById(R.id.button38);
        //add_desc.setOnItemSelectedListener();
        b5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d("CLicked the button: ", "Yes its not null");
                setContentView(R.layout.rr_fixtures);
                showFixturesOnly();
                prev=(Button)findViewById(R.id.vt0);
                next=(Button)findViewById(R.id.vt01);
                mainHead=(TextView)findViewById(R.id.vt1);
                mainHead.setText("Super 10");
                setClickOnNext(prev,"min");
                setClickOnNext(next,"add");
            }});

        //b1.setText("GoTHere");

    }
    public void setClickOnNext(Button t, final String what) {
        mainHead=(TextView)findViewById(R.id.vt1);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(what.equalsIgnoreCase("add"))
                  z=z+1;
                else
                  z=z-1;
                if(z==3)z=0;
                if(z==-1||z==2)
                {
                    z=2;
                    //update knockouts
                    setKnockOutMatches();
                    mainHead.setText("Knock Outs");
                }
                else if (z == 1) {
                    setRound2Matches();
                    mainHead.setText("Super 6");
                }
                else {
                    showFixturesOnly();
                    mainHead.setText("Super 10");
                }

            }
        });
    }
    public void showFixturesOnly()
    {int i=0,j=0;
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(160,160);
        TableLayout table = (TableLayout) findViewById(R.id.tableLayout1);
        table.removeAllViews();
        TableRow[] row = new TableRow[45];
        TextView[] col = new TextView[45];
        String fix[]=new String[3];
        while (i < 45)
        {
            fix=db1.getMCFixtures(i);
            row[i] = new TableRow(MixedCupHome.this);
            params.setMargins(10, 27, 10, 10);
            row[i].setLayoutParams(params);
            while(j<3)
            {
                col[j] = new TextView(MixedCupHome.this);
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
    {int i=60,j=0;
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(160,160);
        TableLayout table = (TableLayout) findViewById(R.id.tableLayout1);
        table.removeAllViews();
        TableRow[] row = new TableRow[3];
        TextView[] col = new TextView[3];
        String fix[]=new String[3]; int i2=0;
        while (i < 63)
        {
            fix=db1.getMCFixtures(i);
            row[i2] = new TableRow(MixedCupHome.this);
            params.setMargins(10, 27, 10, 10);
            row[i2].setLayoutParams(params);
            while(j<3)
            {
                col[j] = new TextView(MixedCupHome.this);
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

    public void setRound2Matches()
    {int i=45,j=0;
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(160,160);
        TableLayout table = (TableLayout) findViewById(R.id.tableLayout1);
        table.removeAllViews();
        TableRow[] row = new TableRow[15];
        TextView[] col = new TextView[15];
        String fix[]=new String[3]; int i2=0;
        while (i < 60)
        {
            fix=db1.getMCFixtures(i);
            row[i2] = new TableRow(MixedCupHome.this);
            params.setMargins(10, 27, 10, 10);
            row[i2].setLayoutParams(params);
            while(j<3)
            {
                col[j] = new TextView(MixedCupHome.this);
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
        b5=(Button) findViewById(R.id.button37);
        //add_desc.setOnItemSelectedListener();
        b5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("CLicked the button: ", "Yes its not null");
                String[] values=new String[3];
                db1.setTourMatchSno(1);//increment the match Sno by 1
                values=db1.getMCFixtures(db1.getTourMatchSno()-1);
                Log.d("CURRENT-MATCH::",values[0]+" "+values[1]);
                tour_flag="M";
                //db1.insertCurrMatch(value1,value2,"N",maxovers);
                if(db1.getTourMatchSno()>45)
                    match_over=50;
                else
                    match_over=20;
                db1.insertCurrMatch(values[0],values[1],tour_flag,match_over);
                startActivity(new Intent(MixedCupHome.this, PlayersSelect.class));
            }});

        //b1.setText("GoTHere");

    }

    public void showStats() {
        b26=(Button)findViewById(R.id.button40);
        b26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MixedCupHome.this, MixedCupStats.class));
            }
        });
    }

    public void showStandings()
    {
        b5=(Button) findViewById(R.id.button39);
        b5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("CLicked the button: ", "Yes its not null");
                setContentView(R.layout.mc_standings);
                showStandingsR1();
                prev=(Button)findViewById(R.id.vt0);
                next=(Button)findViewById(R.id.vt01);
                setOnNext(prev,"min");
                setOnNext(next,"add");
            }});
    }

    public void setOnNext(Button t, final String what) {
        mainHead=(TextView)findViewById(R.id.vt1);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(what.equalsIgnoreCase("add"))
                    za=za+1;
                else
                    za=za-1;
                if(za==2)za=0;
                if(za==-1||za==1)
                {
                    za=1;
                    showStandingsR2();
                }
                else {
                    showStandingsR1();
                }

            }
        });
 }

    public void showStandingsR2()
    {
        TextView tv,tv1,tv2;
        tv1=(TextView)findViewById(R.id.vt1);
        tv1.setText("Super 6");
        // teamName.setText(match[0]);
        int j=3,k=1,kk=0,ii=1,i=0;
        String data[]=new String[6];
        while(i<6) {
            if(k==7)
            {j=j+1;k=1;kk=0;ii=ii+1;i=i+1;}
            data = db1.getMCStandings(i,"mc_standings_r2");
            String tvID = "vt" + j + ""+k;
            Log.d("PLAYERS TEXT::",tvID);
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            tv = ((TextView)findViewById(resID));
            tv.setText(""+data[kk]);
            Log.d("DATA at::",tvID+data[kk]);
            k=k+1;
            kk=kk+1;
            if(j==12&&k==7)break;
        }
        while(i<10) {
            if(k==7)
            {j=j+1;k=1;kk=0;ii=ii+1;i=i+1;}
            String tvID = "vt" + j + ""+k;
            Log.d("PLAYERS TEXT::",tvID);
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            tv = ((TextView)findViewById(resID));
            tv.setText("");
            //Log.d("DATA at::",tvID+data[kk]);
            k=k+1;
            kk=kk+1;
            if(j==12&&k==7)break;
        }
    }

public void showStandingsR1()
{
    TextView tv,tv1,tv2;
    tv1=(TextView)findViewById(R.id.vt1);
    tv1.setText("Super 10");
    // teamName.setText(match[0]);
    int j=3,k=1,kk=0,ii=1,i=0;
    String data[]=new String[6];
    while(i<10) {
        if(k==7)
        {j=j+1;k=1;kk=0;ii=ii+1;i=i+1;}
        data = db1.getMCStandings(i,"mc_standings_r1");
        String tvID = "vt" + j + ""+k;
        Log.d("PLAYERS TEXT::",tvID);
        int resID = getResources().getIdentifier(tvID, "id", getPackageName());

        tv = ((TextView)findViewById(resID));
        tv.setText(""+data[kk]);
        Log.d("DATA at::",tvID+data[kk]);
        k=k+1;
        kk=kk+1;
        if(j==12&&k==7)break;
    }
}
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(MixedCupHome.this, MixedCupHome.class));
        finish();

    }
    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            // start the animation
            animationDrawable.start();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            // stop the animation
            animationDrawable.stop();
        }
    }
}
