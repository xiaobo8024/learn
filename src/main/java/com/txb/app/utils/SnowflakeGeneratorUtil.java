package com.txb.app.utils;

import cn.hutool.core.lang.generator.SnowflakeGenerator;

public class SnowflakeGeneratorUtil {

    private static final SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator(1, 1);

    //获取long类型uuid
    public static Long getLongUUID(){
        return snowflakeGenerator.next();
    }

    //获取string类型uuid
    public static String getStrUUID(){
        return snowflakeGenerator.next().toString();
    }

}
