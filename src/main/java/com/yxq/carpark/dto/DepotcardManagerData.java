package com.yxq.carpark.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DepotcardManagerData implements Serializable {

    private String cardnum;

    /**
     * ������ 1��ͨ����2�¿���3�꿨
     */
    private String type;

    /**
     * ��ֵ���
     */
    private double money;

    /**
     * �ֿ����û���
     */
    private String username;

    /**
     * �ֿ��˳��ƺ�
     */
    private String carNum;

    /**
     * �ֿ�����ϵ�绰
     */
    private String tel;

    /**
     * �ֿ�����ϵ�绰
     */
    private String name;

    /**
     * ����ʱ��
     */
    private String time;

    /**
     * �Ƿ��ʧ
     */
    private int islose;

    /**
     * Υ�����
     */
    private int illegalcount;

    /**
     * ֧��id
     */
    private int payid;

    /**
     * �۷�ʱ��
     */
    private Date deductedtime;

    /**
     * ֧����ʽ��0�ֽ�1֧������2΢�ţ�9�ӿ��п۷ѣ�
     */
    private int alertpayid;

    /**
     * ��Ҫ֧�����
     */
    private int alertpay_money;

    /**
     * �۷ѻ����¿����꿨δ���� (0�۷ѣ�1���ÿ۷ѣ�9��Ǯ)
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
