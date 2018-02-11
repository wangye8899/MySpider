package os;


import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;



public class OsArticle implements PageProcessor {
    private Site site = Site.me().setRetryTimes(2).setSleepTime(1000);
//            .addCookie("BAIDUID", "66F6F0685731E940ACDD0D8485CCED80:FG=1")
//            .addCookie("BIDUPSID", "66F6F0685731E940ACDD0D8485CCED80")
//            .addCookie("HMACCOUNT", "20D248323A465713")
//            .addCookie("HMVT", "6bcd52f51e9b3dce32bec4a3997715ac|1517281387|")
//            .addCookie("H_PS_PSSID", "25641_1454_21079_22160")
//            .addCookie("Hm_lpvt_a411c4d1664dd70048ee98afe7b28f0b", "1517288005")
//            .addCookie("Hm_lvt_a411c4d1664dd70048ee98afe7b28f0b", "1517279638,1517280172,1517280221,1517280323")
//            .addCookie("PSINO", "1")
//            .addCookie("PSTM", "1517281349")
//            .addCookie("_reg_key_", "tgUCGE4CoPOl1IH5P3D2")
//            .addCookie("_user_behavior_", "cdf35554-44e4-4361-a13c-f86711da2cdc")
//            .addCookie("aliyungf_tc", "AQAAAPM0LmnkfwoAxuwvarDfbCSS4/pn")
//            .addCookie("aliyungf_tc", "AQAAANXCtzWN3wgAxuwvai8QTQ5rc+H2")
//            .addCookie("Hm_lpvt_a411c4d1664dd70048ee98afe7b28f0b", "1517288377")
//            .addCookie("Hm_lvt_a411c4d1664dd70048ee98afe7b28f0b", "1517279638,1517280172,1517280221,1517280323")
//            .addCookie("_reg_key_", "tgUCGE4CoPOl1IH5P3D2")
//            .addCookie("_user_behavior_", "cdf35554-44e4-4361-a13c-f86711da2cdc")
//            .addCookie("aliyungf_tc", "AQAAANXCtzWN3wgAxuwvai8QTQ5rc+H2")
//            .addCookie("oscid", "BYWQSnPnkWmeTLruwWemFrBUZ%2FbL0yuQdy7OUFowbGd2Twf%2FWTEcKP3ohBCtS1yJupQeer63q9nOVHHOJW8Kq1%2FC1HHXD5DoWjE9Gk4YKYdyojsfXUVhEePukG7bb0Ef%2BgZ61je4CwL3BeJp6OcEnFp%2BJNjCr9Bm6wjL1FTgO0c%3D")
//            .setUserAgent("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3095.5 Mobile Safari/537.36")
//            .addHeader("Accept", "application/json")
//            .addHeader("Accept-Encoding", "gzip, deflate, br")
//            .addHeader("Accept-Language", "zh-CN,zh;q=0.8")
//            .addHeader("Connection", "keep-alive")
//            .addHeader("Content-Length", "70")
//            .addHeader("Content-Type", "application/x-www-form-urlencoded")
//            .addHeader("Host", "www.oschina.net")
//            .addHeader("Origin", "https://www.oschina.net")
//            .addHeader("Referer", "https://www.oschina.net/home/login?goto_page=https%3A%2F%2Fwww.oschina.net%2Fblog");

    static int i = 0;
    List<String> Authorlist = null;

    public void process(Page page) {

        if (page.getUrl().toString().equals("https://www.oschina.net/action/ajax/get_more_recommend_blog?classification=0&p=1")) {

            List<String> url = new ArrayList<String>();
            url = this.ProcessUrl();
            for(int i=0;i<url.size();i++)
            {
                System.out.println(url.get(i).toString());
            }
            page.addTargetRequests(url);

        }
        else
        {
            i++;
            page.putField("authorname", page.getHtml().xpath("//div[@class='box item']//div[@class='box-fl']/a/img/@alt").all());
            page.putField("authorlink", page.getHtml().xpath("//div[@class='box-fl']/a/@href").all());
            page.putField("articlelink", page.getHtml().xpath("//a[@class='sc overh blog-title-link']/@href").all());
            page.putField("articletitle", page.getHtml().xpath("//a[@class='sc overh blog-title-link']/h2/text()").all());
            page.putField("time", page.getHtml().xpath("//div[@class='box-aw']/footer/span[3]/text()").all());
            page.putField("readingsum", page.getHtml().xpath("//div[@class='box-aw']/footer/span[4]/text()").all());
            page.putField("introduction", page.getHtml().xpath("//div[@class='box-aw']/section/text()").all());
        }
//        else if (page.getUrl().regex("https://www\\.osarticle\\.net/action/ajax/get_more_recommend_blog\\?classification=0&p=\\d*").match()) {
//
//            i++;
//            page.putField("authorname", page.getHtml().xpath("//div[@class='box item']//div[@class='box-fl']/a/img/@alt").all());
//            page.putField("authorlink", page.getHtml().xpath("//div[@class='box-fl']/a/@href").all());
//            page.putField("articlelink", page.getHtml().xpath("//a[@class='sc overh blog-title-link']/@href").all());
//            page.putField("articletitle", page.getHtml().xpath("//a[@class='sc overh blog-title-link']/h2/text()").all());
//            page.putField("time", page.getHtml().xpath("//div[@class='box-aw']/footer/span[3]/text()").all());
//            page.putField("readingsum", page.getHtml().xpath("//div[@class='box-aw']/footer/span[4]/text()").all());
//            page.putField("introduction", page.getHtml().xpath("//div[@class='box-aw']/section/text()").all());
//        }
    }


    public List<String> ProcessUrl() {
        List<String> list = new ArrayList<String>();
        for (int i = 1; i < 3; i++) {
            list.add("https://www.osarticle.net/action/ajax/get_more_recommend_blog?classification=0" + "&p=" + i);
        }
        return list;
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args   ) {
        Spider.create(new OsArticle()).addUrl("https://www.oschina.net/action/ajax/get_more_recommend_blog?classification=0&p=1")
                .run();
        System.out.println(Spider.Status.Running);



    }
}

