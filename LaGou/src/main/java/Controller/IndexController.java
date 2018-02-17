package Controller;

import Model.user;
import Service.LoginService;
import Service.osarticleservice;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.bg.PixelBoundryBackground;
import com.kennycason.kumo.font.KumoFont;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.nlp.tokenizers.ChineseWordTokenizer;
import com.kennycason.kumo.palette.ColorPalette;
import com.kennycason.kumo.palette.LinearGradientColorPalette;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import static java.awt.Color.*;

public class IndexController extends Controller {
    public void index()
    {
        render("/amz_15_bdp/html/login.html");
    }

    public void login()
    {
        user u = getModel(user.class);
        LoginService login = new LoginService();
        boolean flag = login.Istrue(u);
        if(flag)
        {
            int i =100;
            setAttr("sum",i);
            setAttr("articles",osarticleservice.findAll(i));
            render("/amz_15_bdp/html/index.html");
           // render("/amz_15_bdp/html/table_complete.html");
        }
        else
        {
            render("/amz_15_bdp/html/login.html");
        }



    }

    public void forgetpwd()
    {
        renderText("忘就忘了吧");
    }

    public void returnArticle()
    {

    }

        public void wordCloud()
    {
        try
        {
            this.produceCloud();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //java词云生成方法
    public void produceCloud() throws IOException
    {
        //建立词频分析器，设置词频，以及词语最短长度
        FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        frequencyAnalyzer.setWordFrequenciesToReturn(600);
        frequencyAnalyzer.setMinWordLength(2);

        //引入中文解析器
        frequencyAnalyzer.setWordTokenizer(new ChineseWordTokenizer());
        final List<WordFrequency> wordFrequencyList = frequencyAnalyzer.load("E:\\爬虫/wordcloud.txt");
        Dimension dimension = new Dimension(1920,1080);
        WordCloud wordCloud = new WordCloud(dimension,CollisionMode.PIXEL_PERFECT);
        wordCloud.setPadding(2);
        java.awt.Font font = new java.awt.Font("STSong-Light", 2, 20);
        wordCloud.setColorPalette(new LinearGradientColorPalette(Color.RED, Color.BLUE, Color.GREEN, 30, 30));
        wordCloud.setKumoFont(new KumoFont(font));
        wordCloud.setBackgroundColor(new Color(255,255,255));
        wordCloud.setBackground(new PixelBoundryBackground("E:\\爬虫/google.jpg"));
//      wordCloud.setBackground(new CircleBackground(255));
        wordCloud.setFontScalar(new SqrtFontScalar(12, 45));
        wordCloud.build(wordFrequencyList);
        wordCloud.writeToFile("E:\\爬虫/wy.png");


    }

}
