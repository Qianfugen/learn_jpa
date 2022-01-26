package cn.qianfg.controller;

import cn.qianfg.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "用户层")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/update.do")
    public void update(String name) {
        userService.updateUser(name);
    }
}
