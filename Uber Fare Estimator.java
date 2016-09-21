/*
	Given ride time, ride distance, and different cost_per_minute, cost_per_mile.
	Return the total price for each different unit cost.

*/

double[] fareEstimator(int ride_time, int ride_distance, double[] cost_per_minute, double[] cost_per_mile) {
    double[] res = new double[cost_per_mile.length];
    for(int i = 0; i < cost_per_mile.length; i++) {
        res[i] = cost_per_mile[i] * ride_distance + cost_per_minute[i] * ride_time;
    }
    return res;
}