package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermissionDao {
    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{id})")
    List<Permission> findByRoleId(String id);
}
