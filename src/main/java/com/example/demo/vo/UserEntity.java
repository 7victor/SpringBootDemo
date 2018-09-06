package com.example.demo.vo;/**
 * Created by 333 on 2018/9/5.
 */

import javax.persistence.*;

/**
 * @author Victor
 * @ClassName: User
 * @Description: JPA测试实体类
 * @date 2018/9/5 16:46
 */
//Entity注解表明该类是一个实体类，与数据库的表名相对应
@Entity
public class UserEntity {
    //Id表示变量对应数据库中的id GeneratedValue配置自增长
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Column对应数据库字段 nullable非空 unique唯一约束
    @Column(nullable = false, unique = true)
    private String userName;

    @Column
    private String passWord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
