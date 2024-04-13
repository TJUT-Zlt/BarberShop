package com.barbershop.business.service.impl;

import java.util.List;
import com.barbershop.common.core.utils.DateUtils;
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
}
