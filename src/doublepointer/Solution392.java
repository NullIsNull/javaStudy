package doublepointer;

import org.junit.Test;

public class Solution392 {

    @Test
    public void test1() {
        boolean res = isSubsequence("ace", "abcde");
        System.out.println(res);
    }

    public boolean isSubsequence(String s, String t) {
        /**
         * 先写汉语，后写英语。双指针（也可以用动态规划来解，但是很难想到）
         * 1. int i，j
         * 2. while（i < s.length && j < t.length）
         *        如果s[i] == t[j]
         *            i++, j++
         *        否则 j++
         * 3. 如果i==s.length （表示i已经出界场景，说明是子序列）
         *      return true
         *    否则（j出界了，i还没到头）
         *      返回false
         */
        //1
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i == s.length();
    }
}
