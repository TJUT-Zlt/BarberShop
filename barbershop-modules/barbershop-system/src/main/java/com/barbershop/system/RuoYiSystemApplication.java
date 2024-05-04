package com.barbershop.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.barbershop.common.security.annotation.EnableCustomConfig;
import com.barbershop.common.security.annotation.EnableRyFeignClients;
import com.barbershop.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.context.annotation.ComponentScan;

/**
 * 系统模块
 * 
 * @author abel
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class RuoYiSystemApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiSystemApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  系统模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
