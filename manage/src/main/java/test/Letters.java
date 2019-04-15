package test;

import com.example.learn.generic.Generator;
import javafx.util.Pair;

import java.util.Iterator;

/**
 * @Author: lzz
 * @Description:
 * @Date: Created in 0:33 2019/3/1
 */
public class Letters implements Generator<Pair<Integer, String>>, Iterable<Integer> {

    private int size = 9;
    private int num = 1;
    private char letter = 'A';
    @Override
    public Pair<Integer, String> next() {
        return new Pair<>(num++, ""+letter++);
    }


    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return num < size;
            }

            @Override
            public Integer next() {
                return num++;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        System.out.println(MapData.map(new Letters(), 11));
    }
}
