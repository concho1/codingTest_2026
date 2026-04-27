import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        HashMap<Integer, Integer> cntMap = new HashMap<>();
        for(int w : weights){
            cntMap.put(w, cntMap.getOrDefault(w, 0) + 1);
        }
        for (int w : cntMap.keySet()) {
            long cnt = cntMap.get(w);

            // 1 : 1
            // 같은 몸무게끼리는 n명 중 2명 고르는 경우
            answer += cnt * (cnt - 1) / 2;

            // 1 : 2
            int target = w * 2;
            if (cntMap.containsKey(target)) {
                answer += cnt * cntMap.get(target);
            }

            // 2 : 3
            // w * 3 / 2가 정수일 때만 가능
            if (w * 3 % 2 == 0) {
                target = w * 3 / 2;
                if (cntMap.containsKey(target)) {
                    answer += cnt * cntMap.get(target);
                }
            }

            // 3 : 4
            // w * 4 / 3이 정수일 때만 가능
            if (w * 4 % 3 == 0) {
                target = w * 4 / 3;
                if (cntMap.containsKey(target)) {
                    answer += cnt * cntMap.get(target);
                }
            }
        }

        return answer;
    }
}