package com.itheima.ssm.service;

import com.itheima.ssm.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IProductService {
    /**
     * 查询所有商品
     * @return
     * @throws Exception
     */
    public List<Product> findAll() throws Exception;

    /**
     * 添加商品方法
     * @param product
     */
    void save(Product product) throws Exception;

    /**
     * 根据ID查找产品
     * @param id
     * @return
     */
    public Product findById(String id)throws Exception;
}
