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
    public String phantomjsPath;
    @Value("${searchPage}")
    public Integer searchPage;
    @Value("${pageSize}")
    public Integer pageSize;

}
