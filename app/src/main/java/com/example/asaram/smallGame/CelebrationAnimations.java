package com.example.asaram.smallGame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.WindowManager;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Asaram on 16-06-2019.
 */

public class CelebrationAnimations {
    public static WindowManager.LayoutParams layoutParams;
    public static AnimationDrawable frameAnimation;
public static void  ShowThat(Context context,String pname) {
        /*AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("How to close alertdialog programmatically");
        builder.setMessage("5 second dialog will close automatically");
        builder.setCancelable(true);

        final AlertDialog closedialog= builder.create();

        closedialog.show();*/
    final Dialog dialog = new Dialog(context);
    dialog.setContentView(R.layout.celeb_four);
    //ImageView view = (ImageView)dialog.findViewById(R.id.imageView);
    //view.setBackgroundResource(R.drawable.four_list);
    //dialog.setTitle("INNINGS BREAK");
    DisplayMetrics displayMetrics = new DisplayMetrics();
    layoutParams = new WindowManager.LayoutParams();
    ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    // The absolute width of the available display size in pixels.
    int displayWidth = displayMetrics.widthPixels;
    // The absolute height of the available display size in pixels.
    int displayHeight = displayMetrics.heightPixels;
    layoutParams.copyFrom(dialog.getWindow().getAttributes());
    // Set alert dialog width equal to screen width 70%
    int dialogWindowWidth = (int) (displayWidth);
    // Set alert dialog height equal to screen height 70%
    int dialogWindowHeight = (int) (displayHeight);
    layoutParams.width = dialogWindowWidth;
    layoutParams.height = dialogWindowHeight;
    dialog.getWindow().setAttributes(layoutParams);
    TextView tv = (TextView) dialog.findViewById(R.id.textView26);
    TextView tv2 = (TextView) dialog.findViewById(R.id.textView14);
    tv2.setText(tv2.getText()+" " + pname);
    MediaPlayer ring= MediaPlayer.create(context,R.raw.applause4);
    ring.start();
    Animation animation = AnimationUtils.loadAnimation(context, R.anim.zoom_in);
    tv.startAnimation(animation);
    dialog.show();
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
       /* frameAnimation = (AnimationDrawable) view.getBackground();
        frameAnimation.start();*/
        final Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            public void run() {
                dialog.dismiss();
                timer2.cancel(); //this will cancel the timer of the system
            }
        }, 2000); // the timer will count 5 seconds....
}

    public static void  ShowThatSix(Context context,String pname) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.celeb_six);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        layoutParams = new WindowManager.LayoutParams();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // The absolute width of the available display size in pixels.
        int displayWidth = displayMetrics.widthPixels;
        // The absolute height of the available display size in pixels.
        int displayHeight = displayMetrics.heightPixels;
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        // Set alert dialog width equal to screen width 70%
        int dialogWindowWidth = (int) (displayWidth);
        // Set alert dialog height equal to screen height 70%
        int dialogWindowHeight = (int) (displayHeight);
        layoutParams.width = dialogWindowWidth;
        layoutParams.height = dialogWindowHeight;
        dialog.getWindow().setAttributes(layoutParams);
        TextView tv = (TextView) dialog.findViewById(R.id.textView26);
        TextView tv2 = (TextView) dialog.findViewById(R.id.textView14);
        tv2.setText(pname+" Blasted in Air");
        MediaPlayer ring= MediaPlayer.create(context,R.raw.applause6);
        ring.start();
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.zoom_in);
        tv.startAnimation(animation);
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
       /* frameAnimation = (AnimationDrawable) view.getBackground();
        frameAnimation.start();*/
        final Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            public void run() {
                dialog.dismiss();
                timer2.cancel(); //this will cancel the timer of the system
            }
        }, 2000); // the timer will count 5 seconds....
    }

    public static void  ShowThatFifty(Context context,String pname,int which,int runs,int balls,int sixes,int fours,float srate) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.celeb_fifty);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        layoutParams = new WindowManager.LayoutParams();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // The absolute width of the available display size in pixels.
        int displayWidth = displayMetrics.widthPixels;
        // The absolute height of the available display size in pixels.
        int displayHeight = displayMetrics.heightPixels;
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        // Set alert dialog width equal to screen width 70%
        int dialogWindowWidth = (int) (displayWidth);
        // Set alert dialog height equal to screen height 70%
        int dialogWindowHeight = (int) (displayHeight);
        layoutParams.width = dialogWindowWidth;
        layoutParams.height = dialogWindowHeight;
        dialog.getWindow().setAttributes(layoutParams);
        TextView tv2 = (TextView) dialog.findViewById(R.id.textView14);
        which=which+1;
        Log.d("fifty number",""+which);
        tv2.setText("Fifty Number "+which);
        TextView bname=(TextView)dialog.findViewById(R.id.tv1);
        TextView ball=(TextView)dialog.findViewById(R.id.tv6);
        TextView four=(TextView)dialog.findViewById(R.id.tv7);
        TextView six=(TextView)dialog.findViewById(R.id.tv8);
        TextView srat=(TextView)dialog.findViewById(R.id.tv9);
        TextView run=(TextView)dialog.findViewById(R.id.textClock);
        bname.setText(""+pname);ball.setText(""+balls);four.setText(""+fours);
        six.setText(""+sixes);srat.setText(""+srate);run.setText(""+runs);
        MediaPlayer ring= MediaPlayer.create(context,R.raw.applause50);
        ring.start();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
       /* frameAnimation = (AnimationDrawable) view.getBackground();
        frameAnimation.start();*/
        final Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            public void run() {
                dialog.dismiss();
                timer2.cancel(); //this will cancel the timer of the system
            }
        }, 5000); // the timer will count 5 seconds....
    }

    public static void  ShowThatHundred(Context context,String pname,int which,int runs,int balls,int sixes,int fours,float srate) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.celeb_hundred);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        layoutParams = new WindowManager.LayoutParams();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // The absolute width of the available display size in pixels.
        int displayWidth = displayMetrics.widthPixels;
        // The absolute height of the available display size in pixels.
        int displayHeight = displayMetrics.heightPixels;
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        // Set alert dialog width equal to screen width 70%
        int dialogWindowWidth = (int) (displayWidth);
        // Set alert dialog height equal to screen height 70%
        int dialogWindowHeight = (int) (displayHeight);
        layoutParams.width = dialogWindowWidth;
        layoutParams.height = dialogWindowHeight;
        dialog.getWindow().setAttributes(layoutParams);
        TextView tv2 = (TextView) dialog.findViewById(R.id.textView14);
        which=which+1;
        Log.d("100 number",""+which);
        tv2.setText("Hundred Number "+which);
        TextView bname=(TextView)dialog.findViewById(R.id.tv1);
        TextView ball=(TextView)dialog.findViewById(R.id.tv6);
        TextView four=(TextView)dialog.findViewById(R.id.tv7);
        TextView six=(TextView)dialog.findViewById(R.id.tv8);
        TextView srat=(TextView)dialog.findViewById(R.id.tv9);
        TextView run=(TextView)dialog.findViewById(R.id.textClock);
        bname.setText(""+pname);ball.setText(""+balls);four.setText(""+fours);
        six.setText(""+sixes);srat.setText(""+srate);run.setText(""+runs);
        MediaPlayer ring= MediaPlayer.create(context,R.raw.applause100);
        ring.start();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
       /* frameAnimation = (AnimationDrawable) view.getBackground();
        frameAnimation.start();*/
        final Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            public void run() {
                dialog.dismiss();
                timer2.cancel(); //this will cancel the timer of the system
            }
        }, 5000); // the timer will count 5 seconds....
    }

    public static void showBatOutDialog(Context context,String name,int runs,int balls,int sixes,int fours)
    {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.batsman_out);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        layoutParams = new WindowManager.LayoutParams();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // The absolute width of the available display size in pixels.
        int displayWidth = displayMetrics.widthPixels;
        // The absolute height of the available display size in pixels.
        int displayHeight = displayMetrics.heightPixels;
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        // Set alert dialog width equal to screen width 70%
        int dialogWindowWidth = (int) (displayWidth);
        // Set alert dialog height equal to screen height 70%
        int dialogWindowHeight = (int) (displayHeight);
        layoutParams.width = dialogWindowWidth;
        layoutParams.height = dialogWindowHeight;
        dialog.getWindow().setAttributes(layoutParams);
        TextView tv2 = (TextView) dialog.findViewById(R.id.textView14);//code for initializing the components in dialog

        TextView bname=(TextView)dialog.findViewById(R.id.tv1);
        TextView ball=(TextView)dialog.findViewById(R.id.tv6);
        TextView four=(TextView)dialog.findViewById(R.id.tv7);
        TextView six=(TextView)dialog.findViewById(R.id.tv8);
        TextView srate=(TextView)dialog.findViewById(R.id.tv9);
        TextView run=(TextView)dialog.findViewById(R.id.textClock);
        float strRate=(float)runs/balls;strRate=strRate*100;
        Log.d("The strike rate::",""+strRate);
        bname.setText(name);run.setText(""+runs);ball.setText(""+balls);
        four.setText(""+fours);six.setText(""+sixes);srate.setText(""+strRate);
        Log.d(name+" SCORED::",""+runs+" balls-"+balls+" 4s-"+fours+" 6s-"+sixes);

        MediaPlayer ring= MediaPlayer.create(context,R.raw.booout);
        ring.start();
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.blink);
        tv2.startAnimation(animation);
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        final Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            public void run() {
                dialog.dismiss();
                timer2.cancel(); //this will cancel the timer of the system
            }
        }, 5555); // the timer will count 5 seconds....
    }
}


