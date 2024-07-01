package com.txb.app.scheduler.quartz.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shiva   2021/10/4 22:52
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * api 返回使用，状态
     */
    protected int success = 200;
    protected int error = 500;

}
