import java.util.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[arr1.length];
        for(int i=0; i<arr1.length; i++){
            int code = arr1[i] | arr2[i];
            char[] ch = new char[n];
            for(int j=0; j<n; j++) ch[n-j-1] = (((1<<j)&code) > 0) ? '#' : ' ';
            answer[i] = new String(ch);
        }
        return answer;
    }
}