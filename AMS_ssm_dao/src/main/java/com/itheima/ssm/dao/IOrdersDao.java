package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id=true, property = "id", column = "id"),
            @Result(id=true, property = "orderNum", column = "orderNum"),
            @Result(id=true, property = "orderTime", column = "orderTime"),
            @Result(id=true, property = "orderStatus", column = "orderStatus"),
            @Result(id=true, property = "peopleCount", column = "peopleCount"),
            @Result(id=true, property = "payType", column = "payType"),
            @Result(id=true, property = "orderDesc", column = "orderDesc"),
            @Result(id=true, property = "product", column = "productId", javaType = Product.class, one=@One(select = "com.itheima.ssm.dao.IProductDao.findById"))
    })
    public List<Orders> findAll() throws Exception;


    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id=true, property = "id", column = "id"),
            @Result(id=true, property = "orderNum", column = "orderNum"),
            @Result(id=true, property = "orderTime", column = "orderTime"),
            @Result(id=true, property = "orderStatus", column = "orderStatus"),
            @Result(id=true, property = "peopleCount", column = "peopleCount"),
            @Result(id=true, property = "payType", column = "payType"),
            @Result(id=true, property = "orderDesc", column = "orderDesc"),
            @Result(id=true, property = "product", column = "productId", javaType = Product.class, one=@One(select = "com.itheima.ssm.dao.IProductDao.findById")),
            @Result(id=true, property = "member", column = "memberId", javaType = Member.class, one=@One(select = "com.itheima.ssm.dao.IMemberDao.findById")),
            @Result(id=true, property = "travellers", column = "id", javaType = java.util.List.class, many=@Many(select = "com.itheima.ssm.dao.ITravellerDao.findByOrdersId"))
    })
    public Orders findById(String id) throws Exception;
}
