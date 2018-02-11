package DouBan;

import redis.clients.jedis.Pipeline;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class DBSPipe implements us.codecraft.webmagic.pipeline.Pipeline {
    public void process(ResultItems resultItems, Task task) {
        String savepath = "E:\\爬虫\\";
        List<String> imglist = resultItems.get("imgadd");
        List<String> bookname = resultItems.get("bookname");
        try {
            this.downLoad(imglist,savepath,bookname);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downLoad(List<String> list, String savepath, List<String> bookname) throws Exception {
        for(int i=0;i<list.size();i++)
        {
            URL url = new URL(list.get(i));
            URLConnection con = url.openConnection();
            con.setConnectTimeout(5*1000);
            InputStream is = con.getInputStream();
            byte[] bs = new byte[1024];
            int len;
            File f = new File(savepath);
            if(!f.exists())
            {
                f.mkdirs();
            }

            OutputStream os = new FileOutputStream(f.getPath()+"\\"+bookname.get(i).toString()+".jpg");
            while((len = is.read(bs)) != -1)
            {
                os.write(bs,0,len);
            }
            os.close();
            is.close();
        }

    }
}
