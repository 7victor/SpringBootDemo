package com.example.demo.dao;/**
 * Created by 333 on 2018/9/5.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @author Victor
 * @ClassName: RedisDao
 * @Description: RedisDao
 * @date 2018/9/5 17:29
 */
//通过Repository注入到IOC容器中
@Repository
public class RedisDao {
    //通过StringRedisTemplate访问数据库的字符串类型
    @Autowired
    private StringRedisTemplate template;

    public void setKey(String key,String value){
        ValueOperations<String,String> ops = template.opsForValue();
        ops.set(key,value,1, TimeUnit.MINUTES);//1分钟过期
    }

    public String getValue(String key){
        ValueOperations<String,String> ops = template.opsForValue();
        return ops.get(key);
    }
}
