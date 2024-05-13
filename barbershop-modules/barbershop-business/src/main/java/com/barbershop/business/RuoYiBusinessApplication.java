package com.barbershop.business;

import com.barbershop.common.security.annotation.EnableCustomConfig;
import com.barbershop.common.security.annotation.EnableRyFeignClients;
import com.barbershop.common.swagger.annotation.EnableCustomSwagger2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 自定义业务模块
 * 
 * @author abel
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
@EnableTransactionManagement
public class RuoYiBusinessApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiBusinessApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  自定义业务模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
