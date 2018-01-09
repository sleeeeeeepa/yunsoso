package com.yss.yunsoso.spdier;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.yss.yunsoso.domain.YunBean;
import org.apache.http.protocol.HTTP;
import org.jsoup.nodes.Element;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.PhantomJSDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;


@Component
public class BaiduYunFindFileFromBaidu implements PageProcessor {

    private Site site = Site.me()
            .setDomain("pan.baidu.com/")
            .setSleepTime(1000)
            .setUserAgent(
                    "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36")
            .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
            .addHeader("Accept-Language","zh-CN,zh;q=0.9")
            .addHeader("Connection","keep-alive")
            .addHeader("Host","www.baidu.com")
            .addHeader("Upgrade-Insecure-Requests","1")
            .addHeader("Cookie","BAIDUID=D3CF67F8FAB389C2953BEE6C64DB9585:FG=1; BIDUPSID=D3CF67F8FAB389C2953BEE6C64DB9585; PSTM=1504325830; PANWEB=1; Hm_lvt_9d483e9e48ba1faa0dfceaf6333de846=1511274755,1511274922,1511275316,1511695518; BDUSS=21zbzhtUzNVWGhxT2RZfnN1UkVDM2FoZDU0eUxmT3hhTlRPY2hLWFdOU2E2VkphQVFBQUFBJCQAAAAAAAAAAAEAAAAtWblAterQobb-2K~YvAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAJpcK1qaXCtad; STOKEN=1b9bcaaa2f589bf44c952a87ad3220565a86197c129ac859b9433729472e1205; SCRC=00ff4312bc9dc5eb81d78315ca1e0225; MCITY=-%3A; __cfduid=d57f7bb4dead8fce5a71718ff9050dec51514814269; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; cflag=15%3A3; BDCLND=MOVLBixQAHxTGZOVIuVtIEowC2SBCIuk; PSINO=2; H_PS_PSSID=1436_21119_17001_20929; Hm_lvt_7a3960b6f067eb0085b7f96ff5e660b0=1515029538,1515029557,1515029563,1515029638; Hm_lpvt_7a3960b6f067eb0085b7f96ff5e660b0=1515029668; PANPSC=4638995888638665119%3AnSvEWpGhaFoeORq07AOFwen90oj%2BY%2FIsSK33LsWGbKHATV5Z7asqnkri4Mor44rD4z5GoXa%2Bqx7xTJ3v1z1m%2BltYlFVeDCX4XGY8gqYUtUX%2B1dbZdUK1tvkDvA%2Br0ePvLPY%2BF1C2z9ca2YA4NkcJCNnR6ytx85nJ%2B%2Bo8ht7g5D2LIWeznIUCtSkA0KYPulwlfkiVl%2Ff%2FRfx4kU4b9dv9by76Oxw8WnAw")
            ;

    /*-----------成员变量------------------*/
    //电影名称--》GN2312 --》URLEncode   使用在贴吧列表翻页时的参数
    public static String moviesNameURLEncodeByGB2312;

    /*-----------错误提示--------------*/
    public static List<String> errPage = Arrays.asList( new String[]{"无法访问","删除","没找到文件","取消"});
    public static List<String> sucPage = Arrays.asList( new String[]{"mp4","rm","rmvb","wmv","avi","mkv"});

    private YunBean spiderBean = null;
    private Page page = null ;

    String PAN_BAIDU_COM = "://pan.baidu.com";

