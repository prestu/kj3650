package com.kj3650.task;

import com.alibaba.excel.EasyExcel;
import com.kj3650.entity.ExportData;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author fanchengheng
 * @date 2023/7/8 22:39
 */


@Slf4j
@Component
public class ExportDataTask {

    @Value("${kj3650.url}")
    private String url;

    @Value("${kj3650.exportPath}")
    private String exportPath;

    @Autowired
    private WebDriver webDriver;
    @Scheduled(cron = "0 */1 * * * ?")
    private void start() throws InterruptedException {
        webDriver.get(url);
        webDriverWait();
        try {
            log.info("--------开始统计--------");
            File file = new File(exportPath);
            List<ExportData> datas = EasyExcel.read(file, ExportData.class, null).doReadAllSync();
            datas.add(getExportData(webDriver));
            // 创建excel对象参数一：路径，参数二：实体类对象。sheet：表sheet名，doWrite：存放的对象集合
            EasyExcel.write(exportPath, ExportData.class).sheet("开奖统计数据").doWrite(datas);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            log.info("--------统计结束--------");
        }
    }

    private ExportData getExportData(WebDriver webDriver) {
        String startTime = new WebDriverWait(webDriver, 40)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div/div[3]/div[3]/div/div[2]/div[3]/table/tr[2]/td[1]")))
                .getText();
        String period = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div[3]/div/div[2]/div[3]/table/tr[2]/td[2]")).getText();
        String numberSum = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div[3]/div/div[2]/div[3]/table/tr[2]/td[3]/span[7]")).getText();
        String number1 = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div[3]/div/div[2]/div[3]/table/tr[2]/td[3]/span[1]")).getAttribute("class");
        String number2 = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div[3]/div/div[2]/div[3]/table/tr[2]/td[3]/span[3]")).getAttribute("class");
        String number3 = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div[3]/div/div[2]/div[3]/table/tr[2]/td[3]/span[5]")).getAttribute("class");
        String specialCode1 = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div[3]/div/div[2]/div[3]/table/tr[2]/td[4]")).getText();
        String specialCode2 = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div[3]/div/div[2]/div[3]/table/tr[2]/td[5]")).getText();
        String specialCode3 = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div[3]/div/div[2]/div[3]/table/tr[2]/td[6]")).getText();
        String waveColor = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div[3]/div/div[2]/div[3]/table/tr[2]/td[7]")).getText();
        String extremeValue = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div[3]/div/div[2]/div[3]/table/tr[2]/td[8]")).getText();
        String sdb = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[3]/div[3]/div/div[2]/div[3]/table/tr[2]/td[9]")).getText();
        number1 = number1 == null ? null : number1.substring(number1.indexOf("-") + 1);
        number2 = number2 == null ? null : number2.substring(number2.indexOf("-") + 1);
        number3 = number3 == null ? null : number3.substring(number3.indexOf("-") + 1);

        log.info("开奖日期：= {}", startTime);
        log.info("期数：= {}", period);
        log.info("数字1：= {}", number1);
        log.info("数字2：= {}", number2);
        log.info("数字3：= {}", number3);
        log.info("数字总和：= {}", numberSum);
        log.info("特码1：= {}", specialCode1);
        log.info("特码2：= {}", specialCode2);
        log.info("特码3：= {}", specialCode3);
        log.info("波色：= {}", waveColor);
        log.info("极值：= {}", extremeValue);
        log.info("顺子/对子/豹子：= {}", sdb);

        return ExportData.builder()
                .startTime(startTime)
                .period(period)
                .number1(number1)
                .number2(number2)
                .number3(number3)
                .numberSum(numberSum)
                .specialCode1(specialCode1)
                .specialCode2(specialCode2)
                .specialCode3(specialCode3)
                .waveColor(waveColor)
                .extremeValue(extremeValue)
                .sdb(sdb)
                .build();
    }

    private void webDriverWait() throws InterruptedException {
        log.info("--------为了稳定打开网页，请等待10秒--------");
        log.info("--------等待剩余10秒--------");
        Thread.sleep(5000);
        log.info("--------等待剩余5秒--------");
        Thread.sleep(5000);
        log.info("--------已准备就绪--------");
    }
}
