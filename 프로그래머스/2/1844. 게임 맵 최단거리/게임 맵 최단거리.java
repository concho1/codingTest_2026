import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        graph.add(new ArrayList<>()); // 인덱스 맞추기용
        boolean[] visited = new boolean[maps.length*maps[0].length+1];
        // 좌표 => 인덱스 형식으로 변환
        int nodeIdx = 0;
        int[][] mapIdxes = new int[maps.length][maps[0].length];
        for(int i=0; i<maps.length; i++){
            for(int j=0; j<maps[i].length; j++){
                ++nodeIdx;
                mapIdxes[i][j] = nodeIdx;
                // visited를 이용해 0 인 구역 방문처리
                if(maps[i][j] == 0) visited[nodeIdx] = true;
                else visited[nodeIdx] = false;
            }
        }
        // 인접리스트 그래프 만들기
        for(int i=0; i<maps.length; i++){
            for(int j=0; j<maps[i].length; j++){
                ArrayList<Integer> g = new ArrayList<>();
                // 상
                if(i-1 >= 0 && !visited[nodeIdx])            
                    g.add(mapIdxes[i-1][j]);
                // 하
                if(i+1 < maps.length && !visited[nodeIdx])   
                    g.add(mapIdxes[i+1][j]);
                // 좌
                if(j-1 >= 0 && !visited[nodeIdx])            
                    g.add(mapIdxes[i][j-1]);
                // 우
                if(j+1 < maps[i].length && !visited[nodeIdx])
                    g.add(mapIdxes[i][j+1]);
                // 저장
                graph.add(g);
            }
        }
        // bfs로 최단거리 구하고 리턴
        return bfs(1, maps.length*maps[0].length, graph, visited);
    }
    
    static int bfs(int start, int end, ArrayList<ArrayList<Integer>> graph, boolean[] visited){
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(start);
        // 노드 깊이 저장용
        ArrayList<Integer> nodeDepthArr = new ArrayList<Integer>(
            Collections.nCopies(visited.length, 1)
        );
        while(!q.isEmpty()){
            int nodeIdx = q.poll();
            for(int i=0; i<graph.get(nodeIdx).size(); i++){
                int tm = graph.get(nodeIdx).get(i);
                if(!visited[tm]){
                    visited[tm] = true;
                    q.offer(tm);
                    // 해당 노드의 깊이를 리턴
                    if(tm == end) return nodeDepthArr.get(nodeIdx) + 1;
                    // 깊이 갱신 => 이전노드 깊이 + 1
                    nodeDepthArr.set(tm, nodeDepthArr.get(nodeIdx)+1);
                }
            }
        }
        return -1;
    }
}