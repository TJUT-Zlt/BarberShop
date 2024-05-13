package com.barbershop.business.service;

import java.time.LocalDate;
import java.util.List;
import com.barbershop.business.domain.BizOrder;
import com.barbershop.business.domain.vo.report.BizOrderReportVO;
import com.barbershop.business.domain.vo.report.TurnoverReportVO;

/**
 * 订单管理Service接口
 * 
 * @author ruoyi
 * @date 2024-04-11
 */
public interface IBizOrderService 
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
     * 新增没有指定客户的订单管理
     * @param bizOrder
     * @return
     */
    public int insertBizOrderWithNoBizCustomer(BizOrder bizOrder);

    /**
     * 修改订单管理
     * 
     * @param bizOrder 订单管理
     * @return 结果
     */
    public int updateBizOrder(BizOrder bizOrder);

    /**
     * 批量删除订单管理
     * 
     * @param orderIds 需要删除的订单管理主键集合
     * @return 结果
     */
    public int deleteBizOrderByOrderIds(Long[] orderIds);

    /**
     * 删除订单管理信息
     * 
     * @param orderId 订单管理主键
     * @return 结果
     */
    public int deleteBizOrderByOrderId(Long orderId);


    /**
     * 统计指定时间区间内的营业额数据
     * @param begin
     * @param end
     * @return
     */
    public TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end);

    /**
     * 统计指定时间区间内的订单数据
     * @param begin
     * @param end
     * @return
     */
    public BizOrderReportVO getOrderStatistics(LocalDate begin, LocalDate end);

}
