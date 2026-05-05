import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int aIdx = 0;
        
        for(int[] com : commands){
            int i = com[0]-1, j = com[1]-1, k = com[2]-1, len = com[1]-com[0]+1;
            // 1 번 과정
            int[] arr = new int[len];
            for(int idx=i; idx<=j; idx++){
                arr[idx-i] = array[idx];
            }
            // 2 번 과정
            Arrays.sort(arr);
            // 3 번 과정
            answer[aIdx++] = arr[k];
        }
        return answer;
    }
}