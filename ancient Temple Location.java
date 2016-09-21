/*

You take part in the archaeological expedition searching for the ruins of an ancient temple. You know that its basement had a circular shape, its center was situated at some point with integer coordinates within the rectangle and its radius was equal to radius. Unfortunately, that amount of information is still not enough to start the excavations.

Your theory is that the temple was erected in the place which has the greatest power. According to the recent research conducted by your colleagues, this civilization evaluated the power of a point (x, y) with integer coordinates as f(x, y) = A * x + B * y + C * x * y + D * x2 + E * y2, where A, B, C, D and E were some fixed integers. They believed the power of a region equaled the sum of the powers of all the points with integer coordinates located inside that region or on its border. Thus, the temple basement must cover the circular region with the greatest power.

Considering all of the above, find the center of the temple basement. If there are several positions satisfying the above requirements, choose the one with the greatest area inside the rectangle. If the answer is still not unique, choose the one with the largest (x + y) value, where x and y are coordinates of the basement center. Finally, if (x1, y1) and (x2, y2) satisfy all of the above-mentioned criteria, choose the point with the smallest x coordinate.

Example

For radius = 2, rectangle = [-1, -1, 1, 0] and parameters = [6, 4, -1, 0, 0],
the output should be ancientTempleLocation(radius, rectangle, parameters) = [1, 0].



For radius = 3, rectangle = [2, 2, 10, 8] and parameters = [0, 0, 0, 0, 0],
the output should be ancientTempleLocation(radius, rectangle, parameters) = [7, 5].

The power of each point within the rectangle equals 0. The area of the part of the temple inside the rectangle is maximized in three cases: with center at (5, 5), (6, 5) and (7, 5) (that way the entire temple basement lies within the rectangle). The last option has the maximal x + y value, that's why this is the answer.

Input/Output

[time limit] 6000ms (java)
[input] integer radius

The radius of the temple basement.

Constraints:
0 ≤ radius ≤ 1000.

[input] array.integer rectangle

A rectangle containing the center of the temple basement. Rectangle sides are parallel to the axis. It is described by four integers: [left, down, right, up], where x = left, x = right, y = down and y = up correspond to the lines containing its sides.

Constraints:
-500 ≤ rectangle[0] ≤ rectangle[2] ≤ 500,
-500 ≤ rectangle[1] ≤ rectangle[3] ≤ 500.

[input] array.integer parameters

An array containing values [A, B, C, D, E] used by the ancient civilization.

Constraints:
-103 ≤ parameters[i] ≤ 103.

[output] array.integer

Coordinates of the basement center in the format [x, y].

*/

int[] ancientTempleLocation(int radius, int[] rectangle, int[] parameters) {
	int maxPower = Integer.MIN_VALUE, maxCoverage = Integer.MIN_VALUE;
	List<Integer[]> res = new ArrayList<>();
	for(int i = rectangle[0]; i <= rectangle[2]; i++) {
		for(int j = rectangle[1]; j <= rectangle[3]; j++) {
			int curPower = getPowerSum(radius, i, j, parameters);
			int curCoverage = getCoverage(radius, i, j, rectangle);
			if(maxPower < curPower || (maxPower == curPower && maxCoverage < curCoverage)) {
				maxPower = curPower;
				maxCoverage = curCoverage;
				res.clear();
				res.add(new Integer[]{i, j});
			}
			else if(maxPower == curPower && maxCoverage == curCoverage) res.add(new Integer[]{i, j});
		}
	}
	Collections.sort(res, (o1, o2) -> {
		if(o1[0] + o1[1] == o2[0] + o2[1]) return Integer.compare(o1[0], o2[0]);
		return Integer.compare(o2[0] + o2[1], o1[0] + o1[1]);
	});
	return  Arrays.stream(res.get(0)).mapToInt(Integer::intValue).toArray();
}
int getCoverage(int radius, int x, int y, int[] rectangle) {
	int coverage = 0;
	for(int i = rectangle[0]; i <= rectangle[2]; i++) {
		for(int j = rectangle[1]; j <= rectangle[3]; j++) {
			if((x - i) * (x - i) + (y - j) * (y - j) <= radius * radius) {
				coverage++;
			}
		}
	}
	return coverage;
}
int getPower(int[] parameters, int x, int y) {
	return parameters[0] * x + parameters[1] * y + parameters[2] * x * y +
			parameters[3] * x * x + parameters[4] * y * y;
}
int getPowerSum(int radius, int x, int y, int[] parameters) {
	int sum = 0;
	for(int i = x - radius; i <= x + radius; i++) {
		for(int j = y - radius; j <= y + radius; j++) {
			if((x - i) * (x - i) + (y - j) * (y - j) <= radius * radius) {
				sum += getPower(parameters, i, j);
			}
		}
	}
	return sum;
}