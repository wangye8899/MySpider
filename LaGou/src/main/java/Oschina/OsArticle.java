package Oschina;

import com.sun.org.apache.regexp.internal.RE;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

public class OsArticle implements PageProcessor {
    private Site site = Site.me().setRetryTimes(2).setSleepTime(100)
            .setDomain("www.osarticle.net")
            .addCookie("Hm_lpvt_a411c4d1664dd70048ee98afe7b28f0b","1517311293")
            .addCookie("Hm_lvt_a411c4d1664dd70048ee98afe7b28f0b","1517280323,1517294024,1517297596,1517311257")
            .addCookie("_user_behavior_","cdf35554-44e4-4361-a13c-f86711da2cdc")
            .addCookie("aliyungf_tc","AQAAAByVsEM3IAcAxuwvakgCPvGmrNjp")
            .addHeader("Accept","*/*")
            .addHeader("Accept-Encoding","gzip, deflate, br")
            .addHeader("Accept-Language","zh-CN,zh;q=0.8")
            .addHeader("Connection","keep-alive")
            .addHeader("Content-Length","0")
            .addHeader("Host","www.oschina.net")
            .addHeader("Origin","https://www.oschina.net")
            .addHeader("Referer","https://www.oschina.net/blog")
            .addHeader("User-Agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3095.5 Mobile Safari/537.36")
            .addHeader("X-Requested-With","XMLHttpRequest");
    static int i = 0;
    static List<us.codecraft.webmagic.Request> Authorlist = null;
    public void process(Page page)
    {

            System.out.println("开始爬取");
            List<String> url = new ArrayList<String>();
             for(int i=1;i<502;i++)
             {
             url.add("https://www.oschina.net/action/ajax/get_more_recommend_blog?classification=0"+"&p="+i);
             }
             page.addTargetRequests(url);

            Authorlist =  page.getTargetRequests();
            System.out.println("调用");
            page.putField("authorname",page.getHtml().xpath("//div[@class='box item']//div[@class='box-fl']/a/img/@alt").all());
            page.putField("authorlink",page.getHtml().xpath("//div[@class='box-fl']/a/@href").all());
            page.putField("articlelink",page.getHtml().xpath("//a[@class='sc overh blog-title-link']/@href").all());
            page.putField("articletitle",page.getHtml().xpath("//a[@class='sc overh blog-title-link']/h2/text()").all());
            page.putField("time",page.getHtml().xpath("//div[@class='box-aw']/footer/span[3]/text()").all());
            page.putField("readingsum",page.getHtml().xpath("//div[@class='box-aw']/footer/span[4]/text()").all());
            page.putField("introduction",page.getHtml().xpath("//div[@class='box-aw']/section/text()").all());

    }


    public List<String> ProcessUrl()
    {
        List<String> list = new ArrayList<String>();

        for(int i=1;i<505;i++)
        {
            list.add("https://www.oschina.net/action/ajax/get_more_recommend_blog?classification=0"+"&p="+i);

        }
        return list;
    }
    public Site getSite()
    {
        return site;
    }
    public static void main(String [] argv)
    {
//        Spider.create(new OsArticle())
//                    .addUrl("https://www.oschina.net/")
//                .addPipeline(new OsArticlePipe())
//                .thread(2)
//                .run();
//        System.out.println("爬取结束");

    }
}
