package com.barbershop.service;

import com.barbershop.business.RuoYiBusinessApplication;
import com.barbershop.business.domain.BizOrder;
import com.barbershop.business.service.IBizOrderService;
import com.barbershop.business.domain.vo.report.BizOrderReportVO;
import com.barbershop.business.domain.vo.report.TurnoverReportVO;
import com.barbershop.common.core.domain.R;
import com.barbershop.system.api.RemoteUserService;
import com.barbershop.system.api.domain.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiBusinessApplication.class})
public class BizOrderServiceTest {

    @Autowired
    private IBizOrderService bizOrderService;

    @Autowired
    private RemoteUserService remoteUserService;

    @Test
    public void testInsertBizOrder(){
        BizOrder bizOrder = new BizOrder();
        bizOrder.setOrderPrice(BigDecimal.valueOf(888.00));
        bizOrder.setCustomerId(1L);
        bizOrder.setOrderType("2");
        bizOrder.setUserId(2L);
        int result = bizOrderService.insertBizOrder(bizOrder);
        System.out.println(result);
    }

    @Test
    public void testInsertBizOrderWithNoBizCustomer(){
        BizOrder bizOrder = new BizOrder();
        bizOrder.setOrderPrice(BigDecimal.valueOf(20.00));
        bizOrder.setOrderType("2");
        bizOrder.setUserId(2L);
        int result = bizOrderService.insertBizOrderWithNoBizCustomer(bizOrder);
        System.out.println(result);
    }
    
    @Test
    public void test(){
        SysUser sysUser = new SysUser();
        sysUser.setUserId(2L);
        sysUser.setCommission(BigDecimal.valueOf(20.00));
        R<Integer> integerR = remoteUserService.updateSysUser(sysUser);
        System.out.println(integerR.getData());
    }


    @Test
    public void testGetTurnoverStatistics(){
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        TurnoverReportVO turnoverStatistics = bizOrderService.getTurnoverStatistics(yesterday, today);
        System.out.println(turnoverStatistics);
    }

    @Test
    public void testGetOrderStatistics(){
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        BizOrderReportVO orderStatistics = bizOrderService.getOrderStatistics(yesterday, today);
        System.out.println(orderStatistics);
    }


}


