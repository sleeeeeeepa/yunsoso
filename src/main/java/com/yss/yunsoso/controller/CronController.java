package com.yss.yunsoso.controller;

import com.yss.yunsoso.service.RedisFacade;
import com.yss.yunsoso.service.SpiderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Set;

@Controller
public class CronController {

    @Resource
    SpiderFacade spiderFacade;
    @Autowired
    private RedisFacade redisFacade;
    @Value("queue")
    private String queue;

    @RequestMapping(value = "/do_spider")
    @ResponseBody
    public String doSpider() {
        Set<String> kwSet = redisFacade.getKeyWordFromRedis(queue);
        for (String kw : kwSet) {
            spiderFacade.getSpider(kw);
            //搜索完毕从队列中删除关键字
            redisFacade.delKeyWordFromRedis(queue, kw);

            Date date = new Date();
            if (date.getHours() > 6) {
                return "由于时间停止";
            }
        }
        return "爬取完毕停止";
    }
}
