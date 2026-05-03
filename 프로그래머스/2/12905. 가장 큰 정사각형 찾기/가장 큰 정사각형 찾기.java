import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        for(int i=1; i<board.length; i++){
            for(int j=1; j<board[0].length; j++){
                if(board[i][j] == 0) continue;  // 0 일떈 생략
                int min = (
                    (board[i-1][j-1] > board[i][j-1]) ? 
                        ((board[i][j-1] > board[i-1][j]) ? board[i-1][j] : board[i][j-1]) : 
                        ((board[i-1][j-1] > board[i-1][j]) ? board[i-1][j] : board[i-1][j-1]) 
                ) + 1;
                board[i][j] = min;
                if(answer < min) answer = min;
            }
        }
        
        // 테두리 확인
        if(answer == 0){
            for(int i=0; i<board.length; i++) 
                if(board[i][0] == 1) {answer = 1; break;}
        }
        if(answer == 0){
            for(int j=1; j<board[0].length; j++)
                    if(board[0][j] == 1) {answer = 1; break;}
        }
        return answer*answer;
    }
}