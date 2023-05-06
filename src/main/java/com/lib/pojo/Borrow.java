package com.lib.pojo;

import java.util.Date;

public class Borrow {
    private int id;
    private int uid;
    private int bid;
    private Date startTime;
    private Date endTime;
    private int status;

    public Borrow(int uid, int bid, Date startTime, Date endTime, int status) {
        this.uid = uid;
        this.bid = bid;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public Borrow() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
