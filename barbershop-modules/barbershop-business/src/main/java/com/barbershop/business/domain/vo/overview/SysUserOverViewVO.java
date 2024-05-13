package com.barbershop.business.domain.vo.overview;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 首页 用户统计
 */
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
