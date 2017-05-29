import entities.User;
import jndi.UserDaoImpl;
import org.apache.openejb.junit.jee.EJBContainerRunner;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(EJBContainerRunner.class)
public class DaoTest {

    private static UserDaoImpl userDao;

    private static Context ctx;
    private static EJBContainer ejbContainer;

    @BeforeClass
    public static void setUp() throws NamingException {
        ejbContainer = EJBContainer.createEJBContainer();
        System.out.println("Opening the container");
        ctx = ejbContainer.getContext();
        userDao = (UserDaoImpl) ctx.lookup("java:global/ips_jndi-web/UserDaoImpl");
    }


    @Test
    public void testFindAll() {
        List<User> all = userDao.findAll();
        System.out.println();

        assertTrue(all.size() == 19);
    }


    @Test
    public void testAdd() {
        User user = getMockEntity();
        userDao.insert(user);

        assertTrue(userDao.findById(user.getEmail()) != null);

        userDao.delete(user.getEmail());
    }


    @Test
    public void testDelete() {
        User user = getMockEntity();
        userDao.insert(user);
        userDao.delete(user.getEmail());

        assertTrue(userDao.findById(user.getEmail()) == null);
    }

    @Test
    public void testUpdate() {
        User user = getMockEntity();
        userDao.insert(user);
        user.setPassword("000000");
        userDao.update(user);

        assertTrue(userDao.findById(user.getEmail()).getPassword().equals(user.getPassword()));
        userDao.delete(user.getEmail());
    }

    public User getMockEntity() {
        User user = new User();
        user.setEmail("olololo@ololo.ruu");
        user.setPhoneNumber("1");
        user.setFirstName("1");
        user.setLastName("1");
        user.setPassword("1234");
        user.setRole("USER");

        return user;
    }

}
