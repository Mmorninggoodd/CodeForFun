/*
	Implement quick sort.

	Note that elements equals to pivot can stay in any part of array.
*/

private static void quickSort(int[] array) {
	quickSort(array, 0, array.length - 1);
}
private static void quickSort(int[] array, int left, int right) {
	if(left >= right) return;
	int pivot = array[(left + right) >>> 1], i = left, j = right;
	while(i <= j) {
		while(array[i] < pivot) i++;
		while(array[j] > pivot) j--;
		if(i <= j) {
			int tmp = array[i];
			array[i] = array[j];
			array[j] = tmp;
			i++; j--;
		}
	}
	quickSort(array, left, j);
	quickSort(array, i, right);
}