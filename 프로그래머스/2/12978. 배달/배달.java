import java.util.*;

class Solution {
    static class Node {
        int num;    // 현재 마을 번호
        int depth;  // 누적 소요시간
        Node(int num, int depth){
            this.num = num;
            this.depth = depth;
        }
    }
    // 출발 마을 -> 도착 마을 hashMap<넘버, 소요시간>
    static HashMap<Integer, HashMap<Integer, Integer>> roadMap = new HashMap<>();
    
    public int solution(int N, int[][] road, int K) {
        // 만약 N = 1 이면 무조건 1
        if(N == 1) return 1;
        
        for(int[] ro : road){
            putRoad(ro[0], ro[1], ro[2]);
            putRoad(ro[1], ro[0], ro[2]);
        }
        
        return bfs(1, N, K);
    }
    
    // start번 마을부터 depth = 누적시간 이 k 이하인 마을들 찾기
    static int bfs(int start, int N, int K){
        Queue<Node> q = new LinkedList<>();
        // 해당 마을까지의 최소 시간
        Integer[] minDepth = new Integer[N+1];
        
        q.offer(new Node(start, 0));
        minDepth[start] = 0;
        
        while(!q.isEmpty()){
            Node cn = q.poll();
            HashMap<Integer, Integer> adjMap = roadMap.get(cn.num);
            for(int adjNum : adjMap.keySet()){
                int nDepth = cn.depth + adjMap.get(adjNum);
                
                // K 넘어버리면 생략
                if(nDepth > K) continue;
                
                // 새로운 시간이 더 적으면
                if(minDepth[adjNum] == null || nDepth < minDepth[adjNum]){
                    minDepth[adjNum] = nDepth;
                }else{  // 더 많으면 생략
                    continue;
                }
                
                q.offer(new Node(adjNum, nDepth));
            }
        }
        System.out.println(Arrays.toString(minDepth));
        int result = 0;
        for(Integer i : minDepth){
            if(i != null && i <= K){
                result++;
            }
        }
        return result;
    }
    
    // 도로 채우기
    static void putRoad(int from, int to, int t){
        if(roadMap.containsKey(from)){
            // 새로운 도로가 더 짧으면 갱신
            HashMap<Integer, Integer> map = roadMap.get(from);
            if(map.containsKey(to)){
                if(map.get(to) > t){
                    map.put(to, t);
                }
            }else{
                map.put(to, t);
            }
        }else {
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(to, t);
            roadMap.put(from, map);
        }
    }
}