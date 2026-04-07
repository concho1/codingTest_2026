class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] map = new int[n][m];
        map[0][0] = 1;
        boolean[][] pMap = new boolean[n][m];
        for(int[] pu : puddles) {
            if(pu.length == 0) break;
            pMap[pu[1]-1][pu[0]-1] = true;
        }
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(pMap[i][j]) continue;
                if(i-1 >= 0) map[i][j] = (map[i][j]+map[i-1][j])%1000000007;
                if(j-1 >= 0) map[i][j] = (map[i][j]+map[i][j-1])%1000000007;
            }
        }
        
        return map[n-1][m-1];
    }
}