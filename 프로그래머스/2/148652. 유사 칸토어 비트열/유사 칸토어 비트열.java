import java.util.*;

class Solution {
    static class Node{
        int depth;
        long[] cArr;
        long[] iArr;
        Node pn;
        
        Node(int depth, long[] cArr, long[] iArr, Node pn){
            this.depth = depth;
            this.cArr = cArr;
            this.iArr = iArr;
            this.pn = pn;
        }
    }
    static int n = 0;
    
    public int solution(int n, long l, long r) {
        int answer = 0;
        this.n = n;
        // n=1 -> 11011 -> 4
        // n=2 -> 11011 11011 00000 11011 11011 -> 4,4,0,4,4 -> idx: 0~4, 5~9, 10~14, 15~19, 20~5^2-1
        // n=3 -> .... -> 4,4,0,4,4 / 4,4,0,4,4 / 0,0,0,0,0 / 4,4,0,4,4 / 4,4,0,4,4
        // -> 4^2/4^2/0/4^2/4^2 -> idx: 0~24, 25~49... , ~5^3-1
        // n=4 -> 4^3/4^3/0/4^3/4^3 -> idx: 0~5^3-1, (5^3)~ , (5^3)*2 ... (5^3)*4 ~ (5^3)*5-1
        Node node = null;
        for(int i=1; i<=n; i++){
            long[] cArr = new long[] {
                (long)Math.pow(4,i-1), (long)Math.pow(4,i-1), 0,
                (long)Math.pow(4,i-1), (long)Math.pow(4,i-1)
            };
            long[] iArr = new long[] {
                (long)Math.pow(5,i-1)-1, (long)Math.pow(5,i-1)*2-1, (long)Math.pow(5,i-1)*3-1,
                (long)Math.pow(5,i-1)*4-1, (long)Math.pow(5,i-1)*5-1,
            };
            node = new Node(i, cArr, iArr, node);
        }
        
        Node nodeTm = node;
        return (int)(getOneCnt(r-1L, nodeTm) - getOneCnt(l-2L, node));
    }
    
    // r 까지의 '1' 개수 구하기
    static long getOneCnt(long r, Node node){
        long cnt = 0; boolean isOver = false;
        
        for(int i=1; i<=n; i++){
            for(int j=4; j>=0; j--){
                if(node.iArr[j] <= r){
                    for(int c=0; c<=j; c++) cnt += node.cArr[c];
                    if(j + 1 < 5 && node.cArr[j + 1] == 0) isOver = true;
                    r -= (node.iArr[j]+1);
                    break;
                }
            }
            if(isOver) break;
            node = node.pn;
        }
        
        return cnt;
    }
}