/*

Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?

Related problem: 7. Reverse Integer

*/

public class Solution {
    /*
		Move bit one by one to a new int.
	*/
    public int reverseBits(int n) {
        int res = 0;
        for(int i = 0, bit = 1; i < 32; i++, n >>>= 1) { // >>> unsigned shift
            res |= (n & bit) << (31 - i);
        }
        return res;
    }
	
	/*
		If called many times, we can use a HashMap to store calculated results
		Since 2^32 is too large, we can just store bytes 2^8.
	*/
    private HashMap<Integer, Integer> cache = new HashMap<>();
    private static final int CACHE_SIZE = 8;  /* Size of each unit */
	public int reverseBits(int n) {
		int res = 0, bit = (1 << (CACHE_SIZE)) - 1;
		int nBlock = 32 / CACHE_SIZE;
		for(int i = 0; i < nBlock; i++, n >>>= CACHE_SIZE) {
			res |= getCache(n & bit) << (CACHE_SIZE * (nBlock - 1 - i));
		}
		return res;
	}
	private int getCache(int curByte) {   /* each time handle one byte */ 
		if(cache.containsKey(curByte)) return cache.get(curByte);
		int res = 0, oldByte = curByte;
		for(int i = 0, bit = 1; i < CACHE_SIZE; i++, curByte >>>= 1) {
			res |= (curByte & bit) << (CACHE_SIZE - 1 - i);
		}
		cache.put(oldByte, res);
		return res;
	}
}