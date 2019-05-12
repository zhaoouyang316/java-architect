package com.zoy.concurrent;

import com.zoy.concurrent.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 实现一个计数功能
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/5/10
 */
@Slf4j
@NotThreadSafe
public class MapExample {

    private static int threadTotal = 200;  // 并发数
    private static int clientTotal = 5000; // 客户端总请求

    private static int count = 0;

    public static void main(String[] args){
        ExecutorService exec= Executors.newCachedThreadPool();  // 线程池
        final Semaphore semaphore = new Semaphore((threadTotal));  // 信号量
        for(int index=0;index<clientTotal;index++){
            exec.execute(()->{
                try{
                    semaphore.acquire();
                    add();
                    semaphore.release();
                }catch (Exception e){

                }
            });
        }
        exec.shutdown();
        System.out.println(count);
    }

    public static void add(){
        count++;
    }

}
