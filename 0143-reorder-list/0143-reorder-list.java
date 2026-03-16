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
    public void reorderList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        if(head == null) return;
        ListNode temp = head;

        while(temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        
        temp = head;
        int size = stack.size()/2;
        for(int i =0;i<size;i++) {
            ListNode last = stack.pop();
            ListNode nextTemp = temp.next;

            temp.next = last;
            last.next = nextTemp;
            temp = nextTemp;
        }
        temp.next = null;

    }
}