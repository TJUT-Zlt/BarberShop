package com.barbershop.business.service;

import java.time.LocalDate;
import java.util.List;
import com.barbershop.business.domain.BizCustomer;
import com.barbershop.business.vo.BizCustomerReportVO;

/**
 * 客户管理Service接口
 * 
 * @author ruoyi
 * @date 2024-04-11
 */
public interface IBizCustomerService 
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
     * 批量删除客户管理
     * 
     * @param customerIds 需要删除的客户管理主键集合
     * @return 结果
     */
    public int deleteBizCustomerByCustomerIds(Long[] customerIds);

    /**
     * 删除客户管理信息
     * 
     * @param customerId 客户管理主键
     * @return 结果
     */
    public int deleteBizCustomerByCustomerId(Long customerId);

    /**
     * 查询所有客户
     * @return
     */
    public List<BizCustomer> selectBizCustomerAll();

    /**
     * 统计指定时间区间内的订单数据
     * @param begin
     * @param end
     * @return
     */
    public BizCustomerReportVO getBizCustomerStatistics(LocalDate begin, LocalDate end);
}
