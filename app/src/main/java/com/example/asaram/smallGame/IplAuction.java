package com.example.asaram.smallGame;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class IplAuction extends AppCompatActivity {
    DatabaseHandler db1;
    Handler hand = new Handler();
    static TextView dname, pool, purse, cdown, bidder,price;
    static Button b33, b35, next, prev;
    static ImageView logo;
    //public static int tms[];
    private Spinner add_desc, add_desc2;
    static int z, index, i,money,n,r,k;
    static int value[],p[];
    static String pool_id,prevt;
    static String tms[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ipl_auction);
        DatabaseHelper dbHelper = new DatabaseHelper(this, getFilesDir().getAbsolutePath());

        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.d("ADD_EXPENSES:", e.getMessage());
        }
        db1 = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
        pool_id = db1.getPoolId();//this will get the first pool where total players<maxplayers
        z = db1.getPoolNumber(pool_id);
        cdown = (TextView) findViewById(R.id.textView25);
        bidder = (TextView) findViewById(R.id.textView15);
        price = (TextView) findViewById(R.id.price);
        purse = (TextView) findViewById(R.id.purse);
        logo = (ImageView) findViewById(R.id.imageView6);
        p=new int[8];
        bid();
        startAuction();
        tms=new String[]{"","kxi","rr","kkr","csk","mi","rcb","srh"};
        value=new int[8];
        checkMax();
    }
