class Solution {
    static int[] dy = new int[] {-1,1,0,0};
    static int[] dx = new int[] {0,0,-1,1};
    
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        int mh = board.length, mw = board[0].length;
        String color = board[h][w];
        for(int i=0; i<4; i++){
            int[] nc = new int[] {dy[i]+h, dx[i]+w};
            if(
                0<=nc[0] && nc[0]<mh &&
                0<=nc[1] && nc[1]<mw &&
                color.equals(board[nc[0]][nc[1]])
              ){
                answer++;
            }
        }
        return answer;
    }
}