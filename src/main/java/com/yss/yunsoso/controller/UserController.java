package com.yss.yunsoso.controller;

import com.yss.yunsoso.dao.YunBeanMapper;
import com.yss.yunsoso.service.SpiderFacade;
import com.yss.yunsoso.utils.RedisClient;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * Created by beyondLi on 2017/6/19.
 */
//证明是controller层并且返回json
@Controller
public class UserController {
    @Resource
    SpiderFacade spderfaFacadeService;
    @Resource
    RedisClient redisClient;
    @Resource
    JedisPool jedisPool;

    @Resource
    private YunBeanMapper beanMapper;

    @RequestMapping(value = "/find/{kw}")
    public String cs(@PathVariable String kw) {
        //调用dao层
        spderfaFacadeService.getSpider(kw);
        return "/search";
    }


    @RequestMapping(value = "/index")
    public String index() {
        return "/search";
    }

    @RequestMapping(value = "/redis_test")
    @ResponseBody
    public String redis_test() throws Exception {
        Jedis jedis = jedisPool.getResource();
        jedis.sadd("incremental","659826","365841");
        jedis.sadd("incremental","789654","3215674");
        Boolean incremental = jedis.sismember("incremental", "56");
        return "";
    }
}