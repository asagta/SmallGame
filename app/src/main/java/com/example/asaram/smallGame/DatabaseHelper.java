package com.example.asaram.smallGame;

/**
 * Created by Asaram on 07-10-2018.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Asaram on 30-04-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private final static String TAG = "ANURAGSAGTA_Helper";
    private final Context myContext;
    private static final String DATABASE_NAME = "MasterDB";
    private static final int DATABASE_VERSION = 1;
    private String pathToSaveDBFile;
    public DatabaseHelper(Context context, String filePath) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        Log.d("DEST-PATH-:",filePath.toString());
        pathToSaveDBFile = new StringBuffer(filePath).append("/").append(DATABASE_NAME).toString();
    }
    public void prepareDatabase() throws IOException {
        boolean dbExist = checkDataBase();
        if(dbExist) {
            Log.d(TAG, "Database exists.");
           /* int currentDBVersion = getVersionId();
            if (DATABASE_VERSION > currentDBVersion) {
                Log.d(TAG, "Database version is higher than old.");
                deleteDb();
                try {
                    copyDataBase();
                } catch (IOException e) {
                    Log.e(TAG, "DB Version Issue");
                }
            }*/
        } else {
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }
    private boolean checkDataBase() {
        boolean checkDB = false;
        try {
            File file = new File(pathToSaveDBFile);
            checkDB = file.exists();
        } catch(SQLiteException e) {
            Log.d(TAG, e.getMessage());
        }
        return checkDB;
    }
    private void copyDataBase() throws IOException {
        final String[] names = myContext.getAssets().list("sqlite");
        for(int i=0;i<names.length;i++) {
            Log.d("COPY DATABASE", names[i]);
        }
        OutputStream os = new FileOutputStream(pathToSaveDBFile);
        InputStream is = myContext.getAssets().open("sqlite/"+DATABASE_NAME);
        //InputStream is=new FileInputStream("A:\\Onsite\\4th.txt");
        Log.d("FILE is:",myContext.getAssets().open("sqlite/"+DATABASE_NAME).toString());
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        is.close();
        //Log.d()
        os.flush();
        os.close();
    }
    public void deleteDb() {
        File file = new File(pathToSaveDBFile);
        if(file.exists()) {
            file.delete();
            Log.d(TAG, "Database deleted.");
        }
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate");
        String CREATE_DBVERSION_TABLE = "CREATE TABLE dbVersion(version_id INTEGER NOT NULL, PRIMARY KEY(`version_id`))";
        db.execSQL(CREATE_DBVERSION_TABLE);
       // db.execSQL("INSERT INTO scores" + " values('IND',0)");
        //String CREATE_EXPENSE_TABLE = "CREATE TABLE expense(sno INTEGER, money INTEGER, desc TEXT, date_of TEXT)";
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private int getVersionId() {
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READWRITE);
        String CREATE_DBVERSION_TABLE = "CREATE TABLE dbVersion(version_id INTEGER NOT NULL, PRIMARY KEY(`version_id`))";

       // db.execSQL(CREATE_DBVERSION_TABLE);
        //db.execSQL("INSERT INTO dbVersion" + " values(1)");
        String query = "SELECT version_id FROM dbVersion";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int v =  cursor.getInt(0);
        db.close();
        return v;
    }
}
