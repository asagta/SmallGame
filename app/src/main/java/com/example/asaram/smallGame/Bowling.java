package com.example.asaram.smallGame;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Dialog;
import java.io.IOException;
import java.util.Random;
import android.view.Window;
public class Bowling extends AppCompatActivity {
    DatabaseHandler db1;
    private Button zero;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six,ib,prevTeam,nextTeam;
    private TextView bat,batScore;
    private TextView bowl;
    private TextView score;
    private TextView result;
    private TextView finalA;
    private TextView finalB;
    private TextView overs,bowler,b_overs,b_runs,b_wick;
    private TextView equation,teamName;
    private double rpo,reqrpo;
    public WindowManager.LayoutParams layoutParams;
    public static Dialog dialog,dialog2;
    static String[] indo,aussie;
    static String eq="",winner,looser;
    static float nrr,nrr2;
    static int[][] rball;static String match[];
    static int b4,b6,r4,r6,nextScore,drid,drid2,freq,freq5,pbowler,bowl_bowls,bowl_runs,bowl_wick,bowl_over,bowl_dots,cfb,cfr,chb,chr;
    static int b,r,row,col,strike,runs,runsLeft,maxOvers,maxWick,n,run,wick,flagBat,ind,aus,over,ball,ind2,over2,tballs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eq="";
        bat=(TextView)findViewById(R.id.textView22);
        bowl=(TextView)findViewById(R.id.textView23);
        score=(TextView)findViewById(R.id.textView16);
        ib=(Button)findViewById(R.id.button);
        zero=(Button)findViewById(R.id.b0);
        one=(Button)findViewById(R.id.b1);
        two=(Button)findViewById(R.id.b2);
        three=(Button)findViewById(R.id.b3);
        four=(Button)findViewById(R.id.b4);
        five=(Button)findViewById(R.id.b5);
        six=(Button)findViewById(R.id.b6);
        result=(TextView)findViewById(R.id.textView18);
        //finalA=(TextView)findViewById(R.id.textView9);
        //finalB=(TextView)findViewById(R.id.textView11);
        overs=(TextView)findViewById(R.id.textView17);
        equation=(TextView)findViewById(R.id.textView19);
        //batScore=(TextView)findViewById(R.id.textView2);
        dialog = new Dialog(Bowling.this);
        dialog.setContentView(R.layout.second_batting);
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
        wick=db1.getCurrData("Wickets");
        over=db1.getCurrData("Overs");
        ball=db1.getCurrData("Balls");ind=db1.getCurrData("ind");
        over2=db1.getCurrData("Over2");row=db1.getCurrData("row");
        col=db1.getCurrData("col");strike=db1.getCurrData("Strike");
        b=db1.getCurrData("batter");r=db1.getCurrData("runner");
        rpo=0;reqrpo=0;
        layoutParams = new WindowManager.LayoutParams();

