/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 
// This approach uses a level order traversal to serialize and deserialze a tree. 
// To handle the null scenarios we make sure to add the null as is to the queue while serialzing and just append # when we encounter
// Similarly for deserialize we just ignore adding the # to a left or right node
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);

        StringBuilder response = new StringBuilder("");
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node==null) {
                response.append("#");
                response.append(",");
                continue;
            } else {
                response.append(node.val);
                response.append(",");
            }

            queue.add(node.left);
            queue.add(node.right);
        }

        return response.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("") || data.equals("#,")) return null;

        String[] arr = data.split(",");

        Queue<TreeNode> queue = new LinkedList();

        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        queue.add(root);
        
        for(int i=1;i<arr.length;i++) {
            TreeNode node = queue.poll();
            if(!arr[i].equals("#")) {
                TreeNode left = new TreeNode(Integer.parseInt(arr[i]));
                node.left=left;
                queue.add(left);
            }

            i++;

            if(!arr[i].equals("#")) {
                TreeNode right = new TreeNode(Integer.parseInt(arr[i]));
                node.right=right;
                queue.add(right);
            }
          
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
