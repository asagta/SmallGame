package com.example.asaram.smallGame;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class TeamSelect extends AppCompatActivity {
    DatabaseHandler db1;
    Intent music;
    static int t,vw,vh;
    private TextView b1,t2;
    public Button b4,b7,b8,b9,b25;
    public static VideoView mVideoView;
    public Button b70;
    //public static int tms[];
    private Spinner add_desc,add_desc2;
    private boolean mIsBound = false;
    private MusicService mServ,ms2;
    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
            Log.d("SERVICE_STATUS::","Connected");
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.select_team);
        //video content code in the background

        mVideoView = (VideoView) findViewById(R.id.vv);
        startVideo(mVideoView);

        //doBindService();
        music = new Intent();
        music.setClass(this,MusicService.class);
        //startService(music);
        DatabaseHelper dbHelper = new DatabaseHelper(this, getFilesDir().getAbsolutePath());

        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.d("ADD_EXPENSES:", e.getMessage());
        }
        db1 = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
       // loadSpinnerData();
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "zippo.ttf");
        t2=(TextView)findViewById(R.id.textV10);
        t2.setTypeface(custom_font);
        blink();
        //setOnClicktoButton();
        setOnClicktoButton2();
        setOnClicktoButton4();
        //setOnClicktoResume();
        startTournament();
        /*startRoundRobin();
        showStats();
        ResumeTournament();
        ResumeRR();*/
        clickIndianPremier();
        clickMixedCup();
        //ResumeIpl();
    }
    public void startVideo(final VideoView mVideoView)
    {
        int ar[]=new int[9];
        ar[0]=R.raw.bgvid1;ar[1]=R.raw.bgvid2;ar[2]=R.raw.bgvid3;
        ar[3]=R.raw.bgvid4;ar[4]=R.raw.bgvid5;ar[5]=R.raw.bgvid6;
        ar[6]=R.raw.bgvid7;ar[7]=R.raw.bgvid8;ar[8]=R.raw.bgvid9;
        Random rand = new Random();
        int n = rand.nextInt(9);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+ar[n]);
        mVideoView.setVideoURI(uri);
        mVideoView.start();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                vw = mediaPlayer.getVideoWidth();
                vh = mediaPlayer.getVideoHeight();
                float videoProportion = (float) vw / (float) vh;
                DisplayMetrics mDisplayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
                int screenWidth = mDisplayMetrics.widthPixels;
                int screenHeight = mDisplayMetrics.heightPixels;
                float screenProportion = (float) screenWidth / (float) screenHeight;
                android.view.ViewGroup.LayoutParams lp = mVideoView.getLayoutParams();
                if (videoProportion > screenProportion) {
                    lp.width = screenWidth;
                    lp.height = (int) ((float) screenWidth / videoProportion);
                } else {
                    lp.width = (int) (videoProportion * (float) screenHeight);
                    lp.height = screenHeight;
                }
                mVideoView.setLayoutParams(lp);
                mediaPlayer.setLooping(true);
                /*PlaybackParams playbackParams = new PlaybackParams();
                playbackParams.setSpeed(2);
                playbackParams.setPitch(1);
                playbackParams.setAudioFallbackMode(
                        PlaybackParams.AUDIO_FALLBACK_MODE_DEFAULT);
                mediaPlayer.setPlaybackParams(playbackParams);*/
            }
        });
    }

    /*private void loadSpinnerData() {
        // database handler
        // Spinner Drop down elements
        List<String> lables = db1.getAllTeams();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
       // add_desc=(Spinner)findViewById(R.id.spinner);
//        add_desc.setAdapter(dataAdapter);
       // add_desc2=(Spinner)findViewById(R.id.spinner2);
  //      add_desc2.setAdapter(dataAdapter);
    }*/
    /*void setOnClicktoButton()
    {
        b1=(TextView) findViewById(R.id.button);
        //add_desc.setOnItemSelectedListener();
        b1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d("CLicked the button: ", "Yes its not null");
                String value1=add_desc.getSelectedItem().toString();
                String value2=add_desc2.getSelectedItem().toString();
                Log.d("TEAMS:: ", value1+"  "+value2);
                if(value1.isEmpty() || value2.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"PLEASE SELECT TEAMS TO MATCH",Toast.LENGTH_SHORT).show();
                }
                else if(value1.equalsIgnoreCase(value2))
                {Toast.makeText(getApplicationContext(),"PLEASE SELECT 2 Different TEAMS TO MATCH",Toast.LENGTH_SHORT).show();}
                else{
                    db1.insertCurrMatch(value1,value2,"N",20);
                    b1.setText("GoTHere");
                    startActivity(new Intent(TeamSelect.this, PlayersSelect.class));
                }

            }});
    }*/
    void clickMixedCup()
    {
        Button bz4=(Button) findViewById(R.id.button7);
        //add_desc.setOnItemSelectedListener();
        bz4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d("CLicked the button: ", "Yes its not null");
                setContentView(R.layout.second_wc);
                mVideoView = (VideoView) findViewById(R.id.vv);
                startVideo(mVideoView);
                startTournament();
                ResumeTournament();
            }});
    }
    void clickIndianPremier()
    {
        Button bz4=(Button) findViewById(R.id.but5);
        //add_desc.setOnItemSelectedListener();
        bz4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d("CLicked the button: ", "Yes its not null");
                setContentView(R.layout.second_wc);
                mVideoView = (VideoView) findViewById(R.id.vv);
                startVideo(mVideoView);
                startIpl();
                ResumeIpl();
            }});
    }
    void setOnClicktoButton2()
    {
        Button bz4=(Button) findViewById(R.id.b7);
        //add_desc.setOnItemSelectedListener();
        bz4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d("CLicked the button: ", "Yes its not null");
                startActivity(new Intent(TeamSelect.this, QuickPlay.class));
                //startActivity(new Intent(TeamSelect.this, TourWinner.class));
            }});
    }

    void setOnClicktoButton4()
    {
        Button bz4=(Button) findViewById(R.id.butt4);
        //add_desc.setOnItemSelectedListener();
        bz4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d("CLicked the button: ", "Yes its not null");
                startActivity(new Intent(TeamSelect.this, SquadMgmt.class));
                //startActivity(new Intent(TeamSelect.this, TourWinner.class));
            }});
    }
    /*
    void setOnClicktoResume()
    {
        b4=(Button) findViewById(R.id.button4);
        //add_desc.setOnItemSelectedListener();
        b4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d("CLicked the button: ", "Yes its not null");
                if(db1.getToss()==1)
                    startActivity(new Intent(TeamSelect.this, MainActivity.class));
                else
                {
                    Intent intent = new Intent(getBaseContext(), Bowling.class);
                    intent.putExtra("resume","yes");
                    startActivity(intent);
                }
                  //  startActivity(new Intent(TeamSelect.this, Bowling.class));
                //startActivity(new Intent(TeamSelect.this, TourWinner.class));
            }});
    }*/
    void startTournament()
    {
        b70=(Button) findViewById(R.id.button7);
        //add_desc.setOnItemSelectedListener();
        b70.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d("CLicked the button: ", "Yes its not null");
                db1.deletePrevTourMC();
                for(int i=1;i<64;i++)
                    db1.setTourMatchesMC(i);
                InitTourDataMC();
                db1.setMCStats();
                db1.setTourMatchSno(0);
                startActivity(new Intent(TeamSelect.this, MixedCupHome.class));
            }});
    }
    public void InitTourData() {
        // database handler
        // Spinner Drop down elements
        List<String> teams = db1.getAllTeams();
        int tms[]=new int[8];
        tms=randomNoReps(8);
        int i = 0,j=1;
        while (i < 8) {
            /*String tvID = "t" + i;
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            tv = ((TextView) findViewById(resID));
            tv.setText(""+teams.get(tms[i]).toString());*/
            db1.addTourTeams(teams.get(tms[i]).toString());
            if(i%2==0)
                db1.addtourMatches("T1",teams.get(tms[i]).toString(),j);
            else {
                db1.addtourMatches("T2", teams.get(tms[i]).toString(), j);
                j=j+1;
            }
            i++;
        }
        //db1.addtourMatches("T1",teams.get(tms[0]).toString(),1);
    }
    public int[] randomNoReps(int a) {
        int tmp, n;
        int temp[];
        temp = new int[a];
        Random rand = new Random();
        n = 0;
        tmp = n;
        temp[0] = n;
        int i = 1, j = 0;
        while (i < a) {
            n = rand.nextInt(a-1) + 1;
            j = 0;
            // System.out.println("THE LENGTH OF::"+temp.length);
            while (j < temp.length) {
                if (temp[j] == n) {
                    n = -1;
                    break;
                }
                j++;
            }
            if (n == -1) continue;
            else {
                System.out.println(n);
                temp[i] = n;
                i++;
            }
        }
        return temp;
    }
    /*void showStats()
    {
//      Put in your Activity that CODE:
        b8=(Button) findViewById(R.id.b8);
        //add_desc.setOnItemSelectedListener();
        b8.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(TeamSelect.this, StatsBatting.class));

            }});
    }*/
    void ResumeTournament()
    {
        b9=(Button) findViewById(R.id.b9);
        //add_desc.setOnItemSelectedListener();
        b9.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d("CLicked the button: ", "Yes its not null");
                startActivity(new Intent(TeamSelect.this, MixedCupHome.class));
            }});
    }
    /*
    void ResumeRR()
    {
        b25=(Button) findViewById(R.id.button25);
        //add_desc.setOnItemSelectedListener();
        b25.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d("CLicked the button: ", "Yes its not null");
                startActivity(new Intent(TeamSelect.this, RoundRobinCentral.class));

            }});
    }
    void startRoundRobin()
    {
        b7=(Button) findViewById(R.id.button24);
        //add_desc.setOnItemSelectedListener();
        b7.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d("CLicked the button: ", "Yes its not null");
                db1.deletePrevTourRR();

                for(int i=1;i<60;i++)
                    db1.setTourMatchesRR(i);
                InitTourDataRR();
                db1.setRRStats();
                startActivity(new Intent(TeamSelect.this, RoundRobinCentral.class));
                db1.setTourMatchSno(0);
                //startActivity(new Intent(TeamSelect.this, TournamentCentral.class));

            }});
    }
*/
    public void InitTourDataMC() {
        List<String> teams = db1.getAllTeams();
        int tms[]=new int[45];
        int a=45;
        tms=randomNoReps(a);
        String teams1[]=new String[45];
        String teams2[]=new String[45];
        int i = 0,j=1,j1=0,jz=0;
        while(j1<45) {
            for (i = 0; i < 10; i++) {
                for (j = i + 1; j < 10; j++) {
                    teams1[j1] = "" + i + "" + j;
                    j1++;
                }
            }
            // j1++;
        }
        i=0;
        while(i<45)
        {
            Log.d("TMS::",""+teams1[i]);
            teams2[i]=teams1[tms[i]];
            i++;
        }
        i=0;
        while (i < 45) {
            Log.d("MC team::",""+teams2[i]);
            Log.d("MC_Team1&2",teams.get(Character.getNumericValue(teams2[i].charAt(0)))+" "+teams.get(Character.getNumericValue(teams2[i].charAt(1))));
            db1.addTourTeamsMC(teams.get(Character.getNumericValue(teams2[i].charAt(0))),teams.get(Character.getNumericValue(teams2[i].charAt(1))),i+1);
            i++;
        }
        i=0;
        while(i<10)
        {
            db1.setRankingsMCR1(db1.getTeamId(teams.get(i)));
            i++;
        }
        //db1.addtourMatches("T1",teams.get(tms[0]).toString(),1);
    }

    public void InitTourDataRR() {
        // database handler
        // Spinner Drop down elements
        List<String> teams = db1.getAllTeams();
        int tms[]=new int[56];
        int a=56;
        tms=randomNoReps(a);
        String teams1[]=new String[56];
        String teams2[]=new String[56];
        int i = 0,j=1,j1=0,jz=0;
        while(j1<56) {
            for (i = 0; i < 8; i++) {
                for (j = i + 1; j < 8; j++) {
                    teams1[j1] = "" + i + "" + j;
                    j1++;
                }
            }
            // j1++;
        }
        i=0;
        while(i<56)
        {
            Log.d("TMS::",""+teams1[i]);
            teams2[i]=teams1[tms[i]];
            i++;
        }
        i=0;
        while (i < 56) {
            Log.d("IPL team::",""+teams2[i]);
            Log.d("RR_Team1&2",teams.get(Character.getNumericValue(teams2[i].charAt(0)))+" "+teams.get(Character.getNumericValue(teams2[i].charAt(1))));
            db1.addTourTeamsRR(teams.get(Character.getNumericValue(teams2[i].charAt(0))),teams.get(Character.getNumericValue(teams2[i].charAt(1))),i+1);
            i++;
        }
        i=0;
        while(i<8)
        {
            db1.setRankings(db1.getTeamId(teams.get(i)));
            i++;
        }
        //db1.addtourMatches("T1",teams.get(tms[0]).toString(),1);
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        //mServ.pauseMusic();
        //doUnbindService();
        super.onPause();

    }
    private void blink(){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeToBlink = 500;
                //in milissegunds
                try{Thread.sleep(timeToBlink);}catch (Exception e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        //TextView txt = (TextView) findViewById(R.id.usage);
                        if(t==1){
                            t=2;
                            t2.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.red));
                        }else{
                            t=1;
                            t2.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
                        }
                        blink();
                    }
                });
            }
        }).start();
    }
    void startIpl()
    {
        b7=(Button) findViewById(R.id.but5);
        //add_desc.setOnItemSelectedListener(5);
        b7.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d("CLicked the button: ", "Yes its not null");
                db1.deletePrevTourIPL();

                for(int i=1;i<61;i++)
                    db1.setTourMatchesIPL(i);
                InitTourDataIPL();
                db1.setIPLStats();
                startActivity(new Intent(TeamSelect.this, IPLCentral.class));
                db1.setTourMatchSno(0);
                //startActivity(new Intent(TeamSelect.this, TournamentCentral.class));

            }});
    }
    void ResumeIpl()
    {
        b7=(Button) findViewById(R.id.b9);
        //add_desc.setOnItemSelectedListener(5);
        b7.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d("CLicked the button: ", "Yes its not null");
                startActivity(new Intent(TeamSelect.this, IPLCentral.class));
            }});
    }
    public void InitTourDataIPL() {
        // database handler
        // Spinner Drop down elements
        List<String> teams = db1.getAllTeamsIPL();
        int tms[]=new int[56];
        int a=56;
        tms=randomNoReps(a);
        String teams1[]=new String[56];
        String teams2[]=new String[56];
        int i = 0,j=1,j1=0,jz=0;
        while(j1<56) {
            for (i = 0; i < 8; i++) {
                for (j = i + 1; j < 8; j++) {
                    teams1[j1] = "" + i + "" + j;
                    j1++;
                }
            }
           // j1++;
        }
        i=0;
        while(i<56)
        {
            Log.d("TMS::",""+teams1[i]);
            teams2[i]=teams1[tms[i]];
            i++;
        }
        i=0;
        while (i < 56) {
            Log.d("IPL team::",""+teams2[i]);
            Log.d("RR_Team1&2",teams.get(Character.getNumericValue(teams2[i].charAt(0)))+" "+teams.get(Character.getNumericValue(teams2[i].charAt(1))));
            db1.addTourTeamsIPL(teams.get(Character.getNumericValue(teams2[i].charAt(0))),teams.get(Character.getNumericValue(teams2[i].charAt(1))),i+1);
            i++;
        }
        i=0;
        while(i<8)
        {
            db1.setRankingsIPL(db1.getFranchiseId(teams.get(i)));
            i++;
        }
        //db1.addtourMatches("T1",teams.get(tms[0]).toString(),1);
    }

 }
