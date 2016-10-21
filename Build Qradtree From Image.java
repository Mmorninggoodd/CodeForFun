/*

一个two-dimenional image, 有black和white 两种数值，  把它最后用一个quadtree表示。 每四个方型pixel可以合成一个，如果四个都是黑，合成一个黑，四个都是白，合成一个白，否则合成灰。

四叉树的叶子节点来自二维数组相邻的上左下右四个方块的值。建树规则是，四个节点的都是0，则父节点也是0，如果四个节点都是1，则父节点也是1.如果四个节点既有0，又有1，则父节点是2.如果四个节点都是2，则父节点是1.

*/
public class QuadTree {
    final static int WHITE = 0, BLACK = 1, GRAY = 2;
    class QuadNode {
        int color;
        boolean isLeaf;
        QuadNode[] children;
        QuadNode() {
            this.children = null;
            this.isLeaf = true;
            this.color = 0;
        }
    }
    private QuadNode root = new QuadNode();
    public QuadTree(int[][] image) {
        this.root = buildTree(image, 0, 0, image[0].length, image.length);
    }
    private QuadNode buildTree(int[][] image, int x, int y, int width, int height) {
        QuadNode curNode = new QuadNode();
        if(width <= 2 && height <= 2) {   // bottom level (leaf)
            int colorCount = 0;
            for(int j = x; j < x + width; j++) {
                for(int i = y; i < y + height; i++) {
                    colorCount += image[i][j];
                }
            }
            if(colorCount == 4) curNode.color = BLACK;
            else if(colorCount == 0) curNode.color = WHITE;
            else curNode.color = GRAY;
            return curNode;
        }
		// parent node
        curNode.children = new QuadNode[4];
        curNode.children[0] = buildTree(image, x, y, width / 2, height / 2);  // NE
        curNode.children[1] = buildTree(image, x + width / 2, y, (width + 1) / 2, height / 2);  //NW
        curNode.children[2] = buildTree(image, x + width / 2, y + height / 2, (width + 1) / 2, (height + 1) / 2);  //SE
        curNode.children[3] = buildTree(image, x, y + height / 2, width / 2, (height + 1) / 2);   //SW
        curNode.isLeaf = false;
        QuadNode[] child = curNode.children;
        if(child[0].color == WHITE && child[1].color == WHITE && child[2].color == WHITE && child[3].color == WHITE) {
            curNode.color = WHITE;
        }
        else if(child[0].color == BLACK && child[1].color == BLACK && child[2].color == BLACK && child[3].color == BLACK) {
            curNode.color = BLACK;
        }
        else if(child[0].color == GRAY && child[1].color == GRAY && child[2].color == GRAY && child[3].color == GRAY) {
            curNode.color = BLACK;
        }
        else curNode.color = GRAY;
        return curNode;
    }

    public static void printTree(QuadTree tree) {  // print out tree
        Deque<QuadNode> q = new ArrayDeque<>();
        if(tree.root != null) q.push(tree.root);
        while(!q.isEmpty()) {
            int size = q.size();
            while(size-- > 0) {
                QuadNode node = q.poll();
                System.out.print(node.color + " ");
                if(!node.isLeaf) {
                    for(QuadNode child : node.children) {
                        if(child != null) q.offer(child);
                    }
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] image = new int[][]{
                {0,0,0,0},
                {0,1,0,0},
                {1,1,0,0},
                {1,1,0,0}
        };
        QuadTree tree = new QuadTree(image);
        printTree(tree);
    }
}
