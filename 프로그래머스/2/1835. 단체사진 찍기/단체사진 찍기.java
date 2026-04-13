import java.util.*;
import java.lang.*;


class Solution {
    static ArrayList<HashMap<Character, Integer>> mapList = new ArrayList<>();
    static char[] chArr = new char[8];
    static char[] h = new char[] {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static boolean[] visited = new boolean[8];
    static void permu(int idx, int r){
        if(idx == r){
            HashMap<Character, Integer> map = new HashMap<>();
            for(int i=0; i<8; i++){
                map.put(chArr[i], i);
            }
            mapList.add(map);
            return;
        }
        for(int i=0; i<8; i++){
            if(visited[i]) continue;
            visited[i] = true;
            chArr[idx] = h[i];
            permu(idx+1, r);
            visited[i] = false;
        }
    }
    
    static {
        permu(0, 8);
    }
    // 8개 -> 8! -> 40320 * 조건 100 -> 4032000
    public int solution(int n, String[] data) {
        int answer = 0;
        char[][] checkCh = new char[data.length][3];
        int[] checkNum = new int[data.length];
        
        // 조건 만들기
        for(int i=0; i<data.length; i++){
            String d = data[i];
            char[] chArr = d.toCharArray();
            checkNum[i] = (int)chArr[4] - 48;
            checkCh[i][0] = chArr[3];
            checkCh[i][1] = chArr[0];
            checkCh[i][2] = chArr[2];
        }
        //System.out.println(mapList.size() + " " + checkNum[0]);
        // 확인
        for(HashMap<Character, Integer> map : mapList){
            boolean isOk = true;
            for(int i=0; i<checkNum.length; i++){
                int abs = Math.abs(map.get(checkCh[i][1])-map.get(checkCh[i][2]))-1;
                if(checkCh[i][0] == '='){
                    if(abs != checkNum[i]){
                        isOk = false;
                        break;
                    }
                }else if(checkCh[i][0] == '>'){
                    if(abs <= checkNum[i]){
                        isOk = false;
                        break;
                    }
                }else if(checkCh[i][0] == '<'){
                    if(abs >= checkNum[i]){
                        isOk = false;
                        break;
                    }
                }
            }
            if(isOk) answer++;
        }
        return answer;
    }
}