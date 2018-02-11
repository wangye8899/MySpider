package AppConfig;

import Controller.IndexController;
import Controller.OsArticleController;
import Model.osarticle;
import Model.user;
import Oschina.OsArticle;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

public class appConfig extends JFinalConfig {
    public void configConstant(Constants constants) {
        PropKit.use("config.txt");
        constants.setDevMode(PropKit.getBoolean("devMode"));
    }

    public void configRoute(Routes routes) {

        routes.add("/", IndexController.class);
        routes.add("/OsArticle", OsArticleController.class);
    }

    public void configEngine(Engine engine) {

    }

    public void configPlugin(Plugins plugins) {

        DruidPlugin dp = new DruidPlugin(PropKit.get("dbConnect"),PropKit.get("dbUser"),PropKit.get("dbPwd"));
        plugins.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        plugins.add(arp);
        arp.setDialect(new MysqlDialect());
        arp.addMapping("osarticle", osarticle.class);
        arp.addMapping("osproject", osarticle.class);
        arp.addMapping("user", user .class);
    }

    public void configInterceptor(Interceptors interceptors) {
//        interceptors.addGlobalActionInterceptor(new )
    }

    public void configHandler(Handlers handlers) {

    }

    public static  void  main(String []argv)
    {
        JFinal.start("src/main/webapp",8082,"/");

    }
}
