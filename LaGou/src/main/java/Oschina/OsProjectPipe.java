package Oschina;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

public class OsProjectPipe implements Pipeline {
    static int temp=0;
    public void process(ResultItems resultItems, Task task) {
        List<String> list = resultItems.get("picaddress");

        for(int i=0;i<list.size();i++)
        {
            temp++;
            System.out.println(list.get(i)+temp);
        }

    }
}
