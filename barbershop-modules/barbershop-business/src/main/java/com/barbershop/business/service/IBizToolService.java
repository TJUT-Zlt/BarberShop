package com.barbershop.business.service;

import java.util.List;
import com.barbershop.business.domain.BizTool;

/**
 * 工具管理Service接口
 * 
 * @author ruoyi
 * @date 2024-04-11
 */
public interface IBizToolService 
{
    /**
     * 查询工具管理
     * 
     * @param toolId 工具管理主键
     * @return 工具管理
     */
    public BizTool selectBizToolByToolId(Long toolId);

    /**
     * 查询工具管理列表
     * 
     * @param bizTool 工具管理
     * @return 工具管理集合
     */
    public List<BizTool> selectBizToolList(BizTool bizTool);

    /**
     * 新增工具管理
     * 
     * @param bizTool 工具管理
     * @return 结果
     */
    public int insertBizTool(BizTool bizTool);

    /**
     * 修改工具管理
     * 
     * @param bizTool 工具管理
     * @return 结果
     */
    public int updateBizTool(BizTool bizTool);

    /**
     * 批量删除工具管理
     * 
     * @param toolIds 需要删除的工具管理主键集合
     * @return 结果
     */
    public int deleteBizToolByToolIds(Long[] toolIds);

    /**
     * 删除工具管理信息
     * 
     * @param toolId 工具管理主键
     * @return 结果
     */
    public int deleteBizToolByToolId(Long toolId);
}
