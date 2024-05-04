package com.barbershop.business.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.barbershop.business.domain.BizCustomer;
import com.barbershop.business.mapper.BizCustomerMapper;
import com.barbershop.business.vo.OrderReportVO;
import com.barbershop.business.vo.TurnoverReportVO;
import com.barbershop.common.core.domain.R;
import com.barbershop.common.core.utils.DateUtils;
import com.barbershop.system.api.RemoteUserService;
import com.barbershop.system.api.domain.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barbershop.business.mapper.BizOrderMapper;
import com.barbershop.business.domain.BizOrder;
import com.barbershop.business.service.IBizOrderService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-11
 */
@Service
public class BizOrderServiceImpl implements IBizOrderService 
{
    @Autowired
    private BizOrderMapper bizOrderMapper;

    @Autowired
    private BizCustomerMapper bizCustomerMapper;

    @Autowired
    private RemoteUserService remoteUserService;

    /**
     * 查询订单管理
     * 
     * @param orderId 订单管理主键
     * @return 订单管理
     */
    @Override
    public BizOrder selectBizOrderByOrderId(Long orderId)
    {
        return bizOrderMapper.selectBizOrderByOrderId(orderId);
    }

    /**
     * 查询订单管理列表
     * 
     * @param bizOrder 订单管理
     * @return 订单管理
     */
    @Override
    public List<BizOrder> selectBizOrderList(BizOrder bizOrder)
    {
        return bizOrderMapper.selectBizOrderList(bizOrder);
    }

    /**
     * 新增订单管理
     * 
     * @param bizOrder 订单管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBizOrder(BizOrder bizOrder)
    {
        bizOrder.setCreateTime(DateUtils.getNowDate());

        Double orderPrice = bizOrder.getOrderPrice();

        BizCustomer bizCustomer = bizCustomerMapper.selectBizCustomerByCustomerId(bizOrder.getCustomerId());
        if(bizCustomer.getCustomerAccountBalance() - orderPrice > 0){
            bizCustomer.setCustomerAccountBalance(bizCustomer.getCustomerAccountBalance() - orderPrice);
            bizCustomer.setCumulativeConsumption(bizCustomer.getCumulativeConsumption() + orderPrice);
            bizCustomerMapper.updateBizCustomer(bizCustomer);

            R<SysUser> sysUserR = remoteUserService.selectSysUserById(bizOrder.getUserId());
            SysUser sysUser = sysUserR.getData();
            sysUser.setCommission(sysUser.getCommission() + orderPrice);
            sysUser.setRealIncome(sysUser.getRealIncome() + orderPrice * 0.2);
            remoteUserService.updateSysUser(sysUser);
            return bizOrderMapper.insertBizOrder(bizOrder);
        }
        return -1;
    }

    /**
     * 修改订单管理
     * 
     * @param bizOrder 订单管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBizOrder(BizOrder bizOrder)
    {
        bizOrder.setUpdateTime(DateUtils.getNowDate());

        Double orderPrice = bizOrder.getOrderPrice();

        BizCustomer bizCustomer = bizCustomerMapper.selectBizCustomerByCustomerId(bizOrder.getCustomerId());
        bizCustomer.setCustomerAccountBalance(bizCustomer.getCustomerAccountBalance() - orderPrice);
        bizCustomer.setCumulativeConsumption(bizCustomer.getCumulativeConsumption() + orderPrice);
        bizCustomerMapper.updateBizCustomer(bizCustomer);

        R<SysUser> sysUserR = remoteUserService.selectSysUserById(bizOrder.getUserId());
        SysUser sysUser = sysUserR.getData();
        sysUser.setCommission(sysUser.getCommission() + orderPrice);

        System.out.println(sysUser);
        remoteUserService.updateSysUser(sysUser);

        return bizOrderMapper.updateBizOrder(bizOrder);
    }

    /**
     * 批量删除订单管理
     * 
     * @param orderIds 需要删除的订单管理主键
     * @return 结果
     */
    @Override
    public int deleteBizOrderByOrderIds(Long[] orderIds)
    {
        return bizOrderMapper.deleteBizOrderByOrderIds(orderIds);
    }

    /**
     * 删除订单管理信息
     * 
     * @param orderId 订单管理主键
     * @return 结果
     */
    @Override
    public int deleteBizOrderByOrderId(Long orderId)
    {
        return bizOrderMapper.deleteBizOrderByOrderId(orderId);
    }
    /**
     * 统计指定时间区间内的营业额数据
     * @param begin
     * @param end
     * @return
     */
    @Override
    public TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end) {
        //当前集合用于存放从begin到end范围内的每天的日期
        List<LocalDate> dateList = new ArrayList<>();

        dateList.add(begin);
        while (!begin.equals(end)){
            //日期计算，计算指定日期的后一天对应的日期
            begin = begin.plusDays(1);
            dateList.add(begin);
        }

        //存放每天的营业额
        List<Double> turnoverList = new ArrayList<>();
        for(LocalDate date:dateList){
            //查询date日期对应的营业额数据
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);

            // select sum(amount) from orders where order_time > beginTime and order_time < endTime
            Map map = new HashMap();
            map.put("begin", beginTime);
            map.put("end", endTime);
            Double turnover = bizOrderMapper.orderSumByMap(map);
            turnover = turnover == null ? 0.0 : turnover;
            turnoverList.add(turnover);
        }
        //封装返回结果
        return TurnoverReportVO
                .builder()
                .dateList(StringUtils.join(dateList, ","))
                .turnoverList(StringUtils.join(turnoverList, ","))
                .build();
    }

    @Override
    public OrderReportVO getOrderStatistics(LocalDate begin, LocalDate end) {
        //当前集合用于存放从begin到end范围内的每天的日期
        List<LocalDate> dateList = new ArrayList<>();

        dateList.add(begin);
        while (!begin.equals(end)){
            //日期计算，计算指定日期的后一天对应的日期
            begin = begin.plusDays(1);
            dateList.add(begin);
        }
        //存放每天的订单总数
        List<Integer> orderCountList = new ArrayList<>();

        for(LocalDate date:dateList){
            //查询date日期对应的营业额数据
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);

            Map map = new HashMap();
            map.put("begin", beginTime);
            map.put("end", endTime);
            Integer sumOrder = bizOrderMapper.orderCountByMap(map);
            sumOrder = sumOrder == null ? 0 : sumOrder;
            orderCountList.add(sumOrder);
        }

        Integer totalOrderCount = orderCountList.stream().reduce(Integer::sum).get();

        return  OrderReportVO
                .builder()
                .dateList(StringUtils.join(dateList,","))
                .orderCountList(StringUtils.join(orderCountList,","))
                .totalOrderCount(totalOrderCount)
                .build();

    }


}
