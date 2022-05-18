package com.demo.dynamic.dao;

import com.demo.dynamic.entity.TenantInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TenantInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TenantInfo record);

    int insertSelective(TenantInfo record);

    TenantInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TenantInfo record);

    int updateByPrimaryKey(TenantInfo record);
}