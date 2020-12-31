package com.yxq.carpark.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DepotcardManagerData implements Serializable {

    private String cardnum;

    /**
     * 卡类型 1普通卡，2月卡，3年卡
     */
    private String type;

    /**
     * 充值金额
     */
    private double money;

    /**
     * 持卡人用户名
     */
    private String username;

    /**
     * 持卡人车牌号
     */
    private String carNum;

    /**
     * 持卡人联系电话
     */
    private String tel;

    /**
     * 持卡人联系电话
     */
    private String name;

    /**
     * 发卡时间
     */
    private String time;

    /**
     * 是否挂失
     */
    private int islose;

    /**
     * 违规次数
     */
    private int illegalcount;

    /**
     * 支付id
     */
    private int payid;

    /**
     * 扣费时间
     */
    private Date deductedtime;

    /**
     * 支付方式（0现金，1支付宝，2微信，9从卡中扣费）
     */
    private int alertpayid;

    /**
     * 需要支付金额
     */
    private int alertpay_money;

    /**
     * 扣费还是月卡或年卡未到期 (0扣费，1不用扣费，9付钱)
     */
    private int alertpay_type;

    public int getAlertpay_money() {
        return alertpay_money;
    }

    public int getAlertpay_type() {
        return alertpay_type;
    }

    public int getAlertpayid() {
        return alertpayid;
    }

    public void setAlertpay_money(int alertpay_money) {
        this.alertpay_money = alertpay_money;
    }

    public void setAlertpay_type(int alertpay_type) {
        this.alertpay_type = alertpay_type;
    }

    public void setAlertpayid(int alertpayid) {
        this.alertpayid = alertpayid;
    }

    public Date getDeductedtime() {
        return deductedtime;
    }

    public void setDeductedtime(Date deductedtime) {
        this.deductedtime = deductedtime;
    }

    public int getPayid() {
        return payid;
    }

    public void setPayid(int payid) {
        this.payid = payid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getIslose() {
        return islose;
    }

    public void setIslose(int islose) {
        this.islose = islose;
    }

    public int getIllegalcount() {
        return illegalcount;
    }

    public void setIllegalcount(int illegalcount) {
        this.illegalcount = illegalcount;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }
}
