package com.barbershop.gen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.barbershop.common.security.annotation.EnableCustomConfig;
import com.barbershop.common.security.annotation.EnableRyFeignClients;
import com.barbershop.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 代码生成
 * 
 * @author abel
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class RuoYiGenApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiGenApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  代码生成模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
