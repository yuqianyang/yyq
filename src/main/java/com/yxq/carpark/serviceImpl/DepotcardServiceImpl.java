package com.yxq.carpark.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yxq.carpark.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yxq.carpark.dao.DepotcardDao;
import com.yxq.carpark.dto.DepotcardManagerData;
import com.yxq.carpark.entity.Depotcard;
import com.yxq.carpark.service.DepotcardService;


@Service
public class DepotcardServiceImpl implements DepotcardService {

    @Autowired
    private DepotcardDao depotcardDao;

    public List<DepotcardManagerData> findAllDepotcard(String cardnum, int page, int size) {
        List<DepotcardManagerData> depotcardManagerDatas = depotcardDao.findAllDepotcard(cardnum, page, size);
        return depotcardManagerDatas;
    }

    public Depotcard save(DepotcardManagerData depotcardManagerData) {
        Date date = new Date();
        String trans = DateUtil.dateFormat(date, DateUtil.yyyyMMdd);
        String dateStr = trans.replaceAll(" ", "").replaceAll("-", "");
        //Éú³ÉÍ£³µ¿¨ºÅ
        String cardnum = depotcardManagerData.getUsername() + dateStr;
        Depotcard depotcard = new Depotcard(cardnum, depotcardManagerData.getCarNum(), Integer.parseInt(depotcardManagerData.getType()), depotcardManagerData.getMoney(), date, depotcardManagerData.getDeductedtime());
        depotcardDao.save(depotcard);
        depotcard = depotcardDao.findByCardnum(cardnum);
        return depotcard;
    }

    public Depotcard findByCardid(int cardid) {
        return depotcardDao.findByCardid(cardid);
    }

    public Depotcard findByCardnum(String cardnum) {
        Depotcard depotcard = depotcardDao.findByCardnum(cardnum);
        return depotcard;
    }

    public void updateDepotcardBycardnum(Depotcard depotcard) {
        depotcardDao.updateDepotcardBycardnum(depotcard);
    }

    public void deleteDepotCard(String cardnum) {
        depotcardDao.deleteDepotCard(cardnum);
    }

    public void addMoney(String cardnum, double money) {
        depotcardDao.addMoney(cardnum, money);
    }

    public int findAllDepotcardCount(String cardnum) {
        return depotcardDao.findAllDepotcardCount(cardnum);
    }

    public List<DepotcardManagerData> findByCardId(int cardid) {
        return depotcardDao.findByCardId(cardid);
    }

    public void updateCardnum(String cardnum, String cardnumNew) {
        depotcardDao.updateCardnum(cardnum, cardnumNew);
    }

    @Override
    public String findCardnumByCarnum(String carnum) {
        // TODO Auto-generated method stub
        return depotcardDao.findCardnumByCarnum(carnum);
    }

    @Override
    public String findCardnumByCarnum1(String carnum) {
        return depotcardDao.findCardnumByCarnum1(carnum);

    }

}
