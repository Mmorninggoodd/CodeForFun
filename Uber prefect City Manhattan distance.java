/*

	Given x, y of two locations, return their smallest Manhattan distance.

*/


double perfectCity(double[] departure, double[] destination) {
    double x = Math.abs(departure[0] - destination[0]), y = Math.abs(departure[1] - destination[1]);
    if((int) departure[0] == (int) destination[0]) {
		x = Math.min(departure[0] + destination[0], 2 - departure[0] - destination[0]); // same horizontal block
		x -= (double)((int) x);
	}
    if((int) departure[1] == (int) destination[1]) {
		y = Math.min(departure[1] + destination[1], 2 - departure[1] - destination[1]); // same vertical block
		y -= (double)((int) y);
	}
    return x + y;
}
