package com.barbershop.business.vo;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDataVO implements Serializable {


    private Double turnover;//营业额

    private Double unitPrice;//平均客单价

    private Integer orderNum;//订单数

    private Integer newBizCustomers;//新增客户数

}
