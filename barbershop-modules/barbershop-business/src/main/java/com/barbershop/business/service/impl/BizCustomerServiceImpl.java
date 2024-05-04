package com.barbershop.business.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.barbershop.business.vo.BizCustomerReportVO;
import com.barbershop.common.core.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barbershop.business.mapper.BizCustomerMapper;
import com.barbershop.business.domain.BizCustomer;
import com.barbershop.business.service.IBizCustomerService;

/**
 * 客户管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-11
 */
@Service
public class BizCustomerServiceImpl implements IBizCustomerService 
{
    @Autowired
    private BizCustomerMapper bizCustomerMapper;

    /**
     * 查询客户管理
     * 
     * @param customerId 客户管理主键
     * @return 客户管理
     */
    @Override
    public BizCustomer selectBizCustomerByCustomerId(Long customerId)
    {
        return bizCustomerMapper.selectBizCustomerByCustomerId(customerId);
    }

    /**
     * 查询客户管理列表
     * 
     * @param bizCustomer 客户管理
     * @return 客户管理
     */
    @Override
    public List<BizCustomer> selectBizCustomerList(BizCustomer bizCustomer)
    {
        return bizCustomerMapper.selectBizCustomerList(bizCustomer);
    }

    /**
     * 新增客户管理
     * 
     * @param bizCustomer 客户管理
     * @return 结果
     */
    @Override
    public int insertBizCustomer(BizCustomer bizCustomer)
    {
        bizCustomer.setCreateTime(DateUtils.getNowDate());
        return bizCustomerMapper.insertBizCustomer(bizCustomer);
    }

    /**
     * 修改客户管理
     * 
     * @param bizCustomer 客户管理
     * @return 结果
     */
    @Override
    public int updateBizCustomer(BizCustomer bizCustomer)
    {
        bizCustomer.setUpdateTime(DateUtils.getNowDate());
        return bizCustomerMapper.updateBizCustomer(bizCustomer);
    }

    /**
     * 批量删除客户管理
     * 
     * @param customerIds 需要删除的客户管理主键
     * @return 结果
     */
    @Override
    public int deleteBizCustomerByCustomerIds(Long[] customerIds)
    {
        return bizCustomerMapper.deleteBizCustomerByCustomerIds(customerIds);
    }

    /**
     * 删除客户管理信息
     * 
     * @param customerId 客户管理主键
     * @return 结果
     */
    @Override
    public int deleteBizCustomerByCustomerId(Long customerId)
    {
        return bizCustomerMapper.deleteBizCustomerByCustomerId(customerId);
    }

    @Override
    public List<BizCustomer> selectBizCustomerAll() {
        return bizCustomerMapper.selectBizCustomerAll();
    }

    /**
     * 统计指定时间区间内的客户数据
     * @param begin
     * @param end
     * @return
     */
    @Override
    public BizCustomerReportVO getBizCustomerStatistics(LocalDate begin, LocalDate end) {
        //存放从begin到end之间的每天对应的日期
        List<LocalDate> dateList = new ArrayList<>();

        dateList.add(begin);

        while (!begin.equals(end)) {
            begin = begin.plusDays(1);
            dateList.add(begin);
        }

        List<Integer> newBizCustomerList = new ArrayList<>();

        List<Integer> totalBizCustomerList = new ArrayList<>();

        for (LocalDate date : dateList) {
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);

            Map map = new HashMap();
            map.put("end", endTime);

            //总用户数量
            Integer totalBizCustomer = bizCustomerMapper.countByMap(map);

            map.put("begin", beginTime);
            //新增用户数量
            Integer newBizCustomer = bizCustomerMapper.countByMap(map);

            totalBizCustomerList.add(totalBizCustomer);
            newBizCustomerList.add(newBizCustomer);
        }

        //封装结果数据
        return BizCustomerReportVO
                .builder()
                .dateList(StringUtils.join(dateList, ","))
                .totalBizCustomerList(StringUtils.join(totalBizCustomerList, ","))
                .newBizCustomerList(StringUtils.join(newBizCustomerList, ","))
                .build();
    }


}
