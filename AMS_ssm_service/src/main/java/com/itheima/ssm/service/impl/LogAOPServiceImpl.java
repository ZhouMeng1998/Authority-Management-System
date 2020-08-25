package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.ILogAOPDao;
import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ILogAOPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class LogAOPServiceImpl implements ILogAOPService {

    @Autowired
    private ILogAOPDao dao;
    @Override
    public void save(SysLog sysLog) throws Exception {
        dao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() throws Exception {
        return dao.findAll();
    }
}
