package com.wisely.highlight_springmvc4.service;

import org.springframework.stereotype.Service;

/**
 * @ClassName: DemoService.java
 * @Description: SpringMVC的测试。演示服务
 * @author zhanghu
 * @date 2019年5月17日 下午5:24:06
 */
@Service
public class DemoService {

    public String saySomething() {
        return "hello";
    }

}
