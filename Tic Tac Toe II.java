/*
tic tac toe的m*n版本，也就两个人是在一个m*n的board上玩。

规则如下： （1）获胜方式依然是横竖对角线有三个连在一起的symbol。 （2）每次movement不能任意放置，只能放在 每一列 的 最下方的空白处，也就是说每个玩家每轮最多只有 n （行数）个选择。 要求实现以下API： （1）valid()。检查当前board是否有效，有效board必须满足 (i) 没有人获胜 (ii) 不能违背第二条规则。 （2）nextMove()。返回当前玩家的任意一个movement，要求对手无法获胜，如果找不到报错（我选择了返回-1） 

脑子实在累了，讨论了一下给出了bruteforce的方案。 （1）valid主要难点在怎么判断已经有人赢了，LZ用check8个方向一共16个格子的方法，于是O(16*N^2)。跟小哥交涉，表示常数可以减小，不过16也合理可以写。 
（2）假设当前为player A， 则枚举A的n个选择，每个选择会对应B的n个选择，复杂度依然是O(16*N^2)。但实际上如果不保存board state，不管是A还是B都得先找到每一列放置的位置，每次都扫描一遍就会多出O(N)的时间，总时间会变成O(N^3)，所以需要保存一下状态。
*/

/*
	Can add one array: int[] nextPosition to maintain next possible positions.
	If we can add one more method that move(i,j,p) then it would be much easier.
*/
class TicTacToe {
	int[][] board;
	TicTacToe(int n, int m) {
		this.board = new int[n][m];
	}
	boolean valid() {
		
	}
	int nextMove() {
		
	}
}