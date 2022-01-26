package cn.qianfg.service.impl;

import cn.qianfg.dao.UserDao;
import cn.qianfg.pojo.User;
import cn.qianfg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public void updateUser(String name) {
        User user = userDao.findUserById(1L);
        System.out.println("user roleId: " + user.getRoleId());
        System.out.println("user role(roleId): " + user.getRole().getId());
        System.out.println("user role(roleName): " + user.getRole().getName());

        user.setRoleId(2L);
        userDao.saveAndFlush(user);

        User user1 = userDao.findUserById(1L);
        System.out.println("user1 roleId: " + user1.getRoleId());
        System.out.println("user1 role(roleId): " + user1.getRole().getId());
        System.out.println("user1 role(roleName): " + user1.getRole().getName());
    }

}
