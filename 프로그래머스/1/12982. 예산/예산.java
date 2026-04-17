import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        Arrays.sort(d);
        // System.out.println(Arrays.toString(d));
        int sum = 0;
        for(int i=0; i<d.length; i++){
            sum += d[i];
            if(sum > budget){
                return i;
            }else if(sum == budget){
                return i+1;
            }
        }
        // 예산이 너무 큰 경우
        return d.length;
    }
}