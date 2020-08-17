package com.itheima.ssm.service;

import com.itheima.ssm.domain.Orders;

import java.util.List;

public interface IOrdersService {
    public List<Orders> findAll(int page, int size) throws Exception;
}
