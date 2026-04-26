import java.util.*;

class Solution {
    static int[] arr = new int[3];
    static boolean[] visited;
    static int[] numberG;
    static ArrayList<int[]> list = new ArrayList<>();
    
    public int solution(int[] number) {
        int answer = 0;
        numberG = number;
        visited = new boolean[number.length];
        
        // 더해서 0 = 삼총사
        comb1(0, number.length, 3);
        
        for(int[] arrTm : list){
            int sum = 0;
            for(int i : arrTm) sum += i;
            if(sum == 0) {
                answer++;
            }
        }
        
        return answer;
    }
    
    // 배열 출력
    static void print() {
        int[] ar = new int[3];
        int arIdx = 0;
        for(int i = 0; i < numberG.length; i++) {
            if(visited[i] == true){
                ar[arIdx++] = numberG[i];
            }
        }
        list.add(ar);
    }
    
    static void comb1(int start, int n, int r) {
        if(r == 0) {
            print();
            return;
        } else {
            for(int i = start; i < n; i++) {
                visited[i] = true;
                comb1(i + 1, n, r - 1);
                visited[i] = false;
            }
        }
    }
}