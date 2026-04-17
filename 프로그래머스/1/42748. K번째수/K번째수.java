import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        // 1. commands에 따라 i번째 숫자부터 j번째 숫자까지 자르고
        // 2. 정렬 후 k번째에 있는 수를 구하기
        int answerIdx = 0;
        for(int[] com : commands){
            int i = com[0];
            int j = com[1];
            int k = com[2];
            int[] arr = cutAndSortArr(array, i, j);
            answer[answerIdx] = arr[k-1];
            answerIdx++;
        }
        return answer;
    }
    // 배열 자르기 및 정렬
    static int[] cutAndSortArr(int[] array, int i, int j){
        int length = j-i+1;
        int[] result = new int[length];
        int idx = 0;
        // index out of range 방지용
        if(j > array.length) j = array.length;
        for(int n=i-1; n<j; n++){
            result[idx] = array[n];
            idx++;
        }
        Arrays.sort(result);
        return result;
    }
}