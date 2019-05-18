package com.wisely.highlight_springmvc4.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 
 * @ClassName: PushService.java
 * @Description: 定时任务，定时更新DeferredResult
 * @author zhanghu
 * @date 2019年5月17日 下午4:45:21
 */
@Service
public class PushService {
    private DeferredResult<String> deferredResult; // 1 在PushService里产生DeferredResult给控制器使用

    public DeferredResult<String> getAsyncUpdate() {
        deferredResult = new DeferredResult<String>();
        return deferredResult;
    }

    @Scheduled(fixedDelay = 5000)
    public void refresh() { // 使用@Scheduled注解，定时更新DeferredResult。
        if (deferredResult != null) {
            deferredResult.setResult(new Long(System.currentTimeMillis()).toString());
        }
    }


}
