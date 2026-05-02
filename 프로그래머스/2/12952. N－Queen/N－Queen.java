import java.util.*;
import java.math.*;

class Solution {
    static class Node {
        int y;
        int x;
        ArrayList<Node> pNodes;
        
        Node(int y, int x, ArrayList<Node> pNodes){
            this.y = y;
            this.x = x;
            this.pNodes = pNodes;
        }
    }
    static int n;
    
    public int solution(int n) {
        // 1. row 마다 순서대로 배치
        // 2. 배치할때마다 배치 가능한지 확인
        // 3. 총 n 개면 통과
        this.n = n;
        return bfs();
    }
    
    static int bfs(){
        int re = 0;
        if(n == 1) return 1;
        Queue<Node> q = new LinkedList<>();
        // 시작 노드들 생성
        for(int i=0; i<n; i++){
            q.offer(new Node(0, i, new ArrayList<Node>()));
        }
        
        while(!q.isEmpty()){
            Node cn = q.poll();
            for(int i=0; i<n; i++){
                ArrayList<Node> npNodes = (ArrayList<Node>) cn.pNodes.clone();
                npNodes.add(cn);
                Node nn = new Node(cn.y+1, i, npNodes);
                if(isAbleNode(nn)){
                    // 만약 y 가 n-1 이면 끝
                    if(nn.y == (n-1)) re++;
                    // 아니면 지속 진행
                    else q.offer(nn);
                }
            }
        }
        return re;
    }
    
    static boolean isAbleNode(Node node){
        // 0. y 가 n 이상인지 확인
        if(node.y >= n) return false;
        for(Node pn : node.pNodes){
            // 1. 부모 노드들과 y가 겹치는지 확인
            if(node.y == pn.y) return false;
            // 2. 부모 노드들과 x가 겹치는지 확인
            if(node.x == pn.x) return false;
            // 3. 부모 노드들과 대각에 위치하는지 확인
            if(Math.abs(pn.x-node.x) ==  Math.abs(pn.y-node.y)) return false;
        }
        return true;
    }
}