package com.barbershop.business.domain.vo.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 订单数据统计图表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BizOrderReportVO implements Serializable {

    //日期，以逗号分隔，例如：2022-10-01,2022-10-02,2022-10-03
    private String dateList;

    //每日订单数，以逗号分隔，例如：260,210,215
    private String orderCountList;

    //订单总数
    private Double totalOrderCount;

}
