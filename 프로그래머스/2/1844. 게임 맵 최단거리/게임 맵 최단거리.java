import java.util.*;

class Solution {
    static int[] dy = new int[] {-1,1,0,0};
    static int[] dx = new int[] {0,0,-1,1};
    static int[] s = new int[] {0,0,1};
    
    public int solution(int[][] maps) {
        return bfs(maps);
    }
    
    static int bfs(int[][] maps){
        int h = maps.length, w = maps[0].length;
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[h][w];
        q.offer(s);
        visited[s[0]][s[1]] = true;
        
        while(!q.isEmpty()){
            int[] cn = q.poll();
            
            for(int i=0; i<4; i++){
                
                int[] nn = new int[] {cn[0]+dy[i], cn[1]+dx[i], cn[2]+1};
                if(
                    0 <= nn[0] && nn[0] < h && 
                    0 <= nn[1] && nn[1] < w &&
                    !visited[nn[0]][nn[1]]  &&
                    maps[nn[0]][nn[1]] == 1
                ){
                    if(nn[0] == (h-1) && nn[1] == (w-1)){
                        return nn[2];
                    }
                    visited[nn[0]][nn[1]] = true;
                    q.offer(nn);
                }
            }
        }
        return -1;
    }
}