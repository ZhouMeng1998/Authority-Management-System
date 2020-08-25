package com.itheima.ssm.controller;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ILogAOPService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.MethodInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.http.HttpRequest;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private ILogAOPService service;
    @Autowired
    private HttpServletRequest request;

    private Date visitTime;
    private Class executionClass;
    private Method executionMethod;

    @Before("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        //获取访问时间
        visitTime = new Date();
        //获取类名
        executionClass = jp.getTarget().getClass();
        //获取执行方法名
        String methodName = jp.getSignature().getName();
        //获取执行方法
        Object[] args = jp.getArgs();
        if(args == null){
            executionMethod = executionClass.getMethod(methodName);
        }else{
            Class[] classArgs = new Class[args.length];
            for(int i = 0; i < args.length; i++){
                classArgs[i] = args.getClass();
            }
            executionMethod = executionClass.getMethod(methodName, classArgs);
        }
    }

    @After("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        //获取IP
        String ip = request.getRemoteAddr();
        //获取URL
        String url = "";
        if(executionClass != LogAop.class) {
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if(classAnnotation != null){
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                url = classAnnotation.value()[0] + methodAnnotation.value()[0];
            }
        }
        //获取执行时间
        Long executionTime =  new Date().getTime() - visitTime.getTime();
        //获取username
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User)context.getAuthentication().getPrincipal();
        String username = user.getUsername();
        //获取方法名
        String methodName = executionMethod.getName();
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(executionTime);
        sysLog.setIp(ip);
        sysLog.setMethod(methodName);
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visitTime);
        if(!url.contains("/sysLog")) {
            service.save(sysLog);
        }
    }

}
