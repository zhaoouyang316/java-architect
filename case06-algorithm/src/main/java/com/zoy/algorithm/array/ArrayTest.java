package com.zoy.algorithm.array;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/5/19
 */
public class ArrayTest {

    /**
     * leetcode 两数之和
     * 一遍哈希表
     * 时间复杂度为 O(n) 只遍历了所有包含n个元素的列表一次
     * 空间复杂度   O(n) 所需要的额外空间取决与哈希表中的元素数量，该表最多需要存储n个元素
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int nums[],int target){
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int result=target-nums[i];
            if(map.containsKey(result)){
                return new int[]{map.get(result),i};
            }
            map.put(nums[i],i);
        }
        return null;
    }

    @Test
    public void testTwoSum(){
        int[] nums=new int[]{1,3,4,5,0,2, 7, 11, 15};
        int[] subscript=twoSum(nums,9);
        System.out.println(subscript[0]+","+subscript[1]);
    }

}
