package com.example.demo.service;/**
 * Created by 333 on 2018/9/5.
 */

import com.example.demo.dao.IUserDao;
import com.example.demo.vo.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Victor
 * @ClassName: UserService
 * @Description: UserService层
 * @date 2018/9/5 16:57
 */
@Service
public class UserService {
    @Autowired
    private IUserDao userDao;

    public UserEntity getUser(String userName) {
        return userDao.findUserEntitiesByUserName(userName);
    }
}
