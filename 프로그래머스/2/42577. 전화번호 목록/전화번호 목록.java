import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        // 공백 제거
        for(int i=0; i<phone_book.length; i++){
            phone_book[i] = phone_book[i].replaceAll(" ", "");
        }
        // 정렬시 12 123 1235 567 88 이런식으로 prfix 가 앞으로 옴
        for(int i=0; i<phone_book.length; i++){
            String tg = phone_book[i];
            for(int j=i+1; j<phone_book.length; j++){
                String tm = phone_book[j];
                if(tm.startsWith(tg)) return false;
                else break; // 이미 정렬 했음으로 다음게 prfix 가 아니면 넘어가기
            }
        }
        return true;
    }
}