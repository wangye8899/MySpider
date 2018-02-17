package LaGou;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LaGouPipe implements Pipeline
{
    static int sum = 0;
    String savepath = "E:\\爬虫/wordcloud.txt";
    public void process(ResultItems resultItems, Task task)
    {
        List<String> position = resultItems.get("positionname");
        List<String> salary = resultItems.get("salary");
        List<String> workYear = resultItems.get("workYear");
        List<String> address = resultItems.get("address");
        List<String> district = resultItems.get("district");
        List<String> createTime = resultItems.get("createTime");
        List<String> companyName = resultItems.get("companyName");
        List<String> discription = resultItems.get("discription");
        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(savepath,true);
            for (int j = 0; j < position.size(); j++)
            {
                fileWriter.write(position.get(j).toString());
                fileWriter.write("\r\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                fileWriter.flush();
                fileWriter.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        for(int i =0;i<position.size();i++)
        {
            System.out.println(position.get(i).toString()+sum);
            System.out.println(salary.get(i).toString()+sum);
            System.out.println(workYear.get(i).toString()+sum);
            System.out.println(address.get(i).toString()+sum);
            System.out.println(district.get(i).toString()+sum);
            System.out.println(createTime.get(i).toString()+sum);
            System.out.println(companyName.get(i).toString()+sum);
            System.out.println(discription.get(i).toString()+sum);

            Record lagou = new Record()
                    .set("positionname",position.get(i).toString())
                    .set("salary",salary.get(i).toString())
                    .set("workYear",workYear.get(i).toString())
                    .set("address",address.get(i).toString())
                    .set("district",district.get(i).toString())
                    .set("createTime",createTime.get(i).toString())
                    .set("companyName",companyName.get(i).toString())
                    .set("discription",discription.get(i).toString());
            Db.save("lagou",lagou);
            sum++;
        }
        }
    }

