/*

Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".

*/
public class Solution {
	/* 5ms 98.47%
		O(n) time O(n) space
		Just consider all corner cases.
		Use a deque to store dir names. (First as Stack when input, then as queue when output)
	*/
    public String simplifyPath(String path) {
        Deque<String> deque = new ArrayDeque<>();
        int n = path.length();
        for(int i = 1; i < n; i++) {
            while(i < n && path.charAt(i) == '/') i++;  // Skip '/'
            int j = i;
            for(; j < n && path.charAt(j) != '/'; j++);  // find end index of next dir name
            String cur = path.substring(i, j);
            if(cur.equals(".") || cur.length() == 0) continue;  // empty or current dir
            if(cur.equals("..")) {  
                if(!deque.isEmpty()) deque.pop();  // go back to upper level
            }
            else deque.push(cur);  // add into deque
            i = j - 1;
        }
        if(deque.isEmpty()) return "/";  // empty deque
        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()) {
            sb.append('/');
            sb.append(deque.removeLast());  // remove from tail (queue)
        }
        return sb.toString();
    }
}