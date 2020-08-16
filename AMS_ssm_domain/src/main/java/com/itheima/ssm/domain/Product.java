package com.itheima.ssm.domain;

import java.util.Date;

public class Product {
    private String id; // 主键
    private String productNum; // 编号 唯一
    private String productName; // 名称
    private String cityName; // 出发城市
    private Date departureTime; // 出发时间
    private String departureTimeStr;
    private double productPrice; // 产品价格
    private String productDesc; // 产品描述
    private Integer productStatus; // 产品状态 0 关闭 1 开启
    private String productStatusStr;
}
