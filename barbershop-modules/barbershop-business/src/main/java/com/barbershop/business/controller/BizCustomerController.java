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
import com.barbershop.business.domain.BizCustomer;
import com.barbershop.business.service.IBizCustomerService;
import com.barbershop.common.core.web.controller.BaseController;
import com.barbershop.common.core.web.domain.AjaxResult;
import com.barbershop.common.core.utils.poi.ExcelUtil;
import com.barbershop.common.core.web.page.TableDataInfo;

/**
 * 客户管理Controller
 * 
 * @author ruoyi
 * @date 2024-04-11
 */
@RestController
@RequestMapping("/BizCustomer")
public class BizCustomerController extends BaseController
{
    @Autowired
    private IBizCustomerService bizCustomerService;

    /**
     * 查询客户管理列表
     */
    @RequiresPermissions("business:BizCustomer:list")
    @GetMapping("/list")
    public TableDataInfo list(BizCustomer bizCustomer)
    {
        startPage();
        List<BizCustomer> list = bizCustomerService.selectBizCustomerList(bizCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户管理列表
     */
    @RequiresPermissions("business:BizCustomer:export")
    @Log(title = "客户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizCustomer bizCustomer)
    {
        List<BizCustomer> list = bizCustomerService.selectBizCustomerList(bizCustomer);
        ExcelUtil<BizCustomer> util = new ExcelUtil<BizCustomer>(BizCustomer.class);
        util.exportExcel(response, list, "客户管理数据");
    }

    /**
     * 获取客户管理详细信息
     */
    @RequiresPermissions("business:BizCustomer:query")
    @GetMapping(value = "/{customerId}")
    public AjaxResult getInfo(@PathVariable("customerId") Long customerId)
    {
        return success(bizCustomerService.selectBizCustomerByCustomerId(customerId));
    }

    /**
     * 新增客户管理
     */
    @RequiresPermissions("business:BizCustomer:add")
    @Log(title = "客户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizCustomer bizCustomer)
    {
        return toAjax(bizCustomerService.insertBizCustomer(bizCustomer));
    }

    /**
     * 修改客户管理
     */
    @RequiresPermissions("business:BizCustomer:edit")
    @Log(title = "客户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizCustomer bizCustomer)
    {
        return toAjax(bizCustomerService.updateBizCustomer(bizCustomer));
    }

    /**
     * 删除客户管理
     */
    @RequiresPermissions("business:BizCustomer:remove")
    @Log(title = "客户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{customerIds}")
    public AjaxResult remove(@PathVariable Long[] customerIds)
    {
        return toAjax(bizCustomerService.deleteBizCustomerByCustomerIds(customerIds));
    }
}
