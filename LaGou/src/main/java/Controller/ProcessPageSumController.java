package Controller;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

public class ProcessPageSumController implements PageProcessor {

    private Site site = Site.me().setSleepTime(1000).setRetryTimes(3);
    public void process(Page page)
    {
        List<String> list = new ArrayList<String>();
        list = this.operte();
        page.addTargetRequests(list);
        page.putField("sum",page.getHtml().xpath("//*[@id='order']/li/div[3]/div[3]/span[2]/text()").all());

    }


    public List<String> operte()
    {
        String [] city = {"上海","北京","大连","杭州","成都","西安","天津","沈阳","长春"};
        String url = "https://www.lagou.com/jobs/list_?px=default&gx=全职&gj=&isSchoolJob=1&city=";
        List<String> list = new ArrayList<String>();
        for(int i =0 ;i<city.length;i++)
        {
            city[i] = url+city[i].toString();
            list.add(city[i].toString());
        }
        return list;
    }

    public Site getSite()
    {
        return site;
    }

    public static void main(String [] argv)
    {
        Spider.create(new ProcessPageSumController())
                .addUrl("https://www.lagou.com")
                .thread(2)
                .run();

    }
}
