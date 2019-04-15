package com.example.learn.generic;

import java.util.Collection;

/**
 * @Author: lzz
 * @Description:
 * @Date: Created in 22:51 2018/8/13
 */
public class Generators {
    public static <T> Collection<T> fill(Collection<T> collection, Generator<T> generator, int n) {
        for (int i = 0; i < n; i++) {
            collection.add(generator.next());
        }
        return collection;
    }
}
