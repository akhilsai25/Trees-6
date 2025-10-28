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

// This solution uses logic to calculate running column number while traversing through the binary tree in BFS order
// We generate a bucker sort and store list of elements at each column in a map
// After generating map, we iterate from min to max to accumulate list of integers
class Task {
    TreeNode node;
    int val;

    public Task(TreeNode node, int val) {
        this.node = node;
        this.val=val;
    }
}
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root==null) return new ArrayList();
        Queue<Task> queue = new LinkedList();
        queue.add(new Task(root, 0));

        Map<Integer, List<Integer>> map = new HashMap();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        while(!queue.isEmpty()) {
            Task node = queue.poll();

            map.putIfAbsent(node.val, new ArrayList());
            map.get(node.val).add(node.node.val);

            min = Math.min(min, node.val);
            max = Math.max(max, node.val);

            if(node.node.left!=null) {
                Task task = new Task(node.node.left, node.val-1);
                queue.add(task);
            }
            if(node.node.right!=null) {
                Task task = new Task(node.node.right, node.val+1);
                queue.add(task);
            }
        }

        List<List<Integer>> response = new ArrayList();
        for(int i=min;i<=max;i++) {
            response.add(map.get(i));
        }
        return response;
    }
}
