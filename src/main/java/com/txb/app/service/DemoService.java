package com.txb.app.service;

import com.txb.app.domain.entity.Demo;
import com.txb.app.exception.MyException;

public interface DemoService {
    Demo putDemo(Demo demo) throws MyException;
}
