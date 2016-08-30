/*

Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

For example, Given s = “eceba”,

T is "ece" which its length is 3.

*/

/*
	Basic idea: Maximum sliding window
	Method 1: 
		HashMap to store (char, lastSeenIndex) pairs
		
	Time Complexity: O(k*n) where k is number of distinct characters (2 here), n is length of string.
	Space: O(k)

*/
public int lengthOfLongestSubstringTwoDistinct(String s) {
	Map<Character, Integer> map = new HashMap<>();
	int maxLen = 0, leftBound = 0;
	for(int i = 0; i < s.length(); i++) {
		map.put(s.charAt(i), i);
		if(map.size() > 2) {  // 2 can be changed to k here
			int leftMostLastSeenIndex = i;
			for(int lastSeenIndex : map.valueSet()) leftMostLastSeenIndex = Math.min(leftMostLastSeenIndex, lastSeenIndex);
			leftBound = leftMostLastSeenIndex + 1;        // update left bound of window
			map.remove(s.charAt(leftMostLastSeenIndex));  // remove it from map
		}
		maxLen = Math.max(maxLen, i - leftBound + 1); // get length of current window
	}
	return maxLen;
}

/*
	Method 2:  HashMap to store (char, occurrence in current window)
	When removing leftmost chars, we don't iterate over hashmap value set, instead, we remove left bound's chars one by one until occurrence of removed char become zero.
	
	Each char will be increase key and decrease key once.
	
	Time & space O(n)
	
	This is suitable for case that k is very large.

*/
public int lengthOfLongestSubstringTwoDistinct(String s) {
	Map<Character, Integer> map = new HashMap<>();
	int maxLen = 0, leftBound = 0;
	for(int i = 0; i < s.length(); i++) {
		char c = s.charAt(i);
		map.put(c, map.getOrDefault(c, 0) + 1); // increase occurrence
		while(map.size() > 2) { // can be changed to k
			char leftChar = s.charAt(leftBound++);
			int occurrence = map.get(c);
			if(occurrence == 1) map.remove(c);
			else map.put(c, occurrence - 1);
		}
		maxLen = Math.max(maxLen, i - leftBound + 1);
	}
	return maxLen;
}