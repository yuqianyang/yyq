package com.yxq.carpark.entity;

import com.yxq.carpark.utils.Constants;
import com.yxq.carpark.utils.DateUtil;

import java.io.Serializable;
import java.util.Date;


public class Coupon implements Serializable {
    private int id;
    private String couponNum;
    private int money;
    private Date time;
    private String cardnum;

    public String getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(String couponNum) {
        this.couponNum = couponNum;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Coupon() {
    }

    public Coupon(String couponNum, int money, String cardnum) {
        this.couponNum = couponNum;
        this.money = money;
        this.time = DateUtil.getNextDay(new Date(), Constants.COUPONVALIDDAY);
        this.cardnum = cardnum;
    }
}
