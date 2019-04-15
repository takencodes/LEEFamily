package com.example.learn;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lzz
 * @Description:
 * @Date: Created in 22:20 2018/8/13
 */
public class FiledList<T> {
    private Class<T> type;

    public FiledList(Class<T> type) {
        this.type = type;
    }

    public List<T> create(int nElements) {
        ArrayList result = new ArrayList();

        try {
            for (int i = 0; i < nElements; ++i) {
                result.add(this.type.newInstance());
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
