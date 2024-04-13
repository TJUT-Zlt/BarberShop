package com.barbershop.business.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
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
import com.barbershop.business.domain.BizTool;
import com.barbershop.business.service.IBizToolService;
import com.barbershop.common.core.web.controller.BaseController;
import com.barbershop.common.core.web.domain.AjaxResult;
import com.barbershop.common.core.utils.poi.ExcelUtil;
import com.barbershop.common.core.web.page.TableDataInfo;

/**
 * 工具管理Controller
 * 
 * @author ruoyi
 * @date 2024-04-11
 */
@RestController
@RequestMapping("/BizTool")
public class BizToolController extends BaseController
{
    @Autowired
    private IBizToolService bizToolService;

    /**
     * 查询工具管理列表
     */
    @RequiresPermissions("business:BizTool:list")
    @GetMapping("/list")
    public TableDataInfo list(BizTool bizTool)
    {
        startPage();
        List<BizTool> list = bizToolService.selectBizToolList(bizTool);
        return getDataTable(list);
    }

    /**
     * 导出工具管理列表
     */
    @RequiresPermissions("business:BizTool:export")
    @Log(title = "工具管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizTool bizTool)
    {
        List<BizTool> list = bizToolService.selectBizToolList(bizTool);
        ExcelUtil<BizTool> util = new ExcelUtil<BizTool>(BizTool.class);
        util.exportExcel(response, list, "工具管理数据");
    }

    /**
     * 获取工具管理详细信息
     */
    @RequiresPermissions("business:BizTool:query")
    @GetMapping(value = "/{toolId}")
    public AjaxResult getInfo(@PathVariable("toolId") Long toolId)
    {
        return success(bizToolService.selectBizToolByToolId(toolId));
    }

    /**
     * 新增工具管理
     */
    @RequiresPermissions("business:BizTool:add")
    @Log(title = "工具管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizTool bizTool)
    {
        return toAjax(bizToolService.insertBizTool(bizTool));
    }

    /**
     * 修改工具管理
     */
    @RequiresPermissions("business:BizTool:edit")
    @Log(title = "工具管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizTool bizTool)
    {
        return toAjax(bizToolService.updateBizTool(bizTool));
    }

    /**
     * 删除工具管理
     */
    @RequiresPermissions("business:BizTool:remove")
    @Log(title = "工具管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{toolIds}")
    public AjaxResult remove(@PathVariable Long[] toolIds)
    {
        return toAjax(bizToolService.deleteBizToolByToolIds(toolIds));
    }
}
