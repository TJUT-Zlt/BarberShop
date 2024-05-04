package com.barbershop.business.service.impl;

import com.barbershop.business.mapper.BizCustomerMapper;
import com.barbershop.business.mapper.BizOrderMapper;
import com.barbershop.business.service.BizWorkspaceService;
import com.barbershop.business.vo.BizCustomerOverViewVO;
import com.barbershop.business.vo.BusinessDataVO;

import com.barbershop.business.vo.SysUserOverViewVO;
import com.barbershop.common.core.domain.R;
import com.barbershop.system.api.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class BizWorkspaceServiceImpl implements BizWorkspaceService {

    @Autowired
    private BizOrderMapper bizOrderMapper;
    @Autowired
    private BizCustomerMapper bizCustomerMapper;

    @Autowired
    private RemoteUserService remoteUserService;
    /**
     * 根据时间段统计营业数据
     * @param begin
     * @param end
     * @return
     */
    public BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end) {
        /**
         * 营业额：当日已完成订单的总金额
         * 平均客单价：营业额 / 有效订单数
         * 新增用户：当日新增用户的数量
         */

        Map map = new HashMap();
        map.put("begin",begin);
        map.put("end",end);

        //查询总订单数
        Integer totalOrderCount = bizOrderMapper.orderCountByMap(map);

        //营业额
        Double turnover = bizOrderMapper.orderSumByMap(map);
        turnover = turnover == null? 0.0 : turnover;

        Double unitPrice = 0.0;

        if(totalOrderCount != 0 ){

            //平均客单价
            unitPrice =  turnover / totalOrderCount;
        }

        //新增用户数
        Integer newBizCustomers = bizCustomerMapper.countByMap(map);

        return BusinessDataVO.builder()
                .turnover(turnover)
                .unitPrice(unitPrice)
                .orderNum(totalOrderCount)
                .newBizCustomers(newBizCustomers)
                .build();
    }

    @Override
    public BizCustomerOverViewVO getBizCustomerOverView() {
        Map map = new HashMap();
        map.put("customerAccountLevel", 1);
        Integer vipBizCustomers = bizCustomerMapper.countByMap(map);

        map.put("customerAccountLevel", 0);
        Integer commonBizCustomers = bizCustomerMapper.countByMap(map);

        return BizCustomerOverViewVO.builder()
                .vipBizCustomers(vipBizCustomers)
                .commonBizCustomers(commonBizCustomers)
                .build();
    }

    @Override
    public SysUserOverViewVO getSysUserOverView() {
        R<Integer> integerR01 = remoteUserService.selectSysUserByTodayStatus('0');
        Integer obDutySysUsers = integerR01.getData();
        R<Integer> integerR02 = remoteUserService.selectSysUserByTodayStatus('1');
        Integer onLeaveSysUsers = integerR02.getData();
        R<Integer> integerR03 = remoteUserService.selectSysUserByTodayStatus('2');
        Integer businessSysUsers = integerR03.getData();
        R<Integer> integerR04 = remoteUserService.selectSysUserByTodayStatus('3');
        Integer departSysUsers = integerR04.getData();

        return SysUserOverViewVO.builder()
                .obDutySysUsers(obDutySysUsers)
                .onLeaveSysUsers(onLeaveSysUsers)
                .businessSysUsers(businessSysUsers)
                .departSysUsers(departSysUsers)
                .build();
    }


}
