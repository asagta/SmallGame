package com.example.asaram.smallGame;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TournamentCentral extends AppCompatActivity {
    DatabaseHandler db1;
    private TextView tv;
    public Button b5;
    public static String tour_flag="N";
    //public List<String> teams;
    private Spinner add_desc, add_desc2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tournament);
        DatabaseHelper dbHelper = new DatabaseHelper(this, getFilesDir().getAbsolutePath());
        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.d("ADD_EXPENSES:", e.getMessage());
        }
        db1 = new DatabaseHandler(this, getFilesDir().getAbsolutePath());

        loadSpinnerData();
        startMatch();
        //setOnClicktoButton();
        //setOnClicktoResume();
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
    public void startMatch()
    {
        b5=(Button) findViewById(R.id.button5);
        //add_desc.setOnItemSelectedListener();
        b5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d("CLicked the button: ", "Yes its not null");

                String[] values=new String[2];
                db1.setTourMatchSno(1);//increment the match Sno by 1
                values=db1.getTourMatch(db1.getTourMatchSno());
                Log.d("CURRENT-MATCH::",values[0]+" "+values[1]);
                tour_flag="Y";
                db1.insertCurrMatch(values[0],values[1],tour_flag,20);


                startActivity(new Intent(TournamentCentral.this, PlayersSelect.class));
            }});

        //b1.setText("GoTHere");

    }

}


