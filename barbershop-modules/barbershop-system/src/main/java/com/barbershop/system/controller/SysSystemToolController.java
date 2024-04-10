package com.barbershop.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.barbershop.system.api.domain.SysDept;
import com.barbershop.system.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.barbershop.common.log.annotation.Log;
import com.barbershop.common.log.enums.BusinessType;
import com.barbershop.common.security.annotation.RequiresPermissions;
import com.barbershop.system.domain.SysSystemTool;
import com.barbershop.system.service.ISysSystemToolService;
import com.barbershop.common.core.web.controller.BaseController;
import com.barbershop.common.core.web.domain.AjaxResult;
import com.barbershop.common.core.utils.poi.ExcelUtil;
import com.barbershop.common.core.web.page.TableDataInfo;

/**
 * 工具管理Controller
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
@RestController
@RequestMapping("/SysTool")
public class SysSystemToolController extends BaseController
{
    @Autowired
    private ISysSystemToolService sysSystemToolService;

    @Autowired
    private ISysDeptService deptService;

    /**
     * 查询工具管理列表
     */
    @RequiresPermissions("system:SysTool:list")
    @GetMapping("/list")
    public TableDataInfo list(SysSystemTool sysSystemTool)
    {
        startPage();
        List<SysSystemTool> list = sysSystemToolService.selectSysSystemToolList(sysSystemTool);
        return getDataTable(list);
    }

    /**
     * 导出工具管理列表
     */
    @RequiresPermissions("system:SysTool:export")
    @Log(title = "工具管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysSystemTool sysSystemTool)
    {
        List<SysSystemTool> list = sysSystemToolService.selectSysSystemToolList(sysSystemTool);
        ExcelUtil<SysSystemTool> util = new ExcelUtil<SysSystemTool>(SysSystemTool.class);
        util.exportExcel(response, list, "工具管理数据");
    }

    /**
     * 获取工具管理详细信息
     */
    @RequiresPermissions("system:SysTool:query")
    @GetMapping(value = "/{toolId}")
    public AjaxResult getInfo(@PathVariable("toolId") Long toolId)
    {
        return success(sysSystemToolService.selectSysSystemToolByToolId(toolId));
    }

    /**
     * 新增工具管理
     */
    @RequiresPermissions("system:SysTool:add")
    @Log(title = "工具管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysSystemTool sysSystemTool)
    {
        return toAjax(sysSystemToolService.insertSysSystemTool(sysSystemTool));
    }

    /**
     * 修改工具管理
     */
    @RequiresPermissions("system:SysTool:edit")
    @Log(title = "工具管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysSystemTool sysSystemTool)
    {
        return toAjax(sysSystemToolService.updateSysSystemTool(sysSystemTool));
    }

    /**
     * 删除工具管理
     */
    @RequiresPermissions("system:SysTool:remove")
    @Log(title = "工具管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{toolIds}")
    public AjaxResult remove(@PathVariable Long[] toolIds)
    {
        return toAjax(sysSystemToolService.deleteSysSystemToolByToolIds(toolIds));
    }

    /**
     * 获取部门树列表
     */
    @RequiresPermissions("system:SysTool:list")
    @GetMapping("/deptTree")
    public AjaxResult deptTree(SysDept dept)
    {
        return success(deptService.selectDeptTreeList(dept));
    }
}
