package com.barbershop.business.domain;

import com.barbershop.common.core.annotation.Excels;
import com.barbershop.system.api.domain.SysDept;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.barbershop.common.core.annotation.Excel;
import com.barbershop.common.core.web.domain.BaseEntity;

/**
 * 工具管理对象 biz_tool
 * 
 * @author ruoyi
 * @date 2024-04-11
 */
public class BizTool extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工具ID */
    @Excel(name = "工具ID")
    private Long toolId;

    /** 工具所属部门ID */
    @Excel(name = "工具所属部门ID")
    private Long deptId;

    /** 工具名称 */
    @Excel(name = "工具名称")
    private String toolName;

    /** 工具价格 */
    @Excel(name = "工具价格")
    private Integer price;

    /** 工具数量 */
    @Excel(name = "工具数量")
    private Integer number;

    /** 工具状态(0正常,1维修,2报废) */
    @Excel(name = "工具状态", readConverterExp = "0=正常,1=维修,2=报废")
    private String toolStatus;

    @Excels({
            @Excel(name = "部门名称", targetAttr = "deptName", type = Excel.Type.EXPORT),
            @Excel(name = "部门负责人", targetAttr = "leader", type = Excel.Type.EXPORT)
    })
    private SysDept dept;

    public void setToolId(Long toolId) 
    {
        this.toolId = toolId;
    }

    public Long getToolId() 
    {
        return toolId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setToolName(String toolName) 
    {
        this.toolName = toolName;
    }

    public String getToolName() 
    {
        return toolName;
    }
    public void setPrice(Integer price) 
    {
        this.price = price;
    }

    public Integer getPrice() 
    {
        return price;
    }
    public void setNumber(Integer number) 
    {
        this.number = number;
    }

    public Integer getNumber() 
    {
        return number;
    }
    public void setToolStatus(String toolStatus) 
    {
        this.toolStatus = toolStatus;
    }

    public String getToolStatus() 
    {
        return toolStatus;
    }

    public SysDept getDept()
    {
        return dept;
    }

    public void setDept(SysDept dept)
    {
        this.dept = dept;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("toolId", getToolId())
            .append("deptId", getDeptId())
            .append("toolName", getToolName())
            .append("price", getPrice())
            .append("number", getNumber())
            .append("toolStatus", getToolStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("dept", getDept())
            .toString();
    }
}
