package com.txb.app.utils;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Demo {
    private static volatile HashMap<Integer,Object> lockMap = new HashMap<>();
    public static void main(String[] args) {
          int num = 0;
            for (int i = 0; i < 100; i++) {
                Object obj = new Object();
                num ++;
                lockMap.put(num,obj);
            }

            new Thread(()->{
                for (Integer sort : lockMap.keySet()) {
                    Object objLock = lockMap.get(sort);
                    synchronized (objLock){
                        try {
                            System.out.println("对象 "+sort +" 获取到锁");
                            Thread.sleep(1000);
                            if(sort == 2){
                                System.out.println("对象 "+sort +" 放弃了当前执行资源");
                                Thread.sleep(1000);
                                objLock.wait();
                            }
                            System.out.println("对象 "+sort +" 开始执行记录");
                            Thread.sleep(1000);
                            System.out.println("-------------------------");
                            if(sort == 10){
                                break;
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            }).start();

        new Thread(()->{
            for (Integer sort : lockMap.keySet()) {
                Object objLock = lockMap.get(sort);
                synchronized (objLock){
                    System.out.println("第"+sort+"线程数");
                    if(sort == 2){
                        System.out.println("另一个线程释放了 "+ sort +"锁");
                        objLock.notify();
                    }
                }
            }
        }).start();
       // System.out.println("放弃了当前线程");
    }
}
