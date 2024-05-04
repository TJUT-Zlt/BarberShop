package Mapper;

import com.barbershop.system.RuoYiSystemApplication;
import com.barbershop.system.api.domain.SysUser;
import com.barbershop.system.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiSystemApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SysUserMapperTest {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void testSelectUserByUserName(){
        SysUser user = sysUserMapper.selectUserByUserName("admin");
        System.out.println(user);
    }

    @Test
    public void testSelectUserList(){
        SysUser sysUser = new SysUser();
        sysUser.setSex("1");
        List<SysUser> sysUserList = sysUserMapper.selectUserList(sysUser);
        sysUserList.forEach(System.out::println);
    }

    @Test
    public void testUpdateUser(){
        SysUser sysUser = new SysUser();
        sysUser.setUserId(1L);
        sysUser.setBirthday(new Date());
        System.out.println(sysUserMapper.updateUser(sysUser));
    }
    @Test
    public void testInsertUser(){
        SysUser sysUser = new SysUser();
        sysUser.setUserName("test07");
        sysUser.setPassword("123456");
        sysUser.setNickName("小天才");
        sysUser.setBirthday(new Date());
        System.out.println(sysUserMapper.insertUser(sysUser));
    }

    @Test
    public void testSelectUserAll(){
        List<SysUser> users = sysUserMapper.selectSysUserAll();
        users.forEach(System.out::println);

    }

    @Test
    public void testSelectSysUserByTodayStatus(){
        int i = sysUserMapper.selectSysUserByTodayStatus('1');
        System.out.println(i);

    }
}

