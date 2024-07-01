package com.txb.app.container.core.task;

import javax.validation.constraints.NotNull;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class ResourceController<BaseTask> {
    private static volatile ResourceController resourceController;
    //包含要执行的任务，
    //包含选择那个线程去执行
    private ThreadPoolExecutor threadPoolExecutor;
    private ConcurrentLinkedQueue queue ;
    public ResourceController(@NotNull ThreadPoolExecutor threadPoolExecutor, @NotNull TaskQueue  queue){
        this.threadPoolExecutor = threadPoolExecutor;
        this.queue = queue;
    }

    public ResourceController(@NotNull ThreadPoolExecutor threadPoolExecutor){
        this.threadPoolExecutor = threadPoolExecutor;
        this.queue = TaskQueue.getInstance();
    }

    public static void init(ThreadPoolExecutor threadPoolExecutor, TaskQueue queue){
        if(null == ResourceController.resourceController){
            synchronized (ResourceController.class){
                if(null == ResourceController.resourceController){
                    ResourceController.resourceController = new ResourceController(threadPoolExecutor,queue);
                }
            }
        }
    }

    public static ResourceController getInstance(){
        return ResourceController.resourceController;
    }

    public ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }

    public void setThreadPoolExecutor(ThreadPoolExecutor threadPoolExecutor,TaskQueue queue) {
        this.threadPoolExecutor = threadPoolExecutor;
    }

    public static ResourceController getResourceController() {
        return resourceController;
    }

    public static void setResourceController(ResourceController resourceController) {
        ResourceController.resourceController = resourceController;
    }

    public void setThreadPoolExecutor(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }

    public ConcurrentLinkedQueue getQueue() {
        return queue;
    }

    public void setQueue(ConcurrentLinkedQueue queue) {
        this.queue = queue;
    }

    public  void execute() {
      if (!queue.isEmpty()){
          Object obj = queue.poll();
          if(obj instanceof com.txb.app.container.core.task.BaseTask){
              com.txb.app.container.core.task.BaseTask task = (com.txb.app.container.core.task.BaseTask) obj;
              threadPoolExecutor.execute(task);
          }
    }

    }
}
