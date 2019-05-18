package com.wisely.highlight_springmvc4.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName: ExceptionHandlerAdvice.java
 * @Description: 控制器的全局配置。结合@ExceptionHandler用于全局异常的处理
 * @author zhanghu
 * @date 2019年5月17日 上午1:26:44
 */
@ControllerAdvice // 1 声明一个控制器的建言
public class ExceptionHandlerAdvice {

    // @ExceptionHandler的作用主要在于声明一个或多个类型的异常
    @ExceptionHandler(value = Exception.class) // 2 定义一个全局处理，value是过滤器的条件，这里是拦截所有的Exception。
    public ModelAndView exception(Exception exception, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("error");// error页面
        modelAndView.addObject("errorMessage", exception.getMessage());
        return modelAndView;
    }

    @ModelAttribute // 3 此处的注解讲键值对添加到全局
    public void addAttributes(Model model) {
        model.addAttribute("msg", "额外信息"); // 3 所有注解的@RequestMapping的方法可获得此键值对。
    }

    // @InitBinder用来设置WebDataBinder，WebDataBinder用来自动绑定前台请求参数到Model中
    @InitBinder // 4 此注解定制WebDataBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id"); // 5 忽略request参数的id
    }
}
