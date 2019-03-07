package com.ecut.lambda;

import java.util.Arrays;
import java.util.function.IntConsumer;

public class HelloLambaTest {
    static int[] arr = {1, 2, 3, 4, 5};

    public static void main(String[] args) {
        //普通输出
        for (int i : arr) {
            System.out.println(i);
        }
        /*使用Java 8中的流 Arrays.stream返回了一个流对象。类似于集合或者数组。foreach接受了一个IntConsumer接口的实现用于对流内部
        对象的处理*/
        Arrays.stream(arr).forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        });

        //省略foreach参数
        Arrays.stream(arr).forEach((final int value) -> {
            System.out.println(value);
        });

        //省略参数类型
        Arrays.stream(arr).forEach((value) -> {
            System.out.println(value);
        });

        //使用lambda表达式,省略括号
        Arrays.stream(arr).forEach(value -> System.out.println(value));

        //使用方法引用
        Arrays.stream(arr).forEach(System.out::println);

        //流式API
        IntConsumer out = System.out::println;
        IntConsumer err = System.err::println;
        Arrays.stream(arr).forEach(out.andThen(err));

    }
}
