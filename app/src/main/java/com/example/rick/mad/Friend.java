package com.example.rick.mad;

/**
 * Created by Rick on 27-3-2015.
 */
public class Friend {
    private long id;
    private String friend;

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id=id;
    }
    public String getFriend(){
        return friend;
    }
    public void setFriend(String friend){
        this.friend=friend;
    }
    //WillbeusedbytheArrayAdapterintheListView
    @Override
    public String toString(){
        return friend;
    }

}
