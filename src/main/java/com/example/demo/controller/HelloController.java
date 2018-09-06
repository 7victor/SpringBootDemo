package com.example.demo.controller;/**
 * Created by 333 on 2018/9/5.
 */

import com.example.demo.dao.RedisDao;
import com.example.demo.service.UserService;
import com.example.demo.vo.UserEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Victor
 * @ClassName: HelloController
 * @Description: 第一个Controller
 * @date 2018/9/5 14:26
 */
@RestController
public class HelloController {
    /**
     * rest请求返回hello
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        return "hello Spring Boot!";
    }

    @Autowired
    private UserService userService;
    @ApiOperation(value = "通过名称查找用户",notes="用户列表")
    @RequestMapping(value = "/findUser",method = RequestMethod.GET)
    public UserEntity findUser(String userName) {
        return userService.getUser(userName);
    }

    @Autowired
    private RedisDao redisDao;
    @RequestMapping("/getRedis")
    public String getRedis(String key) {
        redisDao.setKey("name","victor");
        redisDao.setKey("age","20");
        System.out.print(redisDao.getValue("name"));
        return redisDao.getValue(key);
    }
}
