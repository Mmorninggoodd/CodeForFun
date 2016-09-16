/*
	Given an array of numbers, increase any duplicated numbers such that there is no duplicated numbers in the array, then return sum of all numbers.
	
	Note that you can only increase the value.
	
	For example, 1 2 2 2 -> 1 2 3 4 return sum = 10
				 1 1 1 2 2 -> 1 3 4 2 5 return sum = 15

*/

/*
	Time O(nlgn) Space O(1)
	First sort, then keep recording lower bound
*/
public static int minUniqueSum(int[] array) {
	if(array == null || array.length == 0) return 0;
	Arrays.sort(array);
	int lower = array[0], sum = lower;
	for(int i = 1; i < array.length; i++) {
		while(lower >= array[i]) {  // make sure array is sorted and non-duplicated in array[0:i]
			array[i]++;
		}
		sum += array[i];
		lower = array[i];
	}
    return sum;
}

/*
	HashSet record existing values
	Time O(n) Space O(n)

*/
public static int minUniqueSum(int[] array) {
	if(array == null || array.length == 0) return 0;
	Set<Integer> set = new HashSet<>();
	int sum = 0;
	for(int i = 0; i < array.length; i++) {
		while(!set.add(array[i])) {  // when failed to add, then it already exists before
			array[i]++;
		}
		sum += array[i];
	}
    return sum;
}