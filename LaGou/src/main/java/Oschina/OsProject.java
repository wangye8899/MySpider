package Oschina;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

public class OsProject implements PageProcessor {
    private static Site site = Site.me().setSleepTime(1000).setRetryTimes(2);

    public void process(Page page) {

        if (page.getUrl().regex("https://www.oschina.net/project/lang/19/java").match()&&page.getUrl().toString().length()<50)
        {

            System.out.println("调用");
            List <String> list =this.generateUrl();
            page.addTargetRequests(list);

        }

        else
        {
            System.out.println("111");
            page.putField("projectlink",page.getHtml().xpath("//div[@class='box-aw']/a/@href").all());
            page.putField("projectname",page.getHtml().xpath("//div[@class='box-aw']/a/div[@class='title']/text()").all());
            page.putField("time",page.getHtml().xpath("//footer[@class='related box']/span[1]/text()").all());
            page.putField("collection",page.getHtml().xpath("//footer[@class='related box']/span[2]/text()").all());
            page.putField("comment",page.getHtml().xpath("//footer[@class='related box']/span[3]/text()").all());
            page.putField("score",page.getHtml().xpath("//footer[@class='related box']/span[4]/text()").all());
            page.putField("picaddress",page.getHtml().xpath("/html/body/section/article/div/section/div/div[3]/div/div[2]/div/div/a/img/@src").all());

        }

    }

        public List<String> generateUrl()
        {
            List<String> list = new ArrayList<String>();
            for(int i=1;i<429;i++)
            {
                list.add("https://www.oschina.net/project/lang/19/java?company=0&sort=score&lang=19&recommend=false&p="+i);
            }
            return list;
        }
        public Site getSite ()
        {
            return site;
        }

        public static void main (String[]argv)
        {
            Spider.create(new OsProject())
                    .addUrl("https://www.oschina.net/project/lang/19/java")
                    //.addPipeline(new OsProjectPipe())
                    .thread(2)
                    .run();
    }
}