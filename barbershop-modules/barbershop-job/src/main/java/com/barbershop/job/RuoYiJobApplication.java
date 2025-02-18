package com.barbershop.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.barbershop.common.security.annotation.EnableCustomConfig;
import com.barbershop.common.security.annotation.EnableRyFeignClients;
import com.barbershop.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 定时任务
 * 
 * @author abel
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients   
@SpringBootApplication
public class RuoYiJobApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiJobApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  定时任务模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
