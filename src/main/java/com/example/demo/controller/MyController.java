package com.example.demo.controller;/**
 * Created by 333 on 2018/9/5.
 */

import com.example.demo.vo.MyBean;
import com.example.demo.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Victor
 * @ClassName: MyController
 * @Description: 测试自定义属性
 * @date 2018/9/5 14:45
 */
@RestController
public class MyController {
    //@Value从配置文件中读取配置属性
    @Value("${my.name}")
    private String name;
    @Value("${my.age}")
    private String age;

    @RequestMapping("/my")
    public String my(){
        return "name:"+name+"  age:"+age;
    }

    @Autowired
    private MyBean myBean;

    @RequestMapping("/bean")
    public String bean(){
        return myBean.toString();
    }

    @Autowired
    private UserVo userVo;
    @RequestMapping("/you")
    public String you(){
        return "name:"+userVo.getName()+"  age:"+userVo.getAge();
    }

}
