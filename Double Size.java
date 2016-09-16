/*
	Given an array and a base number. You need to iterate through all elements in the array. 
	If value of element == base number, then you need to double your base number.
	
	
	You need to return the maximum that base number can become, if you can reorder the array. 
*/

/*
	Just sort O(nlgn) and iterate O(n).
*/
public static int doubleSize(int[] array, int b) {
	Arrays.sort(array);
	for(int num : array) {
		if(b == num) b *= 2;
	}
	return b;
}

/*
	HashSet to store all existing numbers. O(n) time O(n) space.
	Then try to double base number if there exists doubled number in hashset.
*/
public static int doubleSize(int[] array, int b) {
	Set<Integer> set = new HashSet<>();
	for(int num : array) {
		set.add(num);
	}
	while(set.contains(b*2)) b *= 2;
	return b;
}