package com.yss.yunsoso.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yss.yunsoso.config.OtherConfig;
import com.yss.yunsoso.controller.UserController;
import com.yss.yunsoso.service.SolrFacade;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class SolrFacadeImpl implements SolrFacade {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(SolrFacadeImpl.class);

    @Autowired
    private SolrClient client;
    @Value("${pageSize}")
    private Integer pageSize ;

    @Override
    public String getResults(String keyword,Integer index) {

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("kw:"+keyword);
        int start = index * pageSize;
        solrQuery.setStart(start);
        solrQuery.setRows(pageSize);
//        solrQuery.setHighlightSnippets(2);// 结果分片数，默认为1
//        solrQuery.setHighlightFragsize(1000);// 每个分片的最大长度，默认为100
        QueryResponse response;
        try {
            response = client.query(solrQuery, SolrRequest.METHOD.POST);
            SolrDocumentList results = response.getResults();
            if(results==null || results.size()==0){
                logger.debug("======== 关键字"+keyword+"未储存数据 =========");
                return null;
            }
            JSONObject object = new JSONObject();
            object.put("results",JSONArray.toJSONString(results));
            object.put("total",results.getNumFound());
            object.put("currPage",index);

            return JSONObject.toJSONString(object);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getResults(Integer index) {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        solrQuery.setStart(index * pageSize);
        solrQuery.setRows(pageSize);
//        solrQuery.setHighlightSnippets(2);// 结果分片数，默认为1
//        solrQuery.setHighlightFragsize(1000);// 每个分片的最大长度，默认为100
        QueryResponse response;
        try {
            response = client.query(solrQuery, SolrRequest.METHOD.POST);
            SolrDocumentList results = response.getResults();

            if(results==null || results.size()==0){ return null;}
            JSONObject object = new JSONObject();
            object.put("results",JSONArray.toJSONString(results));
            object.put("total",results.getNumFound());
            object.put("kw",results.getNumFound());
            object.put("currPage",index);
            return JSONObject.toJSONString(object);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
