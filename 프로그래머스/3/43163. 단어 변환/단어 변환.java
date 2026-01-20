import java.util.*;

class Solution {
    static class Node {
        int idx;                // 인덱스
        char[] w;               // 단어
        int d;                  // 깊이
        boolean isV;            // 방문여부
        ArrayList<Node> adjs;   // 인접 노드
        
        Node(int idx, char[] w, int d){
            this.idx = idx;
            this.w = w;
            this.d = d;
            this.isV = false;
            this.adjs = new ArrayList<>();
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        // char[] 변환
        ArrayList<char[]> wList = new ArrayList<>();
        wList.add(begin.toCharArray());
        for(String w : words) wList.add(w.toCharArray());
        wList.add(target.toCharArray());
        
        // words 에 target 있는지 확인
        if(!isInWords(words, target)) return 0;
        
        // 노드 리스트 생성
        ArrayList<Node> nodes = new ArrayList<>();
        for(int i=0; i<wList.size(); i++){
            nodes.add(
                new Node(i, wList.get(i), 0)
            );
        }
        
        // 인접노드 넣기
        for(int i=0; i<nodes.size(); i++){
            for(int j=0; j<nodes.size(); j++){
                Node node = nodes.get(i);
                Node tNode = nodes.get(j);
                if(i == j) continue;
                if(isChangable(node.w, tNode.w)){
                    node.adjs.add(tNode);
                }
            }
        }
        
        return bfs(nodes);
    }
    
    // BFS 최단거리 탐색
    private static int bfs(ArrayList<Node> nodes){
        Queue<Node> q = new LinkedList<>();
        nodes.get(0).isV = true;
        q.offer(nodes.get(0));
        
        while(!q.isEmpty()){
            Node node = q.poll();
            
            for(Node adjN : node.adjs){
                if(!adjN.isV){
                    adjN.d = node.d + 1;    // 깊이 갱신
                    adjN.isV = true;
                    q.offer(adjN);
                    // 종료 조건
                    if(nodes.size()-1 == adjN.idx){
                        return adjN.d;
                    }
                }
            }
        }
        
        return 0;
    }
    
    // words 에 target 있는지 확인
    private static boolean isInWords(String[] words, String target){
        int eqCnt = 0;
        for(String w : words){
            if(w.equals(target)){
                eqCnt++;
                return true;
            }
        }
        return false;
    }
        
    // 다른 단어가 1개 인지 확인
    private static boolean isChangable(char[] word, char[] target){
        int cnt = 0;
        for(int i=0; i<word.length; i++){
            if(word[i] != target[i]){
                cnt++;
                if(cnt > 1) return false;
            }
        }
        return true;
    }
}