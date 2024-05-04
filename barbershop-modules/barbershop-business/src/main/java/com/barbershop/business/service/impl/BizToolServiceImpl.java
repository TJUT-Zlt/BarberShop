package com.barbershop.business.service.impl;

import java.util.List;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.barbershop.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barbershop.business.mapper.BizToolMapper;
import com.barbershop.business.domain.BizTool;
import com.barbershop.business.service.IBizToolService;

/**
 * 工具管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-11
 */
@Service
public class BizToolServiceImpl implements IBizToolService 
{
    @Autowired
    private BizToolMapper bizToolMapper;

    /**
     * 查询工具管理
     * 
     * @param toolId 工具管理主键
     * @return 工具管理
     */
    @Override
    public BizTool selectBizToolByToolId(Long toolId)
    {
        return bizToolMapper.selectBizToolByToolId(toolId);
    }

    /**
     * 查询工具管理列表
     * 
     * @param bizTool 工具管理
     * @return 工具管理
     */
    @Override
    public List<BizTool> selectBizToolList(BizTool bizTool)
    {
        return bizToolMapper.selectBizToolList(bizTool);
    }

    /**
     * 新增工具管理
     * 
     * @param bizTool 工具管理
     * @return 结果
     */
    @Override
    public int insertBizTool(BizTool bizTool)
    {
        bizTool.setCreateTime(DateUtils.getNowDate());
        return bizToolMapper.insertBizTool(bizTool);
    }

    /**
     * 修改工具管理
     * 
     * @param bizTool 工具管理
     * @return 结果
     */
    @Override
    public int updateBizTool(BizTool bizTool)
    {
        bizTool.setUpdateTime(DateUtils.getNowDate());
        return bizToolMapper.updateBizTool(bizTool);
    }

    /**
     * 批量删除工具管理
     * 
     * @param toolIds 需要删除的工具管理主键
     * @return 结果
     */
    @Override
    public int deleteBizToolByToolIds(Long[] toolIds)
    {
        return bizToolMapper.deleteBizToolByToolIds(toolIds);
    }

    /**
     * 删除工具管理信息
     * 
     * @param toolId 工具管理主键
     * @return 结果
     */
    @Override
    public int deleteBizToolByToolId(Long toolId)
    {
        return bizToolMapper.deleteBizToolByToolId(toolId);
    }


}
