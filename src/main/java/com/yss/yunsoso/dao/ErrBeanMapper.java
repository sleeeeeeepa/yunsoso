package com.yss.yunsoso.dao;

import com.yss.yunsoso.domain.ErrBean;
import com.yss.yunsoso.domain.ErrBeanExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ErrBeanMapper {
    int countByExample(ErrBeanExample example);

    int deleteByExample(ErrBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ErrBean record);

    int insertSelective(ErrBean record);

    List<ErrBean> selectByExample(ErrBeanExample example);

    ErrBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ErrBean record, @Param("example") ErrBeanExample example);

    int updateByExample(@Param("record") ErrBean record, @Param("example") ErrBeanExample example);

    int updateByPrimaryKeySelective(ErrBean record);

    int updateByPrimaryKey(ErrBean record);
}