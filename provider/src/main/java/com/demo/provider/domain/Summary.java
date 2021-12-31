package com.demo.provider.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author lzy
 * @version 1.0
 * @date 2021/11/20 15:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ContentRowHeight(15)
@HeadRowHeight(20)
@ColumnWidth(15)
public class Summary {

    @DateTimeFormat("yyyy年MM月dd日")
    @ExcelProperty(value = "制表日期",index = 0)
    private LocalDateTime date;
    @NumberFormat("#.##")
    @ExcelProperty(value = "总用气量",index = 1)
    private Double usegas;
    @NumberFormat("#.##")
    @ExcelProperty(value = "总用气金额",index = 2)
    private Double amount;
}
