//-5    0    5
// 0    0    0
// 0    0    0
// 5    0   -5
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int h = board.length; int w = board[0].length;
        int[][] diff = new int[h+1][w+1];
        
        for(int[] s : skill){
            int de = s[0]==1 ? -s[5] : s[5];
            diff[s[1]][s[2]] += de;
            diff[s[1]][s[4]+1] -= de;
            diff[s[3]+1][s[2]] -= de;
            diff[s[3]+1][s[4]+1] += de;
        }
        // 가로 누적합
        for(int i=0; i<h; i++){
            for(int j=1; j<w; j++){
                diff[i][j] += diff[i][j-1];
            }
        }
        // 세로 누적합 + 정답
        for(int j=0; j<w; j++){
            if(board[0][j]+diff[0][j] > 0) answer++;
            for(int i=1; i<h; i++){
                diff[i][j] += diff[i-1][j];
                if(board[i][j]+diff[i][j] > 0) answer++;
            }
        }
        return answer;
    }
    
    // 테스트용
    static void printArr(int[][] arr){
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[0].length; j++){
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }
}