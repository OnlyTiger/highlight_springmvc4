package com.wisely.highlight_springmvc4;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.wisely.highlight_springmvc4.interceptor.DemoInterceptor;
import com.wisely.highlight_springmvc4.messageconverter.MyMessageConverter;

@Configuration
@EnableWebMvc // 1 开始SpringMVC的支持，若无此句，重写WebMvcConfigurerAdapter方法无效。
@EnableScheduling // 开启计划任务的支持。如AysncController类中的异步功能。
@ComponentScan("com.wisely.highlight_springmvc4")
public class MyMvcConfig extends WebMvcConfigurerAdapter {// 2
                                                          // 继承WebMvcConfigurerAdapter类，重写其方法可对SpringMVC进行配置。

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");// 3
                                                                                             // addResourceHandler指的是对外暴露的访问路径，addResourceLocations指的是文件放置目录

    }

    @Bean
    // 1 配置拦截器的Bean
    public DemoInterceptor demoInterceptor() {
        return new DemoInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {// 2 重写addInterceptors方法，注册拦截器
        registry.addInterceptor(demoInterceptor());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {// 重写addViewControllers，实现“页面转向”功能。
        // addViewController是暴露的url路径，setViewName视图views代码下jsp名称
        registry.addViewController("/index").setViewName("/index"); // HelloController类
        registry.addViewController("/toUpload").setViewName("/upload");// UploadController类
        registry.addViewController("/converter").setViewName("/converter"); // 看自定义的MyMessageConverter类
        registry.addViewController("/sse").setViewName("/sse");
        registry.addViewController("/async").setViewName("/async");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);// 此设置为false，可以忽略url请求路径参数中“.”后面的参数。
    }

    @Bean
    public MultipartResolver multipartResolver() { // SpringMVC通过配置一个MultipartResolver（文件上传解析）来上传文件
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }

    @Bean
    public MyMessageConverter converter() {
        return new MyMessageConverter();
    }


}
