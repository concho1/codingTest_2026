import java.util.*;

class Solution {
    static char[] ginsu = new char[] {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'A', 'B', 'C', 'D', 'E', 'F'
    };
    static int ginbub = 0;
    
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        ginbub = n;
        int max = m*t;
        
        // 모든 순서 만들기
        char[] chArr = new char[max];
        int num = 0;
        for(int i=0; i<max; i++){
            ArrayList<Integer> idxList = getIdxListByNum(num);
            for(int idx : idxList){
                if(i >= max) break;
                chArr[i] = ginsu[idx];
                i++;
            }
            i--;
            num++;
        }
        
        char[] result = new char[t];
        int rIdx = 0;
        for(int i=p-1; i<max; i+=m){
            //System.out.println(rIdx + " " + i + " " + chArr[i]);
            result[rIdx] = chArr[i];
            rIdx++;
        }
        
        return new String(result);
    }
    
    // 456 => 456%10 = 6, 456/10 = 45, 45%10 = 5, 45/10 = 4
    ArrayList<Integer> getIdxListByNum(int num){
        ArrayList<Integer> list = new ArrayList<>();
        while(true){
            list.add(num%ginbub);
            if(num < ginbub) break;
            num = num/ginbub;
        }
        Collections.reverse(list);
        return list;
    }
}