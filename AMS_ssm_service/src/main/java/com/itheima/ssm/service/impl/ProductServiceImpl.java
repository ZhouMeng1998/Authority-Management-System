package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IProductDao;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao dao;
    @Override
    public List<Product> findAll() throws Exception {
        List<Product> products = dao.findAll();
        return products;
    }

    @Override
    public void save(Product product) throws Exception{
        dao.save(product);
    }

    @Override
    public Product findById(String id) throws Exception{
        return dao.findById(id);
    }
}
