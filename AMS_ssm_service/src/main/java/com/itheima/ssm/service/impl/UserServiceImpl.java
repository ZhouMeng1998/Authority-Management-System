package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IUserDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao dao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("进入此方法");
        UserInfo userInfo = null;
        try {
            userInfo = dao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Role> roles = userInfo.getRoles();
        User user = new User(userInfo.getUsername(), bCryptPasswordEncoder.encode( userInfo.getPassword()),
                userInfo.getStatus() == 0 ? false : true, true, true, true,
                getAuthorities(roles));
        return user;
    }
    public List<SimpleGrantedAuthority> getAuthorities(List<Role> roles){
        List<SimpleGrantedAuthority> authorities = new LinkedList<>();
        for(Role role : roles){
            String roleName = role.getRoleName();
            authorities.add(new SimpleGrantedAuthority("ROLE_"+roleName));
        }
        return authorities; }

    @Override
    public List<UserInfo> findAll() throws Exception{
        return dao.findAll();
    }

    @Override
    public void save(UserInfo info) throws Exception{
        info.setPassword(bCryptPasswordEncoder.encode(info.getPassword()));
        dao.save(info);
    }
}
