import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        // 이웃한 학생에게만 체육복 빌려주기 가능
        // reserve 에서 lost 를 뺴면 = 남는 체육복 있는 사람들
        // lost 에서 reserve 를 뺴면 = 체육복 없는 사람들
        Arrays.sort(lost);
        Arrays.sort(reserve);
        var lSet = toSet(lost);
        var rSet = toSet(reserve);
        rSet.removeAll(lSet);   // 차집합
        lSet.removeAll(toSet(reserve));   // 차집합
        
        // 여별 체육복이 있는 사람
        for(int i : rSet){
            int idx1 = i-1;
            int idx2 = i+1;
            // 왼쪽 잃어버린 사람에게 먼저
            if(idx1 >= 1 && lSet.contains(idx1)){
                lSet.remove(idx1);
            }else if(idx2 <= n && lSet.contains(idx2)){
                lSet.remove(idx2);
            }
        }
        return n - lSet.size();
    }
    
    static LinkedHashSet<Integer> toSet(int[] arr){
        var set = new LinkedHashSet<Integer>();
        for(int a : arr) set.add(a);
        return set;
    }
}