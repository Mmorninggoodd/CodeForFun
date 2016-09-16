/*
	Sort an array by frequency in ascending order. If some frequencies are the same, then compare their values.
	
	For example,
	[1 1 2] -> [2 1 1]
	[1 1 1 4 2 5 5] -> [2 4 5 5 1 1 1]
	
*/
class Frequency implements Comparable<Frequency>{
	int freq, value;
	public Frequency(int freq, int value) {
		this.freq = freq;
		this.value = value;
	}

	@Override
	public int compareTo(Frequency o) {
		if(this.freq != o.freq) return Integer.compare(this.freq, o.freq);
		return Integer.compare(this.value, o.value);
	}
}
public int[] sortByFrequency(int[] array) {
	Map<Integer, Frequency> freqs = new HashMap<>();
	for(int num : array) {
		Frequency freq = freqs.containsKey(num) ? freqs.get(num) : new Frequency(0, num);
		freq.freq = freq.freq + 1;
		freqs.put(num, freq);
	}
	PriorityQueue<Frequency> pq = new PriorityQueue<>(freqs.values());
	int i = 0;
	while(!pq.isEmpty()) {
		Frequency freq = pq.poll();
		for(int j = 0; j < freq.freq; j++) {
			array[i++] = freq.value;
		}
	}
	return array;
}