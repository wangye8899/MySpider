package Service;

import Model.user;

public class LoginService {

    public boolean Istrue(user u)
    {
        String name = u.getStr("username");
        String pwd  = u.getStr("password");
        user up = user.dao.findFirst("select * from user where username = ? and password = ?",name,pwd);
        return up == null?false:true;
    }
}
