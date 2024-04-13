package com.barbershop.business.mapper;

import java.util.List;
import com.barbershop.business.domain.BizOrder;

/**
 * 订单管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-11
 */
public interface BizOrderMapper 
{
    /**
     * 查询订单管理
     * 
     * @param orderId 订单管理主键
     * @return 订单管理
     */
    public BizOrder selectBizOrderByOrderId(Long orderId);

    /**
     * 查询订单管理列表
     * 
     * @param bizOrder 订单管理
     * @return 订单管理集合
     */
    public List<BizOrder> selectBizOrderList(BizOrder bizOrder);

    /**
     * 新增订单管理
     * 
     * @param bizOrder 订单管理
     * @return 结果
     */
    public int insertBizOrder(BizOrder bizOrder);

    /**
     * 修改订单管理
     * 
     * @param bizOrder 订单管理
     * @return 结果
     */
    public int updateBizOrder(BizOrder bizOrder);

    /**
     * 删除订单管理
     * 
     * @param orderId 订单管理主键
     * @return 结果
     */
    public int deleteBizOrderByOrderId(Long orderId);

    /**
     * 批量删除订单管理
     * 
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizOrderByOrderIds(Long[] orderIds);
}
