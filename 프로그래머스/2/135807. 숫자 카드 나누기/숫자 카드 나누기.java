import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        // 1. 철수 카드는 모두 나눌 수 있고, 영희 카드는 모두 나눌 수 없음
        // 2. 영희 카드는 모두 나눌 수 있고, 철수 카드는 모두 나눌 수 없음
        
        // 1. 철수 카드의 최대공약수
        int ag = arrayA[0];
        for(int i=1; i<arrayA.length; i++){
            ag = gcd(ag, arrayA[i]);
        }
        // 2. 영희 카드의 최대공약수
        int bg = arrayB[0];
        for(int i=1; i<arrayB.length; i++){
            bg = gcd(bg, arrayB[i]);
        }
        // System.out.println(ag+ " " + bg);
        // 3. 해당 공약수가 상대방 카드를 나눌 수 있는지 확인
        for(int a : arrayA){
            if(a%bg == 0) {
                bg = 0;
                break;
            }
        }
        for(int b : arrayB){
            if(b%ag == 0) {
                ag = 0;
                break;
            }
        }
        if(ag==0 && bg==0) return 0;
        // 4. 최대 공약수 중 큰거 리턴
        return Math.max(ag, bg);
    }
    
    static int gcd(int a, int b){
        int r = a%b;
        if(r == 0) return b;
        return gcd(b, r);
    }
}