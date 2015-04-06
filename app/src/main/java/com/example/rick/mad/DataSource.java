package com.example.rick.mad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rick on 27-3-2015.
 */
public class DataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[]friendAllColumns={MySQLiteHelper.COLUMN_FRIEND_ID,
    MySQLiteHelper.COLUMN_FRIEND};

    public DataSource(Context context)
    {

    dbHelper=new MySQLiteHelper(context);
    database=dbHelper.getWritableDatabase();
    dbHelper.close();
    }
    //Opensthedatabasetouseit
    public void open()throws SQLException
    {
        database=dbHelper.getWritableDatabase();
    }
    //Closesthedatabasewhenyounolongerneedit
    public void close()
    {
        dbHelper.close();
    }

    public long createFriend(String friend)
    {
        //Ifthedatabaseisnotopenyet,openit
        if(!database.isOpen())
            open();
        ContentValues values=new ContentValues();
        values.put(MySQLiteHelper.COLUMN_FRIEND,friend);
        long insertId=database.insert(MySQLiteHelper.TABLE_FRIENDS,null,values);
        //Ifthedatabaseisopen,closeit
        if(database.isOpen())
            close();
        return insertId;
    }
    public void deleteFriend(Friend friend)
    {
        if(!database.isOpen())
            open();
        database.delete(MySQLiteHelper.TABLE_FRIENDS,
                MySQLiteHelper.COLUMN_FRIEND_ID+"=?",new String[]{
        Long.toString(friend.getId())});
        if(database.isOpen())
            close();
    }
    public void updateFriend(Friend friend)
    {
        if(!database.isOpen())
            open();
        ContentValues args=new ContentValues();
        args.put(MySQLiteHelper.COLUMN_FRIEND,friend.getFriend());
        database.update(MySQLiteHelper.TABLE_FRIENDS,args,
                MySQLiteHelper.COLUMN_FRIEND_ID+"=?",new String[]{Long.toString(friend.getId())
    });
        if(database.isOpen())
            close();
    }

    public List<Friend> getAllFriends()
    {
        if(!database.isOpen())
            open();
        List<Friend>friends=new ArrayList<Friend>();
        Cursor cursor=database.query(MySQLiteHelper.TABLE_FRIENDS,
                friendAllColumns,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            Friend friend=cursorToFriend(cursor);
            friends.add(friend);
            cursor.moveToNext();
        }
//makesuretoclosethecursor
        cursor.close();
        if(database.isOpen())
            close();
        return friends;
    }
    private Friend cursorToFriend(Cursor cursor)
    {
        try
        {
            Friend friend=new Friend();
            friend.setId(cursor.getLong(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_FRIEND_ID)));
            friend.setFriend(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_FRIEND)));
            return friend;
        }catch(CursorIndexOutOfBoundsException exception)
        {
        exception.printStackTrace();
        return null;
        }
    }
    public Friend getFriend(long columnId)
    {
        if(!database.isOpen())
            open();
        Cursor cursor=database.query(MySQLiteHelper.TABLE_FRIENDS,
                friendAllColumns,MySQLiteHelper.COLUMN_FRIEND_ID+"=?",new String[]{
        Long.toString(columnId)},null,null,null);
        cursor.moveToFirst();
        Friend friend =cursorToFriend(cursor);
        cursor.close();
        if(database.isOpen())
            close();
        return friend;
    }



}
