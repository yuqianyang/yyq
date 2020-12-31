package com.yxq.carpark.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.yxq.carpark.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxq.carpark.dto.CouponData;
import com.yxq.carpark.dto.FormData;
import com.yxq.carpark.entity.Coupon;
import com.yxq.carpark.entity.Depotcard;
import com.yxq.carpark.entity.IllegalInfo;
import com.yxq.carpark.entity.Income;
import com.yxq.carpark.entity.ParkInfo;
import com.yxq.carpark.entity.Parkinfoall;
import com.yxq.carpark.entity.User;
import com.yxq.carpark.service.CouponService;
import com.yxq.carpark.service.DepotcardService;
import com.yxq.carpark.service.IllegalInfoService;
import com.yxq.carpark.service.IncomeService;
import com.yxq.carpark.service.ParkinfoService;
import com.yxq.carpark.service.ParkinfoallService;
import com.yxq.carpark.service.ParkspaceService;
import com.yxq.carpark.service.UserService;
import com.yxq.carpark.utils.Constants;
import com.yxq.carpark.utils.Msg;


@Controller
public class CheckController {

    @Autowired
    private ParkinfoService parkinfoservice;
    @Autowired
    private ParkspaceService parkspaceService;
    @Autowired
    private DepotcardService depotcardService;
    @Autowired
    private UserService userService;
    @Autowired
    private IllegalInfoService illegalInfoService;
    @Autowired
    private ParkinfoallService parkinfoallService;
    @Autowired
    private IncomeService incomeService;
    @Autowired
    private CouponService couponService;

    static int i = 0;

    @RequestMapping("/index/check/checkIn")
    @ResponseBody
    @Transactional
    // ������
    public Msg checkIn(Model model, FormData data) {
        Depotcard depotcard = depotcardService.findByCardnum(data.getCardNum());
        if (data.getParkTem() != 1) {
            if (depotcard != null) {
                if (depotcard.getIslose() == 1) {
                    return Msg.fail().add("va_msg", "�ÿ��ѹ�ʧ��");
                }
            } else {
                return Msg.fail().add("va_msg", "�ÿ������ڣ�");
            }
        }
        parkinfoservice.saveParkinfo(data);
        parkspaceService.changeStatus(data.getId(), 1);
        return Msg.success();
    }

    @RequestMapping("/index/check/checkOut")
    @ResponseBody
    @Transactional
    // ���������0�۷ѣ�1���ÿ۷ѣ�9��Ǯ��
    public Msg checkOut(Model model, FormData data) {
        int pay_money = data.getPay_money();
        Date parkout = new Date();
        Parkinfoall parkinfoall = new Parkinfoall();
        ParkInfo parkInfo = parkinfoservice.findParkinfoByParknum(data.getParkNum());
        if (data.getPay_type() == 9) {
            Depotcard depotcard = depotcardService.findByCardnum(data.getCardNum());
            IllegalInfo illegalInfo = illegalInfoService.findByCardnumParkin(data.getCardNum(), parkInfo.getParkin());
            Income income = new Income();
            List<CouponData> coupons = couponService.findAllCouponByCardNum(data.getCardNum(), "");
            if (coupons != null && coupons.size() > 0) {
                couponService.deleteCoupon(coupons.get(0).getId());
            }
            depotcardService.addMoney(data.getCardNum(), 0);
            income.setMoney(pay_money);
            income.setMethod(data.getPayid());
            income.setCardnum(data.getCardNum());
            income.setCarnum(data.getCarNum());
            if (depotcard != null) {
                income.setType(depotcard.getType());
            }
            if (illegalInfo != null) {
                income.setIsillegal(1);
            }
            income.setSource(1);
            income.setTime(parkout);
            Date parkin = parkInfo.getParkin();
            long day = parkout.getTime() - parkin.getTime();
            long time = day / (1000 * 60);
            if (day % (1000 * 60) > 0) {
                time += 1;
            }
            income.setDuration(time);
            incomeService.save(income);
        } else {
            if (data.getPay_type() == 9) {
                return Msg.fail().add("va_msg", "ϵͳ����");
            } else if (data.getPay_type() == 0) {
                //�����۷ѣ��¿����꿨����
                Depotcard depotcard = depotcardService.findByCardnum(data.getCardNum());
                IllegalInfo illegalInfo = illegalInfoService.findByCardnumParkin(data.getCardNum(), parkInfo.getParkin());
                double money = depotcard.getMoney();
                List<CouponData> coupons = couponService.findAllCouponByCardNum(data.getCardNum(), "");
                if (coupons != null && coupons.size() > 0) {
                    money -= coupons.get(0).getMoney();
                    couponService.deleteCoupon(coupons.get(0).getId());
                }
                money -= pay_money;
                depotcardService.addMoney(depotcard.getCardnum(), money);
				/*Income income=new Income();
				income.setMoney(pay_money);
				income.setMethod(data.getPayid());
				income.setCardnum(data.getCardNum());
				income.setCarnum(data.getCarNum());
				income.setType(depotcard.getType());
				if(illegalInfo!=null)
				{
					income.setIsillegal(1);
				}
				income.setSource(1);
				income.setTime(parkout);*/
				/*Date parkin=parkInfo.getParkin();
				long day=parkout.getTime()-parkin.getTime();
				long time=day/(1000*60);
				if(day%(1000*60)>0){
				time+=1;
				}
				income.setDuration(time);
				income.setTrueincome(1);
				incomeService.save(income);*/
            } else {
                //�¿����꿨����
            }
        }
        parkinfoall.setCardnum(parkInfo.getCardnum());
        parkinfoall.setCarnum(parkInfo.getCarnum());
        parkinfoall.setParkin(parkInfo.getParkin());
        parkinfoall.setParknum(data.getParkNum());
        parkinfoall.setParkout(parkout);
        parkinfoall.setParktemp(parkInfo.getParktem());
        parkinfoallService.save(parkinfoall);
        parkspaceService.changeStatusByParkNum(data.getParkNum(), 0);
        parkinfoservice.deleteParkinfoByParkNum(data.getParkNum());
        return Msg.success();
    }

