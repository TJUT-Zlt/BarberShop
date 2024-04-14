package com.barbershop.business.service.impl;

import java.util.List;

import com.barbershop.business.domain.BizCustomer;
import com.barbershop.business.mapper.BizCustomerMapper;
import com.barbershop.common.core.constant.SecurityConstants;
import com.barbershop.common.core.domain.R;
import com.barbershop.common.core.utils.DateUtils;
import com.barbershop.system.api.RemoteUserService;
import com.barbershop.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barbershop.business.mapper.BizOrderMapper;
import com.barbershop.business.domain.BizOrder;
import com.barbershop.business.service.IBizOrderService;

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
    @Override
    public int insertBizOrder(BizOrder bizOrder)
    {
        bizOrder.setCreateTime(DateUtils.getNowDate());

        Integer orderPrice = bizOrder.getOrderPrice();

        BizCustomer bizCustomer = bizCustomerMapper.selectBizCustomerByCustomerId(bizOrder.getCustomerId());
        bizCustomer.setCustomerAccountBalance(bizCustomer.getCustomerAccountBalance() - orderPrice);
        bizCustomerMapper.updateBizCustomer(bizCustomer);

//        SysUser sysUser = sysUserMapper.selectUserById(bizOrder.getUserId());
//        sysUser.setCommission(sysUser.getCommission() + orderPrice);
//        sysUserMapper.updateUser(sysUser);

        return bizOrderMapper.insertBizOrder(bizOrder);
    }

    /**
     * 修改订单管理
     * 
     * @param bizOrder 订单管理
     * @return 结果
     */
    @Override
    public int updateBizOrder(BizOrder bizOrder)
    {
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

}
