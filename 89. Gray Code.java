/*

The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.


*/



/*
Copy from:
https://discuss.leetcode.com/topic/608/what-if-i-have-no-knowledge-over-gray-code-before/4

From my intuition, the problem is like Hanoi. If you're able to solve n = 2 case, then you can kind of repeat it twice to achieve n = 3 case. Lets try to extend n = 2 case to n = 3 case first.

When n = 2, the sequence is
00 -> 01 -> 11 -> 10
If you want to extend it to n=3 directly without modifying old part, there are only two possible sequence, and they are not hard to find out.

000 -> 001 -> 011 -> 010 -> 110 -> 111 -> 101 -> 100

000 -> 001 -> 011 -> 010 -> 110 -> 100 -> 101 -> 111

So now, the problem is, which one should we choose. I would choose the first one for two reasons.

The last elements have similar form in both n=2 and n=3 case. They are 1 follows bunch of 0's. Since we hope to extend the same algorithm to n=4 n=5... cases. It's good to preserve some properties.

If we only look at the last 2 digits, we can see that in the first sequence, the second half is exact the reverse of the first half, that means, we can systematically generate the second half according to the first half.

*/

public class Solution {
	/* 1ms
		A simple method from thought above
	*/
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        for(int i = 1, bit = 1; i <= n; i++, bit <<= 1) {
            int size = res.size();
            for(int j = size - 1; j >= 0; j--) {
                res.add(res.get(j) | bit);
            }
        }
        return res;
    }
	
	/*
		Formula of gray code
		G(i) = i^ (i/2)
	*/
	public List<Integer> grayCode(int n) {
		List<Integer> result = new LinkedList<>();
		for (int i = 0; i < 1<<n; i++) result.add(i ^ i>>1);
		return result;
	}
	
	/*
		Backtracking
	*/
	public List<Integer> grayCode(int n) {
		List<Integer> res = new ArrayList<>();
		helper(n, new int[1], n - 1, res);
		return res;
	}

	private void helper(int n, int[] val, int index, List<Integer> res){
		if(index == -1){
			res.add(val[0]);
			return;
		}
		helper(n, val, index - 1, res);
		val[0] ^= (1 << index);
		helper(n, val, index - 1, res);
	}
}