package sampson.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 */
public class Demo2 {
    /**
     * 两数之和
     * 题目：给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * 实例：numbers = [1,2,3,4,5]
     *      target = 6
     *      返回 [1,3]
     * 提示：哈希查找的时间复杂度为O(1)，或许你该考虑使用哈希表
     * 挑战：1.O(n)空间复杂度，O(nlogn)时间复杂度
     *      2.O(n)空间复杂度，O(n)时间复杂度
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{0};
    }
    /**
     * 两数之和-输入有序数组
     * 题目：给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * 实例：numbers = [2,7,11,15]
     *      target = 9
     *      返回 [1,2]
     *实例：numbers = [2,3]
     *     target = 5
     *     返回 [1,2]
     * 提示：输入的数据已经排序，故使用双指针能大幅提高遍历效率（左指针，右指针向不同方向移动）
     */
    public int[] twoSum1(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            //数组中最大值跟最小值相加
            long sum = nums[left] + nums[right];
            //如果和大于目标值，最小值不变，最大值变小
            if (sum > target){
                right--;
                //如果和小于目标值，最小值变大，最大值不变
            }else if (sum < target){
                left++;
            }else {
                //和等于目标值，根据题目要求，坐标从1开始，因此需要加一
                int[] res = new int[2];
                res[0] = left + 1;
                res[1] = right + 1;
                return res;
            }
        }
        return new int[]{0};
    }
    /**
     * 两数之和-BST版本
     * 题目：给一棵二叉搜索树以及一个整数n，如果在树中找到和为n的两个数字则返回true
     * 实例：n = 3
     *      二叉搜索树如下：
     *               4
     *              / \
     *             2   5
     *            / \
     *           1   3
     *      输出：true
     * 提示：使用广度优先搜索（BFS）遍历整个树
     */

    public static void main(String[] args) {
        Demo2 d = new Demo2();
        int[] ints = d.twoSum(new int[]{1, 2, 3, 4, 5}, 6);
        System.out.println(ints.toString());
        int[] ints1 = d.twoSum1(new int[]{2, 7, 11, 15}, 9);
        System.out.println(ints1);
    }
}
