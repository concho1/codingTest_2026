import java.util.*;

class Solution {
    public int solution(int[][] maze) {
        int answer = 0;
        Node.m = maze;
        
        // 시작노드
        int[] rs = new int[2];
        int[] bs = new int[2];
        for(int i=0; i<maze.length; i++){
            for(int j=0; j<maze[0].length; j++){
                if(maze[i][j] == 1){
                    rs[0] = i;
                    rs[1] = j;
                }else if(maze[i][j] == 2){
                    bs[0] = i;
                    bs[1] = j;
                }
            }
        }
        
        // BFS
        Queue<Node> q = new LinkedList<>();
        HashSet<Long> rvsSet = new HashSet<Long>();
        rvsSet.add(Node.toKey(rs));
        HashSet<Long> bvsSet = new HashSet<Long>();
        bvsSet.add(Node.toKey(bs));
        Node startNode = new Node(rs, bs, false, false, 0, rvsSet, bvsSet);
        q.offer(startNode);
        
        while(!q.isEmpty()){
            Node n = q.poll();
            if(n.rFlg && n.bFlg) return n.depth;    // 둘다 도착시
            for(Node tmN : n.getSubNodes()) q.offer(tmN);
        }
        
        return 0;
    }
}

class Node {
    static int[] dy = new int[] {1,-1,0,0};
    static int[] dx = new int[] {0,0,-1,1};
    static int[][] m;       // 맵
    
    int[] rc;               // 빨간 수레 좌표
    int[] bc;               // 파란 수레 좌표
    boolean rFlg;           // 빨강 도착 여부
    boolean bFlg;           // 파랑 도착 여부
    int depth;              // 깊이
    HashSet<Long> rvSet;    // 빨간수레 방문
    HashSet<Long> bvSet;    // 파란수레 방문
    
    Node(int[] rc, int[] bc, boolean rFlg, boolean bFlg, int depth, HashSet<Long> rvSet, HashSet<Long> bvSet){
        this.rc = rc;
        this.bc = bc;
        this.rFlg = rFlg;
        this.bFlg = bFlg;
        this.depth = depth;
        this.rvSet = rvSet;
        this.bvSet = bvSet;
    }
    
    static long toKey(int[] c){
        return ((long)c[0] << 32) + c[1];
    }
    
    // 이동 가능한 노드 반환
    ArrayList<Node> getSubNodes(){
        ArrayList<Node> nodes = new ArrayList<>();
        
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                int[] nrc = new int[] {rc[0] + dy[i], rc[1] + dx[i]};
                int[] nbc = new int[] {bc[0] + dy[j], bc[1] + dx[j]};
                // 도착칸 수례는 좌표 고정
                if(rFlg) nrc = rc;
                if(bFlg) nbc = bc;

                    // 벽이나 격자판 밖인가?
                if(
                    nrc[0] >= m.length || nbc[0] >= m.length || 
                    nrc[1] >= m[0].length || nbc[1] >= m[0].length || 
                    nrc[0] < 0 || nbc[0] < 0 || nrc[1] < 0 || nbc[1] < 0 ||
                    m[nrc[0]][nrc[1]] == 5 || m[nbc[0]][nbc[1]] == 5
                ){
                    continue;
                }   // 도착 안했는데 방문했던 칸인가?
                else if( (!rFlg && rvSet.contains(toKey(nrc))) || (!bFlg && bvSet.contains(toKey(nbc))) ){
                    continue;
                }   // 동시에 두 수레를 같은 칸으로 움직였나?
                else if(nrc[0] == nbc[0] && nrc[1] == nbc[1]){
                    continue;
                }   // 수레끼리 자리를 바꿨나?
                else if(
                    rc[0] == nbc[0] && rc[1] == nbc[1] && 
                    bc[0] == nrc[0] && bc[1] == nrc[1]
                ){
                    continue;
                }
                // 도착여부 판단
                boolean nrFlg = (m[nrc[0]][nrc[1]] == 3);
                boolean nbFlg = (m[nbc[0]][nbc[1]] == 4);
                
                // 방문여부 복사 및 갱신 
                HashSet<Long> newRvSet = (HashSet<Long>)rvSet.clone();
                newRvSet.add(toKey(nrc));
                HashSet<Long> newBvSet = (HashSet<Long>)bvSet.clone();
                newBvSet.add(toKey(nbc));
                
                // 새로운 노드 만들기
                Node newNode = new Node(nrc, nbc, nrFlg, nbFlg, depth+1, newRvSet, newBvSet);
                nodes.add(newNode);
            }
        }
        
        return nodes;
    }
}