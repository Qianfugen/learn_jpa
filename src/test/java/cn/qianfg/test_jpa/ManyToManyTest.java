package cn.qianfg.test_jpa;

import cn.qianfg.dao.RoleDao;
import cn.qianfg.dao.UserDao;
import cn.qianfg.pojo.Role;
import cn.qianfg.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ManyToManyTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Test
    @Transactional
    @Rollback(false)
    public void testAdd(){
        User user = new User();
        user.setUserName("小李");

        Role role = new Role();
        role.setRoleName("Java程序员");

        //配置用户到角色关系，可以对中间表的数据进行维护
        user.getRoles().add(role);

        userDao.save(user);
        roleDao.save(role);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeAdd(){
        User user = new User();
        user.setUserName("小李");

        Role role = new Role();
        role.setRoleName("Java程序员");

        //配置用户到角色关系，可以对中间表的数据进行维护
        user.getRoles().add(role);

        userDao.save(user);
    }

}
