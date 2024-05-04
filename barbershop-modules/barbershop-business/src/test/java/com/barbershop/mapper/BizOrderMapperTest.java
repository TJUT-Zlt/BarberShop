package com.barbershop.mapper;

import com.barbershop.business.RuoYiBusinessApplication;
import com.barbershop.business.domain.BizOrder;
import com.barbershop.business.mapper.BizOrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiBusinessApplication.class})
public class BizOrderMapperTest {

    @Autowired
    private BizOrderMapper bizOrderMapper;

    @Test
    public void testInsertBizOrder(){
        BizOrder bizOrder = new BizOrder();
        bizOrder.setOrderPrice(20.00);
        bizOrder.setCustomerId(1L);
        bizOrder.setOrderType("2");
        bizOrder.setUserId(2L);
        int result = bizOrderMapper.insertBizOrder(bizOrder);
        System.out.println(result);
    }




}