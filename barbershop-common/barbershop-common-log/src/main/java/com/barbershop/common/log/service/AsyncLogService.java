package com.barbershop.common.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.barbershop.common.core.constant.SecurityConstants;
import com.barbershop.system.api.RemoteLogService;
import com.barbershop.system.api.domain.SysOperLog;

/**
 * 异步调用日志服务
 * 
 * @author abel
 */
@Service
public class AsyncLogService
{
    @Autowired
    private RemoteLogService remoteLogService;

    /**
     * 保存系统日志记录
     */
    @Async
    public void saveSysLog(SysOperLog sysOperLog) throws Exception
    {
        remoteLogService.saveLog(sysOperLog, SecurityConstants.INNER);
    }
}
