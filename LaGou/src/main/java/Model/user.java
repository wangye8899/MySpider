package Model;

import com.jfinal.plugin.activerecord.Model;

public class user extends Model<user> {
    public static final user dao = new user().dao();

}
