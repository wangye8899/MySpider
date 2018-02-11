package Controller;

import Oschina.OsArticle;
import Oschina.OsArticlePipe;
import com.jfinal.core.Controller;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

public class OsArticleController extends Controller implements PageProcessor
{
    private Site site = Site.me().setRetryTimes(2).setSleepTime(100);

    public void process(Page page) {
        if(page.getUrl().toString().equals("https://www.oschina.net/"))
        {
            List<String> list = this.ProcessUrl();


               page.addTargetRequests(list);

        }
        else
        {
            System.out.println("开始爬取");
            page.putField("authorname",page.getHtml().xpath("//div[@class='box item']//div[@class='box-fl']/a/img/@alt").all());
            page.putField("authorlink",page.getHtml().xpath("//div[@class='box-fl']/a/@href").all());
            page.putField("articlelink",page.getHtml().xpath("//a[@class='sc overh blog-title-link']/@href").all());
            page.putField("articletitle",page.getHtml().xpath("//a[@class='sc overh blog-title-link']/h2/text()").all());
            page.putField("time",page.getHtml().xpath("//div[@class='box-aw']/footer/span[3]/text()").all());
            page.putField("readingsum",page.getHtml().xpath("//div[@class='box-aw']/footer/span[4]/text()").all());
            page.putField("introduction",page.getHtml().xpath("//div[@class='box-aw']/section/text()").all());

        }

    }
    public List<String> ProcessUrl()
    {
        List<String> list = new ArrayList<String>();

        for(int i=1;i<503;i++)
        {
            list.add("https://www.oschina.net/action/ajax/get_more_recommend_blog?classification=0"+"&p="+i);

        }
        return list;
    }

    public Site getSite() {
        return site;
    }

    public void runSpider()
    {

        Spider.create(new OsArticleController())
                .addUrl("https://www.oschina.net/")
                .addPipeline(new OsArticlePipe())
                .thread(2)
                .run();
        render("/startspider.html");
    }
}
