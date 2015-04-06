package com.example.rick.mad;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
    //Databaseinfo
    private static final String DATABASE_NAME="myfriendlist.db";
    private static final int DATABASE_VERSION=1;
//Assignments
    public static final String TABLE_FRIENDS="friends";
    public static final String COLUMN_FRIEND_ID="friend_id";
    public static final String COLUMN_FRIEND="friend";

    //Creatingthetable
    private static final String DATABASE_CREATE_FRIENDS=
    "CREATE TABLE "+TABLE_FRIENDS+
    "("+
    COLUMN_FRIEND_ID+" integer primary key autoincrement, "+
    COLUMN_FRIEND+" text not null"+
    ");";

    //Mandatoryconstructorwhichpassesthecontext,databasenameanddatabaseversion
    //andpassesittotheparent
    public MySQLiteHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase database)
    {
        //Executethesqltocreatethetableassignments
        database.execSQL(DATABASE_CREATE_FRIENDS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {
    /*
    *Whenthedatabasegetsupgradedyoushouldhandletheupdatetomakesurethere
    isnodataloss.
    *Thisisthedefaultcodeyouputintheupgrademethod,todeletethetableand
    calltheoncreateagain.
    */
        Log.w(MySQLiteHelper.class.getName(), "Upgrading database from version" +
                oldVersion + "to" + newVersion + ",which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_FRIENDS);
        onCreate(db);
    }
}
