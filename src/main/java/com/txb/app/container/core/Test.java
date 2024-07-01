package com.txb.app.container.core;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.txb.app.container.core.task.ResourceController;
import com.txb.app.container.core.task.TaskQueue;
import com.txb.app.container.entity.Demo;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.concurrent.*;

@Slf4j
public class Test {

    private static volatile ArrayList<Integer> arrayList=new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {

        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix("容器-").setThreadFactory(Executors.defaultThreadFactory())
                .setUncaughtExceptionHandler((thread, throwable) -> {
                    log.error(thread.getName() + "线程出错了");
                }).build();
        ResourceController.init(new ThreadPoolExecutor(10, 20, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), threadFactory), TaskQueue.getInstance());
        Container container = Container.getInstance();
        container.start();
        TaskQueue queue = TaskQueue.getInstance();
        for (int i = 0; i < 1000000; i++) {
            Demo hello = new Demo("任务 "+i,null);
            hello.setSay("说你好");
            queue.offer(hello);
        }


        Thread.sleep(10000);
        System.out.println("数量:"+Demo.queue.size());

    }


}
