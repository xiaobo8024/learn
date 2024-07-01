package com.txb.app.container.entity;

import com.txb.app.container.core.task.BaseTask;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Demo <T> extends BaseTask<T> {
    public static volatile int num = 0;
    public static volatile ConcurrentLinkedQueue queue=new ConcurrentLinkedQueue();
    private String say;
    public Demo() {
        super();
    }

    public Demo(String taskName, T paramObj) {
        super(taskName, paramObj);
    }

    @Override
    public synchronized Object runTask(T paramObj) {
        Thread thread = Thread.currentThread();
        System.out.println("我是"+thread.getName()+"任务,"+ "我要做的事是 是: "+this.say);
        queue.offer(num++);
        return null;
    }

    public String getSay() {
        return say;
    }

    public void setSay(String say) {
        this.say = say;
    }
}
