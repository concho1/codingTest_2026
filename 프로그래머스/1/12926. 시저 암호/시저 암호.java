import java.util.*;

class Solution {
    public String solution(String s, int n) {
        // A~Z 65~90, a~z 97~122
        char[] chArr = s.toCharArray();
        for(int i=0; i<chArr.length; i++){
            if('A' <= chArr[i] && chArr[i] <= 'Z'){
                int num = chArr[i] + n;
                if(num>90) num-=26;
                chArr[i] = (char)num;
            }else if('a' <= chArr[i] && chArr[i] <= 'z'){
                int num = chArr[i] + n;
                if(num>122) num-=26;
                chArr[i] = (char)num;
            }
        }
        return new String(chArr);
    }
}