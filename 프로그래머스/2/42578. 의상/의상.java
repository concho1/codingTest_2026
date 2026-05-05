import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        // 종류별 옷 개수 카운팅
        HashMap<String, Integer> map = new HashMap<>();
        for(String[] cloth : clothes){
            map.put(cloth[1], map.getOrDefault(cloth[1], 0) + 1);
        }
        // 종류 별로 2, 1 개면
        // (2+1) x (1+1) - 1 = 6-1 = 5
        // 종류별로 3 개면
        // (3+1) - 1 = 3
        for(Integer cnt : map.values()){
            answer *= (cnt + 1);
        }
        
        return answer-1;
    }
}