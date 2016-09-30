/*
function log parsing

function_name start/end timestamp
f1 start 1
f1 start 2
f2 start 4
f2 end  8
f1 end  16
f1 end  32
f3 start 64
f3 end  128.

Output:
f1: [1,4] [8,32] 
f2: [4,8]
f3: [64,128]

*/

/*
	One pass O(n) time O(n) space
	Use a stack to store function stack.
	
	Be careful about:
	(1) merging interval
*/
import java.util.*;
class Interval {
	int start, end;
	Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
	public String toString() {
	    return "[" + this.start + "," + this.end + "]";
	}
}
public class Solution {
    public static Map<String, List<Interval>> logParser(String[][] log) {
	    Map<String, List<Interval>> map = new HashMap<>();
    	if(log == null || log.length == 0) return map;
    	Deque<String> stack = new ArrayDeque<>();
    	int lastTime = 0;
    	for(String[] record : log) {
    		if(record[1].equals("start")) {
    			if(!stack.isEmpty()) {  // last function was running
    				insert(map, lastTime, Integer.parseInt(record[2]), stack.peek());
    			}
    			stack.push(record[0]);
    		}
    		else {
    		    String function = stack.pop();
    		    assert function.equals(record[0]);  // should satisfy this requirement
    			insert(map, lastTime, Integer.parseInt(record[2]), record[0]);  // insert this ended function
    		}
    		lastTime = Integer.parseInt(record[2]);
    	}
    	return map;
    }
    private static void insert(Map<String, List<Interval>> map, int start, int end, String function) {
    	List<Interval> list = map.getOrDefault(function, new ArrayList<>());
    	if(!list.isEmpty() && list.get(list.size() - 1).end >= start) {
    		list.get(list.size() - 1).end = end;
    	}
    	else {
    		Interval interval = new Interval(start, end);
    		list.add(interval);
    		map.put(function, list);
    	}
    }
    public static void main(String[] args) {
        System.out.print(logParser(new String[][]{
            {"f1", "start", "1"},
            {"f1", "start", "2"},
            {"f2", "start", "4"},
            {"f2", "end", "8"},
            {"f1", "end", "16"},
            {"f1", "end", "32"},
            {"f3", "start", "64"},
            {"f3", "end", "128"}
        }));
        
    }
}