package com.yss.yunsoso.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.yss.yunsoso.service.SolrFacade;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SolrFacadeImpl implements SolrFacade {

    @Autowired
    private SolrClient client;

    @Override
    public String getResults(String keyword,Integer index , Integer pageSize) {

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("kw:"+keyword);
        solrQuery.setStart(index);
        solrQuery.setRows(pageSize);
//        solrQuery.setHighlightSnippets(2);// 结果分片数，默认为1
//        solrQuery.setHighlightFragsize(1000);// 每个分片的最大长度，默认为100
        QueryResponse response;
        try {
            response = client.query(solrQuery, SolrRequest.METHOD.POST);
            SolrDocumentList results = response.getResults();

            if(results==null || results.size()==0){ return null;}

            return JSONArray.toJSONString(results);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
