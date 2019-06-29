package com.example.asaram.smallGame;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
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
import java.util.Timer;
import java.util.TimerTask;

import android.view.Window;
public class MainActivity extends AppCompatActivity {
    DatabaseHandler db1;
    private Button zero,one,two,three,four,five,six,ib,prevTeam,nextTeam;
    private TextView bat,bowl,score,result,overs,equation,teamName,bowler,b_overs,b_runs,b_wick;
    public WindowManager.LayoutParams layoutParams;
    public static Dialog dialog;
    static String[] ecoBowlrs;
    static String eq="",winner,looser;
    static float nrr,nrr2;
    static int[][] rball;static String match[];
    static int cbowler,bowl_wick,bowl_runs,bowl_dots,bowl_over,bowl_bowls;
    static int b4,b6,r4,r6,nextScore,drid,drid2,freq6,freq5;
    static int b,r,row,col,strike,runs,runsLeft,maxOvers,maxWick,n,run,wick,flagBat,afb,ind,aus,over,ball,ind2,over2,tballs,cfb,cfr,chb,chr;
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
        overs=(TextView)findViewById(R.id.textView17);
        equation=(TextView)findViewById(R.id.textView19);
        bowler=(TextView)findViewById(R.id.vtt1);
        b_overs=(TextView)findViewById(R.id.vtt2);
        b_runs=(TextView)findViewById(R.id.vtt3);
        b_wick=(TextView)findViewById(R.id.vtt4);
        dialog = new Dialog(MainActivity.this);
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
        if(flagBat==0){afb=1;
            bowler.setText(db1.getBowler(match[flagBat+1],cbowler));}
        else{afb=0;
            bowler.setText(db1.getBowler(match[flagBat-1],cbowler));}
    }
    void updateScoreCard(int s){
     if(maxOvers==20 || over>40) {

         Random rand = new Random();
         n = rand.nextInt(7) + 1;
         bat.setText("" + s);
         bowl.setText("" + n);
         if (s == n && s != 5) {
         //CelebrationAnimations ca=new CelebrationAnimations();
             wick = wick + 1;
             db1.updateCurrGame("Wickets", wick);
             bowl_wick = db1.getBowlStats("bwick", (String) bowler.getText()) + 1;
             b_wick.setText("" + bowl_wick);//For saving the gaem
             db1.updateBowlStats("bwick", (String) bowler.getText(), 1);
             //result.setText("OUT");
             score.setText("" + runs + "-" + wick);
             db1.setScores(match[flagBat], (String) score.getText());
             if (strike == 0) {
                 rball[row][col + 1] += 1;
                 db1.updateCurrGame("batter_ball", rball[row][col + 1]);
                     db1.updateBalls(db1.getCurrPlayerName(match[flagBat], b), rball[row][col + 1]);
                     db1.updateRuns(db1.getCurrPlayerName(match[flagBat], b), rball[row][col]);
                     db1.updateOut(db1.getCurrPlayerName(match[flagBat], b));
                     CelebrationAnimations.showBatOutDialog(MainActivity.this,db1.getCurrPlayerName(match[flagBat], b), rball[row][col], rball[row][col + 1], b6, b4);
                 rball[row][col] = 0;cfb=0;chb=0;
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
                 db1.updateBalls(db1.getCurrPlayerName(match[flagBat],r),rball[row + 1][col + 1]);
                     db1.updateRuns(db1.getCurrPlayerName(match[flagBat], r), rball[row + 1][col]);
                     db1.updateOut(db1.getCurrPlayerName(match[flagBat], r));
                     CelebrationAnimations.showBatOutDialog(MainActivity.this,db1.getCurrPlayerName(match[flagBat], r), rball[row + 1][col], rball[row + 1][col + 1], r6, r4);
               rball[row + 1][col] = 0;cfr=0;
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
         } else if (s == 0) {
             runs = runs + 0;
             //CelebrationAnimations.ShowThat(MainActivity.this);
             db1.updateCurrGame("runs", runs);
             run = 0;
             db1.updateCurrGame("run", run);
             //updating the dots of the bowler
             bowl_dots = db1.getBowlStats("bmaiden", (String) bowler.getText()) + 1;
             db1.updateBowlStats("bmaiden", (String) bowler.getText(), 1);

             if (strike == 0) {
                 rball[row][col + 1] += 1;
                 db1.updateCurrGame("batter_ball", rball[row][col + 1]);
                 db1.updateBalls(db1.getCurrPlayerName(match[flagBat], b), rball[row][col + 1]);
             } else {
                 rball[row + 1][col + 1] += 1;
                 db1.updateCurrGame("runner_ball", rball[row + 1][col + 1]);
                 db1.updateBalls(db1.getCurrPlayerName(match[flagBat], r), rball[row + 1][col + 1]);
             }
         } else {
             run = Math.abs(s - n);
             if (n == 7 && s == 2) {
                 run = 4;
             }
             if (s == 5 && n >= 5) {
                 run = 0;
             }
             if (s == 5 && n < 5) {
                 run = 1;
             }
             db1.updateCurrGame("run", run);
             if (run == 5 || run == 6) {
                 run = 6;
                 Log.d("SIXES_STRIKE::", "" + strike);
                 if (strike == 0) {
                     b6 += 1;
                     Log.d("BAT_SIXES::", "" + b6);
                     db1.updateCurrGame("bat_six", b6);
                     db1.updateSixes(db1.getCurrPlayerName(match[flagBat], b), b6);
                     CelebrationAnimations.ShowThatSix(MainActivity.this,db1.getCurrPlayerName(match[flagBat], b));
                 } else {
                     r6 += 1;
                     Log.d("RUN_SIXES::", "" + r6);
                     db1.updateCurrGame("run_six", r6);
                     db1.updateSixes(db1.getCurrPlayerName(match[flagBat], r), r6);
                     CelebrationAnimations.ShowThatSix(MainActivity.this,db1.getCurrPlayerName(match[flagBat], r));
                 }
             }
             if (run == 4) {

                 if (strike == 0) {
                     b4 += 1;
                     Log.d("BAT_FOURS::", "" + b4);
                     db1.updateCurrGame("bat_four", b4);
                     db1.updateFours(db1.getCurrPlayerName(match[flagBat], b), b4);
                     CelebrationAnimations.ShowThat(MainActivity.this,db1.getCurrPlayerName(match[flagBat], b));
                 } else {
                     r4 += 1;
                     Log.d("RUN_FOURS::", "" + r4);
                     db1.updateCurrGame("run_four", r4);
                     db1.updateFours(db1.getCurrPlayerName(match[flagBat], r), r4);
                     CelebrationAnimations.ShowThat(MainActivity.this,db1.getCurrPlayerName(match[flagBat], r));
                 }
             }
             runs = runs + run;
             db1.updateCurrGame("runs", runs);
             if (strike == 0) {
                 rball[row][col] += run;
                 db1.updateCurrGame("batter_run", rball[row][col]);
                 rball[row][col + 1] += 1;
                 //show fifty menu
                 if(rball[row][col] > 49)
                 {cfb=cfb+1;}
                 if(rball[row][col] > 99)
                 {chb=chb+1;}
                 if(cfb==1) {
                     float strRate=(float)rball[row][col]/rball[row][col + 1];strRate=strRate*100;
                     int rew[]=new int[3];
                     rew=db1.getMatchRew(db1.getCurrPlayerName(match[flagBat], b));
                     CelebrationAnimations.ShowThatFifty(MainActivity.this,db1.getCurrPlayerName(match[flagBat], b),rew[1],rball[row][col],rball[row][col + 1],b6, b4,strRate);
                 }
                 if(chb==1) {
                     float strRate=(float)rball[row][col]/rball[row][col + 1];strRate=strRate*100;
                     int rew[]=new int[3];
                     rew=db1.getMatchRew(db1.getCurrPlayerName(match[flagBat], b));
                     CelebrationAnimations.ShowThatHundred(MainActivity.this,db1.getCurrPlayerName(match[flagBat], b),rew[2],rball[row][col],rball[row][col + 1],b6, b4,strRate);
                 }
                 db1.updateCurrGame("batter_ball", rball[row][col + 1]);
                 db1.updateRuns(db1.getCurrPlayerName(match[flagBat], b), rball[row][col]);
                 db1.updateBalls(db1.getCurrPlayerName(match[flagBat], b), rball[row][col + 1]);

             } else {
                 rball[row + 1][col] += run;
                 db1.updateCurrGame("runner_run", rball[row + 1][col]);
                 rball[row + 1][col + 1] += 1;
                 //show that runner fifty
                 if(rball[row+1][col] > 49)
                 {cfr=cfr+1;}
                 if(rball[row+1][col] > 99)
                 {chr=chr+1;}
                 if(cfr==1) {
                     float strRate=(float)rball[row+1][col]/rball[row+1][col + 1];strRate=strRate*100;
                     int rew[]=new int[3];
                     rew=db1.getMatchRew(db1.getCurrPlayerName(match[flagBat], r));
                     CelebrationAnimations.ShowThatFifty(MainActivity.this,db1.getCurrPlayerName(match[flagBat], r),rew[1],rball[row+1][col],rball[row+1][col + 1],r6, r4,strRate);
                 }
                 if(chr==1) {
                     float strRate=(float)rball[row+1][col]/rball[row+1][col + 1];strRate=strRate*100;
                     int rew[]=new int[3];
                     rew=db1.getMatchRew(db1.getCurrPlayerName(match[flagBat], r));
                     CelebrationAnimations.ShowThatHundred(MainActivity.this,db1.getCurrPlayerName(match[flagBat], r),rew[2],rball[row+1][col],rball[row+1][col + 1],r6, r4,strRate);
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
             //updating bowler stats
             bowl_runs = db1.getBowlStats("bruns", (String) bowler.getText()) + run;
             b_runs.setText("" + bowl_runs);
             db1.updateBowlStats("bruns", (String) bowler.getText(), run);

             if (flagBat == 1) {
                 Log.d("IND VARIABLE IS: ", "" + ind);
                 Log.d("RUNS VARIABLE IS: ", "" + runs);
                 if (runs > ind) {
                     //finalB.setText("" + runs);
                     Log.d("LAST BALL ANDAUS WON:", "" + runs);
                     Toast.makeText(getApplicationContext(), match[1] + " WON!!", Toast.LENGTH_SHORT).show();
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
             //startActivity(new Intent(MainActivity.this, secondBat.class));
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
             db1.updateCurrGame("Balls", ball);
             bowl_bowls = bowl_bowls + 1;
             bowl_over = db1.getBowlStats("bovers", (String) bowler.getText());
             b_overs.setText("" + bowl_over + "." + bowl_bowls);
             //tballs=tballs+1;
             if (ball > 5) {
                 over = over + 1;
                 disableAll();
                 db1.updateCurrGame("Overs", over);
                 db1.updateBowlStats("bovers", (String) bowler.getText(), 1);
                 bowl_bowls = 0;
                 bowl_runs = 0;
                 bowl_wick = 0;
                 bowl_dots = 0;
                 ball = 0;
                 db1.updateCurrGame("Balls", ball);
                 if (strike == 0) {
                     strike = 1;
                     db1.updateCurrGame("Strike", strike);
                 } else {
                     strike = 0;
                     db1.updateCurrGame("Strike", strike);
                 }
                 switch(over) {
                     case 4:case 12:cbowler = cbowler + 2;break;
                     case 16:cbowler=0;break;
                     case 41:case 43:case 45: cbowler = db1.getBowlerNo(match[afb], ecoBowlrs[3]);break;
                     case 40:case 42:case 44: cbowler = db1.getBowlerNo(match[afb], ecoBowlrs[0]);break;
                     case 46:case 48: ecoBowlrs=db1.getQuotaBowlers(match[afb],(String)bowler.getText());cbowler = db1.getBowlerNo(match[afb], ecoBowlrs[0]);break;
                     case 47:case 49: ecoBowlrs=db1.getQuotaBowlers(match[afb],(String)bowler.getText());cbowler = db1.getBowlerNo(match[afb], ecoBowlrs[0]);break;
                 }
                 String bowlu = db1.getBowler(match[afb], cbowler);
                 Log.d("CBOWLER is", "" + cbowler);
                 if (bowlu.equalsIgnoreCase((String) bowler.getText())) {
                     bowler.setText(db1.getBowler(match[afb], cbowler + 1));
                     b_wick.setText("" + db1.getBowlStats("bwick", (String) bowler.getText()));
                     b_runs.setText("" + db1.getBowlStats("bruns", (String) bowler.getText()));
                     b_overs.setText("" + db1.getBowlStats("bovers", (String) bowler.getText()));
                 } else {
                     bowler.setText(db1.getBowler(match[afb], cbowler));
                     b_wick.setText("" + db1.getBowlStats("bwick", (String) bowler.getText()));
                     b_runs.setText("" + db1.getBowlStats("bruns", (String) bowler.getText()));
                     b_overs.setText("" + db1.getBowlStats("bovers", (String) bowler.getText()));
                 }
                 Log.d("FlagBat in over is:", "" + flagBat);
                 activateAll();
             }
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
             if (strike == 0)
                 equation.setText("   *" + db1.getCurrPlayerName(match[flagBat], b) + " " + rball[row][col] + "(" + rball[row][col + 1] + ")" + "\t\t" + db1.getCurrPlayerName(match[flagBat], r) + " " + rball[row + 1][col] + "(" + rball[row + 1][col + 1] + ")\n\n" + eq);
             else
                 equation.setText("   " + db1.getCurrPlayerName(match[flagBat], b) + " " + rball[row][col] + "(" + rball[row][col + 1] + ")" + "\t\t" + db1.getCurrPlayerName(match[flagBat], r) + " " + rball[row + 1][col] + "(" + rball[row + 1][col + 1] + ")*\n\n" + eq);
          }
     else
     {
         Random rand = new Random();
         n = rand.nextInt(6) + 0;
         bat.setText("" + s);
         bowl.setText("" + n);
         if (s == n && s != 5 && n!=0) {

             wick = wick + 1;
             db1.updateCurrGame("Wickets", wick);
             bowl_wick = db1.getBowlStats("bwick", (String) bowler.getText()) + 1;
             b_wick.setText("" + bowl_wick);//For saving the gaem
             db1.updateBowlStats("bwick", (String) bowler.getText(), 1);
             //result.setText("OUT");
             score.setText("" + runs + "-" + wick);
             db1.setScores(match[flagBat], (String) score.getText());
             if (strike == 0) {
                 rball[row][col + 1] += 1;
                 db1.updateCurrGame("batter_ball", rball[row][col + 1]);
                     db1.updateBalls(db1.getCurrPlayerName(match[flagBat], b), rball[row][col + 1]);
                     db1.updateRuns(db1.getCurrPlayerName(match[flagBat], b), rball[row][col]);
                     db1.updateOut(db1.getCurrPlayerName(match[flagBat], b));
                     CelebrationAnimations.showBatOutDialog(MainActivity.this,db1.getCurrPlayerName(match[flagBat], b), rball[row][col], rball[row][col + 1], b6, b4);
                 // batScore.setText(""+rball[row][col]+"("+ ++rball[row][col+1]+")  ");
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
                     db1.updateBalls(db1.getCurrPlayerName(match[flagBat], r), rball[row + 1][col + 1]);
                     db1.updateRuns(db1.getCurrPlayerName(match[flagBat], r), rball[row + 1][col]);
                     db1.updateOut(db1.getCurrPlayerName(match[flagBat], r));
                 CelebrationAnimations.showBatOutDialog(MainActivity.this,db1.getCurrPlayerName(match[flagBat], r), rball[row + 1][col], rball[row + 1][col + 1], r6, r4);
                 rball[row + 1][col] = 0;
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
         } else if (s == 0 || n==0) {
             runs = runs + 0;
             if(s==5)
                 freq5++;
             if(freq5==4){disableThat(5);}
             if(s==6)
                 freq6++;
             if(freq6==2){disableThat(6);}
             db1.updateCurrGame("runs", runs);
             run = 0;
             db1.updateCurrGame("run", run);
             //updating the dots of the bowler
             bowl_dots = db1.getBowlStats("bmaiden", (String) bowler.getText()) + 1;
             db1.updateBowlStats("bmaiden", (String) bowler.getText(), 1);

             if (strike == 0) {
                 rball[row][col + 1] += 1;
                 db1.updateCurrGame("batter_ball", rball[row][col + 1]);
                 db1.updateBalls(db1.getCurrPlayerName(match[flagBat], b), rball[row][col + 1]);
             } else {
                 rball[row + 1][col + 1] += 1;
                 db1.updateCurrGame("runner_ball", rball[row + 1][col + 1]);
                 db1.updateBalls(db1.getCurrPlayerName(match[flagBat], r), rball[row + 1][col + 1]);
              }
         } else {
             run = Math.abs(s - n);
             if (n == 7 && s == 2) {
                 run = 4;
             }
             if (s == 5 && n >= 4) {
                 run = 0;freq5++;
             }
             if (s == 5 && n < 4) {
                 run = 1;freq5++;
             }
             if(s==6)
                     freq6++;
                 if(freq6==2){disableThat(6);}
             if(freq5==4){disableThat(5);}
             db1.updateCurrGame("run", run);
             if (run == 5 || run == 6) {
                 run = 6;
                 {
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
                 }}
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
             if (strike == 0) {
                 rball[row][col] += run;
                 db1.updateCurrGame("batter_run", rball[row][col]);
                 rball[row][col + 1] += 1;
                 db1.updateCurrGame("batter_ball", rball[row][col + 1]);
                 db1.updateRuns(db1.getCurrPlayerName(match[flagBat], b), rball[row][col]);
                 db1.updateBalls(db1.getCurrPlayerName(match[flagBat], b), rball[row][col + 1]);
             } else {
                 rball[row + 1][col] += run;
                 db1.updateCurrGame("runner_run", rball[row + 1][col]);
                 rball[row + 1][col + 1] += 1;
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
             //updating bowler stats
             bowl_runs = db1.getBowlStats("bruns", (String) bowler.getText()) + run;
             b_runs.setText("" + bowl_runs);
             db1.updateBowlStats("bruns", (String) bowler.getText(), run);

             if (flagBat == 1) {
                 Log.d("IND VARIABLE IS: ", "" + ind);
                 Log.d("RUNS VARIABLE IS: ", "" + runs);
                 if (runs > ind) {
                     //finalB.setText("" + runs);
                     Log.d("LAST BALL ANDAUS WON:", "" + runs);
                     Toast.makeText(getApplicationContext(), match[1] + " WON!!", Toast.LENGTH_SHORT).show();
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
             //startActivity(new Intent(MainActivity.this, secondBat.class));
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
         }
         //code for updating overs
         if (over2 == 1) {
             over2 = 0;
             db1.updateCurrGame("over2", over2);
         } else {
             ball = ball + 1;
             db1.updateCurrGame("Balls", ball);
             bowl_bowls = bowl_bowls + 1;
             bowl_over = db1.getBowlStats("bovers", (String) bowler.getText());
             b_overs.setText("" + bowl_over + "." + bowl_bowls);
             //tballs=tballs+1;
             if (ball > 5) {
                 over = over + 1;
                 disableAll();
                 db1.updateCurrGame("Overs", over);
                 db1.updateBowlStats("bovers", (String) bowler.getText(), 1);
                 bowl_bowls = 0;
                 bowl_runs = 0;
                 bowl_wick = 0;
                 bowl_dots = 0;
                 ball = 0;
                 freq6=0;
                 freq5=0;
                 enableBowls();
                 db1.updateCurrGame("Balls", ball);
                 if (strike == 0) {
                     strike = 1;
                     db1.updateCurrGame("Strike", strike);
                 } else {
                     strike = 0;
                     db1.updateCurrGame("Strike", strike);
                 }

                 switch(over)
                 {
                     case 8:case 20: cbowler = cbowler + 2; break;
                     case 30: ecoBowlrs=db1.getEconomicBowlers(match[afb]);
                              cbowler=db1.getBowlerNo(match[afb],ecoBowlrs[1]);break;
                     case 31:case 33:case 35:case 37:cbowler = db1.getBowlerNo(match[afb], ecoBowlrs[2]);break;
                     case 32:case 34:case 36: cbowler=db1.getBowlerNo(match[afb],ecoBowlrs[1]);break;
                     case 38:case 40: cbowler = db1.getBowlerNo(match[afb], ecoBowlrs[3]);break;
                     case 39:case 41: cbowler = db1.getBowlerNo(match[afb], ecoBowlrs[0]);break;
                 }
                String bowlu = db1.getBowler(match[afb], cbowler);
                 Log.d("CBOWLER is", "" + cbowler);
                 if (bowlu.equalsIgnoreCase((String) bowler.getText())) {
                     if(over==30||over==32||over==34||over==36)
                     {
                        cbowler=db1.getBowlerNo(match[afb], ecoBowlrs[2]);
                        bowler.setText(db1.getBowler(match[afb], cbowler));
                     }
                     else if(over==31||over==33||over==35||over==37)
                     {
                         cbowler=db1.getBowlerNo(match[afb], ecoBowlrs[1]);
                         bowler.setText(db1.getBowler(match[afb], cbowler));
                     }
                     else{
                         bowler.setText(db1.getBowler(match[afb], cbowler+1));
                     }
                 }
                 else{
                         bowler.setText(db1.getBowler(match[afb], cbowler));
                 }
                 b_wick.setText("" + db1.getBowlStats("bwick", (String) bowler.getText()));
                 b_runs.setText("" + db1.getBowlStats("bruns", (String) bowler.getText()));
                 b_overs.setText("" + db1.getBowlStats("bovers", (String) bowler.getText()));
                 Log.d("FlagBat in over is:", "" + flagBat);
                 activateAll();
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
         if (strike == 0)
                 equation.setText("   *" + db1.getCurrPlayerName(match[flagBat], b) + " " + rball[row][col] + "(" + rball[row][col + 1] + ")" + "\t\t" + db1.getCurrPlayerName(match[flagBat], r) + " " + rball[row + 1][col] + "(" + rball[row + 1][col + 1] + ")\n\n" + eq);
         else
                 equation.setText("   " + db1.getCurrPlayerName(match[flagBat], b) + " " + rball[row][col] + "(" + rball[row][col + 1] + ")" + "\t\t" + db1.getCurrPlayerName(match[flagBat], r) + " " + rball[row + 1][col] + "(" + rball[row + 1][col + 1] + ")*\n\n" + eq);
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
                dialog = new Dialog(MainActivity.this);
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
        final Dialog dialog = new Dialog(MainActivity.this);
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
        Log.d("IND VARIABLE IS: ",""+MainActivity.ind);
        Log.d("RUNS VARIABLE IS: ",""+MainActivity.runs);
        Log.d("WICK VARIABLE IS: ",""+MainActivity.wick);
        Log.d("AUS VARIABLE IS: ",""+MainActivity.aus);
        Log.d("FlagBat VARIABLE IS: ",""+MainActivity.flagBat);
        Log.d("Over VARIABLE IS: ",""+MainActivity.over);
        Log.d("Ball VARIABLE IS: ",""+MainActivity.ball);
        MainActivity.ind2=MainActivity.runs+1;
        //scoreLine.setText("jaIt ko ilayao " + MainActivity.ind2 +" rna caaihyao");
        MainActivity.ind=MainActivity.runs;
        //db1.updateCurrGame("ind",ind);
        MainActivity.runs=0;row=0;col=0;strike=0;b=0;r=1;
        b4=0;r4=0;b6=0;r6=0;
        rball[0][0]=0;rball[0][1]=0;rball[1][0]=0;rball[1][1]=0;
        MainActivity.wick=0;//db1.updateCurrGame("Wickets",MainActivity.wick);//flagBat=1;
        MainActivity.over=0;MainActivity.ball=0;MainActivity.runsLeft=MainActivity.ind+1;
        db1.resetCurrGame(MainActivity.runsLeft,MainActivity.maxOvers);

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
        cfb=0;chb=0;cfr=0;chr=0;
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
                score.setText(""+MainActivity.runs+"-"+MainActivity.wick);
                result.setText("TARGET "+ind2);
                //Log.d("IND VARIABLE IS: ",""+ind);
                activateAll();
                ImageView img=(ImageView)findViewById(R.id.imageView3);
                img.setImageResource(drid2);
                ImageView img2=(ImageView)findViewById(R.id.imageView4);
                img2.setImageResource(drid);
                startActivity(new Intent(MainActivity.this, Bowling.class));
                dialog.dismiss();  //australia dialog exits
            }
        });
        dialog.show();
    }
    void IndWins()
    {
        float rr1=db1.setRunRate(winner);
        float rr2=db1.setRunRate(looser);
        cbowler=0;
        nrr=rr1-rr2;
        nrr2=rr2-rr1;
        final Dialog dialog = new Dialog(MainActivity.this);
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

        cfb=0;chb=0;cfr=0;chr=0;
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
                        startActivity(new Intent(MainActivity.this, TourWinner.class));
                    }
                    else {
                        db1.updateNextRound(winner);
                        startActivity(new Intent(MainActivity.this, TournamentCentral.class));
                    }
                }
                else if(db1.getCurrData("aus")==2)
                {
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
                        startActivity(new Intent(MainActivity.this, RoundRobinCentral.class));
                    }
                    else if(db1.getTourMatchSno()==57)//final match
                    {
                        db1.updateRR(winner,db1.getTourMatchSno());
                        db1.updateRRFinals();
                        startActivity(new Intent(MainActivity.this, RoundRobinCentral.class));
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
                        startActivity(new Intent(MainActivity.this, RoundRobinCentral.class));
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
                        startActivity(new Intent(MainActivity.this, IPLCentral.class));
                    }
                    else if(db1.getTourMatchSno()==60)//final match
                    {
                        db1.updateIPL(winner,db1.getTourMatchSno(),looser);
                        db1.updateIPLFinals();
                        startActivity(new Intent(MainActivity.this, IPLCentral.class));
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
                        startActivity(new Intent(MainActivity.this, IPLCentral.class));
                    }
                }
                else
                {
                    startActivity(new Intent(MainActivity.this, TeamSelect.class));
                }
                dialog.dismiss();  //australia dialog exits
            }
        });
        dialog.show();
    }
    void AusWins()
    {
        float rr1=db1.setRunRate(winner);
        float rr2=db1.setRunRate(looser);
        cbowler=0;
        cfb=0;chb=0;cfr=0;chr=0;
        nrr=rr1-rr2;
        nrr2=rr2-rr1;
        final Dialog dialog = new Dialog(MainActivity.this);
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
                        startActivity(new Intent(MainActivity.this, TourWinner.class));
                    }
                    else {
                        db1.updateNextRound(winner);
                        startActivity(new Intent(MainActivity.this, TournamentCentral.class));
                    }
                }
                else if(db1.getCurrData("aus")==2)
                {
                    for(int i=1;i<12;i++)
                    {
                        db1.updatePlayersStat(match[0],i,"rr_stats");
                        db1.updatePlayersStat(match[1],i,"rr_stats");
                    }
                    //db1.updateRRMatches(winner);
                    if(db1.getTourMatchSno()==56)
                    {
                        //update for semi finals
                        db1.updateRR(winner,db1.getTourMatchSno());
                        db1.updateRRPointsTable(winner,looser,nrr,nrr2);
                        db1.updateRRSemiFinals();
                        startActivity(new Intent(MainActivity.this, RoundRobinCentral.class));
                    }
                    else if(db1.getTourMatchSno()==57)//final match
                    {
                        db1.updateRR(winner,db1.getTourMatchSno());
                        db1.updateRRFinals();
                        startActivity(new Intent(MainActivity.this, RoundRobinCentral.class));
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
                        startActivity(new Intent(MainActivity.this, RoundRobinCentral.class));
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
                        startActivity(new Intent(MainActivity.this, IPLCentral.class));
                    }
                    else if(db1.getTourMatchSno()==60)//final match
                    {
                        db1.updateIPL(winner,db1.getTourMatchSno(),looser);
                        db1.updateIPLFinals();
                        startActivity(new Intent(MainActivity.this, IPLCentral.class));
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
                        startActivity(new Intent(MainActivity.this, IPLCentral.class));
                    }
                }
                else
                {
                    startActivity(new Intent(MainActivity.this, TeamSelect.class));
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

}
