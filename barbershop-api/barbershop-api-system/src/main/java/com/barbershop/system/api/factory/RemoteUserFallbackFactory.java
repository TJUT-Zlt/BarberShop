package com.barbershop.system.api.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import com.barbershop.common.core.domain.R;
import com.barbershop.system.api.RemoteUserService;
import com.barbershop.system.api.domain.SysUser;
import com.barbershop.system.api.model.LoginUser;

import java.util.List;

/**
 * 用户服务降级处理
 * 
 * @author abel
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

    @Override
    public RemoteUserService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService()
        {
            @Override
            public R<LoginUser> getUserInfo(String username, String source)
            {
                return R.fail("获取用户失败:" + throwable.getMessage());
            }

            @Override
            public R<Boolean> registerUserInfo(SysUser sysUser, String source)
            {
                return R.fail("注册用户失败:" + throwable.getMessage());
            }

            @Override
            public R<List<SysUser>> selectSysUserAll() {
                return R.fail("查询用户失败" + throwable.getMessage());
            }

            @Override
            public R<SysUser> selectSysUserById(Long userId) {
                return R.fail("根据用户ID查询用户失败" + throwable.getMessage());
            }

            @Override
            public R<Integer> updateSysUser(SysUser sysUser) {
                return R.fail("更新用户失败" + throwable.getMessage());
            }

            @Override
            public R<Integer> selectSysUserByTodayStatus(char todayStatus) {
                return R.fail("根据状态查询用户失败" + throwable.getMessage());
            }


        };
    }
}
