import java.util.*;

class Solution {
    public int[] solution(int[] numlist, int n) {
        Integer[] arr = new Integer[numlist.length];
        for(int i=0; i<arr.length; i++) arr[i] = numlist[i];
        Arrays.sort(
            arr,
            Comparator.comparing((Integer num) -> Math.abs(num-n))
            .thenComparing((Integer num) -> num, Comparator.reverseOrder())
        );
        for(int i=0; i<arr.length; i++) numlist[i] = arr[i];
        return numlist;
    }
}