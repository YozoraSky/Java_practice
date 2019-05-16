package cc.openhome;

public class Solution {
	public static void main(String[] args) {
		Solution solution = new Solution();
		TreeNode node1 = solution.new TreeNode(1);
		node1.left = solution.new TreeNode(2);
		node1.right = solution.new TreeNode(2);
		System.out.println(solution.isSymmetric(node1));
	}
	
    public boolean isSymmetric(TreeNode root) {
        return isSameTree(root,root);
    }
	
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if(p==null && q==null) return true;
		if(p==null || q==null) return false;
		if(p.val != q.val) return false;
		return isSameTree(p.left, q.right) && isSameTree(p.right, q.left);
    }
    
    public class TreeNode {
    	int val;
    	TreeNode left;
    	TreeNode right;
    	TreeNode(int x){ 
    		val = x;
    	}
    }
}