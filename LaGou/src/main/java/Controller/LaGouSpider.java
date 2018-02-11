package Controller;

import us.codecraft.webmagic.*;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.scheduler.DuplicateRemovedScheduler;
import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.utils.HttpConstant;
import java.util.*;


public class LaGouSpider  implements PageProcessor {
    int flag = 0;
    int mark = 0;
    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .addHeader("Accept","application/json, text/javascript, */*; q=0.01")
            .addHeader("Accept-Encoding","gzip, deflate, br")
            .addHeader("Accept-Language","zh-CN,zh;q=0.8")
            .addHeader("Connection","keep-alive")
            //.addHeader("Content-Length","23")
            .addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
            .addHeader("Cookie","Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1516684224,1516688332,1516708458,1517989813; _ga=GA1.2.803780703.1515996477; user_trace_token=20180115140756-6e315eee-f9ba-11e7-a353-5254005c3644; LGUID=20180115140756-6e316229-f9ba-11e7-a353-5254005c3644; index_location_city=%E5%85%A8%E5%9B%BD; JSESSIONID=ABAAABAAADEAAFI7B8A950147564B82F61A115D162E1281; LGSID=20180207155015-888d0972-0bdb-11e8-bdd2-525400f775ce; LGRID=20180207163606-f07d2f3d-0be1-11e8-af98-5254005c3644; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1517992563; TG-TRACK-CODE=index_navigation; SEARCH_ID=ada31aea74d74f0ba5625adf851d1c6f; X_HTTP_TOKEN=4235610f3926fcdc9a4b942f0c350399; _putrc=0DA9DA012C722A8E; login=true; unick=%E7%8E%8B%E5%86%B6; showExpriedIndex=1; showExpriedCompanyHome=1; showExpriedMyPublish=1; hasDeliver=0; gate_login_token=fc49718b5340e22bfe7adebb2937015b765f94906d1f154c; _gat=1")
            .addHeader("Host","www.lagou.com")
            .addHeader("Origin","https://www.lagou.com")
            .addHeader("Referer","https://www.lagou.com/jobs/list_Java")
            .addHeader("User-Agent","-Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3095.5 Mobile Safari/537.36")
            .addHeader("X-Anit-Forge-Code","0")
            .addHeader("X-Anit-Forge-Token","None")
            .addHeader("X-Requested-With","XMLHttpRequest");

    public void process(Page page)
    {

        this.processBeiJing(page);
//        this.processTianJin(page);
        page.putField("position",new JsonPathSelector("$.content.positionResult.result[*].positionName").selectList(page.getRawText()));

    }




    public static void main(String []argv)
    {
          Spider.create(new LaGouSpider())
                .addUrl("https://www.lagou.com/jobs/positionAjax.json?px=default&city=北京&needAddtionalResult=false&isSchoolJob=0")
                .thread(2)
                .run();
    }

    //爬取北京的java职位信息
    public void processBeiJing(Page page)
    {
        if(flag==0)
        {

            Request [] requests = new Request[30];
            Map<String,Object> map = new HashMap<String, Object>();
            for(int i=0;i<requests.length;i++)
            {
                requests[i] = new Request("https://www.lagou.com/jobs/positionAjax.json?px=default&city=北京&needAddtionalResult=false&isSchoolJob=0");
                requests[i].setMethod(HttpConstant.Method.POST);
                if(i==0)
                {
                    map.put("first","true");
                    map.put("pn",i+1);
                    map.put("kd","java");
                    requests[i].setRequestBody(HttpRequestBody.form(map,"utf-8"));
                    page.addTargetRequest(requests[i]);
                }
                else
                {
                    map.put("first","false");
                    map.put("pn",i+1);
                    map.put("kd","java");
                    requests[i].setRequestBody(HttpRequestBody.form(map,"utf-8"));
                    page.addTargetRequest(requests[i]);
                }
            }

            flag++;
        }
    }
    //爬取天津的java职位信息
    public void processTianJin(Page page)
    {
        if(mark==0)
        {
            Request [] requests = new Request[9];
            Map<String,Object> map = new HashMap<String, Object>();
            for(int i =0 ;i<requests.length;i++)
            {
                requests[i] = new Request("https://www.lagou.com/jobs/positionAjax.json?px=default&city=天津&needAddtionalResult=false&isSchoolJob=0");
                if(mark==0)
                {
                    map.put("first",true);
                    map.put("pn",i+1);
                    map.put("kd","java");
                    requests[i].setMethod(HttpConstant.Method.POST);
                    requests[i].setRequestBody(HttpRequestBody.form(map,"utf-8"));
                    page.addTargetRequest(requests[i]);

                }
                else
                {
                    map.put("first",false);
                    map.put("kd","java");
                    map.put("pn",i+1);
                    requests[i].setMethod(HttpConstant.Method.POST);
                    requests[i].setRequestBody(HttpRequestBody.form(map,"utf-8"));
                    page.addTargetRequest(requests[i]);
                }
            }
            mark++;
        }
    }
    public Site getSite()
    {
        return site;
    }


}

