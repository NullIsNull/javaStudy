package doublepointer;

import org.junit.Assert;
import org.junit.Test;

public class Solution141 {

    @Test
    public void test1() {
        ListNode head = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node0 = new ListNode(0);
        ListNode node_4 = new ListNode(-4);

        head.next = node2;
        node2.next = node0;
        node0.next  = node_4;
        node_4.next = node2;

        boolean b = hasCycle(head);
        System.out.println(b);
        Assert.assertEquals(b, true);
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        return  true;
    }

    public static void main(String[] args) {
        while(true) {
            int a = 1+1;
        }
    }
}
