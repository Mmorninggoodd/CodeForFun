/*

Given List<word>, String para, 返回包涵所有word最短的一段话,返回String 
先把word简化成character来做的，就是LC的minimum window substring

*/

/*
	Minimum Sliding Window.
	
	We need to define what's the metric of length here.
	If it is number of words, then we just do it in normal sliding window, and
	calculate length of window by subtract index of both pointers.
	If it is number of characters, then we need to record another window,
	which record start index of each word in current window.
	
	O(n) time O(m) space where n is length of para, m is number of words.
*/
public static String shortestPara(List<String> words, String para) {
	
}