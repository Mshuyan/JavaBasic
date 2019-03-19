package com.shuyan.learn.java;

import com.shuyan.learn.java.thread.AsyncTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static java.lang.Thread.sleep;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaApplicationTests {
    @Autowired
    private AsyncTest asyncTest;

    @Test
    public void contextLoads() throws InterruptedException {
        asyncTest.test1();
        System.out.println("主线程睡觉");
        sleep(5000);
    }

    @Test
    public void test2() throws InterruptedException, ExecutionException {
        Future<String> future = asyncTest.test2();
        while (!future.isDone());
        System.out.println("future result = " + future.get());
    }
}
