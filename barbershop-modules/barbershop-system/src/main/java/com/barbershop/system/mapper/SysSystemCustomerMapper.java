package com.barbershop.system.mapper;

import java.util.List;
import com.barbershop.system.domain.SysSystemCustomer;

/**
 * 客户管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
public interface SysSystemCustomerMapper 
{
    /**
     * 查询客户管理
     * 
     * @param customerId 客户管理主键
     * @return 客户管理
     */
    public SysSystemCustomer selectSysSystemCustomerByCustomerId(Long customerId);

    /**
     * 查询客户管理列表
     * 
     * @param sysSystemCustomer 客户管理
     * @return 客户管理集合
     */
    public List<SysSystemCustomer> selectSysSystemCustomerList(SysSystemCustomer sysSystemCustomer);

    /**
     * 新增客户管理
     * 
     * @param sysSystemCustomer 客户管理
     * @return 结果
     */
    public int insertSysSystemCustomer(SysSystemCustomer sysSystemCustomer);

    /**
     * 修改客户管理
     * 
     * @param sysSystemCustomer 客户管理
     * @return 结果
     */
    public int updateSysSystemCustomer(SysSystemCustomer sysSystemCustomer);

    /**
     * 删除客户管理
     * 
     * @param customerId 客户管理主键
     * @return 结果
     */
    public int deleteSysSystemCustomerByCustomerId(Long customerId);

    /**
     * 批量删除客户管理
     * 
     * @param customerIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysSystemCustomerByCustomerIds(Long[] customerIds);
}
