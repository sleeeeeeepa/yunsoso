package com.yss.yunsoso.controller;

import com.yss.yunsoso.dao.YunBeanMapper;
import com.yss.yunsoso.service.SpiderFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}