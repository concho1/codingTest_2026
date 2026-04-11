import java.util.*;

class Solution {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        // 최대 경우의 수 5x5x5xn => 완탐 가능
        for(int r=0; r<5; r++){
            char[][] chArr = new char[5][5];
            for(int i=0; i<places[0].length; i++){
                chArr[i] = places[r][i].toCharArray();
            }
            
            if(checkRoom(chArr)){
                answer[r] = 1;
            }else{
                answer[r] = 0;
            }
        }
        return answer;
    }
    
    static boolean checkRoom(char[][] chArr){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(chArr[i][j] == 'P'){
                    if(!isAbleBfs(chArr, new int[] {i, j, 0})){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    // bfs, c[] = y,x,depth
    static boolean isAbleBfs(char[][] chArr, int[] s){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        q.offer(s);
        visited[s[0]][s[1]] = true;
        
        while(!q.isEmpty()){
            int[] c = q.poll();
            for(int i=0; i<4; i++){
                int[] n = new int[] {c[0]+dy[i], c[1]+dx[i], c[2]+1};
                if(
                    n[2]<=2 && 0<=n[0] && n[0]<5 && 0<=n[1] && n[1]<5 
                    && !visited[n[0]][n[1]] && chArr[n[0]][n[1]] != 'X'
                ){
                    if(chArr[n[0]][n[1]] == 'P') return false;
                    q.offer(n);
                }
            }
        }
        return true;
    }
}