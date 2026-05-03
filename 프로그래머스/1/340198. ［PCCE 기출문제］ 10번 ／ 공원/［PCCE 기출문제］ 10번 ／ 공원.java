import java.util.*;

class Solution {
    static int[][] map;
    
    public int solution(int[] mats, String[][] park) {
        int answer = -1;
        map = new int[park.length][park[0].length];
        // 돗자리 깔 수 있는곳만 1로
        for(int i=0; i<map.length; i++){
            for(int j=0; j<map[0].length; j++){
                if(park[i][j].equals("-1")){
                    map[i][j] = 1;
                }else{
                    map[i][j] = 0;
                }
            }
        }
        // 누적합을 통한 최대 사각형 길이 찾기
        int maxLen = 1;
        for(int i=1; i<map.length; i++){
            for(int j=1; j<map[0].length; j++){
                if(map[i][j] == 0) continue;
                int min = Arrays.stream(
                    new int[] {map[i-1][j-1], map[i][j-1], map[i-1][j]}
                ).min().getAsInt() + 1;
                map[i][j] = min;
                if(maxLen < min) maxLen = min;
            }
        }
        
        // mats 중 maxLen 이하인 최대값 찾기
        Arrays.sort(mats);
        for(int i=mats.length-1; i>=0; i--){
            if(mats[i] <= maxLen){
                answer = mats[i];
                break;
            }
        }
        
        return answer;
    }
}