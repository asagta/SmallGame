package com.example.asaram.smallGame;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SquadMgmt extends AppCompatActivity {
    DatabaseHandler db1;
    public static TextView tv, mainHead, pl1, pl2;
    public static Button b5, prev, next, b26,vt0,vt01;
    public static String tour_flag = "N";
    static int res[];
    static int z,z1;
    static String[] tms,tms2;
    //public List<String> teams;
    static Spinner teamId, bowl;
    static EditText pname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.squad_mgmt);
        DatabaseHelper dbHelper = new DatabaseHelper(this, getFilesDir().getAbsolutePath());
        tms=new String[]{"IND","ENG","PAK","AUS","WIN","SAF","NZL","BAN","SLK","AFG"};
        tms2=new String[]{"INDIA","ENGLAND","PAKISTAN","AUSTRALIA","WEST INDIES","SOUTH AFRICA","NEW ZEALAND","BANGLADESH","SRI LANKA","AFGHANISTAN"};
        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.d("ADD_EXPENSES:", e.getMessage());
        }
        db1 = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
        showSquadOnly(0);
        res = new int[2];
        setClickonSwap();
        setClickonAddMore();
        showOtherTeams();
    }
public void showOtherTeams()
{
    next=(Button) findViewById(R.id.vt01);
    prev=(Button) findViewById(R.id.vt0);
    next.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
      z1=z1+1;
      Log.d("TOUCHED:",""+z1);
      if(z1==10) z1=0;
      showSquadOnly(z1);
        }
    });
}
    public void showSquadOnly(int i1) {
        int i = 0, j = 0, ii = +1;
        int tplay=db1.getTotalPlayers(tms[i1]);
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(270, 230);
        TableLayout table = (TableLayout) findViewById(R.id.tbl1);
        table.removeAllViews();
        TextView title=(TextView)findViewById(R.id.vt1);
        title.setText(""+tms2[i1]);
        TableRow[] row = new TableRow[tplay];
        TextView[] col = new TextView[tplay];
        String pl = new String();
        while (i < 16) {
            pl = db1.getSquadPlayer(i,tms[i1]);
            row[i] = new TableRow(SquadMgmt.this);
            params.setMargins(0, 5, 8, 8);
            row[i].setLayoutParams(params);
            col[i] = new TextView(SquadMgmt.this);
            col[i].setId(ii);
            col[i].setText(pl);
            col[i].setHeight(80);
            col[i].setWidth(380);
            col[i].setGravity(Gravity.CENTER);
            col[i].setTextSize(14);
            setOnClicktoButton(col[i]);
            col[i].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            row[i].addView(col[i]);
            table.addView(row[i]);
            i++;
            ii++;
            // row[k].addView(spacerColumn, new TableRow.LayoutParams(12, 12));
        }
        j = 16;
        i = 0;
        //getTotalSquadPerson-16
        //int tplay=db1.getTotalPlayers("IND");
        tplay=tplay-16;
        int jj = +18;
        table = (TableLayout) findViewById(R.id.tbl2);
        table.removeAllViews();
        while (i < tplay) {
            pl = db1.getSquadPlayer(j,tms[i1]);
            row[i] = new TableRow(SquadMgmt.this);
            params.setMargins(0, 5, 8, 8);
            row[i].setLayoutParams(params);
            col[i] = new TextView(SquadMgmt.this);
            col[i].setText(pl);
            col[i].setHeight(80);
            col[i].setWidth(380);
            col[i].setGravity(Gravity.CENTER);
            col[i].setTextSize(14);
            col[i].setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            col[i].setId(jj);
            setOnClicktoButton(col[i]);
            row[i].addView(col[i]);
            table.addView(row[i]);
            Log.d("JJ:", "" + jj);
            j++;
            i++;
            jj++;
            // row[k].addView(spacerColumn, new TableRow.LayoutParams(12, 12));
        }
    }

    void setOnClicktoButton(final TextView pd) {
        int i, l = 0;
        final int k;
        pd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("THe value in res::", "" + res[0] + " " + res[1]);
                if (pd.getId() == res[0]) {
                    res[0] = 0;
                    pd.setBackgroundResource(0);
                } else {
                    pd.setBackgroundResource(R.color.goldy);
                    if (res[0] == 0) {
                        res[0] = pd.getId();
                        //updateStats((String)pd.getText());
                    } else if (res[1] == 0) {
                        res[1] = pd.getId();
                        //updateStats((String)pd.getText());
                    } else {
                        TextView pd1 = ((TextView) findViewById(res[1]));
                        pd1.setBackgroundResource(0);
                        res[1] = pd.getId();
                        //updateStats((String)pd.getText());
                        //stats.setText()
                    }

                }
                //pd.setBackgroundResource(R.color.goldy);
            }
        });
        //add_desc.setOnItemSelectedListener();
    }

    void setClickonSwap() {
        Button bt = (Button) findViewById(R.id.bts);
        Log.d("THe value in res::", "" + res[0] + " " + res[1]);
        pl1 = (TextView) findViewById(res[0]);
        pl2 = (TextView) findViewById(res[1]);

        //add_desc.setOnItemSelectedListener();
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (res[0] == 0 && res[1] == 0) {

                } else {
                    Log.d("THvalueres when clickd:", "" + res[0] + " " + res[1]);
                    pl1 = (TextView) findViewById(res[0]);
                    pl2 = (TextView) findViewById(res[1]);
                    db1.updatePlayerID((String) pl1.getText(), (String) pl2.getText());
                    //db1.updatePlayerIDTest((String) pl1.getText(), (String) pl2.getText());
                    String tmp = (String) pl1.getText();
                    pl1.setText(pl2.getText());
                    pl2.setText(tmp);

                    pl1.setBackgroundResource(0);
                    pl2.setBackgroundResource(0);
                    res[0] = 0;
                    res[1] = 0;
                }
            }
        });
    }

    public void setClickonAddMore() {
        Button bt = (Button) findViewById(R.id.button34);
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(SquadMgmt.this);
                dialog.setContentView(R.layout.add_players);
                dialog.setTitle("ADD PLAYERS");
                dialog.show();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                // The absolute width of the available display size in pixels.
                int displayWidth = displayMetrics.widthPixels;
                // The absolute height of the available display size in pixels.
                int displayHeight = displayMetrics.heightPixels;

                // Initialize a new window manager layout parameters
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                // Copy the alert dialog window attributes to new layout parameter instance
                layoutParams.copyFrom(dialog.getWindow().getAttributes());
                int dialogWindowWidth = (int) (displayWidth);

                int dialogWindowHeight = (int) (displayHeight);
                layoutParams.width = dialogWindowWidth;
                layoutParams.height = dialogWindowHeight;
                dialog.getWindow().setAttributes(layoutParams);
                pname = (EditText) dialog.findViewById(R.id.editText);
                bowl = (Spinner) dialog.findViewById(R.id.spinner4);
                teamId = (Spinner) dialog.findViewById(R.id.spinner3);
                Button add = (Button) dialog.findViewById(R.id.button35);
                Button back = (Button) dialog.findViewById(R.id.button33);
                AddToTeam(add);//BackToSquads();
            }
        });
    }

    public void AddToTeam(Button b) {
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(pname.getText().toString().isEmpty()||teamId.getSelectedItem().toString().isEmpty()||bowl.getSelectedItem().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"FIELDS CANNOT BE LEFT BLANK",Toast.LENGTH_SHORT).show();
                else {
                    db1.addPlayer(pname.getText().toString(), teamId.getSelectedItem().toString(), bowl.getSelectedItem().toString());
                    Toast.makeText(getApplicationContext(),pname.getText().toString()+" is ADDED",Toast.LENGTH_SHORT).show();
                    pname.setText("");
                }
            }
        });
    }
}

