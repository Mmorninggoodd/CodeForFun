/*

Two Sigma's mission is to uncover value in the world's data, and as part of that it's necessary to download massive amounts of information on a regular basis. Naturally, this data should be transferred as quickly and efficiently as possible.

A new data resource was recently added to the network, and your job is to establish a connection between it and Two Sigma's server. Due to security reasons, all connections in Two Sigma's network are unidirectional (i.e. only have a one-way connection), and no data can be stored on any node of the network. This means that every second the amount of data a node receives should be equal to the amount of data it forwards. The only exceptions to this rule are resource and server, since the former only sends data while the latter only receives it.

Unfortunately, some segments of the network are slower than is ideal due to limitations with legacy telecommunication operators around the world. This complicates finding the optimal route through the network significantly, which is why your help is required.

Find a route between the resource and the server that maximizes the amount of data downloaded in a second, and return this maximum value.

Example

For resource = 4, server = 5 and

network = [[0, 2, 4, 8, 0, 0],
           [0, 0, 0, 9, 0, 0],
           [0, 0, 0, 0, 0, 10],
           [0, 0, 6, 0, 0, 10],
           [10, 10, 0, 0, 0, 0],
           [0, 0, 0, 0, 0, 0]]
the output should be dataRoute(resource, server, network) = 19.

Here's what the network looks like:


And here's how data should be transfered within it:


Input/Output

[time limit] 3000ms (java)
[input] integer resource

A 0-based index of the resource node.

Constraints:
0 ≤ resource ≤ 15.

[input] integer server

A 0-based index of the server node.

Constraints:
0 ≤ server ≤ 15,
server ≠ resource.

[input] array.array.integer network

A square matrix of non-zero elements. network[i][j] corresponds to the maximum amount of data that can be transfered from the ith to the jth node in one second, in megabytes.

Note that although all connections go only one way, there can be two routs between two nodes a and b, one transferring data from a to b and another one from b to a.

Constraints:
2 ≤ network.length ≤ 15,
network[i].length = network.length,
0 ≤ network[i][j] ≤ 105,
network[i][i] = 0.

[output] integer

The maximum amount of data that can be transferred in one second, in megabytes.

*/

int dataRoute(int resource, int server, int[][] network) {
	int n = network.length;
	int residual[][] = new int[n][n];
	for (int u = 0; u < n; u++)
		System.arraycopy(network[u], 0, residual[u], 0, n);
	int sum = 0, curAugment = 0;
	while((curAugment = findPath(resource, server, network, residual, new ArrayList<>(), new ArrayList<>(), Integer.MAX_VALUE, new boolean[n])) != 0) {
		sum += curAugment;
	}
	return sum;
}

private  int findPath(int start, int end, int[][] capacity, int[][] residual, List<Integer> path, List<Boolean> reversed, int maxFlow, boolean[] visited) {
	path.add(start);
	visited[start] = true;
	if(start == end) {
		for(int i = 0; i < path.size() - 1; i++) {
			if(reversed.get(i)) residual[path.get(i + 1)][path.get(i)] += maxFlow; // reversed
			else residual[path.get(i)][path.get(i + 1)] -= maxFlow;

		}
		return maxFlow;
	}
	int find = 0;
	for(int next = 0; next < residual[start].length; next++) {
		if(visited[next]) continue;
		int nextFlow = 0;
		if(residual[start][next] != 0) {
			nextFlow = Math.min(maxFlow, residual[start][next]);
			reversed.add(false);
		} else if(capacity[next][start] - residual[next][start] > 0) {
			nextFlow = Math.min(maxFlow, capacity[next][start] - residual[next][start]);
			reversed.add(true);
		}
		if(nextFlow == 0) continue;
		find = findPath(next, end, capacity, residual, path, reversed, nextFlow, visited);
		if(find != 0) break;
		else {
			visited[next] = false;
			path.remove(path.size() - 1);
		}

	}
	return find;
}