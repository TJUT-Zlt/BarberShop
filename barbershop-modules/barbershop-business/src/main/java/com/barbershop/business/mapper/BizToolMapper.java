package com.barbershop.business.mapper;

import java.util.List;
import java.util.Map;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.barbershop.business.domain.BizTool;

/**
 * 工具管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-11
 */
public interface BizToolMapper 
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
     * 删除工具管理
     * 
     * @param toolId 工具管理主键
     * @return 结果
     */
    public int deleteBizToolByToolId(Long toolId);

    /**
     * 批量删除工具管理
     * 
     * @param toolIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizToolByToolIds(Long[] toolIds);



    /**
     * 根据动态条件统计工具数量
     * @param map
     * @return
     */
    public Integer countByMap(Map map);
}
