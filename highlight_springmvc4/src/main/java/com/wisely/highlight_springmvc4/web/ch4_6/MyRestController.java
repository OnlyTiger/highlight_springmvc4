package com.wisely.highlight_springmvc4.web.ch4_6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wisely.highlight_springmvc4.service.DemoService;

/**
 * 
 * @ClassName: MyRestController.java
 * @Description: SpringMVC测试。RestController控制器
 * @author zhanghu
 * @date 2019年5月17日 下午5:47:14
 */
@RestController // 此处用@Controller返回结果是一样，Service业务业务中的返回值：字符串。@RestController注解相当于@ResponseBody和@Controller合在一起的作用。
public class MyRestController {

    @Autowired
    DemoService demoService;

    @RequestMapping(value = "/testRest", produces = "text/plain;charset=UTF-8")
    public @ResponseBody String testRest() { // 其实有@RestController，方法上就可以不需要@ResponseBody。但@ResponseBody要与produces仪器使用。
        return demoService.saySomething();
    }

}
