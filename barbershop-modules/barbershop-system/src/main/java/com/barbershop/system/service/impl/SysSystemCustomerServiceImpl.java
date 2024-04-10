package com.barbershop.system.service.impl;

import java.util.List;
import com.barbershop.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barbershop.system.mapper.SysSystemCustomerMapper;
import com.barbershop.system.domain.SysSystemCustomer;
import com.barbershop.system.service.ISysSystemCustomerService;

/**
 * 客户管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
@Service
public class SysSystemCustomerServiceImpl implements ISysSystemCustomerService 
{
    @Autowired
    private SysSystemCustomerMapper sysSystemCustomerMapper;

    /**
     * 查询客户管理
     * 
     * @param customerId 客户管理主键
     * @return 客户管理
     */
    @Override
    public SysSystemCustomer selectSysSystemCustomerByCustomerId(Long customerId)
    {
        return sysSystemCustomerMapper.selectSysSystemCustomerByCustomerId(customerId);
    }

    /**
     * 查询客户管理列表
     * 
     * @param sysSystemCustomer 客户管理
     * @return 客户管理
     */
    @Override
    public List<SysSystemCustomer> selectSysSystemCustomerList(SysSystemCustomer sysSystemCustomer)
    {
        return sysSystemCustomerMapper.selectSysSystemCustomerList(sysSystemCustomer);
    }

    /**
     * 新增客户管理
     * 
     * @param sysSystemCustomer 客户管理
     * @return 结果
     */
    @Override
    public int insertSysSystemCustomer(SysSystemCustomer sysSystemCustomer)
    {
        sysSystemCustomer.setCreateTime(DateUtils.getNowDate());
        return sysSystemCustomerMapper.insertSysSystemCustomer(sysSystemCustomer);
    }

    /**
     * 修改客户管理
     * 
     * @param sysSystemCustomer 客户管理
     * @return 结果
     */
    @Override
    public int updateSysSystemCustomer(SysSystemCustomer sysSystemCustomer)
    {
        sysSystemCustomer.setUpdateTime(DateUtils.getNowDate());
        return sysSystemCustomerMapper.updateSysSystemCustomer(sysSystemCustomer);
    }

    /**
     * 批量删除客户管理
     * 
     * @param customerIds 需要删除的客户管理主键
     * @return 结果
     */
    @Override
    public int deleteSysSystemCustomerByCustomerIds(Long[] customerIds)
    {
        return sysSystemCustomerMapper.deleteSysSystemCustomerByCustomerIds(customerIds);
    }

    /**
     * 删除客户管理信息
     * 
     * @param customerId 客户管理主键
     * @return 结果
     */
    @Override
    public int deleteSysSystemCustomerByCustomerId(Long customerId)
    {
        return sysSystemCustomerMapper.deleteSysSystemCustomerByCustomerId(customerId);
    }
}
