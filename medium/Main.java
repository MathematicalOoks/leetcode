import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome! Here are my java solutions to various leetcode problems");
    }
}

// Leetcode 901 - Online Stock Span

class StockSpanner {
    int smaller = 0;
    int index = 0;
    ArrayList<Integer> a = new ArrayList<Integer>();
    public StockSpanner() {

    }

    public int next(int price) {
        smaller = 0;
        a.add(price);
        index = a.size()-1;
        while( index > -1 && a.get(index) <= price ){
            smaller++;
            index--;
        }
        return smaller;
    }
}

// Leetcode 152 - Maximum Product Subarray

class Solution {
    public int maxProduct(int[] nums) {
        if( nums.length == 1 ) return nums[0];
        int positive = 1;
        int negative = 1;
        int max = 0;
        for(int i=0; i < nums.length; i++){
            if( nums[i] < 0 ){
                negative *= nums[i];
                max = Math.max(negative, max);
                positive = 1;
            }
            else if( nums[i] == 0 ){ positive = 1; negative = 1; }
            else{
                positive *= nums[i];
                negative *= nums[i];
                max = Math.max(positive, max);
                if( negative > 0 ) max = Math.max(max, negative);
            }
        }
        positive = 1;
        negative = 1;
        for(int i=nums.length-1; i > -1; i--){
            if( nums[i] < 0 ){
                negative *= nums[i];
                max = Math.max(negative, max);
                positive = 1;
            }
            else if( nums[i] == 0 ){ positive = 1; negative = 1; }
            else{
                positive *= nums[i];
                negative *= nums[i];
                max = Math.max(positive, max);
                if( negative > 0 ) max = Math.max(max, negative);
            }
        }
        return max;
    }
}

// Leetcode 2491 - Divide Players Into Teams of Equal Skill

class Solution2491 {
    public long dividePlayers(int[] skill) {
        long product = 0;
        int sum = 0;
        int n = 0;
        HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for(int i=0; i < skill.length; i++){
            sum += skill[i];
            if( !freq.containsKey(skill[i]) ) freq.put(skill[i], 1);
            else freq.put(skill[i], freq.get(skill[i])+1);
        }
        if( 2*sum%skill.length != 0 ) return -1;
        sum = 2*sum/skill.length;
        for(int i=0; i < skill.length; i++){
            if( freq.containsKey(sum-skill[i]) && freq.get(sum-skill[i]) > 0 && freq.get(skill[i]) > 0 ){
                product += skill[i]*(sum-skill[i]);
                freq.put(sum-skill[i], freq.get(sum-skill[i])-1);
                freq.put(skill[i], freq.get(skill[i])-1);
                n++;
            }
        }
        return (n==skill.length/2)?product:-1;
    }
}

// Leetcode 11 - Container With Most Water

class Solution11 {
    public int maxArea(int[] height) {
        int a = 0;
        int b = height.length-1;
        int maxArea = 0;
        while( a < b && b < height.length ){
            maxArea = Math.max(maxArea, Math.min(height[a], height[b])*(b-a));
            if( height[a] < height[b] ) a++;
            else b--;
        }
        return maxArea;
    }
}

// Leetcode 2425 - Bitwise XOR of All Pairings

class Solution2425 {
    public int xorAllNums(int[] nums1, int[] nums2) {
        int xor = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0; i < nums1.length; i++){
            if( !map.containsKey(nums1[i]) ) map.put(nums1[i], nums2.length);
            else map.put(nums1[i], map.get(nums1[i])+nums2.length);
        }
        for(int i=0; i < nums2.length; i++){
            if( !map.containsKey(nums2[i]) ) map.put(nums2[i], nums1.length);
            else map.put(nums2[i], map.get(nums2[i])+nums1.length);
        }
        for(int key : map.keySet()){
            if( map.get(key)%2 != 0 ) xor ^= key;
        }
        return xor;
    }
}

// Leetcode 1877 - Minimise Maximum Pair Sum in Array

class Solution1877 {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int max = 0;
        for(int i=0; i < nums.length/2; i++){
            max = Math.max(max, nums[i]+nums[nums.length-1-i]);
        }
        return max;
    }
}

// Leetcode 2279 - Maximum Bags With Full Capacity of Rocks

class Solution2279 {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int bags = 0;
        for(int i=0; i < capacity.length; i++){
            capacity[i] -= rocks[i];
        }
        Arrays.sort(capacity);
        for(int i=0; i < capacity.length; i++){
            if( additionalRocks == 0 ) break;
            else if( capacity[i] > additionalRocks ) break;
            else{
                bags++;
                additionalRocks -= capacity[i];
            }
        }
        return bags;
    }
}

