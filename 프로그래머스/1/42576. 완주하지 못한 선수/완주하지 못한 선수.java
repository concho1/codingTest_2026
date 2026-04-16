import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> comMap = new HashMap<>();
        for(String com : completion){
            if(comMap.containsKey(com)){
                comMap.put(com, comMap.get(com) + 1);
            }else{
                comMap.put(com, 1);
            }
        }
        for(String par : participant){
            if(comMap.containsKey(par) && comMap.get(par) >= 1){
                comMap.put(par, comMap.get(par)-1);
            }else{
                answer = par;
                break;
            }
        }
        
        
        return answer;
    }
}