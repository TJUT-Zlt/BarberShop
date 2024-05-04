package com.barbershop.mapper;

import com.barbershop.business.RuoYiBusinessApplication;
import com.barbershop.business.domain.BizCustomer;
import com.barbershop.business.mapper.BizCustomerMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiBusinessApplication.class})
public class BizCustomerMapperTest {
    @Autowired
    private BizCustomerMapper bizCustomerMapper;

    @Test
    public void testSelectBizCustomerList(){
        BizCustomer bizCustomer = new BizCustomer();
        List<BizCustomer> bizCustomers = bizCustomerMapper.selectBizCustomerList(bizCustomer);
    }

    @Test
    public void testSelectBizCustomerByCustomerId(){
        BizCustomer bizCustomer = bizCustomerMapper.selectBizCustomerByCustomerId(1L);
        System.out.println(bizCustomer);
    }
    @Test
    public void testSelectBizCustomerAll(){
        List<BizCustomer> bizCustomerList = bizCustomerMapper.selectBizCustomerAll();
        bizCustomerList.forEach(System.out::println);
    }


}

