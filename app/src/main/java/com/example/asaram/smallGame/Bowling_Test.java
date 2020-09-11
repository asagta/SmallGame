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

public class Bowling_Test extends AppCompatActivity {
    DatabaseHandler db1;
    private Button zero;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six,ib,prevTeam,nextTeam;
    private TextView bat,batScore,bat1,bat2,bat1_run,bat2_run;
    private TextView bowl;
    private TextView score;
    private TextView result;
    private TextView finalA;
    private TextView finalB;
    private TextView overs,bowler,b_overs,b_runs,b_wick;
    private TextView equation,teamName,bowl_name,bowl_fig,float_fig;
    private TextView thisover,thisover0,team;
    private double rpo,reqrpo;
    public WindowManager.LayoutParams layoutParams;
    public static Dialog dialog,dialog2;
    static String[] indo,aussie;
    static String eq="",winner,looser,ftext,thover="",ftext2;
    static double nrr,nrr2;
    static int[][] rball;static String match[];
    static int freq0,teem1,teem2,opp1,ttm1,topp,opp2,you,day,i8,i7,b4,b6,r4,r6,nextScore,drid,drid2,freq,freq5,pbowler,bowl_bowls,bowl_runs,bowl_wick,bowl_over,bowl_dots,cfb,cfr,chb,chr;
    static int target,rover,b,r,row,col,strike,runs,runsLeft,maxOvers,maxWick,n,run,wick,flagBat,ind,aus,over,ball,ind2,over2,tballs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration configuration = getResources().getConfiguration();
        int screenWidthDp = configuration.screenWidthDp; //The current width of the available screen space, in dp units, corresponding to screen width resource qualifier.
        int sm = configuration.smallestScreenWidthDp;
        Log.d("CURR & SMALL WIDTH IS",""+screenWidthDp+" "+sm);
        if(screenWidthDp>700)
            setContentView(R.layout.main_activity2);
        else
            setContentView(R.layout.activity_main);
        eq="";rover=0;
        bat=(TextView)findViewById(R.id.textView22);
        bowl=(TextView)findViewById(R.id.textView23);
        bowl_name=(TextView)findViewById(R.id.tv2);
        bowl_fig=(TextView)findViewById(R.id.tv3);
        float_fig=(TextView)findViewById(R.id.ffig);
        score=(TextView)findViewById(R.id.textView16);
        thisover=(TextView)findViewById(R.id.tover);
        thisover0=(TextView)findViewById(R.id.textView20);
        ib=(Button)findViewById(R.id.button);
        zero=(Button)findViewById(R.id.b0);
        one=(Button)findViewById(R.id.b1);
        two=(Button)findViewById(R.id.b2);
        three=(Button)findViewById(R.id.b3);
        four=(Button)findViewById(R.id.b4);
        five=(Button)findViewById(R.id.b5);
        six=(Button)findViewById(R.id.b6);
       // result=(TextView)findViewById(R.id.textView18);
        bat1=(TextView)findViewById(R.id.bat1);bat1_run=(TextView)findViewById(R.id.b1r);
        bat2=(TextView)findViewById(R.id.bat2);bat2_run=(TextView)findViewById(R.id.b2r);
        //finalA=(TextView)findViewById(R.id.textView9);
        //finalB=(TextView)findViewById(R.id.textView11);
        overs=(TextView)findViewById(R.id.textView17);
        equation=(TextView)findViewById(R.id.textView19);
        //batScore=(TextView)findViewById(R.id.textView2);
        dialog = new Dialog(Bowling_Test.this);
        dialog.setContentView(R.layout.second_batting);
        team=(TextView)findViewById(R.id.tm);
        teamName=(TextView)dialog.findViewById(R.id.vt1);
        b_overs=(TextView)findViewById(R.id.vtt2);
        b_runs=(TextView)findViewById(R.id.vtt3);
        b_wick=(TextView)findViewById(R.id.vtt4);
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
        setOnClickListeneronButton(ib);
        winner="";
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
        over2=db1.getCurrDataTest("Over2");row=db1.getCurrDataTest("row");
        col=db1.getCurrDataTest("col");strike=db1.getCurrDataTest("Strike");
        b=db1.getCurrDataTest("batter");r=db1.getCurrDataTest("runner");
        rpo=0;reqrpo=0;
        layoutParams = new WindowManager.LayoutParams();
        overs.setText(""+over+"."+ball);
        runs=db1.getCurrDataTest("runs");
        maxOvers=db1.getCurrDataTest("maxOvers");maxWick=10;
        flagBat=db1.getCurrDataTest("flagBat");tballs=db1.getCurrDataTest("tballs");
        rball=new int[2][2];
        rball[0][0]=db1.getCurrDataTest("batter_run");rball[0][1]=db1.getCurrDataTest("batter_ball");
        rball[1][0]=db1.getCurrDataTest("runner_run");rball[1][1]=db1.getCurrDataTest("runner_ball");
        nextScore=db1.getCurrDataTest("nextScore");
        b4=db1.getCurrDataTest("bat_four");b6=db1.getCurrDataTest("bat_six");
        r4=db1.getCurrDataTest("run_four");r6=db1.getCurrDataTest("run_six");
        runsLeft=db1.getCurrDataTest("runsLeft");
        day=db1.getCurrDataTest("day");you=db1.getCurrDataTest("you");
        teem1=db1.getCurrDataTest("teem_1");teem2=db1.getCurrDataTest("teem_2");
        opp1=db1.getCurrDataTest("opp_1");opp2=db1.getCurrDataTest("opp_2");
        score.setText(""+runs+"-"+wick);
        team.setText("" + match[you]);
        Log.d("Match[you] is :",""+you+" "+match[you]);
        drid=getResources().getIdentifier(match[you].toLowerCase(), "drawable", getPackageName());
        if(you==0) {
            drid2 = getResources().getIdentifier(match[you + 1].toLowerCase(), "drawable", getPackageName());
        }else {
            drid2 = getResources().getIdentifier(match[you - 1].toLowerCase(), "drawable", getPackageName());
        }/* ImageView img=(ImageView)findViewById(R.id.imageView3);
        img.setImageResource(drid);
        ImageView img2=(ImageView)findViewById(R.id.imageView4);
        img2.setImageResource(drid2);*/
        team.setText(""+match[you]);
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
        //if resume, don't show bowlers menu populate black bowler menu with the saved detail
        try{if(getIntent().getStringExtra("resume").equalsIgnoreCase("yes"))
        {
            //find out which bowler's data is not in multiple of 6 and display that
           String blrs[]=new String[8];
            for(int i=0;i<8;i++)
            {
                try
                {blrs[i]=db1.getBowler(match[0],i);}
                catch(Exception e){blrs[i]="";}
            }


        }
        else
           showBowlersMenu();}
        catch(Exception ex){showBowlersMenu();}
        ttm1=teem1+teem2;topp=opp1+opp2;
        //floating text code
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
        if(teem1>0&&opp1>0)
            flagBat=1;
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
                if(teem2!=0) {
                    target=(ttm1 - topp) + 1;
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
            ftext2="SWING MORE IN AIR";
        }
        else
        {
            ftext2=thover;
        }
        return ftext2;
    }
    void updateScoreCard(int s) {
            Random rand = new Random();
            if(over < 25)
             n = rand.nextInt(7) + 0;
            else if (over>24&&over<61)
             n=rand.nextInt(8) + 0;
            else
             n=rand.nextInt(9) + 0;
            bat.setText("" + n);
            bowl.setText("" + s);

            if (s == n && s > 0 && s != 5 && s != 1&& s!=2)
            {
                disableThat(s);
                wick = wick + 1;
                thover=thover+" "+"W";
                thisover.setText(getFloatThisOverText(i8));
                float_fig.setText(getFloatText(i7));
                if(flagBat==0) {
                    bowl_wick = db1.getBowlStatsTest("bwick", (String) bowler.getText()) + 1;
                    db1.updateBowlStatsTest("bwick", (String) bowler.getText(), 1);
                }
                    else {
                    bowl_wick = db1.getBowlStatsTest("bwick_2", (String) bowler.getText()) + 1;
                    db1.updateBowlStatsTest("bwick_2", (String) bowler.getText(), 1);
                }
                bowl_fig.setText(""+bowl_wick+"-"+bowl_runs);
                b_wick.setText("" + bowl_wick);
                db1.updateCurrGameTest("Wickets", wick);//For saving the gaem
                //result.setText("OUT");
                score.setText("" + runs + "-" + wick);
                float_fig.setText(getFloatText(i7));
                if(flagBat==0)
                    db1.setScoresTest(match[1], (String) score.getText(),1);
                else
                    db1.setScoresTest(match[1], (String) score.getText(),2);
                if (strike == 0) {
                    rball[row][col + 1] += 1;
                    db1.updateCurrGameTest("batter_ball", rball[row][col + 1]);
                    if (flagBat == 0) {
                        db1.updateCurrPlayerTest("Balls", db1.getCurrTestPlayerName(match[you], b), rball[row][col + 1]);
                        db1.updateCurrPlayerTest("Runs", db1.getCurrTestPlayerName(match[you], b), rball[row][col]);
                        db1.updateCurrPlayerTest("out", db1.getCurrTestPlayerName(match[you], b), 1);
                        CelebrationAnimations.showBatOutDialog(Bowling_Test.this,db1.getCurrTestPlayerName(match[1], b), rball[row][col], rball[row][col + 1], b6, b4);
                        cfb=0;chb=0;
                    } else {
                        db1.updateCurrPlayerTest("Balls_2", db1.getCurrTestPlayerName(match[you], b), rball[row][col + 1]);
                        db1.updateCurrPlayerTest("Runs_2", db1.getCurrTestPlayerName(match[you], b), rball[row][col]);
                        db1.updateCurrPlayerTest("out_2", db1.getCurrTestPlayerName(match[you], b), 1);
                        CelebrationAnimations.showBatOutDialog(Bowling_Test.this,db1.getCurrTestPlayerName(match[1], b), rball[row][col], rball[row][col + 1], b6, b4);
                        cfb=0;chb=0;
                    }// batScore.setText(""+rball[row][col]+"("+ ++rball[row][col+1]+")  ");
                    rball[row][col] = 0;
                    db1.updateCurrGameTest("batter_run", rball[row][col]);
                    rball[row][col + 1] = 0;
                    db1.updateCurrGameTest("batter_ball", rball[row][col + 1]);
                    b4 = 0;
                    db1.updateCurrGameTest("bat_four", b4);
                    b6 = 0;
                    db1.updateCurrGameTest("bat_six", b6);
                    b = Math.max(b, r) + 1;
                    db1.updateCurrGameTest("batter", b);
                } else {
                    rball[row + 1][col + 1] += 1;
                    db1.updateCurrGameTest("runner_ball", rball[row + 1][col + 1]);
                    //db1.updateBalls(db1.getCurrPlayerName(match[1],b),rball[row+1][col+1]);
                    if (flagBat == 0) {
                        db1.updateCurrPlayerTest("Balls", db1.getCurrTestPlayerName(match[you], r), rball[row + 1][col + 1]);
                        db1.updateCurrPlayerTest("Runs", db1.getCurrTestPlayerName(match[you], r), rball[row + 1][col]);
                        db1.updateCurrPlayerTest("out", db1.getCurrTestPlayerName(match[you], r), 1);
                        CelebrationAnimations.showBatOutDialog(Bowling_Test.this,db1.getCurrTestPlayerName(match[1], r), rball[row + 1][col], rball[row + 1][col + 1], r6, r4);
                    } else {
                        db1.updateCurrPlayerTest("Balls_2", db1.getCurrTestPlayerName(match[you], r), rball[row + 1][col + 1]);
                        db1.updateCurrPlayerTest("Runs_2", db1.getCurrTestPlayerName(match[you], r), rball[row + 1][col]);
                        db1.updateCurrPlayerTest("out_2", db1.getCurrTestPlayerName(match[you], r), 1);
                        CelebrationAnimations.showBatOutDialog(Bowling_Test.this,db1.getCurrTestPlayerName(match[1], r), rball[row + 1][col], rball[row + 1][col + 1], r6, r4);
                    }  // batScore.setText(batScore.getText()+""+rball[row+1][col]+"("+ ++rball[row+1][col+1]+")  ");
                    rball[row + 1][col] = 0;
                    db1.updateCurrGameTest("runner_run", rball[row + 1][col]);
                    rball[row + 1][col + 1] = 0;
                    db1.updateCurrGameTest("runner_ball", rball[row + 1][col + 1]);
                    cfr=0;chr=0;
                    r4 = 0;
                    db1.updateCurrGameTest("run_four", r4);
                    r6 = 0;
                    db1.updateCurrGameTest("run_six", r6);
                    r = Math.max(b, r) + 1;
                    db1.updateCurrGameTest("Runner", r);
                }
                run = 0;
                db1.updateCurrGameTest("run", run);
            } else if (s == 0) {
                if (n == 6) run = 4;
                else run = 0;
                freq0++;
                if(freq0==4)disableThat(0);
                thover=thover+" "+run;
                thisover.setText(getFloatThisOverText(i8));
                float_fig.setText(getFloatText(i7));
                if (run == 4) {
                    if (strike == 0) {
                        b4 += 1;
                        Log.d("BAT_FOURS::", "" + b4);
                        db1.updateCurrGameTest("bat_four", b4);
                        if(flagBat==0)
                        {
                            db1.updateCurrPlayerTest("4s", db1.getCurrTestPlayerName(match[you], b), b4);
                        }
                        else
                        {
                            db1.updateCurrPlayerTest("4s_2", db1.getCurrTestPlayerName(match[you], b), b4);
                        }
                    }
                    else {
                        r4 += 1;
                        Log.d("RUN_FOURS::", "" + r4);
                        db1.updateCurrGameTest("run_four", r4);
                        if(flagBat==0)
                        {
                            db1.updateCurrPlayerTest("4s", db1.getCurrTestPlayerName(match[you], r), r4);
                        }
                        else
                        {
                            db1.updateCurrPlayerTest("4s_2", db1.getCurrTestPlayerName(match[you], r), r4);
                        }
                    }
                }
                runs = runs + run;
                rover=rover+run;
                db1.updateCurrGameTest("runs", runs);
                db1.updateCurrGameTest("run", run);
                if (strike == 0) {
                    rball[row][col] += run;
                    db1.updateCurrGameTest("batter_run", rball[row][col]);
                    rball[row][col + 1] += 1;
                    if(rball[row][col] > 49)
                    {cfb=cfb+1;}
                    if(rball[row][col] > 99)
                    {chb=chb+1;}
                    if(cfb==1) {
                        float strRate=(float)rball[row][col]/rball[row][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRewTest(db1.getCurrTestPlayerName(match[you], b));
                        CelebrationAnimations.ShowThatFifty(Bowling_Test.this,db1.getCurrTestPlayerName(match[you], b),rew[0],rball[row][col],rball[row][col + 1],b6, b4,strRate);
                    }
                    if(chb==1) {
                        float strRate=(float)rball[row][col]/rball[row][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRewTest(db1.getCurrTestPlayerName(match[you], b));
                        CelebrationAnimations.ShowThatHundred(Bowling_Test.this,db1.getCurrTestPlayerName(match[you], b),rew[1],rball[row][col],rball[row][col + 1],b6, b4,strRate);
                    }
                    db1.updateCurrGameTest("batter_ball", rball[row][col + 1]);
                    if(flagBat==0)
                    {
                        db1.updateCurrPlayerTest("Runs", db1.getCurrTestPlayerName(match[you], b), rball[row][col]);
                        db1.updateCurrPlayerTest("Balls", db1.getCurrTestPlayerName(match[you], b), rball[row][col+1]);
                    }
                    else
                    {
                        db1.updateCurrPlayerTest("Runs_2", db1.getCurrTestPlayerName(match[you], b), rball[row][col]);
                        db1.updateCurrPlayerTest("Balls_2", db1.getCurrTestPlayerName(match[you], b), rball[row][col+1]);
                    }
                } else {
                    rball[row + 1][col] += run;
                    db1.updateCurrGameTest("runner_run", rball[row + 1][col]);
                    rball[row + 1][col + 1] += 1;
                    if(rball[row+1][col] > 49)
                    {cfr=cfr+1;}
                    if(rball[row+1][col] > 99)
                    {chr=chr+1;}
                    if(cfr==1) {
                        float strRate=(float)rball[row+1][col]/rball[row+1][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRewTest(db1.getCurrTestPlayerName(match[you], r));
                        CelebrationAnimations.ShowThatFifty(Bowling_Test.this,db1.getCurrTestPlayerName(match[you], r),rew[0],rball[row+1][col],rball[row+1][col + 1],r6, r4,strRate);
                    }
                    if(chr==1) {
                        float strRate=(float)rball[row+1][col]/rball[row+1][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRewTest(db1.getCurrTestPlayerName(match[you], r));
                        CelebrationAnimations.ShowThatHundred(Bowling_Test.this,db1.getCurrTestPlayerName(match[you], r),rew[1],rball[row+1][col],rball[row+1][col + 1],r6, r4,strRate);
                    }
                    db1.updateCurrGameTest("runner_ball", rball[row + 1][col + 1]);
                    if(flagBat==0)
                    {
                        db1.updateCurrPlayerTest("Runs", db1.getCurrTestPlayerName(match[you], r), rball[row+1][col]);
                        db1.updateCurrPlayerTest("Balls", db1.getCurrTestPlayerName(match[you], r), rball[row+1][col+1]);
                    }
                    else
                    {
                        db1.updateCurrPlayerTest("Runs_2", db1.getCurrTestPlayerName(match[you], r), rball[row+1][col]);
                        db1.updateCurrPlayerTest("Balls_2", db1.getCurrTestPlayerName(match[you], r), rball[row+1][col+1]);
                    }
                }

                score.setText("" + runs + "-" + wick);
                float_fig.setText(getFloatText(i7));
                if(flagBat==0) {
                    bowl_runs = db1.getBowlStatsTest("bruns", (String) bowl_name.getText()) + run;
                    db1.updateBowlStatsTest("bruns", (String) bowler.getText(), run);
                }
                else {
                    bowl_runs = db1.getBowlStatsTest("bruns_2", (String) bowl_name.getText()) + run;
                    db1.updateBowlStatsTest("bruns_2", (String) bowler.getText(), run);
                }
                b_runs.setText("" + bowl_runs);
                bowl_fig.setText(""+bowl_wick+"-"+bowl_runs);
                if(flagBat==0)
                    db1.setScoresTest(match[1], (String) score.getText(),1);
                else
                    db1.setScoresTest(match[1], (String) score.getText(),2);
            } else//if other than 0
                {
                run = s - n;
                if (s == 5) {
                    freq5++;
                    if (freq5 == 4) {
                        disableThat(5);
                    }
                } else {
                    disableThat(s);
                }

                if (run == -1 || run == 0 || run == -2) {
                    run = 0;
                } else if (run == -3 ||run == -4|| run == 1) {
                    run = 1;
                } else if (run < -3||run ==2) {
                    run = 2;
                } else if (run == 4) {
                    run = 4;
                }
                else if (run == 3) {
                    run = 3;
                }
                else {
                    run = 6;
                }
                if(s==5 && n<8 && n!=0 && n!=5 && n!=4)
                    run=1;
                if(n==0) run=0;
                thover=thover+" "+run;
                thisover.setText(getFloatThisOverText(i8));
                float_fig.setText(getFloatText(i7));
                bowl_fig.setText(""+bowl_wick+"-"+bowl_runs);
                rover=rover+run;
                db1.updateCurrGameTest("run", run);
                if (run == 6) {
                    run = 6;
                    Log.d("SIXES_STRIKE::", "" + strike);
                    if (strike == 0) {
                        b6 += 1;
                        Log.d("BAT_SIXES::", "" + b6);
                        db1.updateCurrGameTest("bat_six", b6);
                        if(flagBat==0)
                          db1.updateCurrPlayerTest("6s", db1.getCurrTestPlayerName(match[you], b), b6);
                        else
                            db1.updateCurrPlayerTest("6s_2", db1.getCurrTestPlayerName(match[you], b), b6);
                    } else {
                        r6 += 1;
                        Log.d("RUN_SIXES::", "" + r6);
                        db1.updateCurrGameTest("run_six", r6);
                        if(flagBat==0)
                            db1.updateCurrPlayerTest("6s", db1.getCurrTestPlayerName(match[you], r), r6);
                        else
                            db1.updateCurrPlayerTest("6s_2", db1.getCurrTestPlayerName(match[you], r), r6);
                    }
                }
                if (run == 4) {
                    if (strike == 0) {
                        b4 += 1;
                        Log.d("BAT_FOURS::", "" + b4);
                        db1.updateCurrGameTest("bat_four", b4);
                        if(flagBat==0)
                            db1.updateCurrPlayerTest("4s", db1.getCurrTestPlayerName(match[you], b), b4);
                        else
                            db1.updateCurrPlayerTest("4s_2", db1.getCurrTestPlayerName(match[you], b), b4);
                    } else {
                        r4 += 1;
                        Log.d("RUN_FOURS::", "" + r4);
                        db1.updateCurrGameTest("run_four", r4);
                        if(flagBat==0)
                            db1.updateCurrPlayerTest("4s", db1.getCurrTestPlayerName(match[you], r), r4);
                        else
                            db1.updateCurrPlayerTest("4s_2", db1.getCurrTestPlayerName(match[you], r), r4);
                    }
                }
                runs = runs + run;
                db1.updateCurrGameTest("runs", runs);
                if(flagBat==0)
                {
                    bowl_runs = db1.getBowlStatsTest("bruns", (String) bowler.getText()) + run;
                    db1.updateBowlStatsTest("bruns", (String) bowler.getText(), run);
                }
                else
                {
                    bowl_runs = db1.getBowlStatsTest("bruns_2", (String) bowler.getText()) + run;
                    db1.updateBowlStatsTest("bruns_2", (String) bowler.getText(), run);
                }

                b_runs.setText("" + bowl_runs);
                bowl_fig.setText(""+bowl_wick+"-"+bowl_runs);
                if (strike == 0) {
                    rball[row][col] += run;
                    db1.updateCurrGameTest("batter_run", rball[row][col]);
                    rball[row][col + 1] += 1;

                    if(rball[row][col] > 49)
                    {cfb=cfb+1;}
                    if(rball[row][col] > 99)
                    {chb=chb+1;}
                    if(cfb==1) {
                        float strRate=(float)rball[row][col]/rball[row][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRewTest(db1.getCurrTestPlayerName(match[you], b));
                        CelebrationAnimations.ShowThatFifty(Bowling_Test.this,db1.getCurrTestPlayerName(match[you], b),rew[0],rball[row][col],rball[row][col + 1],b6, b4,strRate);
                    }
                    if(chb==1) {
                        float strRate=(float)rball[row][col]/rball[row][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRewTest(db1.getCurrTestPlayerName(match[you], b));
                        CelebrationAnimations.ShowThatHundred(Bowling_Test.this,db1.getCurrTestPlayerName(match[you], b),rew[1],rball[row][col],rball[row][col + 1],b6, b4,strRate);
                    }

                    db1.updateCurrGameTest("batter_ball", rball[row][col + 1]);
                    if(flagBat==0)
                    {
                        db1.updateCurrPlayerTest("Runs", db1.getCurrTestPlayerName(match[you], b), rball[row][col]);
                        db1.updateCurrPlayerTest("Balls", db1.getCurrTestPlayerName(match[you], b), rball[row][col+1]);
                    }
                    else
                    {
                        db1.updateCurrPlayerTest("Runs_2", db1.getCurrTestPlayerName(match[you], b), rball[row][col]);
                        db1.updateCurrPlayerTest("Balls_2", db1.getCurrTestPlayerName(match[you], b), rball[row][col+1]);
                    }
                } else {
                    rball[row + 1][col] += run;
                    db1.updateCurrGameTest("runner_run", rball[row + 1][col]);
                    rball[row + 1][col + 1] += 1;
                    if(rball[row+1][col] > 49)
                    {cfr=cfr+1;}
                    if(rball[row+1][col] > 99)
                    {chr=chr+1;}
                    if(cfr==1) {
                        float strRate=(float)rball[row+1][col]/rball[row+1][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRewTest(db1.getCurrTestPlayerName(match[you], r));
                        CelebrationAnimations.ShowThatFifty(Bowling_Test.this,db1.getCurrTestPlayerName(match[you], r),rew[0],rball[row+1][col],rball[row+1][col + 1],r6, r4,strRate);
                    }
                    if(chr==1) {
                        float strRate=(float)rball[row+1][col]/rball[row+1][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRewTest(db1.getCurrTestPlayerName(match[you], r));
                        CelebrationAnimations.ShowThatHundred(Bowling_Test.this,db1.getCurrTestPlayerName(match[you], r),rew[1],rball[row+1][col],rball[row+1][col + 1],r6, r4,strRate);
                    }
                    db1.updateCurrGameTest("runner_ball", rball[row + 1][col + 1]);
                    if(flagBat==0)
                    {
                        db1.updateCurrPlayerTest("Runs", db1.getCurrTestPlayerName(match[you], r), rball[row+1][col]);
                        db1.updateCurrPlayerTest("Balls", db1.getCurrTestPlayerName(match[you], r), rball[row+1][col+1]);
                    }
                    else
                    {
                        db1.updateCurrPlayerTest("Runs_2", db1.getCurrTestPlayerName(match[you], r), rball[row+1][col]);
                        db1.updateCurrPlayerTest("Balls_2", db1.getCurrTestPlayerName(match[you], r), rball[row+1][col+1]);
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
                    if(runs>=target && target!=0)
                    {
                        disableAll();
                        InningsBreak();
                    }
                float_fig.setText(getFloatText(i7));
                    if(flagBat==0)
                        db1.setScoresTest(match[1], (String) score.getText(),1);
                    else
                        db1.setScoresTest(match[1], (String) score.getText(),2);
                //result.setText(""+run);
               //if target is acheived in 4th Innings, Innings break will appear with the match summary

         }

            //code for updating overs
            if (over2 == 1) {
                over2 = 0;
                db1.updateCurrGameTest("over2", over2);
            } else {
                ball = ball + 1;
                if(flagBat==0) {
                    db1.updateBowlStatsTest("bovers", (String) bowler.getText(), 1);
                    bowl_over = db1.getBowlStatsTest("bovers", (String) bowler.getText());
                }else {
                    db1.updateBowlStatsTest("bovers_2", (String) bowler.getText(), 1);
                    bowl_over = db1.getBowlStatsTest("bovers_2", (String) bowler.getText());
                }
                bowl_bowls = bowl_bowls + 1;
                bowl_over = bowl_over / 6;
                Log.d("BALLS BOWLED:",""+bowl_over+"."+bowl_bowls);
                b_overs.setText("" + bowl_over + "." + bowl_bowls);
                db1.updateCurrGameTest("Balls", ball);
                //tballs=tballs+1;
                if (ball > 5) {
                    over = over + 1;
                    MainActivity_Test.day_over=MainActivity_Test.day_over+1;
                    db1.updateCurrGameTest("Overs", over);
                    //db1.updateBowlStats("bovers",(String)bowler.getText(),1);
                    thisover.setText("LAST OVER : "+rover);
                    thover="";
                    //thisover.setText(getFloatThisOverText(i8));
                    float_fig.setText(getFloatText(i7));
                  //  thisover0.setText("LAST OVER : "+rover);
                    rover=0;
                    bowl_bowls = 0;
                    bowl_runs = 0;
                    bowl_wick = 0;
                    bowl_dots = 0;
                    b_overs.setText("" + bowl_bowls);
                    b_runs.setText("" + bowl_runs);
                    bowl_fig.setText(""+bowl_wick+"-"+bowl_runs);
                    b_wick.setText("" + bowl_wick);
                    enableBowls();
                    disableAll();
                    freq = 0;
                    freq5 = 0;
                    ball = 0;
                    db1.updateCurrGameTest("Balls", ball);
                    showBowlersMenu();
                    if (strike == 0) {
                        strike = 1;
                        db1.updateCurrGameTest("Strike", strike);
                    } else {
                        strike = 0;
                        db1.updateCurrGameTest("Strike", strike);
                    }
                    Log.d("FlagBat in over is:", "" + flagBat);
                }
                //rpo=runs*6/(tballs);
            }
            overs.setText("" + over + "." + ball);
        if (wick == maxWick) {
            score.setText("" + runs);

            if(flagBat==0)
                db1.setOversTest(match[1], (String) overs.getText(),1);
            else
                db1.setOversTest(match[1], (String) overs.getText(),2);
            Toast.makeText(getApplicationContext(), "Innings over", Toast.LENGTH_SHORT).show();
            disableAll();
            if(flagBat==0)
                db1.setScoresTest(match[1], (String) score.getText(),1);
            else
                db1.setScoresTest(match[1], (String) score.getText(),2);
            InningsBreak();
            disableAll();
            //Toast.makeText(getApplicationContext(),"Australian Batting Starts",Toast.LENGTH_SHORT).show();
            you = 0;over = 0;ball = 0;
            db1.updateCurrGameTest("you", you);
            db1.updateCurrGameTest("Balls", ball);
            //ind=runs;
            runs = 0;
            db1.updateCurrGameTest("runs", runs);
            wick = 0;
            db1.updateCurrGameTest("Wickets", wick);
            //score.setText("" + runs + "-" + wick);
            float_fig.setText(getFloatText(i7));
            thover="";
        }
        if(flagBat==0)
            db1.setOversTest(match[1], (String) overs.getText(),1);
        else
            db1.setOversTest(match[1], (String) overs.getText(),2);
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
        if(MainActivity_Test.day_over == db1.getConfigValue("TEST_MATCH_DAY_OVERS"))
        {
            //if all overs in a day has been bowled then day will be incremented and day_over will be 0
            day=db1.getCurrDataTest("day");
            day=day+1;
            db1.updateCurrGameTest("day", day);
                //Day over innings break Flashes
            InningsBreak();
            MainActivity_Test.day_over=0;
        }
   }

    void setOnClickListeneronButtons(Button b,final int i)
    {
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(db1.getBowlerStatus((String) bowl_name.getText())==0)
                {
                    disableThat(3);
                }
                updateScoreCard(i);
            }});
    }
    void setOnClickListeneronButton(Button b)
    {
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialog = new Dialog(Bowling_Test.this);
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
                teamName.setText(match[you]);
                setPlayerNames(dialog);
                updateScoreBoard(you);
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

    void InningsBreak()
    {
        final Dialog dialog = new Dialog(Bowling_Test.this);
        dialog.setContentView(R.layout.match_summary_test);
        dialog.setTitle("INNINGS BREAK");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // The absolute width of the available display size in pixels.
        int displayWidth = displayMetrics.widthPixels;
        // The absolute height of the available display size in pixels.
        int displayHeight = displayMetrics.heightPixels;
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        // Set alert dialog width equal to screen width 70%
        int dialogWindowWidth = (int) (displayWidth);
        // Set alert dialog height equal to screen height 70%
        int dialogWindowHeight = (int) (displayHeight);

        // Set the width and height for the layout parameters
        // This will bet the width and height of alert dialog
        layoutParams.width = dialogWindowWidth;
        layoutParams.height = dialogWindowHeight;
        dialog.getWindow().setAttributes(layoutParams);
        Button start2=(Button)dialog.findViewById(R.id.button8);
        Log.d("IND VARIABLE IS: ",""+ind);
        Log.d("RUNS VARIABLE IS: ",""+runs);
        Log.d("WICK VARIABLE IS: ",""+wick);
        Log.d("AUS VARIABLE IS: ",""+aus);
        Log.d("FlagBat VARIABLE IS: ",""+flagBat);
        Log.d("Over VARIABLE IS: ",""+over);
        Log.d("Ball VARIABLE IS: ",""+ball);
        ind2=runs+1;
        //scoreLine.setText("jaIt ko ilayao " + MainActivity.ind2 +" rna caaihyao");
        ind=runs;
        opp1=db1.getCurrDataTest("opp_1");opp2=db1.getCurrDataTest("opp_2");
        if(MainActivity_Test.day_over == db1.getConfigValue("TEST_MATCH_DAY_OVERS") && day!=5)
        {}
        else {
            if (opp1 == 0) db1.updateCurrGameTest("opp_1", ind);
            else {
                db1.updateCurrGameTest("opp_2", ind);
                flagBat = 1;
            }
            cfb = 0;
            chb = 0;
            cfr = 0;
            chr = 0;
            runs = 0;
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
            wick = 0;//db1.updateCurrGame("Wickets",MainActivity.wick);//flagBat=1;
            over = 0;
            ball = 0;
            runsLeft = ind + 1;
        }
        db1.updateCurrGameTest("Balls", ball);
        teem1=db1.getCurrDataTest("teem_1");teem2=db1.getCurrDataTest("teem_2");
        opp1=db1.getCurrDataTest("opp_1");opp2=db1.getCurrDataTest("opp_2");
        TextView Tname=(TextView)dialog.findViewById(R.id.vt31);
        TextView Tovers=(TextView)dialog.findViewById(R.id.vt32);
        TextView Truns=(TextView)dialog.findViewById(R.id.vt33);
        TextView Tname2=(TextView)dialog.findViewById(R.id.vt91);
        TextView Tovers2=(TextView)dialog.findViewById(R.id.vt92);
        TextView Truns2=(TextView)dialog.findViewById(R.id.vt93);

        String summ[]=new String[4];String summ3[]=new String[4];
        String summ2[]=new String[4];String summ4[]=new String[4];
        summ=db1.getSummaryTest(match[1],flagBat+1);
        summ2=db1.getSummaryTest(match[0],flagBat+1);
        if(flagBat==1) {//it is second innings we need 1st innings data
            summ3 = db1.getSummaryTest(match[1], flagBat);
            summ4 = db1.getSummaryTest(match[0], flagBat);
        }
        else
        {//we need 2nd innings data
            summ3 = db1.getSummaryTest(match[1], flagBat+2);
            summ4 = db1.getSummaryTest(match[0], flagBat+2);
        }
        TextView tvs,tvs2;
        int ii=4,jj=1,j=0,i=0,jj1=4;
        String top[]=new String[3];
        String top2[]=new String[3];
        if(flagBat==0) {
            if(teem1==0) {
                Tname.setText(summ[0]);
                Tovers.setText(summ[1]);
                Truns.setText(summ[2]);
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
            }
            else
            {
                Tname.setText(summ2[0]);
                Tovers.setText(summ2[1]);
                Truns.setText(summ2[2]);
                ii=4;jj=1;j=0;i=0;jj1=4;
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

                Tname=(TextView)dialog.findViewById(R.id.vt61);
                Tovers=(TextView)dialog.findViewById(R.id.vt62);
                Truns=(TextView)dialog.findViewById(R.id.vt63);
                Tname.setText(summ[0]);
                Tovers.setText(summ[1]);
                Truns.setText(summ[2]);
                ii=7;jj=1;j=0;i=0;jj1=4;
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
            }
        }
        else
        {
            if(teem2==0)
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
                int count=0;int curr=1;int acurr=0;
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
                if((opp1+opp2 < teem1) && opp2!=0 && (MainActivity_Test.day_over != db1.getConfigValue("TEST_MATCH_DAY_OVERS")))
                {
                    winner="teem1";
                }
                else if((teem1+teem2 < opp1) && teem2!=0 && (MainActivity_Test.day_over != db1.getConfigValue("TEST_MATCH_DAY_OVERS")))
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
                int count=0;int curr=0;int acurr=1;
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
        if((MainActivity_Test.day_over != db1.getConfigValue("TEST_MATCH_DAY_OVERS")))
        {
            Log.d("DAY VALUE::",""+MainActivity_Test.day_over);
            db1.resetCurrGameTest(MainActivity_Test.runsLeft, flagBat);
        }
        //Populating match summary
        TextView Teq=(TextView)dialog.findViewById(R.id.vtl);
        teem1=db1.getCurrDataTest("teem_1");teem2=db1.getCurrDataTest("teem_2");
        opp1=db1.getCurrDataTest("opp_1");opp2=db1.getCurrDataTest("opp_2");
        int ttm=teem1+teem2;int topp=opp1+opp2;
        if(ttm<topp && (MainActivity_Test.day_over != db1.getConfigValue("TEST_MATCH_DAY_OVERS"))) {
            if(teem1!=0 && teem2!=0 && opp1!=0 && opp2!=0 && (MainActivity_Test.day_over != db1.getConfigValue("TEST_MATCH_DAY_OVERS")))
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
                        startActivity(new Intent(Bowling_Test.this, TeamSelect.class));
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
                            startActivity(new Intent(Bowling_Test.this, TeamSelect.class));
                            dialog.dismiss();  //australia dialog exits
                        }
                    });
                }
                else {
                    if((topp-ttm) < 0)
                        Teq.setText("" + match[1] + " Trail by " + (ttm - topp) + " runs");
                    else
                        Teq.setText("" + match[1] + " Lead by " + (topp - ttm)+ " runs");
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
                        startActivity(new Intent(Bowling_Test.this, TeamSelect.class));
                        dialog.dismiss();  //australia dialog exits
                    }
                });
            }
        }else {
            if(teem1!=0 && teem2!=0 && opp1!=0 && opp2!=0 && (MainActivity_Test.day_over != db1.getConfigValue("TEST_MATCH_DAY_OVERS")))
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
                        startActivity(new Intent(Bowling_Test.this, TeamSelect.class));
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
                            startActivity(new Intent(Bowling_Test.this, TeamSelect.class));
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

                        startActivity(new Intent(Bowling_Test.this, TeamSelect.class));
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
                score.setText(""+runs+"-"+wick);
                //result.setText("TARGET "+ind2);
                //Log.d("IND VARIABLE IS: ",""+ind);
                activateAll();
                /*ImageView img=(ImageView)findViewById(R.id.imageView3);
                img.setImageResource(drid2);
                ImageView img2=(ImageView)findViewById(R.id.imageView4);

               img2.setImageResource(drid);*/
                if(MainActivity_Test.day_over != 0)
                  startActivity(new Intent(Bowling_Test.this, MainActivity_Test.class));
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
    public void showBowlersMenu()
    {
        dialog2 = new Dialog(Bowling_Test.this);
        dialog2.setContentView(R.layout.bowler_menu);
        dialog2.setTitle("BOWLERS_MENU");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // The absolute width of the available display size in pixels.
        int displayWidth = displayMetrics.widthPixels;
        // The absolute height of the available display size in pixels.
        int displayHeight = displayMetrics.heightPixels;
        layoutParams.copyFrom(dialog2.getWindow().getAttributes());
        int dialogWindowWidth = (int) (displayWidth);
        // Set alert dialog height equal to screen height 70%
        int dialogWindowHeight = (int) (displayHeight);
        layoutParams.width = dialogWindowWidth;
        layoutParams.height = dialogWindowHeight;
        dialog2.getWindow().setAttributes(layoutParams);
        //code for initializing the components in dialog
        // TextView scoreLine=(TextView)dialog.findViewById(R.id.textView7);
        // Typeface custom_font = Typeface.createFromAsset(getAssets(), "Shusha02.ttf");
        //scoreLine.setTypeface(custom_font);
        //scoreLine.setTextSize(34);
        Button go=(Button)dialog2.findViewById(R.id.button8);
        go.setEnabled(false);
        int i,j,jk;//j to get the order of the bowler
        j=0;jk=1;
        TextView tv1,tv1a;
        for(i=2;i<9;i++)
        {
            String tvID = "bt" + i;
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            tv1 = ((TextView)dialog2.findViewById(resID));
            tv1.setText(db1.getBowlerTest(match[0],j));
            String sta1[];
            if(flagBat==0)
                sta1=new String[]{"","bovers","bmaiden","bruns","bwick"};
            else
                sta1=new String[]{"","bovers_2","bmaiden_2","bruns_2","bwick_2"};
            for(jk=1;jk<5;jk++)
            {
            String tvID2 = "vt" + i + jk;
            int resID2 = getResources().getIdentifier(tvID2, "id", getPackageName());
            tv1a = ((TextView)dialog2.findViewById(resID2));
                if(sta1[jk].equalsIgnoreCase("bovers")||sta1[jk].equalsIgnoreCase("bovers_2"))
                    tv1a.setText(""+db1.getBowlStatsTest(sta1[jk],(String)tv1.getText())/6);
                else
                    tv1a.setText(""+db1.getBowlStatsTest(sta1[jk],(String)tv1.getText()));
            }
            setClickToBowler(tv1,i);
            j++;
        }
        dialog2.show();
    }
    public void setClickToBowler(final TextView tv,final int i)
    {
        Log.d("PBOWLER_IS:",""+pbowler);
        if(flagBat==0)
            bowl_over=db1.getBowlStatsTest("bovers",(String)tv.getText())/6;
        else
            bowl_over=db1.getBowlStatsTest("bovers_2",(String)tv.getText())/6;
        bowler=(TextView)findViewById(R.id.vtt1);
              if(tv.getText().equals((String)bowl_name.getText()))
        { tv.setEnabled(false);tv.setBackgroundResource(R.color.white);Log.d("SAME_BOWLER","YES");}
        else {
            tv.setBackgroundResource(R.color.goldy);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(pbowler>0)
                    {

                            String tvID = "bt" + pbowler;
                            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
                            TextView tv1 = ((TextView) dialog2.findViewById(resID));
                            tv1.setEnabled(true);
                            tv1.setBackgroundResource(R.color.goldy);
                        if(tv1.getText().equals((String)bowler.getText()))
                        { tv1.setEnabled(false);tv1.setBackgroundResource(R.color.white);}
                            pbowler = i;
                    }
                    else
                    {pbowler=i;}
                    Button go = (Button) dialog2.findViewById(R.id.button8);
                    go.setEnabled(true);
                    tv.setEnabled(false);
                    go.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            bowler.setText(tv.getText());
                            bowl_name.setText(tv.getText());
                            if(flagBat==0) {
                                bowl_wick = db1.getBowlStatsTest("bwick", (String) bowler.getText());
                                bowl_over = db1.getBowlStatsTest("bovers", (String) bowler.getText());
                                bowl_runs = db1.getBowlStatsTest("bruns", (String) bowler.getText());
                            }
                            else
                            {
                                bowl_wick = db1.getBowlStatsTest("bwick_2", (String) bowler.getText());
                                bowl_over = db1.getBowlStatsTest("bovers_2", (String) bowler.getText());
                                bowl_runs = db1.getBowlStatsTest("bruns_2", (String) bowler.getText());
                            }
                            b_wick.setText(""+bowl_wick);
                            bowl_over=bowl_over/6;
                            Log.d("TheBOWL_OVER:",""+bowl_over);
                            b_overs.setText(""+bowl_over);
                            b_runs.setText(""+bowl_runs);
                            bowl_fig.setText(""+bowl_wick+"-"+bowl_runs);
                            activateAll();
                            if(db1.getBowlerStatus((String) bowl_name.getText())==0)
                            {
                                disableThat(3);
                            }
                            if(over>24 && over<90)
                                disableThat(4);
                            dialog2.dismiss();
                        }
                    });
                }
            });
        }
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Sorry Can't go back from this stage", Toast.LENGTH_SHORT).show();
    }
}