    @RequestMapping("/index/check/findParkinfoByParknum")
    @ResponseBody
    // ����ͣ��λ�Ų���ͣ��λ��Ϣ
    public Msg findParkinfoByParknum(@RequestParam("parkNum") int parknum) {
        ParkInfo parkInfo = parkinfoservice.findParkinfoByParknum(parknum);
        return Msg.success().add("parkInfo", parkInfo);
    }

    @RequestMapping("/index/check/findParkinfoByCardnum")
    @ResponseBody
    // ����ͣ��λ��/���ƺŲ���ͣ��λ��Ϣ
    public Msg findParkinfoByCardnum(@RequestParam("cardnum") String cardnum) {
        ParkInfo parkInfo = parkinfoservice.findParkinfoByCardnum(cardnum);
        //System.out.println("ello"+parkInfo.getId());
        if (parkInfo != null) {
            return Msg.success().add("parkInfo", parkInfo);
        }
        return Msg.fail();
    }

    @RequestMapping("/index/check/findParkinfoDetailByParknum")
    @ResponseBody
    //����ͣ��λ�Ų���ͣ����ϸ��Ϣ
    public Msg findParkinfoDetailByParknum(@RequestParam("parkNum") int parknum) {
        ParkInfo parkInfo = parkinfoservice.findParkinfoByParknum(parknum);
        if (parkInfo == null) {
            return Msg.fail();
        }
        Date date = parkInfo.getParkin();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String parkin = formatter.format(date);
        System.out.println(parkInfo.toString());
        String cardnum = parkInfo.getCardnum();
        Depotcard depotcard = depotcardService.findByCardnum(cardnum);
        int cardid = 0;
        User user = null;
        if (depotcard != null) {
            cardid = depotcard.getId();
            user = userService.findUserByCardid(cardid);
        }
        return Msg.success().add("parkInfo", parkInfo).add("user", user).add("parkin", parkin);
    }

