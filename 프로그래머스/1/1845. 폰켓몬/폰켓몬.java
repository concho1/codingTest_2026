import java.util.*;

class Solution {
    public int solution(int[] nums) {
        // 포켓몬 종류별로 몇마리인지 카운트
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 가져갈 수 있는 포켓몬 수
        int ableCnt = nums.length/2;
        int cataCnt = map.keySet().size();
        // 포켓몬 종류 >= ableCnt 이면 ableCnt 만큼의 종류 가능
        // 종류가 더 적으면 종류
        return cataCnt >= ableCnt ? ableCnt : cataCnt;
    }
}