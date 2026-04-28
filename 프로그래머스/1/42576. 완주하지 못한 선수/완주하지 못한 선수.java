import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> compMap = new HashMap<>();
        for(String comp : completion) 
            compMap.put(comp, compMap.getOrDefault(comp, 0) + 1);
        // 참가자들을 완주자 cnt 에서 --
        for(String part : participant){
            int cnt = compMap.getOrDefault(part, 0);
            if(--cnt == -1){
                return part;
            }else{
                compMap.put(part, cnt);
            }
        }
        return "";
    }
}