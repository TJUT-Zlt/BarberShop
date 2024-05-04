package com.barbershop.system.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.barbershop.common.core.constant.SecurityConstants;
import com.barbershop.common.core.constant.ServiceNameConstants;
import com.barbershop.common.core.domain.R;
import com.barbershop.system.api.domain.SysUser;
import com.barbershop.system.api.factory.RemoteUserFallbackFactory;
import com.barbershop.system.api.model.LoginUser;

import java.util.List;

/**
 * 用户服务
 * 
 * @author abel
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping("/user/info/{username}")
    public R<LoginUser> getUserInfo(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 注册用户信息
     *
     * @param sysUser 用户信息
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/user/register")
    public R<Boolean> registerUserInfo(@RequestBody SysUser sysUser, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 查询所有用户
     * @return
     */
    @GetMapping("/user/selectSysUserAll")
    public  R<List<SysUser>> selectSysUserAll();

    /**
     * 根据用户ID查询用户
     * @param userId
     * @return
     */
    @GetMapping("/user/selectSysUserById/{userId}")
    public  R<SysUser> selectSysUserById(@PathVariable("userId") Long userId);

    /**
     * 修改用户信息
     * @param sysUser
     * @return
     */
    @PutMapping("/user/updateSysUser")
    public  R<Integer> updateSysUser(@RequestBody SysUser sysUser);


    /**
     * 根据状态查询用户
     * @param todayStatus
     * @return
     */
    @GetMapping("/user/selectSysUserByTodayStatus/{todayStatus}")
    public  R<Integer> selectSysUserByTodayStatus(@PathVariable("todayStatus") char todayStatus);

}
