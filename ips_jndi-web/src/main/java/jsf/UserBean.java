package jsf;

import entities.User;
import jndi.UserDaoImpl;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("userBean")
@ManagedBean
@RequestScoped
public class UserBean {

    @EJB
    private UserDaoImpl userDao;
    private List<User> users;

    @PostConstruct
    private void init(){
        users = userDao.findAll();
    }

    public User findById(String o){
        return userDao.findById(o);
    }

    public void insert(User entity){
        userDao.insert(entity);
    }

    public void update(User entity){
        userDao.update(entity);
    }

    public boolean delete(String key){
        return userDao.delete(key);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
