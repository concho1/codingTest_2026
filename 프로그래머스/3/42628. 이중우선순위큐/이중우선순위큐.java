import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};
        // 중복 가능하므로 map 으로
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for(String str : operations){
            if(str.startsWith("I")){
                String[] strArr = str.split(" ");
                Integer num = Integer.valueOf(strArr[1]);
                // 추가 
                if(treeMap.containsKey(num)){
                    treeMap.put(num, treeMap.get(num)+1);   
                }else{
                    treeMap.put(num, 1);
                }
            }else if(str.equals("D 1")){
                // 가장 큰 수 삭제
                Map.Entry<Integer, Integer> e = treeMap.lastEntry();
                if(e == null) continue;
                Integer cnt = e.getValue()-1;
                if(cnt == 0){
                    treeMap.remove(e.getKey());
                }else{
                    treeMap.put(e.getKey(), cnt);
                }
            }else{
                // 가장 작은 수 삭제
                Map.Entry<Integer, Integer> e = treeMap.firstEntry();
                if(e == null) continue;
                Integer cnt = e.getValue()-1;
                if(cnt == 0){
                    treeMap.remove(e.getKey());
                }else{
                    treeMap.put(e.getKey(), cnt);
                }
            }
        }
        if(treeMap.isEmpty()) return new int[] {0,0};
        else return new int[] {treeMap.lastKey(),treeMap.firstKey()};
    }
}