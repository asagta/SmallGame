package com.example.asaram.smallGame;

/**
 * Created by Asaram on 24-02-2019.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.io.IOException;

public class QuickPlay extends AppCompatActivity {
    private ImageSwitcher swc1,swc2;
    Button btnNext,btnPrev,btnNext2,btnPrev2,bgo;
    DatabaseHandler db1;
    public TextView t1,t2;
    static int maxovers;

    // Array of Image IDs to Show In ImageSwitcher
    String imageIds[] = {"ind","eng","nzl","saf","aus","pak","ban","win","slk","afg"};
    int count = imageIds.length;
    // to keep current Index of ImageID array
    int currentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        DatabaseHelper dbHelper = new DatabaseHelper(this, getFilesDir().getAbsolutePath());
        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.d("ADD_EXPENSES:", e.getMessage());
        }
        db1 = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
        btnNext = (Button) findViewById(R.id.button28);
        btnPrev = (Button) findViewById(R.id.button29);
        btnNext2 = (Button) findViewById(R.id.button31);
        btnPrev2 = (Button) findViewById(R.id.button30);
        swc1 = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        swc2 = (ImageSwitcher) findViewById(R.id.imageSwitcher2);
        t1=(TextView)findViewById(R.id.textView10);
        t2=(TextView)findViewById(R.id.textView11);
        bgo=(Button)findViewById(R.id.button32);
        bgo.setEnabled(false);
        setTeams(btnNext,btnPrev,swc1,t1);setTeams(btnNext2,btnPrev2,swc2,t2);
        setGo();
        setOvers();
    }
  public void setOvers()
  {
     final TextView t20=(TextView)findViewById(R.id.t20);
     final TextView t50=(TextView)findViewById(R.id.t50);
      t20.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
           maxovers=20;
           bgo.setEnabled(true);
           t20.setBackgroundResource(R.color.grey);
              t50.setBackgroundResource(R.color.blue);
          }
      });
      t50.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
              maxovers=50;
              bgo.setEnabled(true);
              t50.setBackgroundResource(R.color.grey);
              t20.setBackgroundResource(R.color.blue);
          }
      });
  }
    public void setGo()
    {
        bgo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String value1=t1.getText().toString();
                String value2=t2.getText().toString();
                Log.d("TEAMS:: ", value1+"  "+value2);
                if(value1.isEmpty() || value2.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"PLEASE SELECT TEAMS TO MATCH",Toast.LENGTH_SHORT).show();
                }
                else if(value1.equalsIgnoreCase(value2))
                {Toast.makeText(getApplicationContext(),"PLEASE SELECT 2 Different TEAMS TO MATCH",Toast.LENGTH_SHORT).show();}
                else{
                    db1.insertCurrMatch(value1,value2,"N",maxovers);
                    startActivity(new Intent(QuickPlay.this, PlayersSelect.class));
                }
            }
        });
    }
public void setTeams(Button bn,Button bp,final ImageSwitcher is,final TextView t)
{

    is.setFactory(new ViewSwitcher.ViewFactory() {

        public View makeView() {
            // TODO Auto-generated method stub

            // Create a new ImageView and set it's properties
            ImageView imageView = new ImageView(getApplicationContext());
            // set Scale type of ImageView to Fit Center
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            // set the Height And Width of ImageView To FIll PARENT
            imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
            return imageView;
        }
    });
    int drid2=getResources().getIdentifier("ind_logos", "drawable", getPackageName());
    is.setImageResource(drid2);
    t.setText(db1.getTeamNameFromId(imageIds[0].toUpperCase()));
    // Declare in and out animations and load them using AnimationUtils class
    Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
    Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

    // set the animation type to ImageSwitcher
    is.setInAnimation(in);
    is.setOutAnimation(out);
    bn.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            // TODO Auto-generated method stub
            currentIndex++;
            //  Check If index reaches maximum then reset it
            if (currentIndex == count)
                currentIndex = 0;
            int drid=getResources().getIdentifier(imageIds[currentIndex]+"_logos", "drawable", getPackageName());
            is.setImageResource(drid);
            t.setText(db1.getTeamNameFromId(imageIds[currentIndex].toUpperCase()));
        }
    });
    bp.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            // TODO Auto-generated method stub
            currentIndex--;
            //  Check If index reaches minimum then reset it
            if (currentIndex == -2 || currentIndex == -1)
                currentIndex = 9;
            int drid=getResources().getIdentifier(imageIds[currentIndex]+"_logos", "drawable", getPackageName());
            is.setImageResource(drid);
            t.setText(db1.getTeamNameFromId(imageIds[currentIndex].toUpperCase()));
        }
    });
}
}
