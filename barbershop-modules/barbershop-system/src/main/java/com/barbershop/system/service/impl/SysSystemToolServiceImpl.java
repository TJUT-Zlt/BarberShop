package com.barbershop.system.service.impl;

import java.util.List;
import com.barbershop.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barbershop.system.mapper.SysSystemToolMapper;
import com.barbershop.system.domain.SysSystemTool;
import com.barbershop.system.service.ISysSystemToolService;

/**
 * 工具管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
@Service
public class SysSystemToolServiceImpl implements ISysSystemToolService 
{
    @Autowired
    private SysSystemToolMapper sysSystemToolMapper;

    /**
     * 查询工具管理
     * 
     * @param toolId 工具管理主键
     * @return 工具管理
     */
    @Override
    public SysSystemTool selectSysSystemToolByToolId(Long toolId)
    {
        return sysSystemToolMapper.selectSysSystemToolByToolId(toolId);
    }

    /**
     * 查询工具管理列表
     * 
     * @param sysSystemTool 工具管理
     * @return 工具管理
     */
    @Override
    public List<SysSystemTool> selectSysSystemToolList(SysSystemTool sysSystemTool)
    {
        return sysSystemToolMapper.selectSysSystemToolList(sysSystemTool);
    }

    /**
     * 新增工具管理
     * 
     * @param sysSystemTool 工具管理
     * @return 结果
     */
    @Override
    public int insertSysSystemTool(SysSystemTool sysSystemTool)
    {
        sysSystemTool.setCreateTime(DateUtils.getNowDate());
        return sysSystemToolMapper.insertSysSystemTool(sysSystemTool);
    }

    /**
     * 修改工具管理
     * 
     * @param sysSystemTool 工具管理
     * @return 结果
     */
    @Override
    public int updateSysSystemTool(SysSystemTool sysSystemTool)
    {
        sysSystemTool.setUpdateTime(DateUtils.getNowDate());
        return sysSystemToolMapper.updateSysSystemTool(sysSystemTool);
    }

    /**
     * 批量删除工具管理
     * 
     * @param toolIds 需要删除的工具管理主键
     * @return 结果
     */
    @Override
    public int deleteSysSystemToolByToolIds(Long[] toolIds)
    {
        return sysSystemToolMapper.deleteSysSystemToolByToolIds(toolIds);
    }

    /**
     * 删除工具管理信息
     * 
     * @param toolId 工具管理主键
     * @return 结果
     */
    @Override
    public int deleteSysSystemToolByToolId(Long toolId)
    {
        return sysSystemToolMapper.deleteSysSystemToolByToolId(toolId);
    }
}
