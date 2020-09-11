package com.example.asaram.smallGame;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Asaram on 07-10-2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private final Context myContext;
    private static final String DATABASE_NAME = "MasterDB";
    private static final int DATABASE_VERSION = 1;
    private String pathToSaveDBFile;
    public SQLiteDatabase db;
    static int r,hs,bwic;
    public DatabaseHandler(Context context, String filePath) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        //write here external db saving code
        filePath= Environment.getExternalStorageDirectory()+"/"+"HomeCric";
        File f=new File(filePath);
        if(!f.exists())
            f.mkdir();

        Log.d("DEST-PATH-:",filePath.toString());
        pathToSaveDBFile = new StringBuffer(filePath).append("/").append(DATABASE_NAME).toString();
        Log.d("FILE IN CONSTRUCTOR",pathToSaveDBFile);
        db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("INSERT INTO dbVersion" + " values(1)");
        // Log.d("Expense staturs: ", "")));
        //create table expense(sno INTEGER, money INTEGER, date_of DATE,time_of );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        db.execSQL("DROP TABLE IF EXISTS dbVersion" );
        // Create tables again
        onCreate(db);
    }
    public void updateScoreTable(int a)
    {

        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE scores"  + " SET score=" + a + " where team='IND'");
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
    public int getScore()
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT score FROM scores";
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        int i=cursor.getInt(0);
        db.close();
        cursor.close();
        return i;
    }
    public void getTeamName()
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT team_name FROM teams";
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String i=cursor.getString(0);
        Log.d("TEAM-NAME::",i);
        db.close();
        cursor.close();
        //return i;
    }
    public String getPlayerName(String team_id,int order,String tabname)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery=null;
        selectQuery = "SELECT player_name FROM "+tabname+" where team_id like '%"+team_id+"%' order by player_id";

        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(order);
        String i=cursor.getString(0);
        db.close();
        cursor.close();
        return i;
    }
    public String getPlayerNameFaces(String team_id,int order,String tabname)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery=null;
        selectQuery = "SELECT player_name FROM "+tabname+" where team_id like '%"+team_id+"%' order by border";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(order);
        String i=cursor.getString(0);
        db.close();
        cursor.close();
        return i;
    }
    public String getPlayerNameTest(String team_id,int order)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT player_name FROM players_test where team_id like '%"+team_id+"%' order by player_id";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(order);
        String i=cursor.getString(0);
        db.close();
        cursor.close();
        return i;
    }
    public String getPlayerNameRR(String team_id,int order)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT player_name FROM rr_stats where team_id like '%"+team_id+"%' order by player_id";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(order);
        String i=cursor.getString(0);
        db.close();
        cursor.close();
        return i;
    }
    public String getPlayerNameIPL(String team_id,int order)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT player_name FROM players_pool where franchise_id like '%"+team_id+"%' order by franchise_id";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(order);
        String i=cursor.getString(0);
        db.close();
        cursor.close();
        return i;
    }
    public String getCurrPlayerName(String team_id,int order)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        order=order+1;
        String selectQuery = "SELECT player_name FROM curr_players where team_id like '%"+team_id+"%' and border="+order;
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String i=cursor.getString(0);
        db.close();
        cursor.close();
        return i;
    }
    public String getCurrTestPlayerName(String team_id,int order)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        order=order+1;
        String selectQuery = "SELECT player_name FROM curr_players_test where team_id like '%"+team_id+"%' and border="+order;
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String i=cursor.getString(0);
        db.close();
        cursor.close();
        return i;
    }
    public int getSixLimit(String team_id,int order)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        order=order+1;
        String selectQuery = "SELECT sixlimit FROM curr_players where team_id like '%"+team_id+"%' and border="+order;
        Log.d("QUERY::",selectQuery);
        Cursor cursor= db.rawQuery(selectQuery, null);;int i=2;
        try{
        cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        i=cursor.getInt(0);}
        catch(Exception ex)
        {
           i=2;
        }
        db.close();
        cursor.close();
        return i;
    }
    public int getSixLimitTest(String team_id,int order)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        order=order+1;
        String selectQuery = "SELECT sixlimit FROM curr_players_test where team_id like '%"+team_id+"%' and border="+order;
        Log.d("QUERY::",selectQuery);
        Cursor cursor= db.rawQuery(selectQuery, null);;int i=2;
        try{
            cursor = db.rawQuery(selectQuery, null);
            cursor.moveToFirst();
            i=cursor.getInt(0);}
        catch(Exception ex)
        {
            i=2;
        }
        db.close();
        cursor.close();
        return i;
    }
    public int getBowlerStatus(String bowl_id)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT goodbowler FROM players where player_name like '%"+bowl_id+"%'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor= db.rawQuery(selectQuery, null);;int i=2;
        try{
            cursor = db.rawQuery(selectQuery, null);
            cursor.moveToFirst();
            i=cursor.getInt(0);}
        catch(Exception ex)
        {
            i=0;
        }
        db.close();
        cursor.close();
        return i;
    }

    public List<String> getAllTeams(){
        List<String> names = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT team_name FROM teams";

        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                names.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning names
        return names;
    }
    public void insertCurrMatch(String t1,String t2,String tf,int maxOvers)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        String s1,s2,selectQuery;
        int maxBalls=maxOvers*6;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("DELETE FROM curr_match");
        if(tf.equalsIgnoreCase("I"))
            selectQuery = "SELECT fran_id FROM franchise_pool where fran_name in ('"+t1+"')";
        else
            selectQuery = "SELECT team_id FROM teams where team_name in ('"+t1+"')";
        Log.d("TEAM-ID QRY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        s1=cursor.getString(0);
        if(tf.equalsIgnoreCase("I"))
            selectQuery = "SELECT fran_id FROM franchise_pool where fran_name in ('"+t2+"')";
        else
            selectQuery = "SELECT team_id FROM teams where team_name in ('"+t2+"')";
        Log.d("TEAM-ID QRY::",selectQuery);
        cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        s2=cursor.getString(0);
        db.execSQL("insert into curr_match values('"+s1+"')");
        db.execSQL("insert into curr_match values('"+s2+"')");
        Log.d("ID ADDED::",s1+"  "+s2);
        db.execSQL("DELETE FROM curr_game");
        if(tf.equalsIgnoreCase("M"))
            db.execSQL("insert into curr_game values(0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,"+maxBalls+","+maxOvers+",0,1,0)");
        else if(tf.equalsIgnoreCase("S"))
            db.execSQL("insert into curr_game values(0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,"+maxBalls+","+maxOvers+",0,2,0)");
        else if(tf.equalsIgnoreCase("I"))
            db.execSQL("insert into curr_game values(0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,"+maxBalls+","+maxOvers+",0,3,0)");
        else if(tf.equalsIgnoreCase("T")) {
            db.execSQL("DELETE FROM curr_game_test");
            db.execSQL("insert into curr_game_test values(0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0," + maxBalls + "," + maxOvers + ",0,0,0,0,0,1)");
        }
            else
            db.execSQL("insert into curr_game values(0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,"+maxBalls+","+maxOvers+",0,0,0)");
        db.close();
        cursor.close();
        //return i;
    }
    public void insertCurrPlayers(String tname,String pid,int o,String cb,int sl)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("insert into curr_players('player_name','team_id','border','can_bowl','sixlimit') values('"+pid+"','"+tname+"',"+o+",'"+cb+"',"+sl+")");
        db.execSQL("update curr_players set bovers=0");
        db.execSQL("update curr_players set bruns=0");
        db.execSQL("update curr_players set bwick=0");
        db.execSQL("update curr_players set bmaiden=0");
        db.close();
    }
    public void insertCurrTestPlayers(String tname,String pid,int o,String cb,int sl)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("insert into curr_players_test('player_name','team_id','border','can_bowl','sixlimit','sixlimit_2') values('"+pid+"','"+tname+"',"+o+",'"+cb+"',"+sl+","+sl+")");
        db.execSQL("update curr_players_test set bovers=0");
        db.execSQL("update curr_players_test set bovers_2=0");
        db.execSQL("update curr_players_test set bruns=0");
        db.execSQL("update curr_players_test set bruns_2=0");
        db.execSQL("update curr_players_test set bwick=0");
        db.execSQL("update curr_players_test set bwick_2=0");
        db.execSQL("update curr_players_test set bmaiden=0");
        db.execSQL("update curr_players_test set bmaiden_2=0");
        db.close();
    }
    public void deleteCurrPlayers()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("DELETE FROM curr_players");
        db.execSQL("DELETE FROM curr_scores");
        db.close();
    }
    public void deleteCurrPlayersTest()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("DELETE FROM curr_players_test");
        db.execSQL("DELETE FROM curr_scores");
        db.close();
    }
    public String getCurrTeamName(int no)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT team_id FROM curr_match";
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if(no==1)
        {
            cursor.moveToNext();
        }
        String i=cursor.getString(0);
        Log.d("TEAM-NAME::",i);
        db.close();
        cursor.close();
        return i;
        //return i;
    }
    public void updateBalls(String p,int b)
    {

        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE curr_players"  + " SET balls=" + b + " where player_name='"+p+"'");
        String bls="UPDATE curr_players"  + " SET balls=" + b + " where player_name='"+p+"'";
        Log.d("UPDATED BALLS::",bls);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
    public void updateRuns(String p,int b)
    {

        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE curr_players"  + " SET runs=" + b + " where player_name='"+p+"'");
        db.close(); // Closing database connection
    }
    public void updateOut(String p)
    {

        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE curr_players"  + " SET out=1" + " where player_name='"+p+"'");
        db.close(); // Closing database connection
    }
    public void updateFours(String p,int b)
    {

        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE curr_players"  + " SET '4s'=" + b + " where player_name='"+p+"'");
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
    public void updateSixes(String p,int b)
    {

        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE curr_players"  + " SET '6s'=" + b + " where player_name='"+p+"'");
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    public void updateCurrPlayerTest(String col,String p,int b)
    {

        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE curr_players_test"  + " SET `"+col+"` =" + b + " where player_name='"+p+"'");
        String bls="UPDATE curr_players_test"  + " SET `"+col+"` =" + b + " where player_name='"+p+"'";
        Log.d("UPDATED BALLS::",bls);
        db.close(); // Closing database connection
    }

    public String[] getPlayersStats(String team_id,int order)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT runs,balls,`4s`,`6s` FROM curr_players where team_id like '%"+team_id+"%' and border="+order;
        Log.d("QUERY::",selectQuery);
        String data[]=new String[4];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        for(int i=0;i<4;i++)
        {
            try
            {data[i]=""+cursor.getInt(i);}
            catch(Exception e)
            {data[i]="";}
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]+" "+data[3]);
        db.close();
        cursor.close();
        return data;
    }
    public void initializeScores(String t1,String t2)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("insert into curr_scores('team_id') values('"+t1+"')");
        db.execSQL("insert into curr_scores('team_id') values('"+t2+"')");
        db.close();
    }
    public void initializeScoresTest(String t1,String t2)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("delete from curr_scores_test");
        db.execSQL("insert into curr_scores_test('team_id','innings') values('"+t1+"','1')");
        db.execSQL("insert into curr_scores_test('team_id','innings') values('"+t1+"','2')");
        db.execSQL("insert into curr_scores_test('team_id','innings') values('"+t2+"','1')");
        db.execSQL("insert into curr_scores_test('team_id','innings') values('"+t2+"','2')");
        db.close();
    }
    public void setOvers(String team_id,String ov)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE curr_scores SET overs='" + ov+"'" + " where team_id='"+team_id+"'");
        db.close();
        // Closing database connection
    }
    public void setOversTest(String team_id,String ov,int in)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE curr_scores_test SET overs='" + ov+"'" + " where team_id='"+team_id+"' and innings='"+in+"'");
        db.close();
    }
    public void setScores(String team_id,String sc)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE curr_scores SET score='" + sc+"'" + " where team_id='"+team_id+"'");
        String qry="UPDATE curr_scores SET score='" + sc+"'" + " where team_id='"+team_id+"'";
        Log.d("Scores Query::",qry);
        db.close(); // Closing database connection
    }
    public void setScoresTest(String team_id,String sc,int in)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE curr_scores_test SET score='" + sc+"'" + " where team_id='"+team_id+"' and innings='"+in+"'");
        String qry="UPDATE curr_scores_test SET score='" + sc+"'" + " where team_id='"+team_id+"' and innings='"+in+"'";
        Log.d("Scores Query::",qry);
        db.close(); // Closing database connection
    }
    public String getScores(String team_id)
    {
        String sc;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT score FROM curr_scores where team_id like '%"+team_id+"%'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        try{
            sc=cursor.getString(0);}
        catch(Exception e){
            Log.d("EXPETION CAUGHT",e.toString());
            sc="";
        }
        Log.d("DSCORES::","tHE db SCORE:"+sc);
        db.close();
        cursor.close();
        return sc;
    }

    public String getOvers(String team_id)
    {
        String ov;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT overs FROM curr_scores where team_id like '%"+team_id+"%'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        try{
            ov=cursor.getString(0);}
        catch(Exception e)
        {
            Log.d("EXPETION IS::",e.toString());
            ov="";
        }
        db.close();
        cursor.close();
        return ov;
    }
    public void updatePrevPerf(String team_id,String opponent,int i) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT player_name,runs,balls,bovers,bruns,bwick,out FROM curr_players where team_id like '%" + team_id + "%' and border=" + i;
        Log.d("PERF_QUERY::", selectQuery);
        String data[] = new String[8];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        data[0]=cursor.getString(0);
        Log.d("PERF_QUERY_DATA::", data[0]);
        for(int i1=1;i1<7;i1++)
        {
            try
            {data[i1]=cursor.getString(i1);Log.d("PERF_QUERY::", data[i1]);}
            catch(Exception e)
            {data[i1]="-";}
        }
        data[7]=opponent;
        //logic for updating prev_performance of players
        selectQuery = "SELECT count(pname) FROM prev_perf where pname='"+data[0]+"'";
        Log.d("PERF_QUERY::", selectQuery);
        cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        int j=cursor.getInt(0);
        if(j==0)
        {
            db.execSQL("insert into prev_perf values(1,'"+data[0]+"','"+data[7]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"','"+data[6]+"')");
        }
        else
        {
            db.execSQL("update prev_perf set sno=sno+1 where pname='"+data[0]+"'");
            try{ db.execSQL("delete from prev_perf where pname='"+data[0]+"' and sno=6");}
            catch(Exception ex){Log.d("DELETE ERROR:",ex.toString());}
            db.execSQL("insert into prev_perf values(1,'"+data[0]+"','"+data[7]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"','"+data[6]+"')");
        }
        cursor.close();
    }
    public void updatePrevPerfTest(String team_id,String opponent,int i) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT player_name,runs,runs_2,balls,balls_2,bovers,bovers_2,bruns,bruns_2,bwick,bwick_2,out,out_2 FROM curr_players_test where team_id like '%" + team_id + "%' and border=" + i;
        Log.d("PERF_QUERY::", selectQuery);
        String data[] = new String[14];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        data[0]=cursor.getString(0);
        Log.d("PERF_QUERY_DATA::", data[0]);
        for(int i1=1;i1<13;i1++)
        {
            try
            {data[i1]=cursor.getString(i1);Log.d("PERF_QUERY::", data[i1]);}
            catch(Exception e)
            {data[i1]="-";}
        }
        data[13]=opponent;
        //logic for updating prev_performance of players
        selectQuery = "SELECT count(pname) FROM prev_perf_test where pname='"+data[0]+"'";
        Log.d("PERF_QUERY::", selectQuery);
        cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        int j=cursor.getInt(0);
        if(j==0)
        {
            db.execSQL("insert into prev_perf_test values(1,'"+data[0]+"','"+data[13]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"','"+data[6]+"','"+data[7]+"','"+data[8]+"','"+data[9]+"','"+data[10]+"','"+data[11]+"','"+data[12]+"')");
        }
        else
        {
            db.execSQL("update prev_perf_test set sno=sno+1 where pname='"+data[0]+"'");
            try{ db.execSQL("delete from prev_perf_test where pname='"+data[0]+"' and sno=6");}
            catch(Exception ex){Log.d("DELETE ERROR:",ex.toString());}
            db.execSQL("insert into prev_perf_test values(1,'"+data[0]+"','"+data[13]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"','"+data[6]+"','"+data[7]+"','"+data[8]+"','"+data[9]+"','"+data[10]+"','"+data[11]+"','"+data[12]+"')");
        }
        cursor.close();
    }
    public void updatePlayersStat(String team_id,int bat_order,String tab_name)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT runs,balls,`4s`,`6s`,bovers,bruns,bwick,bmaiden,out FROM curr_players where team_id like '%"+team_id+"%' and border="+bat_order;
        Log.d("QUERY::",selectQuery);
        int data[]=new int[9];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        for(int i=0;i<9;i++)
        {
            try
            {data[i]=cursor.getInt(i);}
            catch(Exception e)
            {data[i]=0;}
        }
        cursor.close();
        selectQuery = "SELECT player_name FROM curr_players where team_id like '%"+team_id+"%' and border="+bat_order;
        Log.d("QUERY::",selectQuery);
        cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String pName=cursor.getString(0);
        cursor.close();
        //updating the matches
        db.execSQL("UPDATE "+tab_name+" SET Matches=Matches+1 where player_name='"+pName+"'");
        //updating runs
        db.execSQL("UPDATE "+tab_name+" SET Runs=Runs+"+data[0]+" where player_name='"+pName+"'");
        //updating the batsman ratings
        int rat=0;
        if(data[1]>0) {
            if (data[0] == 0) rat = -5;
            else if (data[0] > 0 && data[0] < 11) rat = -4;
            else if (data[0] > 10 && data[0] < 21) rat = -3;
            else if (data[0] > 20 && data[0] < 31) rat = -2;
            else if (data[0] > 30 && data[0] < 41) rat = 3;
            else if (data[0] > 40 && data[0] < 51) rat = 4;
            else if (data[0] > 50 && data[0] < 61) rat = 5;
            else if (data[0] > 60 && data[0] < 71) rat = 6;
            else if (data[0] > 70 && data[0] < 81) rat = 7;
            else if (data[0] > 80 && data[0] < 91) rat = 8;
            else if (data[0] > 90 && data[0] < 101) rat = 9;
            else rat = 10;
            db.execSQL("UPDATE "+tab_name+"  SET bat_rat=bat_rat+" + rat + " where player_name='" + pName + "'");
        }
        //updating 4s
        db.execSQL("UPDATE "+tab_name+" SET `4s`=`4s`+"+data[2]+" where player_name='"+pName+"'");
        //updating 6s
        db.execSQL("UPDATE "+tab_name+" SET `6s`=`6s`+"+data[3]+" where player_name='"+pName+"'");
        if(data[8]==0){db.execSQL("UPDATE "+tab_name+" SET nouts=nouts+1 where player_name='"+pName+"'");}
        db.execSQL("UPDATE "+tab_name+" SET bovers = bovers+"+data[4]+" where player_name='"+pName+"'");
        db.execSQL("UPDATE "+tab_name+" SET bruns = bruns+"+data[5]+" where player_name='"+pName+"'");
        db.execSQL("UPDATE "+tab_name+" SET bwick = bwick+"+data[6]+" where player_name='"+pName+"'");
        db.execSQL("UPDATE "+tab_name+" SET bdots = bdots+"+data[7]+" where player_name='"+pName+"'");
        //updating 4Wicket hauls and 5 wicket hauls
        if(data[6]==4)
            db.execSQL("UPDATE "+tab_name+" SET `4Ws`=`4Ws`+1 where player_name='"+pName+"'");
        else if(data[6]>4)
            db.execSQL("UPDATE "+tab_name+" SET `5Ws`=`5Ws`+1 where player_name='"+pName+"'");
        else
        {}

        //updaing 30s/50s/100s
        if(data[0]>29 && data[0]<50)
            db.execSQL("UPDATE "+tab_name+" SET `30s`=`30s`+1 where player_name='"+pName+"'");
        else if(data[0]>49 && data[0]<100)
            db.execSQL("UPDATE "+tab_name+" SET `50s`=`50s`+1 where player_name='"+pName+"'");
        else if(data[0]>99)
            db.execSQL("UPDATE "+tab_name+" SET `100s`=`100s`+1 where player_name='"+pName+"'");
        else
        {}
        //updating best bowling

        selectQuery = "SELECT BB FROM "+tab_name+" where player_name='"+pName+"'";
        Log.d("QUERY::",selectQuery);
        cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String bb=cursor.getString(0);
        int bwic;
        int i1=0;
        try {
            while (bb.charAt(i1) != '/') {
                i1++;
            }

            bwic = Character.getNumericValue(bb.charAt(i1 + 1));
            if (bwic < data[6])
                db.execSQL("UPDATE " + tab_name + " SET BB='" + data[5] + "/" + data[6] + "' where player_name='" + pName + "'");
            else {

            }}
        catch(Exception ex)
        { Log.d("THE_EXCEPTION:",""+ex);}
        //updating highScore
        selectQuery = "SELECT highest FROM " + tab_name + " where player_name='" + pName + "'";
        Log.d("QUERY::", selectQuery);
        cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        int hs = cursor.getInt(0);
        if (hs < data[0])
            db.execSQL("UPDATE " + tab_name + " SET Highest=" + data[0] + " where player_name='" + pName + "'");
        //updating balls
        db.execSQL("UPDATE " + tab_name + " SET Balls=Balls+" + data[1] + " where player_name='" + pName + "'");
        db.close();
        cursor.close();

    }

    public void updatePlayersStatTest(String team_id,int bat_order,String tab_name)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT runs,runs_2,balls,balls_2,`4s`,`4s_2`,`6s`,`6s_2`,bovers,bovers_2,bruns,bruns_2,bwick,bwick_2,out,out_2 FROM curr_players_test where team_id like '%"+team_id+"%' and border="+bat_order;
        Log.d("QUERY::",selectQuery);
        int data[]=new int[16];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        for(int i=0;i<16;i++)
        {
            try
            {data[i]=cursor.getInt(i);}
            catch(Exception e)
            {data[i]=0;}
        }
        int Truns=data[0]+data[1];
        int Tballs=data[2]+data[3];
        int Tinnings;
        if(Tballs==data[2]||Tballs==data[3])
            Tinnings=1;
        else
            Tinnings=2;
        int Tfour=data[4]+data[5];
        int Tsix=data[6]+data[7];
        int Tbovers=data[8]+data[9];
        int Tbruns=data[10]+data[11];
        int Tbwick=data[12]+data[13];
        cursor.close();
        selectQuery = "SELECT player_name FROM curr_players_test where team_id like '%"+team_id+"%' and border="+bat_order;
        Log.d("QUERY::",selectQuery);
        cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String pName=cursor.getString(0);
        cursor.close();
        //updating the matches
        db.execSQL("UPDATE "+tab_name+" SET Matches=Matches+1 where player_name='"+pName+"'");
        //update innings in the case of a test match
        db.execSQL("UPDATE "+tab_name+" SET Innings=Innings+"+Tinnings+" where player_name='"+pName+"'");
        //updating runs
        db.execSQL("UPDATE "+tab_name+" SET Runs=Runs+"+Truns+" where player_name='"+pName+"'");
        db.execSQL("UPDATE "+tab_name+" SET `4s`=`4s`+"+Tfour+" where player_name='"+pName+"'");
        db.execSQL("UPDATE "+tab_name+" SET `6s`=`6s`+"+Tsix+" where player_name='"+pName+"'");
        if(data[14]==0 && data[15]==0){db.execSQL("UPDATE "+tab_name+" SET nouts=nouts+2 where player_name='"+pName+"'");}
        else if(data[14]==0 || data[15]==0){db.execSQL("UPDATE "+tab_name+" SET nouts=nouts+1 where player_name='"+pName+"'");}
        db.execSQL("UPDATE "+tab_name+" SET bovers = bovers+"+Tbovers+" where player_name='"+pName+"'");
        db.execSQL("UPDATE "+tab_name+" SET bruns = bruns+"+Tbruns+" where player_name='"+pName+"'");
        db.execSQL("UPDATE "+tab_name+" SET bwick = bwick+"+Tbwick+" where player_name='"+pName+"'");
        //db.execSQL("UPDATE "+tab_name+" SET bdots = bdots+"+data[7]+" where player_name='"+pName+"'");
        //updating 4Wicket hauls and 5 wicket hauls
        if(data[12]==4)
            db.execSQL("UPDATE "+tab_name+" SET `4Ws`=`4Ws`+1 where player_name='"+pName+"'");
        else if(data[12]>4)
            db.execSQL("UPDATE "+tab_name+" SET `5Ws`=`5Ws`+1 where player_name='"+pName+"'");
        else
        {}
        if(data[13]==4)
            db.execSQL("UPDATE "+tab_name+" SET `4Ws`=`4Ws`+1 where player_name='"+pName+"'");
        else if(data[13]>4)
            db.execSQL("UPDATE "+tab_name+" SET `5Ws`=`5Ws`+1 where player_name='"+pName+"'");
        else
        {}

        //updaing 30s/50s/100s
        if(data[0]>49 && data[0]<100)
            db.execSQL("UPDATE "+tab_name+" SET `50s`=`50s`+1 where player_name='"+pName+"'");
        else if(data[0]>99 && data[0]<200)
            db.execSQL("UPDATE "+tab_name+" SET `100s`=`100s`+1 where player_name='"+pName+"'");
        else if(data[0]>199)
            db.execSQL("UPDATE "+tab_name+" SET `200s`=`200s`+1 where player_name='"+pName+"'");
        else
        {}
        if(data[1]>49 && data[1]<100)
            db.execSQL("UPDATE "+tab_name+" SET `50s`=`50s`+1 where player_name='"+pName+"'");
        else if(data[1]>99 && data[1]<200)
            db.execSQL("UPDATE "+tab_name+" SET `100s`=`100s`+1 where player_name='"+pName+"'");
        else if(data[1]>199)
            db.execSQL("UPDATE "+tab_name+" SET `200s`=`200s`+1 where player_name='"+pName+"'");
        else
        {}
        //updating best bowling

        selectQuery = "SELECT BB FROM "+tab_name+" where player_name='"+pName+"'";
        Log.d("QUERY::",selectQuery);
        cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String bb=cursor.getString(0);
        int i1=0;
        try {
            while (bb.charAt(i1) != '/') {
                i1++;
            }

            bwic = Character.getNumericValue(bb.charAt(i1 + 1));
            if (bwic < data[12]) {
                bwic=data[12];
                db.execSQL("UPDATE " + tab_name + " SET BB='" + data[10] + "/" + data[12] + "' where player_name='" + pName + "'");
            }
            if (bwic < data[13])
                db.execSQL("UPDATE " + tab_name + " SET BB='" + data[11] + "/" + data[13] + "' where player_name='" + pName + "'");
            else {

            }}
        catch(Exception ex)
        { Log.d("THE_EXCEPTION:",""+ex);}
        //updating highScore
        selectQuery = "SELECT highest FROM " + tab_name + " where player_name='" + pName + "'";
        Log.d("QUERY::", selectQuery);
        cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        hs = cursor.getInt(0);
        if (hs < data[0]) {
            hs=data[0];
            db.execSQL("UPDATE " + tab_name + " SET Highest=" + data[0] + " where player_name='" + pName + "'");
        }
        if(hs<data[1])
            db.execSQL("UPDATE " + tab_name + " SET Highest=" + data[1] + " where player_name='" + pName + "'");
        //updating balls
        db.execSQL("UPDATE " + tab_name + " SET Balls=Balls+" + Tballs + " where player_name='" + pName + "'");
        db.close();
        cursor.close();
    }
    public int getMatches(String pName)
    {
        int mtch;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT Matches FROM players where player_name='"+pName+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        mtch=cursor.getInt(0);
        db.close();
        cursor.close();
        return mtch;
    }
    public int getNotOuts(String pName)
    {
        int mtch;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT nouts FROM players where player_name='"+pName+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        mtch=cursor.getInt(0);
        db.close();
        cursor.close();
        return mtch;
    }
    public int getMatchRuns(String pName)
    {
        int mtch;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT Runs FROM players where player_name='"+pName+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        mtch=cursor.getInt(0);
        db.close();
        cursor.close();
        return mtch;
    }
    public int getMatchHS(String pName)
    {
        int mtch;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT Highest FROM players where player_name='"+pName+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        mtch=cursor.getInt(0);
        db.close();
        cursor.close();
        return mtch;
    }
    public int getStatsTest(String attr,String pName)
    {
        int mtch;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT "+attr+" FROM players_test where player_name='"+pName+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        mtch=cursor.getInt(0);
        db.close();
        cursor.close();
        return mtch;
    }
    public int getStatsIntl(String attr,String pName,String tabname)
    {
        int mtch;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT "+attr+" FROM "+tabname+" where player_name='"+pName+"'";
        Log.d("QUERY_Intl::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        mtch=cursor.getInt(0);
        db.close();
        cursor.close();
        return mtch;
    }
    public int getStatsIPL(String attr,String pName)
    {
        int mtch;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT "+attr+" FROM ipl_records where player_name='"+pName+"'";
        Log.d("QUERY_IPL::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        mtch=cursor.getInt(0);
        db.close();
        cursor.close();
        return mtch;
    }
    public int getMatchBalls(String pName)
    {
        int mtch;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT Balls FROM players where player_name='"+pName+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        mtch=cursor.getInt(0);
        db.close();
        cursor.close();
        return mtch;
    }
    public int[] getMatchRew(String pName,String tabname)
    {
        int mtch[]=new int[3];
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT `30s`,`50s`,`100s` FROM "+tabname+" where player_name='"+pName+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        mtch[0]=cursor.getInt(0);mtch[1]=cursor.getInt(1);mtch[2]=cursor.getInt(2);
        Log.d("Fifty Count of "+pName+"::",""+mtch[1]);
        db.close();
        cursor.close();
        return mtch;
    }
    public int[] getMatchRew(String pName)
    {
        int mtch[]=new int[3];
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT `30s`,`50s`,`100s` FROM players where player_name='"+pName+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        mtch[0]=cursor.getInt(0);mtch[1]=cursor.getInt(1);mtch[2]=cursor.getInt(2);
        Log.d("Fifty Count of "+pName+"::",""+mtch[1]);
        db.close();
        cursor.close();
        return mtch;
    }
    public int[] getMatchRewIPL(String pName)
    {
        int mtch[]=new int[3];
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT `30s`,`50s`,`100s` FROM ipl_records where player_name='"+pName+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        mtch[0]=cursor.getInt(0);mtch[1]=cursor.getInt(1);mtch[2]=cursor.getInt(2);
        Log.d("Fifty Count of "+pName+"::",""+mtch[1]);
        db.close();
        cursor.close();
        return mtch;
    }
    public int[] getMatchRewTest(String pName)
    {
        int mtch[]=new int[3];
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT `50s`,`100s`,`200s` FROM players_test where player_name='"+pName+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        mtch[0]=cursor.getInt(0);mtch[1]=cursor.getInt(1);mtch[2]=cursor.getInt(2);
        Log.d("Fifty Count of "+pName+"::",""+mtch[1]);
        db.close();
        cursor.close();
        return mtch;
    }
    public int[] getMatchFsix(String pName)
    {
        int mtch[]=new int[3];
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT `4s`,`6s` FROM players where player_name='"+pName+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        mtch[0]=cursor.getInt(0);mtch[1]=cursor.getInt(1);
        db.close();
        cursor.close();
        return mtch;
    }
    public void updatePlayerID(String a,String b,String tabname)
    {
        String mtch[]=new String[2];
        String selectQuery="";String selectQuery2="";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        if(IPLCentral.tour_flag.equalsIgnoreCase("I")) {
            selectQuery = "SELECT franchise_id FROM players_pool where player_name='"+a+"'";
            selectQuery2 = "SELECT franchise_id FROM players_pool where player_name='"+b+"'";
        }
            else {
            selectQuery = "SELECT player_id FROM "+tabname+" where player_name='"+a+"'";
            selectQuery2 = "SELECT player_id FROM "+tabname+" where player_name='"+b+"'";
        }
            Log.d("QUERY::",selectQuery);Log.d("QUERY::",selectQuery2);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        mtch[0]=cursor.getString(0);
        cursor = db.rawQuery(selectQuery2, null);
        cursor.moveToFirst();
        mtch[1]=cursor.getString(0);
        Log.d("THE PLAYER IDs:",mtch[0]+"  "+mtch[1]);
        if(IPLCentral.tour_flag.equalsIgnoreCase("I")) {
            db.execSQL("UPDATE players_pool SET franchise_id='" + mtch[1] + "'" + " where player_name='" + a + "'");
            db.execSQL("UPDATE players_pool SET franchise_id='" + mtch[0] + "'" + " where player_name='" + b + "'");
        }
        else{
            Log.d("QUERY EXECUTED::","YES");
            db.execSQL("UPDATE "+tabname+" SET player_id='" + mtch[1] + "'" + " where player_name='" + a + "'");
            //db.execSQL("UPDATE players_test SET player_id='" + mtch[1] + "'" + " where player_name='" + a + "'");
            db.execSQL("UPDATE "+tabname+" SET player_id='" + mtch[0] + "'" + " where player_name='" + b + "'");
            //db.execSQL("UPDATE players_test SET player_id='" + mtch[0] + "'" + " where player_name='" + b + "'");
        }
        String q2="UPDATE players SET player_id='" + mtch[1]+"'" + " where player_name='"+a+"'";
        String qry="UPDATE players SET player_id='" + mtch[0]+"'" + " where player_name='"+b+"'";
        Log.d("Scores Query1::",q2);
        Log.d("Scores Query2::",qry);
        db.close();
    }
    //SAVED GAME STATS
    public int getCurrData(String var)
    {
        int mtch;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT "+var+" FROM curr_game ";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        mtch=cursor.getInt(0);
        db.close();
        cursor.close();
        return mtch;
    }
    public int getCurrDataTest(String var)
    {
        int mtch;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT "+var+" FROM curr_game_test ";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        mtch=cursor.getInt(0);
        db.close();
        cursor.close();
        return mtch;
    }
    public void updateCurrPlayer(String col,String pname,String val)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        if(col.equalsIgnoreCase("fow"))
           db.execSQL("UPDATE curr_players SET "+col+"=" + val + " where player_name='"+pname+"'");
        else
           db.execSQL("UPDATE curr_players SET "+col+"='" + val+"'"+ " where player_name='"+pname+"'");
        String qry="UPDATE curr_players SET "+col+"=" + val + " where player_name='"+pname+"'";
        Log.d("CURRENT GAME Query::",qry);
        db.close(); // Closing database connection
    }
    public void updateCurrGame(String col,int val)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE curr_game SET "+col+"=" + val);
        String qry="UPDATE curr_game SET "+col+"=" + val;
        Log.d("CURRENT GAME Query::",qry);
        db.close(); // Closing database connection
    }
    public void updateCurrGameTest(String col,int val)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE curr_game_test SET "+col+"=" + val);
        String qry="UPDATE curr_game_test SET "+col+"=" + val;
        Log.d("CURRENT GAME Query::",qry);
        db.close(); // Closing database connection
    }
    public void addTourTeamsSeries(String t1,String t2,int i)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        //db.execSQL("DELETE FROM tour_teams");
        db.execSQL("insert into series_fixtures('sno','T1','T2') values("+i+",'"+t1+"','"+t2+"')");
        db.close();
    }
    public void setTourMatches(int i)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        //db.execSQL("DELETE FROM tour_matches");
        db.execSQL("insert into tour_matches('sno') values("+i+")");
        db.close();
    }
    public void addtourMatches(String team,String tname,int i)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        //db.execSQL("DELETE FROM tour_matches");
        //update tour_matches set T1='INDIA' where sno=1
        db.execSQL("update tour_matches set "+team+" ='"+tname+"' where sno="+i);
        String qry="update tour_matches set "+team+" ='"+tname+"' where sno="+i;
        Log.d("TO::",qry);

        String selectQuery = "SELECT T1 FROM tour_matches";
        //Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        Log.d("UPDATES::",cursor.getString(0));
        db.close();
    }
    public void deletePrevTour()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("DELETE FROM tour_matches");
        db.execSQL("DELETE FROM tour_teams");
        db.close();
    }
    public void setTourFlag()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("insert into curr_scores('tour_flag') values('Y')");
        //db.execSQL("insert into curr_scores('team_id') values('"+t2+"')");
        db.close();
    }
    public String[] getTourMatch(int i)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String str[]=new String[2];
        String selectQuery = "SELECT T1,T2 FROM tour_matches where sno="+i;
        //Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        str[0]=cursor.getString(0);str[1]=cursor.getString(1);
        db.close();
        return str;
    }
    public void setTourMatchSno(int i)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        if(i==0)
            db.execSQL("UPDATE tour_vars SET sno=0");
        else
            db.execSQL("UPDATE tour_vars SET sno=sno+1");
        // String qry="UPDATE tour_vars SET sno=sno+1";
        //Log.d("CURRENT GAME Query::",qr);
        String selectQuery = "SELECT sno FROM tour_vars";
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        int sno=cursor.getInt(0);
        Log.d("TOUR_SNO::",""+sno);
        db.close();
    }
    public void setSeriesVars(int i)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        if(i==0)
            db.execSQL("insert into series_var values("+SeriesStart.gree+","+SeriesStart.max_overs+",0)");
        else
            db.execSQL("UPDATE series_var SET mno=mno+1");
        db.close();
    }
    public int getTourMatchSno()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT sno FROM tour_vars";
        //Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        int sno=cursor.getInt(0);
        db.close();
        Log.d("SERIAL nO::",""+sno);
        return sno;
    }
    public int getSeriesMatchValue(String col_name)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT "+col_name+" FROM series_var";
        //Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        int sno=cursor.getInt(0);
        db.close();
        Log.d("SERIAL nO::",""+sno);
        return sno;
    }
    public void updateTourMatches(String winner)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        int sno=getTourMatchSno();
        db.execSQL("UPDATE tour_matches SET res='"+winner+"' where sno="+sno);
        db.close();
    }
    public String getTourTeamName(int i)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT team_id FROM tour_teams";
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(i);
        String t=cursor.getString(0);
        //Log.d("TEAM-NAME::",i);
        db.close();
        cursor.close();
        return t;
    }
    public void updateNextRound(String win)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        int count[]=new int[2];
        String selectQuery = "SELECT count(T1),count(T2) FROM tour_matches";
        //Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        count[0]=cursor.getInt(0);count[1]=cursor.getInt(1);
        selectQuery = "SELECT team_name FROM teams where team_id='"+win+"'";
        cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        Log.d("QUERY::",selectQuery);
        String i=cursor.getString(0);
        if(count[0]==count[1])
        {
            //matlab T1 me entry hogi wining team k
            count[0]=count[0]+1;
            db.execSQL("UPDATE tour_matches SET T1='"+i+"' where sno="+count[0]);
        }
        else
        {
            count[1]=count[1]+1;
            db.execSQL("UPDATE tour_matches SET T2='"+i+"' where sno="+count[1]);
        }
        db.close();
    }
    public String getTourMatchWinner(int sno)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String team;
        String selectQuery = "SELECT res FROM tour_matches where sno="+sno;
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            cursor.moveToFirst();
            Log.d("QUERY::", selectQuery);
            team = cursor.getString(0);
            if(team.isEmpty())
                team="TBD";
        }
        catch(Exception e)
        {
            team="TBD";
        }
        return team;
    }
    public String[] getPlayersStatsBat(int pos,String tab_name)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT player_name,team_id,Matches,Runs FROM "+tab_name+" order by Runs desc";
        Log.d("QUERY::",selectQuery);
        String data[]=new String[4];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(pos);
        for(int i=0;i<4;i++){
            try
            {data[i]=""+cursor.getString(i);}
            catch(Exception e)
            {data[i]="";}
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]+" "+data[3]);
        db.close();
        cursor.close();
        return data;
    }
    public String[] getPlayersStatsBat50s(int pos,String tab_name)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT player_name,team_id,Matches,`50s` FROM "+tab_name+" order by `50s` desc";
        Log.d("QUERY::",selectQuery);
        String data[]=new String[4];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(pos);
        for(int i=0;i<4;i++){
            try
            {data[i]=""+cursor.getString(i);}
            catch(Exception e)
            {data[i]="";}
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]+" "+data[3]);
        db.close();
        cursor.close();
        return data;
    }
    public String[] getPlayersStatsBowl(int pos,String tab_name)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT player_name,team_id,bdots,bwick FROM "+tab_name+" order by bwick desc";
        Log.d("QUERY::",selectQuery);
        String data[]=new String[4];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(pos);
        for(int i=0;i<4;i++){
            try
            {data[i]=""+cursor.getString(i);}
            catch(Exception e)
            {data[i]="";}
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]+" "+data[3]);
        db.close();
        cursor.close();
        return data;
    }
    public String[] getPlayersStatsEcoBowl(int pos,String tab_name)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT player_name,team_id,bwick,bruns,bovers, bruns/(bovers/6) eco FROM "+tab_name+" where bovers>0 order by eco";
        Log.d("QUERY::",selectQuery);
        String data[]=new String[5];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(pos);
        for(int i=0;i<5;i++){
            try
            {data[i]=""+cursor.getString(i);}
            catch(Exception e)
            {data[i]="";}
        }
        int r=Integer.parseInt(data[3]);
        float ov=Integer.parseInt(data[4]);
        ov=(float)ov/6;
        float eco=(float)r/(ov);
        //Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]+" "+data[3]);
        data[3]=""+eco;
        db.close();
        cursor.close();
        return data;
    }

    public String[] getBatRatings(int pos)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT player_name,team_id,Runs,bat_rat FROM players order by bat_rat desc";
        Log.d("QUERY::",selectQuery);
        String data[]=new String[4];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(pos);
        for(int i=0;i<4;i++){
            try
            {data[i]=""+cursor.getString(i);}
            catch(Exception e)
            {data[i]="";}
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]+" "+data[3]);
        db.close();
        cursor.close();
        return data;
    }

    public String[] getSummary(int ii)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT * FROM curr_scores";
        Log.d("QUERY::",selectQuery);
        String data[]=new String[3];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(ii);
        for(int i=0;i<3;i++){
            try
            {data[i]=""+cursor.getString(i);}
            catch(Exception e)
            {data[i]="";}
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]);
        db.close();
        cursor.close();
        return data;
    }
    public String[] getSummaryTest(String team_id,int fb)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT * FROM curr_scores_test where team_id='"+team_id+"' and innings='"+fb+"'";
        Log.d("QUERY::",selectQuery);
        String data[]=new String[4];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(0);
        for(int i=0;i<4;i++){
            try
            {data[i]=""+cursor.getString(i);}
            catch(Exception e)
            {data[i]="";}
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]);
        db.close();
        cursor.close();
        return data;
    }
    public String[] getBatsmenScores(String team,int pos)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT player_name,bowl_by,Runs,Balls FROM curr_players where team_id='"+team+"' and border="+pos;
        Log.d("SCOREBOARD-QUERY::",selectQuery);
        String data[]=new String[4];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        for(int i=0;i<4;i++){
            try
            {data[i]=""+cursor.getString(i);}
            catch(Exception e)
            {data[i]="";}
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]);
        db.close();
        cursor.close();
        return data;
    }
    public String[] getBowlerScores(String team,int pos)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT player_name,bovers,bmaiden,bruns,bwick FROM curr_players where team_id='"+team+"' and can_bowl='Y' and bovers>0 order by border desc";
        Log.d("BOWLERBOARD-QUERY::",selectQuery);
        String data[]=new String[5];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(pos);
        for(int i=0;i<5;i++){
            try
            {
                data[i]=""+cursor.getString(i);
                if(i==1)
                {
                    int q=Integer.parseInt(data[i])/6;
                    int r=Integer.parseInt(data[i])%6;
                    if(r==0)
                        data[i]=""+q;
                    else
                        data[i]=""+q+"."+r;
                }
            }
            catch(Exception e)
            {data[i]="";}
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]);
        db.close();
        cursor.close();
        return data;
    }
    public String[] getTopPlayers(String team,int pos)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT player_name,Runs,Balls FROM curr_players where team_id='"+team+"' order by runs desc";
        Log.d("QUERY::",selectQuery);
        String data[]=new String[3];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(pos);
        for(int i=0;i<3;i++){
            try
            {data[i]=""+cursor.getString(i);}
            catch(Exception e)
            {data[i]="";}
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]);
        db.close();
        cursor.close();
        return data;
    }
    public String[] getTopPlayersTest(String team,int pos,int innings)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery;
        if(innings==1)
            selectQuery = "SELECT player_name,Runs,Balls FROM curr_players_test where team_id='"+team+"' order by runs desc";
        else
            selectQuery = "SELECT player_name,Runs_2,Balls_2 FROM curr_players_test where team_id='"+team+"' order by runs_2 desc";
        Log.d("QUERY::",selectQuery);
        String data[]=new String[3];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(pos);
        for(int i=0;i<3;i++){
            try
            {data[i]=""+cursor.getString(i);}
            catch(Exception e)
            {data[i]="";}
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]);
        db.close();
        cursor.close();
        return data;
    }
    public void resetCurrGame(int runsL,int maxOvers)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE curr_game SET Wickets=" + 0);
        db.execSQL("UPDATE curr_game SET Overs=" + 0);
        db.execSQL("UPDATE curr_game SET Balls=" + 0);
        db.execSQL("UPDATE curr_game SET row=" + 0);
        db.execSQL("UPDATE curr_game SET col=" + 0);
        db.execSQL("UPDATE curr_game SET Strike=" + 0);
        db.execSQL("UPDATE curr_game SET batter=" + 0);
        db.execSQL("UPDATE curr_game SET Runner=" + 1);
        db.execSQL("UPDATE curr_game SET flagBat=" + 1);
        db.execSQL("UPDATE curr_game SET batter_run=" + 0);
        db.execSQL("UPDATE curr_game SET batter_ball=" + 0);
        db.execSQL("UPDATE curr_game SET runner_run=" + 0);
        db.execSQL("UPDATE curr_game SET runner_ball=" + 0);
        db.execSQL("UPDATE curr_game SET runs=" + 0);
        db.execSQL("UPDATE curr_game SET runsleft=" + runsL);
        db.execSQL("UPDATE curr_game SET bat_four=" + 0);
        db.execSQL("UPDATE curr_game SET bat_six=" + 0);
        db.execSQL("UPDATE curr_game SET run_four=" + 0);
        db.execSQL("UPDATE curr_game SET run_six=" + 0);
        int tballs=maxOvers*6;
        db.execSQL("UPDATE curr_game SET tballs=" + tballs);
        int ind=runsL-1;
        db.execSQL("UPDATE curr_game SET ind=" + ind);
        String selectQuery = "SELECT you FROM curr_game";
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        int you=cursor.getInt(0);
        if(you==1)
        {db.execSQL("UPDATE curr_game SET you=2");}
        else
        {db.execSQL("UPDATE curr_game SET you=1");}
        db.close();
    }

    public void resetCurrGameTest(int runsL,int flag)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE curr_game_test SET Wickets=" + 0);
        db.execSQL("UPDATE curr_game_test SET Overs=" + 0);
        db.execSQL("UPDATE curr_game_test SET Balls=" + 0);
        db.execSQL("UPDATE curr_game_test SET row=" + 0);
        db.execSQL("UPDATE curr_game_test SET col=" + 0);
        db.execSQL("UPDATE curr_game_test SET Strike=" + 0);
        db.execSQL("UPDATE curr_game_test SET batter=" + 0);
        db.execSQL("UPDATE curr_game_test SET Runner=" + 1);
        db.execSQL("UPDATE curr_game_test SET flagBat=" + flag);
        db.execSQL("UPDATE curr_game_test SET batter_run=" + 0);
        db.execSQL("UPDATE curr_game_test SET batter_ball=" + 0);
        db.execSQL("UPDATE curr_game_test SET runner_run=" + 0);
        db.execSQL("UPDATE curr_game_test SET runner_ball=" + 0);
        db.execSQL("UPDATE curr_game_test SET runs=" + 0);
        db.execSQL("UPDATE curr_game_test SET runsleft=" + runsL);
        db.execSQL("UPDATE curr_game_test SET bat_four=" + 0);
        db.execSQL("UPDATE curr_game_test SET bat_six=" + 0);
        db.execSQL("UPDATE curr_game_test SET run_four=" + 0);
        db.execSQL("UPDATE curr_game_test SET run_six=" + 0);
        int tballs=500;
        db.execSQL("UPDATE curr_game_test SET tballs=" + tballs);
        int ind=runsL-1;
        String selectQuery = "SELECT you FROM curr_game_test";
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        int you=cursor.getInt(0);
        if(you==1)
        {db.execSQL("UPDATE curr_game_test SET you=0");}
        else
        {db.execSQL("UPDATE curr_game_test SET you=1");}
        db.close();
    }

    public void setToss(int i)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE curr_game SET you=" + i);
        db.close();
    }
    public void setTossTest(int i)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE curr_game_test SET you=" + i);
        db.close();
    }
    public void reverseTeams()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT team_id FROM curr_match";
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String t1=cursor.getString(0);
        cursor.moveToNext();
        String t2=cursor.getString(0);
        db.execSQL("DELETE FROM curr_match");
        db.execSQL("insert into curr_match values('"+t2+"')");
        Log.d("REVERSE","insert into curr_match values('"+t2+"')");
        db.execSQL("insert into curr_match values('"+t1+"')");
        Log.d("REVERSE","insert into curr_match values('"+t2+"')");
        db.execSQL("DELETE FROM curr_scores");
        db.execSQL("insert into curr_scores('team_id') values('"+t2+"')");
        db.execSQL("insert into curr_scores('team_id') values('"+t1+"')");
        db.close();
        cursor.close();
    }
    public String getCanBowlStatus(String pname)
    {
        String str;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT can_bowl from players where player_name='"+pname+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        try{
            str=cursor.getString(0);
        }
        catch(Exception e)
        {str="";}
        db.close();cursor.close();
        return str;
    }
    public int getLimitSix(String pname)
    {
        int str;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT sixlimit from players where player_name='"+pname+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        try{
            str=cursor.getInt(0);
        }
        catch(Exception e)
        {str=2;}
        db.close();cursor.close();
        return str;
    }
    public String getBowler(String mtch,int j)
    {
        String str;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT player_name from curr_players where team_id='"+mtch+"' and can_bowl='Y' order by border desc";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        try{
            cursor.moveToPosition(j);
            str=cursor.getString(0);}
        catch(Exception ex){str="";}
        db.close();cursor.close();
        return str;
    }

    public String getBowlerTest(String mtch,int j)
    {
        String str;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT player_name from curr_players_test where team_id='"+mtch+"' and can_bowl='Y' order by border desc";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        try{
            cursor.moveToPosition(j);
            str=cursor.getString(0);}
        catch(Exception ex){str="";}
        db.close();cursor.close();
        return str;
    }

    public int getBowlStats(String att_name,String pname)
    {
        String bowls;int b2;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT " +att_name+" from curr_players where player_name='"+pname+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        try {
            cursor.moveToFirst();
            bowls = cursor.getString(0);
            if(bowls==null){b2=0;}
            else{b2=cursor.getInt(0);}
        }
        catch(Exception ex){b2=0;}

        db.close();cursor.close();
        return b2;
    }
    public int getBowlStatsTest(String att_name,String pname)
    {
        String bowls;int b2;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT " +att_name+" from curr_players_test where player_name='"+pname+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        try {
            cursor.moveToFirst();
            bowls = cursor.getString(0);
            if(bowls==null){b2=0;}
            else{b2=cursor.getInt(0);}
        }
        catch(Exception ex){b2=0;}

        db.close();cursor.close();
        return b2;
    }
    public String[] getBowlStatsPlayer(String pname,String tabname)
    {
        String[] bowls=new String[4];
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT bwick,`4Ws`,`5Ws`,BB from "+tabname+" where player_name='"+pname+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        for(int i=0;i<4;i++) {
            try {
                bowls[i] = cursor.getString(i);
            } catch (Exception ex) {
                Log.d("EXPECTION",ex.toString());
                bowls[i] = "";
            }
        }
        db.close();cursor.close();
        return bowls;
    }
    public String[] getBowlStatsPlayerIPL(String pname)
    {
        String[] bowls=new String[4];
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT bwick,`4Ws`,`5Ws`,BB from ipl_records where player_name='"+pname+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        for(int i=0;i<4;i++) {
            try {
                bowls[i] = cursor.getString(i);
            } catch (Exception ex) {
                Log.d("EXPECTION",ex.toString());
                bowls[i] = "";
            }
        }
        db.close();cursor.close();
        return bowls;
    }
    public String[] getBowlStatsPlayerTest(String pname)
    {
        String[] bowls=new String[4];
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT bwick,`4Ws`,`5Ws`,BB from players_test where player_name='"+pname+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        for(int i=0;i<4;i++) {
            try {
                bowls[i] = cursor.getString(i);
            } catch (Exception ex) {
                Log.d("EXPECTION",ex.toString());
                bowls[i] = "";
            }
        }
        db.close();cursor.close();
        return bowls;
    }
    public int getLast5Match(String pname)
    {
        int count=0;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT count(pname) from prev_perf where pname='"+pname+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
            try {
                count = cursor.getInt(0);
            } catch (Exception ex) {
                Log.d("EXPECTION",ex.toString());
                count=0;
            }
        db.close();cursor.close();
        return count;
    }
    public int getLast5MatchTest(String pname)
    {
        int count=0;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT count(pname) from prev_perf_test where pname='"+pname+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        try {
            count = cursor.getInt(0);
        } catch (Exception ex) {
            Log.d("EXPECTION",ex.toString());
            count=0;
        }
        db.close();cursor.close();
        return count;
    }
    public String[] getLast5MatchData(String pname,int i)
    {
        int count=0;
        String str[]=new String[3];
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT versus,runs,balls,b_overs,b_runs,b_wick from prev_perf where pname='"+pname+"' and sno="+i;
        Log.d("QUERY::",selectQuery);
        Cursor cursor= db.rawQuery(selectQuery, null);;
        try {
            cursor.moveToFirst();
            str[0] = cursor.getString(0);
            str[1] = cursor.getString(1) + "(" + cursor.getString(2) + ")";
            int zz = Integer.parseInt(cursor.getString(3)) / 6;
            str[2] = cursor.getString(4) + "-" + cursor.getString(5) + " (" + zz + ")";
        }
        catch (Exception ex){str[0]="-";str[1]="-";str[2]="-";}
            db.close();cursor.close();
        return str;
    }
    public String[] getLast5MatchDataTest(String pname,int i)
    {
        int count=0;
        String str[]=new String[5];
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT versus,runs,runs_2,balls,balls_2,b_overs,b_overs_2,b_runs,b_runs_2,b_wick,b_wick_2 from prev_perf_test where pname='"+pname+"' and sno="+i;
        Log.d("QUERY::",selectQuery);
        Cursor cursor= db.rawQuery(selectQuery, null);;
        try {
            cursor.moveToFirst();
            str[0] = cursor.getString(0);
            str[1] = cursor.getString(1) + "(" + cursor.getString(3) + ")";
            str[2] = cursor.getString(2) + "(" + cursor.getString(4) + ")";
            int zz = Integer.parseInt(cursor.getString(5)) / 6;
            int zz2 = Integer.parseInt(cursor.getString(6)) / 6;
            str[3] = cursor.getString(7) + "-" + cursor.getString(9) + "(" + zz + ")";
            str[4] = cursor.getString(8) + "-" + cursor.getString(10) + "(" + zz2 + ")";
        }
        catch (Exception ex){str[0]="-";str[1]="-";str[2]="-";str[3]="-";str[4]="-";}
        db.close();cursor.close();
        return str;
    }
    public void updateBowlStats(String att_name,String pname,int k)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT " +att_name+" from curr_players where player_name='"+pname+"'";
        Log.d("QUERY::",selectQuery);
        int b2=0;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String bowls=cursor.getString(0);
        if(bowls==null){b2=0;db.execSQL("UPDATE curr_players SET "+att_name+"=" +b2+" where player_name='"+pname+"'");}
        else{}
        db.execSQL("UPDATE curr_players SET "+att_name+"=" +att_name+"+"+k+" where player_name='"+pname+"'");
        Log.d("UP_BOWL","UPDATE curr_players SET "+att_name+"=" +att_name+"+"+k+" where player_name='"+pname+"'");
        db.close();cursor.close();
    }
    public void updateBowlStatsTest(String att_name,String pname,int k)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT " +att_name+" from curr_players_test where player_name='"+pname+"'";
        Log.d("QUERY::",selectQuery);
        int b2=0;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String bowls=cursor.getString(0);
        if(bowls==null){b2=0;db.execSQL("UPDATE curr_players_test SET "+att_name+"=" +b2+" where player_name='"+pname+"'");}
        else{}
        db.execSQL("UPDATE curr_players_test SET "+att_name+"=" +att_name+"+"+k+" where player_name='"+pname+"'");
        Log.d("UP_BOWL","UPDATE curr_players_test SET "+att_name+"=" +att_name+"+"+k+" where player_name='"+pname+"'");
        db.close();cursor.close();
    }
    public String[] getTopPlayersBowler(String team,int pos)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT player_name,bruns,bwick FROM curr_players where team_id='"+team+"' order by bwick desc";
        Log.d("QUERY::",selectQuery);
        String data[]=new String[3];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(pos);
        for(int i=0;i<3;i++){
            try
            {data[i]=""+cursor.getString(i);}
            catch(Exception e)
            {data[i]="";}
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]);
        db.close();
        cursor.close();
        return data;
    }
    public String[] getTopPlayersBowlerTest(String team,int pos,int innings)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery;
        if(innings==1)
            selectQuery = "SELECT player_name,bruns,bwick FROM curr_players_test where team_id='"+team+"' and can_bowl='Y' and bovers > 0 order by bwick desc,bruns/(bovers/6)";
        else
            selectQuery = "SELECT player_name,bruns_2,bwick_2 FROM curr_players_test where team_id='"+team+"' and can_bowl='Y' and bovers_2 > 0 order by bwick_2 desc,bruns_2/(bovers_2/6)";
        Log.d("QUERY::",selectQuery);
        String data[]=new String[3];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(pos);
        for(int i=0;i<3;i++){
            try
            {data[i]=""+cursor.getString(i);}
            catch(Exception e)
            {data[i]="";}
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]);
        db.close();
        cursor.close();
        return data;
    }

    public void deletePrevTourRR()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("DELETE FROM rr_matches");
        db.execSQL("DELETE FROM rr_teams");
        db.execSQL("DELETE FROM rr_rankings");
        db.execSQL("DELETE FROM rr_stats");
        db.close();
    }
    public void deletePrevTourMC()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("DELETE FROM mc_fixtures");
        db.execSQL("DELETE FROM mc_standings_r1");
        db.execSQL("DELETE FROM mc_standings_r2");
        db.execSQL("DELETE FROM mc_stats");
        db.close();
    }
    public void deletePrevTourSeries()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("DELETE FROM series_fixtures");
        db.execSQL("DELETE FROM series_rankings");
        db.execSQL("DELETE FROM series_var");
        db.execSQL("DELETE FROM series_stats");
        db.close();
    }
    public void setTourMatchesRR(int i)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        //db.execSQL("DELETE FROM tour_matches");
        db.execSQL("insert into rr_matches('sno') values("+i+")");
        db.close();
    }
    public void setTourMatchesMC(int i)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        //db.execSQL("DELETE FROM tour_matches");
        db.execSQL("insert into mc_fixtures('sno') values("+i+")");
        db.close();
    }
    public void setTourMatchesIPL(int i)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        //db.execSQL("DELETE FROM tour_matches");
        db.execSQL("insert into ipl_matches('sno') values("+i+")");
        db.close();
    }
    public void addTourTeamsRR(String tm1,String tm2,int i)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        //db.execSQL("DELETE FROM rr_matches");

        db.execSQL("update rr_matches set T1='"+tm1+"' where sno="+i);
        db.execSQL("update rr_matches set T2='"+tm2+"' where sno="+i);
        db.close();
    }
    public void addTourTeamsMC(String tm1,String tm2,int i)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        //db.execSQL("DELETE FROM rr_matches");
        db.execSQL("update mc_fixtures set T1='"+tm1+"' where sno="+i);
        db.execSQL("update mc_fixtures set T2='"+tm2+"' where sno="+i);
        db.close();
    }

    public String[] getMCFixtures(int sno)
    {
        sno=sno+1;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT T1,T2,res FROM mc_fixtures where sno="+sno;
        Log.d("QUERY::",selectQuery);
        String data[]=new String[3];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        for(int i=0;i<3;i++){
            try
            {data[i]=""+cursor.getString(i);}
            catch(Exception e)
            {data[i]="";}
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]);
        db.close();
        cursor.close();
        return data;
    }
    public String[] getIPLFixtures(int sno)
    {
        sno=sno+1;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT T1,T2,res FROM ipl_matches where sno="+sno;
        Log.d("QUERY::",selectQuery);
        String data[]=new String[3];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        for(int i=0;i<3;i++){
            try
            {data[i]=""+cursor.getString(i);}
            catch(Exception e)
            {data[i]="";}
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]);
        db.close();
        cursor.close();
        return data;
    }
    public String[] getSeriesFixtures(int sno)
    {
        sno=sno+1;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT T1,T2,res FROM series_fixtures where sno="+sno;
        Log.d("QUERY::",selectQuery);
        String data[]=new String[3];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        for(int i=0;i<3;i++){
            try
            {data[i]=""+cursor.getString(i);}
            catch(Exception e)
            {data[i]="";}
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]);
        db.close();
        cursor.close();
        return data;
    }
    public String getTeamId(String t1)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        String s1,s2;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT team_id FROM teams where team_name in ('"+t1+"')";
        Log.d("TEAM-ID QRY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        s1=cursor.getString(0);
        Log.d("TEAM-ID QRY::",selectQuery);
        db.close();
        cursor.close();
        return s1;
    }
    public void setRankings(String i)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("insert into rr_rankings values('"+i+"',0,0,0,0,0)");
        db.close();
    }
    public void setRankingsMCR1(String i)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("insert into mc_standings_r1 values('"+i+"',0,0,0,0,0)");
        db.close();
    }
    public void setRankingsMCR2(String i)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("insert into mc_standings_r2 values('"+i+"',0,0,0,0,0)");
        db.close();
    }
    public void updateRRMatches(String winner)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        int sno=getTourMatchSno();
        db.execSQL("UPDATE tour_matches SET res='"+winner+"' where sno="+sno);
        db.close();
    }
    public void updateRR(String win,int i)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE series_fixtures SET res='"+win+"' where sno="+i);
        db.close();
    }
    public void updateMC(String win,int i)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE mc_fixtures SET res='"+win+"' where sno="+i);
        db.close();
    }
    public void updateIPL(String win,int i,String loose)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE ipl_matches SET res='"+win+"' where sno="+i);
        String win2=getFranchiseName(win);
        String loose2=getFranchiseName(loose);
        if(getTourMatchSno()==57)
        {
            db.execSQL("UPDATE ipl_matches SET T1='"+win2+"' where sno=60");
            db.execSQL("UPDATE ipl_matches SET T1='"+loose2+"' where sno=59");
        }
        else if(getTourMatchSno()==58)
        {
            db.execSQL("UPDATE ipl_matches SET T2='"+win2+"' where sno=59");
        }
        db.close();
    }
    public double setRunRate(String team)
    {
        int runs,balls;float rr;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT sum(Runs) FROM curr_players where team_id='"+team+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        runs=cursor.getInt(0);
        selectQuery = "SELECT sum(Balls) FROM curr_players where team_id='"+team+"'";
        Log.d("QUERY::",selectQuery);
        cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        balls=cursor.getInt(0);
        rr=(float)(runs*6)/(balls);
        double rrr;
        //rrr=(float)(rr*100)/100;
        rrr = Math.round(rr * 100.0) / 100.0;
        db.close();
        cursor.close();
        return rrr;
    }
    public void updateRRPointsTable(String w,String l,double nr,double nr2)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE series_rankings SET Played=Played+1 where team='"+w+"'");
        db.execSQL("UPDATE series_rankings SET Win=Win+1 where team='"+w+"'");
        db.execSQL("UPDATE series_rankings SET NRR=NRR+"+nr+" where team='"+w+"'");
        db.execSQL("UPDATE series_rankings SET points=points+2 where team='"+w+"'");
        db.execSQL("UPDATE series_rankings SET Played=Played+1 where team='"+l+"'");
        db.execSQL("UPDATE series_rankings SET Loss=Loss+1 where team='"+l+"'");
        db.execSQL("UPDATE series_rankings SET NRR=NRR+"+nr2+" where team='"+l+"'");
        db.close();
    }
    public void updateMCPointsTable(String w,String l,double nr,double nr2,String tab)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE "+tab+" SET Played=Played+1 where team='"+w+"'");
        db.execSQL("UPDATE "+tab+" SET Win=Win+1 where team='"+w+"'");
        db.execSQL("UPDATE "+tab+" SET NRR=NRR+"+nr+" where team='"+w+"'");
        db.execSQL("UPDATE "+tab+" SET points=points+2 where team='"+w+"'");
        db.execSQL("UPDATE "+tab+" SET Played=Played+1 where team='"+l+"'");
        db.execSQL("UPDATE "+tab+" SET Loss=Loss+1 where team='"+l+"'");
        db.execSQL("UPDATE "+tab+" SET NRR=NRR+"+nr2+" where team='"+l+"'");
        db.close();
    }
    public void updateIPLPointsTable(String w,String l,double nr,double nr2)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE ipl_rankings SET Played=Played+1 where team='"+w+"'");
        db.execSQL("UPDATE ipl_rankings SET Win=Win+1 where team='"+w+"'");
        db.execSQL("UPDATE ipl_rankings SET NRR=NRR+"+nr+" where team='"+w+"'");
        db.execSQL("UPDATE ipl_rankings SET points=points+2 where team='"+w+"'");
        db.execSQL("UPDATE ipl_rankings SET Played=Played+1 where team='"+l+"'");
        db.execSQL("UPDATE ipl_rankings SET Loss=Loss+1 where team='"+l+"'");
        db.execSQL("UPDATE ipl_rankings SET NRR=NRR+"+nr2+" where team='"+l+"'");
        db.close();
    }
    public void setRRStats()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("INSERT INTO rr_stats(player_name,team_id)SELECT player_name,team_id FROM players");
        db.execSQL("update rr_stats set matches=0");
        db.execSQL("update rr_stats set Runs=0");
        db.execSQL("update rr_stats set `30s`=0");
        db.execSQL("update rr_stats set `50s`=0");
        db.execSQL("update rr_stats set `100s`=0");
        db.execSQL("update rr_stats set `4s`=0");
        db.execSQL("update rr_stats set `6s`=0");
        db.execSQL("update rr_stats set Highest=0");
        db.execSQL("update rr_stats set Balls=0");
        db.execSQL("update rr_stats set can_bowl='U'");
        db.execSQL("update rr_stats set bovers=0");
        db.execSQL("update rr_stats set bruns=0");
        db.execSQL("update rr_stats set bdots=0");
        db.execSQL("update rr_stats set bwick=0");
        db.execSQL("update rr_stats set `4Ws`=0");
        db.execSQL("update rr_stats set `5Ws`=0");
        db.execSQL("update rr_stats set BB='0/0'");
        db.execSQL("update rr_stats set bat_rat=0");
        db.execSQL("update rr_stats set nouts=0");
        db.close();
    }
    public void setMCStats()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("INSERT INTO mc_stats(player_name,team_id)SELECT player_name,team_id FROM players_odi");
        db.execSQL("update mc_stats set matches=0");
        db.execSQL("update mc_stats set Runs=0");
        db.execSQL("update mc_stats set `30s`=0");
        db.execSQL("update mc_stats set `50s`=0");
        db.execSQL("update mc_stats set `100s`=0");
        db.execSQL("update mc_stats set `4s`=0");
        db.execSQL("update mc_stats set `6s`=0");
        db.execSQL("update mc_stats set Highest=0");
        db.execSQL("update mc_stats set Balls=0");
        db.execSQL("update mc_stats set can_bowl='U'");
        db.execSQL("update mc_stats set bovers=0");
        db.execSQL("update mc_stats set bruns=0");
        db.execSQL("update mc_stats set bdots=0");
        db.execSQL("update mc_stats set bwick=0");
        db.execSQL("update mc_stats set `4Ws`=0");
        db.execSQL("update mc_stats set `5Ws`=0");
        db.execSQL("update mc_stats set BB='0/0'");
        db.execSQL("update mc_stats set bat_rat=0");
        db.execSQL("update mc_stats set nouts=0");
        db.close();
    }
    public String[] getSeriesStandings(int pos)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "select * from series_rankings order by points desc,NRR desc";
        Log.d("QUERY::",selectQuery);
        String data[]=new String[6];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(pos);
        for(int i=0;i<6;i++){
            try
            {data[i]=""+cursor.getString(i);}
            catch(Exception e)
            {data[i]="";}
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]+" "+data[3]);
        db.close();
        cursor.close();
        return data;
    }
    public String[] getMCStandings(int pos,String tbl)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "select * from "+tbl+" order by points desc,NRR desc";
        Log.d("QUERY::",selectQuery);
        String data[]=new String[6];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(pos);
        for(int i=0;i<6;i++){
            try
            {data[i]=""+cursor.getString(i);}
            catch(Exception e)
            {data[i]="";}
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]+" "+data[3]);
        db.close();
        cursor.close();
        return data;
    }
    public String[] getIPLStandings(int pos)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "select * from ipl_rankings order by points desc,NRR desc";
        Log.d("QUERY::",selectQuery);
        String data[]=new String[6];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(pos);
        for(int i=0;i<6;i++){
            try
            {data[i]=""+cursor.getString(i);}
            catch(Exception e)
            {data[i]="";}
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]+" "+data[3]);
        db.close();
        cursor.close();
        return data;
    }
    public String getTeamNameFromId(String id)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT team_name FROM teams where team_id='"+id+"'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String i=cursor.getString(0);
        Log.d("TEAM-NAME::",i);
        db.close();
        cursor.close();
        return i;
    }
    public void updateRRSemiFinals()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "select team from rr_rankings order by points desc,NRR desc";
        Log.d("QUERY::",selectQuery);
        String data[]=new String[4];
        Cursor cursor = db.rawQuery(selectQuery, null);
        for(int i=0;i<4;i++) {
            cursor.moveToPosition(i);
            data[i]=getTeamNameFromId(cursor.getString(0));
        }
        Log.d("TEAMS ",data[0]+" "+data[1]+" "+data[2]+" "+data[3]);
        db.execSQL("update rr_matches set T1='"+data[0]+"' where sno=57");
        db.execSQL("update rr_matches set T2='"+data[1]+"' where sno=57");
        db.execSQL("update rr_matches set T1='"+data[2]+"' where sno=58");
        db.execSQL("update rr_matches set T2='"+data[3]+"' where sno=58");
        db.close();
        cursor.close();
    }
    public void updateMCSemiFinals()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "select team from mc_standings_r2 order by points desc,NRR desc";
        Log.d("QUERY::",selectQuery);
        String data[]=new String[4];
        Cursor cursor = db.rawQuery(selectQuery, null);
        for(int i=0;i<4;i++) {
            cursor.moveToPosition(i);
            data[i]=getTeamNameFromId(cursor.getString(0));
        }
        Log.d("TEAMS ",data[0]+" "+data[1]+" "+data[2]+" "+data[3]);
        db.execSQL("update mc_fixtures set T1='"+data[0]+"' where sno=61");
        db.execSQL("update mc_fixtures set T2='"+data[1]+"' where sno=61");
        db.execSQL("update mc_fixtures set T1='"+data[2]+"' where sno=62");
        db.execSQL("update mc_fixtures set T2='"+data[3]+"' where sno=62");
        db.close();
        cursor.close();
    }
    public List<String> getMCTopR1Teams()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "select team from mc_standings_r1 order by points desc,NRR desc";
        Log.d("QUERY::",selectQuery);
        List<String> data = new ArrayList<String>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        for(int i=0;i<6;i++) {
            cursor.moveToPosition(i);
            data.add(getTeamNameFromId(cursor.getString(0)));
        }
        //Log.d("TEAMS ",data[0]+" "+data[1]+" "+data[2]+" "+data[3]);
        db.close();
        cursor.close();
        return data;
    }
    public void updateIPLPlayOffFixture()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "select team from ipl_rankings order by points desc,NRR desc";
        Log.d("QUERY::",selectQuery);
        String data[]=new String[4];
        Cursor cursor = db.rawQuery(selectQuery, null);
        for(int i=0;i<4;i++) {
            cursor.moveToPosition(i);
            data[i]=getFranchiseName(cursor.getString(0));
        }
        Log.d("TEAMS ",data[0]+" "+data[1]+" "+data[2]+" "+data[3]);
        db.execSQL("update ipl_matches set T1='"+data[0]+"' where sno=57");
        db.execSQL("update ipl_matches set T2='"+data[1]+"' where sno=57");
        db.execSQL("update ipl_matches set T1='"+data[2]+"' where sno=58");
        db.execSQL("update ipl_matches set T2='"+data[3]+"' where sno=58");
        db.close();
        cursor.close();
    }
    public void updateRRFinals()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "select res from rr_matches where sno in(57,58)";
        Log.d("QUERY::",selectQuery);
        String data[]=new String[2];
        Cursor cursor = db.rawQuery(selectQuery, null);
        for(int i=0;i<2;i++) {
            cursor.moveToPosition(i);
            data[i]=getTeamNameFromId(cursor.getString(0));
        }
        Log.d("TEAMS ",data[0]+" "+data[1]);
        //  db.execSQL("update rr_matches set T1='"+data[0]+"' where sno=29");
        // db.execSQL("update rr_matches set T2='"+data[1]+"' where sno=29");
        db.execSQL("update rr_matches set T1='"+data[0]+"' where sno=59");
        db.execSQL("update rr_matches set T2='"+data[1]+"' where sno=59");
        db.close();
        cursor.close();
    }

    public void updateMCFinals()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "select res from mc_fixtures where sno in(61,62)";
        Log.d("QUERY::",selectQuery);
        String data[]=new String[2];
        Cursor cursor = db.rawQuery(selectQuery, null);
        for(int i=0;i<2;i++) {
            cursor.moveToPosition(i);
            data[i]=getTeamNameFromId(cursor.getString(0));
        }
        Log.d("TEAMS ",data[0]+" "+data[1]);
        //  db.execSQL("update rr_matches set T1='"+data[0]+"' where sno=29");
        // db.execSQL("update rr_matches set T2='"+data[1]+"' where sno=29");
        db.execSQL("update mc_fixtures set T1='"+data[0]+"' where sno=63");
        db.execSQL("update mc_fixtures set T2='"+data[1]+"' where sno=63");
        db.close();
        cursor.close();
    }

    public void updateIPLFinals()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "select res from ipl_matches where sno=59";
        Log.d("QUERY::",selectQuery);
        String data;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        data=getFranchiseName(cursor.getString(0));
        Log.d("TEAMS ",data);
        //  db.execSQL("update rr_matches set T1='"+data[0]+"' where sno=29");
        // db.execSQL("update rr_matches set T2='"+data[1]+"' where sno=29");
        db.execSQL("update rr_matches set T2='"+data+"' where sno=60");
        db.close();
        cursor.close();
    }

    public String giveSeriesWinner(int sno)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "select res from series_fixtures where sno = "+sno;
        Log.d("QUERY::",selectQuery);
        String data;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        data=cursor.getString(0);
        db.close();
        cursor.close();
        return data;
    }
    public String giveMCWinner()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "select res from mc_fixtures where sno = 63";
        Log.d("QUERY::",selectQuery);
        String data;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        data=cursor.getString(0);
        String d2=getTeamNameFromId(data);
        db.close();
        cursor.close();
        return d2;
    }
    public String giveIPLWinner()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "select res from ipl_matches where sno = 60";
        Log.d("QUERY::",selectQuery);
        String data;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        data=cursor.getString(0);
        db.close();
        cursor.close();
        return data;
    }
    public String[] getEconomicBowlers(String teamID)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "select player_name from curr_players where team_id='"+teamID+"' and can_bowl='Y' and bovers > 0 order by bruns/(bovers/6)";
        Log.d("QUERY::",selectQuery);
        String data[]=new String[6];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        for(int i=0;i<6;i++){
            try
            {data[i]=""+cursor.getString(0);}
            catch(Exception e)
            {data[i]="";}
            cursor.moveToNext();
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]+" "+data[3]);
        db.close();
        cursor.close();
        return data;
    }

    public String[] getWicketBowlersTest(int fb,String teamID)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery;
        if(fb==0)
         selectQuery = "select player_name from curr_players_test where team_id='"+teamID+"' and can_bowl='Y' and bovers > 0 order by bwick desc,bruns/(bovers/6)";
        else
         selectQuery = "select player_name from curr_players_test where team_id='"+teamID+"' and can_bowl='Y' and bovers_2 > 0 order by bwick_2 desc,bruns_2/(bovers_2/6)";
        Log.d("QUERY::",selectQuery);
        String data[]=new String[6];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        for(int i=0;i<6;i++){
            try
            {data[i]=""+cursor.getString(0);}
            catch(Exception e)
            {data[i]="";}
            cursor.moveToNext();
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]+" "+data[3]);
        db.close();
        cursor.close();
        return data;
    }

    public String[] getQuotaBowlers(String tname,String bname)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "select player_name from curr_players where team_id='"+tname+"' and can_bowl='Y' and bovers < 60 and bovers > 0 and player_name not in ('"+bname+"') order by bruns/(bovers/6)";
        Log.d("QUOTA_QUERY::",selectQuery);
        String data[]=new String[6];
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        for(int i=0;i<6;i++){
            try
            {data[i]=""+cursor.getString(0);}
            catch(Exception e)
            {data[i]="";}
            cursor.moveToNext();
        }
        Log.d("PLAYERS ",data[0]+" "+data[1]+" "+data[2]+" "+data[3]);
        db.close();
        cursor.close();
        return data;
    }
    public int getBowlerNo(String tname,String bname)
    {
        int i;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT player_name from curr_players where team_id='"+tname+"' and can_bowl='Y' order by border desc";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        for(i=0;i<6;i++){
            if(cursor.getString(0).equalsIgnoreCase(bname))
            {Log.d("FOUND AT I=::",""+i);
                break;}
            else
                cursor.moveToNext();
        }
        return i;
    }
    public int getConfigValue(String config)
    {
        int i;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT config_value from game_configs where config_name='"+config+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        i=cursor.getInt(0);
        return i;
    }
    public int getBowlerNoTest(String tname,String bname)
    {
        int i;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT player_name from curr_players_test where team_id='"+tname+"' and can_bowl='Y' order by border desc";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        for(i=0;i<6;i++){
            if(cursor.getString(0).equalsIgnoreCase(bname))
            {Log.d("FOUND AT I=::",""+i);
                break;}
            else
                cursor.moveToNext();
        }
        return i;
    }

    public String getSquadPlayer(int j,String team)
    {
        String str=new String();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "select player_name from players where team_id='"+team+"' order by player_id";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        try {
            cursor.moveToPosition(j);
            str = cursor.getString(0);
            Log.d("The player is: ",str);
        }
        catch(Exception ex){str="";}
        db.close();cursor.close();
        return str;
    }
    public void addPlayer(String p,String tm,String b)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        //db.execSQL("DELETE FROM tour_teams");
        String selectQuery = "select count(player_name) from players where team_id='"+tm+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String is_foreign="Y";
        int z=cursor.getInt(0)+1;
        String pid=tm+""+z;
        cursor.close();
        db.execSQL("insert into players values('"+tm+"','"+pid+"','"+p+"',0,0,0.0,0,0,0,0,0,0,0,0,'"+b+"',0,0,0,0,0,0,'0/0',0,4,0)");
        db.execSQL("insert into players_t20 values('"+tm+"','"+pid+"','"+p+"',0,0,0.0,0,0,0,0,0,0,0,0,'"+b+"',0,0,0,0,0,0,'0/0',0,4,0)");
        db.execSQL("insert into players_odi values('"+tm+"','"+pid+"','"+p+"',0,0,0.0,0,0,0,0,0,0,0,0,'"+b+"',0,0,0,0,0,0,'0/0',0,4,0)");
        db.execSQL("insert into players_test values('"+tm+"','"+pid+"','"+p+"',0,0,0,0.0,0,0,0,0,0,0,0,0,'"+b+"',0,0,0,0,0,0,'0/0',0,4,0)");
        if(tm.equalsIgnoreCase("IND"))
        {is_foreign="N";}
        db.execSQL("insert into players_pool values('"+p+"','"+tm+"','"+b+"','','','','','"+is_foreign+"','')");
        db.execSQL("insert into ipl_records values('"+tm+"','"+p+"',0,0,0.0,0,0,0,0,0,0,0,'"+b+"',0,0,0,0,0,0,'0/0',0,0)");
        db.close();
    }
    public String getPlayerIpl(String colname,int j)
    {
        String str="Hanuma";
        //Random rand = new Random();
        int n,n2;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT player_name from players_pool where category='"+colname+"' and franchise=''";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.d("CURSOR_COUNT:",""+cursor.getCount());
        //n2=cursor.getCount()+1;
        //n=rand.nextInt(n2);
        try{
            cursor.moveToFirst();
            str=cursor.getString(0);}
        catch(Exception ex){str="";}
        db.close();cursor.close();
        return str;
    }
    public String getPoolId()
    {
        String str="";
        int ar1,ar2;
        //ar1=new int[8];ar2=new int[8];
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT total_players,max_players,pool from pools_info";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        for(int i=0;i<cursor.getCount();i++)
        {
            cursor.moveToPosition(i);
            ar1=cursor.getInt(0);
            ar2=cursor.getInt(1);
            str=cursor.getString(2);
            if(ar1<ar2)
            {
                break;
            }
        }
        db.close();cursor.close();
        return str;
    }
    public String getPoolName(String t1)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        String s1,s2;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT pool_name from pools_info where pool in ('"+t1+"')";
        Log.d("TEAM-ID QRY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        s1=cursor.getString(0);
        Log.d("TEAM-ID QRY::",selectQuery);
        db.close();
        cursor.close();
        return s1;
    }
    public int getPoolNumber(String t1)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        int s1;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT max_players from pools_info where pool in ('"+t1+"')";
        Log.d("TEAM-ID QRY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        s1=cursor.getInt(0);
        Log.d("TEAM-ID QRY::",selectQuery);
        db.close();
        cursor.close();
        return s1;
    }
    public void updatePools(String str)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE pools_info set total_players=total_players+1 where pool='"+str+"'");
        db.close();
    }
    public String getFranchiseName(String t1)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        String s1,s2;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT fran_name from franchise_pool where fran_id in ('"+t1+"')";
        Log.d("TEAM-ID QRY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        s1=cursor.getString(0);
        Log.d("TEAM-ID QRY::",selectQuery);
        db.close();
        cursor.close();
        return s1;
    }
    public int getBasePrice(String pname)
    {
        int s1;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT baseprice from players_pool where player_name in ('"+pname+"')";
        Log.d("TEAM-ID QRY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        s1=cursor.getInt(0);
        Log.d("TEAM-ID QRY::",selectQuery);
        db.close();
        cursor.close();
        return s1;
    }
    public String getFranchiseId(String t1)
    {
        //SQLiteDatabase db = this.getWritableDatabase();
        String s1,s2;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT fran_id from franchise_pool where fran_name in ('"+t1+"')";
        Log.d("TEAM-ID QRY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        s1=cursor.getString(0);
        Log.d("TEAM-ID QRY::",selectQuery);
        db.close();
        cursor.close();
        return s1;
    }
    public void updatePlayersPool(String p,int price,String fran)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE players_pool set franchise='"+fran+"' where player_name='"+p+"'");
        db.execSQL("UPDATE players_pool set soldprice="+price+" where player_name='"+p+"'");
        String selectQuery = "select count(player_name) from players_pool where franchise='"+fran+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        int z=cursor.getInt(0);
        String pid=fran+""+z;
        cursor.close();
        db.execSQL("UPDATE players_pool set franchise_id='"+pid+"' where player_name='"+p+"'");
        db.close();
    }
    public void updateFranchisePool(int price,String p,String fran,String pool)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("UPDATE franchise_pool set budget=budget-"+price+" where fran_id='"+fran+"'");
        String selectQuery = "select is_foreign from players_pool where player_name='"+p+"'";
        Log.d("QUERY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String z=cursor.getString(0);
        if(z.equalsIgnoreCase("Y"))
            db.execSQL("UPDATE franchise_pool set foreign_players=foreign_players-1 where fran_id='"+fran+"'");
        db.execSQL("UPDATE franchise_pool set "+pool+"="+pool+"-1 where fran_id='"+fran+"'");
        db.execSQL("UPDATE franchise_pool set total_players=total_players-1 where fran_id='"+fran+"'");
        db.close();
    }
    public int getMax(String fran,String col)
    {
        int s1;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT "+col+" from franchise_pool where fran_name in ('"+fran+"')";
        Log.d("TEAM-ID QRY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        s1=cursor.getInt(0);
        Log.d("TEAM-ID QRY::",selectQuery);
        db.close();
        cursor.close();
        return s1;
    }
    public int getBudget(String fran)
    {
        int s1;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT budget from franchise_pool where fran_name in ('"+fran+"')";
        Log.d("TEAM-ID QRY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        s1=cursor.getInt(0);
        Log.d("TEAM-ID QRY::",selectQuery);
        db.close();
        cursor.close();
        return s1;
    }
    public int getForeignPlayerCount(String fran)
    {
        int s1;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT foreign_players from franchise_pool where fran_id in ('"+fran+"')";
        Log.d("TEAM-ID QRY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        s1=cursor.getInt(0);
        Log.d("TEAM-ID QRY::",selectQuery);
        db.close();
        cursor.close();
        return s1;
    }
    public int getTotalPlayers(String fran,String tabname)
    {
        int s1;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT count(player_name) from "+tabname+" where team_id in ('"+fran+"')";
        Log.d("TEAM-ID QRY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        s1=cursor.getInt(0);
        Log.d("TEAM-ID QRY::",selectQuery);
        db.close();
        cursor.close();
        return s1;
    }
    public String getIsForeign(String pname)
    {
        String s1;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT is_foreign from players_pool where player_name in ('"+pname+"')";
        Log.d("TEAM-ID QRY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        s1=cursor.getString(0);
        Log.d("TEAM-ID QRY::",selectQuery);
        db.close();
        cursor.close();
        return s1;
    }
    public String getIsBowler(String pname)
    {
        String s1;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String selectQuery = "SELECT can_bowl from players_pool where player_name in ('"+pname+"')";
        Log.d("TEAM-ID QRY::",selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        s1=cursor.getString(0);
        Log.d("TEAM-ID QRY::",selectQuery);
        db.close();
        cursor.close();
        return s1;
    }
    public void updateFranchiseQuota(String col)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String str=col;
        String str2=str.replace('2','1');
        Log.d("The replaced col is::",str2);
        int i=0;
        db.execSQL("UPDATE franchise_pool set "+col+"="+col+"+"+str2+"");
        db.execSQL("UPDATE franchise_pool set "+str2+"=0");
        db.close();
    }
    public void deletePrevTourIPL()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("DELETE FROM ipl_matches");
        //db.execSQL("DELETE FROM ipl_teams");
        db.execSQL("DELETE FROM ipl_rankings");
        db.execSQL("DELETE FROM ipl_stats");
        db.close();
    }
    public List<String> getAllTeamsIPL(){
        List<String> names = new ArrayList<String>();
        String selectQuery = "SELECT team_id FROM ipl_teams";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                names.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning names
        return names;
    }
    public void addTourTeamsIPL(String tm1,String tm2,int i)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        //db.execSQL("DELETE FROM rr_matches");

        db.execSQL("update ipl_matches set T1='"+tm1+"' where sno="+i);
        db.execSQL("update ipl_matches set T2='"+tm2+"' where sno="+i);
        db.close();
    }
    public void setIPLStats()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("INSERT INTO ipl_stats(player_name,team_id)SELECT player_name,franchise FROM players_pool");
        db.execSQL("update ipl_stats set matches=0");
        db.execSQL("update ipl_stats set Runs=0");
        db.execSQL("update ipl_stats set `30s`=0");
        db.execSQL("update ipl_stats set `50s`=0");
        db.execSQL("update ipl_stats set `100s`=0");
        db.execSQL("update ipl_stats set `4s`=0");
        db.execSQL("update ipl_stats set `6s`=0");
        db.execSQL("update ipl_stats set Highest=0");
        db.execSQL("update ipl_stats set Balls=0");
        db.execSQL("update ipl_stats set can_bowl='U'");
        db.execSQL("update ipl_stats set bovers=0");
        db.execSQL("update ipl_stats set bruns=0");
        db.execSQL("update ipl_stats set bdots=0");
        db.execSQL("update ipl_stats set bwick=0");
        db.execSQL("update ipl_stats set `4Ws`=0");
        db.execSQL("update ipl_stats set `5Ws`=0");
        db.execSQL("update ipl_stats set BB='0/0'");
        db.execSQL("update ipl_stats set bat_rat=0");
        db.execSQL("update ipl_stats set nouts=0");
        db.close();
    }
    public void setSeriesStats(String tab_name)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("INSERT INTO series_stats(player_name,team_id)SELECT player_name,team_id from "+tab_name);
        db.execSQL("update series_stats set matches=0");
        db.execSQL("update series_stats set Runs=0");
        db.execSQL("update series_stats set `30s`=0");
        db.execSQL("update series_stats set `50s`=0");
        db.execSQL("update series_stats set `100s`=0");
        db.execSQL("update series_stats set `4s`=0");
        db.execSQL("update series_stats set `6s`=0");
        db.execSQL("update series_stats set Highest=0");
        db.execSQL("update series_stats set Balls=0");
        db.execSQL("update series_stats set can_bowl='U'");
        db.execSQL("update series_stats set bovers=0");
        db.execSQL("update series_stats set bruns=0");
        db.execSQL("update series_stats set bdots=0");
        db.execSQL("update series_stats set bwick=0");
        db.execSQL("update series_stats set `4Ws`=0");
        db.execSQL("update series_stats set `5Ws`=0");
        db.execSQL("update series_stats set BB='0/0'");
        db.execSQL("update series_stats set bat_rat=0");
        db.execSQL("update series_stats set nouts=0");
        db.close();
    }
    public void setRankingsIPL(String i)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("insert into ipl_rankings values('"+i+"',0,0,0,0,0)");
        db.close();
    }
    public void setRankingsSeries(String i)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("insert into series_rankings values('"+i+"',0,0,0,0,0)");
        db.close();
    }

}
