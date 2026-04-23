import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        // calling => 해당 player idx+1 과 인덱스 교체
        var pMap = new HashMap<String, Integer>();
        var idxMap = new HashMap<Integer, String>();
        for(int i=0; i<players.length; i++){
            pMap.put(players[i], i);
            idxMap.put(i, players[i]);
        }
        for(String player : callings){
            int idx = pMap.get(player);
            int nIdx = idx-1;
            String prePlayer = idxMap.get(nIdx);
            // 앞의 플레이어의 정보 갱신 -> 뒤로
            pMap.put(prePlayer, idx);
            idxMap.put(idx, prePlayer);
            // 불린 플레이어의 정보 갱신 -> 앞으로
            pMap.put(player, nIdx);
            idxMap.put(nIdx, player);
        }
        
        String[] answer = new String[players.length];
        for(int idx : idxMap.keySet()){
            answer[idx] = idxMap.get(idx);
        }
        return answer;
    }
}