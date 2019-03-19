package com.shuyan.learn.java.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args){
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-test-%d").build();
        ExecutorService executorService1 = new ThreadPoolExecutor(5,10,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(10),namedThreadFactory);

        ScheduledExecutorService executorService2 = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("thread-test-%d").daemon(true).build());
    }
}
