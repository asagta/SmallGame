package com.example.asaram.smallGame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;

public class IPLStats extends AppCompatActivity {
    DatabaseHandler db1;
    static TextView b1,stat1,stat2,head1,head2,mainHead;
    public Button b4,b7,next,prev;
    //public static int tms[];
    private Spinner add_desc,add_desc2;
    static int z,btp,btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_bat);
        z=0;
        DatabaseHelper dbHelper = new DatabaseHelper(this, getFilesDir().getAbsolutePath());

        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.d("ADD_EXPENSES:", e.getMessage());
        }
        db1 = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
        stat1=(TextView)findViewById(R.id.vt1);
        prev=(Button)findViewById(R.id.vt0);
        next=(Button)findViewById(R.id.vt01);
        head1=(TextView)findViewById(R.id.vt23);
        head2=(TextView)findViewById(R.id.vt24);
        mainHead=(TextView)findViewById(R.id.vt1);
        String pre = "vt0";String ne = "vt01";
        btp=getResources().getIdentifier(pre, "id", getPackageName());
        btn=getResources().getIdentifier(ne, "id", getPackageName());
        setClickOnNext(prev);
        setClickOnNext(next);
        setStats();
    }
    public void setStats()
    {
        head1.setText("Matches");
        head2.setText("Runs");
        mainHead.setText("Most Runs");
        TextView tv,tv1,tv2;
        // teamName.setText(match[0]);
        int j=3,k=1,kk=0,ii=1,i=0;
        String data[]=new String[4];
        while(i<11) {
            if(k==5)
            {j=j+1;k=1;kk=0;ii=ii+1;i=i+1;}
            data = db1.getPlayersStatsBat(i,"ipl_stats");
            String tvID = "vt" + j + ""+k;
            Log.d("PLAYERS TEXT::",tvID);
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            tv = ((TextView)findViewById(resID));
            tv.setText(""+data[kk]);
            k=k+1;
            kk=kk+1;
        }
    }
    public void setStatsBowl()
    {
        head1.setText("Dots");
        head2.setText("Wickets");
        mainHead.setText("Most Wickets");
        TextView tv,tv1,tv2;
        // teamName.setText(match[0]);
        int j=3,k=1,kk=0,ii=1,i=0;
        String data[]=new String[4];
        while(i<11) {
            if(k==5)
            {j=j+1;k=1;kk=0;ii=ii+1;i=i+1;}
            data = db1.getPlayersStatsBowl(i,"ipl_stats");
            String tvID = "vt" + j + ""+k;
            Log.d("PLAYERS TEXT::",tvID);
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            tv = ((TextView)findViewById(resID));
            tv.setText(""+data[kk]);
            k=k+1;
            kk=kk+1;
        }
    }
    public void setClickOnNext(final Button t) {
        Log.d("INSIDE::", "TEXTVIEW");
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t.getId()==btn)
                   z++;
                else z--;
                switch(z) {
                    case 1:
                        setStatsBowl();
                        break;
                    case 2: setStatsBatAvg(); break;
                    case 3: setStatsBowlEco(); break;
                    case 4: z=0;setStats();break;
                    default:z=0;setStats();break;
                }

            }
        });
    }
    public void setStatsBatAvg()
    {
        head1.setText("Matches");
        head2.setText("Fifties");
        mainHead.setText("Most Fifties");
        TextView tv,tv1,tv2;
        // teamName.setText(match[0]);
        int j=3,k=1,kk=0,ii=1,i=0;
        String data[]=new String[4];
        while(i<11) {
            if(k==5)
            {j=j+1;k=1;kk=0;ii=ii+1;i=i+1;}
            data = db1.getPlayersStatsBat50s(i,"ipl_stats");
            String tvID = "vt" + j + ""+k;
            Log.d("PLAYERS TEXT::",tvID);
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            tv = ((TextView)findViewById(resID));
            tv.setText(""+data[kk]);
            k=k+1;
            kk=kk+1;
        }
    }
    public void setStatsBowlEco()
    {
        head1.setText("Wickets");
        head2.setText("Economy");
        mainHead.setText("Economic Bowler");
        TextView tv,tv1,tv2;
        // teamName.setText(match[0]);
        int j=3,k=1,kk=0,ii=1,i=0;
        String data[]=new String[4];
        while(i<11) {
            if(k==5)
            {j=j+1;k=1;kk=0;ii=ii+1;i=i+1;}
            data = db1.getPlayersStatsEcoBowl(i,"ipl_stats");
            String tvID = "vt" + j + ""+k;
            Log.d("PLAYERS TEXT::",tvID);
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            tv = ((TextView)findViewById(resID));
            tv.setText(""+data[kk]);
            k=k+1;
            kk=kk+1;
        }
    }
}

