package org.rone.study.spring.utils;

/**
 * Created by zouRongHui on 2018/5/21.
 * spring 提供的用于对运行程序进行耗时统计的工具
 */
public class StopWatch {

    public static void main(String[] args) throws InterruptedException {
        org.springframework.util.StopWatch sw = new org.springframework.util.StopWatch("work time.");

        sw.start("first");
        Thread.sleep(100);
        sw.stop();

        sw.start("second");
        Thread.sleep(200);
        sw.stop();

        sw.start("third");
        Thread.sleep(300);
        sw.stop();

        sw.start("fourth");
        Thread.sleep(400);
        sw.stop();

        System.out.println(sw.prettyPrint());
    }
}
