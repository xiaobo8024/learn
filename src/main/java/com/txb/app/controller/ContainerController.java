package com.txb.app.controller;


import com.txb.app.container.core.task.TaskQueue;
import com.txb.app.container.entity.Demo;
import com.txb.app.utils.ApiResp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/container")
public class ContainerController {


    @PostMapping("/runTask")
    public ApiResp<Object> runTask(@RequestBody Demo hello){
        TaskQueue queue = TaskQueue.getInstance();
        queue.add(hello);
        return ApiResp.success();
    }
}