public void bid()
{
    b33 = (Button) findViewById(R.id.button33);
    b33.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(db1.getMax("Delhi Capitals",pool_id.toLowerCase())==0||checkIfForeign((String)dname.getText(),"dc")||db1.getMax("Delhi Capitals","total_players")<=0)
            {
                b33.setEnabled(false);
            }
            else{
                bidder.setText("Delhi Capitals");
                int drid2 = getResources().getIdentifier("dc", "drawable", getPackageName());
                logo.setImageResource(drid2);
                if (money < 100000) {
                    money = money + 10000;
                } else if (money >= 100000 && money < 200000) {
                    money = money + 20000;
                } else if (money >= 200000 && money < 500000) {
                    money = money + 50000;
                } else {
                    money = money + 100000;
                }
                price.setText("" + money);
            }
        }

    });
}
    public void startAuction() {
        b35 = (Button) findViewById(R.id.button35);
        b35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDisplay();
                hand.postDelayed(t1,125);
            }
        });
    }

    public void updateDisplay() {
        pool = (TextView) findViewById(R.id.pool);
        dname = (TextView) findViewById(R.id.textView12);
        pool.setText("" + db1.getPoolName(pool_id));
        dname.setText("" + db1.getPlayerIpl(pool_id, i));
        money=db1.getBasePrice((String)dname.getText());
        price.setText(""+money);
        db1.updatePools(pool_id);
        if(pool_id.equalsIgnoreCase("BAT2")||pool_id.equalsIgnoreCase("AR2")||pool_id.equalsIgnoreCase("BOWL2"))
        {
            db1.updateFranchiseQuota(pool_id.toLowerCase());
        }
        i++;
        Log.d("The I is: ", "" + i);
        Log.d("The Z is: ", "" + z);
        if (i == z) {
            pool_id = db1.getPoolId();//this will get the first pool where total players<maxplayers
            z = db1.getPoolNumber(pool_id);
            i = 0;
            if(pool_id.equalsIgnoreCase("BAT2")||pool_id.equalsIgnoreCase("AR2")||pool_id.equalsIgnoreCase("BOWL2"))
            {
                db1.updateFranchiseQuota(pool_id.toLowerCase());
            }
            deleteValueArray(value);
            k=0;
        }
        if(pool_id.equalsIgnoreCase("MAR")||pool_id.equalsIgnoreCase("RND"))
        {}
        else {deleteValueArray(p);changeTees();}
        //startCountDown();

    }
    public boolean checkIfForeign(String pname,String f)
    {
        boolean fory=false;
        if(db1.getIsForeign(pname).equalsIgnoreCase("Y"))
        {
            if(db1.getForeignPlayerCount(f.toUpperCase())<=0)
                fory=true;
            else
                fory=false;
        }
      return fory;

    }
    public void deleteValueArray(int val[])
    {
        for(int i=0;i<val.length;i++)
        {
            val[i]=-1;
        }
    }
    Runnable t1=new Runnable(){
                    @Override
                    public void run() {
                        if(IplAuction.index < 180) {
                            IplAuction.cdown.setText("" + IplAuction.index);
                            b35.setEnabled(false);
                            IplAuction.index++;
                            //value=getThePosition(db1.)
                            if((IplAuction.index%10)==0)
                            {
                                Random rand = new Random();
                                if(IplAuction.index<170) {
                                    if(pool_id.equalsIgnoreCase("MAR")||pool_id.equalsIgnoreCase("RND"))
                                       n = rand.nextInt(8);
                                    else
                                    {
                                       //int xyz[]={4,6,7};
                                       //int rrnd=rand.nextInt(3);
                                       //rrnd=rrnd+1;
                                        n = rand.nextInt(8);
                                    }
                                }
                                if(tms[n].equalsIgnoreCase(prevt)||tms[n].equalsIgnoreCase("") || IplAuction.index>169 || n==r || contains(value,n)||checkIfForeign((String)dname.getText(),tms[n])||db1.getMax(db1.getFranchiseName(tms[n].toUpperCase()),"budget")<=0||db1.getMax(db1.getFranchiseName(tms[n].toUpperCase()),pool_id)==0||db1.getMax(db1.getFranchiseName(tms[n].toUpperCase()),"total_players")<=0){}
                                else{//random bid happened
                                bidder.setText(""+db1.getFranchiseName(tms[n].toUpperCase()));
                                int drid2=getResources().getIdentifier(tms[n], "drawable", getPackageName());
                                logo.setImageResource(drid2);
                                if(money<100000){money=money+10000;}
                                else if(money>=100000 && money<200000)
                                {money=money+20000;}
                                else if(money>=200000 && money<500000)
                                {money=money+50000;}
                                else
                                 {money=money+100000;}
                                  price.setText(""+money);
                                  r=n;
                                  prevt=tms[n];
                                }
                            }
                            hand.postDelayed(t1,125);
                        }
                        else {
                            IplAuction.index = 0;
                            showSoldMenu();
                            b35.setEnabled(true);
                            b33.setEnabled(true);
                            bidder.setText("bidder");
                            purse.setText(""+db1.getBudget("Delhi Capitals"));
                        }
                    }
                };

      public void showSoldMenu()
      {
          final Dialog dialog = new Dialog(IplAuction.this);
          dialog.setContentView(R.layout.sold_menu);
          dialog.getWindow().setBackgroundDrawableResource(android.R.color.background_light);
          android.view.Window window = dialog.getWindow();
          WindowManager.LayoutParams layoutParams1 = new WindowManager.LayoutParams();
          layoutParams1.copyFrom(dialog.getWindow().getAttributes());
          DisplayMetrics displayMetrics = new DisplayMetrics();
          getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
          // The absolute width of the available display size in pixels.
          int displayWidth = displayMetrics.widthPixels;
          // The absolute height of the available display size in pixels.
          int displayHeight = displayMetrics.heightPixels;
          int dialogWindowWidth = (int) (displayWidth);
          int dialogWindowHeight = (int) (displayHeight * 0.5f);
          layoutParams1.width = dialogWindowWidth;
          layoutParams1.height = dialogWindowHeight;
          dialog.getWindow().setAttributes(layoutParams1);
          //code for initializing the components in dialog
          TextView pname=(TextView)dialog.findViewById(R.id.tv1);
          TextView soldto=(TextView)dialog.findViewById(R.id.tv6);
          TextView price2=(TextView)dialog.findViewById(R.id.tv7);
          ImageView team=(ImageView)dialog.findViewById(R.id.textClock);
          if(bidder.getText().toString().equalsIgnoreCase("bidder"))
          {
              pname.setText(""+dname.getText());soldto.setText(""+"UNSOLD");
              price2.setText(""+0);
              db1.updatePlayersPool((String)dname.getText(),0,"UNSOLD");
          }//the player is not sold
          else{
          pname.setText(""+dname.getText());soldto.setText(""+bidder.getText());
          price2.setText(""+price.getText());
          int drid2=getResources().getIdentifier(db1.getFranchiseId(""+bidder.getText()).toLowerCase(), "drawable", getPackageName());
          team.setImageResource(drid2);
          db1.updatePlayersPool((String)dname.getText(),money,(String)db1.getFranchiseId(""+bidder.getText()));//update franchise,francise_id,soldprice of sold player
          //update franchise pool mainly budget and foreign and total players count
          db1.updateFranchisePool(money,(String)dname.getText(),(String)db1.getFranchiseId(""+bidder.getText()),pool_id.toLowerCase());
              if(db1.getMax((String)bidder.getText(),pool_id.toLowerCase())==0)
              {
                 value[k]=getThePosition(db1.getFranchiseId(""+bidder.getText()).toLowerCase());
                 k++;
              }
          }
          dialog.show();
      }
      public void checkMax()
      {
         for(int i=1;i<8;i++)
         {
             if(db1.getMax(db1.getFranchiseName(tms[i].toUpperCase()),pool_id.toLowerCase())==0)
             {
                 value[k]=getThePosition(tms[i]);
                 k++;
             }
         }
      }
      public int getThePosition(String t)
      {
          int z=0;
          for(int i=0;i<tms.length;i++)
          {
              if(tms[i].equalsIgnoreCase(t))
              {
                z=i;
                break;
              }
          }
          return z;
      }
      public boolean contains(int val[],int rand)
      {
          boolean having=false;
          Log.d("The val.length is::",""+val.length);
          for(int i=0;i<val.length;i++)
          {
              if(val[i]==n)
              {
                  having=true;
                  break;
              }
          }
          return having;
      }
    public void changeTees()
    {
       //System.out.println("The original array:\n");
        String t2[]=new String[]{"","kxi","rr","kkr","csk","mi","rcb","srh"};

        for(int i=0;i<tms.length;i++)
        {
            Log.d("Original: ",t2[i]+" ");
            //t2[i]=tms[i];
        }
        Random rand = new Random();
        int n=0;
        int i=0;
        //System.out.println("The mixed array:\n");
        while(i<8)
        {
            n=rand.nextInt(8);
            Log.d("The n is::",""+n);
            /*if(contains(p,n))
            {}
            else
            {*/
                p[i]=n;
                tms[i]=t2[n];
                Log.d("Mixed: ",tms[i]+" ");
                i++;
           // }
        }

    }
    }