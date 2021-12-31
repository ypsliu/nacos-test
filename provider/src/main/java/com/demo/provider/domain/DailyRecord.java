package com.demo.provider.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.*;

import java.util.Date;

/**
 * @author lzy
 * @version 1.0
 * @date 2021/11/20 9:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ContentRowHeight(15)
@HeadRowHeight(20)
@ColumnWidth(15)
public class DailyRecord {
    /**
     * 我想所有的 字符串起前面加上"自定义："三个字
     */
//    @ExcelProperty(converter = CustomStringStringConverter.class)

    @ExcelProperty(value = "表具编号",index = 0)
    private String meterNo;
    @ExcelProperty(value = "用户编号",index = 1)
    private String userNo;
    @ExcelProperty(value = "用气地址",index = 3)
    private String address;
    @ExcelProperty(value = "用户姓名",index = 2)
    private String userName;
    @ExcelProperty(value = "单价",index = 5)
    private String price;

    @DateTimeFormat("yyyy年MM月dd日")
    @ExcelProperty(value = "日期标题",index = 4)
    private Date date;

    @NumberFormat("#.##")
    @ExcelProperty(value = "用气量",index = 6)
    private Double usegas;
    @NumberFormat("#.##")
    @ExcelProperty(value = "用气金额",index = 7)
    private Double amount;
}
