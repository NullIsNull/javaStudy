package linklist;

public class Solution0202 {


    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public int kthToLast(ListNode head, int k) {
        /**
         * 链表。遍历两趟的方法已经试过了。现在用快慢指针解决下。
         * 1. 慢指针i=head，快指针j=head。
         * 2. 把快指针向前挪动k-1次。
         * 3. 然后慢指针和快指针，都向前挪动，直到挪到快指针的下一跳为null为止。
         * 4. 返回慢指针的值。
         */
        // 1
        ListNode i = head;
        ListNode j = head;
        // 2
        for (int s = 0; s < k - 1; s++) {
            j = j.next;
        }
        // 3
        while (j.next != null) {
            i = i.next;
            j = j.next;
        }
        // 4
        return i.val;
    }
}