    @RequestMapping("/index/check/illegalSubmit")
    @ResponseBody
    //Υ���ύ
    public Msg illegalSubmit(FormData data, HttpSession httpSession) {
        User currentUser = (User) httpSession.getAttribute("user");
        //System.out.println(data.getCardNum());
        ParkInfo parkInfo = parkinfoservice.findParkinfoByCardnum(data.getCardNum());
        //System.out.println(parkInfo.getParkin()+"hell");
        IllegalInfo info = new IllegalInfo();
        IllegalInfo illegalInfo = illegalInfoService.findByCardnumParkin(data.getCardNum(), parkInfo.getParkin());
        if (illegalInfo != null) {
            return Msg.fail().add("va_msg", "���ʧ��,�Ѿ���Υ�棡");
        }
        info.setCardnum(data.getCardNum());
        info.setCarnum(data.getCarNum());
        String cardnum = data.getCarNum();
        //String carnum=data.getCarNum();
        //Depotcard depotcard=depotcardService.findByCardnum(cardnum);
        //System.out.println(depotcard.getCardnum());
        //	int cardid=0;
        //User user =null;
		/*if(depotcard!=null)
		{
		int cardid=depotcard.getId();

		User user =userService.findUserByCardid(cardid);
		System.out.println(user.getId()+"1");
		info.setUid(user.getId());
		}else {
			info.setUid(i+1);
		}*/
        //info.setUsername(user.getUsername());
        info.setIllegalInfo(data.getIllegalInfo());

        //	info.setUsername(data.get);
        //	info.setUid(currentUser.getId());
        //info.setUid(user.getId());
        info.setUid(0);
        Date date = new Date();
        info.setTime(date);

        info.setParkin(parkInfo.getParkin());

        info.setDelete("N");

        try {

            illegalInfoService.save(info);

        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().add("va_msg", "���ʧ��");
        }
        return Msg.success().add("va_msg", "��ӳɹ�");
    }

    /*�Ƿ���Ҫ֧��
     * type:0�������۷�
     * type:1���¿��꿨û����*/
    @RequestMapping("/index/check/ispay")
    @ResponseBody
    public Msg ispay(@RequestParam("parknum") Integer parknum) {
        ParkInfo parkInfo = parkinfoservice.findParkinfoByParknum(parknum.intValue());
        Date parkin = parkInfo.getParkin();
        long hour;
        //��ʱͣ����10�飩
        if (parkInfo == null) {
            return Msg.fail().add("type", 9);
        }
        //�Ƿ���Υ����Ҫ�ɷ�
        int illegalmoney = 0;
        String illegalMsg = "";
        IllegalInfo illegalInfo = illegalInfoService.findByCarnum(parkInfo.getCarnum(), parkInfo.getParkin());
        if (illegalInfo != null) {
            illegalmoney = Constants.ILLEGAL;
            illegalMsg = illegalInfo.getIllegalInfo();
        }
        String cardnum = parkInfo.getCardnum();
        //����ó�û��ͣ��������ʱͣ���Ʒ�
        if (StringUtils.isEmpty(cardnum)) {
            //��Ҫ�ֽ��ɨ��֧��,1Сʱ10��
            hour = DateUtil.getDiffHours(parkin, new Date());
            return Msg.success().add("money_pay", hour * Constants.TEMPMONEY + illegalmoney).add("va_msg", "��ʱͣ��" + (illegalmoney > 0 ? ",��Υ�棺" + illegalMsg : ""));
        }
        //��ѯͣ����
        Depotcard depotcard = depotcardService.findByCardnum(cardnum);
        if (depotcard != null) {
            int type = depotcard.getType();
            boolean isPast = true;
            Date deductedtime = depotcard.getDeductedtime();
            if (deductedtime != null) {
                int days = 0;
                if (type == 2) {
                    days = Constants.MONTHDAY;
                }
                if (type == 3) {
                    days = Constants.YEARDAY;
                }
                String failDay = DateUtil.getNextDay(deductedtime.toString(), days);
                isPast = DateUtil.isPastDate(failDay);
            }
            //����¿����꿨û���ڣ�ֱ�ӳ���
            if (!isPast) {
                return Msg.fail().add("type", 1);
            } else if (isPast || type == 1) {
                //�������
                double balance = depotcard.getMoney();
                //��ѯͣ�����Ƿ����Ż�ȯ
                int freeMoney = 0;
                List<CouponData> coupons = couponService.findAllCouponByCardNum(cardnum, "");
                if (coupons != null && coupons.size() > 0) {
                    freeMoney = coupons.get(0).getMoney();
                }
                //����鿴ͣ��������Ƿ��㹻�۷ѣ���������Ҫ�ֽ��ɨ��
                hour = DateUtil.getDiffHours(parkin, new Date());
                //���ý��
                double validMoney = balance + freeMoney - illegalmoney;
                //��Ҫ֧���Ľ��(������������Żݽ��)
                long needPayMoney = hour * Constants.HOURMONEY;
                if (validMoney < needPayMoney) {
                    return Msg.success().add("money_pay", needPayMoney - validMoney).add("va_msg", "����" + (illegalmoney > 0 ? ",��Υ�棺" + illegalMsg : ""));
                } else {
                    return Msg.fail().add("type", 0).add("money_pay", needPayMoney + illegalmoney - freeMoney);
                }
            }
        }
        return null;
    }


}
