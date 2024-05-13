package com.barbershop.business.domain.vo.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 客户数据统计图表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BizCustomerReportVO implements Serializable {

    //日期，以逗号分隔，例如：2022-10-01,2022-10-02,2022-10-03
    private String dateList;

    //用户总量，以逗号分隔，例如：200,210,220
    private String totalBizCustomerList;

    //新增用户，以逗号分隔，例如：20,21,10
    private String newBizCustomerList;

}
