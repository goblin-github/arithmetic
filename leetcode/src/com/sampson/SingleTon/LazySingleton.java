package com.sampson.SingleTon;

/**
 * 懒汉式：延迟创建对象
 * 1.线程不安全（适用于单线程）
 * 2.线程安全（适合于多线程）
 * 静态内部类形式（适用于多线程）
 */
public class LazySingleton {
    public static void main(String[] args) {

    }

}

/**
 * 存在多线程安全问题
 */
class LazySingleton1{
    private static LazySingleton1 instance;
    private LazySingleton1(){
    }
    public static LazySingleton1 getInstance(){
        if (instance == null){
            instance = new LazySingleton1();
        }
        return instance;
    }
}
class LazySingleton2 {
    private static LazySingleton2 instance;

    private LazySingleton2() {
    }

    public static LazySingleton2 getInstance() {
        if (instance == null) {
            synchronized (LazySingleton2.class) {
                if (instance == null) {
                    instance = new LazySingleton2();
                }
            }
        }
        return instance;
    }
}

/**
 * 在内部类被加载和初始化时，才创建INSTANCE实例对象
 * 静态内部类不会自动随着外部类的加载和初始化而初始化，他是要单独去加载和初始化的
 * 因为是在内部类加载和初始化时创建的，因此线程安全的
 */
class LazySingleton3{
    private LazySingleton3(){

    }
    private static class Inner{
        private static final LazySingleton3 INSTANCE = new LazySingleton3();
    }
    public static LazySingleton3 getInstance(){
        return Inner.INSTANCE;
    }

}