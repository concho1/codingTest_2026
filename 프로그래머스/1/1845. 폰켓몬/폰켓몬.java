import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int n = nums.length/2;
        HashSet<Integer> nSet = new HashSet<>();
        for(int num : nums) nSet.add(num);
        if(nSet.size() >= n) return n;
        return nSet.size();
    }
}