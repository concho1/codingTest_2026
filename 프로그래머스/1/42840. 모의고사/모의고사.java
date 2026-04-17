import java.util.*;

class Solution {
    static int[] supo1 = new int[] {1, 2, 3, 4, 5};
    static int[] supo2 = new int[] {2, 1, 2, 3, 2, 4, 2, 5};
    static int[] supo3 = new int[] {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    
    public int[] solution(int[] answers) {
        int[] supoScArr = new int[3];
        for(int i=0; i<answers.length; i++){
            if(answers[i] == getSupoCheck(supo1, i)){
                supoScArr[0]++;
            }
            if(answers[i] == getSupoCheck(supo2, i)){
                supoScArr[1]++;
            }
            if(answers[i] == getSupoCheck(supo3, i)){
                supoScArr[2]++;
            }
        }
        // 최대값
        int maxSc = 0;
        for(int i=0; i<supoScArr.length; i++){
            if(supoScArr[i] > maxSc) maxSc = supoScArr[i];
        }
        // 최대점수와 동일한 사람
        ArrayList<Integer> result = new ArrayList<>();
        for(int i=0; i<supoScArr.length; i++){
            if(supoScArr[i] == maxSc){
                result.add(i+1);
            }
        }
        // 오름차순 정렬
        Collections.sort(result);
        // 결과 형식으로 변환
        int[] answer = new int[result.size()];
        for(int i=0; i<result.size(); i++){
            answer[i] = result.get(i);
        }
        return answer;
    }
    
    // supo 가 찍은 번호 리턴
    static int getSupoCheck(int[] supo, int idx){
        // 0,1,2,3,4,5,6,7,8,9
        // 0,1,2,3,4,0,1,2,3,4
        idx = idx%supo.length;
        return supo[idx];
    }
}