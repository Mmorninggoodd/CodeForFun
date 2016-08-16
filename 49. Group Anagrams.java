/*
Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note: All inputs will be in lower-case.
*/
public class Solution {
	/*
		20ms 75%
		
		Use a HashMap to store (sorted string, grouped strings list) pairs.
		Time O(n) Space O(n)
		
		Tricks here:
		(1) First convert string to char[], then use Arrays.sort(), finally use String.valueOf() to convert back to string. (Because equal method of any array is just shadow equal, i.e., only compare the pointer itself, not the content. Also there is no existing sorting string method.)
		
		(2) Directly use value of hashmap to store grouped strings. At the end, just use map.values() to get Collection<V>.
	
	*/
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs.length == 0) return new ArrayList<List<String>>();
        List<List<String>> res = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for(String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = String.valueOf(charArray);
            if(!map.containsKey(sortedStr)) map.put(sortedStr, new ArrayList<String>());
            map.get(sortedStr).add(str);
        }
        return new ArrayList<List<String>>(map.values());
    }
}