package com.yss.yunsoso.service;

public interface SolrFacade {
   public String  getResults(String keyword, Integer index);

    String getResults(Integer pageSize);
}
