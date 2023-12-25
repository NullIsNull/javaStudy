package graphbestfirstserch;

public class Solution264 {

    public int nthUglyNumber(int n) {
        /**
         * 先写汉语，后写英语。先用本能方法求解下。
         * 1. 如果n = 1，返回1
         * 2.否则，int count = 1
         *        for i Integer.Max
         *           如果这个数是2，3,或者5的倍数
         *              count++
         *              if (count == n-1) return i
         *
         */
        // 1
        if (n == 1) {
            return 1;
        }
        // 2
        int count = 1;
        for (int i = 2; i < Integer.MAX_VALUE; i++) {
            if (i % 2 == 0 || i % 3 == 0 || i % 5 == 0) {
                count++;
                if (count == n) {
                    return i;
                }
            }
        }
        return 0;
    }
}
