package com.sampson.SingleTon;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 饿汉式：直接创建对象，不管你是否需要这个对象，不存在线程安全问题
 * 1.直接实例化饿汉式（简洁直观）
 * 2.枚举式（最简洁）
 * 3.静态代码块饿汉式（适合复杂实例化）
 */
public class HurrySingleton {
    public static void main(String[] args) {
        HurrySingleton1 instance1 = HurrySingleton1.INSTANCE;
        HurrySingleton2 instance = HurrySingleton2.INSTANCE;
        HurrySingleton3 instance2 = HurrySingleton3.INSTANCE;
        System.out.println(instance2.getInfo());


    }
}

/**
 * (1).构造器私有化
 * (2).自行创建，并且用静态变量保存
 * (3).向外提供这个实例
 * (4).强调这是一个单例，我们可以用final修饰
 */
class HurrySingleton1{
    public static final HurrySingleton1 INSTANCE = new HurrySingleton1();
    private HurrySingleton1(){
    }
}

/**
 * 枚举类型，表示该类型的对象是有限的几个
 * 我们可以限定一个，就成了单例
 */
enum  HurrySingleton2{
    INSTANCE
}

class HurrySingleton3{
    public static final HurrySingleton3 INSTANCE;
    private String info;
    static {
        Properties pro = new Properties();
        try {
            //System.out.println(HurrySingleton.class.getClassLoader());
            InputStream in = new FileInputStream("leetcode/single.properties");
            pro.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        INSTANCE = new HurrySingleton3(pro.getProperty("info"));
    }
    private HurrySingleton3(String info){
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}