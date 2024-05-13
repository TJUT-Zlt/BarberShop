package com.barbershop.business.domain;

import com.barbershop.system.api.domain.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.barbershop.common.core.annotation.Excel;
import com.barbershop.common.core.web.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 订单管理对象 biz_order
 * 
 * @author ruoyi
 * @date 2024-04-11
 */
public class BizOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 服务订单ID */
    private Long orderId;

    /** 服务人员ID */
    @Excel(name = "服务人员ID")
    private Long userId;

    /** 客户ID */
    @Excel(name = "客户ID")
    private Long customerId;

    /** 服务类型 */
    @Excel(name = "服务类型")
    private String orderType;

    /** 服务价格 */
    @Excel(name = "服务价格")
    private BigDecimal orderPrice;

    private SysUser sysUser;

    private BizCustomer bizCustomer;


    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setOrderType(String orderType) 
    {
        this.orderType = orderType;
    }

    public String getOrderType() 
    {
        return orderType;
    }
    public void setOrderPrice(BigDecimal orderPrice)
    {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getOrderPrice()
    {
        return orderPrice;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public BizCustomer getBizCustomer() {
        return bizCustomer;
    }

    public void setBizCustomer(BizCustomer bizCustomer) {
        this.bizCustomer = bizCustomer;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("userId", getUserId())
            .append("customerId", getCustomerId())
            .append("orderType", getOrderType())
            .append("orderPrice", getOrderPrice())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("sysUser",getSysUser())
            .append("bizCustomer",getBizCustomer())
            .toString();
    }
}
