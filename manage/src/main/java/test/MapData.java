package test;

import com.example.learn.generic.Generator;
import javafx.util.Pair;

import java.util.LinkedHashMap;

/**
 * @Author: lzz
 * @Description:
 * @Date: Created in 0:22 2019/3/1
 */
public class MapData<K, V> extends LinkedHashMap<K, V> {

    public MapData(Generator<Pair<K, V>> gen, int quantity){
       for(int i = 0; i < quantity; i ++){
           Pair<K, V> p = gen.next();
           put(p.getKey(), p.getValue());
       }
    }

    public MapData(Generator<K> genK, Generator<V> genV, int quantity){
        for (int i = 0; i < quantity ; i++) {
            put(genK.next(), genV.next());
        }
    }

    public static <K, V> MapData<K, V> map(Generator<Pair<K, V>> gen, int quantity){
        return new MapData<K, V>(gen, quantity);
    }

    public static <K, V> MapData<K, V> map(Generator<K> genK, Generator<V> genV, int quantity){
        return new MapData<K, V>(genK, genV, quantity);
    }
}
