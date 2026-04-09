import java.util.*;

class Node {
    boolean flag;   // 점수 가져갔는지?
    int sc;         // 누적 점수차
    int a;          // 쏜 화살
    int aCnt;       // 라이언의 남은 화살
    int rDepth;     // 노드 깊이(반대, 10부터 시작)
    Node pNode;     // 부모노드
    
    Node(boolean flag, int sc, int aCnt, int rDepth, int a, Node pNode){
        this.flag = flag;
        this.sc = sc;
        this.a = a;
        this.aCnt = aCnt;
        this.rDepth = rDepth;
        this.pNode = pNode;
    }
}

class Solution {
    public int[] solution(int n, int[] info) {
        int[] answer = {};
        // 0 점부터 화살을 소비해 점수를 가져가거나 안가져가거나
        // 경우의 수 : 2^11
        // 점수차를 기준으로 priority Quere 사용
        PriorityQueue<Node> nodePq = 
            new PriorityQueue<>(Comparator.comparingInt(no -> -no.sc));
        
        Queue<Node> q = new LinkedList<>();
        Node root = new Node(false, 0, n, 10, 0, null);
        q.offer(root);
        
        while(!q.isEmpty()){
            Node cn = q.poll();
            if (cn.rDepth < 0) {    // 리프노드
                nodePq.add(cn);
                continue;
            }
            
            int aCnt = cn.aCnt - (info[cn.rDepth]+1);   // 가져가면 남는 화살
            int depthSc = 10-cn.rDepth;                 // 과녁 점수
            int defSc1 = depthSc;                             // 가져갈떄의 점수차
            int defSc2 = (info[cn.rDepth]>0) ? -depthSc : 0;  // 안가져갈떄의 점수차
            
            // 가져갈 수 있으면
            if(aCnt >= 0){
                Node n1 = new Node(
                    true, cn.sc+defSc1, aCnt, 
                    cn.rDepth-1, (info[cn.rDepth]+1), cn
                );
                q.offer(n1);
            }
            // 안가져갈떄
            Node n2 = new Node(
                false, cn.sc+defSc2, 
                cn.aCnt, cn.rDepth-1, 0, cn
            );
            q.offer(n2);
        }
        
        Node re = nodePq.poll();
        if(re.sc <= 0) return new int[] {-1};
        
        int leftArrow = re.aCnt;    // 남은화살 
        
        int[] result = new int[11];
        while(re.pNode != null){
            result[re.rDepth+1] = re.a;
            re = re.pNode;
        }
        
        result[10] = leftArrow;
        
        return result;
    }
}