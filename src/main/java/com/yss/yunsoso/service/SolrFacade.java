package com.yss.yunsoso.service;

public interface SolrFacade {
   public String  getResults (String keyword,Integer index , Integer pageSize);

    String getResults(Integer pageSize, Integer size);
}
