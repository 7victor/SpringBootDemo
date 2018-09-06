package com.example.demo;

import com.example.demo.dao.RedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {

	@Test
	public void contextLoads() {
	}
	private Logger logger = LoggerFactory.getLogger(SpringBootDemoApplicationTests.class);
	@Autowired
	private RedisDao redisDao;
	@Test
	public void testRedis(){
		redisDao.setKey("name","victor");
		redisDao.setKey("age","20");
		logger.debug(redisDao.getValue("name"));
		logger.debug(redisDao.getValue("age"));
	}
}
