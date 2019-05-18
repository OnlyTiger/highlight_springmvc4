package com.wisely.highlight_springmvc4.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @ClassName: DemoInterceptor.java
 * @Description: 拦截器配置
 * @author zhanghu
 * @date 2019年5月17日 上午1:25:40
 */
public class DemoInterceptor extends HandlerInterceptorAdapter {// 1
                                                                // 继承HandlerInterceptorAdapter类来实行自定义拦截器

    @Override
    public boolean preHandle(HttpServletRequest request, // 2 重写preHandle方法，在请求发生之前执行
            HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, // 3 重写postHandle方法，在请求完成之后执行
            HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        long endTime = System.currentTimeMillis();
        System.out.println("本次请求处理时间为:" + new Long(endTime - startTime) + "ms");
        request.setAttribute("handlingTime", endTime - startTime);
    }

}
