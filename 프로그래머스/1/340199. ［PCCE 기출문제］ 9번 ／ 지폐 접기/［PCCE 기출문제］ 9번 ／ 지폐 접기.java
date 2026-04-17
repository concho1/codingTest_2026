import java.util.*;

class Solution {
    public int solution(int[] wallet, int[] bill) {
        // 돌려서 넣을 수 있음에 주의
        // 종료 조건 : bill max <= wallet max && bill min <= wallet min
        int wMax = (wallet[0] >= wallet[1]) ? wallet[0] : wallet[1];
        int wMin = (wallet[0] >= wallet[1]) ? wallet[1] : wallet[0];
        // 지폐를 접을 때는 항상 길이가 긴 쪽을 반으로 접습니다.
        int bMax = (bill[0] >= bill[1]) ? bill[0] : bill[1];
        int bMin = (bill[0] >= bill[1]) ? bill[1] : bill[0];
        int cnt = 0;
        while( !(bMax <= wMax && bMin <= wMin) ){
            cnt++;
            bMax = bMax/2;
            // 자리 교체
            if(bMax < bMin) {
                int tm = bMin;
                bMin = bMax;
                bMax = tm;
            }
        }
        return cnt;
    }
}