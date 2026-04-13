import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while(n > 0){
            list.add(n%3);
            n = n/3;
        }
        int mul = 1;
        Collections.reverse(list);
        for(int num : list){
            answer += (mul*num);
            mul *= 3;
        }
        return answer;
    }
}