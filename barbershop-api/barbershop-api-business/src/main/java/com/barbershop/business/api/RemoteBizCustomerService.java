package com.barbershop.business.api;

import com.barbershop.business.api.factory.RemoteBizCustomerFallbackFactory;
import com.barbershop.common.core.constant.ServiceNameConstants;
import com.barbershop.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "remoteBizCustomerService", value = ServiceNameConstants.BUSINESS_SERVICE, fallbackFactory = RemoteBizCustomerFallbackFactory.class)
public interface RemoteBizCustomerService {

    /**
     * 通过客户Id查询客户信息
     * @return 结果
     */
    @GetMapping("/bizCustomer/get/{id}")
    public R<String> getBizCustomer(@PathVariable("id") Long id);

}
