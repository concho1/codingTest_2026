import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        for(int i : queue1) q1.offer(i);
        for(int i : queue2) q2.offer(i);
        
        long sum1 = Arrays.stream(queue1).sum();
        long sum2 = Arrays.stream(queue2).sum();
        
        if((sum1+sum2)%2 != 0) return -1;
        long target = (sum1+sum2)/2;
        
        
        int cnt = 0;
        for(int i=0; i<(queue1.length+queue2.length+10); i++){
            if(sum1==target && sum2==target){
                return cnt;
            }else if(q1.isEmpty() || q2.isEmpty()){
                return -1;
            }else if(sum1 > target){
                int tm = q1.poll();
                q2.offer(tm);
                sum1 -= tm;
                sum2 += tm;
            }else{
                int tm = q2.poll();
                q1.offer(tm);
                sum1 += tm;
                sum2 -= tm;
            }
            cnt++;
        }
        
        return -1;
    }
}