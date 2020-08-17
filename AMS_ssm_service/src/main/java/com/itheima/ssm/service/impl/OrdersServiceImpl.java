package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IOrdersDao;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao dao;

    @Override
    public List<Orders> findAll(int page, int size) throws Exception{
        //配置分页查询,PageNum页码值，PageSize每页显示条数
        PageHelper.startPage(page, size);
        return dao.findAll();
    }
}
