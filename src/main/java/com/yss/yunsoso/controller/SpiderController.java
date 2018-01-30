package com.yss.yunsoso.controller;

import com.alibaba.fastjson.JSONObject;
import com.yss.yunsoso.service.RedisFacade;
import com.yss.yunsoso.service.SolrFacade;
import com.yss.yunsoso.service.SpiderFacade;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by beyondLi on otherConfig.pageSize17/6/19.
 */
//证明是controller层并且返回json
@Controller
public class SpiderController {

    private static final Logger  logger = org.slf4j.LoggerFactory.getLogger(SpiderController.class);

    @Resource
    SpiderFacade spiderFacade;
    @Autowired
    private SolrFacade solrFacade;
    @Autowired
    private RedisFacade redisFacade;
    @Value("queue")
    private String queue;

    @Resource
    private RedisTemplate redisTemplate;


    @RequestMapping(value = "/find/{kw}/{index}")
    public String find(@PathVariable String kw , @PathVariable Integer index , ModelMap map) {
        String results = solrFacade.getResults(kw, index);
        if(results!=null){
            JSONObject responseObject = JSONObject.parseObject(results);
            map.put("currentPage", responseObject.get("currPage"));
            map.put("totalItem", responseObject.get("total"));
            map.put("results", responseObject.getJSONArray("results"));
            map.put("kw", kw);
            return "/list";
        }else{
            Boolean isok = redisFacade.addReidisQueue(queue, kw);

//            siderFacade.getSpider(kw);
        }


        return "suc";
    }

    @RequestMapping(value = "/reserve/{kw}")
    @ResponseBody
    public String reserve(@PathVariable String kw ,  ModelMap map) {
        Boolean isok = redisFacade.addReidisQueue(queue, kw);

        return "suc";
    }

    @RequestMapping(value = "/findAll/{index}")
    public String findAll(ModelMap map,@PathVariable Integer index) {

        String results = solrFacade.getResults(index);
        JSONObject responseObject = JSONObject.parseObject(results);
        map.put("currentPage", responseObject.get("currPage"));
        map.put("totalItem", responseObject.get("total"));
        map.put("results", responseObject.getJSONArray("results"));


        return "/list";
    }




    @RequestMapping(value = "/index")
    public String index() {
        return "/search";
    }

    @RequestMapping(value = "/list")
    public String list() {
        return "/list";
    }


    @RequestMapping(value = "/redis_test")
    @ResponseBody
    public String redis_test()  {
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add("11", "22", 1.0);
        zSetOperations.incrementScore("11", "xuexusheng", 1.0);
        zSetOperations.incrementScore("11", "xuexusheng2", 4.0);
        zSetOperations.incrementScore("11", "xuexusheng3", 3.0);
        zSetOperations.incrementScore("11", "xuexusheng4", 2.0);

        Set range = zSetOperations.range("11", 0, -1);
        zSetOperations.remove("11","xuexusheng");


        return null;
    }
}