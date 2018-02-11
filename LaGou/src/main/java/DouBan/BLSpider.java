package DouBan;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BLSpider implements Pipeline {

    static int j =1;
    public void process(ResultItems resultItems, Task task)
    {
        List<String> name = resultItems.get("videoname");
        List<String> link = resultItems.get("videolink");
        String savepath = "E:\\爬虫\\b站会声会影视频链接爬虫/bilibiliSpider.txt";
        this.writeTxt(savepath,name,link);

    }

    public void writeTxt(String savepath,List<String> name,List<String> link) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(savepath,true);
            for (int i = 0; i < name.size(); i++)
            {
                fileWriter.write(j+"、"+"  "+name.get(i).toString() + ":" + link.get(i).toString());
                fileWriter.write("\r\n");
                j++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }
}
