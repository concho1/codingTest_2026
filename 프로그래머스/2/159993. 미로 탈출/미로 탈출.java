import java.util.*;

class Solution {
    static int[] dy = new int[] {-1,1,0,0};
    static int[] dx = new int[] {0,0,-1,1};
    
    public int solution(String[] maps) {
        // 1. char Map 만들기 + 시작 지점 찾기
        int[] start = new int[3];
        char[][] chMaps = new char[maps.length][maps[0].length()];
        for(int i=0; i<maps.length; i++){
            chMaps[i] = maps[i].toCharArray();
            for(int j=0; j<chMaps[i].length; j++){
                if(chMaps[i][j] == 'S'){
                    start[0] = i; start[1] = j; start[2] = 0;
                }
            }
        }
        // 2. 레버까지 bfs
        int[] re1 = bfs(start, chMaps, 'L');
        if(re1[2] == -1) return -1;
        // 3. 출구까지 bfs => visited 는 자동 초기화
        return bfs(re1, chMaps, 'E')[2];
    }
    
    // int[] = {y,x,depth}
    static int[] bfs(int[] start, char[][] maps, char endCh){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        
        q.offer(start);
        visited[start[0]][start[1]] = true;
        
        while(!q.isEmpty()){
            int[] cc = q.poll();
            
            for(int i=0; i<4; i++){
                int[] nc = new int[] {cc[0]+dy[i], cc[1]+dx[i], cc[2]+1};
                if(
                    0 <= nc[0] && nc[0] < maps.length &&
                    0 <= nc[1] && nc[1] < maps[0].length &&
                    !visited[nc[0]][nc[1]] && maps[nc[0]][nc[1]] != 'X'
                ){
                    if(maps[nc[0]][nc[1]] == endCh){
                        return nc;
                    }
                    visited[nc[0]][nc[1]] = true;
                    q.offer(nc);
                }
            }
        }
        return new int[] {-1,-1,-1};
    }
}