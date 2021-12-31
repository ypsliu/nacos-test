package com.demo.provider.excel.read.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author lz
 * @date 2021-10-18 13:42
 */
@Data
public class Meter {
    @ExcelProperty("表具编号")
    private String meterNo;
    @ExcelProperty("协议号")
    private String protocolId;
}
