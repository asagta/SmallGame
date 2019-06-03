package com.example.asaram.smallGame;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class TossTeams extends AppCompatActivity {
    DatabaseHandler db1;
    private TextView t1[];
    private TextView tv, pl1, pl2, match, runs, fifty, fuur, hs, srate, avg;
    private Button bt1, bt2;
    static int res;
    ImageView view;
    AnimationDrawable frameAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toss_first);
        DatabaseHelper dbHelper = new DatabaseHelper(this, getFilesDir().getAbsolutePath());
        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.d("ADD_EXPENSES:", e.getMessage());
        }
        db1 = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
        tv=(TextView)findViewById(R.id.textView3);
        tv.setText(db1.getCurrTeamName(0));
        setClickToHeads();
        setClickToTails();


// Setting animation_list.xml as the background of the image view


// Typecasting the Animation Drawable


    }

    public void setClickToHeads() {
        bt1 = (Button) findViewById(R.id.button9);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                res = 1;
                setContentView(R.layout.toss_teams);
                view = (ImageView) findViewById(R.id.imageView);
                view.setBackgroundResource(R.drawable.toss_list);
                frameAnimation = (AnimationDrawable) view.getBackground();
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = ""+frameAnimation.getNumberOfFrames();
                runner.execute(sleepTime);
            }
        });
    }

    public void setClickToTails() {
        bt2 = (Button) findViewById(R.id.button18);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                res = 2;
                setContentView(R.layout.toss_teams);
                view = (ImageView) findViewById(R.id.imageView);
                view.setBackgroundResource(R.drawable.toss_list);
                frameAnimation = (AnimationDrawable) view.getBackground();
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = ""+frameAnimation.getNumberOfFrames();
                runner.execute(sleepTime);
            }


        });
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;


        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping...");
            resp="Hell";// Calls onProgressUpdate()
            try {
                int time = Integer.parseInt("5")*1000;

                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            startActivity(new Intent(TossTeams.this, TossDecision.class));
        }


        @Override
        protected void onPreExecute() {
            setContentView(R.layout.toss_teams);
            res = 2;
            view = (ImageView) findViewById(R.id.imageView);
            view.setBackgroundResource(R.drawable.toss_list);
            frameAnimation = (AnimationDrawable) view.getBackground();
            frameAnimation.start();

        }


        @Override
        protected void onProgressUpdate(String... text) {
            Log.d("ANIMATION","EXECUTED");

        }
    }

}
