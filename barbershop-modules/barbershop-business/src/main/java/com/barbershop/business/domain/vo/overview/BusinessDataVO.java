package com.barbershop.business.domain.vo.overview;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 首页 今日数据统计
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDataVO implements Serializable {

    private BigDecimal totalTurnover; // 总营业额

    private BigDecimal turnover;//营业额

    private BigDecimal unitPrice;//平均客单价

    private Double orderNum;//订单数

    private Integer newBizCustomers;//新增客户数

}
