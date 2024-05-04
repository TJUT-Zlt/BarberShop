package com.barbershop.business.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
