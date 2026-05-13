import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        // 1. 빈 구간의 길이들을 구해서 해결 가능
        int l = 1;
        int len = w+w+1;
        for(int s : stations){
            int lostLen = s-w-l;
            int cnt = lostLen/len + ((lostLen%len > 0) ? 1 : 0);
            answer += cnt;
            l = s+w+1;
        }
        
        // 2. 끝에도 확인
        int lastS = stations[stations.length-1]+w;
        if(lastS < n){
            int lostLen = n-lastS;
            int cnt = lostLen/(w+w+1) + ((lostLen%(w+w+1) > 0) ? 1 : 0);
            answer += cnt;
        }
        

        return answer;
    }
}