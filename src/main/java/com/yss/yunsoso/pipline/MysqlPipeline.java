package com.yss.yunsoso.pipline;

import com.yss.yunsoso.dao.YunBeanMapper;
import com.yss.yunsoso.domain.YunBean;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.Random;
import java.util.UUID;

@Component
public class MysqlPipeline implements Pipeline {

    @Resource
    private YunBeanMapper beanMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        if (resultItems.get("bean") != null) {
            YunBean bean = (YunBean) resultItems.get("bean");
            bean.setId(UUID.randomUUID().toString());
            beanMapper.insert(bean);
        }
    }
}
