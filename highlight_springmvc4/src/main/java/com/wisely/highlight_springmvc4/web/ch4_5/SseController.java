package com.wisely.highlight_springmvc4.web.ch4_5;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: SseController.java
 * @Description: 服务器端推送技术SSE（Server Send Event 服务端发送事件）
 * @author zhanghu
 * @date 2019年5月17日 上午1:22:54
 */
@Controller
public class SseController {

    @RequestMapping(value = "/push", produces = "text/event-stream") // 1 这里使用输出的媒体类型为text/event-stream，这是服务器端SSE的支持
    public @ResponseBody String push() {
        Random r = new Random();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data:Testing 1,2,3" + r.nextInt() + "\n\n";
    }

}
