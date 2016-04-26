package com.shuyinqi.guava.cache;

import com.google.common.base.Objects;
import com.google.common.cache.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiayusun on 2016/4/25.
 * google guava中有cache包，此包提供内存缓存功能。内存缓存需要考虑很多问题，包括并发问题，缓存失效机制，内存不够用时缓存释放，缓存的命中率，缓存的移除等等。 当然这些东西guava都考虑到了
 *
 *guava缓存过期时间分为两种，一种是从写入时开始计时，一种是从最后访问时间开始计时，而且guava缓存的过期时间是设置到整个一组缓存上的；这和EHCache，redis，memcached等不同，这些缓存系统设置都将缓存时间设置到了单个缓存上。
 *guava缓存设计成了一组对象一个缓存实例，这样做的好处是一组对象设置一组缓存策略，你可以根据不同的业务来设置不同的缓存策略，包括弱引用，软引用，过期时间，最大项数等。另外一点好处是你可以根据不同的组来统计缓存的命中率，这样更有意义一些。
 *这样做也是有缺点的，缺点是首先是每个缓存组都需要声明不同的缓存实例，具体到业务程序中可能就是每个业务对象一个缓存了。这样就把不同的业务缓存分散到不同的业务系统中了，不太好管理。
 **/
public class GuavaCache {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LoadingCache<Integer,Student> studentCache = CacheBuilder.newBuilder()
                .concurrencyLevel(8) //设置并发级别为8，并发级别是指可以同时写缓存的线程数
                .expireAfterWrite(8, TimeUnit.SECONDS)  //设置写缓存后8秒钟过期
                .initialCapacity(10) //设置缓存容器的初始容量为10
                .maximumSize(100) //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
                .recordStats()  //设置要统计缓存的命中率
                .removalListener(new RemovalListener<Object, Object>() {  //设置缓存的移除通知
                    @Override
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause());
                    }
                }).build(new CacheLoader<Integer,Student>() { //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
                    @Override
                    public Student load(Integer key) throws Exception {
                        System.out.println("load student " + key);
                        Student student = new Student();
                        student.setId(key);
                        student.setName("name " + key);
                        return student;
                    }
                });
        studentCache.put(2,new Student());
        for(int i=0;i<20;i++){
            Student student = studentCache.get(2);
            System.out.println(student);
            //休眠1秒
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("cache stats:");
        //最后打印缓存的命中率等 情况
        System.out.println(studentCache.stats().toString());
        System.out.println(studentCache.asMap());

        /****
         * ImmutableMap<K, V> getAllPresent(Iterable<?> keys) 一次获得多个键的缓存值
           put和putAll方法向缓存中添加一个或者多个缓存项
           invalidate 和 invalidateAll方法从缓存中移除缓存项
           asMap()方法获得缓存数据的ConcurrentMap<K, V>快照
           cleanUp()清空缓存
           refresh(Key) 刷新缓存，即重新取缓存数据，更新缓存

         */
    }

}
class Student {
    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return Objects.toStringHelper(this.getClass())
                .add("id", id)
                .add("name", name)
                .add("age", age)
                .omitNullValues().toString();
    }
}