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
import java.util.Date;
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
            bean.setSizeFormat(getPrintSize(Long.parseLong(bean.getSize())));
            bean.setCreateDate(new Date());
            bean.setRecycle("0");
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

    public static String getPrintSize(long size) {

        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        //因为还没有到达要使用另一个单位的时候
        //接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            //因为如果以MB为单位的话，要保留最后1位小数，
            //因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "MB";
        } else {
            //否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "GB";
        }
    }

}
