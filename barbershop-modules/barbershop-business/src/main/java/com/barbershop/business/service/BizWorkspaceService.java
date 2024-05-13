package com.barbershop.business.service;

import com.barbershop.business.domain.vo.overview.BizCustomerOverViewVO;
import com.barbershop.business.domain.vo.overview.BizToolOverViewVO;
import com.barbershop.business.domain.vo.overview.BusinessDataVO;
import com.barbershop.business.domain.vo.overview.SysUserOverViewVO;

import java.time.LocalDateTime;

public interface BizWorkspaceService {

    /**
     * 根据时间段统计营业数据
     * @param begin
     * @param end
     * @return
     */
    BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end);

    /**
     * 查询客户总览
     * @return
     */

    BizCustomerOverViewVO getBizCustomerOverView();

    /**
     * 查询员工总览
     * @return
     */
    SysUserOverViewVO getSysUserOverView();

    BizToolOverViewVO getBizToolOverView();
}
