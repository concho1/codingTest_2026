import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        // 1. 조건을 만족하는 데이터만 추출
        ArrayList<int[]> list = new ArrayList<>();
        int idx1 = getIdxByStr(ext);
        for(int i=0; i<data.length; i++){
            if(data[i][idx1] < val_ext){
                list.add(data[i]);
            }
        }
        if(list.size() == 0) return new int[0][0];
        // 2. 정렬
        int idx2 = getIdxByStr(sort_by);
        list.sort(Comparator.comparingInt((int[] d) -> d[idx2]));
        
        // 3. 리턴 형식 맞추기
        int[][] answer = new int[list.size()][list.get(0).length];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    static int getIdxByStr(String str){
        if(str.equals("code")) return 0;
        else if(str.equals("date"))  return 1;
        else if(str.equals("maximum"))  return 2;
        else  return 3;
    }
}