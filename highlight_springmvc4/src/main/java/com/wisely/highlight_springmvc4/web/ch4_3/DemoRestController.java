package com.wisely.highlight_springmvc4.web.ch4_3;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisely.highlight_springmvc4.domain.DemoObj;

@RestController // 1
@RequestMapping("/rest")
public class DemoRestController {

    @RequestMapping(value = "/getjson", produces = {"application/json;charset=UTF-8"}) // 2
    public DemoObj getjson(DemoObj obj) {
        System.out.println("id:" + obj.getId()); // id被拦截了，而且接收是Long类型，url传递是String类型。下同
        System.out.println("name:" + obj.getName());
        // obj.setId(2l);
        return new DemoObj(obj.getId() + 1, obj.getName() + "yy");// 3
    }

    @RequestMapping(value = "/getxml", produces = {"application/xml;charset=UTF-8"}) // 4
    public DemoObj getxml(DemoObj obj) {
        // obj.setId(2l);
        return new DemoObj(obj.getId() + 1, obj.getName() + "yy");
    }

}
