package com.demo.provider.excel.write;

import cn.hutool.core.convert.Convert;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.demo.provider.domain.DailyRecord;
import com.demo.provider.domain.Summary;
import com.demo.provider.mapper.DailyRecordMapper;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lzy
 * @version 1.0
 * @date 2021/11/20 9:37
 */
@Slf4j
@Component
public class WriteHandler {
    private static final Integer PAGE_COUNT = 100000;

    @Value("${path.desktop}")
    private String url;
    @Resource
    private DailyRecordMapper dailyRecordMapper;

    public void writeExcelByPageInfo(){
        String fileName = url + "daily_record" + System.currentTimeMillis() + ".xlsx";
        ExcelWriter excelWriter = null;
        List<DailyRecord> dailyRecords = null;
        Summary summary = new Summary(LocalDateTime.now(),0.00,0.00);
        //统计总个数，后边分页设置count()为false，避免每次查询count*
        Integer counts = Convert.toInt(PageHelper.count(
                () -> dailyRecordMapper.selectAllDailyRecord()
        ));
        Integer pages = counts % PAGE_COUNT == 0 ? counts / PAGE_COUNT : counts / PAGE_COUNT + 1;
        log.info("counts:{},page_count:{},pages:{}",counts,PAGE_COUNT,pages);

        try {
            // 这里 指定文件
            excelWriter = EasyExcel.write(fileName).build();
            // 去调用写入，实际使用时根据数据库分页的总的页数来
            for (int i = 1; i <= pages; i++) {
                // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
                WriteSheet writeSheet = EasyExcel.writerSheet(i, "sheet" + i).head(DailyRecord.class).build();
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                PageHelper.startPage(i, PAGE_COUNT, false);
                dailyRecords = dailyRecordMapper.selectAllDailyRecord();
                Double pageAllAmount = dailyRecords.stream()
                                .collect(Collectors.summingDouble(DailyRecord::getAmount));
                summary.setAmount(summary.getAmount()+pageAllAmount);
                Double pageAllUsegas = dailyRecords.stream()
                                .collect(Collectors.summingDouble(DailyRecord::getUsegas));
                summary.setUsegas(summary.getUsegas()+pageAllUsegas);
                excelWriter.write(dailyRecords, writeSheet);
            }
            //汇总信息入表
            WriteSheet writeSheet = EasyExcel.writerSheet(pages+1, "summary" ).head(Summary.class).build();
            summary.setDate(LocalDateTime.now());
            excelWriter.write(Arrays.asList(summary),writeSheet);
        } catch (Exception e) {
            log.error("WriteHandler error:" + e);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }
}
