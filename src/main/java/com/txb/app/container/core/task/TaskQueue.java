package com.txb.app.container.core.task;

import com.txb.app.container.core.Container;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TaskQueue<BaseTask> extends ConcurrentLinkedQueue<BaseTask> {
    private static volatile TaskQueue taskQueue;
    private TaskQueue (){

    }
    private TaskQueue(Collection<? extends BaseTask> c) {
        this();
        addAll(c);
    }

    public static TaskQueue getInstance(){
        if(null == TaskQueue.taskQueue){
            synchronized (TaskQueue.class){
                if (null == TaskQueue.taskQueue){
                    TaskQueue.taskQueue = new TaskQueue<>();
                }
            }
        }

        return TaskQueue.taskQueue;
    }

    @Override
    public boolean add(BaseTask t) {
        if(t instanceof com.txb.app.container.core.task.BaseTask){
            boolean ok = super.add(t);
            if(ok){
                Container container = Container.getInstance();
                container.unLock();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean offer(BaseTask t) {
        if(t instanceof com.txb.app.container.core.task.BaseTask){
            boolean ok = super.offer(t);
            if(ok){
                Container container = Container.getInstance();
                container.unLock();
                return true;
            }
        }

         return false;
    }

    @Override
    public boolean addAll(Collection<? extends BaseTask> c) {
        if(c instanceof com.txb.app.container.core.task.BaseTask){
          boolean ok = super.addAll(c);
            if(ok){
                Container container = Container.getInstance();
                container.unLock();
                return true;
            }
        }
        return false;
    }
}