        overs.setText(""+over+"."+ball);
        runs=db1.getCurrData("runs");
        maxOvers=db1.getCurrData("maxOvers");maxWick=10;
        flagBat=db1.getCurrData("flagBat");tballs=db1.getCurrData("tballs");
        rball=new int[2][2];
        rball[0][0]=db1.getCurrData("batter_run");rball[0][1]=db1.getCurrData("batter_ball");
        rball[1][0]=db1.getCurrData("runner_run");rball[1][1]=db1.getCurrData("runner_ball");
        nextScore=db1.getCurrData("nextScore");
        b4=db1.getCurrData("bat_four");b6=db1.getCurrData("bat_six");
        r4=db1.getCurrData("run_four");r6=db1.getCurrData("run_six");
        runsLeft=db1.getCurrData("runsLeft");
        ind=db1.getCurrData("ind");
        if(ind>0)
        { int id=ind+1;result.setText("TARGET "+id);}
        score.setText(""+runs+"-"+wick);
        drid=getResources().getIdentifier(match[flagBat].toLowerCase(), "drawable", getPackageName());
        if(flagBat==0)
            drid2=getResources().getIdentifier(match[flagBat+1].toLowerCase(), "drawable", getPackageName());
        else
            drid2=getResources().getIdentifier(match[flagBat-1].toLowerCase(), "drawable", getPackageName());
        ImageView img=(ImageView)findViewById(R.id.imageView3);
        img.setImageResource(drid);
        ImageView img2=(ImageView)findViewById(R.id.imageView4);
        img2.setImageResource(drid2);
        if(strike==0)
            equation.setText("   *"+db1.getCurrPlayerName(match[flagBat],b)+" "+rball[row][col]+"("+rball[row][col+1]+")"+"\t"+db1.getCurrPlayerName(match[flagBat],r)+" "+rball[row+1][col]+"("+rball[row+1][col+1]+")");
        else
            equation.setText("   " + db1.getCurrPlayerName(match[flagBat],b) + " " + rball[row][col] + "(" + rball[row][col + 1] + ")" + "\t\t" + db1.getCurrPlayerName(match[flagBat],r) + " " + rball[row + 1][col] + "(" + rball[row + 1][col + 1] + ")*\n\n" + eq);
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
    }
    void updateScoreCard(int s) {
        if (maxOvers == 20 || over > 32) {
            Random rand = new Random();
            n = rand.nextInt(8) + 1;
            bat.setText("" + n);
            bowl.setText("" + s);

            if (s == n && s > 0 && s != 5 && s != 1) {
                disableThat(s);
                wick = wick + 1;
                bowl_wick = db1.getBowlStats("bwick", (String) bowler.getText()) + 1;
                b_wick.setText("" + bowl_wick);
                db1.updateBowlStats("bwick", (String) bowler.getText(), 1);
                db1.updateCurrGame("Wickets", wick);//For saving the gaem
                //result.setText("OUT");
                score.setText("" + runs + "-" + wick);
                db1.setScores(match[flagBat], (String) score.getText());
                if (strike == 0) {
                    rball[row][col + 1] += 1;
                    db1.updateCurrGame("batter_ball", rball[row][col + 1]);
                    if (flagBat == 0) {

                        db1.updateBalls(db1.getCurrPlayerName(match[0], b), rball[row][col + 1]);
                        db1.updateRuns(db1.getCurrPlayerName(match[0], b), rball[row][col]);
                        db1.updateOut(db1.getCurrPlayerName(match[0], b));
                        CelebrationAnimations.showBatOutDialog(Bowling.this,db1.getCurrPlayerName(match[0], b), rball[row][col], rball[row][col + 1], b6, b4);
                        cfb=0;chb=0;
                    } else {

                        db1.updateBalls(db1.getCurrPlayerName(match[1], b), rball[row][col + 1]);
                        db1.updateRuns(db1.getCurrPlayerName(match[1], b), rball[row][col]);
                        db1.updateOut(db1.getCurrPlayerName(match[1], b));
                        CelebrationAnimations.showBatOutDialog(Bowling.this,db1.getCurrPlayerName(match[1], b), rball[row][col], rball[row][col + 1], b6, b4);
                        cfb=0;chb=0;
                    }// batScore.setText(""+rball[row][col]+"("+ ++rball[row][col+1]+")  ");
                    rball[row][col] = 0;
                    db1.updateCurrGame("batter_run", rball[row][col]);
                    rball[row][col + 1] = 0;
                    db1.updateCurrGame("batter_ball", rball[row][col + 1]);
                    b4 = 0;
                    db1.updateCurrGame("bat_four", b4);
                    b6 = 0;
                    db1.updateCurrGame("bat_six", b6);
                    b = Math.max(b, r) + 1;
                    db1.updateCurrGame("batter", b);
                } else {
                    rball[row + 1][col + 1] += 1;
                    db1.updateCurrGame("runner_ball", rball[row + 1][col + 1]);
                    //db1.updateBalls(db1.getCurrPlayerName(match[1],b),rball[row+1][col+1]);
                    if (flagBat == 0) {

                        db1.updateBalls(db1.getCurrPlayerName(match[0], r), rball[row + 1][col + 1]);
                        db1.updateRuns(db1.getCurrPlayerName(match[0], r), rball[row + 1][col]);
                        db1.updateOut(db1.getCurrPlayerName(match[0], r));
                        CelebrationAnimations.showBatOutDialog(Bowling.this,db1.getCurrPlayerName(match[0], r), rball[row + 1][col], rball[row + 1][col + 1], r6, r4);

                    } else {
                        db1.updateBalls(db1.getCurrPlayerName(match[1], r), rball[row + 1][col + 1]);
                        db1.updateRuns(db1.getCurrPlayerName(match[1], r), rball[row + 1][col]);
                        db1.updateOut(db1.getCurrPlayerName(match[1], r));
                        CelebrationAnimations.showBatOutDialog(Bowling.this,db1.getCurrPlayerName(match[1], r), rball[row + 1][col], rball[row + 1][col + 1], r6, r4);
                    }  // batScore.setText(batScore.getText()+""+rball[row+1][col]+"("+ ++rball[row+1][col+1]+")  ");
                    rball[row + 1][col] = 0;
                    db1.updateCurrGame("runner_run", rball[row + 1][col]);
                    rball[row + 1][col + 1] = 0;
                    db1.updateCurrGame("runner_ball", rball[row + 1][col + 1]);
                    cfr=0;chr=0;
                    r4 = 0;
                    db1.updateCurrGame("run_four", r4);
                    r6 = 0;
                    db1.updateCurrGame("run_six", r6);
                    r = Math.max(b, r) + 1;
                    db1.updateCurrGame("Runner", r);
                }
                run = 0;
                db1.updateCurrGame("run", run);
            } else if (s == 0) {
                if (n == 6) run = 4;
                else if (n == 7) run = 6;
                else if (n == 8) run = 4;
                else run = 0;
                if (run == 6) {
                    run = 6;
                    Log.d("SIXES_STRIKE::", "" + strike);
                    if (strike == 0) {
                        b6 += 1;
                        Log.d("BAT_SIXES::", "" + b6);
                        db1.updateCurrGame("bat_six", b6);
                        db1.updateSixes(db1.getCurrPlayerName(match[flagBat], b), b6);
                    } else {
                        r6 += 1;
                        Log.d("RUN_SIXES::", "" + r6);
                        db1.updateCurrGame("run_six", r6);
                        db1.updateSixes(db1.getCurrPlayerName(match[flagBat], r), r6);
                    }
                }
                if (run == 4) {
                    if (strike == 0) {
                        b4 += 1;
                        Log.d("BAT_FOURS::", "" + b4);
                        db1.updateCurrGame("bat_four", b4);
                        db1.updateFours(db1.getCurrPlayerName(match[flagBat], b), b4);
                    } else {
                        r4 += 1;
                        Log.d("RUN_FOURS::", "" + r4);
                        db1.updateCurrGame("run_four", r4);
                        db1.updateFours(db1.getCurrPlayerName(match[flagBat], r), r4);
                    }
                }
                runs = runs + run;
                db1.updateCurrGame("runs", runs);
                db1.updateCurrGame("run", run);
                if (strike == 0) {
                    rball[row][col] += run;
                    db1.updateCurrGame("batter_run", rball[row][col]);
                    rball[row][col + 1] += 1;
                    if(rball[row][col] > 49)
                    {cfb=cfb+1;}
                    if(rball[row][col] > 99)
                    {chb=chb+1;}
                    if(cfb==1) {
                        float strRate=(float)rball[row][col]/rball[row][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRew(db1.getCurrPlayerName(match[flagBat], b));
                        CelebrationAnimations.ShowThatFifty(Bowling.this,db1.getCurrPlayerName(match[flagBat], b),rew[1],rball[row][col],rball[row][col + 1],b6, b4,strRate);
                    }
                    if(chb==1) {
                        float strRate=(float)rball[row][col]/rball[row][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRew(db1.getCurrPlayerName(match[flagBat], b));
                        CelebrationAnimations.ShowThatHundred(Bowling.this,db1.getCurrPlayerName(match[flagBat], b),rew[2],rball[row][col],rball[row][col + 1],b6, b4,strRate);
                    }
                    db1.updateCurrGame("batter_ball", rball[row][col + 1]);
                    db1.updateRuns(db1.getCurrPlayerName(match[flagBat], b), rball[row][col]);
                    db1.updateBalls(db1.getCurrPlayerName(match[flagBat], b), rball[row][col + 1]);
                } else {
                    rball[row + 1][col] += run;
                    db1.updateCurrGame("runner_run", rball[row + 1][col]);
                    rball[row + 1][col + 1] += 1;
                    if(rball[row+1][col] > 49)
                    {cfr=cfr+1;}
                    if(rball[row+1][col] > 99)
                    {chr=chr+1;}
                    if(cfr==1) {
                        float strRate=(float)rball[row+1][col]/rball[row+1][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRew(db1.getCurrPlayerName(match[flagBat], r));
                        CelebrationAnimations.ShowThatFifty(Bowling.this,db1.getCurrPlayerName(match[flagBat], r),rew[1],rball[row+1][col],rball[row+1][col + 1],r6, r4,strRate);
                    }
                    if(chr==1) {
                        float strRate=(float)rball[row+1][col]/rball[row+1][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRew(db1.getCurrPlayerName(match[flagBat], r));
                        CelebrationAnimations.ShowThatHundred(Bowling.this,db1.getCurrPlayerName(match[flagBat], r),rew[2],rball[row+1][col],rball[row+1][col + 1],r6, r4,strRate);
                    }
                    db1.updateCurrGame("runner_ball", rball[row + 1][col + 1]);
                    db1.updateRuns(db1.getCurrPlayerName(match[flagBat], r), rball[row + 1][col]);
                    db1.updateBalls(db1.getCurrPlayerName(match[flagBat], r), rball[row + 1][col + 1]);
                }

                score.setText("" + runs + "-" + wick);

                bowl_runs = db1.getBowlStats("bruns", (String) bowler.getText()) + run;
                b_runs.setText("" + bowl_runs);
                db1.updateBowlStats("bruns", (String) bowler.getText(), run);
                db1.setScores(match[flagBat], (String) score.getText());
                if (run == 0) {
                    bowl_dots = db1.getBowlStats("bmaiden", (String) bowler.getText()) + 1;
                    db1.updateBowlStats("bmaiden", (String) bowler.getText(), 1);
                }
                freq++;
                if (flagBat == 1) {
                    Log.d("IND VARIABLE IS: ", "" + ind);
                    Log.d("RUNS VARIABLE IS: ", "" + runs);
                    if (runs > ind) {
                        //finalB.setText("" + runs);
                        Log.d("LAST BALL ANDAUS WON:", "" + runs);
                        Toast.makeText(getApplicationContext(), match[1] + " Won!!!!", Toast.LENGTH_SHORT).show();
                        winner = match[1];
                        looser = match[0];
                        AusWins();
                        ind = 0;
                        over = 0;
                        aus = 0;//so that flow doesn't go into next conditions
                        db1.updateCurrGame("ind", ind);
                        db1.updateCurrGame("Overs", over);
                        //db1.updateCurrGame("aus",aus);
                    }
                }
                if (freq == 3) disableThat(0);
            } else {
                run = s - n;
                if (s == 5) {
                    freq5++;
                    if (freq5 == 3) {
                        disableThat(5);
                    }
                } else {
                    disableThat(s);
                }
                if (run == -1 || run == 0) {
                    run = 0;
                } else if (run == -2 || run == 1) {
                    run = 1;
                } else if (run == -3 || run == 2 || run == -5) {
                    run = 2;
                } else if (run == -4 || run == 3) {
                    run = 3;
                } else if (run == 4) {
                    run = 4;
                } else if (run == -6 || run == -7) {
                    run = 4;
                } else {
                    run = 6;
                }
                db1.updateCurrGame("run", run);
                if (run == 6) {
                    run = 6;
                    Log.d("SIXES_STRIKE::", "" + strike);
                    if (strike == 0) {
                        b6 += 1;
                        Log.d("BAT_SIXES::", "" + b6);
                        db1.updateCurrGame("bat_six", b6);
                        db1.updateSixes(db1.getCurrPlayerName(match[flagBat], b), b6);
                    } else {
                        r6 += 1;
                        Log.d("RUN_SIXES::", "" + r6);
                        db1.updateCurrGame("run_six", r6);
                        db1.updateSixes(db1.getCurrPlayerName(match[flagBat], r), r6);
                    }
                }
                if (run == 4) {
                    if (strike == 0) {
                        b4 += 1;
                        Log.d("BAT_FOURS::", "" + b4);
                        db1.updateCurrGame("bat_four", b4);
                        db1.updateFours(db1.getCurrPlayerName(match[flagBat], b), b4);
                    } else {
                        r4 += 1;
                        Log.d("RUN_FOURS::", "" + r4);
                        db1.updateCurrGame("run_four", r4);
                        db1.updateFours(db1.getCurrPlayerName(match[flagBat], r), r4);
                    }
                }
                runs = runs + run;
                db1.updateCurrGame("runs", runs);
                bowl_runs = db1.getBowlStats("bruns", (String) bowler.getText()) + run;
                b_runs.setText("" + bowl_runs);
                db1.updateBowlStats("bruns", (String) bowler.getText(), run);
                if (run == 0) {
                    bowl_dots = db1.getBowlStats("bmaiden", (String) bowler.getText()) + 1;
                    db1.updateBowlStats("bmaiden", (String) bowler.getText(), 1);
                }
                if (strike == 0) {
                    rball[row][col] += run;
                    db1.updateCurrGame("batter_run", rball[row][col]);
                    rball[row][col + 1] += 1;

                    if(rball[row][col] > 49)
                    {cfb=cfb+1;}
                    if(rball[row][col] > 99)
                    {chb=chb+1;}
                    if(cfb==1) {
                        float strRate=(float)rball[row][col]/rball[row][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRew(db1.getCurrPlayerName(match[flagBat], b));
                        CelebrationAnimations.ShowThatFifty(Bowling.this,db1.getCurrPlayerName(match[flagBat], b),rew[1],rball[row][col],rball[row][col + 1],b6, b4,strRate);
                    }
                    if(chb==1) {
                        float strRate=(float)rball[row][col]/rball[row][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRew(db1.getCurrPlayerName(match[flagBat], b));
                        CelebrationAnimations.ShowThatHundred(Bowling.this,db1.getCurrPlayerName(match[flagBat], b),rew[2],rball[row][col],rball[row][col + 1],b6, b4,strRate);
                    }

                    db1.updateCurrGame("batter_ball", rball[row][col + 1]);
                    db1.updateRuns(db1.getCurrPlayerName(match[flagBat], b), rball[row][col]);
                    db1.updateBalls(db1.getCurrPlayerName(match[flagBat], b), rball[row][col + 1]);
                } else {
                    rball[row + 1][col] += run;
                    db1.updateCurrGame("runner_run", rball[row + 1][col]);
                    rball[row + 1][col + 1] += 1;

                    if(rball[row+1][col] > 49)
                    {cfr=cfr+1;}
                    if(rball[row+1][col] > 99)
                    {chr=chr+1;}
                    if(cfr==1) {
                        float strRate=(float)rball[row+1][col]/rball[row+1][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRew(db1.getCurrPlayerName(match[flagBat], r));
                        CelebrationAnimations.ShowThatFifty(Bowling.this,db1.getCurrPlayerName(match[flagBat], r),rew[1],rball[row+1][col],rball[row+1][col + 1],r6, r4,strRate);
                    }
                    if(chr==1) {
                        float strRate=(float)rball[row+1][col]/rball[row+1][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRew(db1.getCurrPlayerName(match[flagBat], r));
                        CelebrationAnimations.ShowThatHundred(Bowling.this,db1.getCurrPlayerName(match[flagBat], r),rew[2],rball[row+1][col],rball[row+1][col + 1],r6, r4,strRate);
                    }

                    db1.updateCurrGame("runner_ball", rball[row + 1][col + 1]);
                    db1.updateRuns(db1.getCurrPlayerName(match[flagBat], r), rball[row + 1][col]);
                    db1.updateBalls(db1.getCurrPlayerName(match[flagBat], r), rball[row + 1][col + 1]);
                }
                if ((run % 2 == 0) && (strike == 0)) {
                    strike = 0;
                    db1.updateCurrGame("Strike", strike);
                } else if ((run % 2 == 0) && (strike == 1)) {
                    strike = 1;
                    db1.updateCurrGame("Strike", strike);
                } else if ((run % 2 != 0) && (strike == 1)) {
                    strike = 0;
                    db1.updateCurrGame("Strike", strike);
                } else if ((run % 2 != 0) && (strike == 0)) {
                    strike = 1;
                    db1.updateCurrGame("Strike", strike);
                }
                score.setText("" + runs + "-" + wick);
                db1.setScores(match[flagBat], (String) score.getText());
                //result.setText(""+run);
                if (flagBat == 1) {
                    Log.d("IND VARIABLE IS: ", "" + ind);
                    Log.d("RUNS VARIABLE IS: ", "" + runs);
                    if (runs > ind) {
                        //finalB.setText("" + runs);
                        Log.d("LAST BALL ANDAUS WON:", "" + runs);
                        Toast.makeText(getApplicationContext(), match[1] + " Won!!!!", Toast.LENGTH_SHORT).show();
                        winner = match[1];
                        looser = match[0];
                        AusWins();
                        ind = 0;
                        over = 0;
                        aus = 0;//so that flow doesn't go into next conditions
                        db1.updateCurrGame("ind", ind);
                        db1.updateCurrGame("Overs", over);
                        //db1.updateCurrGame("aus",aus);
                    }
                }
            }
            if (wick == maxWick) {
                score.setText("" + runs);
                //db1.updateScoreTable(runs);
                Toast.makeText(getApplicationContext(), "Innings over", Toast.LENGTH_SHORT).show();
                disableAll();
                InningsBreak();
                //activateAll();
                Log.d("IND VARIABLE IS: ", "" + ind);
                Log.d("RUNS VARIABLE IS: ", "" + runs);
                Log.d("WICK VARIABLE IS: ", "" + wick);
                Log.d("AUS VARIABLE IS: ", "" + aus);
                Log.d("FlagBat VARIABLE IS: ", "" + flagBat);
                Log.d("Over VARIABLE IS: ", "" + over);
                Log.d("Ball VARIABLE IS: ", "" + ball);
                //startActivity(new Intent(Bowling.this, secondBat.class));
                if (flagBat == 0)//if first batting
                {
                    //  finalA.setText("" + ind);
                    flagBat = 1;
                    db1.updateCurrGame("flagBat", flagBat);
                    over2 = 1;
                    db1.updateCurrGame("Over2", over2);
                    Log.d("Now,FlagBat VARIABLE: ", "" + flagBat);
                } else //if 2nd batting
                {
                    //finalB.setText("" + runs);
                    aus = runs;
                    //db1.updateCurrGame("aus",aus);
                    Toast.makeText(getApplicationContext(), match[0] + " WON!!", Toast.LENGTH_SHORT).show();
                    winner = match[0];
                    looser = match[1];
                    IndWins();//means 1stBatting Team Won
                }
                //Toast.makeText(getApplicationContext(),"Australian Batting Starts",Toast.LENGTH_SHORT).show();
                flagBat = 1;
                db1.updateCurrGame("flagBat", flagBat);
                //ind=runs;
                runs = 0;
                db1.updateCurrGame("runs", runs);
                wick = 0;
                db1.updateCurrGame("Wickets", wick);
                score.setText("" + runs + "-" + wick);
                db1.setScores(match[flagBat], (String) score.getText());
                //result.setText("NEW");

            }
            //code for updating overs
            if (over2 == 1) {
                over2 = 0;
                db1.updateCurrGame("over2", over2);
            } else {
                ball = ball + 1;
                db1.updateBowlStats("bovers", (String) bowler.getText(), 1);
                bowl_bowls = bowl_bowls + 1;
                bowl_over = db1.getBowlStats("bovers", (String) bowler.getText());
                bowl_over = bowl_over / 6;
                b_overs.setText("" + bowl_over + "." + bowl_bowls);

                db1.updateCurrGame("Balls", ball);
                //tballs=tballs+1;
                if (ball > 5) {
                    over = over + 1;
                    db1.updateCurrGame("Overs", over);
                    //db1.updateBowlStats("bovers",(String)bowler.getText(),1);

                    bowl_bowls = 0;
                    bowl_runs = 0;
                    bowl_wick = 0;
                    bowl_dots = 0;
                    b_overs.setText("" + bowl_bowls);
                    b_runs.setText("" + bowl_runs);
                    b_wick.setText("" + bowl_wick);
                    enableBowls();
                    disableAll();
                    freq = 0;
                    freq5 = 0;
                    ball = 0;
                    db1.updateCurrGame("Balls", ball);
                    showBowlersMenu();
                    if (strike == 0) {
                        strike = 1;
                        db1.updateCurrGame("Strike", strike);
                    } else {
                        strike = 0;
                        db1.updateCurrGame("Strike", strike);
                    }
                    Log.d("FlagBat in over is:", "" + flagBat);
                }
                //rpo=runs*6/(tballs);
                if (flagBat == 1) {
                    runsLeft = runsLeft - run;
                    db1.updateCurrGame("runsleft", runsLeft);
                    tballs = tballs - 1;
                    db1.updateCurrGame("tballs", tballs);
                    Log.d("RUNS LEFT VARIABLE: ", "" + runsLeft);
                    eq = "NEED " + runsLeft + " runs in " + tballs + "BALLS";
                }

                if (over == maxOvers) {
                    disableAll();
                    InningsBreak();
                    //activateAll();
                    // tballs=0;
                    if (flagBat == 0) {
                        System.out.println("");
                        flagBat = 1;
                        db1.updateCurrGame("flagBat", flagBat);
                    }//if first batting
                    //finalA.setText("" + runs);
                    else //if 2nd batting
                    {
                        //finalB.setText("" + runs);
                        aus = runs;
                        //db1.updateCurrGame("aus",aus);
                        if (aus > ind) {
                            Log.d("Last Ball: ", "VICTORY");
                            Toast.makeText(getApplicationContext(), match[1] + " WON!!", Toast.LENGTH_SHORT).show();
                            winner = match[1];
                            looser = match[0];
                            AusWins();
                        } else {
                            Toast.makeText(getApplicationContext(), match[0] + " WON!!", Toast.LENGTH_SHORT).show();
                            winner = match[0];
                            looser = match[1];
                            IndWins();
                        }
                    }
                    over = 0;
                    db1.updateCurrGame("Overs", over);
                }
            }
            overs.setText("" + over + "." + ball);
            db1.setOvers(match[flagBat], (String) overs.getText());
            if (flagBat == 0) {
                if (strike == 0)
                    equation.setText("   *" + db1.getCurrPlayerName(match[0], b) + " " + rball[row][col] + "(" + rball[row][col + 1] + ")" + "\t\t" + db1.getCurrPlayerName(match[0], r) + " " + rball[row + 1][col] + "(" + rball[row + 1][col + 1] + ")");
                else
                    equation.setText("   " + db1.getCurrPlayerName(match[0], b) + " " + rball[row][col] + "(" + rball[row][col + 1] + ")" + "\t\t" + db1.getCurrPlayerName(match[0], r) + " " + rball[row + 1][col] + "(" + rball[row + 1][col + 1] + ")*");
            } else {
                if (strike == 0)
                    equation.setText("   *" + db1.getCurrPlayerName(match[1], b) + " " + rball[row][col] + "(" + rball[row][col + 1] + ")" + "\t\t" + db1.getCurrPlayerName(match[1], r) + " " + rball[row + 1][col] + "(" + rball[row + 1][col + 1] + ")\n\n" + eq);
                else
                    equation.setText("   " + db1.getCurrPlayerName(match[1], b) + " " + rball[row][col] + "(" + rball[row][col + 1] + ")" + "\t\t" + db1.getCurrPlayerName(match[1], r) + " " + rball[row + 1][col] + "(" + rball[row + 1][col + 1] + ")*\n\n" + eq);
            }

        }
        else{
            Random rand = new Random();
            n = rand.nextInt(9);
            bat.setText("" + n);
            bowl.setText("" + s);

            if (s == n && s > 0 && s != 5 && s != 1 && s != 2) {
                disableThat(s);
                wick = wick + 1;
                bowl_wick = db1.getBowlStats("bwick", (String) bowler.getText()) + 1;
                b_wick.setText("" + bowl_wick);
                db1.updateBowlStats("bwick", (String) bowler.getText(), 1);
                db1.updateCurrGame("Wickets", wick);//For saving the gaem
                //result.setText("OUT");
                score.setText("" + runs + "-" + wick);
                db1.setScores(match[flagBat], (String) score.getText());
                if (strike == 0) {
                    rball[row][col + 1] += 1;
                    db1.updateCurrGame("batter_ball", rball[row][col + 1]);
                    if (flagBat == 0) {

                        db1.updateBalls(db1.getCurrPlayerName(match[0], b), rball[row][col + 1]);
                        db1.updateRuns(db1.getCurrPlayerName(match[0], b), rball[row][col]);
                        db1.updateOut(db1.getCurrPlayerName(match[0], b));
                        CelebrationAnimations.showBatOutDialog(Bowling.this,db1.getCurrPlayerName(match[0], b), rball[row][col], rball[row][col + 1], b6, b4);
                    } else {

                        db1.updateBalls(db1.getCurrPlayerName(match[1], b), rball[row][col + 1]);
                        db1.updateRuns(db1.getCurrPlayerName(match[1], b), rball[row][col]);
                        db1.updateOut(db1.getCurrPlayerName(match[1], b));
                        CelebrationAnimations.showBatOutDialog(Bowling.this,db1.getCurrPlayerName(match[1], b), rball[row][col], rball[row][col + 1], b6, b4);
                    }// batScore.setText(""+rball[row][col]+"("+ ++rball[row][col+1]+")  ");
                    rball[row][col] = 0;
                    cfb=0;chb=0;
                    db1.updateCurrGame("batter_run", rball[row][col]);
                    rball[row][col + 1] = 0;
                    db1.updateCurrGame("batter_ball", rball[row][col + 1]);
                    b4 = 0;
                    db1.updateCurrGame("bat_four", b4);
                    b6 = 0;
                    db1.updateCurrGame("bat_six", b6);
                    b = Math.max(b, r) + 1;
                    db1.updateCurrGame("batter", b);
                } else {
                    rball[row + 1][col + 1] += 1;
                    db1.updateCurrGame("runner_ball", rball[row + 1][col + 1]);
                    //db1.updateBalls(db1.getCurrPlayerName(match[1],b),rball[row+1][col+1]);
                    if (flagBat == 0) {

                        db1.updateBalls(db1.getCurrPlayerName(match[0], r), rball[row + 1][col + 1]);
                        db1.updateRuns(db1.getCurrPlayerName(match[0], r), rball[row + 1][col]);
                        db1.updateOut(db1.getCurrPlayerName(match[0], r));
                        CelebrationAnimations.showBatOutDialog(Bowling.this,db1.getCurrPlayerName(match[0], r), rball[row + 1][col], rball[row + 1][col + 1], r6, r4);
                    } else {
                        db1.updateBalls(db1.getCurrPlayerName(match[1], r), rball[row + 1][col + 1]);
                        db1.updateRuns(db1.getCurrPlayerName(match[1], r), rball[row + 1][col]);
                        db1.updateOut(db1.getCurrPlayerName(match[1], r));
                        CelebrationAnimations.showBatOutDialog(Bowling.this,db1.getCurrPlayerName(match[1], r), rball[row + 1][col], rball[row + 1][col + 1], r6, r4);
                    }  // batScore.setText(batScore.getText()+""+rball[row+1][col]+"("+ ++rball[row+1][col+1]+")  ");
                    rball[row + 1][col] = 0;
                    cfr=0;chr=0;
                    db1.updateCurrGame("runner_run", rball[row + 1][col]);
                    rball[row + 1][col + 1] = 0;
                    db1.updateCurrGame("runner_ball", rball[row + 1][col + 1]);
                    r4 = 0;
                    db1.updateCurrGame("run_four", r4);
                    r6 = 0;
                    db1.updateCurrGame("run_six", r6);
                    r = Math.max(b, r) + 1;
                    db1.updateCurrGame("Runner", r);
                }
                run = 0;
                db1.updateCurrGame("run", run);
            }

            else if (s==0) {
                if (n == 6) run = 4;
                else if (n == 7) run = 2;
                else run = 0;
                if (run == 6) {
                    run = 6;
                    Log.d("SIXES_STRIKE::", "" + strike);
                    if (strike == 0) {
                        b6 += 1;
                        Log.d("BAT_SIXES::", "" + b6);
                        db1.updateCurrGame("bat_six", b6);
                        db1.updateSixes(db1.getCurrPlayerName(match[flagBat], b), b6);
                    } else {
                        r6 += 1;
                        Log.d("RUN_SIXES::", "" + r6);
                        db1.updateCurrGame("run_six", r6);
                        db1.updateSixes(db1.getCurrPlayerName(match[flagBat], r), r6);
                    }
                }
                if (run == 4) {
                    if (strike == 0) {
                        b4 += 1;
                        Log.d("BAT_FOURS::", "" + b4);
                        db1.updateCurrGame("bat_four", b4);
                        db1.updateFours(db1.getCurrPlayerName(match[flagBat], b), b4);
                    } else {
                        r4 += 1;
                        Log.d("RUN_FOURS::", "" + r4);
                        db1.updateCurrGame("run_four", r4);
                        db1.updateFours(db1.getCurrPlayerName(match[flagBat], r), r4);
                    }
                }
                runs = runs + run;
                db1.updateCurrGame("runs", runs);
                db1.updateCurrGame("run", run);
                if (strike == 0) {
                    rball[row][col] += run;
                    db1.updateCurrGame("batter_run", rball[row][col]);
                    rball[row][col + 1] += 1;

                    if(rball[row][col] > 49)
                    {cfb=cfb+1;}
                    if(rball[row][col] > 99)
                    {chb=chb+1;}
                    if(cfb==1) {
                        float strRate=(float)rball[row][col]/rball[row][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRew(db1.getCurrPlayerName(match[flagBat], b));
                        CelebrationAnimations.ShowThatFifty(Bowling.this,db1.getCurrPlayerName(match[flagBat], b),rew[1],rball[row][col],rball[row][col + 1],b6, b4,strRate);
                    }
                    if(chb==1) {
                        float strRate=(float)rball[row][col]/rball[row][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRew(db1.getCurrPlayerName(match[flagBat], b));
                        CelebrationAnimations.ShowThatHundred(Bowling.this,db1.getCurrPlayerName(match[flagBat], b),rew[2],rball[row][col],rball[row][col + 1],b6, b4,strRate);
                    }
                    db1.updateCurrGame("batter_ball", rball[row][col + 1]);
                    db1.updateRuns(db1.getCurrPlayerName(match[flagBat], b), rball[row][col]);
                    db1.updateBalls(db1.getCurrPlayerName(match[flagBat], b), rball[row][col + 1]);
                } else {
                    rball[row + 1][col] += run;
                    db1.updateCurrGame("runner_run", rball[row + 1][col]);
                    rball[row + 1][col + 1] += 1;

                    if(rball[row+1][col] > 49)
                    {cfr=cfr+1;}
                    if(rball[row+1][col] > 99)
                    {chr=chr+1;}
                    if(cfr==1) {
                        float strRate=(float)rball[row+1][col]/rball[row+1][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRew(db1.getCurrPlayerName(match[flagBat], r));
                        CelebrationAnimations.ShowThatFifty(Bowling.this,db1.getCurrPlayerName(match[flagBat], r),rew[1],rball[row+1][col],rball[row+1][col + 1],r6, r4,strRate);
                    }
                    if(chr==1) {
                        float strRate=(float)rball[row+1][col]/rball[row+1][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRew(db1.getCurrPlayerName(match[flagBat], r));
                        CelebrationAnimations.ShowThatHundred(Bowling.this,db1.getCurrPlayerName(match[flagBat], r),rew[2],rball[row+1][col],rball[row+1][col + 1],r6, r4,strRate);
                    }
                    db1.updateCurrGame("runner_ball", rball[row + 1][col + 1]);
                    db1.updateRuns(db1.getCurrPlayerName(match[flagBat], r), rball[row + 1][col]);
                    db1.updateBalls(db1.getCurrPlayerName(match[flagBat], r), rball[row + 1][col + 1]);
                }

                score.setText("" + runs + "-" + wick);

                bowl_runs = db1.getBowlStats("bruns", (String) bowler.getText()) + run;
                b_runs.setText("" + bowl_runs);
                db1.updateBowlStats("bruns", (String) bowler.getText(), run);
                db1.setScores(match[flagBat], (String) score.getText());
                if (run == 0) {
                    bowl_dots = db1.getBowlStats("bmaiden", (String) bowler.getText()) + 1;
                    db1.updateBowlStats("bmaiden", (String) bowler.getText(), 1);
                }
                freq++;
                if (flagBat == 1) {
                    Log.d("IND VARIABLE IS: ", "" + ind);
                    Log.d("RUNS VARIABLE IS: ", "" + runs);
                    if (runs > ind) {
                        //finalB.setText("" + runs);
                        Log.d("LAST BALL ANDAUS WON:", "" + runs);
                        Toast.makeText(getApplicationContext(), match[1] + " Won!!!!", Toast.LENGTH_SHORT).show();
                        winner = match[1];
                        looser = match[0];
                        AusWins();
                        ind = 0;
                        over = 0;
                        aus = 0;//so that flow doesn't go into next conditions
                        db1.updateCurrGame("ind", ind);
                        db1.updateCurrGame("Overs", over);
                        //db1.updateCurrGame("aus",aus);
                    }
                }
                if (freq == 4) disableThat(0);
            } else {
                run = s - n;
                Log.d("RUN_CHECKER",""+run);
                if (s == 5) {
                    freq5++;
                    if (freq5 == 4) {
                        disableThat(5);
                    }
                } else {
                    disableThat(s);
                }
                if (run == -1 || run == 0) {
                    run = 0;
                } else if (run == -2 || run == 1) {
                    run = 1;
                } else if (run == -3 || run == 2 || run == -5) {
                    run = 2;
                } else if (run == -4 || run == 3) {
                    run = 3;
                } else if (run == 4) {
                    run = 4;
                } else if (run == -6 || run == -7) {
                    run = 4;
                }
                else if ((run == (s-n))&& (s!=6 && n!=1)) {
                    run = 0;
                }
                else if(run==5){
                    Log.d("The Runs is in","5");
                    run = 6;
                }
                else {
                    Log.d("The Runs is in","0");
                    run = 0;
                }

                if((n==8) && (s==1||s==2||s==5))
                   run=0;
                else if((n==8) && (s==3||s==4||s==6))
                   run=1;
                db1.updateCurrGame("run", run);
                if (run == 6) {
                    run = 6;
                    Log.d("SIXES_STRIKE::", "" + strike);
                    if (strike == 0) {
                        b6 += 1;
                        Log.d("BAT_SIXES::", "" + b6);
                        db1.updateCurrGame("bat_six", b6);
                        db1.updateSixes(db1.getCurrPlayerName(match[flagBat], b), b6);
                    } else {
                        r6 += 1;
                        Log.d("RUN_SIXES::", "" + r6);
                        db1.updateCurrGame("run_six", r6);
                        db1.updateSixes(db1.getCurrPlayerName(match[flagBat], r), r6);
                    }
                }
                if (run == 4) {
                    if (strike == 0) {
                        b4 += 1;
                        Log.d("BAT_FOURS::", "" + b4);
                        db1.updateCurrGame("bat_four", b4);
                        db1.updateFours(db1.getCurrPlayerName(match[flagBat], b), b4);
                    } else {
                        r4 += 1;
                        Log.d("RUN_FOURS::", "" + r4);
                        db1.updateCurrGame("run_four", r4);
                        db1.updateFours(db1.getCurrPlayerName(match[flagBat], r), r4);
                    }
                }
                runs = runs + run;
                db1.updateCurrGame("runs", runs);
                bowl_runs = db1.getBowlStats("bruns", (String) bowler.getText()) + run;
                b_runs.setText("" + bowl_runs);
                db1.updateBowlStats("bruns", (String) bowler.getText(), run);
                if (run == 0) {
                    bowl_dots = db1.getBowlStats("bmaiden", (String) bowler.getText()) + 1;
                    db1.updateBowlStats("bmaiden", (String) bowler.getText(), 1);
                }
                if (strike == 0) {
                    rball[row][col] += run;
                    db1.updateCurrGame("batter_run", rball[row][col]);
                    rball[row][col + 1] += 1;

                    if(rball[row][col] > 49)
                    {cfb=cfb+1;}
                    if(rball[row][col] > 99)
                    {chb=chb+1;}
                    if(cfb==1) {
                        float strRate=(float)rball[row][col]/rball[row][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRew(db1.getCurrPlayerName(match[flagBat], b));
                        CelebrationAnimations.ShowThatFifty(Bowling.this,db1.getCurrPlayerName(match[flagBat], b),rew[1],rball[row][col],rball[row][col + 1],b6, b4,strRate);
                    }
                    if(chb==1) {
                        float strRate=(float)rball[row][col]/rball[row][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRew(db1.getCurrPlayerName(match[flagBat], b));
                        CelebrationAnimations.ShowThatHundred(Bowling.this,db1.getCurrPlayerName(match[flagBat], b),rew[2],rball[row][col],rball[row][col + 1],b6, b4,strRate);
                    }

                    db1.updateCurrGame("batter_ball", rball[row][col + 1]);
                    db1.updateRuns(db1.getCurrPlayerName(match[flagBat], b), rball[row][col]);
                    db1.updateBalls(db1.getCurrPlayerName(match[flagBat], b), rball[row][col + 1]);
                } else {
                    rball[row + 1][col] += run;
                    db1.updateCurrGame("runner_run", rball[row + 1][col]);
                    rball[row + 1][col + 1] += 1;

                    if(rball[row+1][col] > 49)
                    {cfr=cfr+1;}
                    if(rball[row+1][col] > 99)
                    {chr=chr+1;}
                    if(cfr==1) {
                        float strRate=(float)rball[row+1][col]/rball[row+1][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRew(db1.getCurrPlayerName(match[flagBat], r));
                        CelebrationAnimations.ShowThatFifty(Bowling.this,db1.getCurrPlayerName(match[flagBat], r),rew[1],rball[row+1][col],rball[row+1][col + 1],r6, r4,strRate);
                    }
                    if(chr==1) {
                        float strRate=(float)rball[row+1][col]/rball[row+1][col + 1];strRate=strRate*100;
                        int rew[]=new int[3];
                        rew=db1.getMatchRew(db1.getCurrPlayerName(match[flagBat], r));
                        CelebrationAnimations.ShowThatHundred(Bowling.this,db1.getCurrPlayerName(match[flagBat], r),rew[2],rball[row+1][col],rball[row+1][col + 1],r6, r4,strRate);
                    }

                    db1.updateCurrGame("runner_ball", rball[row + 1][col + 1]);
                    db1.updateRuns(db1.getCurrPlayerName(match[flagBat], r), rball[row + 1][col]);
                    db1.updateBalls(db1.getCurrPlayerName(match[flagBat], r), rball[row + 1][col + 1]);
                }
                if ((run % 2 == 0) && (strike == 0)) {
                    strike = 0;
                    db1.updateCurrGame("Strike", strike);
                } else if ((run % 2 == 0) && (strike == 1)) {
                    strike = 1;
                    db1.updateCurrGame("Strike", strike);
                } else if ((run % 2 != 0) && (strike == 1)) {
                    strike = 0;
                    db1.updateCurrGame("Strike", strike);
                } else if ((run % 2 != 0) && (strike == 0)) {
                    strike = 1;
                    db1.updateCurrGame("Strike", strike);
                }
                score.setText("" + runs + "-" + wick);
                db1.setScores(match[flagBat], (String) score.getText());
                //result.setText(""+run);
                if (flagBat == 1) {
                    Log.d("IND VARIABLE IS: ", "" + ind);
                    Log.d("RUNS VARIABLE IS: ", "" + runs);
                    if (runs > ind) {
                        //finalB.setText("" + runs);
                        Log.d("LAST BALL ANDAUS WON:", "" + runs);
                        Toast.makeText(getApplicationContext(), match[1] + " Won!!!!", Toast.LENGTH_SHORT).show();
                        winner = match[1];
                        looser = match[0];
                        AusWins();
                        ind = 0;
                        over = 0;
                        aus = 0;//so that flow doesn't go into next conditions
                        db1.updateCurrGame("ind", ind);
                        db1.updateCurrGame("Overs", over);
                        //db1.updateCurrGame("aus",aus);
                    }
                }
            }
            if (wick == maxWick) {
                score.setText("" + runs);
                //db1.updateScoreTable(runs);
                Toast.makeText(getApplicationContext(), "Innings over", Toast.LENGTH_SHORT).show();
                disableAll();
                InningsBreak();
                //activateAll();
                Log.d("IND VARIABLE IS: ", "" + ind);
                Log.d("RUNS VARIABLE IS: ", "" + runs);
                Log.d("WICK VARIABLE IS: ", "" + wick);
                Log.d("AUS VARIABLE IS: ", "" + aus);
                Log.d("FlagBat VARIABLE IS: ", "" + flagBat);
                Log.d("Over VARIABLE IS: ", "" + over);
                Log.d("Ball VARIABLE IS: ", "" + ball);
                //startActivity(new Intent(Bowling.this, secondBat.class));
                if (flagBat == 0)//if first batting
                {
                    //  finalA.setText("" + ind);
                    flagBat = 1;
                    db1.updateCurrGame("flagBat", flagBat);
                    over2 = 1;
                    db1.updateCurrGame("Over2", over2);
                    Log.d("Now,FlagBat VARIABLE: ", "" + flagBat);
                } else //if 2nd batting
                {
                    //finalB.setText("" + runs);
                    aus = runs;
                    //db1.updateCurrGame("aus",aus);
                    Toast.makeText(getApplicationContext(), match[0] + " WON!!", Toast.LENGTH_SHORT).show();
                    winner = match[0];
                    looser = match[1];
                    IndWins();//means 1stBatting Team Won
                }
                //Toast.makeText(getApplicationContext(),"Australian Batting Starts",Toast.LENGTH_SHORT).show();
                flagBat = 1;
                db1.updateCurrGame("flagBat", flagBat);
                //ind=runs;
                runs = 0;
                db1.updateCurrGame("runs", runs);
                wick = 0;
                db1.updateCurrGame("Wickets", wick);
                score.setText("" + runs + "-" + wick);
                db1.setScores(match[flagBat], (String) score.getText());
                //result.setText("NEW");

            }
            //code for updating overs
            if (over2 == 1) {
                over2 = 0;
                db1.updateCurrGame("over2", over2);
            } else {
                ball = ball + 1;
                db1.updateBowlStats("bovers", (String) bowler.getText(), 1);
                bowl_bowls = bowl_bowls + 1;
                bowl_over = db1.getBowlStats("bovers", (String) bowler.getText());
                bowl_over = bowl_over / 6;
                b_overs.setText("" + bowl_over + "." + bowl_bowls);

                db1.updateCurrGame("Balls", ball);
                //tballs=tballs+1;
                if (ball > 5) {
                    over = over + 1;
                    db1.updateCurrGame("Overs", over);
                    //db1.updateBowlStats("bovers",(String)bowler.getText(),1);

                    bowl_bowls = 0;
                    bowl_runs = 0;
                    bowl_wick = 0;
                    bowl_dots = 0;
                    b_overs.setText("" + bowl_bowls);
                    b_runs.setText("" + bowl_runs);
                    b_wick.setText("" + bowl_wick);
                    enableBowls();
                    disableAll();
                    freq = 0;
                    freq5 = 0;
                    ball = 0;
                    db1.updateCurrGame("Balls", ball);
                    showBowlersMenu();
                    if (strike == 0) {
                        strike = 1;
                        db1.updateCurrGame("Strike", strike);
                    } else {
                        strike = 0;
                        db1.updateCurrGame("Strike", strike);
                    }
                    Log.d("FlagBat in over is:", "" + flagBat);
                }
                //rpo=runs*6/(tballs);
                if (flagBat == 1) {
                    runsLeft = runsLeft - run;
                    db1.updateCurrGame("runsleft", runsLeft);
                    tballs = tballs - 1;
                    db1.updateCurrGame("tballs", tballs);
                    Log.d("RUNS LEFT VARIABLE: ", "" + runsLeft);
                    eq = "NEED " + runsLeft + " runs in " + tballs + "BALLS";
                }

                if (over == maxOvers) {
                    disableAll();
                    InningsBreak();
                    //activateAll();
                    // tballs=0;
                    if (flagBat == 0) {
                        System.out.println("");
                        flagBat = 1;
                        db1.updateCurrGame("flagBat", flagBat);
                    }//if first batting
                    //finalA.setText("" + runs);
                    else //if 2nd batting
                    {
                        //finalB.setText("" + runs);
                        aus = runs;
                        //db1.updateCurrGame("aus",aus);
                        if (aus > ind) {
                            Log.d("Last Ball: ", "VICTORY");
                            Toast.makeText(getApplicationContext(), match[1] + " WON!!", Toast.LENGTH_SHORT).show();
                            winner = match[1];
                            looser = match[0];
                            AusWins();
                        } else {
                            Toast.makeText(getApplicationContext(), match[0] + " WON!!", Toast.LENGTH_SHORT).show();
                            winner = match[0];
                            looser = match[1];
                            IndWins();
                        }
                    }
                    over = 0;
                    db1.updateCurrGame("Overs", over);
                }
            }
            overs.setText("" + over + "." + ball);
            db1.setOvers(match[flagBat], (String) overs.getText());
            if (flagBat == 0) {
                if (strike == 0)
                    equation.setText("   *" + db1.getCurrPlayerName(match[0], b) + " " + rball[row][col] + "(" + rball[row][col + 1] + ")" + "\t\t" + db1.getCurrPlayerName(match[0], r) + " " + rball[row + 1][col] + "(" + rball[row + 1][col + 1] + ")");
                else
                    equation.setText("   " + db1.getCurrPlayerName(match[0], b) + " " + rball[row][col] + "(" + rball[row][col + 1] + ")" + "\t\t" + db1.getCurrPlayerName(match[0], r) + " " + rball[row + 1][col] + "(" + rball[row + 1][col + 1] + ")*");
            } else {
                if (strike == 0)
                    equation.setText("   *" + db1.getCurrPlayerName(match[1], b) + " " + rball[row][col] + "(" + rball[row][col + 1] + ")" + "\t\t" + db1.getCurrPlayerName(match[1], r) + " " + rball[row + 1][col] + "(" + rball[row + 1][col + 1] + ")\n\n" + eq);
                else
                    equation.setText("   " + db1.getCurrPlayerName(match[1], b) + " " + rball[row][col] + "(" + rball[row][col + 1] + ")" + "\t\t" + db1.getCurrPlayerName(match[1], r) + " " + rball[row + 1][col] + "(" + rball[row + 1][col + 1] + ")*\n\n" + eq);
            }
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
                dialog = new Dialog(Bowling.this);
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

    void InningsBreak()
    {
        final Dialog dialog = new Dialog(Bowling.this);
        dialog.setContentView(R.layout.match_summary);
        dialog.setTitle("INNINGS BREAK");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // The absolute width of the available display size in pixels.
        int displayWidth = displayMetrics.widthPixels;
        // The absolute height of the available display size in pixels.
        int displayHeight = displayMetrics.heightPixels;
        layoutParams.copyFrom(dialog.getWindow().getAttributes());

        // Set the alert dialog window width and height
        // Set alert dialog width equal to screen width 90%
        // int dialogWindowWidth = (int) (displayWidth * 0.9f);
        // Set alert dialog height equal to screen height 90%
        // int dialogWindowHeight = (int) (displayHeight * 0.9f);

        // Set alert dialog width equal to screen width 70%
        int dialogWindowWidth = (int) (displayWidth);
        // Set alert dialog height equal to screen height 70%
        int dialogWindowHeight = (int) (displayHeight);

        // Set the width and height for the layout parameters
        // This will bet the width and height of alert dialog
        layoutParams.width = dialogWindowWidth;
        layoutParams.height = dialogWindowHeight;
        dialog.getWindow().setAttributes(layoutParams);
        //code for initializing the components in dialog
        // TextView scoreLine=(TextView)dialog.findViewById(R.id.textView7);
        // Typeface custom_font = Typeface.createFromAsset(getAssets(), "Shusha02.ttf");
        //scoreLine.setTypeface(custom_font);
        //scoreLine.setTextSize(34);
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
        //db1.updateCurrGame("ind",ind);
        cfb=0;chb=0;cfr=0;chr=0;
        runs=0;row=0;col=0;strike=0;b=0;r=1;
        b4=0;r4=0;b6=0;r6=0;
        rball[0][0]=0;rball[0][1]=0;rball[1][0]=0;rball[1][1]=0;
        wick=0;//db1.updateCurrGame("Wickets",MainActivity.wick);//flagBat=1;
        over=0;ball=0;runsLeft=ind+1;
        db1.resetCurrGame(runsLeft,maxOvers);

        //Populating match summary
        TextView Tname=(TextView)dialog.findViewById(R.id.vt31);
        TextView Tovers=(TextView)dialog.findViewById(R.id.vt32);
        TextView Truns=(TextView)dialog.findViewById(R.id.vt33);
        TextView Teq=(TextView)dialog.findViewById(R.id.vtl);
        Teq.setText(""+match[1]+" needs "+ind2 + " runs to win");
        String summ[]=new String[3];
        summ=db1.getSummary(0);
        Tname.setText(summ[0]);Tovers.setText(summ[1]);Truns.setText(summ[2]);
        TextView tvs,tvs2;
        int ii=4,jj=1,j=0,i=0,jj1=4;
        String top[]=new String[3];
        String top2[]=new String[3];
        while(j<4) {
            top = db1.getTopPlayers(summ[0], j);
            if(flagBat==0)
                top2=db1.getTopPlayersBowler(match[flagBat+1], j);
            else
                top2=db1.getTopPlayersBowler(match[flagBat-1], j);
            while (i < 3) {
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
        start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setContentView(R.layout.activity_main);
                //Intent myIntent = new Intent(getBaseContext(),MainActivity.class);
                //startActivity(myIntent);
                score.setText(""+runs+"-"+wick);
                result.setText("TARGET "+ind2);
                //Log.d("IND VARIABLE IS: ",""+ind);
                activateAll();
                ImageView img=(ImageView)findViewById(R.id.imageView3);
                img.setImageResource(drid2);
                ImageView img2=(ImageView)findViewById(R.id.imageView4);
                img2.setImageResource(drid);
                if(TossDecision.choose==2)
                {startActivity(new Intent(Bowling.this, MainActivity.class));}
                dialog.dismiss();  //australia dialog exits
            }
        });
        dialog.show();
    }
    void IndWins()
    {
        float rr1=db1.setRunRate(winner);
        float rr2=db1.setRunRate(looser);
        nrr=rr1-rr2;
        nrr2=rr2-rr1;
        freq=0;freq5=0;
        cfb=0;chb=0;cfr=0;chr=0;
        final Dialog dialog = new Dialog(Bowling.this);
        dialog.setContentView(R.layout.match_summary);
        //dialog.setTitle("INDIA!!!INDIA!!!");
        android.view.Window window = dialog.getWindow();
        dialog.getWindow().setAttributes(layoutParams);
        for(int i=1;i<12;i++)
        {
            db1.updatePlayersStat(match[0],i,"players");
            db1.updatePlayersStat(match[1],i,"players");
        }

        //code for updating match summary
        //Populating match summary
        TextView Tname=(TextView)dialog.findViewById(R.id.vt31);
        TextView Tovers=(TextView)dialog.findViewById(R.id.vt32);
        TextView Truns=(TextView)dialog.findViewById(R.id.vt33);
        TextView Tname2=(TextView)dialog.findViewById(R.id.vt81);
        TextView Tovers2=(TextView)dialog.findViewById(R.id.vt82);

        TextView Truns2=(TextView)dialog.findViewById(R.id.vt83);
        TextView Teq=(TextView)dialog.findViewById(R.id.vtl);
        Teq.setText(""+match[0]+" has won the match");
        String summ[]=new String[3];String summ2[]=new String[3];
        summ=db1.getSummary(0);summ2=db1.getSummary(1);
        Tname.setText(summ[0]);Tovers.setText(summ[1]);Truns.setText(summ[2]);
        Tname2.setText(summ2[0]);Tovers2.setText(summ2[1]);Truns2.setText(summ2[2]);
        TextView tvs,tvs2;
        int ii=4,jj=1,j=0,i=0,jj1=4;
        String top[]=new String[3];
        String top2[]=new String[3];
        while(j<4) {
            top = db1.getTopPlayers(summ[0], j);
            top2=db1.getTopPlayersBowler(match[1], j);
            while (i < 3) {
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
        ii=9;jj=1;j=0;i=0;jj1=4;
        while(j<4) {
            top = db1.getTopPlayers(summ2[0], j);
            top2=db1.getTopPlayersBowler(match[0], j);
            while (i < 3) {
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
        //code for initializing the components in dialog
        Button start3=(Button)dialog.findViewById(R.id.button8);
        start3.setEnabled(false);
        Button start2=(Button)dialog.findViewById(R.id.button10);
        start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = getIntent();
                finish();
                startActivity(intent);*/
                //Log.d("IND VARIABLE IS: ",""+ind);
                if(db1.getCurrData("aus")==1)
                {
                    db1.updateTourMatches(winner);
                    if(db1.getTourMatchSno()==7)
                    {
                        startActivity(new Intent(Bowling.this, TourWinner.class));
                    }
                    else {
                        db1.updateNextRound(winner);
                        startActivity(new Intent(Bowling.this, TournamentCentral.class));
                    }
                }
                else if(db1.getCurrData("aus")==2)
                {
                   // db1.updateRRMatches(winner);
                    for(int i=1;i<12;i++)
                    {
                        db1.updatePlayersStat(match[0],i,"rr_stats");
                        db1.updatePlayersStat(match[1],i,"rr_stats");
                    }
                    if(db1.getTourMatchSno()==56)
                    {
                        //update for semi finals
                        db1.updateRR(winner,db1.getTourMatchSno());
                        db1.updateRRPointsTable(winner,looser,nrr,nrr2);
                        db1.updateRRSemiFinals();
                        startActivity(new Intent(Bowling.this, RoundRobinCentral.class));
                    }
                    else if(db1.getTourMatchSno()==57)//final match
                    {
                        db1.updateRR(winner,db1.getTourMatchSno());
                        db1.updateRRFinals();
                        startActivity(new Intent(Bowling.this, RoundRobinCentral.class));
                    }
                    else if(db1.getTourMatchSno()==58)//final match
                    {
                        db1.updateRR(winner,db1.getTourMatchSno());
                        Intent intent = new Intent(getBaseContext(), RoundRobinWinner.class);
                        intent.putExtra("winner", winner);
                        startActivity(intent);
                    }
                    else {
                        db1.updateRR(winner,db1.getTourMatchSno());
                        db1.updateRRPointsTable(winner,looser,nrr,nrr2);
                        startActivity(new Intent(Bowling.this, RoundRobinCentral.class));
                    }
                }
                else if(db1.getCurrData("aus")==3)
                {
                    for(int i=1;i<12;i++)
                    {
                        db1.updatePlayersStat(match[0],i,"ipl_stats");
                        db1.updatePlayersStat(match[1],i,"ipl_stats");
                    }
                    //db1.updateRRMatches(winner);
                    if(db1.getTourMatchSno()==56)
                    {
                        //update for PlayOffs
                        db1.updateIPL(winner,db1.getTourMatchSno(),looser);
                        db1.updateIPLPointsTable(winner,looser,nrr,nrr2);
                        db1.updateIPLPlayOffFixture();//fixtures for eliminator and qualifier
                        startActivity(new Intent(Bowling.this, IPLCentral.class));
                    }
                    else if(db1.getTourMatchSno()==60)//final match
                    {
                        db1.updateIPL(winner,db1.getTourMatchSno(),looser);
                        db1.updateIPLFinals();
                        startActivity(new Intent(Bowling.this, IPLCentral.class));
                    }
                    else if(db1.getTourMatchSno()==61)//final match
                    {
                        db1.updateIPL(winner,db1.getTourMatchSno(),looser);
                        Intent intent = new Intent(getBaseContext(), RoundRobinWinner.class);
                        intent.putExtra("winner", winner);
                        startActivity(intent);
                    }
                    else {
                        db1.updateIPL(winner,db1.getTourMatchSno(),looser);
                        db1.updateIPLPointsTable(winner,looser,nrr,nrr2);
                        startActivity(new Intent(Bowling.this, IPLCentral.class));
                    }
                }
                else
                {
                    startActivity(new Intent(Bowling.this, TeamSelect.class));
                }
                dialog.dismiss();  //australia dialog exits
            }
        });
        ball=0;bowl_bowls=0;
        dialog.show();
    }
    void AusWins()
    {
        ball=0;bowl_bowls=0;
        freq=0;freq5=0;
        cfb=0;chb=0;cfr=0;chr=0;
        final Dialog dialog = new Dialog(Bowling.this);
        dialog.setContentView(R.layout.match_summary);
        android.view.Window window = dialog.getWindow();
        dialog.getWindow().setAttributes(layoutParams);
        for(int i=1;i<12;i++)
        {
            db1.updatePlayersStat(match[0],i,"players");
            db1.updatePlayersStat(match[1],i,"players");
        }

        //code for updating match summary
        //Populating match summary
        TextView Tname=(TextView)dialog.findViewById(R.id.vt31);
        TextView Tovers=(TextView)dialog.findViewById(R.id.vt32);
        TextView Truns=(TextView)dialog.findViewById(R.id.vt33);
        TextView Tname2=(TextView)dialog.findViewById(R.id.vt81);
        TextView Tovers2=(TextView)dialog.findViewById(R.id.vt82);

        TextView Truns2=(TextView)dialog.findViewById(R.id.vt83);
        TextView Teq=(TextView)dialog.findViewById(R.id.vtl);
        Teq.setText(""+match[1]+" has won the match");
        String summ[]=new String[3];String summ2[]=new String[3];
        summ=db1.getSummary(0);summ2=db1.getSummary(1);
        Tname.setText(summ[0]);Tovers.setText(summ[1]);Truns.setText(summ[2]);
        Tname2.setText(summ2[0]);Tovers2.setText(summ2[1]);Truns2.setText(summ2[2]);
        TextView tvs,tvs2;
        int ii=4,jj=1,j=0,i=0,jj1=4;
        String top[]=new String[3];
        String top2[]=new String[3];
        while(j<4) {
            top = db1.getTopPlayers(summ[0], j);
            top2=db1.getTopPlayersBowler(match[1], j);
            while (i < 3) {
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
        ii=9;jj=1;j=0;i=0;jj1=4;
        while(j<4) {
            top = db1.getTopPlayers(summ2[0], j);
            top2=db1.getTopPlayersBowler(match[0], j);
            while (i < 3) {
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
        //code for initializing the components in dialog
        Button start3=(Button)dialog.findViewById(R.id.button8);
        start3.setEnabled(false);
        Button start2=(Button)dialog.findViewById(R.id.button10);
        start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = getIntent();
                finish();
                startActivity(intent);
                //Log.d("IND VARIABLE IS: ",""+ind);*/
                if(db1.getCurrData("aus")==1)
                {
                    db1.updateTourMatches(winner);
                    if(db1.getTourMatchSno()==7)
                    {
                        startActivity(new Intent(Bowling.this, TourWinner.class));
                    }
                    else {
                        db1.updateNextRound(winner);
                        startActivity(new Intent(Bowling.this, TournamentCentral.class));
                    }
                }
                else if(db1.getCurrData("aus")==2)
                {
                   // db1.updateRRMatches(winner);
                    for(int i=1;i<12;i++)
                    {
                        db1.updatePlayersStat(match[0],i,"rr_stats");
                        db1.updatePlayersStat(match[1],i,"rr_stats");
                    }
                    if(db1.getTourMatchSno()==56)
                    {
                        //update for semi finals
                        db1.updateRR(winner,db1.getTourMatchSno());
                        db1.updateRRPointsTable(winner,looser,nrr,nrr2);
                        db1.updateRRSemiFinals();
                        startActivity(new Intent(Bowling.this, RoundRobinCentral.class));
                    }
                    else if(db1.getTourMatchSno()==57)//final match
                    {
                        db1.updateRR(winner,db1.getTourMatchSno());
                        db1.updateRRFinals();
                        startActivity(new Intent(Bowling.this, RoundRobinCentral.class));
                    }
                    else if(db1.getTourMatchSno()==58)//final match
                    {
                        db1.updateRR(winner,db1.getTourMatchSno());
                        Intent intent = new Intent(getBaseContext(), RoundRobinWinner.class);
                        intent.putExtra("winner", winner);
                        startActivity(intent);
                    }
                    else {
                        db1.updateRR(winner,db1.getTourMatchSno());
                        db1.updateRRPointsTable(winner,looser,nrr,nrr2);
                        startActivity(new Intent(Bowling.this, RoundRobinCentral.class));
                    }

                }
                else if(db1.getCurrData("aus")==3)
                {
                    for(int i=1;i<12;i++)
                    {
                        db1.updatePlayersStat(match[0],i,"ipl_stats");
                        db1.updatePlayersStat(match[1],i,"ipl_stats");
                    }
                    //db1.updateRRMatches(winner);
                    if(db1.getTourMatchSno()==56)
                    {
                        //update for PlayOffs
                        db1.updateIPL(winner,db1.getTourMatchSno(),looser);
                        db1.updateIPLPointsTable(winner,looser,nrr,nrr2);
                        db1.updateIPLPlayOffFixture();//fixtures for eliminator and qualifier
                        startActivity(new Intent(Bowling.this, IPLCentral.class));
                    }
                    else if(db1.getTourMatchSno()==60)//final match
                    {
                        db1.updateIPL(winner,db1.getTourMatchSno(),looser);
                        db1.updateIPLFinals();
                        startActivity(new Intent(Bowling.this, IPLCentral.class));
                    }
                    else if(db1.getTourMatchSno()==61)//final match
                    {
                        db1.updateIPL(winner,db1.getTourMatchSno(),looser);
                        Intent intent = new Intent(getBaseContext(), RoundRobinWinner.class);
                        intent.putExtra("winner", winner);
                        startActivity(intent);
                    }
                    else {
                        db1.updateIPL(winner,db1.getTourMatchSno(),looser);
                        db1.updateIPLPointsTable(winner,looser,nrr,nrr2);
                        startActivity(new Intent(Bowling.this, IPLCentral.class));
                    }
                }
                else
                {
                    startActivity(new Intent(Bowling.this, TeamSelect.class));
                }
                dialog.dismiss();  //australia dialog exits
            }
        });
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
        dialog2 = new Dialog(Bowling.this);
        dialog2.setContentView(R.layout.bowler_menu);
        dialog2.setTitle("BOWLERS_MENU");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // The absolute width of the available display size in pixels.
        int displayWidth = displayMetrics.widthPixels;
        // The absolute height of the available display size in pixels.
        int displayHeight = displayMetrics.heightPixels;
        layoutParams.copyFrom(dialog2.getWindow().getAttributes());

        // Set the alert dialog window width and height
        // Set alert dialog width equal to screen width 90%
        // int dialogWindowWidth = (int) (displayWidth * 0.9f);
        // Set alert dialog height equal to screen height 90%
        // int dialogWindowHeight = (int) (displayHeight * 0.9f);

        // Set alert dialog width equal to screen width 70%
        int dialogWindowWidth = (int) (displayWidth);
        // Set alert dialog height equal to screen height 70%
        int dialogWindowHeight = (int) (displayHeight);

        // Set the width and height for the layout parameters
        // This will bet the width and height of alert dialog
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
            if(TossDecision.choose==2)
            {tv1.setText(db1.getBowler(match[1],j));}
            else
            {tv1.setText(db1.getBowler(match[0],j));}
            String sta1[]={"","bovers","bmaiden","bruns","bwick"};
            for(jk=1;jk<5;jk++)
            {
            String tvID2 = "vt" + i + jk;
            int resID2 = getResources().getIdentifier(tvID2, "id", getPackageName());
            tv1a = ((TextView)dialog2.findViewById(resID2));
                if(sta1[jk].equalsIgnoreCase("bovers"))
                    tv1a.setText(""+db1.getBowlStats(sta1[jk],(String)tv1.getText())/6);
                else
                    tv1a.setText(""+db1.getBowlStats(sta1[jk],(String)tv1.getText()));
            }
            setClickToBowler(tv1,i);
            j++;
        }
        dialog2.show();
    }
    public void setClickToBowler(final TextView tv,final int i)
    {
        Log.d("PBOWLER_IS:",""+pbowler);
        bowl_over=db1.getBowlStats("bovers",(String)tv.getText())/6;
        bowler=(TextView)findViewById(R.id.vtt1);
              if(tv.getText().equals((String)bowler.getText()))
        { tv.setEnabled(false);tv.setBackgroundResource(R.color.white);Log.d("SAME_BOWLER","YES");}
       else if((bowl_over==4 && maxOvers==20)||(bowl_over==10 && maxOvers==50) )
        { tv.setEnabled(false);tv.setBackgroundResource(R.color.white);Log.d("QUOTA_KHATAM:","YES");}
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
                            bowl_wick=db1.getBowlStats("bwick",(String)bowler.getText());
                            b_wick.setText(""+bowl_wick);
                            bowl_over=db1.getBowlStats("bovers",(String)bowler.getText());
                            bowl_over=bowl_over/6;
                            Log.d("TheBOWL_OVER:",""+bowl_over);
                            b_overs.setText(""+bowl_over);
                            bowl_runs=db1.getBowlStats("bruns",(String)bowler.getText());
                            b_runs.setText(""+bowl_runs);
                            activateAll();
                            if(over>20)
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
