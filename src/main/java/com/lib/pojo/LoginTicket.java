package com.lib.pojo;

import java.util.Date;

public class LoginTicket {
    private int id;
    private int userId;
    private int userType;
    private String ticket;
    private int status;
    private Date expired;

    public LoginTicket() {
    }

    public LoginTicket(int userId, int userType, String ticket, int status, Date expired) {
        this.userId = userId;
        this.userType = userType;
        this.ticket = ticket;
        this.status = status;
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", userId=" + userId +
                ", userType=" + userType +
                ", ticket='" + ticket + '\'' +
                ", status=" + status +
                ", expired=" + expired +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }
}
