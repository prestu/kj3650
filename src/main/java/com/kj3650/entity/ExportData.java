package com.kj3650.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fanchengheng
 * @date 2023/7/8 21:42
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
public class ExportData {

    @ExcelProperty(value = "开奖时间")
    @ColumnWidth(25)
    private String startTime;



    @ExcelProperty(value = "期数")
    @ColumnWidth(20)
    private String period;

    @ExcelProperty(value = "数字1")
    private String number1;

    @ExcelProperty(value = "数字2")
    private String number2;

    @ExcelProperty(value = "数字3")
    private String number3;

    @ExcelProperty(value = "数字总和")
    @ColumnWidth(15)
    private String numberSum;
    @ExcelProperty(value = "特码1")
    private String specialCode1;

    @ExcelProperty(value = "特码2")
    private String specialCode2;

    @ExcelProperty(value = "特码3")
    private String specialCode3;

    @ExcelProperty(value = "波色")
    private String waveColor;

    @ExcelProperty(value = "极值")
    private String extremeValue;
    @ExcelProperty(value = "顺子/对子/豹子")
    @ColumnWidth(25)
    private String sdb;
}
