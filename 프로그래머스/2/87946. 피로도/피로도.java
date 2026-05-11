import java.util.*;

class Solution {
    static ArrayList<int[]> cList = new ArrayList<>();  // 모든 경우의 수
    static int[][] dg;          // 던전 (복사용)
    static boolean[] visited;   // 방문 배열
    static int[] pArr;          // 조합 저장용
    
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        // 최소 피로도, 소모 피로도 고려
        // 던전은 8개 이하 -> 완탐 가능
        pArr = new int[dungeons.length];
        visited = new boolean[dungeons.length];
        dg = dungeons;
        permu(0);
        
        // 던전 탐색 순서 완전탐색
        int maxCnt = 0;
        for(int[] arr : cList){
            int cnt = 0;
            int userHp = k;
            for(int idx : arr){
                int[] hp = dungeons[idx];
                // 1. 최소피로도 & 소모 피로도 검사
                if(userHp >= hp[0] && ((userHp-hp[1]) >= 0)){
                    userHp -= hp[1];
                    cnt++;  // 2. 괜찮으면 카운팅
                }else break;
            }
            if(maxCnt < cnt) maxCnt = cnt;
        }
        
        return maxCnt;
    }
    
    static void permu(int idx){
        if(idx == dg.length){
            //System.out.println(Arrays.toString(pArr));
            cList.add(pArr.clone());
            idx = 0;
            return;
        }
        for(int i=0; i<dg.length; i++){
            if(visited[i]) continue;
            visited[i] = true;
            pArr[idx] = i;
            permu(idx+1);
            visited[i] = false;
        }
    }
}