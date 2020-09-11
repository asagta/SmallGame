package com.example.asaram.smallGame;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

public class MainActivity_Test extends AppCompatActivity {
    DatabaseHandler db1;
    private Button zero,one,two,three,four,five,six,ib,prevTeam,nextTeam;
    private TextView team,bat,bowl,score,result,overs,bat1,bat2,bat1_run,bat2_run,teamName,bowler,b_overs,b_runs,b_wick;
    private TextView thisover,thisover0,bowl_name,bowl_fig,float_fig;
    public WindowManager.LayoutParams layoutParams;
    public static Dialog dialog;
    static String[] ecoBowlrs;
    static String eq="",winner,looser,ftext,thover="",ftext2="";
    static double nrr,nrr2;
    static int[][] rball;static String match[];
    static int cbowler,bowl_wick,bowl_runs,bowl_dots,bowl_over,bowl_bowls;
    static int decl,b4,b6,r4,r6,nextScore,drid,drid2,freq6,freq1,freq5,freq0,freq3,you;
    public static int day_over,day;
    static int ref,i7,i8,rover,b,r,row,col,strike,runs,runsLeft,maxOvers,maxWick,n,run,wick,flagBat,afb,teem1,teem2,opp1,opp2,over,ball,ind2,over2,tballs,cfb,cfr,chb,chr;
    static int target,ttm1,topp,p_mode,p_freq,p_taken,blimit,rlimit,lb,lr,ind,aus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration configuration = getResources().getConfiguration();
        int screenWidthDp = configuration.screenWidthDp; //The current width of the available screen space, in dp units, corresponding to screen width resource qualifier.
        int sm = configuration.smallestScreenWidthDp;
        Log.d("CURR & SMALL WIDTH IS",""+screenWidthDp+" "+sm);
        if(screenWidthDp>720)
          setContentView(R.layout.main_activity2);
        else
            setContentView(R.layout.activity_main);
        eq="";
        rover=0;lb=3;lr=3;
        bat=(TextView)findViewById(R.id.textView22);
        bowl=(TextView)findViewById(R.id.textView23);
        bowl_name=(TextView)findViewById(R.id.tv2);
        bowl_fig=(TextView)findViewById(R.id.tv3);
        float_fig=(TextView)findViewById(R.id.ffig);
        score=(TextView)findViewById(R.id.textView16);
        team=(TextView)findViewById(R.id.tm);
        ib=(Button)findViewById(R.id.button);
        zero=(Button)findViewById(R.id.b0);
        one=(Button)findViewById(R.id.b1);
        two=(Button)findViewById(R.id.b2);
        three=(Button)findViewById(R.id.b3);
        four=(Button)findViewById(R.id.b4);
        five=(Button)findViewById(R.id.b5);
        six=(Button)findViewById(R.id.b6);
        thisover=(TextView)findViewById(R.id.tover);
        result=(TextView)findViewById(R.id.textView18);
        overs=(TextView)findViewById(R.id.textView17);
        bat1=(TextView)findViewById(R.id.bat1);bat1_run=(TextView)findViewById(R.id.b1r);
        bat2=(TextView)findViewById(R.id.bat2);bat2_run=(TextView)findViewById(R.id.b2r);
        bowler=(TextView)findViewById(R.id.vtt1);
        b_overs=(TextView)findViewById(R.id.vtt2);
        b_runs=(TextView)findViewById(R.id.vtt3);
        b_wick=(TextView)findViewById(R.id.vtt4);
        dialog = new Dialog(MainActivity_Test.this);
        dialog.setContentView(R.layout.second_batting);
        teamName=(TextView)dialog.findViewById(R.id.vt1);
        prevTeam=(Button)dialog.findViewById(R.id.vt0);
        nextTeam=(Button)dialog.findViewById(R.id.vt01);
        match=new String[2];
        setOnClickListeneronText(nextTeam);
        setOnClickListeneronText(prevTeam);
        setOnClickListeneronButtons(zero,0);
        setOnClickListeneronButtons(one,1);
        setOnClickListeneronButtons(two,2);
        setOnClickListeneronButtons(three,3);
        setOnClickListeneronButtons(four,4);
        setOnClickListeneronButtons(five,5);
        setOnClickListeneronButtons(six,6);
        ib.setText("DECLARE");
        declareInnings(ib);
        DatabaseHelper dbHelper = new DatabaseHelper(this, getFilesDir().getAbsolutePath());
        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.d("ADD_EXPENSES:", e.getMessage());
        }
        db1 = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
        db1.getTeamName();
        match[0]=db1.getCurrTeamName(0);match[1]=db1.getCurrTeamName(1);
        //db1.initializeScores(match[0],match[1]);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "led_board-7.ttf");
        bat.setTypeface(custom_font);
        bowl.setTypeface(custom_font);
        wick=db1.getCurrDataTest("Wickets");
        over=db1.getCurrDataTest("Overs");
        ball=db1.getCurrDataTest("Balls");
        teem1=db1.getCurrDataTest("teem_1");teem2=db1.getCurrDataTest("teem_2");
        opp1=db1.getCurrDataTest("opp_1");opp2=db1.getCurrDataTest("opp_2");
        over2=db1.getCurrDataTest("Over2");row=db1.getCurrDataTest("row");
        col=db1.getCurrDataTest("col");strike=db1.getCurrDataTest("Strike");
        b=db1.getCurrDataTest("batter");r=db1.getCurrDataTest("runner");
        day=db1.getCurrDataTest("day");you=db1.getCurrDataTest("you");
        Log.d("YOU VARIABLE",""+you);
        layoutParams = new WindowManager.LayoutParams();
        overs.setText(""+over+"."+ball);
        runs=db1.getCurrDataTest("runs");
        maxOvers=db1.getCurrDataTest("maxOvers");maxWick=10;
        flagBat=db1.getCurrDataTest("flagBat");tballs=db1.getCurrDataTest("tballs");
        team.setText(""+match[you]);
        winner="";
        rball=new int[2][2];
        rball[0][0]=db1.getCurrDataTest("batter_run");rball[0][1]=db1.getCurrDataTest("batter_ball");
        rball[1][0]=db1.getCurrDataTest("runner_run");rball[1][1]=db1.getCurrDataTest("runner_ball");
        nextScore=db1.getCurrDataTest("nextScore");
        b4=db1.getCurrDataTest("bat_four");b6=db1.getCurrDataTest("bat_six");
        r4=db1.getCurrDataTest("run_four");r6=db1.getCurrDataTest("run_six");
        //runsLeft=db1.getCurrData("runsLeft");
        score.setText(""+runs+"-"+wick);
         drid=getResources().getIdentifier(match[you].toLowerCase(), "drawable", getPackageName());
        if(you==0)
            drid2=getResources().getIdentifier(match[you+1].toLowerCase(), "drawable", getPackageName());
        else
            drid2=getResources().getIdentifier(match[you-1].toLowerCase(), "drawable", getPackageName());
        blimit=db1.getSixLimitTest(match[you],b);rlimit=db1.getSixLimitTest(match[you],r);
        if(strike==0) {
            bat1.setText(db1.getCurrTestPlayerName(match[you], b));
            bat1_run.setText(""+rball[row][col]+"("+rball[row][col + 1]+")*");
            bat2.setText(db1.getCurrTestPlayerName(match[you], r)) ;
            bat2_run.setText(""+rball[row+1][col]+"("+rball[row+1][col + 1]+")");
        }
        else
        {
            bat1.setText(db1.getCurrTestPlayerName(match[you], b));
            bat1_run.setText(""+rball[row][col]+"("+rball[row][col + 1]+")");
            bat2.setText(db1.getCurrTestPlayerName(match[you], r)) ;
            bat2_run.setText(""+rball[row+1][col]+"("+rball[row+1][col + 1]+")*");
        }
        if(you==0){afb=1;
            bowler.setText(db1.getBowlerTest(match[you+1],cbowler));
            bowl_name.setText(db1.getBowlerTest(match[you+1],cbowler));
        }
        else{afb=0;
            bowler.setText(db1.getBowlerTest(match[you-1],cbowler));
            bowl_name.setText(db1.getBowlerTest(match[you-1],cbowler));
        }
        ttm1=teem1+teem2;topp=opp1+opp2;
        if(teem1>0&&opp1>0)
           flagBat=1;
        ftext="Right Now : 0";
        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(15000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!
                              i7=i7+1;i8=i8+1;
                                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_anim);
                                animation.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.blblue));
                                float_fig.startAnimation(animation);
                              if(i7==4) i7=1;
                                if(i8==3) i8=1;
                              float_fig.setText(getFloatText(i7));
                                thisover.setText(getFloatThisOverText(i8));
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        thread.start();

    }

    String getFloatText(int flag)
    {

       if(i7==1)
       {
           double f;
           if(flagBat==0) {
               ftext = "1st Innings";
           }
           else{
               if(opp2!=0) {
                   target = (ttm1 - topp) + 1;
                   if(target<0)
                       target = (topp - ttm1) + 1;
                   ftext = "TARGET: " + target;
               }else
                   ftext = "2nd Innings";
           }
       }
       else
       {ftext="Best Test Experience";}
     return ftext;
    }
    String getFloatThisOverText(int flag)
    {

        if(i8==2)
        {
            ftext2="SETTLE THE PLAYER";
        }
        else
        {
            ftext2=thover;
        }
        return ftext2;
    }
    void updateScoreCard(int s)
    {
         Random rand = new Random();
         if (over < 31)
             n = rand.nextInt(7) + 0;
         else
             n = rand.nextInt(8) + 0;
         bat.setText("" + s);
         bowl.setText("" + n);
         if(s==5)
             freq5++;
        if(s==6)
            freq6++;
        if(s==0)
            freq0++;
        if(s==1)
            freq1++;
        if(s==3)
            freq3++;
         if(freq5==2)
             disableThat(5);
        if(freq6==2)
            disableThat(6);
        if(freq1==2)disableThat(1);
        if(freq0==4)
            disableThat(0);
        if(freq3==3)
            disableThat(3);
         if (s == n && s != 5 && s != 3 && s != 0) {
             //CelebrationAnimations ca=new CelebrationAnimations();
             wick = wick + 1;
             thover = thover + " " + "W";
             thisover.setText(getFloatThisOverText(i8));
             db1.updateCurrGameTest("Wickets", wick);
             if (flagBat == 0) {
                 bowl_wick = db1.getBowlStatsTest("bwick", (String) bowl_name.getText()) + 1;
                 db1.updateBowlStatsTest("bwick", (String) bowl_name.getText(), 1);
             } else {
                 bowl_wick = db1.getBowlStatsTest("bwick_2", (String) bowl_name.getText()) + 1;
                 db1.updateBowlStatsTest("bwick_2", (String) bowl_name.getText(), 1);
             }
             b_wick.setText("" + bowl_wick);//For saving the gaem
             //result.setText("OUT");
             score.setText("" + runs + "-" + wick);
             float_fig.setText(getFloatText(i7));
             if(flagBat==0)
               db1.setScoresTest(match[0], (String) score.getText(),1);
             else
                 db1.setScoresTest(match[0], (String) score.getText(),2);
             if (strike == 0) {
                 rball[row][col + 1] += 1;
                 db1.updateCurrGameTest("batter_ball", rball[row][col + 1]);
                 if (flagBat == 0) {
                     db1.updateCurrPlayerTest("Balls", db1.getCurrTestPlayerName(match[you], b), rball[row][col + 1]);
                     db1.updateCurrPlayerTest("Runs", db1.getCurrTestPlayerName(match[you], b), rball[row][col]);
                     db1.updateCurrPlayerTest("out", db1.getCurrTestPlayerName(match[you], b), 1);
                 } else {
                     db1.updateCurrPlayerTest("Balls_2", db1.getCurrTestPlayerName(match[you], b), rball[row][col + 1]);
                     db1.updateCurrPlayerTest("Runs_2", db1.getCurrTestPlayerName(match[you], b), rball[row][col]);
                     db1.updateCurrPlayerTest("out_2", db1.getCurrTestPlayerName(match[you], b), 1);
                 }
                 CelebrationAnimations.showBatOutDialog(MainActivity_Test.this, db1.getCurrTestPlayerName(match[you], b), rball[row][col], rball[row][col + 1], b6, b4);
                 rball[row][col] = 0;
                 cfb = 0;
                 chb = 0;
                 db1.updateCurrGameTest("batter_run", rball[row][col]);
                 rball[row][col + 1] = 0;
                 db1.updateCurrGameTest("batter_ball", rball[row][col + 1]);
                 b4 = 0;
                 db1.updateCurrGameTest("bat_four", b4);
                 b6 = 0;
                 db1.updateCurrGameTest("bat_six", b6);
                 b = Math.max(b, r) + 1;
                 blimit = db1.getSixLimitTest(match[you], b);
                 lb = 3;
                 db1.updateCurrGame("batter", b);
             } else {
                 rball[row + 1][col + 1] += 1;
                 db1.updateCurrGameTest("runner_ball", rball[row + 1][col + 1]);
                 if (flagBat == 0) {
                     db1.updateCurrPlayerTest("Balls", db1.getCurrTestPlayerName(match[you], r), rball[row + 1][col + 1]);
                     db1.updateCurrPlayerTest("Runs", db1.getCurrTestPlayerName(match[you], r), rball[row + 1][col]);
                     db1.updateCurrPlayerTest("out", db1.getCurrTestPlayerName(match[you], r), 1);
                 } else {
                     db1.updateCurrPlayerTest("Balls_2", db1.getCurrTestPlayerName(match[you], r), rball[row + 1][col + 1]);
                     db1.updateCurrPlayerTest("Runs_2", db1.getCurrTestPlayerName(match[you], r), rball[row + 1][col]);
                     db1.updateCurrPlayerTest("out_2", db1.getCurrTestPlayerName(match[you], r), 1);
                 }
                 CelebrationAnimations.showBatOutDialog(MainActivity_Test.this, db1.getCurrTestPlayerName(match[you], r), rball[row + 1][col], rball[row + 1][col + 1], r6, r4);
                 rball[row + 1][col] = 0;
                 cfr = 0;
                 chr = 0;
                 db1.updateCurrGameTest("runner_run", rball[row + 1][col]);
                 rball[row + 1][col + 1] = 0;
                 db1.updateCurrGameTest("runner_ball", rball[row + 1][col + 1]);
                 r4 = 0;
                 db1.updateCurrGameTest("run_four", r4);
                 r6 = 0;
                 db1.updateCurrGameTest("run_six", r6);
                 r = Math.max(b, r) + 1;
                 rlimit = db1.getSixLimitTest(match[you], r);
                 lr = 3;
                 db1.updateCurrGameTest("Runner", r);
             }
             run = 0;
             db1.updateCurrGameTest("run", run);
         } else if (s == 0 || n == 0) {
             runs = runs + 0;
             thover = thover + " " + "0";
             thisover.setText(getFloatThisOverText(i8));
             float_fig.setText(getFloatText(i7));
             //CelebrationAnimations.ShowThat(MainActivity.this);
             db1.updateCurrGameTest("runs", runs);
             run = 0;
             db1.updateCurrGameTest("run", run);
             //updating the dots of the bowler
             bowl_dots = db1.getBowlStatsTest("bmaiden", (String) bowl_name.getText()) + 1;
             //db1.updateBowlStats("bmaiden", (String) bowl_name.getText(), 1);
             if (strike == 0) {
                 rball[row][col + 1] += 1;
                 db1.updateCurrGameTest("batter_ball", rball[row][col + 1]);
                 if (flagBat == 0)
                     db1.updateCurrPlayerTest("Balls", db1.getCurrTestPlayerName(match[you], b), rball[row][col + 1]);
                 else
                     db1.updateCurrPlayerTest("Balls_2", db1.getCurrTestPlayerName(match[you], b), rball[row][col + 1]);
             } else {
                 rball[row + 1][col + 1] += 1;
                 db1.updateCurrGameTest("runner_ball", rball[row + 1][col + 1]);
                 if (flagBat == 0)
                     db1.updateCurrPlayerTest("Balls", db1.getCurrTestPlayerName(match[you], r), rball[row + 1][col + 1]);
                 else
                     db1.updateCurrPlayerTest("Balls_2", db1.getCurrTestPlayerName(match[you], r), rball[row + 1][col + 1]);
             }
         } else {
             run = s - n;
             if (n == 7 && s == 2) {
                 run = 4;
                 //thisover.setText(""+thisover.getText()+""+run);
             }
             if (s == 5 && (n == 4 || n == 6 || n == 7)) {
                 run = 1;
                 // thisover.setText(""+thisover.getText()+""+run);
             }
             if (s == 5 && n < 4) {
                 run = 0;
             }
             if (s == 1 && n == 5) {
                 run = 4;
             }
             if (run == 5 || run == 6) run = 6;
             if (run == -1 ) run = 0;
             if (run == -3|| run == -2) run = 1;
             if (run < -3) run = 2;
             if (s == 5 && n == 5) run = db1.getConfigValue("TEST_MATCH_EXCESS_5");
             if (s == 3 && n == 3) run = db1.getConfigValue("TEST_MATCH_EXCESS_3");
             //code for limiting sixes
             if (strike == 0 && run >= 5) {
                 if (b6 == blimit) {
                     lb = lb - 1;
                     if (lb < 0) run = 2;
                     else run = 4;
                 }
             } else if (strike == 1 && run >= 5) {
                 if (r6 == rlimit) {
                     lr = lr - 1;
                     if (lr < 0) run = 2;
                     else run = 4;
                 }
             }
             thover = thover + " " + run;
             thisover.setText(getFloatThisOverText(i8));
             db1.updateCurrGameTest("run", run);
             if (run == 5 || run == 6) {
                 run = 6;
                 Log.d("SIXES_STRIKE::", "" + strike);
                 if (strike == 0) {
                     b6 += 1;
                     Log.d("BAT_SIXES::", "" + b6);
                     db1.updateCurrGameTest("bat_six", b6);
                     if (flagBat == 0)
                         db1.updateCurrPlayerTest("6s", db1.getCurrTestPlayerName(match[you], b), b6);
                     else
                         db1.updateCurrPlayerTest("6s_2", db1.getCurrTestPlayerName(match[you], b), b6);
                     CelebrationAnimations.ShowThatSix(MainActivity_Test.this, db1.getCurrTestPlayerName(match[you], b));
                 } else {
                     r6 += 1;
                     Log.d("RUN_SIXES::", "" + r6);
                     db1.updateCurrGameTest("run_six", r6);
                     if (flagBat == 0)
                         db1.updateCurrPlayerTest("6s", db1.getCurrTestPlayerName(match[you], r), r6);
                     else
                         db1.updateCurrPlayerTest("6s_2", db1.getCurrTestPlayerName(match[you], r), r6);
                     CelebrationAnimations.ShowThatSix(MainActivity_Test.this, db1.getCurrTestPlayerName(match[you], r));
                 }
             }
             if (run == 4) {

                 if (strike == 0) {
                     b4 += 1;
                     Log.d("BAT_FOURS::", "" + b4);
                     db1.updateCurrGameTest("bat_four", b4);
                     if (flagBat == 0)
                         db1.updateCurrPlayerTest("4s", db1.getCurrTestPlayerName(match[you], b), b4);
                     else
                         db1.updateCurrPlayerTest("4s_2", db1.getCurrTestPlayerName(match[you], b), b4);
                     CelebrationAnimations.ShowThat(MainActivity_Test.this, db1.getCurrTestPlayerName(match[you], b));
                 } else {
                     r4 += 1;
                     Log.d("RUN_FOURS::", "" + r4);
                     db1.updateCurrGameTest("run_four", r4);
                     if (flagBat == 0)
                         db1.updateCurrPlayerTest("4s", db1.getCurrTestPlayerName(match[you], r), r4);
                     else
                         db1.updateCurrPlayerTest("4s_2", db1.getCurrTestPlayerName(match[you], r), r4);
                     CelebrationAnimations.ShowThat(MainActivity_Test.this, db1.getCurrTestPlayerName(match[you], r));
                 }
             }
             runs = runs + run;
             db1.updateCurrGameTest("runs", runs);
             if (strike == 0) {
                 rball[row][col] += run;
                 db1.updateCurrGameTest("batter_run", rball[row][col]);
                 rball[row][col + 1] += 1;
                 //show fifty menu
                 if (rball[row][col] > 49) {
                     cfb = cfb + 1;
                 }
                 if (rball[row][col] > 99) {
                     chb = chb + 1;
                 }
                 if (cfb == 1) {
                     float strRate = (float) rball[row][col] / rball[row][col + 1];
                     strRate = strRate * 100;
                     int rew[] = new int[3];
                     rew = db1.getMatchRewTest(db1.getCurrTestPlayerName(match[you], b));
                     CelebrationAnimations.ShowThatFifty(MainActivity_Test.this, db1.getCurrTestPlayerName(match[you], b), rew[0], rball[row][col], rball[row][col + 1], b6, b4, strRate);
                 }
                 if (chb == 1) {
                     float strRate = (float) rball[row][col] / rball[row][col + 1];
                     strRate = strRate * 100;
                     int rew[] = new int[3];
                     rew = db1.getMatchRewTest(db1.getCurrTestPlayerName(match[you], b));
                     CelebrationAnimations.ShowThatHundred(MainActivity_Test.this, db1.getCurrTestPlayerName(match[you], b), rew[1], rball[row][col], rball[row][col + 1], b6, b4, strRate);
                 }
                 db1.updateCurrGameTest("batter_ball", rball[row][col + 1]);
                 if (flagBat == 0) {
                     db1.updateCurrPlayerTest("Runs", db1.getCurrTestPlayerName(match[you], b), rball[row][col]);
                     db1.updateCurrPlayerTest("Balls", db1.getCurrTestPlayerName(match[you], b), rball[row][col + 1]);
                 } else {
                     db1.updateCurrPlayerTest("Runs_2", db1.getCurrTestPlayerName(match[you], b), rball[row][col]);
                     db1.updateCurrPlayerTest("Balls_2", db1.getCurrTestPlayerName(match[you], b), rball[row][col + 1]);
                 }
             } else {
                 rball[row + 1][col] += run;
                 db1.updateCurrGameTest("runner_run", rball[row + 1][col]);
                 rball[row + 1][col + 1] += 1;
                 //show that runner fifty
                 if (rball[row + 1][col] > 49) {
                     cfr = cfr + 1;
                 }
                 if (rball[row + 1][col] > 99) {
                     chr = chr + 1;
                 }
                 if (cfr == 1) {
                     float strRate = (float) rball[row + 1][col] / rball[row + 1][col + 1];
                     strRate = strRate * 100;
                     int rew[] = new int[3];
                     rew = db1.getMatchRewTest(db1.getCurrTestPlayerName(match[you], r));
                     CelebrationAnimations.ShowThatFifty(MainActivity_Test.this, db1.getCurrTestPlayerName(match[you], r), rew[0], rball[row + 1][col], rball[row + 1][col + 1], r6, r4, strRate);
                 }
                 if (chr == 1) {
                     float strRate = (float) rball[row + 1][col] / rball[row + 1][col + 1];
                     strRate = strRate * 100;
                     int rew[] = new int[3];
                     rew = db1.getMatchRewTest(db1.getCurrTestPlayerName(match[you], r));
                     CelebrationAnimations.ShowThatHundred(MainActivity_Test.this, db1.getCurrTestPlayerName(match[you], r), rew[1], rball[row + 1][col], rball[row + 1][col + 1], r6, r4, strRate);
                 }
                 db1.updateCurrGameTest("runner_ball", rball[row + 1][col + 1]);
                 if (flagBat == 0) {
                     db1.updateCurrPlayerTest("Runs", db1.getCurrTestPlayerName(match[you], r), rball[row + 1][col]);
                     db1.updateCurrPlayerTest("Balls", db1.getCurrTestPlayerName(match[you], r), rball[row + 1][col + 1]);
                 } else {
                     db1.updateCurrPlayerTest("Runs_2", db1.getCurrTestPlayerName(match[you], r), rball[row + 1][col]);
                     db1.updateCurrPlayerTest("Balls_2", db1.getCurrTestPlayerName(match[you], r), rball[row + 1][col + 1]);
                 }
             }
             if ((run % 2 == 0) && (strike == 0)) {
                 strike = 0;
                 db1.updateCurrGameTest("Strike", strike);
             } else if ((run % 2 == 0) && (strike == 1)) {
                 strike = 1;
                 db1.updateCurrGameTest("Strike", strike);
             } else if ((run % 2 != 0) && (strike == 1)) {
                 strike = 0;
                 db1.updateCurrGameTest("Strike", strike);
             } else if ((run % 2 != 0) && (strike == 0)) {
                 strike = 1;
                 db1.updateCurrGameTest("Strike", strike);
             }
             score.setText("" + runs + "-" + wick);
             //updating floating text
             float_fig.setText(getFloatText(i7));
             if(flagBat==0)
                 db1.setScoresTest(match[0], (String) score.getText(),1);
             else
                 db1.setScoresTest(match[0], (String) score.getText(),2);
             //updating bowler stats
             if (flagBat == 0) {
                 bowl_runs = db1.getBowlStatsTest("bruns", (String) bowl_name.getText()) + run;
                 db1.updateBowlStatsTest("bruns", (String) bowl_name.getText(), run);
             } else {
                 bowl_runs = db1.getBowlStatsTest("bruns_2", (String) bowl_name.getText()) + run;
                 db1.updateBowlStatsTest("bruns_2", (String) bowl_name.getText(), run);
             }
             b_runs.setText("" + bowl_runs);
             rover = rover + run;
             if(runs>=target && target!=0 )
             {
                 disableAll();
                 InningsBreak();
             }
         }
             if (wick == maxWick || decl == 1) {
                 if(decl == 1)
                  score.setText("" + runs+"-"+wick+" decl");
                 else
                  score.setText("" + runs);
                 if(flagBat==0)
                     db1.setOversTest(match[0], (String) overs.getText(),1);
                 else
                     db1.setOversTest(match[0], (String) overs.getText(),2);
                 Toast.makeText(getApplicationContext(), "Innings over", Toast.LENGTH_SHORT).show();
                 disableAll();
                 if(flagBat==0)
                     db1.setScoresTest(match[0], (String) score.getText(),1);
                 else
                     db1.setScoresTest(match[0], (String) score.getText(),2);
                 InningsBreak();
                 disableAll();
                 //Toast.makeText(getApplicationContext(),"Australian Batting Starts",Toast.LENGTH_SHORT).show();
                 you = 1;
                 db1.updateCurrGameTest("you", you);
                 //ind=runs;
                 runs = 0;
                 db1.updateCurrGameTest("runs", runs);
                 wick = 0;over=0;ball = 0;
                 db1.updateCurrGameTest("Balls", ball);
                 db1.updateCurrGameTest("Wickets", wick);
                 //score.setText("" + runs + "-" + wick);
                 float_fig.setText(getFloatText(i7));
                 thover="";

             }
             //code for updating overs
             if (over2 == 1) {
                 over2 = 0;
                 db1.updateCurrGameTest("over2", over2);
             } else {
                 ball = ball + 1;
                 if (flagBat == 0) {
                     bowl_fig.setText("" + db1.getBowlStatsTest("bwick", (String) bowl_name.getText()) + "-" + db1.getBowlStatsTest("bruns", (String) bowl_name.getText()));
                     db1.updateBowlStatsTest("bovers", (String) bowl_name.getText(), 1);
                 } else {
                     bowl_fig.setText("" + db1.getBowlStatsTest("bwick_2", (String) bowl_name.getText()) + "-" + db1.getBowlStatsTest("bruns_2", (String) bowl_name.getText()));
                     db1.updateBowlStatsTest("bovers_2", (String) bowl_name.getText(), 1);
                 }
                 db1.updateCurrGameTest("Balls", ball);
                 bowl_bowls = bowl_bowls + 1;
                 Log.d("BALLS BOWLED:",""+bowl_over+"."+bowl_bowls);
                 if (flagBat == 0)
                     bowl_over = db1.getBowlStatsTest("bovers", (String) bowl_name.getText());
                 else
                     bowl_over = db1.getBowlStatsTest("bovers_2", (String) bowl_name.getText());
                 bowl_over = bowl_over / 6;
                 b_overs.setText("" + bowl_over + "." + bowl_bowls);
                 //tballs=tballs+1;
                 if (ball > 5) {
                     over = over + 1;
                     day_over=day_over+1;
                     //disableAll();
                     db1.updateCurrGameTest("Overs", over);
                     thover = "";
                     thisover.setText("LAST OVER : " + rover);
                     //thisover.setText(getFloatThisOverText(i8));
                     // thisover0.setText("LAST OVER : " + rover);
                     rover = 0;

                     //db1.updateBowlStats("bovers", (String) bowler.getText(), 1);
                     bowl_bowls = 0;
                     bowl_runs = 0;
                     bowl_wick = 0;
                     bowl_dots = 0;
                     ball = 0;
                     freq5=0;
                     freq6=0;freq0=0;freq1=0;freq3=0;

                     db1.updateCurrGameTest("Balls", ball);
                     if (strike == 0) {
                         strike = 1;
                         db1.updateCurrGameTest("Strike", strike);
                     } else {
                         strike = 0;
                         db1.updateCurrGameTest("Strike", strike);
                     }
                     enableBowls();
                     String tmp,tmp2;

                     if (maxOvers == 0) {
                         switch (over) {
                             case 12:
                             case 24:
                                 cbowler = cbowler + 2;
                                 break;
                             case 44:case 76:case 108:case 140:
                                 //swap in ecoBowlers String array
                                 tmp=ecoBowlrs[0];tmp2=ecoBowlrs[1];
                                 ecoBowlrs[0]=ecoBowlrs[2];ecoBowlrs[1]=ecoBowlrs[3];
                                 ecoBowlrs[2]=tmp;ecoBowlrs[3]=tmp2;
                                 break;
                             case 56:case 88:case 120:case 152:
                                 tmp=ecoBowlrs[0];tmp2=ecoBowlrs[1];
                                 ecoBowlrs[0]=ecoBowlrs[4];ecoBowlrs[1]=ecoBowlrs[5];
                                 ecoBowlrs[4]=tmp;ecoBowlrs[5]=tmp2;
                                 break;
                             case 32:
                             case 64:
                             case 96:
                             case 128:
                             case 160:
                                 if(flagBat==0)
                                   ecoBowlrs = db1.getWicketBowlersTest(flagBat,match[afb]);
                                 else
                                   ecoBowlrs = db1.getWicketBowlersTest(flagBat,match[afb]);
                                 cbowler = db1.getBowlerNoTest(match[afb], ecoBowlrs[0]);
                                 break;
                         }
                         if(over>=32)
                           cbowler = db1.getBowlerNoTest(match[afb], ecoBowlrs[0]);
                         String bowlu = db1.getBowlerTest(match[afb], cbowler);
                         Log.d("CBOWLER is", "" + cbowler);
                         if (bowlu.equalsIgnoreCase((String) bowl_name.getText())) {
                             if(over>=32) {
                                 cbowler = db1.getBowlerNoTest(match[afb], ecoBowlrs[1]);
                                 bowl_name.setText(db1.getBowlerTest(match[afb], cbowler));
                             }
                             else
                                   bowl_name.setText(db1.getBowlerTest(match[afb], cbowler + 1));
                             }
                          else {
                             bowl_name.setText(db1.getBowlerTest(match[afb], cbowler));
                         }
                         if(flagBat==0) {
                             bowl_fig.setText("" + db1.getBowlStatsTest("bwick", (String) bowl_name.getText()) + "-" + db1.getBowlStatsTest("bruns", (String) bowl_name.getText()));
                             b_wick.setText("" + db1.getBowlStatsTest("bwick", (String) bowl_name.getText()));
                             b_runs.setText("" + db1.getBowlStatsTest("bruns", (String) bowl_name.getText()));
                             bowl_over = db1.getBowlStatsTest("bovers", (String) bowl_name.getText());
                         }
                         else
                         {
                             bowl_fig.setText("" + db1.getBowlStatsTest("bwick_2", (String) bowl_name.getText()) + "-" + db1.getBowlStatsTest("bruns_2", (String) bowl_name.getText()));
                             b_wick.setText("" + db1.getBowlStatsTest("bwick_2", (String) bowl_name.getText()));
                             b_runs.setText("" + db1.getBowlStatsTest("bruns_2", (String) bowl_name.getText()));
                             bowl_over = db1.getBowlStatsTest("bovers_2", (String) bowl_name.getText());
                         }
                         bowl_over = bowl_over / 6;
                         b_overs.setText("" + bowl_over);
                     }
                     Log.d("FlagBat in over is:", "" + flagBat);
                     //activateAll();
                 }
                 if (flagBat == 1) {
                     runsLeft = runsLeft - run;
                     db1.updateCurrGame("runsleft", runsLeft);
                     tballs = tballs - 1;
                     db1.updateCurrGame("tballs", tballs);
                     Log.d("RUNS LEFT VARIABLE: ", "" + runsLeft);
                     eq = "NEED " + runsLeft + " in " + tballs;
                     getFloatThisOverText(i8);
                 }
             }

                 overs.setText("" + over + "." + ball);
        if(flagBat==0)
            db1.setOversTest(match[0], (String) overs.getText(),1);
        else
            db1.setOversTest(match[0], (String) overs.getText(),2);
                 if (strike == 0) {
                     bat1.setText(db1.getCurrTestPlayerName(match[you], b));
                     bat1_run.setText("" + rball[row][col] + "(" + rball[row][col + 1] + ")*");
                     bat2.setText(db1.getCurrTestPlayerName(match[you], r));
                     bat2_run.setText("" + rball[row + 1][col] + "(" + rball[row + 1][col + 1] + ")");
                 } else {
                     bat1.setText(db1.getCurrTestPlayerName(match[you], b));
                     bat1_run.setText("" + rball[row][col] + "(" + rball[row][col + 1] + ")");
                     bat2.setText(db1.getCurrTestPlayerName(match[you], r));
                     bat2_run.setText("" + rball[row + 1][col] + "(" + rball[row + 1][col + 1] + ")*");
                 }
      if(day_over == db1.getConfigValue("TEST_MATCH_DAY_OVERS"))
      {
          //if all overs in a day has been bowled then day will be incremented and day_over will be 0
          day=db1.getCurrDataTest("day");
          day=day+1;
          db1.updateCurrGameTest("day", day);
          if(day==5)
          {
              //match drawn screen flashes
              InningsBreak();
          }
          else {
              //Day over innings break Flashes
              InningsBreak();
          }
          day_over=0;
      }
    }
    void setOnClickListeneronButtons(Button b,final int i)
    {
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                updateScoreCard(i);
            }});
    }
    void setOnClickListeneronButton(Button b)
    {
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialog = new Dialog(MainActivity_Test.this);
                dialog.setContentView(R.layout.second_batting);
                dialog.setTitle("INNINGS BREAK");
                dialog.show();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                // The absolute width of the available display size in pixels.
                int displayWidth = displayMetrics.widthPixels;
                // The absolute height of the available display size in pixels.
                int displayHeight = displayMetrics.heightPixels;

                // Initialize a new window manager layout parameters

                // Copy the alert dialog window attributes to new layout parameter instance
                layoutParams.copyFrom(dialog.getWindow().getAttributes());
                int dialogWindowWidth = (int) (displayWidth);

                int dialogWindowHeight = (int) (displayHeight);
                layoutParams.width = dialogWindowWidth;
                layoutParams.height = dialogWindowHeight;
                dialog.getWindow().setAttributes(layoutParams);

                teamName=(TextView)dialog.findViewById(R.id.vt1);
                prevTeam=(Button)dialog.findViewById(R.id.vt0);
                nextTeam=(Button)dialog.findViewById(R.id.vt01);
                teamName.setText(match[flagBat]);
                setPlayerNames(dialog);
                updateScoreBoard(flagBat);
                setOnClickListeneronText(nextTeam);
                setOnClickListeneronText(prevTeam);
            }});

    }

    void setOnClickListeneronText(final Button t)
    {

        Log.d("INSIDE::","TEXTVIEW");
        t.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Log.d("CLICKED::","TEXTVIEW");
                if(nextScore==0){nextScore=1;db1.updateCurrGame("nextScore",nextScore);}
                else {nextScore=0;db1.updateCurrGame("nextScore",nextScore);}
               teamName.setText(""+match[nextScore]);
                setPlayerNames(dialog);
                updateScoreBoard(nextScore);
                //t.setVisibility(View.GONE);
                //t2.setVisibility(View.VISIBLE);
            }});
    }

    void setPlayerNames(Dialog d)
    {
       TextView tv;
        teamName=(TextView)d.findViewById(R.id.vt1);
       int i=3,j=1,k=0;
        for(i=3;i<14;i++)
        {
            String tvID = "vt" + i + ""+j;
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            tv = ((TextView)d.findViewById(resID));
            tv.setText(db1.getCurrPlayerName((String)teamName.getText(),k));
            k++;
        }
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Sorry Can't go back from this stage", Toast.LENGTH_SHORT).show();
    }
    void InningsBreak()
    {
        cbowler=0;
        p_mode=0;p_taken=0;p_freq=0;
        final Dialog dialog = new Dialog(MainActivity_Test.this);
        dialog.setContentView(R.layout.match_summary_test);
        dialog.setTitle("INNINGS BREAK");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // The absolute width of the available display size in pixels.
        int displayWidth = displayMetrics.widthPixels;
        // The absolute height of the available display size in pixels.
        int displayHeight = displayMetrics.heightPixels;
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        int dialogWindowWidth = (int) (displayWidth);
        // Set alert dialog height equal to screen height 70%
        int dialogWindowHeight = (int) (displayHeight);
        layoutParams.width = dialogWindowWidth;
        layoutParams.height = dialogWindowHeight;
        dialog.getWindow().setAttributes(layoutParams);
        Button start2=(Button)dialog.findViewById(R.id.button8);
        TextView tvs,tvs2;
        cfb=0;chb=0;cfr=0;chr=0;
        String top[]=new String[3];
        String top2[]=new String[3];
        MainActivity_Test.ind2= MainActivity_Test.runs+1;
        //scoreLine.setText("jaIt ko ilayao " + MainActivity.ind2 +" rna caaihyao");
        MainActivity_Test.ind= MainActivity_Test.runs;
        String which;
        teem1=db1.getCurrDataTest("teem_1");teem2=db1.getCurrDataTest("teem_2");
        if(day_over == db1.getConfigValue("TEST_MATCH_DAY_OVERS"))
        {}
        else
        {
        if(teem1==0) {
            db1.updateCurrGameTest("teem_1", MainActivity_Test.ind);
            which="teem_1";
        }
        else {
            db1.updateCurrGameTest("teem_2", MainActivity_Test.ind);
            which="teem_2";
         }
        }
        //db1.updateCurrGame("ind",ind);
        if(day_over == db1.getConfigValue("TEST_MATCH_DAY_OVERS") && day!=5)
        {}
        else {
            MainActivity_Test.runs = 0;
            row = 0;
            col = 0;
            strike = 0;
            b = 0;
            r = 1;
            b4 = 0;
            r4 = 0;
            b6 = 0;
            r6 = 0;
            rball[0][0] = 0;
            rball[0][1] = 0;
            rball[1][0] = 0;
            rball[1][1] = 0;
            MainActivity_Test.wick = 0;//db1.updateCurrGame("Wickets",MainActivity.wick);//flagBat=1;
            MainActivity_Test.over = 0;
            MainActivity_Test.ball = 0;
            MainActivity_Test.runsLeft = MainActivity_Test.ind + 1;
        }
        TextView Tname=(TextView)dialog.findViewById(R.id.vt31);
        TextView Tovers=(TextView)dialog.findViewById(R.id.vt32);
        TextView Truns=(TextView)dialog.findViewById(R.id.vt33);
        TextView Tname2=(TextView)dialog.findViewById(R.id.vt91);
        TextView Tovers2=(TextView)dialog.findViewById(R.id.vt92);
        TextView Truns2=(TextView)dialog.findViewById(R.id.vt93);
        String summ[]=new String[4];String summ2[]=new String[4];
        String summ3[]=new String[4];String summ4[]=new String[4];
        summ=db1.getSummaryTest(match[0],flagBat+1);
        summ2=db1.getSummaryTest(match[1],flagBat+1);
        if(flagBat==1) {//it is second innings we need 1st innings data
            summ3 = db1.getSummaryTest(match[0], flagBat);
            summ4 = db1.getSummaryTest(match[1], flagBat);
        }
        else
        {//we need 2nd innings data
            summ3 = db1.getSummaryTest(match[0], flagBat+2);
            summ4 = db1.getSummaryTest(match[1], flagBat+2);
        }
        int ii=4,jj=1,j=0,i=0,jj1=4;
        opp1=db1.getCurrDataTest("opp_1");opp2=db1.getCurrDataTest("opp_2");
        teem1=db1.getCurrDataTest("teem_1");teem2=db1.getCurrDataTest("teem_2");
        if(flagBat==0) {
            if(opp1==0) {
                Tname.setText(summ[0]);
                Tovers.setText(summ[1]);
                Truns.setText(summ[2]);
                while(j<2)
                {
                   top = db1.getTopPlayersTest(match[0], j,1);
                   top2=db1.getTopPlayersBowlerTest(match[1], j,1);
                   while (i < 3)
                   {
                     String tvID = "vt" + ii + "" + jj;
                     String tvID2 = "vt" + ii + "" + jj1;
                     Log.d("PLAYERS TEXT::", tvID);
                     int resID = getResources().getIdentifier(tvID, "id", getPackageName());
                     int resID2 = getResources().getIdentifier(tvID2, "id", getPackageName());
                     tvs = ((TextView)dialog.findViewById(resID));
                     tvs2 = ((TextView)dialog.findViewById(resID2));
                     tvs.setText("" + top[i]);
                     tvs2.setText("" + top2[i]);
                     jj=jj+1;
                     jj1=jj1+1;
                    if(i==1)
                    {
                    i++;
                    tvs.setText(tvs.getText()+"(" + top[i]+")");
                    tvs2.setText(tvs2.getText()+"-" + top2[i]);
                    }
                i++;
            }
            ii=ii+1;
            jj=1;jj1=4;
            i=0;
            j++;
           }
    }
    else
            {
                Tname.setText(summ2[0]);
                Tovers.setText(summ2[1]);
                Truns.setText(summ2[2]);
                ii=4;jj=1;j=0;i=0;jj1=4;
                while(j<2)
                {
                    top = db1.getTopPlayersTest(match[1], j,1);
                    top2=db1.getTopPlayersBowlerTest(match[0], j,1);
                    while (i < 3)
                    {
                        String tvID = "vt" + ii + "" + jj;
                        String tvID2 = "vt" + ii + "" + jj1;
                        Log.d("PLAYERS TEXT::", tvID);
                        int resID = getResources().getIdentifier(tvID, "id", getPackageName());
                        int resID2 = getResources().getIdentifier(tvID2, "id", getPackageName());
                        tvs = ((TextView)dialog.findViewById(resID));
                        tvs2 = ((TextView)dialog.findViewById(resID2));
                        tvs.setText("" + top[i]);
                        tvs2.setText("" + top2[i]);
                        jj=jj+1;
                        jj1=jj1+1;
                        if(i==1)
                        {
                            i++;
                            tvs.setText(tvs.getText()+"(" + top[i]+")");
                            tvs2.setText(tvs2.getText()+"-" + top2[i]);
                        }
                        i++;
                    }
                    ii=ii+1;
                    jj=1;jj1=4;
                    i=0;
                    j++;
                }

                Tname=(TextView)dialog.findViewById(R.id.vt61);
                Tovers=(TextView)dialog.findViewById(R.id.vt62);
                Truns=(TextView)dialog.findViewById(R.id.vt63);
                Tname.setText(summ[0]);
                Tovers.setText(summ[1]);
                Truns.setText(summ[2]);
                ii=7;jj=1;j=0;i=0;jj1=4;
                while(j<2)
                {
                    top = db1.getTopPlayersTest(match[0], j,1);
                    top2=db1.getTopPlayersBowlerTest(match[1], j,1);
                    while (i < 3)
                    {
                        String tvID = "vt" + ii + "" + jj;
                        String tvID2 = "vt" + ii + "" + jj1;
                        Log.d("PLAYERS TEXT::", tvID);
                        int resID = getResources().getIdentifier(tvID, "id", getPackageName());
                        int resID2 = getResources().getIdentifier(tvID2, "id", getPackageName());
                        tvs = ((TextView)dialog.findViewById(resID));
                        tvs2 = ((TextView)dialog.findViewById(resID2));
                        tvs.setText("" + top[i]);
                        tvs2.setText("" + top2[i]);
                        jj=jj+1;
                        jj1=jj1+1;
                        if(i==1)
                        {
                            i++;
                            tvs.setText(tvs.getText()+"(" + top[i]+")");
                            tvs2.setText(tvs2.getText()+"-" + top2[i]);
                        }
                        i++;
                    }
                    ii=ii+1;
                    jj=1;jj1=4;
                    i=0;
                    j++;
                }
            }
        }
        else
        {
            if(opp2==0)
            {
                Tname.setText(summ3[0]);
                Tovers.setText(summ3[1]);
                Truns.setText(summ3[2]);
                Tname=(TextView)dialog.findViewById(R.id.vt61);
                Tovers=(TextView)dialog.findViewById(R.id.vt62);
                Truns=(TextView)dialog.findViewById(R.id.vt63);
                Tname.setText(summ4[0]);
                Tovers.setText(summ4[1]);
                Truns.setText(summ4[2]);
                ii=4;jj=1;j=0;i=0;jj1=4;
                int count=0;int curr=0;int acurr=1;
                while(count<3)
                {
                 while(j<2)
                 {
                    if(ii<9) {
                        top = db1.getTopPlayersTest(match[curr], j, 1);
                        top2 = db1.getTopPlayersBowlerTest(match[acurr], j, 1);
                    }
                    else
                    {
                        top = db1.getTopPlayersTest(match[curr], j, 2);
                        top2 = db1.getTopPlayersBowlerTest(match[acurr], j, 2);
                    }
                    while (i < 3)
                    {
                        String tvID = "vt" + ii + "" + jj;
                        String tvID2 = "vt" + ii + "" + jj1;
                        Log.d("PLAYERS TEXT::", tvID);
                        int resID = getResources().getIdentifier(tvID, "id", getPackageName());
                        int resID2 = getResources().getIdentifier(tvID2, "id", getPackageName());
                        tvs = ((TextView)dialog.findViewById(resID));
                        tvs2 = ((TextView)dialog.findViewById(resID2));
                        tvs.setText("" + top[i]);
                        tvs2.setText("" + top2[i]);
                        jj=jj+1;
                        jj1=jj1+1;
                        if(i==1)
                        {
                            i++;
                            tvs.setText(tvs.getText()+"(" + top[i]+")");
                            tvs2.setText(tvs2.getText()+"-" + top2[i]);
                        }
                        i++;
                    }
                    ii=ii+1;
                    jj=1;jj1=4;
                    i=0;
                    j++;
                }
             count=count+1;
                    curr=curr+acurr;
                    acurr=curr-acurr;
                    curr=curr-acurr;
             ii=ii+1;jj=1;j=0;i=0;jj1=4;
            }

                Tname2.setText(summ[0]);
                Tovers2.setText(summ[1]);
                Truns2.setText(summ[2]);
                Log.d("opp1&opp2&&teem1&teem2",""+opp1+" "+opp2+" "+teem1+" "+teem2);
                if((opp1+opp2 < teem1) && opp2!=0 && (day_over != db1.getConfigValue("TEST_MATCH_DAY_OVERS")))
                {
                    winner="teem1";
                }
                else if((teem1+teem2 < opp1) && teem2!=0 && (day_over != db1.getConfigValue("TEST_MATCH_DAY_OVERS")))
                {
                    winner="opp1";
                }
                Log.d("WINNER::",winner);
            }
            else
            {
                Tname.setText(summ4[0]);
                Tovers.setText(summ4[1]);
                Truns.setText(summ4[2]);
                Tname=(TextView)dialog.findViewById(R.id.vt61);
                Tovers=(TextView)dialog.findViewById(R.id.vt62);
                Truns=(TextView)dialog.findViewById(R.id.vt63);
                Tname.setText(summ3[0]);
                Tovers.setText(summ3[1]);
                Truns.setText(summ3[2]);
                ii=4;jj=1;j=0;i=0;jj1=4;
                int count=0;int curr=1;int acurr=0;
                while(count<4)
                {
                    while(j<2)
                    {
                        if(ii<9) {
                            top = db1.getTopPlayersTest(match[curr], j, 1);
                            top2 = db1.getTopPlayersBowlerTest(match[acurr], j, 1);
                        }
                        else
                        {
                            top = db1.getTopPlayersTest(match[curr], j, 2);
                            top2 = db1.getTopPlayersBowlerTest(match[acurr], j, 2);
                        }
                        while (i < 3)
                        {
                            String tvID = "vt" + ii + "" + jj;
                            String tvID2 = "vt" + ii + "" + jj1;
                            Log.d("PLAYERS TEXT::", tvID);
                            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
                            int resID2 = getResources().getIdentifier(tvID2, "id", getPackageName());
                            tvs = ((TextView)dialog.findViewById(resID));
                            tvs2 = ((TextView)dialog.findViewById(resID2));
                            tvs.setText("" + top[i]);
                            tvs2.setText("" + top2[i]);
                            jj=jj+1;
                            jj1=jj1+1;
                            if(i==1)
                            {
                                i++;
                                tvs.setText(tvs.getText()+"(" + top[i]+")");
                                tvs2.setText(tvs2.getText()+"-" + top2[i]);
                            }
                            i++;
                        }
                        ii=ii+1;
                        jj=1;jj1=4;
                        i=0;
                        j++;
                    }
                    count=count+1;
                    curr=curr+acurr;
                    acurr=curr-acurr;
                    curr=curr-acurr;
                    ii=ii+1;jj=1;j=0;i=0;jj1=4;
                }

                Tname2.setText(summ2[0]);
                Tovers2.setText(summ2[1]);
                Truns2.setText(summ2[2]);
                Tname2=(TextView)dialog.findViewById(R.id.vt121);
                Tovers2=(TextView)dialog.findViewById(R.id.vt122);
                Truns2=(TextView)dialog.findViewById(R.id.vt123);
                Tname2.setText(summ[0]);
                Tovers2.setText(summ[1]);
                Truns2.setText(summ[2]);
            }
        }
        TextView Teq=(TextView)dialog.findViewById(R.id.vtl);
        if((day_over != db1.getConfigValue("TEST_MATCH_DAY_OVERS"))) {
            Log.d("DAY VALUE::",""+day_over);
            db1.resetCurrGameTest(MainActivity_Test.runsLeft, flagBat);
        }
        //Populating match summary
        teem1=db1.getCurrDataTest("teem_1");teem2=db1.getCurrDataTest("teem_2");
        opp1=db1.getCurrDataTest("opp_1");opp2=db1.getCurrDataTest("opp_2");
        int ttm=teem1+teem2;int topp=opp1+opp2;
        if(ttm<topp && (day_over != db1.getConfigValue("TEST_MATCH_DAY_OVERS"))) {
            if(teem1!=0 && teem2!=0 && opp1!=0 && opp2!=0 && (day_over != db1.getConfigValue("TEST_MATCH_DAY_OVERS")))
            {
                Teq.setText("" + match[1] + " Won the Match");
                start2.setEnabled(false);
                Button start3=(Button)dialog.findViewById(R.id.button10);
                start3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i=1;i<12;i++)
                        {
                            db1.updatePlayersStatTest(match[0],i,"players_test");
                            db1.updatePlayersStatTest(match[1],i,"players_test");
                            db1.updatePrevPerfTest(match[1],match[0],i);
                            db1.updatePrevPerfTest(match[0],match[1],i);
                        }
                        startActivity(new Intent(MainActivity_Test.this, TeamSelect.class));
                        dialog.dismiss();  //australia dialog exits
                    }
                });
            }
            else {
                if(day==5)
                {
                    Teq.setText("Match Drawn");
                    start2.setEnabled(false);
                    Button start3=(Button)dialog.findViewById(R.id.button10);
                    start3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for(int i=1;i<12;i++)
                            {
                                db1.updatePlayersStatTest(match[0],i,"players_test");
                                db1.updatePlayersStatTest(match[1],i,"players_test");
                                db1.updatePrevPerfTest(match[1],match[0],i);
                                db1.updatePrevPerfTest(match[0],match[1],i);
                            }
                            startActivity(new Intent(MainActivity_Test.this, TeamSelect.class));
                            dialog.dismiss();  //australia dialog exits
                        }
                    });
                }
                else {
                    if((topp - ttm) < 0)
                        Teq.setText("" + match[1] + " Trail by " + (ttm - topp) + " runs");
                    else
                        Teq.setText("" + match[1] + " Lead by " + (topp - ttm) + " runs");
                }
            }
            if(winner.equals("opp1")) {
                Teq.setText("" + match[1] + " Won by An Innings and " + (topp - ttm) + " runs");
                start2.setEnabled(false);
                Button start3=(Button)dialog.findViewById(R.id.button10);
                start3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i=1;i<12;i++)
                        {
                            db1.updatePlayersStatTest(match[0],i,"players_test");
                            db1.updatePlayersStatTest(match[1],i,"players_test");
                            db1.updatePrevPerfTest(match[1],match[0],i);
                            db1.updatePrevPerfTest(match[0],match[1],i);
                        }
                        startActivity(new Intent(MainActivity_Test.this, TeamSelect.class));
                        dialog.dismiss();  //australia dialog exits
                    }
                });
            }
        }
            else {
            if(teem1!=0 && teem2!=0 && opp1!=0 && opp2!=0 && (day_over != db1.getConfigValue("TEST_MATCH_DAY_OVERS")))
            {
                Teq.setText("" + match[0] + " Won the Match");
                start2.setEnabled(false);
                Button start3=(Button)dialog.findViewById(R.id.button10);
                start3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i=1;i<12;i++)
                        {
                            db1.updatePlayersStatTest(match[0],i,"players_test");
                            db1.updatePlayersStatTest(match[1],i,"players_test");
                            db1.updatePrevPerfTest(match[1],match[0],i);
                            db1.updatePrevPerfTest(match[0],match[1],i);
                        }
                        startActivity(new Intent(MainActivity_Test.this, TeamSelect.class));
                        dialog.dismiss();  //australia dialog exits
                    }
                });
            }
            else
            {
                if(day==5)
                {
                    Teq.setText("Match Drawn");
                    start2.setEnabled(false);
                    Button start3=(Button)dialog.findViewById(R.id.button10);
                    start3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for(int i=1;i<12;i++)
                            {
                                db1.updatePlayersStatTest(match[0],i,"players_test");
                                db1.updatePlayersStatTest(match[1],i,"players_test");
                                db1.updatePrevPerfTest(match[1],match[0],i);
                                db1.updatePrevPerfTest(match[0],match[1],i);
                            }
                            startActivity(new Intent(MainActivity_Test.this, TeamSelect.class));
                            dialog.dismiss();  //australia dialog exits
                        }
                    });
                }
                else{
                    if((ttm-topp) < 0)
                        Teq.setText("" + match[0] + " Trail by " + (topp - ttm) + " runs");
                    else
                        Teq.setText("" + match[0] + " Lead by " + (ttm - topp) + " runs");
                }
            }

            if(winner.equals("teem1")) {
                Teq.setText("" + match[0] + " Won by An Innings and " + (ttm - topp) + " runs");
                start2.setEnabled(false);
                Button start3=(Button)dialog.findViewById(R.id.button10);
                start3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i=1;i<12;i++)
                        {
                            db1.updatePlayersStatTest(match[0],i,"players_test");
                            db1.updatePlayersStatTest(match[1],i,"players_test");
                            db1.updatePrevPerfTest(match[1],match[0],i);
                            db1.updatePrevPerfTest(match[0],match[1],i);
                        }
                        startActivity(new Intent(MainActivity_Test.this, TeamSelect.class));
                        dialog.dismiss();  //australia dialog exits
                    }
                });
            }
        }
        start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setContentView(R.layout.activity_main);
                //Intent myIntent = new Intent(getBaseContext(),MainActivity.class);
                //startActivity(myIntent);
                score.setText(""+ MainActivity_Test.runs+"-"+ MainActivity_Test.wick);
            //    result.setText("TARGET "+ind2);
                //Log.d("IND VARIABLE IS: ",""+ind);
                activateAll();
                /*ImageView img=(ImageView)findViewById(R.id.imageView3);
                img.setImageResource(drid2);
                ImageView img2=(ImageView)findViewById(R.id.imageView4);
                img2.setImageResource(drid);*/
                if(day_over != 0)
                 startActivity(new Intent(MainActivity_Test.this, Bowling_Test.class));
                dialog.dismiss();  //australia dialog exits
            }
        });
        activateAll();
        dialog.show();
    }
    void activateAll()
    {
        zero.setClickable(true);one.setClickable(true);two.setClickable(true);three.setClickable(true);
        four.setClickable(true);five.setClickable(true);six.setClickable(true);
    }
    void disableAll()
    {
        zero.setClickable(false);one.setClickable(false);two.setClickable(false);three.setClickable(false);
        four.setClickable(false);five.setClickable(false);six.setClickable(false);
    }
    public void declareInnings(Button bt)
    {
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decl=1;
                updateScoreCard(0);
            }
        });
    }
    void updateScoreBoard(int zed) {
        TextView tv,tv1,tv2;
       // teamName.setText(match[0]);
        int j=3,k=2,kk=0,ii=1,i=1;
        String data[]=new String[4];
        while(i<12) {
            if(k==6)
            {j=j+1;k=2;kk=0;ii=ii+1;i=i+1;}
            data = db1.getPlayersStats(match[zed], ii);
            String tvID = "vt" + j + ""+k;
            Log.d("PLAYERS TEXT::",tvID);
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            tv = ((TextView)dialog.findViewById(resID));
            tv.setText(""+data[kk]);
            k=k+1;
            kk=kk+1;
        }
        tv1 = ((TextView)dialog.findViewById(R.id.vt142));
        tv2 = ((TextView)dialog.findViewById(R.id.vt143));
        tv1.setText(db1.getScores(match[zed]));
        tv2.setText(db1.getOvers(match[zed]));
    }
    public void disableThat(int no)
    {
        String tvID = "b" + no;
        int resID = getResources().getIdentifier(tvID, "id", getPackageName());
        Button bt=(Button)findViewById(resID);
        bt.setVisibility(View.INVISIBLE);
    }
    public void enableBowls()
    {
        for(int i=0;i<7;i++)
        {
            String tvID = "b" + i;
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            Button bt=(Button)findViewById(resID);
            bt.setVisibility(View.VISIBLE);
        }
    }

}
