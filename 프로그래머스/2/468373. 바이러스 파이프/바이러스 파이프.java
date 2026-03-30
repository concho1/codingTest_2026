import java.util.*;

class Solution {
    ArrayList<ArrayList<Integer>> list2D = new ArrayList<>();
    
    public int solution(int n, int infection, int[][] edges, int k) {
        int answer = 0;
        make2D_List(new ArrayList<Integer>(), k, 0);
        NodeMap nodeMap = new NodeMap(edges, infection);
        int maxInfectCnt = 0;

        // DP 도 추가하면 더 빨라질 듯?
        for(ArrayList<Integer> list : list2D){
            // 초기화
            nodeMap.clearNodes();
            for(int type : list){
                // 통로 열기
                nodeMap.openWithType(type);
            }
            // 감염 수
            int infectCnt = nodeMap.getInfectCnt();
            if(maxInfectCnt < infectCnt) maxInfectCnt = infectCnt;
        }

        return maxInfectCnt;
    }
    
    // 조합 만들기
    void make2D_List(ArrayList<Integer> list, int k, int c){
        if(k==0){
            // System.out.println(list.toString());
            list2D.add(new ArrayList<Integer>(list));
            k = 3;
            return;
        }
        for(int i=1; i<=3; i++){
            if(i!=c){
                list.add(i);
                make2D_List(list, k-1, i);
                list.remove(list.size()-1);
            }
        }
    }
}




class Node {
    int idx;
    boolean isInfect;
    
    Node(int idx, boolean isInfect){
        this.idx = idx;
        this.isInfect = isInfect;
    }
}

class NodeMap {
    int infectionIdx;
    HashMap<Integer, Node> nodes;
    HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> adjNodeIdxes;
    
    NodeMap(int[][] edges, int infection){
        this.infectionIdx = infection;
        adjNodeIdxes = new HashMap<>();
        adjNodeIdxes.put(1, new HashMap<Integer, ArrayList<Integer>>());
        adjNodeIdxes.put(2, new HashMap<Integer, ArrayList<Integer>>());
        adjNodeIdxes.put(3, new HashMap<Integer, ArrayList<Integer>>());
        nodes = new HashMap<>();
        
        for(int i=0; i<edges.length; i++){
            Node node1 = new Node(edges[i][0], (edges[i][0] == infection));
            Node node2 = new Node(edges[i][1], (edges[i][1] == infection));
            
            var adjMap = adjNodeIdxes.get(edges[i][2]);
            addAdjNode(adjMap, node1.idx, node2.idx);
            addAdjNode(adjMap, node2.idx, node1.idx);
            nodes.put(node1.idx, node1);
            nodes.put(node2.idx, node2);
        }
    }
    
    // 노드 감염상태 초기화
    void clearNodes(){
        for(int k : nodes.keySet()){
            if(k==infectionIdx) nodes.get(k).isInfect = true;
            else nodes.get(k).isInfect = false;
        }
    }
    
    // 통로(파이프) 열기
    void openWithType(int type){
        HashMap<Integer, ArrayList<Integer>> adjNodes = adjNodeIdxes.get(type);
        for(int k : adjNodes.keySet()){
            if(nodes.get(k).isInfect){
                // k 노드부터 쭉쭉
                Queue<Integer> q = new LinkedList<>();
                boolean[] visited = new boolean[nodes.size()+1];
                q.offer(k);
                visited[k] = true;
                
                while(!q.isEmpty()){
                    int ck = q.poll();
                    
                    for(int nk : adjNodes.get(ck)){
                        if(!visited[nk]){
                            nodes.get(nk).isInfect = true;
                            q.offer(nk);
                            visited[nk] = true;
                        }
                    }
                }
            }
        }
    }
    
    // 감염 노드 수
    int getInfectCnt(){
        int cnt = 0;
        for(int k : nodes.keySet())
            if(nodes.get(k).isInfect) cnt++;
        return cnt;
    }
    
    void addAdjNode(HashMap<Integer, ArrayList<Integer>> adjMap, int idx1, int idx2){
        if(adjMap.containsKey(idx1)){
            adjMap.get(idx1).add(idx2);
        }else{
            var adjList = new ArrayList<Integer>();
            adjList.add(idx2);
            adjMap.put(idx1, adjList);
        }
    }
}