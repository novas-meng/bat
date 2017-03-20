package com.novas;

/**
 * Created by 讨鬼 on 2017/3/20.
 */
public class Trans {
    private long id;
    private long startTime;
    private String transName;
    private String STATUS;
    private long endTime;
    public Trans(long id,long startTime,String transName,String STATUS,long endTime)
    {
        this.id=id;
        this.startTime=startTime;
        this.transName=transName;
        this.STATUS=STATUS;
        this.endTime=endTime;
    }
    public long getId()
    {
        return id;
    }
    public long getStartTime()
    {
        return startTime;
    }
    public long getEndTime()
    {
        return endTime;
    }
    public String getTransName()
    {
        return transName;
    }
    public String getSTATUS()
    {
        return STATUS;
    }

    @Override
    public String toString() {
        return "TRANS:"+id+":"+startTime+":"+transName+":"+STATUS+":"+endTime;
    }
}
