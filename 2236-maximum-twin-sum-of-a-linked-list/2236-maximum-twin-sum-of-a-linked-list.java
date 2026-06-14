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
    public int pairSum(ListNode head) {
        List<Integer> nums = new ArrayList<>();
        ListNode temp = head;
        int max = 0;

        while(temp != null) {
            nums.add(temp.val);
            temp = temp.next;
        }        

        for(int i =0;i<nums.size();i++) {
            max = Math.max(max, nums.get(i) + nums.get( nums.size() - 1 - i));
        }

        return max;
    }
}