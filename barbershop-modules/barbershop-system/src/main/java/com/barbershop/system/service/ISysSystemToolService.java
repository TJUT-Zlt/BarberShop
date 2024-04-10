package com.barbershop.system.service;

import java.util.List;
import com.barbershop.system.domain.SysSystemTool;

/**
 * 工具管理Service接口
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
public interface ISysSystemToolService 
{
    /**
     * 查询工具管理
     * 
     * @param toolId 工具管理主键
     * @return 工具管理
     */
    public SysSystemTool selectSysSystemToolByToolId(Long toolId);

    /**
     * 查询工具管理列表
     * 
     * @param sysSystemTool 工具管理
     * @return 工具管理集合
     */
    public List<SysSystemTool> selectSysSystemToolList(SysSystemTool sysSystemTool);

    /**
     * 新增工具管理
     * 
     * @param sysSystemTool 工具管理
     * @return 结果
     */
    public int insertSysSystemTool(SysSystemTool sysSystemTool);

    /**
     * 修改工具管理
     * 
     * @param sysSystemTool 工具管理
     * @return 结果
     */
    public int updateSysSystemTool(SysSystemTool sysSystemTool);

    /**
     * 批量删除工具管理
     * 
     * @param toolIds 需要删除的工具管理主键集合
     * @return 结果
     */
    public int deleteSysSystemToolByToolIds(Long[] toolIds);

    /**
     * 删除工具管理信息
     * 
     * @param toolId 工具管理主键
     * @return 结果
     */
    public int deleteSysSystemToolByToolId(Long toolId);
}
