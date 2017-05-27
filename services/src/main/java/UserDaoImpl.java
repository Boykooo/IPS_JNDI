import entities.User;
import remote.UserDaoRemote;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.List;
import java.util.Properties;

@Stateless
public class UserDaoImpl {

    private UserDaoRemote userDaoRemote;

    public UserDaoImpl() {

        try {
            final Properties jndiProp = new Properties();

            jndiProp.put("jboss.naming.client.ejb.context", true);
            jndiProp.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

            final Context context = new InitialContext(jndiProp);

            userDaoRemote = (UserDaoRemote) context.lookup("ejb:/IPSDatabase/UserDao!remote.UserDaoRemote");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public User findById(String o){
        return userDaoRemote.findById(o);
    }

    public List<User> findAll(){
        return userDaoRemote.findAll();
    }

    public void insert(User entity){
        userDaoRemote.insert(entity);
    }

    public void update(User entity){
        userDaoRemote.update(entity);
    }

    public boolean delete(String key){
        return userDaoRemote.delete(key);
    }
}
