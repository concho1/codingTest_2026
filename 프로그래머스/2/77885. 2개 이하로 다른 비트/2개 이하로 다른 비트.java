import java.util.*;

class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        //System.out.println(1L << 62);
        for(int i=0; i<numbers.length; i++){
            answer[i] = f(numbers[i]);
        }
        return answer;
    }
    
    static long f(long num){
        // 짝수면 +1 리턴
        if(num%2 == 0) return num+1;
        // 홀수면 << 1 가다가 처음으로 0인 부분에 1을 채워주고 >> 1 로 다시 와서 0 채우기
        for(long j=0; j<63; j++){
            // 해당 자리의 비트가 0 이면   ex) ~0011-> 1100 & 0100 = 1
            // 0011 | 0100 = 0111
            // ~0111-> 1000 | 0010 = 1010
            // ~1010 -> 0101
            
            // 0111 -> 1000 & 1000 
            if( ((~num) & (1L << j)) != 0){
                num = num | (1L << j);
                num = ~((~num) | (1L << (j-1)));
                return num;
            }
        }
        return 0L;
    }
}