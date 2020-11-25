package com.sampson.simple;
/**
 * 2，3，4的幂
 */
public class Demo3 {
    /**
     * 2的幂
     * 给定一个整数，写一个函数来确定它是否是2的次幂
     */
    public Boolean isPowerOfTwo(int i){
        return i > 0 && (i & (i - 1)) == 0 ;
    }
    /**
     * 3的幂
     * 给定一个整数，写一个函数来确定它是否是3的次幂
     */
    public Boolean isPowerOfThree1(int n){
        //常规方法1，该方法为常规办法，对3取余，递归
        if(n < 1){
            return false;
        }
        while(n % 3 == 0){
            n /= 3;
        }
        return n == 1;
    }
    public Boolean isPowerOfThree2(int n){
        //基准转换法
        return Integer.toString(n,3).matches("^10*$");
    }
    public Boolean isPowerOfThree3(int n){
        //传入的参数为int，所以最大值为2147483647，该范围内最大的3的幂为1162261467
        return n > 0 && 1162261467 % n == 0;
    }
    /**
     * 4的幂
     * 给定一个整数，写一个函数来确定它是否是4的次幂
     *
     */
    public Boolean isPowerOfFour(int n){
        //通过数学办法
        return n > 0 && Math.log(n) / Math.log(2) % 2 == 0;
    }

}
