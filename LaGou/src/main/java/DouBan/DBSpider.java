package DouBan;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class DBSpider implements PageProcessor {
    private Site site = Site.me().setSleepTime(1000).setRetryTimes(3);
    public void process(Page page)
    {
        if (page.getUrl().regex("https://book\\.douban\\.com/top250\\?start=\\d*").match())
        {
            page.putField("bookname",page.getHtml().xpath("//div[@class='pl2']/a/text()").all());
            page.putField("booklink",page.getHtml().xpath("//div[@class='pl2']/a/@href").all());
            page.putField("introduction",page.getHtml().xpath("//div[@class='pl2']/span/text()").all());
            page.putField("news",page.getHtml().xpath("//p[@class='pl']/text()").all());
            page.putField("imgadd",page.getHtml().xpath("//a[@class='nbg']/img/@src").all());
            page.addTargetRequests(page.getHtml().xpath("//*[@id='content']/div/div[1]/div/div/span[3]/a").links().all());
        }
    }

    public Site getSite()
    {
        return site;
    }

    //下载图片的方法


    public static void main(String [] argv)
    {
        String savepath = "E:\\爬虫\\";
        Spider.create(new DBSpider())
                .addPipeline(new DBSPipe())
                .addUrl("https://book.douban.com/top250?start=0")
                .thread(3)
                .run();
    }

}
