package com.demo.dynamic.service;

import com.demo.dynamic.constant.Constant;
import com.demo.dynamic.dao.TenantInfoMapper;
import com.demo.dynamic.entity.TenantInfo;
import com.demo.dynamic.target.ChooseSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/18
 * Time: 16:27
 * Description: No Description
 */
@Service
public class TenantInfoService {
    @Resource
    private TenantInfoMapper tenantInfoMapper;

    //不开启事务
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @ChooseSource(Constant.MASTER_DATA_SOURCE)
    public TenantInfo list_master() {
        return tenantInfoMapper.selectByPrimaryKey(1L);
    }

    //开启事务
    @Transactional(propagation = Propagation.REQUIRED)
    @ChooseSource(Constant.SALVE_DATA_SOURCE)
    public TenantInfo list_slave() {
        return tenantInfoMapper.selectByPrimaryKey(1L);
    }

    //默认
    public TenantInfo list_default(){
        return tenantInfoMapper.selectByPrimaryKey(1L);
    }
}
