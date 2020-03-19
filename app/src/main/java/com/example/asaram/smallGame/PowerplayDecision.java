package com.example.asaram.smallGame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Asaram on 18-11-2019.
 */

public class PowerplayDecision{
    public static WindowManager.LayoutParams layoutParams;
    public static void  PowerplayMenu(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.powerplay);
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
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Button bt1=(Button)dialog.findViewById(R.id.button41);
        Button bt2=(Button)dialog.findViewById(R.id.button42);
        PowerplayDecision pm=new PowerplayDecision();
        pm.getDecision(dialog,bt1);pm.getDecision(dialog,bt2);
        //dialog.dismiss();
    }
    public void getDecision(final Dialog d,final Button b )
    {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("POWER CLICKED TEXT::",b.getText().toString());
                if(b.getText().toString().equalsIgnoreCase("YES"))
                {
                    //powerplay is taken by the user
                    MainActivity.p_mode=1;
                    d.dismiss();
                }
                else
                {
                    //user did not take the powerplay
                    d.dismiss();
                }
            }
                });
    }
}


