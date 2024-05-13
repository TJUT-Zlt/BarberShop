package com.barbershop.business.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.barbershop.business.domain.BizCustomer;
import com.barbershop.business.mapper.BizCustomerMapper;
import com.barbershop.business.domain.vo.report.BizOrderReportVO;
import com.barbershop.business.domain.vo.report.TurnoverReportVO;
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

        BigDecimal orderPrice = bizOrder.getOrderPrice();

        BizCustomer bizCustomer = bizCustomerMapper.selectBizCustomerByCustomerId(bizOrder.getCustomerId());
        if(bizCustomer.getCustomerAccountBalance().subtract(orderPrice).compareTo(BigDecimal.valueOf(0)) == 1 ){

            bizCustomer.setCustomerAccountBalance(bizCustomer.getCustomerAccountBalance().subtract(orderPrice));
            bizCustomer.setCumulativeConsumption(bizCustomer.getCumulativeConsumption().add(orderPrice));
            bizCustomerMapper.updateBizCustomer(bizCustomer);

            R<SysUser> sysUserR = remoteUserService.selectSysUserById(bizOrder.getUserId());
            SysUser sysUser = sysUserR.getData();
            sysUser.setCommission(sysUser.getCommission().add(orderPrice));
            sysUser.setRealIncome(sysUser.getRealIncome().add(orderPrice.multiply(BigDecimal.valueOf(0.20))));
            remoteUserService.updateSysUser(sysUser);

            return bizOrderMapper.insertBizOrder(bizOrder);
        }else {
            return -1;
        }

    }

    /**
     * 新增没有指定客户的订单管理
     * @param bizOrder
     * @return
     */
    @Transactional
    @Override
    public int insertBizOrderWithNoBizCustomer(BizOrder bizOrder) {
        bizOrder.setCreateTime(DateUtils.getNowDate());

        BigDecimal orderPrice = bizOrder.getOrderPrice();

        R<SysUser> sysUserR = remoteUserService.selectSysUserById(bizOrder.getUserId());
        SysUser sysUser = sysUserR.getData();
        sysUser.setCommission(sysUser.getCommission().add(orderPrice));
        sysUser.setRealIncome(sysUser.getRealIncome().add(orderPrice.multiply(BigDecimal.valueOf(0.20))));
        remoteUserService.updateSysUser(sysUser);

        return bizOrderMapper.insertBizOrder(bizOrder);

    }

    /**
     * 修改订单管理
     * 
     * @param bizOrder 订单管理
     * @return 结果
     */
    // TODO: 2024/5/13  修改订单功能待完善 --目前仅仅允许修改价格和服务人员 --不支持修改客户
    @Transactional
    @Override
    public int updateBizOrder(BizOrder bizOrder)
    {

        BizOrder oldBizOrder = bizOrderMapper.selectBizOrderByOrderId(bizOrder.getOrderId());

        // 计算订单价格变化
        BigDecimal orderPriceChange = bizOrder.getOrderPrice().subtract(oldBizOrder.getOrderPrice());

        BizCustomer bizCustomer = bizCustomerMapper.selectBizCustomerByCustomerId(bizOrder.getCustomerId());
        if(bizCustomer != null && bizOrder.getUserId() == oldBizOrder.getUserId() ){
            bizCustomer.setCustomerAccountBalance(bizCustomer.getCustomerAccountBalance().subtract(orderPriceChange));
            bizCustomer.setCumulativeConsumption(bizCustomer.getCumulativeConsumption().add(orderPriceChange));
            bizCustomerMapper.updateBizCustomer(bizCustomer);

            R<SysUser> sysUserR = remoteUserService.selectSysUserById(bizOrder.getUserId());
            SysUser sysUser = sysUserR.getData();
            sysUser.setCommission(sysUser.getCommission().add(orderPriceChange));
            sysUser.setRealIncome(sysUser.getRealIncome().add(orderPriceChange.multiply(BigDecimal.valueOf(0.20))));
            remoteUserService.updateSysUser(sysUser);


        }else if(bizCustomer != null && bizOrder.getUserId() != oldBizOrder.getUserId()){
            bizCustomer.setCustomerAccountBalance(bizCustomer.getCustomerAccountBalance().subtract(orderPriceChange));
            bizCustomer.setCumulativeConsumption(bizCustomer.getCumulativeConsumption().add(orderPriceChange));
            bizCustomerMapper.updateBizCustomer(bizCustomer);
            //新的服务人员
            R<SysUser> newSysUserR = remoteUserService.selectSysUserById(bizOrder.getUserId());
            SysUser newSysUser = newSysUserR.getData();
            newSysUser.setCommission(newSysUser.getCommission().add(bizOrder.getOrderPrice()));
            newSysUser.setRealIncome(newSysUser.getRealIncome().add(bizOrder.getOrderPrice().multiply(BigDecimal.valueOf(0.20))));
            remoteUserService.updateSysUser(newSysUser);
            //原来的服务人员
            R<SysUser> oldSysUserR = remoteUserService.selectSysUserById(oldBizOrder.getUserId());
            SysUser oldSysUser = oldSysUserR.getData();
            oldSysUser.setCommission(oldSysUser.getCommission().subtract(oldBizOrder.getOrderPrice()));
            oldSysUser.setRealIncome(oldSysUser.getRealIncome().subtract(oldBizOrder.getOrderPrice().multiply(BigDecimal.valueOf(0.20))));
            remoteUserService.updateSysUser(oldSysUser);

        }else if(bizCustomer == null && bizOrder.getUserId() == oldBizOrder.getUserId() ){
            R<SysUser> sysUserR = remoteUserService.selectSysUserById(bizOrder.getUserId());
            SysUser sysUser = sysUserR.getData();
            sysUser.setCommission(sysUser.getCommission().add(orderPriceChange));
            sysUser.setRealIncome(sysUser.getRealIncome().add(orderPriceChange.multiply(BigDecimal.valueOf(0.20))));
            remoteUserService.updateSysUser(sysUser);
        }else if(bizCustomer == null && bizOrder.getUserId() != oldBizOrder.getUserId()){
            //新的服务人员
            R<SysUser> newSysUserR = remoteUserService.selectSysUserById(bizOrder.getUserId());
            SysUser newSysUser = newSysUserR.getData();
            newSysUser.setCommission(newSysUser.getCommission().add(bizOrder.getOrderPrice()));
            newSysUser.setRealIncome(newSysUser.getRealIncome().add(bizOrder.getOrderPrice().multiply(BigDecimal.valueOf(0.20))));
            remoteUserService.updateSysUser(newSysUser);
            //原来的服务人员
            R<SysUser> oldSysUserR = remoteUserService.selectSysUserById(oldBizOrder.getUserId());
            SysUser oldSysUser = oldSysUserR.getData();
            oldSysUser.setCommission(oldSysUser.getCommission().subtract(oldBizOrder.getOrderPrice()));
            oldSysUser.setRealIncome(oldSysUser.getRealIncome().subtract(oldBizOrder.getOrderPrice().multiply(BigDecimal.valueOf(0.20))));
            remoteUserService.updateSysUser(oldSysUser);

        }

        bizOrder.setUpdateTime(DateUtils.getNowDate());
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
        List<BigDecimal> turnoverList = new ArrayList<>();
        for(LocalDate date:dateList){
            //查询date日期对应的营业额数据
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);

            // select sum(amount) from orders where order_time > beginTime and order_time < endTime
            Map map = new HashMap();
            map.put("begin", beginTime);
            map.put("end", endTime);
            BigDecimal turnover = bizOrderMapper.orderSumByMap(map);
            turnover = turnover == null ? BigDecimal.valueOf(0.0) : turnover;
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
    public BizOrderReportVO getOrderStatistics(LocalDate begin, LocalDate end) {
        //当前集合用于存放从begin到end范围内的每天的日期
        List<LocalDate> dateList = new ArrayList<>();

        dateList.add(begin);
        while (!begin.equals(end)){
            //日期计算，计算指定日期的后一天对应的日期
            begin = begin.plusDays(1);
            dateList.add(begin);
        }
        //存放每天的订单总数
        List<Double> orderCountList = new ArrayList<>();

        for(LocalDate date:dateList){
            //查询date日期对应的营业额数据
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);

            Map map = new HashMap();
            map.put("begin", beginTime);
            map.put("end", endTime);
            Double sumOrder = bizOrderMapper.orderCountByMap(map);
            sumOrder = sumOrder == null ? 0 : sumOrder;
            orderCountList.add(sumOrder);
        }

        Double totalOrderCount = orderCountList.stream().reduce(Double::sum).get();

        return  BizOrderReportVO
                .builder()
                .dateList(StringUtils.join(dateList,","))
                .orderCountList(StringUtils.join(orderCountList,","))
                .totalOrderCount(totalOrderCount)
                .build();

    }


}
