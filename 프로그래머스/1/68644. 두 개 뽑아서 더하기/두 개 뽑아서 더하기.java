import java.util.*;

class Solution {
    static int[] arrTm;
    static boolean[] visited;
    static HashSet<Integer> cSet = new HashSet<>();
    
    public int[] solution(int[] numbers) {
        arrTm = new int[2];
        visited = new boolean[numbers.length];
        comb(numbers, numbers.length, 2, 0, 0);
        
        int[] answer = new int[cSet.size()];
        int idxTm = 0;
        for(int num : cSet) answer[idxTm++] = num;
        Arrays.sort(answer);
        return answer;
    }
    
    static void comb(int[] numbers, int n, int r, int start, int idx){
        if(r==idx){
            cSet.add(Arrays.stream(arrTm).sum());
            return;
        }
        for(int i=start; i<n; i++){
            if(!visited[i]){
                visited[i] = true;
                arrTm[idx] = numbers[i];
                comb(numbers, n, r, i+1, idx+1);
                visited[i] = false;
            }
        }
    }
}