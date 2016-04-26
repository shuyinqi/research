package com.shuyinqi.guava.concurrent;

import com.google.common.util.concurrent.Monitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiayusun on 2016/4/26.
 * Monitor就像java原生的synchronized, ReentrantLock一样，每次只允许一个线程执行代码块，且可重占用，每一次占用要对应一次退出占用
 */
public class GuavaMonitor {

    private List<String> list = new ArrayList<String>();
    private static final int MAX_SIZE = 10;
    private Monitor monitor = new Monitor();

    private Monitor.Guard listBelowCapacity = new Monitor.Guard(monitor){
        @Override
        public boolean isSatisfied() {
            return list.size() < MAX_SIZE;
        }
    };

    public void addToList(String item) throws InterruptedException {
        monitor.enterWhen(listBelowCapacity); //Guard(形如Condition)，不满足则阻塞，而且我们并没有在Guard进行任何通知操作
        try {
            list.add(item);
        } finally {
            monitor.leave();
        }
        //or
        if (monitor.enterIf(listBelowCapacity)) {
            try {
                //doWork();
            } finally {
                monitor.leave();
            }
        }
    }

    /***其他的Monitor访问方法
     * Monitor.enter //进入Monitor块，将阻塞其他线程直到Monitor.leave
     * Monitor.tryEnter //尝试进入Monitor块，true表示可以进入, false表示不能，并且不会一直阻塞
     * Monitor.tryEnterIf //根据条件尝试进入Monitor块
     */
    public static void main(String[] args) {

    }
}
