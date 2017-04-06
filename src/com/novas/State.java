package com.novas;

/**
 * Created by 讨鬼 on 2017/3/20.
 */
public class State {
    private long id;
    private int type;
    private long startTime;
    private String name;
    private String STATUS;
    private long endTime;
    public State(long id,int type,long startTime,String name,String STATUS,long endTime)
    {
        this.id=id;
        this.type=type;
        this.startTime=startTime;
        this.name=name;
        this.STATUS=STATUS;
        this.endTime=endTime;
    }
    public long getId()
    {
        return id;
    }
    public int getType()
    {
        return type;
    }
    public long getStartTime()
    {
        return startTime;
    }
    public long getEndTime()
    {
        return endTime;
    }
    public String getName()
    {
        return name;
    }
    public String getSTATUS()
    {
        return STATUS;
    }

    @Override
    public String toString() {
        return "TRANS:"+id+":"+type+":"+startTime+":"+name+":"+STATUS+":"+endTime;
    }
}
