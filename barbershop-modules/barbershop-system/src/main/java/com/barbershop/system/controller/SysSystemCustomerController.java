package com.barbershop.system.controller;

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
import com.barbershop.system.domain.SysSystemCustomer;
import com.barbershop.system.service.ISysSystemCustomerService;
import com.barbershop.common.core.web.controller.BaseController;
import com.barbershop.common.core.web.domain.AjaxResult;
import com.barbershop.common.core.utils.poi.ExcelUtil;
import com.barbershop.common.core.web.page.TableDataInfo;

/**
 * 客户管理Controller
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
@RestController
@RequestMapping("/SysCustomer")
public class SysSystemCustomerController extends BaseController
{
    @Autowired
    private ISysSystemCustomerService sysSystemCustomerService;

    /**
     * 查询客户管理列表
     */
    @RequiresPermissions("system:SysCustomer:list")
    @GetMapping("/list")
    public TableDataInfo list(SysSystemCustomer sysSystemCustomer)
    {
        startPage();
        List<SysSystemCustomer> list = sysSystemCustomerService.selectSysSystemCustomerList(sysSystemCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户管理列表
     */
    @RequiresPermissions("system:SysCustomer:export")
    @Log(title = "客户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysSystemCustomer sysSystemCustomer)
    {
        List<SysSystemCustomer> list = sysSystemCustomerService.selectSysSystemCustomerList(sysSystemCustomer);
        ExcelUtil<SysSystemCustomer> util = new ExcelUtil<SysSystemCustomer>(SysSystemCustomer.class);
        util.exportExcel(response, list, "客户管理数据");
    }

    /**
     * 获取客户管理详细信息
     */
    @RequiresPermissions("system:SysCustomer:query")
    @GetMapping(value = "/{customerId}")
    public AjaxResult getInfo(@PathVariable("customerId") Long customerId)
    {
        return success(sysSystemCustomerService.selectSysSystemCustomerByCustomerId(customerId));
    }

    /**
     * 新增客户管理
     */
    @RequiresPermissions("system:SysCustomer:add")
    @Log(title = "客户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysSystemCustomer sysSystemCustomer)
    {
        return toAjax(sysSystemCustomerService.insertSysSystemCustomer(sysSystemCustomer));
    }

    /**
     * 修改客户管理
     */
    @RequiresPermissions("system:SysCustomer:edit")
    @Log(title = "客户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysSystemCustomer sysSystemCustomer)
    {
        return toAjax(sysSystemCustomerService.updateSysSystemCustomer(sysSystemCustomer));
    }

    /**
     * 删除客户管理
     */
    @RequiresPermissions("system:SysCustomer:remove")
    @Log(title = "客户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{customerIds}")
    public AjaxResult remove(@PathVariable Long[] customerIds)
    {
        return toAjax(sysSystemCustomerService.deleteSysSystemCustomerByCustomerIds(customerIds));
    }
}
