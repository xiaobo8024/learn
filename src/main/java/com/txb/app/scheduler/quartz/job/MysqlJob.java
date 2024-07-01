package com.txb.app.scheduler.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author shiva   2021/10/5 20:55
 */
@Slf4j
@Component("mysqlJob")
public class MysqlJob {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void execute(String param) {
        logger.info("执行 Mysql Job，当前时间：{}，任务参数：{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), param);
    }
}
