package com.yxq.carpark.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yxq.carpark.dao.ParkinfoDao;
import com.yxq.carpark.dto.FormData;
import com.yxq.carpark.entity.ParkInfo;
import com.yxq.carpark.service.ParkinfoService;


@Service
public class ParkinfoServiceImpl implements ParkinfoService {

    @Autowired
    private ParkinfoDao parkinfoDao;

    public void saveParkinfo(FormData data) {
        ParkInfo parkInfo = new ParkInfo(data.getParkNum(),data.getCardNum(),data.getCarNum(),new Date(),data.getParkTem());
        parkinfoDao.save(parkInfo);
    }

    public ParkInfo findParkinfoByParknum(int parknum) {
        return parkinfoDao.findParkinfoByParknum(parknum);
    }

    public void deleteParkinfoByParkNum(int parkNum) {
        parkinfoDao.deleteParkinfoByParkNum(parkNum);
    }

    public ParkInfo findParkinfoByCardnum(String cardnum) {
        return parkinfoDao.findParkinfoByCardnum(cardnum);
    }

    public void updateCardnum(String cardnum, String cardnumNew) {
        parkinfoDao.updateCardnum(cardnum, cardnumNew);
    }
}
