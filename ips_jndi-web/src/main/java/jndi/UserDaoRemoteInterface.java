package jndi;

import entities.User;

import java.util.List;

public interface UserDaoRemoteInterface {

     User findById(String o);

     List<User> findAll();

     void insert(User entity);

     void update(User entity);

     boolean delete(String key);

}
