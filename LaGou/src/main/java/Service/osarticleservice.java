package Service;

import Model.osarticle;
import us.codecraft.webmagic.Page;

import java.util.List;

public class osarticleservice {


    public static List<osarticle> findAll(int sum)
    {
        return osarticle.dao.find("select * from osarticle order by readingsum desc limit ?",sum);
    }
}
