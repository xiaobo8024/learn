package com.txb.app.scheduler.quartz.srvice;


import com.txb.app.scheduler.quartz.entity.QuartzJob;
import org.quartz.SchedulerException;

/**
 * @author shiva   2021/10/5 21:31
 */
public interface IQuartzJobService {

    /**
     * 暂停任务
     *
     * @param job 调度信息
     */
    public int pauseJob(QuartzJob job) throws SchedulerException;

    /**
     * 恢复任务
     *
     * @param job 调度信息
     */
    public int resumeJob(QuartzJob job) throws SchedulerException;

    /**
     * 删除任务后，所对应的trigger也将被删除
     *
     * @param job 调度信息
     */
    public int deleteJob(QuartzJob job) throws SchedulerException;

    /**
     * 任务调度状态修改
     */
    public int changeStatus(Long jobId, String status) throws SchedulerException;

    /**
     * 立即运行任务
     *
     * @param job 调度信息
     */
    public void run(QuartzJob job) throws SchedulerException;

    /**
     * 新增任务
     *
     * @param job 调度信息
     */
    public int insertJob(QuartzJob job) throws Exception;

    /**
     * 更新任务
     *
     * @param job 调度信息
     */
    public int updateJob(QuartzJob job) throws SchedulerException, Exception;

    /**
     * 校验cron表达式是否有效
     *
     * @param cronExpression 表达式
     */
    public boolean checkCronExpressionIsValid(String cronExpression);

}
