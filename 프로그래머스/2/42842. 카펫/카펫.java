class Solution {
    public int[] solution(int brown, int yellow) {
        int whSum = (brown + 4)/2;
        int whMul = (yellow + brown);
        int w = Math.abs(((int)Math.sqrt(whSum*whSum - 4*whMul) - whSum) / 2);
        int h = whSum-w;
        return new int[] {Math.max(w,h), Math.min(w,h)};
    }
}
// 1. w, h 일떄 테두리와 안쪽을 구해보기
    // 2,2 -> b:4, y:0
    // 3,3 -> b:8, y:1
    // 4,4 -> b: 2*(w+h)-3, y: w*h-b
    // b = 2(w+h)-4 -> (b+4)/2 = w+h
    // y = w*h-b -> y+b = w*h
// 2. 2차 방정식 만들기
    // w+h = sum, wh = mul -> w(sum-w) = mul -> w^2 + sum*w -mul = 0
    // h = sum-w
// 3. 근해공식으로 구하기