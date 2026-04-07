import java.util.*;

class Solution {
    public int solution(int[][] cost, int[][] hint) {
        // 2^15 = 32000~ 가지
        permu(0, new boolean[hint.length]);
        stageMaxHints = cost[0].length;
        
        for(boolean[] buyArr : buyList){
            buyCase.add(new Hint(buyArr, hint));
        }
        
        // 최소 비용 산출
        int minCaseCost = Integer.MAX_VALUE;
        for(Hint oh : buyCase){
            int caseCost = oh.spandCost;
            for(int i=0; i<cost.length; i++){
                caseCost += cost[i][oh.stageHints.getOrDefault(i, 0)];
                if(caseCost > minCaseCost) break;
            }
            if(minCaseCost > caseCost) minCaseCost = caseCost;
        }
        
        return minCaseCost;
    }
    
    static ArrayList<Hint> buyCase = new ArrayList<>();
    static ArrayList<boolean[]> buyList = new ArrayList<>();
    static Integer stageMaxHints = 0;
    
    static void permu(int depth, boolean[] arr) {
        if (depth == arr.length) {
            // System.out.println(Arrays.toString(arr));
            buyList.add(arr.clone());
            return;
        }

        arr[depth] = false;
        permu(depth + 1, arr);

        arr[depth] = true;
        permu(depth + 1, arr);
    }
    
    static class Hint {
        int spandCost = 0;
        HashMap<Integer, Integer> stageHints = new HashMap<>();
        
        Hint(boolean[] buyArr, int[][] hint){
            for(int i=0; i<hint.length; i++){
                if(buyArr[i]){
                    spandCost += hint[i][0];
                    for(int j=1; j<hint[i].length; j++){
                        int cnt = stageHints.getOrDefault(hint[i][j]-1, 0) + 1;
                        stageHints.put(hint[i][j]-1, cnt >= stageMaxHints ? cnt-1 : cnt);
                    }
                }
            }
        }
    }
}