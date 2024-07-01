package com.txb.app.container.core;

import com.txb.app.container.core.task.ResourceController;
import com.txb.app.container.core.task.TaskQueue;
import lombok.extern.slf4j.Slf4j;

/**
 * 触发容器
 */
@Slf4j
public class Container{

    private Thread thread;

    private final Object blockerLock = new Object();

    private static volatile Container container;

    private final TaskQueue queue = TaskQueue.getInstance();

    private Container() {
    }

    public static Container getInstance(){
        if(null == Container.container ){
            synchronized (Container.class){
                if(null == Container.container){
                    Container.container = new Container();
                }
            }
        }
        return Container.container;
    }


    public void start(){
        thread = new Thread(()->{
            ResourceController resourceController = ResourceController.getInstance();
            if (null == resourceController) {
                log.error("任务执行控制器未初始化");
                return;
            }
            synchronized (blockerLock){
                for (; ; ) {
                    if (queue.isEmpty()) {
                        try {
                            blockerLock.wait();
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                    resourceController.execute();
                }
            }
        });
        thread.start();
    }

    //当前任务是否中断
    public boolean isInterrupted(){
        if(null == thread){
            return false;
        }
        return thread.isInterrupted();
    }

    //当前任务是否存活
    public boolean isAlive(){
        if(null == thread){
            return false;
        }
        return thread.isAlive();
    }

    public void interrupt(){
        thread.interrupt();
    }
    /**
     * 条件解锁
     */
    public void unLock(){
        synchronized (blockerLock){
            if(!queue.isEmpty()){
                blockerLock.notifyAll();
            }
        }
    }

}
