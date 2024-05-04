package com.barbershop.business.controller;
import com.barbershop.business.service.BizWorkspaceService;
import com.barbershop.business.vo.BusinessDataVO;
import com.barbershop.common.core.web.domain.AjaxResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.barbershop.common.core.web.domain.AjaxResult.success;

/**
 * 工作台
 */
@RestController
@RequestMapping("/BizWorkSpace")
public class BizWorkSpaceController {

    @Autowired
    private BizWorkspaceService bizWorkspaceService;

    /**
     * 工作台今日数据查询
     * @return
     */
    @GetMapping("/businessData")
    public AjaxResult businessData(){
        //获得当天的开始时间
        LocalDateTime begin = LocalDateTime.now().with(LocalTime.MIN);
        //获得当天的结束时间
        LocalDateTime end = LocalDateTime.now().with(LocalTime.MAX);

        BusinessDataVO businessDataVO = bizWorkspaceService.getBusinessData(begin, end);
        return success(businessDataVO);
    }

    /**
     * 查询客户总览
     * @return
     */
    @GetMapping("/overviewBizCustomer")
    public AjaxResult bizCustomerOverView(){
        return success(bizWorkspaceService.getBizCustomerOverView());
    }

    /**
     * 查询用户总览
     * @return
     */
    @GetMapping("/overviewSysUser")
    public AjaxResult sysUserOverView(){
        return success(bizWorkspaceService.getSysUserOverView());
    }



}
