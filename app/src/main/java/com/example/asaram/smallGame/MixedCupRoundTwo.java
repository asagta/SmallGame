package com.example.asaram.smallGame;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Created by Asaram on 16-08-2019.
 */

public class MixedCupRoundTwo {
    DatabaseHandler db1;
    public void InitTourDataMC(Context c) {
        DatabaseHelper dbHelper = new DatabaseHelper(c, c.getFilesDir().getAbsolutePath());
        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.d("ADD_EXPENSES:", e.getMessage());
        }
        db1 = new DatabaseHandler(c, c.getFilesDir().getAbsolutePath());
        List<String> teams = db1.getMCTopR1Teams();
        int tms[]=new int[15];
        int a=15;
        tms=randomNoReps(a);
        String teams1[]=new String[15];
        String teams2[]=new String[15];
        int i = 0,j=1,j1=0,jz=0;
        while(j1<15) {
            for (i = 0; i < 6; i++) {
                for (j = i + 1; j < 6; j++) {
                    teams1[j1] = "" + i + "" + j;
                    j1++;
                }
            }
            // j1++;
        }
        i=0;
        while(i<15)
        {
            Log.d("TMS::",""+teams1[i]);
            teams2[i]=teams1[tms[i]];
            i++;
        }
        i=45;
        int ic=0;
        while (i < 60) {
            Log.d("MC team::",""+teams2[ic]);
            Log.d("MC_Team1&2",teams.get(Character.getNumericValue(teams2[ic].charAt(0)))+" "+teams.get(Character.getNumericValue(teams2[ic].charAt(1))));
            db1.addTourTeamsMC(teams.get(Character.getNumericValue(teams2[ic].charAt(0))),teams.get(Character.getNumericValue(teams2[ic].charAt(1))),i+1);
            i++;ic++;
        }
        i=0;
        while(i<6)
        {
            db1.setRankingsMCR2(db1.getTeamId(teams.get(i)));
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
}
