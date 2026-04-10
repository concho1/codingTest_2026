import java.util.*;

class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right - left + 1)];
        // (j<=i) 이면 i+1 , (j>i) 일떄 j+1
        // left = 5 이면 left/n = 1 => i 인덱스
        // left = 5 이면 left%n = 1 => j 인덱스
        int[] a = new int[] {
            (int)(left/n), (int)(left%n), (int)(right/n), (int)(right%n), 0
        };
        boolean flg = true;
        for(int i = a[0]; i<n; i++){
            for(int j = flg ? a[1] : 0; j<n; j++){
                if(j<=i){
                    answer[a[4]++] = i+1;
                }else{
                    answer[a[4]++] = j+1;
                }
                if(i==a[2] && j==a[3]) return answer;
            }
            if(flg) flg = false;
        }
        return answer;
    }
}