package com.example.learn.generic;

/**
 * @Author: lzz
 * @Description:
 * @Date: Created in 21:20 2018/8/12
 */
public class Fibonacci implements Generator<Integer> {
    private int count = 0;

    @Override
    public Integer next() {
        return fib(count++);
    }

    private int fib(int n) {
        if (n < 2) {
            return 1;
        }
        return fib(n - 2) + fib(n - 1);
    }
}
