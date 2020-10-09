package 集合;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/
 */
public class _349_两个数组的交集_Y {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> array = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                array.add(nums2[i]);
            }
        }
        int length = nums1.length > nums2.length ? nums2.length : nums1.length;
        int[] nums = new int[length];
        int index = 0;
        for (Integer s : array) {
            nums[index++] = s;
        }
        return Arrays.copyOf(nums, index);
    }
}