    public static void main(String[] args) {
        String s = "复仇者联盟";
        String moviesName = "\""+s+"\" ";
        String postfix = "\"pan.baidu.com\"";
        try {
            moviesName = URLEncoder.encode(moviesName, "gb2312");
            postfix = URLEncoder.encode(postfix, "gb2312");
            System.out.println(moviesName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Spider.create(new
                BaiduYunFindFileFromBaidu())
                .addUrl("https://www.baidu.com/s?wd="+moviesName+postfix+"&pn=0")
                .addUrl("https://www.baidu.com/s?wd="+moviesName+postfix+"&pn=10")
                .setDownloader(new PhantomJSDownloader("E:\\idea_workspace\\tieba\\phantomjs.exe"))
                .run();
    }


    public Site getSite() {
        return site;
    }


    public void process(Page page) {
        this.page = page;
        Html html = page.getHtml();

        //百度云
        if(page.getUrl().regex(PAN_BAIDU_COM).match()){
            //校验是否错误
            if(!yunIsSuccess(html)){
                return;
            }

            //循环将找到的文件夹链接放到队列
            firstYunList(html);
        }else{
            addURLByNotYun(html);
        }
    }


    //百度云地址是否错误
    public boolean yunIsSuccess(Html html){

        //错误页面提示框
        Selectable share_nofound_des = html.xpath("//*[@id=\"share_nofound_des\"]");
        //-----循环错误信息对比
        for (String errMes : errPage) {
            if(share_nofound_des.regex(errMes).match()){
                System.out.println("错误页面///////////////");
                page.setSkip(true);
                return false;
            }
        }
        return true;
    }


    //文件夹内部数据，ajax
    public void dir(Html html){

        try {
            if(html.xpath("//*[@class=\"share-error error-box\"]").match()){
                return;
            }
            Element body = html.getDocument().body();
            String html1 = body.html();

            JSONArray fileInfo = null;
            try {
                JSONObject jsonObject1 = JSONObject.parseObject(html1);
                fileInfo = jsonObject1.getJSONArray("list");
            } catch (JSONException e) {     //有时进来的不是json带了样式代码  此时截取
                html1 = html.regex("\\{.*\\}").get();
                try {
                    JSONObject jsonObject1 = JSONObject.parseObject(html1);
                    fileInfo = jsonObject1.getJSONArray("list");
                } catch (Exception e1) {        //依然无法转换报错
                    return;
                }
            }

            for (Object o : fileInfo) {
                JSONObject jsonObject = JSONObject.parseObject(o.toString());
                if(jsonObject.getInteger("isdir")==1){
                    if(page.getUrl().get().contains("list?uk=")){
                        String url = page.getUrl().regex("https.*dir=").get();
                        page.addTargetRequest(url+jsonObject.getString("path"));
                    }
                }else if (jsonObject.getInteger("isdir")==0){
                    YunBean bean = new YunBean();
                    bean.setFileName(jsonObject.getString("server_filename"));
//                    bean.setAuthor();
                    bean.setSize(getPrintSize(jsonObject.getLong("size")));
                    bean.setUrl(page.getUrl().get().replaceFirst("list","link"));
                    System.out.println(bean);
                    insertDB(bean);
                }
            }
        }catch (NullPointerException e) {
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    //首次进入百度云列表
    public void  firstYunList(Html html){
        try {
            Selectable regex = html.regex("yunData.FILEINFO = .*?];");


            //判断是否是进入百度云列表且为首次，yunData.FILEINFO（首次）
            if(regex.match() && page.getUrl().get().contains(PAN_BAIDU_COM)){
                String fileInfo_s = html.regex("yunData.FILEINFO = .*?];").replace("yunData.FILEINFO =", "").replace(";", "").get().trim();
                JSONArray fileInfo = JSONArray.parseArray(fileInfo_s);
                String share_id  = html.regex("yunData.SHARE_ID .*?;").replace("yunData.SHARE_ID =", "").replace(";", "").replace("\"","").get().trim();
                String uk_id  = html.regex("yunData.SHARE_UK .*?;").replace("yunData.SHARE_UK =", "").replace(";", "").replace("\"","").get().trim();

                for (Object o : fileInfo) {
                    JSONObject jsonObject = JSONObject.parseObject(o.toString());
                    if(jsonObject.getInteger("isdir")==1){
                        String url = "https://pan.baidu.com/share/list?uk="+uk_id+"&shareid="+share_id+"&dir=";
                        page.addTargetRequest(url+jsonObject.getString("path"));
                    }else if (jsonObject.getInteger("isdir")==0){
                        YunBean bean = new YunBean();
                        bean.setFileName(jsonObject.getString("server_filename"));
                        //                    bean.setAuthor();
                        bean.setSize(getPrintSize(jsonObject.getLong("size")));
                        bean.setUrl(page.getUrl().get().replaceFirst("list","link"));
                        System.out.println(bean);
                        insertDB(bean);
                        break;
                    }
                }
            }else if(html.xpath("//*[@id=\"kcih9Yxx\"]/div[2]/form/div[2]/dl[1]/dt").match()){      //加密  直接输出，后期获取密码
                System.out.println(page.getUrl().get().replaceFirst("list","link"));

            }else if(html.regex("yunData.setData.*?];").match()){   //直接播放
                Selectable regex1 = html.regex("yunData.setData.*?\\);");
                if (regex1 != null) {
                    String setData_S = regex1.get().replace("yunData.setData(", "").replace(")","").replace(";","").trim();
                    JSONObject setData_json = JSONObject.parseObject(setData_S);
                    JSONArray file_list = setData_json.getJSONObject("file_list").getJSONArray("list");

                    YunBean bean = new YunBean();
                    for (Object o : file_list) {
                        JSONObject o1 = JSONObject.parseObject(o.toString());
                        bean.setSize(getPrintSize(o1.getLong("size")));
                        bean.setFileName(o1.getString("server_filename"));

                    }
                    bean.setUrl(page.getUrl().get().replaceFirst("list","link"));
                    System.out.println(bean);
                    insertDB(bean);
                    return;
                }
            }else if(!regex.match() && page.getUrl().get().contains(PAN_BAIDU_COM)){  //非首次进入 但地址为百度云
                String s = page.getUrl().get();
                dir(html);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private BaiduyunSpiderBean getSpiderBean(){
//        if(spiderBean == null){
//            return new BaiduyunSpiderBean();
//        }
//        return  spiderBean;
//    }

    private String encode(String s){
        try {
            return URLEncoder.encode(s, "gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
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

    //非百度云页面 ， 方法目标爬取其中百度云链接
    public void addURLByNotYun(Html html){

        Selectable links = html.links();
        if(html.xpath("//*[@id=\"content_left\"]").match()){
            //去重
//            List<String> all = html.xpath("//*[@id=\"content_left\"]").nodes().regex("http://www.baidu.com/link?.*").all();
            List<Selectable> nodes = html.xpath("//*[@id=\"content_left\"]/div").nodes();
            for (Selectable node : nodes) {
                List<String> all = node.xpath("//*[@class=\"t\"]").links().regex("http://www.baidu.com/link?.*").all();
                page.addTargetRequests(all);
            }
        }else{
            page.addTargetRequests(links.regex(".*://pan.baidu.com.*").all());
        }
    }

    public static void runNewsList(List<String> strList){
        for(String str:strList){
            Spider.create(new BaiduYunFindFileFromBaidu()).addUrl(str).thread(1).run(); //添加爬取地址、设置线程数
        }
    }

    public void insertDB(YunBean bean){
        page.putField("bean",bean);
    }


}
