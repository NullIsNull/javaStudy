package dfs;

public class Solution101 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        return dfs(root, root);
    }

    boolean dfs(TreeNode rootL, TreeNode rootR) {
        /**
         * 先写汉语，后写英语。树的遍历（2dfs再试试）
         * 1. base case。顶到头的场景。
         * 2. 往子问题要答案：
         * 3. 做本层的逻辑，并返回结果。
         *    3.1 a如果rootL的左子树和rootR的右子树是轴对称的。b且rootL的右子树和rootR的左子树是对称的。c且rootL和rootR的val值相等，
         *        那么返回true。
         */
        // 1
        if (rootL == null && rootR == null) {
            return true;
        } else if (rootL == null || rootR == null) {
            return false;
        } else {
            return rootL.val == rootR.val && dfs(rootL.left, rootR.right) && dfs(rootL.right, rootR.left);
        }
    }
}
