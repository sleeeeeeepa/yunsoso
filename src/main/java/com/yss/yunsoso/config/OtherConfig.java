package com.yss.yunsoso.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource("classpath:conf/other-conf.properties")
public class OtherConfig {
    @Value("${phantomjsPath}")
    public String phantomjsPath;//phantomjs 路径
    @Value("${searchPage}")
    public Integer searchPage; //爬取百度前几页
    @Value("${pageSize}")
    public Integer pageSize;    //结果页每页显示条数
    @Value("${pictureFormat}")
    public String pictureFormat;    //图片格式

}
