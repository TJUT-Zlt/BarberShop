package com.barbershop.business.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.barbershop.business.service.IBizCustomerService;
import com.barbershop.common.core.utils.StringUtils;
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
import com.barbershop.business.domain.BizOrder;
import com.barbershop.business.service.IBizOrderService;
import com.barbershop.common.core.web.controller.BaseController;
import com.barbershop.common.core.web.domain.AjaxResult;
import com.barbershop.common.core.utils.poi.ExcelUtil;
import com.barbershop.common.core.web.page.TableDataInfo;

/**
 * 订单管理Controller
 *
 * @author ruoyi
 * @date 2024-04-11
 */
@RestController
@RequestMapping("/BizOrder")
public class BizOrderController extends BaseController
{
    @Autowired
    private IBizOrderService bizOrderService;
    @Autowired
    private IBizCustomerService bizCustomerService;

    /**
     * 查询订单管理列表
     */
    @RequiresPermissions("business:BizOrder:list")
    @GetMapping("/list")
    public TableDataInfo list(BizOrder bizOrder)
    {
        startPage();
        List<BizOrder> list = bizOrderService.selectBizOrderList(bizOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单管理列表
     */
    @RequiresPermissions("business:BizOrder:export")
    @Log(title = "订单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizOrder bizOrder)
    {
        List<BizOrder> list = bizOrderService.selectBizOrderList(bizOrder);
        ExcelUtil<BizOrder> util = new ExcelUtil<BizOrder>(BizOrder.class);
        util.exportExcel(response, list, "订单管理数据");
    }

    /**
     * 获取订单管理详细信息
     */
    @RequiresPermissions("business:BizOrder:query")
    @GetMapping(value = {"/", "/{orderId}"})
    public AjaxResult getInfo(@PathVariable(value = "orderId" , required = false) Long orderId)
    {
        AjaxResult ajax = AjaxResult.success();
        //ajax.put("bizCustomers",bizCustomerService.selectBizCustomerAll());
        if (StringUtils.isNotNull(orderId)){
            BizOrder bizOrder = bizOrderService.selectBizOrderByOrderId(orderId);
            ajax.put(AjaxResult.DATA_TAG,bizOrder);
        }
        return ajax;
    }

    /**
     * 新增订单管理
     */
    @RequiresPermissions("business:BizOrder:add")
    @Log(title = "订单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizOrder bizOrder)
    {
        return toAjax(bizOrderService.insertBizOrder(bizOrder));
    }

    /**
     * 修改订单管理
     */
    @RequiresPermissions("business:BizOrder:edit")
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizOrder bizOrder)
    {
        return toAjax(bizOrderService.updateBizOrder(bizOrder));
    }

    /**
     * 删除订单管理
     */
    @RequiresPermissions("business:BizOrder:remove")
    @Log(title = "订单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(bizOrderService.deleteBizOrderByOrderIds(orderIds));
    }

    /**
     * 查询客户列表
     * @return
     */
    @RequiresPermissions("business:BizOrder:list")
    @GetMapping("/bizCustomerList")
    public AjaxResult bizCustomerList()
    {
        return success(bizCustomerService.selectBizCustomerAll());
    }

}
