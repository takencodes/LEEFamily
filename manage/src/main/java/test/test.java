package test;


import org.springframework.expression.spel.ast.SpelNodeImpl;

import javax.swing.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lzz
 * @Description:
 * @Date: Created in 10:09 2019/1/10
 */
public class test {

    public static void main(String[] args) throws Exception {

        String a = "user/login.do";
        String b = "login.do";
        System.out.println(a.contains(b));
    }
}
