import java.util.*;

class Solution {
    static private class Music{
        String genre;   // 장르
        int genCnt;     // 장르 누적 재생 수
        int idx;        // 고유 인덱스
        int plays;      // 재생 수
        Music(String genre, int genCnt, int idx, int plays){
            this.genre = genre;
            this.genCnt = genCnt;
            this.idx = idx;
            this.plays = plays;
        }
    }
    public int[] solution(String[] genres, int[] plays) {
        // 1. 장르별로 재생 수 누적
        HashMap<String, Integer> genMap = new HashMap<>();
        for(int i=0; i<genres.length; i++){
            genMap.put(genres[i], genMap.getOrDefault(genres[i],0) + plays[i]);
        }
        // 2. Class list 형태로 만들기
        ArrayList<Music> mList = new ArrayList<>();
        for(int i=0; i<plays.length; i++){
            Music m = new Music(
                genres[i], genMap.get(genres[i]), i, plays[i]
            );
            mList.add(m);
        }
        // 3. genCnt 내림차순 -> plays 내림차순 -> idx 오름차순 정렬
        mList.sort(
            Comparator.comparingInt((Music m) -> -m.genCnt)
            .thenComparingInt((Music m) -> -m.plays)
            .thenComparingInt((Music m) -> m.idx)
        );
        // 4. 두개씩만 저장
        ArrayList<Integer> tmList = new ArrayList<>();
        String genTm = "";
        int cnt = 1;
        for(int i=0; i<mList.size(); i++) {
            Music m = mList.get(i);
            if(genTm.equals(m.genre)) cnt++;
            else cnt = 1;
            if(cnt > 2) continue;
            genTm = m.genre; 
            tmList.add(m.idx);
        }
        int[] answer = new int[tmList.size()];
        for(int i=0; i<tmList.size(); i++){
            answer[i] = tmList.get(i);
        }
        return answer;
    }
}