package com.example.demo.dao;/**
 * Created by 333 on 2018/9/5.
 */

import com.example.demo.vo.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Victor
 * @ClassName: IUserDao
 * @Description: User表对应的dao的interface
 * @date 2018/9/5 16:54
 */
public interface IUserDao extends JpaRepository<UserEntity,Long>{
    UserEntity findUserEntitiesByUserName(String userName);
}
