package com.barbershop.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.barbershop.common.core.annotation.Excel;
import com.barbershop.common.core.web.domain.BaseEntity;

/**
 * 订单管理对象 sys_system_order
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
public class SysSystemOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 服务订单ID */
    private Long orderId;

    /** 所属部门ID */
    @Excel(name = "所属部门ID")
    private Long deptId;

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
    private Integer orderPrice;

    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
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
    public void setOrderPrice(Integer orderPrice) 
    {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderPrice() 
    {
        return orderPrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("deptId", getDeptId())
            .append("userId", getUserId())
            .append("customerId", getCustomerId())
            .append("orderType", getOrderType())
            .append("orderPrice", getOrderPrice())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
