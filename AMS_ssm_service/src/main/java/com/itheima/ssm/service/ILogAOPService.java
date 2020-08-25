package com.itheima.ssm.service;

import com.itheima.ssm.domain.SysLog;

import java.util.List;

public interface ILogAOPService {
    public void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll() throws Exception;
}
