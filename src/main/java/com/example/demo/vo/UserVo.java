package com.example.demo.vo;/**
 * Created by 333 on 2018/9/5.
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Victor
 * @ClassName: UserVo
 * @Description: 配置文件中获取
 * @date 2018/9/5 15:31
 */
@Configuration
//PropertySource指定扫描的配置文件
@PropertySource(value = "classpath:test.properties")
@ConfigurationProperties(prefix = "you")
public class UserVo {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
