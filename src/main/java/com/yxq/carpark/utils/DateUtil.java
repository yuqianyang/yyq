package com.yxq.carpark.utils;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;


/**
 * ����ϸ��ʱ�乤����
 *
 * @author Administrator
 */
@Component
public class DateUtil {

    public static String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static String yyyyMMdd = "yyyy-MM-dd";

    /**
     * ��ȡ���쿪ʼʱ��
     *
     * @return
     */
    public static Date getDayBegin() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);//0��
        cal.set(Calendar.MINUTE, 0);//0��
        cal.set(Calendar.SECOND, 0);//0��
        cal.set(Calendar.MILLISECOND, 0);//0����
        return cal.getTime();
    }


    /**
     * ��ȡ�������ʱ��
     *
     * @return
     */
    public static Date getDayEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);//23��
        cal.set(Calendar.MINUTE, 59);//59��
        cal.set(Calendar.SECOND, 59);//59��
        return cal.getTime();
    }


    /**
     * ��ȡ���쿪ʼʱ��
     *
     * @return
     */
    public static Date getBeginDayOfYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDayBegin());//���쿪ʼʱ��
        cal.add(Calendar.DAY_OF_MONTH, -1);//�����·�������1
        return cal.getTime();
    }


    /**
     * ��ȡ�������ʱ��
     *
     * @return
     */
    public static Date getEndDayOfYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDayEnd());//�������ʱ��
        cal.add(Calendar.DAY_OF_MONTH, -1);//�����·�������1
        return cal.getTime();
    }


    /**
     * ��ȡ���쿪ʼʱ��
     *
     * @return
     */
    public static Date getBeginDayOfTomorrow() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDayBegin());//���쿪ʼʱ��
        cal.add(Calendar.DAY_OF_MONTH, 1);//�����·�������1
        return cal.getTime();
    }


    /**
     * ��ȡ�������ʱ��
     *
     * @return
     */
    public static Date getEndDayOfTomorrow() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDayEnd());//�������ʱ��
        cal.add(Calendar.DAY_OF_MONTH, 1);//�����·�������1
        return cal.getTime();
    }


    /**
     * ��ȡĳ�����ڵĿ�ʼʱ��
     *
     * @param d
     * @return
     */
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }


    /**
     * ��ȡĳ�����ڵĽ���ʱ��
     *
     * @param d
     * @return
     */
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }


    /**
     * ��ȡ���ܵĿ�ʼʱ��
     *
     * @return
     */
    @SuppressWarnings("unused")
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            dayOfWeek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayOfWeek);
        return getDayStartTime(cal.getTime());
    }


    /**
     * ��ȡ���ܵĽ���ʱ��
     *
     * @return
     */
    public static Date getEndDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }


    /**
     * ��ȡ���ܿ�ʼʱ��
     */
    @SuppressWarnings("unused")
    public static Date getBeginDayOfLastWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek - 7);
        return getDayStartTime(cal.getTime());
    }


    /**
     * ��ȡ���ܵĽ���ʱ��
     *
     * @return
     */
    public static Date getEndDayOfLastWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfLastWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }


    /**
     * ��ȡ��������һ��
     *
     * @return
     */
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }


    /**
     * ��ȡ��������һ��
     *
     * @return
     */
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }


    /**
     * ��ȡ���µĿ�ʼʱ��
     *
     * @return
     */
    public static Date getBeginDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        return getDayStartTime(calendar.getTime());
    }


    /**
     * ��ȡ���µĽ���ʱ��
     *
     * @return
     */
    public static Date getEndDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 1, day);
        return getDayEndTime(calendar.getTime());
    }


    /**
     * ��ȡ���µĿ�ʼʱ��
     *
     * @return
     */
    public static Date getBeginDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 2, 1);
        return getDayStartTime(calendar.getTime());
    }


    /**
     * ��ȡ���µĽ���ʱ��
     *
     * @return
     */
    public static Date getEndDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 2, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 2, day);
        return getDayEndTime(calendar.getTime());
    }


    /**
     * ��ȡ����Ŀ�ʼʱ��
     *
     * @return
     */
    public static java.util.Date getBeginDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        return getDayStartTime(cal.getTime());
    }


    /**
     * ��ȡ����Ľ���ʱ��
     *
     * @return
     */
    public static java.util.Date getEndDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return getDayEndTime(cal.getTime());
    }


    /**
     * ������������õ�������
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int getDiffDays(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            throw new IllegalArgumentException("getDiffDays param is null!");
        }
        long diff = (endDate.getTime() - beginDate.getTime()) / (1000 * 60 * 60 * 24);
        int days = new Long(diff).intValue();
        return days;
    }

    /**
     * ������������õ���Сʱ
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int getDiffHours(Date beginDate, Date endDate) {
        long hours;
        long day = endDate.getTime() - beginDate.getTime();
        hours = day / (1000 * 60 * 60);
        if (day % (1000 * 60 * 60) > 0) {
            hours += 1;
        }
        return (int)hours;
    }


    /**
     * ������������õ��ĺ�����
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static long dateDiff(Date beginDate, Date endDate) {
        long date1ms = beginDate.getTime();
        long date2ms = endDate.getTime();
        return date2ms - date1ms;
    }


    /**
     * ��ȡ���������е��������
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Date max(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {//beginDate���ڴ���endDate
            return beginDate;
        }
        return endDate;
    }


    /**
     * ��ȡ���������е���С����
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Date min(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return endDate;
        }
        return beginDate;
    }


    /**
     * ��ȡĳ�¸ü��ȵĵ�һ����
     *
     * @param date
     * @return
     */
    public static Date getFirstSeasonDate(Date date) {
        final int[] SEASON = {1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int sean = SEASON[cal.get(Calendar.MONTH)];
        cal.set(Calendar.MONTH, sean * 3 - 3);
        return cal.getTime();
    }


    /**
     * ����ĳ�������¼��������
     *
     * @param date
     * @param i
     * @return
     */
    public static Date getNextDay(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
        return cal.getTime();
    }


    /**
     * ����ĳ������ǰ���������
     *
     * @param date
     * @param i
     * @return
     */
    public static Date getFrontDay(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
        return cal.getTime();
    }


    /**
     * ��ȡĳ��ĳ�°�����Ƭ���ڼ��ϣ�ĳ���¼������������ڼ��ϣ�
     *
     * @param beginYear
     * @param beginMonth
     * @param k
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static List getTimeList(int beginYear, int beginMonth, int k) {
        List list = new ArrayList();
        Calendar begincal = new GregorianCalendar(beginYear, beginMonth, 1);
        int max = begincal.getActualMaximum(Calendar.DATE);
        for (int i = 1; i < max; i = i + k) {
            list.add(begincal.getTime());
            begincal.add(Calendar.DATE, k);
        }
        begincal = new GregorianCalendar(beginYear, beginMonth, max);
        list.add(begincal.getTime());
        return list;
    }


    /**
     * ��ȡĳ��ĳ�µ�ĳ��ĳ�°������Ƭ���ڼ��ϣ���������ļ��ϣ�
     *
     * @param beginYear
     * @param beginMonth
     * @param endYear
     * @param endMonth
     * @param k
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List getTimeList(int beginYear, int beginMonth, int endYear, int endMonth, int k) {
        List list = new ArrayList();
        if (beginYear == endYear) {
            for (int j = beginMonth; j <= endMonth; j++) {
                list.add(getTimeList(beginYear, j, k));
            }
        } else {
            {
                for (int j = beginMonth; j < 12; j++) {
                    list.add(getTimeList(beginYear, j, k));
                }
                for (int i = beginYear + 1; i < endYear; i++) {
                    for (int j = 0; j < 12; j++) {
                        list.add(getTimeList(i, j, k));
                    }
                }
                for (int j = 0; j <= endMonth; j++) {
                    list.add(getTimeList(endYear, j, k));
                }
            }
        }
        return list;
    }


    //=================================ʱ���ʽת��==========================

    /**
     * date���ͽ��и�ʽ��������������ͣ�String��
     *
     * @param date
     * @return
     */
    public static String dateFormat(Date date, String format) {
        if (StringUtils.isEmpty(format)) {
            format = DateUtil.yyyyMMddHHmmss;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        return dateString;
    }


    /**
     * ��"2015-08-31 21:08:06"���ַ���ת��ΪDate
     *
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date StringToDate(String str, String format) throws ParseException {
        if (StringUtils.isEmpty(format)) {
            format = DateUtil.yyyyMMddHHmmss;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = (Date) formatter.parse(str);
        return date;
    }


    /**
     * ��CSTʱ�������ַ������и�ʽ�����
     *
     * @param str
     * @return
     * @throws ParseException
     */
    public static String CSTFormat(String str) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date date = (Date) formatter.parse(str);
        return dateFormat(date, null);
    }


    /**
     * ��long����ת��ΪDate
     *
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date LongToDare(long str) throws ParseException {
        return new Date(str * 1000);
    }


    //====================================�����������ڲ�������======================

    /**
     * �жϵ�ǰ�����Ƿ���[startDate, endDate]����
     *
     * @param startDate ��ʼ����
     * @param endDate   ��������
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return false;
        }
        long currentTime = new Date().getTime();
        if (currentTime >= startDate.getTime()
                && currentTime <= endDate.getTime()) {
            return true;
        }
        return false;
    }


    /**
     * �õ��������ڼ�ļ������
     *
     * @param secondString����һ������
     * @param firstString��ǰһ������
     * @return
     */
    public static String getTwoDay(String secondString, String firstString) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            java.util.Date secondTime = myFormatter.parse(secondString);
            java.util.Date firstTime = myFormatter.parse(firstString);
            day = (secondTime.getTime() - firstTime.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }


    /**
     * ʱ��ǰ�ƻ���Ʒ���,����JJ��ʾ����.
     *
     * @param StringTime��ʱ��
     * @param minute�����ӣ�������֮�֣�
     * @return
     */
    public static String getPreTime(String StringTime, String minute) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mydate1 = "";
        try {
            Date date1 = format.parse(StringTime);
            long Time = (date1.getTime() / 1000) + Integer.parseInt(minute) * 60;
            date1.setTime(Time * 1000);
            mydate1 = format.format(date1);
        } catch (Exception e) {
            return "";
        }
        return mydate1;
    }


    /**
     * ����ʱ���ʽ�ַ���ת��Ϊʱ�� yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(DateUtil.yyyyMMdd);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }


    /**
     * �õ�һ��ʱ���Ӻ��ǰ�Ƽ����ʱ��
     *
     * @param nowdate��ʱ��
     * @param days��ǰ�ƻ���ӵ�����
     * @return
     */
    public static String getNextDay(String nowdate, int days) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String mdate = "";
            Date d = strToDate(nowdate);
            long myTime = (d.getTime() / 1000) + days * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = format.format(d);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * �ж��Ƿ��ǹ�ȥ������
     *
     * @param str:���������
     * @return
     */
    public static boolean isPastDate(String str) {

        boolean flag = false;
        Date nowDate = new Date();
        Date pastDate = null;
        //��ʽ������
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.CHINA);
        //�������ַ����ǿ�ʱִ��
        if (str != null && !"".equals(str)) {
            try {
                //���ַ���תΪ���ڸ�ʽ������˴��ַ���Ϊ�ǺϷ����ھͻ��׳��쳣��
                pastDate = sdf.parse(str);
                //����Date�����before���������ж� true:���������ڽ��� false:���������ڽ���
                flag = pastDate.before(nowDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("���ڲ�������Ϊ��");
        }
        return flag;
    }


    /**
     * �ж��Ƿ�����
     *
     * @param ddate
     * @return
     */
    public static boolean isLeapYear(String ddate) {
        /**
         * ��ϸ��ƣ� 1.��400���������꣬���� 2.���ܱ�4������������ 3.�ܱ�4����ͬʱ���ܱ�100������������
         * 3.�ܱ�4����ͬʱ�ܱ�100������������
         */
        Date d = strToDate(ddate);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(d);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0) {
            return true;
        } else if ((year % 4) == 0) {
            if ((year % 100) == 0) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    /**
     * ��������ʱ���ʽ
     *
     * @param str
     * @return
     */
    public static String getEDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(str, pos);
        String j = strtodate.toString();
        String[] k = j.split(" ");
        return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
    }


    /**
     * �ж϶���ʱ���Ƿ���ͬһ����
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameWeekDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
            // ���12�µ����һ�ܺ�������һ�ܵĻ������һ�ܼ���������ĵ�һ��
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        }
        return false;
    }


    /**
     * ����������,���õ���ǰʱ�����ڵ�����ǵڼ���
     *
     * @return
     */
    public static String getSeqWeek() {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
        if (week.length() == 1)
            week = "0" + week;
        String year = Integer.toString(c.get(Calendar.YEAR));
        return year + "���" + week + "��";
    }


    /**
     * ���һ���������ڵ��ܵ����ڼ������ڣ���Ҫ�ҳ�2002��2��3�������ܵ�����һ�Ǽ���
     *
     * @param sdate������
     * @param num�����ڼ�����������һ�ܵĵ�һ�죩
     * @return
     */
    public static String getWeek(String sdate, String num) {
        // ��ת��Ϊʱ��
        Date dd = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(dd);
        if (num.equals("1")) // ��������һ���ڵ�����
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        else if (num.equals("2")) // �������ڶ����ڵ�����
            c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        else if (num.equals("3")) // �������������ڵ�����
            c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        else if (num.equals("4")) // �������������ڵ�����
            c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        else if (num.equals("5")) // �������������ڵ�����
            c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        else if (num.equals("6")) // �������������ڵ�����
            c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        else if (num.equals("0")) // �������������ڵ�����
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }


    /**
     * ����һ�����ڣ����������ڼ����ַ���
     *
     * @param sdate
     * @return
     */
    public static String getWeek(String sdate) {
        // ��ת��Ϊʱ��
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour�д�ľ������ڼ��ˣ��䷶Χ 1~7
        // 1=������ 7=����������������
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }


    /**
     * ����һ�����ڣ����������ڼ����ַ���
     *
     * @param sdate
     * @return
     */
    public static String getWeekStr(String sdate) {
        String str = "";
        str = getWeek(sdate);
        if ("1".equals(str)) {
            str = "������";
        } else if ("2".equals(str)) {
            str = "����һ";
        } else if ("3".equals(str)) {
            str = "���ڶ�";
        } else if ("4".equals(str)) {
            str = "������";
        } else if ("5".equals(str)) {
            str = "������";
        } else if ("6".equals(str)) {
            str = "������";
        } else if ("7".equals(str)) {
            str = "������";
        }
        return str;
    }


    /**
     * ����ʱ��֮�������
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getDays(String date1, String date2) {
        if (date1 == null || date1.equals(""))
            return 0;
        if (date2 == null || date2.equals(""))
            return 0;
        // ת��Ϊ��׼ʱ��
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        java.util.Date mydate = null;
        try {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (Exception e) {
        }
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }


    /**
     * �γ����µ����� �� ���ݴ����һ��ʱ�䷵��һ���ṹ ������ ����һ ���ڶ� ������ ������ ������ ������ �����ǵ��µĸ���ʱ��
     * �˺������ظ�������һ�����������ڵ�����
     *
     * @param sdate
     * @return
     */
    public static String getNowMonth(String sdate) {
        // ȡ��ʱ�������µ�һ��
        sdate = sdate.substring(0, 8) + "01";

        // �õ�����µ�1�������ڼ�
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int u = c.get(Calendar.DAY_OF_WEEK);
        String newday = getNextDay(sdate, 1 - u);
        return newday;
    }


    /**
     * �����û������ʱ���ʾ��ʽ�����ص�ǰʱ��ĸ�ʽ �����yyyyMMdd��ע����ĸy���ܴ�д
     *
     * @param sformat
     * @return
     */
    public static String getUserDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    /**
     * ����һ��iλ���������
     *
     * @param i
     * @return
     */
    public static String getRandom(int i) {
        Random jjj = new Random();
        // int suiJiShu = jjj.nextInt(9);
        if (i == 0)
            return "" +
                    "";
        String jj = "";
        for (int k = 0; k < i; k++) {
            jj = jj + jjj.nextInt(9);
        }
        return jj;
    }


    /**
     * ȡ�����ݿ����� ���ɸ�ʽΪyyyymmddhhmmss+kλ�����
     *
     * @param k����ʾ��ȡ��λ������������Լ���
     * @return
     */
    public static String getNo(int k) {
        return getUserDate("yyyyMMddhhmmss") + getRandom(k);
    }


    public static void main(String[] args) {
        System.out.println(getNo(8));
    }
}