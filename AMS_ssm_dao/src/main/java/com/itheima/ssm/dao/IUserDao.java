package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "email", property = "email"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles",javaType = java.util.List.class, many=@Many(select = "com.itheima.ssm.dao.IRoleDao.findByUserId"))
    })
    public UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    @Insert("insert into users(username, password, email, phoneNum, status) values(#{username}, #{password}, #{email}, #{phoneNum}, #{status}) ")
    void save(UserInfo info);

    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "email", property = "email"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = java.util.List.class, many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findByUserId"))
    })
    UserInfo findById(String id);

    @Insert("insert into users_role(userId, roleId) values(#{userId}, #{roleId})")
    void addRoleToUser(@Param("roleId")String roleId, @Param("userId") String userId);
}
