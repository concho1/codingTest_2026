import java.util.*;

class Cube {
    int[][] map;
    
    Cube(int r, int c){
        map = new int[r][c];
        int num = 1;
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                map[i][j] = num++;
            }
        }
    }
    
    // 회전
    int round(int[] q){
        int min = Integer.MAX_VALUE;
        q[0]--; q[1]--; q[2]--; q[3]--;
        int cnt = 0;
        int[] idx = new int[] {q[0], q[1]};
        
        int tm = map[idx[0]][idx[1]]; // 1,1 3,3
        while(cnt < ((q[2]-q[0]+q[3]-q[1])*2) ){
            int tm2 = 0;
            if(idx[0] == q[0] && idx[1] < q[3]){
                idx[1]++;
            }else if(idx[1] == q[3] && idx[0] < q[2]){
                idx[0]++;
            }else if(idx[0] == q[2] && idx[1] > q[1]){
                idx[1]--;
            }else{
                idx[0]--;
            }
            tm2 = map[idx[0]][idx[1]];
            if(tm2 < min) min = tm2;
            map[idx[0]][idx[1]] = tm;
            tm = tm2;

            cnt++;
        }
        return min;
    }
}

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] re = new int[queries.length];
        Cube cube = new Cube(rows, columns);
        for(int i=0; i<queries.length; i++){
            int[] q = queries[i];
            re[i] = cube.round(q);
        }
        return re;
    }
}