package com.shuyinqi.guava.concurrent;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Created by jiayusun on 2016/4/26.
 */
public class GuavaRateLimiter {

    public static void main(String[] args) {
        //RateLimiter类似于JDK的信号量Semphore，他用来限制对资源并发访问的线程数
        RateLimiter limiter = RateLimiter.create(4);////每秒不超过4个任务被提交
        limiter.acquire();//请求RateLimiter, 超过permits会被阻塞

        //也可以以非阻塞的形式来使用
        if(limiter.tryAcquire()){ //未请求到limiter则立即返回false
        //    doSomething();
        }else{
         //   doSomethingElse();
        }

    }
}
