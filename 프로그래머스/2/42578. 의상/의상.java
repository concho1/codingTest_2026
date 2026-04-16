import java.util.*;

class Solution {
    static HashMap<String, Integer> map = new HashMap<>();
    
    public int solution(String[][] clothes) {
        int answer = 1;
        // 각 종류별로 최대 1가지
        // 의상 종류별로 모으기
        // 같은 이름을 가진 의상은 존재하지 않습니다. => 수 만 카운팅
        // 종류 리스트
        for(String[] strArr : clothes){
            if(map.containsKey(strArr[1])){
                map.put(strArr[1], map.get(strArr[1]) + 1);
            }else{
                map.put(strArr[1], 2);  // 안입는 경우 추가
            }
        }
        
        for(String k : map.keySet()){
            answer *= map.get(k);
        }
        
        return answer-1;
    }
}