/*

Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.

*/


/*

	The first BFS is to build a HashMap that store each node as key, minimum distance to the first word as value. 
	DFS is expanding from the end word, and only go to those neighbors has smallest distance to the start word. So actually it just likes A* search, but here heuristic function is the true minimum distance to our destination.

	Space O(n) (not counting output list) Time O(n) to construction, exponential time to get result list.
*/
public class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        Map<String, List<String>> edges = new HashMap<>();  // edges (backward to start word)
        Map<String, Integer> distance = new HashMap<>();   // distances from each word to start word
        Deque<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        distance.put(beginWord, 0);
        while(!queue.isEmpty()) {
            String curWord = queue.poll();
            StringBuilder sb = new StringBuilder(curWord);
            int curDist = distance.get(curWord);
            for(int i = 0; i < curWord.length(); i++) {
                for(char c = 'a'; c <= 'z'; c++) {
                    sb.setCharAt(i, c);
                    String nextWord = sb.toString();
                    if(wordList.contains(nextWord) && !distance.containsKey(nextWord)) { // not visited before
                        distance.put(nextWord, curDist + 1);
                        if(!edges.containsKey(nextWord)) edges.put(nextWord, new ArrayList<>());
                        edges.get(nextWord).add(curWord);
                        queue.offer(nextWord);
                    }
                    else if(distance.containsKey(nextWord) && distance.get(nextWord) == curDist + 1) {  // same distance, then only add edge
                        edges.get(nextWord).add(curWord);
                    }
                    sb.setCharAt(i, curWord.charAt(i));
                }
            }
        }
        List<List<String>> paths = new ArrayList<>();
        List<String> path = new LinkedList<>();
        path.add(endWord);
        getPaths(endWord, beginWord, edges, distance, path, paths);  // start from end word to start word (backward A* search using true distance to start word)
        return paths;
    }
    private void getPaths(String startWord, String endWord, Map<String, List<String>> edges, Map<String, Integer> distance, List<String> path, List<List<String>> paths) {
        if(!distance.containsKey(startWord)) return;  // not reachable from start point
        if(startWord.equals(endWord)) {               // reach the end
            paths.add(new ArrayList<>(path));
            return;
        }
        int curDistance = distance.get(startWord);
        for(String next : edges.get(startWord)) {
            if(distance.get(next) == curDistance - 1) {
                path.add(0, next);
                getPaths(next, endWord, edges, distance, path, paths);
                path.remove(0);
            }
        }
    }
}