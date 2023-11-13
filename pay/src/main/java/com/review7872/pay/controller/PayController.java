package com.review7872.pay.controller;

import com.review7872.pay.pojo.Pay;
import com.review7872.pay.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pay")
@Slf4j
public class PayController {
    @Autowired
    private PayService payService;

    @GetMapping("/selectAll")
    @Cacheable(value = "pay", key = "#root.methodName")
    public List<Pay> selectAll() {
        return payService.selectAll();
    }

    @GetMapping("/selectByPayStat")
    @Cacheable(value = "pay", key = "#root.methodName + #payStat")
    public List<Pay> selectByPayStat(byte payStat) {
        return payService.selectByPayStat(payStat);
    }

    @GetMapping("/selectByPayWay")
    @Cacheable(value = "pay", key = "#root.methodName + #payWay")
    public List<Pay> selectByPayWay(byte payWay) {
        return payService.selectByPayWay(payWay);
    }

    @GetMapping("/selectByPayWayAndPayStat")
    @Cacheable(value = "pay", key = "#root.methodName + #payWay + #payStat")
    public List<Pay> selectByPayWayAndPayStat(byte payWay, byte payStat) {
        return payService.selectByPayWayAndPayStat(payWay, payStat);
    }

    @GetMapping("/selectByPayId")
    public Pay selectByPayId(long payId) {
        Pay pay = payService.selectByPayId(payId);
        log.info(pay.toString());
        return pay;
    }

    @PostMapping("/insert")
    @CacheEvict(value = "pay", allEntries = true)
    public long insertPay() {
        return payService.insertPay();
    }

    @PostMapping("/updatePayStat")
    @CacheEvict(value = "pay", allEntries = true)
    public Integer updatePayStat(byte payStat, long payId) {
        return payService.updatePayStat(payStat, payId);
    }

    @PostMapping("/updatePayWay")
    @CacheEvict(value = "pay", allEntries = true)
    public Integer updatePayWay(byte payWay, long payId) {
        return payService.updatePayWay(payWay, payId);
    }

    @PostMapping("/updatePayWayAndPayStat")
    @CacheEvict(value = "pay", allEntries = true)
    public Integer updatePayWayAndPayStat(byte payWay, byte payStat, long payId) {
        return payService.updatePayWayAndPayStat(payWay, payStat, payId);
    }
}
