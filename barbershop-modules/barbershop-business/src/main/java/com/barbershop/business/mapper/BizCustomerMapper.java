package com.barbershop.business.mapper;

import java.util.List;
import com.barbershop.business.domain.BizCustomer;
import com.barbershop.system.api.domain.SysUser;

/**
 * 客户管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-11
 */
public interface BizCustomerMapper 
{
    /**
     * 查询客户管理
     * 
     * @param customerId 客户管理主键
     * @return 客户管理
     */
    public BizCustomer selectBizCustomerByCustomerId(Long customerId);

    /**
     * 查询客户管理列表
     * 
     * @param bizCustomer 客户管理
     * @return 客户管理集合
     */
    public List<BizCustomer> selectBizCustomerList(BizCustomer bizCustomer);

    /**
     * 新增客户管理
     * 
     * @param bizCustomer 客户管理
     * @return 结果
     */
    public int insertBizCustomer(BizCustomer bizCustomer);

    /**
     * 修改客户管理
     * 
     * @param bizCustomer 客户管理
     * @return 结果
     */
    public int updateBizCustomer(BizCustomer bizCustomer);

    /**
     * 删除客户管理
     * 
     * @param customerId 客户管理主键
     * @return 结果
     */
    public int deleteBizCustomerByCustomerId(Long customerId);

    /**
     * 批量删除客户管理
     * 
     * @param customerIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizCustomerByCustomerIds(Long[] customerIds);

    /**
     * 查询所有客户
     * @return
     */
    public List<BizCustomer> selectBizCustomerAll();

}
