package com.barbershop.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.barbershop.common.core.annotation.Excel;
import com.barbershop.common.core.web.domain.BaseEntity;

/**
 * 客户管理对象 sys_system_customer
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
public class SysSystemCustomer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 顾客ID */
    private Long customerId;

    /** 所属分店ID */
    @Excel(name = "所属分店ID")
    private Long customerDeptId;

    /** 顾客账号 */
    @Excel(name = "顾客账号")
    private String customerName;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickName;

    /** 性别 */
    @Excel(name = "性别",readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 年龄 */
    @Excel(name = "年龄")
    private Integer age;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 账号余额 */
    @Excel(name = "账号余额")
    private Integer customerAccountBalance;

    /** 偏好 */
    @Excel(name = "偏好")
    private String perferences;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phonenumber;

    /** 账号状态(1正常,0停用) */
    @Excel(name = "账号状态",readConverterExp = "0=正常,1=停用")
    private String customerAccountStatus;

    /** 顾客等级(1普通用户,2vip用户) */
    @Excel(name = "顾客等级",readConverterExp = "1=普通用户,2=vip用户")
    private String customerAccountLevel;

    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setCustomerDeptId(Long customerDeptId) 
    {
        this.customerDeptId = customerDeptId;
    }

    public Long getCustomerDeptId() 
    {
        return customerDeptId;
    }
    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setAge(Integer age)
    {
        this.age = age;
    }

    public Integer getAge()
    {
        return age;
    }
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }
    public void setCustomerAccountBalance(Integer customerAccountBalance)
    {
        this.customerAccountBalance = customerAccountBalance;
    }

    public Integer getCustomerAccountBalance()
    {
        return customerAccountBalance;
    }
    public void setPerferences(String perferences) 
    {
        this.perferences = perferences;
    }

    public String getPerferences() 
    {
        return perferences;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setPhonenumber(String phonenumber) 
    {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber() 
    {
        return phonenumber;
    }
    public void setCustomerAccountStatus(String customerAccountStatus) 
    {
        this.customerAccountStatus = customerAccountStatus;
    }

    public String getCustomerAccountStatus() 
    {
        return customerAccountStatus;
    }
    public void setCustomerAccountLevel(String customerAccountLevel) 
    {
        this.customerAccountLevel = customerAccountLevel;
    }

    public String getCustomerAccountLevel() 
    {
        return customerAccountLevel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("customerId", getCustomerId())
            .append("customerDeptId", getCustomerDeptId())
            .append("customerName", getCustomerName())
            .append("nickName", getNickName())
            .append("sex", getSex())
            .append("age", getAge())
            .append("birthday", getBirthday())
            .append("customerAccountBalance", getCustomerAccountBalance())
            .append("perferences", getPerferences())
            .append("email", getEmail())
            .append("phonenumber", getPhonenumber())
            .append("customerAccountStatus", getCustomerAccountStatus())
            .append("customerAccountLevel", getCustomerAccountLevel())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