// Leetcode 7 - Reverse Integer

class Solution7 {
    public int reverse(int x) {
        long multiply = 1;
        int sign = (x<0)?-1:1;
        long reverse = 0;
        x = Math.abs(x);
        int copy = x;
        while( copy > 0 ){
            copy /= 10;
            multiply *= 10;
        }
        multiply /= 10;
        while( x > 0 ){
            reverse += multiply * (x % 10);
            multiply /= 10;
            x /= 10;
        }
        if( reverse > Integer.MAX_VALUE ) reverse = 0;
        return sign*((int)reverse);
    }
}

// Leetcode 1814 - Count Nice Pairs in an Array

class Solution1814 {
    public int rev(int x){
        long multiply = 1;
        long reverse = 0;
        int copy = x;
        while( copy > 0 ){
            copy /= 10;
            multiply *= 10;
        }
        multiply /= 10;
        while( x > 0 ){
            reverse += multiply * (x % 10);
            multiply /= 10;
            x /= 10;
        }
        if( reverse > Integer.MAX_VALUE ) reverse = 0;
        return (int)reverse;
    }
    public int countNicePairs(int[] nums) {
        int reverse = 0;
        long pairs = 0;
        HashMap<Integer, Integer> frequency = new HashMap<Integer, Integer>();
        for(int i=0; i < nums.length; i++){
            reverse = rev(nums[i]);
            if( !frequency.containsKey(nums[i]-reverse) ) frequency.put(nums[i]-reverse, 1);
            else frequency.put(nums[i]-reverse, frequency.get(nums[i]-reverse)+1);
        }
        for(int i : frequency.keySet()){
            if( frequency.get(i) != 1 ) pairs += ((long)frequency.get(i))*((long)(frequency.get(i)-1))/2;
        }
        // nums[i] - rev(nums[i]) = nums[j] - rev(nums[j])
        // Keep frequency
        // Sum frequency if greater than 1
        return (int)(pairs%(1000000007));
    }
}

// Leetcode 904 - Fruit Baskets

class Solution904 {
    public int totalFruit(int[] fruits) {
        if( fruits.length == 1 ) return 1;
        int[] trees = new int[]{fruits[0], fruits[1]};
        int nums1 = 0;
        int nums2 = 0;
        int streak1 = 0;
        int streak2 = 0;
        int maximumFruit = 0;
        for(int i=0; i < fruits.length; i++){
            if( fruits[i] != trees[0] && fruits[i] != trees[1] ){
                maximumFruit = Math.max(maximumFruit, nums1+nums2);
                if( streak1 == 0 ){
                    trees[0] = trees[1];
                    trees[1] = fruits[i];
                    nums1 = streak2;
                }
                else{
                    trees[1] = fruits[i];
                    nums1 = streak1;
                }
                nums2 = 1;
                streak2 = 1;
                streak1 = 0;
            }
            else if( fruits[i] == trees[0] ){ nums1++; streak1++; streak2 = 0; }
            else{ nums2++; streak2++; streak1 = 0; }
        }
        maximumFruit = Math.max(maximumFruit, nums1+nums2);
        return maximumFruit;
    }
}

// Leetcode 452 - Minimum Number of Arrows to Brust Balloons

class Solution452 {
    public int findMinArrowShots(int[][] points) {
        if( points.length == 1 ) return 1;
        Arrays.sort(points, (int[] o1, int[] o2) -> o2[0] - o1[0]);
        if( points.length == 2 ){
            if( points[1][1] >= points[0][0] && points[1][0] <= points[0][1] ) return 1;
            else return 2;
        }
        int arrows = 0;
        int start = 0;
        for(int i=0; i < points.length; i++){
            while( i < points.length && points[i][1] >= points[start][0] && points[i][0] <= points[i][1] ){
                i++;
            }
            if( i < points.length ){
                if( points[i][1] < points[start][0] || points[i][0] > points[i][1] ){
                    start = i;
                    arrows++;
                }
            }
        }
        return arrows+1;
    }
}

// Leetcode 2527 - Find Xor-Beauty of Array

class Solution2527 {
    public int xorBeauty(int[] nums) {
        int beauty = nums[0];
        for(int i=1; i < nums.length; i++){
            beauty ^= nums[i];
        }
        return beauty;
    }
}
