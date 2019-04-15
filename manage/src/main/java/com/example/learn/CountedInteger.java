package com.example.learn;

/**
 * @Author: lzz
 * @Description:
 * @Date: Created in 22:19 2018/8/13
 */
public class CountedInteger {
    private static long counter;
    private final long id;

    public CountedInteger() {
        this.id = (long) (counter++);
    }

    @Override
    public String toString() {
        return Long.toString(this.id);
    }
}
