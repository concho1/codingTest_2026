import java.util.*;
// 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수

class Solution {
    static class Stage{
        int stage;
        double sc;
        Stage(int stage, double sc){
            this.stage = stage;
            this.sc = sc;
        }
        double getSc(){
            return this.sc;
        }
    }
    public int[] solution(int N, int[] stages) {
        
        // 스테이지 -> 클리어 못한 사람 수
        var stageMap = new HashMap<Integer, Integer>();
        for(int s : stages){
            stageMap.put(s, stageMap.getOrDefault(s, 0) + 1);
        }
        // 스테이지 -> 도달한 사람 수
        var scMap = new HashMap<Integer, Integer>();
        for(int s : stages){
            for(int i=1; i<=s; i++){
                scMap.put(i, scMap.getOrDefault(i, 0) + 1);
            }
        }
        // 스테이지 별 실패율
        var stageFale = new HashMap<Integer, Double>();
        for(int s=1; s<=N; s++){
            int fCnt = stageMap.getOrDefault(s, 0);
            if(fCnt == 0) stageFale.put(s, 0.0);
            else stageFale.put(s, (double)fCnt/scMap.get(s));
        }
        
        var list = new ArrayList<Stage>();
        for(int s : stageFale.keySet()){
            list.add(new Stage(s, -stageFale.get(s)));
        }
        list.sort(Comparator.comparingDouble(Stage::getSc));
        
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i).stage;
        }
        return answer;
    }
}