package com.txb.app.controller;

import com.txb.app.domain.entity.Demo;
import com.txb.app.exception.MyException;
import com.txb.app.service.DemoService;
import com.txb.app.utils.ApiResp;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/Demo")
public class DemoController {

    @Resource
    private DemoService demoService;

    @PostMapping("/input")
    public Object putDemo(@RequestBody Demo demo) throws MyException {
        Demo demo1 = null;
        demo1 = demoService.putDemo(demo);
        if (null == demo1) {
            return ApiResp.error(500, "添加失败",null);

        }

        return ApiResp.success();
    }
}
