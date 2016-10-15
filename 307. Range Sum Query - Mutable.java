/*

Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.

*/

/*
	Segment Tree implemented in tree.
	
	construction takes O(n) time and space
	getSum and update take O(lgn) each time
	
	Node that we can also implement it in array form. Or use Binary indexed tree (BIT)
	
	Good reference :
	https://www.topcoder.com/community/data-science/data-science-tutorials/range-minimum-query-and-lowest-common-ancestor/#Segment_Trees
	
	https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/
	
*/
public class NumArray {
    SegmentTree tree;
    public NumArray(int[] nums) {
        this.tree = new SegmentTree(nums);
    }

    void update(int i, int val) {
        this.tree.update(i, val);
    }

    public int sumRange(int i, int j) {
        return this.tree.getSumRange(i, j);
    }

    class SegmentTree {
        Node root;
        SegmentTree(int[] nums) {
            this.root = buildTree(nums, 0, nums.length - 1);
        }
        public int getSumRange(int i, int j) {
            return getSumRange(root, i ,j);
        }
        private int getSumRange(Node node, int i, int j) {
            if(node == null || j < node.start || i > node.end) return 0;
            if(node.start == i && node.end == j) return node.sum;
            return getSumRange(node.left, i, Math.min(node.left.end, j)) + getSumRange(node.right, Math.max(node.left.end + 1, i), j);
        }
        public void update(int index, int val) {
            update(root, index, val);
        }
        private Node buildTree(int[] nums, int left, int right) {
            if(left > right) return null;
            if(left == right) return new Node(left, right, nums[left]);
            int mid = (left + right) >>> 1;
            Node node = new Node(left, right, 0);
            node.left = buildTree(nums, left, mid);
            node.right = buildTree(nums, mid + 1, right);
            node.sum = node.left.sum + (node.right == null ? 0 : node.right.sum);
            return node;
        }
        private void update(Node node, int index, int value) {
            if(index == node.start && index == node.end) node.sum = value;
            else {
                if(index > node.left.end) update(node.right, index, value);
                else update(node.left, index, value);
                node.sum = node.left.sum + (node.right == null ? 0 : node.right.sum);
            }
        }
        class Node {
            int start, end, sum;
            Node left = null, right = null;
            Node(int start, int end, int sum) {
                this.start = start;
                this.end = end;
                this.sum = sum;
            }
        }
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);