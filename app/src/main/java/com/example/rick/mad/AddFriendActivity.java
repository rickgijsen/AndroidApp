package com.example.rick.mad;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class AddFriendActivity extends ActionBarActivity {

    private EditText friendEditText;
    private DataSource datasource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        friendEditText=(EditText)findViewById(R.id.add_friend_friend_edittext);
        datasource = new DataSource(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_friend, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id==R.id.add_friend_menu_save)
        {
            long friendId=
                    datasource.createFriend(friendEditText.getText().toString());
            Intent resultIntent=new Intent();
            resultIntent.putExtra(MainActivity.EXTRA_FRIEND_ID,friendId);
            setResult(Activity.RESULT_OK,resultIntent);
            finish();
            return true;
        }



        return super.onOptionsItemSelected(item);
    }
}
