package com.yss.yunsoso.pipline;

import com.yss.yunsoso.dao.YunBeanMapper;
import com.yss.yunsoso.domain.YunBean;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

@Component
public class MysqlPipeline implements Pipeline {

    @Resource
    private YunBeanMapper beanMapper;
    @Autowired
    private SolrClient client;

    @Override
    public void process(ResultItems resultItems, Task task) {
        if (resultItems.get("bean") != null) {
            YunBean bean = (YunBean) resultItems.get("bean");
            toDB(bean);
            toSolr(bean);
        }
    }

    private void toSolr(YunBean bean) {
        try {
            client.addBean(bean);
            client.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }

    private void toDB(YunBean bean) {
        bean.setId(UUID.randomUUID().toString());
        beanMapper.insert(bean);
    }
}
