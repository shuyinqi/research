package com.shuyinqi.base;

/**
 * Created by jiayusun on 2016/6/12.
 */
public class TestThreadLocal {

    private static java.lang.ThreadLocal<Integer> seqNum = new java.lang.ThreadLocal<Integer>(){
        public Integer initialValue(){
            return 0;
        }
    };

    public int getNextNum(){
        seqNum.set(seqNum.get()+1);
        return seqNum.get();
    }

    public static void main(String[] args) {
        TestThreadLocal sn = new TestThreadLocal();
        // ③ 3个线程共享sn，各自产生序列号
        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);
        t1.start();
        t2.start();
        t3.start();
    }

    private static class TestClient extends  Thread{
        private TestThreadLocal sn;

        public TestClient(TestThreadLocal sn){
            this.sn=sn;
        }

        public void run(){
            for( int i=0;i<30;i++ ){
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn["
                        + sn.getNextNum() + "]");
            }
        }

    }
}
