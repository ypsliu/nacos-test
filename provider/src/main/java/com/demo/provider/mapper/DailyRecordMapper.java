package com.demo.provider.mapper;

import com.demo.provider.entity.DailyRecord;

import java.util.List;

/**
 * @author lzy
 * @version 1.0
 * @date 2021/11/20 11:45
 */
public interface DailyRecordMapper {
    List<DailyRecord> selectAllDailyRecord();
}
