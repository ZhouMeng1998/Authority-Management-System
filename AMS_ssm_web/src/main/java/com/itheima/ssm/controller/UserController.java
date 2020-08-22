package com.itheima.ssm.controller;


import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import com.itheima.ssm.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService service;
    /**
     * 查询所有用户
     * @return
     */


    @RequestMapping("/save.do")
    public String save(UserInfo info) throws Exception{
        service.save(info);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = service.findAll();
        mv.addObject("userList", userList);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo user = service.findById(id);
        mv.addObject("user", user);
        mv.setViewName("user-show");
        return mv;
    }
}
