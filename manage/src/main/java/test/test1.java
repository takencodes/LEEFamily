package test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: lzz
 * @Description:
 * @Date: Created in 17:38 2019/1/11
 */
public class test1 {
    public static void main(String[] args) {
        Object obj = "{element={id=157, name=槙惧枵, relation=5, delete=false, modifyDate=1545359705000, createDate=1545359705000, mobile=15203691128}}";
        String str = "element is element";
        List list = new ArrayList<>();
        list.add("{id=157, name=槙惧枵, relation=5, delete=false, modifyDate=1545359705000, createDate=1545359705000, mobile=15203691128}");
        Map<String, List> map = new HashMap(2);

        map.put("element", list);
        System.out.println(map.toString());

        String pattern = "element";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(map.toString());
        int i = 0;
        while (m.find()){
            i++;
            System.out.println(m.group());
        }
        System.out.println(i);

    }
}
