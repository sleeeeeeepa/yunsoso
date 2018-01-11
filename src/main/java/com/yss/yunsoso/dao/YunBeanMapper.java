package com.yss.yunsoso.dao;

import com.yss.yunsoso.domain.YunBean;
import com.yss.yunsoso.domain.YunBeanExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface YunBeanMapper {
    int countByExample(YunBeanExample example);

    int deleteByExample(YunBeanExample example);

    int deleteByPrimaryKey(String id);

    int insert(YunBean record);

    int insertSelective(YunBean record);

    List<YunBean> selectByExample(YunBeanExample example);

    YunBean selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") YunBean record, @Param("example") YunBeanExample example);

    int updateByExample(@Param("record") YunBean record, @Param("example") YunBeanExample example);

    int updateByPrimaryKeySelective(YunBean record);

    int updateByPrimaryKey(YunBean record);
}