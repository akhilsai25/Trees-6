/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
 // This solution uses a simple dfs in order iteration and add to a value only if the number at a node is within range
 // We can remove unnecessary traversal if an element falls out of range as it is BST
class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        
        if(root==null) return 0;

        int result = 0;

        if(root.val<low) {
            result += rangeSumBST(root.right, low, high);
        }

        result+=root.val;

        if(root.val>high) {
            result += rangeSumBST(root.left, low, high);
        }

        return result;
    }
}
