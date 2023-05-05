package com.lib.pojo;

public class Page {
    private int current = 1;
    private int limit = 4;

    private int rows;


    //获取总页数
    public int getTotal() {
        if (rows % limit == 0) {
            return rows / limit;
        } else {
            return (rows / limit) + 1;
        }
    }

    //获取当前页的起始行
    public int getOffset() {
        return current * limit - limit;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

}
