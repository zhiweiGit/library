package com.lib.vo;

import java.util.Date;

public class BorrowVo {
    private int id;
    private String username;

    private String bookName;
    private int sid;
    private String author;
    private String publish;
    private String edition;
    private Date startTime;
    private Date endTime;

    private int status;

    public BorrowVo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public BorrowVo(String bookName, int sid, String author, String publish, String edition, Date startTime, int status) {
        this.bookName = bookName;
        this.sid = sid;
        this.author = author;
        this.publish = publish;
        this.edition = edition;
        this.startTime = startTime;
        this.status = status;
    }


    public BorrowVo(String username, String bookName, int sid, String author, String publish, String edition, Date startTime, Date endTime, int status) {
        this.username = username;
        this.bookName = bookName;
        this.sid = sid;
        this.author = author;
        this.publish = publish;
        this.edition = edition;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public BorrowVo(int id, String username, String bookName, int sid, String author, String publish, String edition, Date startTime, Date endTime, int status) {
        this.id = id;
        this.username = username;
        this.bookName = bookName;
        this.sid = sid;
        this.author = author;
        this.publish = publish;
        this.edition = edition;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }
}
