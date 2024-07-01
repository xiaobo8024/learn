package com.txb.app.scheduler.quartz.util;


import com.txb.app.scheduler.quartz.entity.QuartzJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（禁止并发执行）
 *
 * @author ruoyi
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, QuartzJob job) throws Exception {
        JobInvokeUtil.invokeMethod(job);
    }
}
