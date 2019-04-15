package test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: lzz
 * @Description:
 * @Date: Created in 10:42 2019/2/21
 */
public class TreeMapTest {

    public static void main(String[] args) {
        Map<String, String> treeMap = new LinkedHashMap<>();
        Map<String, String> treeMap1 = new LinkedHashMap<>();
        treeMap1.put("凌嘉欣","18");
        treeMap1.put("王欣","19");
        treeMap.put("tomik","12");
        treeMap.put("tom","21");
        treeMap.put("jack","10");
        treeMap.put("tomy","33");
        treeMap.put("tomik","30");
        treeMap.put("jack","15");
        treeMap.put("goolky   ","");
        treeMap1.putAll(treeMap);
        for (Map.Entry entry : treeMap1.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
            System.out.println("......................");
        }
    }
}
