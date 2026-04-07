import java.util.*;

class Cal {
    int N;
    HashMap<Integer, LinkedHashSet<Integer>> nMap;
    
    Cal(int N){
        this.N = N;
        nMap = new HashMap<>();
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        set.add(N);
        nMap.put(1, set);
    }
    
    // N 을 n 개 조합한 경우
    LinkedHashSet<Integer> getNumberOfCase(int n){
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        if(n == 1) return nMap.get(n);
        // NNN 넣기
        for(int preNum : nMap.get(n-1)){
            set.add(preNum*10 + N);
            break;
        }
        
        for(int i=1; i<=n/2; i++){
            int n1 = i;
            int n2 = n-i;
            for(int num1 : nMap.get(n1)){
                for(int num2 : nMap.get(n2)){
                    set.add(num1 + num2);

                    set.add(num1 - num2);
                    set.add(num2 - num1);

                    if(num1 != 0) set.add(num2 / num1);
                    if(num2 != 0) set.add(num1 / num2);

                    set.add(num1 * num2);
                }
            }
        }
        
        nMap.put(n, set);
        return set;
    }
}

class Solution {
    public int solution(int N, int number) {
        // +, -, /, *
        // N 이 하나일때 5
        // N 이 2개일떄 55, 5+5, 5-5, 5/5, 5*5
        // N 이 3개일때 555, (5+5)+5, ... 2개일떄와 하나일떄를 조합
        // N 이 4개일때 2,2 일떄 조합 + 1,3 일떄 조합
        // N 이 5개 3,2 개 조합, 4,1개 조합
        
        Cal cal = new Cal(N);
        for(int i=1; i<=8; i++){
            if(cal.getNumberOfCase(i).contains(number))
                return i;
        }
        
        return -1;
    }
}