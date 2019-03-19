package com.shuyan.learn.java.thread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

import static java.lang.Thread.sleep;

/**
 * @author will
 */
@Component
// @Async("asyncServiceExecutor")
public class AsyncTest {

    @Async("asyncServiceExecutor")
    public void test1() throws InterruptedException {
        for(int i=0; i<100; i++){
            System.out.println(Thread.currentThread().getName() + " : i = " + i);
            sleep(100);
        }
    }

    @Async("asyncServiceExecutor")
    public Future<String> test2() throws InterruptedException {
        for(int i=0; i<10; i++){
            System.out.println(Thread.currentThread().getName() + " : i = " + i);
            sleep(100);
        }
        return new AsyncResult<>("test2");
    }
}
