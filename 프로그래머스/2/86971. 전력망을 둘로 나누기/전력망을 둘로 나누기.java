import java.util.*;
import java.math.*;

// 풀이 컨셉 
// 1. Node 마다 (자신 포함)하위 모든 노드들의 수 = nCnt 를 저장
// 2. 전체 노드수 - nodeCnt 가 가장 작은것을 리턴
class Solution {
    static class Node {
        int idx;
        int depth;      // 트리 계층
        int nCnt;       // (자신 포함)하위 모든 노드들의 수
        Node pNode;     // 부모 노드

        Node(int idx, int depth, Node pNode){
            this.idx = idx;
            this.depth = depth;
            this.pNode = pNode;
            this.nCnt = 1;
        }
    }
    
    static HashMap<Integer, ArrayList<Integer>> adjMap = new HashMap<>(); // 이웃노드
    static HashMap<Integer, ArrayList<Node>> depthMap = new HashMap<>();  // 깊이별 노드
    static HashMap<Integer, Node> nodeMap = new HashMap<>();              // idx 별 노드
   
    
    public int solution(int n, int[][] wires) {
        // 1. 이웃노드 정보 생성
        for(int[] w : wires){
            putAdjNode(w[0]-1, w[1]-1);
            putAdjNode(w[1]-1, w[0]-1);
        }
        // 2. 노드 생성
        int maxDepth = bfs(0, n);
        
        // 3. 최대 depth 부터 depth == 2 까지 순회하면서
        //    각 노드의 (자신 포함)하위 모든 노드들의 수 를 구하기
        for(int i=maxDepth; i > 1; i--){
            for(Node no : depthMap.get(i)){
                no.pNode.nCnt += no.nCnt;
            }
        }
        
        // 4. 이제 노드들의 nCnt 값으로 정답 리턴
        int minAnswer = Integer.MAX_VALUE;
        for(int i=0; i<n; i++){
            Node no = nodeMap.getOrDefault(i, new Node(0,0,null));
            // System.out.println("no. : " + (i+1) + " nCnt : " + no.nCnt);
            int an = Math.abs(n-no.nCnt - no.nCnt);
            if(minAnswer > an) minAnswer = an;
        }
        
        return minAnswer;
    }
    
    // BFS 통해 depth 포함 노드 생성
    static int bfs(int start, int n){
        int maxDepth = 1;
        
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        visited[start] = true;
        q.offer(start);
        Node sNode = new Node(start, 1, null);
        
        nodeMap.put(start, sNode);
        putDepthMap(1, sNode);
        
        while(!q.isEmpty()){
            Node cn = nodeMap.get(q.poll());
            
            for(int adjIdx : adjMap.get(cn.idx)){
                if(!visited[adjIdx]){
                    // depth 포함 노드 생성
                    int nDepth = cn.depth+1;
                    if(maxDepth < nDepth) maxDepth = nDepth;
                    Node nNode = new Node(adjIdx, nDepth, cn);
                    
                    nodeMap.put(adjIdx, nNode);
                    putDepthMap(nDepth, nNode);
                    
                    q.offer(adjIdx);
                    visited[adjIdx] = true;
                }
            }
        }
        return maxDepth;
    }
    
    static void putDepthMap(int depth, Node node){
        if(depthMap.containsKey(depth)){
            depthMap.get(depth).add(node);
        }else{
            ArrayList<Node> list = new ArrayList<>();
            list.add(node);
            depthMap.put(depth, list);
        }
    }
    
    static void putAdjNode(int fr, int to){
        if(adjMap.containsKey(fr)){
            adjMap.get(fr).add(to);
        }else{
            ArrayList<Integer> list = new ArrayList<>();
            list.add(to);
            adjMap.put(fr, list);
        }
    }
}