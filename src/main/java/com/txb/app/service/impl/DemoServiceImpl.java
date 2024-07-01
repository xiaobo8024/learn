package com.txb.app.service.impl;

import com.txb.app.dao.DemoMapper;
import com.txb.app.domain.entity.Demo;
import com.txb.app.exception.MyException;
import com.txb.app.service.DemoService;
import com.txb.app.utils.SnowflakeGeneratorUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.awt.*;
import java.io.IOException;
import java.util.Date;
@Slf4j
@Service
public class DemoServiceImpl implements DemoService {

    @Resource
    private DemoMapper demoMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Demo putDemo(Demo demo) throws MyException {

        demo.setId(SnowflakeGeneratorUtil.getStrUUID());
        demo.settCrtTm(new Date());
        demo.settUpdTm(new Date());

        int row = this.insert(demo);
        if(row == 1){
            throw new MyException(100,"添加失败");
        }

        return demo;
    }


    public int insert(Demo demo){
        return demoMapper.insert(demo);
    }
}
