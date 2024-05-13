package com.barbershop.business.domain.vo.overview;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 首页 工具统计
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BizToolOverViewVO {

    // 正常工具数量
    private Integer normalBizTools;

    // 维修工具数量
    private Integer repairBizTools;

    // 报废工具数量
    private Integer scrapBizTools;

}
