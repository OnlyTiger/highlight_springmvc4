package com.wisely.highlight_springmvc4.web.ch4_5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.wisely.highlight_springmvc4.service.PushService;

/**
 * 
 * @ClassName: AysncController.java
 * @Description: Servlet3.0+异步方法处理
 * @author zhanghu
 * @date 2019年5月17日 下午4:38:12
 */
@Controller
public class AysncController {
    @Autowired
    PushService pushService; // 1 调用定时任务

    @RequestMapping("/defer")
    @ResponseBody
    public DeferredResult<String> deferredCall() { // 2 返回给客户端
        return pushService.getAsyncUpdate();
    }

}

