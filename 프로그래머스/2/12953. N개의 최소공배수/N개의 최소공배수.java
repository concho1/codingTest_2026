import java.util.*;

class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        // arr 의 i-1, i 의 최소공배수 찾기
        // 새로운 최소공배수를 i 에 넣고 다음 루프에서 반복
        Arrays.sort(arr);
        for(int i=1; i<arr.length; i++){
            arr[i] = getMinLCM(arr[i-1],arr[i]);
        }
        
        return arr[arr.length-1];
    }
    static int getMinLCM(int a, int b){
        int aa = a; int am = 2;
        int bb = b; int bm = 2;
        
        while(true){
            if(aa == bb){
                return aa;
            }else if(aa > bb){
                bb = b * bm++;
            }else{
                aa = a * am++;
            }
        }
    }
}