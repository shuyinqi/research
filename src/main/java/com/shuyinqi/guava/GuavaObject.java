package com.shuyinqi.guava;

import com.google.common.base.Objects;

/**
 * Created by jiayusun on 2016/4/25.
 */
public class GuavaObject {
    //我们在开发中经常会需要比较两个对象是否相等，这时候我们需要考虑比较的两个对象是否为null，然后再调用equals方法来比较是否相等
    public static void testEquals(){
        Object a = null;
        Object b = new Object();
        boolean aEqualsB = Objects.equal(a, b);
    }

    public static void main(String[] args) {
        //Objects类中还为我们提供了方便的重写toString()方法的机制
        Student jim = new Student();
        jim.setId(1);
        jim.setName("Jim");
        jim.setAge(13);
        System.out.println(jim.toString());
    }

    public static class Student {
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
}
