/*

	Sweep line algorithm to find closest points
	
	O(nlgn)

	https://www.topcoder.com/community/data-science/data-science-tutorials/line-sweep-algorithms/
*/

static class Point {
	double x, y;
	Point(double x, double y) {this.x = x; this.y = y;}
	public String toString() {
		return "x: " + this.x + " y: " + this.y;
	}
}
private static double getDistance(Point p1, Point p2) {
	return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
}
public static double closestPoints(List<Point> points) {
	Collections.sort(points, ((o1, o2) -> Double.compare(o1.x, o2.x)));   // sort by x
	SortedSet<Point> activePoints = new TreeSet<>((o1, o2) -> Double.compare(o1.y, o2.y));  // sort by y
	List<Point> closestPoints = new ArrayList<>(2);
	double minDist = getDistance(points.get(0), points.get(1));
	int leftBound = 0;
	for (Point point : points) {
		while (points.get(leftBound).x <= point.x - minDist) {  // remove points out of current range
			activePoints.remove(points.get(leftBound));
			leftBound++;
		}
		SortedSet<Point> curSet = activePoints.subSet(new Point(0, point.y - minDist), new Point(0, point.y + minDist));
		for (Point otherPoint : curSet) {  // compare to possible candidates
			double dist = getDistance(otherPoint, point);
			if (dist < minDist) {
				minDist = dist;
				closestPoints.clear();
				closestPoints.add(otherPoint);
				closestPoints.add(point);
				if(minDist == 0) return minDist;
			}
		}
		activePoints.add(point);   // add to current candidates
	}
	System.out.println(closestPoints);
	return minDist;
}