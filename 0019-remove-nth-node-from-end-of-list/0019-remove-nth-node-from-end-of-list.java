/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || n<1) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode temp = head;
        ListNode prev = null;
        int length = 0;
        while(temp != null) {
            temp = temp.next;
            length++;
        }
        System.out.println(length);
        if(n>length) return null;
        temp = dummy;
        for(int i =0;i<length - n;i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        return dummy.next;
    }
}