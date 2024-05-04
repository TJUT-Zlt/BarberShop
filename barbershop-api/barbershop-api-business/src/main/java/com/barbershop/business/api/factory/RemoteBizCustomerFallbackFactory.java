package com.barbershop.business.api.factory;

import com.barbershop.business.api.RemoteBizCustomerService;
import com.barbershop.common.core.domain.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;


/**
 * 用户服务降级处理
 *
 * @author ruoyi
 */
@Component
public class RemoteBizCustomerFallbackFactory implements FallbackFactory<RemoteBizCustomerService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteBizCustomerFallbackFactory.class);

    @Override
    public RemoteBizCustomerService create(Throwable throwable)
    {
        log.error("客户服务调用失败:{}", throwable.getMessage());
        return new RemoteBizCustomerService()
        {
            @Override
            public R<String> getBizCustomer(Long id) {
                return R.fail("获取客户失败:" + throwable.getMessage());
            }
        };
    }
}
