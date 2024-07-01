package com.txb.app.container.core.task;

import com.txb.app.utils.SnowflakeGeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
@Slf4j
@Component
public abstract class BaseTask <T> implements Runnable{

    //参数
    private T paramObj;

    //任务名
    private String taskName;

    public BaseTask() {
        this.taskName = SnowflakeGeneratorUtil.getStrUUID();
    }

    public BaseTask(String taskName,T paramObj){
        this.taskName = taskName;
        this.paramObj = paramObj;
    }
    //执行方法
    public abstract Object runTask(T paramObj);

    public T getParamObj() {
        return paramObj;
    }

    public void setParamObj(T paramObj) {
        this.paramObj = paramObj;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        synchronized (this){
            this.beforeRun(paramObj);
            this.runTask(paramObj);
            this.afterRun(paramObj);
        }
    }

    public void afterRun(T paramObj){

    }

    public void beforeRun(T paramObj){

    }
}
