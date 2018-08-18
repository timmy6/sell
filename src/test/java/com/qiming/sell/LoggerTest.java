package com.qiming.sell;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liuqiming
 * @date 2018/8/17
 * @email liuqiminggood@gmail.com
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest {
    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test1() {
        String name = "liuqiming";
        int age = 16;
        logger.debug("debug");
        logger.info("name:{},age:{}", name, age);
        logger.info("info");
        logger.error("error");
    }
}