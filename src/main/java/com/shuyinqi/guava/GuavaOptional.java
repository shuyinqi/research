package com.shuyinqi.guava;


import com.google.common.base.Optional;

import javax.swing.text.html.Option;

/**
 * Created by jiayusun on 2016/4/25.
 * 使用Optional除了赋予null语义，增加了可读性，最大的优点在于它是一种傻瓜式的防护。Optional迫使你积极思考引用缺失的情况
 */
public class GuavaOptional {
    /***
     * 使用Optional除了赋予null语义，增加了可读性，最大的优点在于它是一种傻瓜式的防护。Optional迫使你积极思考引用缺失的情况，因为你必须显式地从Optional获取引用。直接使用null很容易让人忘掉某些情形，尽管FindBugs可以帮助查找null相关的问题，但是我们还是认为它并不能准确地定位问题根源。
     如同输入参数，方法的返回值也可能是null。和其他人一样，你绝对很可能会忘记别人写的方法method(a,b)会返回一个null，就好像当你实现method(a,b)时，也很可能忘记输入参数a可以为null。将方法的返回类型指定为Optional，也可以迫使调用者思考返回的引用缺失的情形。
     * @param args
     */
    public static void main(String[] args) {
        //1.在使用Optional静态方法of时，如果传入的参数为null就抛出NullPointerException异常
       Optional<Student> possiableNull= Optional.of(null);
        possiableNull.get();
        //2.使用Optional.absent方法来初始化posibleNull实例，然后我们get此对象，IllegalStateException
        Optional<Student> possibleNull = Optional.absent();
        Student jim = possibleNull.get();
    }

    public static class Student { }
}
