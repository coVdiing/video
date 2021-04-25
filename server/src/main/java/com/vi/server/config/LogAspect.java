package com.vi.server.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.vi.server.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 定义切点
     */
    @Pointcut("execution(public * com.vi.*.controller..*Controller.*(..))")
    public void controllerPointcut() {

    }

    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        // 日志编号
        MDC.put("UUID", UuidUtil.getShortUuid());

        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        // 打印业务操作
        String nameCn = "";
        if (name.contains("list") || name.contains("query")) {
            nameCn = "查询";
        } else if (name.contains("save")) {
            nameCn = "保存";
        } else if (name.contains("delete")) {
            nameCn = "删除";
        } else {
            nameCn = "操作";
        }

        // 使用反射获取业务名称
        Class clazz = signature.getDeclaringType();
        Field field;
        String businessName = "";
        try {
            field = clazz.getField("BUSINESS_NAME");
            if (!StringUtils.isEmpty(field)) {
                businessName = (String) field.get(clazz);
            }
        } catch (NoSuchFieldException e) {
            log.error("未获取到业务名称");
        } catch (IllegalAccessException e) {
            log.error("获取业务名称失败", e);
        }

        // 打印请求信息
        log.info("--------------------[{}] [{}]开始--------------------", businessName, nameCn);
        log.info("请求地址:{} {}", request.getRequestURL().toString(), request.getMethod());
        log.info("类名方法: {}.{}", signature.getDeclaringTypeName(), name);
        log.info("远程地址: {}", request.getRemoteAddr());

        // 打印请求参数
        Object[] args = joinPoint.getArgs();
        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest ||
                    args[i] instanceof ServletResponse ||
                    args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }

        // 排除字段，敏感字段和太长的字段不显示
        String[] excludeProperties = {"shard"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
        excludefilter.addExcludes(excludeProperties);
        log.info("请求参数: {}", JSONObject.toJSONString(arguments,excludefilter));
    }

    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 排除字段，敏感字段，太长的不显示
        String[] excludeProperties = {"password", "shard"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
        excludefilter.addExcludes(excludeProperties);
        log.info("返回结果: {}",JSONObject.toJSONString(result,excludefilter));
        log.info("--------------------结束 耗时:{} ms--------------------",System.currentTimeMillis()-start);
        return result;
    }
}
