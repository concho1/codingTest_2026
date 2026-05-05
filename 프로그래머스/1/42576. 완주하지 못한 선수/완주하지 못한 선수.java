import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        // participant 에는 있지만 completion 에는 없는 친구를 찾으면 됨
        // 동명이인은 주의
        HashMap<String, Integer> pMap = new HashMap<>();
        for(String par : participant){
            pMap.put(par, pMap.getOrDefault(par, 0) + 1);
        }
        HashMap<String, Integer> cMap = new HashMap<>();
        for(String com : completion){
            cMap.put(com, cMap.getOrDefault(com, 0) + 1);
        }
        // 참여했지만 완주자 명단에 없는 사람 찾기
        for(String person : pMap.keySet()){
            if(!pMap.get(person).equals(cMap.getOrDefault(person, 0))) {
                return person;
            }
        }
        
        return answer;
    }
}