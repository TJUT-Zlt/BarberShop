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
public class SysUserOverViewVO implements Serializable {

    // 在岗员工数量
    private Integer obDutySysUsers;

    // 休假员工数量
    private Integer onLeaveSysUsers;

    //出差员工数量
    private Integer businessSysUsers;

    //离职员工数量
    private Integer departSysUsers;
}
