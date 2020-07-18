package com.example.asaram.smallGame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;

public class IPLStats extends AppCompatActivity {
    DatabaseHandler db1;
    static TextView b1,stat1,stat2,head1,head2,mainHead;
    public Button b4,b7,next,prev;
    static ImageView i1;
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
        /*head1.setText("Matches");
        head2.setText("Runs");*/
        //p10 p20  p30 p40  p50 p60  p70 p80
        int j=1,k=1,kk=0;
        while(j<9)
        {
            String tvID = "p" + j+""+kk;
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            head1=(TextView)findViewById(resID);
            head1.setText("Matches");
            j++;
            tvID = "p" + j+""+kk;
            resID = getResources().getIdentifier(tvID, "id", getPackageName());
            head1=(TextView)findViewById(resID);
            head1.setText("Runs");
            j++;
        }
        mainHead.setText("Most Runs");
        TextView tv,tv1,tv2;
        // teamName.setText(match[0]);
        int ii=4,i=0,row=1,col=1;
        String data[]=new String[4];
        while(i<4) {

            data = db1.getPlayersStatsBat(i,"ipl_stats");
            String tvID = "im" + i;
            String iv = "im" + ii;
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            int imID= getResources().getIdentifier(iv, "id", getPackageName());
            Log.d("PLAYERS TEXT::",tvID);
            i1=(ImageView)findViewById(resID);
            String conv_pname=PlayersFaces.convertPlayer(data[0]);
            Log.d("CONVERTED:",conv_pname);
            String filepath= Environment.getExternalStorageDirectory()+"/"+"HomeCric/Players/"+conv_pname+".png";
            Bitmap bitmap = BitmapFactory.decodeFile(filepath);
            i1.setImageBitmap(bitmap);
            //int resID3=getResources().getIdentifier(conv_pname, "drawable", getPackageName());
            //i1.setImageResource(resID3);
            String tvID2 = data[1].toLowerCase()+"_logos";
            int resID2 = getResources().getIdentifier(tvID2, "drawable", getPackageName());
            i1=(ImageView)findViewById(imID);
            i1.setImageResource(resID2);
            //p11 p12 p21  p31 p32 p41  p51 p52 p61  p71 p72 p81
            tvID = "p" + row+""+col;
            resID = getResources().getIdentifier(tvID, "id", getPackageName());
            stat2=(TextView)findViewById(resID);
            stat2.setText(data[2]);
            col=col+1;
            tvID = "p" + row+""+col;
            resID = getResources().getIdentifier(tvID, "id", getPackageName());
            stat2=(TextView)findViewById(resID);
            stat2.setText(data[0]);
            row=row+1;col=1;
            tvID = "p" + row+""+col;
            resID = getResources().getIdentifier(tvID, "id", getPackageName());
            stat2=(TextView)findViewById(resID);
            stat2.setText(data[3]);
            i++;ii++;row++;
        }
    }
    public void setStatsBowl()
    {
        int j=1,k=1,kk=0;
        while(j<9)
        {
            String tvID = "p" + j+""+kk;
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            head1=(TextView)findViewById(resID);
            head1.setText("Dots");
            j++;
            tvID = "p" + j+""+kk;
            resID = getResources().getIdentifier(tvID, "id", getPackageName());
            head1=(TextView)findViewById(resID);
            head1.setText("Wickets");
            j++;
        }
        mainHead.setText("Most Wickets");
        TextView tv,tv1,tv2;
        int ii=4,i=0,row=1,col=1;
        String data[]=new String[4];
        while(i<4) {

            data = db1.getPlayersStatsBowl(i,"ipl_stats");
            String tvID = "im" + i;
            String iv = "im" + ii;
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            int imID= getResources().getIdentifier(iv, "id", getPackageName());
            Log.d("PLAYERS TEXT::",tvID);
            i1=(ImageView)findViewById(resID);
            String conv_pname=PlayersFaces.convertPlayer(data[0]);
            Log.d("CONVERTED:",conv_pname);
            String filepath= Environment.getExternalStorageDirectory()+"/"+"HomeCric/Players/"+conv_pname+".png";
            Bitmap bitmap = BitmapFactory.decodeFile(filepath);
            i1.setImageBitmap(bitmap);
            // int resID3=getResources().getIdentifier(conv_pname, "drawable", getPackageName());
            //i1.setImageResource(resID3);
            String tvID2 = data[1].toLowerCase()+"_logos";
            int resID2 = getResources().getIdentifier(tvID2, "drawable", getPackageName());
            i1=(ImageView)findViewById(imID);
            i1.setImageResource(resID2);
            //p11 p12 p21  p31 p32 p41  p51 p52 p61  p71 p72 p81
            tvID = "p" + row+""+col;
            resID = getResources().getIdentifier(tvID, "id", getPackageName());
            stat2=(TextView)findViewById(resID);
            stat2.setText(data[2]);
            col=col+1;
            tvID = "p" + row+""+col;
            resID = getResources().getIdentifier(tvID, "id", getPackageName());
            stat2=(TextView)findViewById(resID);
            stat2.setText(data[0]);
            row=row+1;col=1;
            tvID = "p" + row+""+col;
            resID = getResources().getIdentifier(tvID, "id", getPackageName());
            stat2=(TextView)findViewById(resID);
            stat2.setText(data[3]);
            i++;ii++;row++;
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
        mainHead.setText("Most Fifties");
        int j=1,k=1,kk=0;
        while(j<9)
        {
            String tvID = "p" + j+""+kk;
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            head1=(TextView)findViewById(resID);
            head1.setText("Matches");
            j++;
            tvID = "p" + j+""+kk;
            resID = getResources().getIdentifier(tvID, "id", getPackageName());
            head1=(TextView)findViewById(resID);
            head1.setText("Fifties");
            j++;
        }
        TextView tv,tv1,tv2;
        // teamName.setText(match[0]);
        int ii=4,i=0,row=1,col=1;
        String data[]=new String[4];
        while(i<4) {
            data = db1.getPlayersStatsBat50s(i,"ipl_stats");
            String tvID = "im" + i;
            String iv = "im" + ii;
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            int imID= getResources().getIdentifier(iv, "id", getPackageName());
            Log.d("PLAYERS TEXT::",tvID);
            i1=(ImageView)findViewById(resID);
            String conv_pname=PlayersFaces.convertPlayer(data[0]);
            Log.d("CONVERTED:",conv_pname);
            String filepath= Environment.getExternalStorageDirectory()+"/"+"HomeCric/Players/"+conv_pname+".png";
            Bitmap bitmap = BitmapFactory.decodeFile(filepath);
            i1.setImageBitmap(bitmap);
            //int resID3=getResources().getIdentifier(conv_pname, "drawable", getPackageName());
            //i1.setImageResource(resID3);
            String tvID2 = data[1].toLowerCase()+"_logos";
            int resID2 = getResources().getIdentifier(tvID2, "drawable", getPackageName());
            i1=(ImageView)findViewById(imID);
            i1.setImageResource(resID2);
            //p11 p12 p21  p31 p32 p41  p51 p52 p61  p71 p72 p81
            tvID = "p" + row+""+col;
            resID = getResources().getIdentifier(tvID, "id", getPackageName());
            stat2=(TextView)findViewById(resID);
            stat2.setText(data[2]);
            col=col+1;
            tvID = "p" + row+""+col;
            resID = getResources().getIdentifier(tvID, "id", getPackageName());
            stat2=(TextView)findViewById(resID);
            stat2.setText(data[0]);
            row=row+1;col=1;
            tvID = "p" + row+""+col;
            resID = getResources().getIdentifier(tvID, "id", getPackageName());
            stat2=(TextView)findViewById(resID);
            stat2.setText(data[3]);
            i++;ii++;row++;
        }
    }
    public void setStatsBowlEco()
    {
        mainHead.setText("Economic Bowler");
        int j=1,k=1,kk=0;
        while(j<9)
        {
            String tvID = "p" + j+""+kk;
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            head1=(TextView)findViewById(resID);
            head1.setText("Wickets");
            j++;
            tvID = "p" + j+""+kk;
            resID = getResources().getIdentifier(tvID, "id", getPackageName());
            head1=(TextView)findViewById(resID);
            head1.setText("Economy");
            j++;
        }
        TextView tv,tv1,tv2;
        // teamName.setText(match[0]);
        int ii=4,i=0,row=1,col=1;
        String data[]=new String[4];
        while(i<4) {
            data = db1.getPlayersStatsEcoBowl(i,"ipl_stats");
            String tvID = "im" + i;
            String iv = "im" + ii;
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            int imID= getResources().getIdentifier(iv, "id", getPackageName());
            Log.d("PLAYERS TEXT::",tvID);
            i1=(ImageView)findViewById(resID);
            String conv_pname=PlayersFaces.convertPlayer(data[0]);
            Log.d("CONVERTED:",conv_pname);
            String filepath= Environment.getExternalStorageDirectory()+"/"+"HomeCric/Players/"+conv_pname+".png";
            Bitmap bitmap = BitmapFactory.decodeFile(filepath);
            i1.setImageBitmap(bitmap);
            //int resID3=getResources().getIdentifier(conv_pname, "drawable", getPackageName());
            //i1.setImageResource(resID3);
            String tvID2 = data[1].toLowerCase()+"_logos";
            int resID2 = getResources().getIdentifier(tvID2, "drawable", getPackageName());
            i1=(ImageView)findViewById(imID);
            i1.setImageResource(resID2);
            //p11 p12 p21  p31 p32 p41  p51 p52 p61  p71 p72 p81
            tvID = "p" + row+""+col;
            resID = getResources().getIdentifier(tvID, "id", getPackageName());
            stat2=(TextView)findViewById(resID);
            stat2.setText(data[2]);
            col=col+1;
            tvID = "p" + row+""+col;
            resID = getResources().getIdentifier(tvID, "id", getPackageName());
            stat2=(TextView)findViewById(resID);
            stat2.setText(data[0]);
            row=row+1;col=1;
            tvID = "p" + row+""+col;
            resID = getResources().getIdentifier(tvID, "id", getPackageName());
            stat2=(TextView)findViewById(resID);
            stat2.setText(data[3]);
            i++;ii++;row++;
        }
}
}

