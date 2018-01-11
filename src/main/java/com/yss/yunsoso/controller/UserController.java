package com.yss.yunsoso.controller;

import com.alibaba.fastjson.JSONArray;
import com.yss.yunsoso.config.OtherConfig;
import com.yss.yunsoso.dao.YunBeanMapper;
import com.yss.yunsoso.service.SolrFacade;
import com.yss.yunsoso.service.SpiderFacade;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by beyondLi on 2017/6/19.
 */
//证明是controller层并且返回json
@Controller
public class UserController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    SpiderFacade spderfaFacade;
    @Autowired
    private SolrFacade solrFacade;
    @Resource
    private OtherConfig otherConfig;
    @Resource
    private YunBeanMapper beanMapper;

    @Autowired
    private SolrClient client;


    @RequestMapping(value = "/find/{kw}/{index}")
    public String cs(@PathVariable String kw , @PathVariable Integer index , ModelMap map) {

        String results = solrFacade.getResults(kw, index, otherConfig.pageSize);
        if(results!=null){
            map.put("results",JSONArray.parseArray(results));
        }else
            spderfaFacade.getSpider(kw);

        //调用爬虫
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
    public String redis_test() throws Exception {
        return null;
//
    }
}