package com.kj3650.util;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author fanchengheng
 * @date 2023/7/9 10:12
 */

@Slf4j
@Component
@Configuration
public class WebDriverUtils {


    @Value("${kj3650.chromedriver}")
    private String chromedriver;

    @Bean("webDriver")
    public WebDriver webDriverInit() {
        log.info("--------正在初始化--------");
        System.setProperty("webdriver.chrome.driver", chromedriver);
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        ChromeOptions options = new ChromeOptions();
        // 无图像化界面
        options.setHeadless(true);
        // 忽略证书告警
        options.addArguments("--ignore-certificate-errors");
        // root 权限
        options.addArguments("--no-sandbox");
        // 不加载图片
        options.addArguments("blink-settings=imagesEnabled=false");
        // 关闭开发者模式
        options.addArguments("--disable-dev-shm-usage");
        // 关闭GPU
        options.addArguments("--disable-gpu");
        WebDriver webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return webDriver;
    }

}
