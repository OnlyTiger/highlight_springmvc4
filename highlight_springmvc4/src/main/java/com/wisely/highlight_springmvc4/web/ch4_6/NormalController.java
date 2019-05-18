package com.wisely.highlight_springmvc4.web.ch4_6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wisely.highlight_springmvc4.service.DemoService;

/**
 * 
 * @ClassName: NormalController.java
 * @Description: SpringMVC测试。普通控制器
 * @author zhanghu
 * @date 2019年5月17日 下午5:46:34
 */
@Controller // 此处不能使用@RestController，配置的视图解析器 InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容，如字符串：page
public class NormalController {
    @Autowired
    DemoService demoService;


    @RequestMapping("/normal")
    public String testPage(Model model) {
        model.addAttribute("msg", demoService.saySomething());
        return "page";
    }

}
