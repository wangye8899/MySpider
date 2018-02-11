package Oschina;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OsArticlePipe implements Pipeline
{
    static  int sum =0;
    public void process(ResultItems resultItems, Task task)
    {
        List<String> namelist = resultItems.get("authorname");
        List<String> list = resultItems.get("authorlink");
        List<String> articlelinklist = resultItems.get("articlelink");
        List<String> articletitlelist = resultItems.get("articletitle");
        List<Integer> readsum = this.processReadSum(resultItems,task);
        List<String> introductionlist = resultItems.get("introduction");
        List<String> timelist = this.processTime(resultItems,task);


        for (int a =0;a<namelist.size();a++)
        {
            sum++;
            System.out.println(namelist.get(a)+sum);
            System.out.println(articletitlelist.get(a));
            System.out.println(articlelinklist.get(a));
            System.out.println(readsum.get(a));
            System.out.println(introductionlist.get(a));
            System.out.println(timelist.get(a));
            System.out.println("-----------------------------------------------------------------");
            Record osarticle = new Record()
                    .set("auhorname",namelist.get(a).toString())
                    .set("authorlink",list.get(a).toString())
                    .set("articlelink",articlelinklist.get(a).toString())
                    .set("articletitle",articletitlelist.get(a).toString())
                    .set("publication",timelist.get(a).toString())
                    .set("readingsum",readsum.get(a))
                    .set("introduction",introductionlist.get(a).toString().substring(0,introductionlist.get(a).toString().length()/2));
            Db.save("osarticle",osarticle);
        }


    }

    public List<Integer> processReadSum(ResultItems resultItems,Task task)
    {
        String [] a = null;
        List<String> readsum = resultItems.get("readingsum");
        List<Integer> newsum = new ArrayList<Integer>();
        for(String rs :readsum)
        {
            a=rs.toString().split("\\s+");
            newsum.add(Integer.parseInt(a[a.length-1]));
        }

        return newsum;
    }

    public List<String> processTime(ResultItems resultItems,Task task)
    {
        //留有预处理时间为时间戳的方法接口
        List<String> list = resultItems.get("time");
        return list;
    }


}
