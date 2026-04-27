import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        int maxN = 0;
        for(int n : citations){
            if(maxN < n) maxN = n;
        }
        for(int h=maxN; h>=0; h--){
            int cnt = getCnt(h, citations);
            if(cnt >= h) {
                answer = h;
                break;
            }
        }
        return answer;
    }
    
    int getCnt(int h, int[] citations){
        int cnt = 0;
        for(int i=(citations.length-1); i>=0; i--){
            if(citations[i] >= h) cnt++;
            else break;
        }
        return cnt;
    }
}