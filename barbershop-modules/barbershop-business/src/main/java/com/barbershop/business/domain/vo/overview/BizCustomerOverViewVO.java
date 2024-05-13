package com.barbershop.business.domain.vo.overview;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 首页 客户统计
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BizCustomerOverViewVO implements Serializable {

    // vip客户数量
    private Integer vipBizCustomers;

    // 普通客户数量
    private Integer commonBizCustomers;
}
