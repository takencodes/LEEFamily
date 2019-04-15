package com.example.learn;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @Author: lzz
 * @Description:
 * @Date: Created in 22:22 2018/8/13
 */
public class ShowMethods {

    private static String usage = "usage: " +
            "ShowMethods qualified.class.name " +
            "To show all methods in class or: " +
            "ShowMethods qualified.class.name word " +
            "To search for methods involving 'word'";
    private static Pattern pattern = Pattern.compile("\\w+\\.");

    public ShowMethods() {
    }

    public static void main(String[] args) {
        System.out.println(args);
        if (args.length < 1) {
            System.out.println("...............................0");
            System.out.println(usage);
            System.exit(0);
        }

        int lines = 0;

        try {
            Class<?> c = Class.forName(args[0]);
            Method[] methods = c.getMethods();
            Constructor[] constructors = c.getConstructors();
            Method[] var5;
            int var6;
            int var7;
            Method method;
            Constructor[] var10;
            Constructor constructor;
            if (args.length == 0) {
                System.out.println("...............................1");
                var5 = methods;
                var6 = methods.length;

                for (var7 = 0; var7 < var6; ++var7) {
                    method = var5[var7];
                    System.out.println(pattern.matcher(method.toString()).replaceAll(""));
                }

                var10 = constructors;
                var6 = constructors.length;

                for (var7 = 0; var7 < var6; ++var7) {
                    constructor = var10[var7];
                    System.out.println(pattern.matcher(constructor.toString()).replaceAll(""));
                    lines = methods.length + constructors.length;
                }
            } else {
                System.out.println("...............................2");
                var5 = methods;
                var6 = methods.length;

                for (var7 = 0; var7 < var6; ++var7) {
                    method = var5[var7];
                    if (method.toString().indexOf(args[1]) != -1) {
                        System.out.println(pattern.matcher(method.toString()).replaceAll(""));
                        ++lines;
                    }
                }

                var10 = constructors;
                var6 = constructors.length;

                for (var7 = 0; var7 < var6; ++var7) {
                    constructor = var10[var7];
                    if (constructor.toString().indexOf(args[1]) != -1) {
                        System.out.println(pattern.matcher(constructor.toString()).replaceAll(""));
                        ++lines;
                    }
                }
            }
        } catch (ClassNotFoundException var9) {
            System.out.println("no such class:" + var9);
        }

    }
}
