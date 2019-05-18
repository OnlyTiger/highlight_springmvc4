package com.wisely.highlight_springmvc4.web.ch4_6;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.wisely.highlight_springmvc4.MyMvcConfig;
import com.wisely.highlight_springmvc4.service.DemoService;

/**
 * 
 * @ClassName: TestControllerIntegrationTests.java
 * @Description: SpringMVC测试。测试用例
 * @author zhanghu
 * @date 2019年5月17日 下午5:26:25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyMvcConfig.class})
@WebAppConfiguration("src/main/resources") // 1 加载ApplicationContex，它的属性是指定的是Web资源的位置。
public class TestControllerIntegrationTests {
    private MockMvc mockMvc; // 2 看下面的方法。MockMvc模拟MVC对象，通过其方法初始化。

    @Autowired
    private DemoService demoService;// 3 可以在测试用例中注入Spring的Bean。

    @Autowired
    WebApplicationContext wac; // 4 可注入WebApplicationContex。

    @Autowired
    MockHttpSession session; // 5 可注入模拟的http session，此处仅作为演示，没有使用

    @Autowired
    MockHttpServletRequest request; // 6 可注入模拟的http request，此处仅作为演示，没有使用

    @Before // 7 @Before在测试开始前进行的初始化工作。
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build(); // 2、MockMvc模拟MVC对象，通过其方法初始化。
    }

    @Test
    public void testNormalController() throws Exception {
        mockMvc.perform(get("/normal")) // 8
                .andExpect(status().isOk())// 9
                .andExpect(view().name("page"))// 10
                .andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp"))// 11
                .andExpect(model().attribute("msg", demoService.saySomething()));// 12

    }

    @Test
    public void testRestController() throws Exception {
        mockMvc.perform(get("/testRest")) // 13
                .andExpect(status().isOk()).andExpect(content().contentType("text/plain;charset=UTF-8"))// 14
                .andExpect(content().string(demoService.saySomething()));// 15
    }

}
