package com.yss.yunsoso.controller;

import com.yss.yunsoso.dao.YunBeanMapper;
import com.yss.yunsoso.service.SpiderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by beyondLi on 2017/6/19.
 */
//证明是controller层并且返回json
@Controller
public class UserController {
    @Resource
    SpiderFacade spderfaFacadeService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
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
        SetOperations setOperations = redisTemplate.opsForSet();//操作set
        Long add = setOperations.add("shuibei","xxs");
        System.out.println(setOperations.isMember("shuibei", "xxs"));
        System.out.println(setOperations.isMember("shuibei", "xxs2"));
        return "";
    }
}