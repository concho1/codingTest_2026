import java.util.*;

class Solution {
    public int[] solution(String s) {
        HashMap<Integer, HashSet<Integer>> idxMap = new HashMap<>();
        char[] chArr = s.toCharArray();
        
        HashSet<Integer> numSet = new HashSet<>();
        int len = 1, num = 0;
        for(int i=chArr.length-1; i>=0; i--){
            if('0' <= chArr[i] && chArr[i] <= '9'){
                num += ((chArr[i] - '0') * len);
                len *= 10;
                continue;
            }
            if(num != 0) numSet.add(num);
            if(chArr[i] == '{' && !numSet.isEmpty()){
                idxMap.put(numSet.size()-1, numSet);
                numSet = new HashSet<>();
            }
            len = 1; num = 0;
        }
        
        // 정답 채우기
        int[] answer = new int[idxMap.size()];
        HashSet<Integer> nSet = new HashSet<>(); 
        for(int i=0; i<idxMap.size(); i++){
             for(int n : idxMap.get(i)){
                 // 지금까지 안나왔던 숫자가 해당 인덱스의 숫자
                 if(!nSet.contains(n)){
                     answer[i] = n;
                     nSet.add(n);
                 }
             }
         }
        
        return answer;
    }
}