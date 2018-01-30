package com.yss.yunsoso.service;

import java.util.Set;

public interface RedisFacade{

    public Boolean addReidisQueue(String key,String value);

    Set<String> getKeyWordFromRedis(String queue);

    //预约关键字已爬取完毕删除
    Boolean delKeyWordFromRedis(String queue,String kw);
}
