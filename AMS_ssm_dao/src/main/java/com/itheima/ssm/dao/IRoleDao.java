package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleDao {
    @Select("select * from role where id in(select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(column = "id", property = "permissions", javaType = java.util.List.class, many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findByRoleId"))

    })
    public List<Role> findByUserId(String userId);

    @Select("select * from role where id not in (select roleId from users_role where userId = #{userId})")
    List<Role> findOtherRoles(String userId);
}
