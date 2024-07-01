package com.txb.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.txb.app.domain.entity.Demo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemoMapper extends BaseMapper<Demo> {
}
