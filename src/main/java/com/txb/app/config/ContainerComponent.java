package com.txb.app.config;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.txb.app.container.core.Container;
import com.txb.app.container.core.task.ResourceController;
import com.txb.app.container.core.task.TaskQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Slf4j
@Component
public class ContainerComponent {
    static {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix("容器-").setThreadFactory(Executors.defaultThreadFactory())
                .setUncaughtExceptionHandler((thread, throwable) -> {
                    log.error(thread.getName() + "线程出错了");
                }).build();
        ResourceController.init(new ThreadPoolExecutor(10, 20, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), threadFactory), TaskQueue.getInstance());
        Container container = Container.getInstance();
        container.start();
    }
}
