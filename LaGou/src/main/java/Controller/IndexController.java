package Controller;

import Model.user;
import Service.LoginService;
import Service.osarticleservice;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;

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
            render("/amz_15_bdp/html/table_complete.html");
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
}
