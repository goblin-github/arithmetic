package sampson.simple;

/**
 * A+B Problem
 * 题目：使用位运算求a和b的和，两个整数a和b，其中0<=a，b<=100
 * 提示：a+b=a^b+(a&b)<<1
 * 挑战：1.去除约束条件0<=a，b<=100
 *     2.不使用运算符+，-
 */
public class Demo1 {

    public int sum(int a, int b) {
        if (b == 0) {
            return a;
        }
        if (a == 0) {
            return b;
        }
        //加操作
        int sum = a ^ b;
        //进位操作
        int carry = (a & b) << 1;
        //将加操作结果加上进位操作结果
        return sum(sum, carry);
    }

    public static void main(String[] args) {
        Demo1 d = new Demo1();
        System.out.println(d.sum(234, 45));
    }
}
