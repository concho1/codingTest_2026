import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        // 1. col번째 컬럼의 값을 기준으로 오름차순 정렬
        // 2. 기본키인 첫 번째 컬럼의 값을 기준으로 내림차순 정렬
        Arrays.sort(
            data, Comparator.comparingInt((int[] d) -> d[col-1])
            .thenComparingInt((int[] d) -> -d[0])
        );
        // 3. S_i를 i 번째 행의 튜플에 대해 각 컬럼의 값을 i 로 나눈 나머지들의 합으로 정의
        // 4. row_begin ≤ i ≤ row_end 인 모든 S_i를 누적
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=row_begin; i<=row_end; i++){
            int siSum = 0;
            for(int j=0; j<data[i-1].length; j++){
                siSum += (data[i-1][j]%i);
            }
            list.add(siSum);
        }
        // 4. bitwise XOR 한 값을 해시 값으로서 반환
        if(list.size() == 0) return 0;
        int xorSum = list.get(0);
        
        for(int i=1; i<list.size(); i++){
            xorSum ^= (int)list.get(i);
        }
        return xorSum;
    }
}