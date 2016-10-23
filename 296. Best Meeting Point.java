/*
A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

For example, given three people living at (0,0), (0,4), and (2,2):

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (0,2) is an ideal meeting point, as the total travel
distance of 2+2+2=6 is minimal. So return 6.

*/

/*
	This problem is very similar to 317. Shortest Distance from All Buildings,
	but here we don't have any obstacle, and meeting points can be any points 
	including where people located at.
	
	This reduce this problem's complexity, we don't need to consider the actual
	path of each person. What we only consider is the Manhattan distance of each person.
	
	If we think about this problem further, we will know that we are looking for a coordinate (x,y)
	such that sum absolute deviations of all persons' coordinate to be minimized. In maths' format:
	argmin_x,y(sum_p(|p.x - x| + |p.y - y|)) where p is all persons.
	
	It is not hard to know that median satisfy this property.
	Here is a simple proof: 
		
		Just take a derivation w.r.t. x and y, we can get sum_p(sign(p.x - x) + sign(p.y - y)),
		only when x and y are medians, the number of positive signs and negative signs equal => derivation = 0.
		Since this is a simple convex optimization problem, this lead to a maximum. End of proof.
		
	Time O(m*n) Space O(m*n) / O(number of persons)
*/

public int minTotalDistance(int[][] grid) {
	ArrayList<Integer> persons = new ArrayList<>();
	int totalDist = 0;
	for(int dim = 0; dim < 2; dim++) {
		if(dim == 0) { // x 
			for(int x = 0; x < grid[0].length; x++) {
				for(int y = 0; y < grid.length; y++) {
					if(grid[x][y] == 1) persons.add(x);
				}
			}
		}
		else {  // y
			for(int y = 0; y < grid.length; y++) {
				for(int x = 0; x < grid[0].length; x++) {
					if(grid[x][y] == 1) persons.add(y);
				}
			}
		}
		for(int left = 0, right = persons.size() - 1; left < right;) {
			totalDist += persons.get(right--) - persons.get(left++);
		}
		persons.clear();
	}
	return totalDist;
}

/*
	Solution 2: Bucket sort.
	Time O(m*n) Space O(m + n)
	https://discuss.leetcode.com/topic/27722/o-mn-java-2ms/2
*/

/*
	Can improve to O(1) space.
	But need one more pass to get total number of persons.
	Then subtract to totalDist when current count <= floor(numPerson / 2)
		 add to totalDist when curCount > ceiling(numPerson / 2)
		 
	 Just image:
	 1-5: we only need 1,2,4,5
	 1-4: we need 1,2,3,4
*/
public static int minTotalDistance(int[][] grid) {
	int numPerson = 0, totalDist = 0;
	for(int iter = 0; iter < 3; iter++) {
		int curCount = 0;
		if(iter <= 1) {  // x or first counting persons
			for(int x = 0; x < grid.length; x++) {
				for(int y = 0; y < grid[0].length; y++) {
					if(grid[x][y] == 1) {
						curCount++;
						if(iter == 0) numPerson++;
						else if(curCount <= numPerson / 2) totalDist -= x;
						else if(curCount > (numPerson + 1) / 2) totalDist += x;
					}
				}
			}
		}
		else {  // y
			for(int y = 0; y < grid[0].length; y++) {
				for(int x = 0; x < grid.length; x++) {
					if(grid[x][y] == 1) {
						curCount++;
						if(curCount <= numPerson / 2) totalDist -= y;
						else if(curCount > (numPerson + 1) / 2) totalDist += y;
					}
				}
			}
		}
	}
	return totalDist;
}