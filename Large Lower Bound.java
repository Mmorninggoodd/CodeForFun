/*
	Given an array, return true if square of minimum > maximum
	
	Note that array is unsorted, can be negative.
*/

/*
	One pass to get minimum and maximum.
	Just be careful about overflow, and all negative case.
	O(n) time O(1) space
*/
public static boolean existsLargeLowerBound(int[] array) {
	if(array == null || array.length == 0) return false;
	int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	for(int num : array) {
		min = Math.min(min, num);
		max = Math.max(max, num);
	}
	return min * (long) min > max;
}

/*
	Follow up 1: If you can delete first element, how many elements you have to delete
	to make function above return true;
	
	If delete all elements, it is still not possible, then return -1;
	
*/
/*
	Greedy.
	
	Traverse from back of array, record current max and min. Then return smallest index
	such that square of minimum > maximum
	
	Time O(n) Space O(1)
*/
public static int minimumDeleteFrontElements(int[] array) {
	if(array == null || array.length == 0) return -1;
	int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, firstSatisfiedIndex = -1;
	for(int i = array.length - 1; i >= 0; i--) {
		min = Math.min(min, array[i]);
		max = Math.max(max, array[i]);
		if(min * (long) min > max) {
			firstSatisfiedIndex = i;
		}
	}
	return firstSatisfiedIndex;
}

/*
	Follow up 2:
		If you can delete front or tail element now, what's minimum deletion?

*/

/*
	A naive way: Basically you are looking for a maximum window that satisfies the requirement.
	So we can just run algorithm in follow up 1 for n times, starting from each index.
	
	Time O(n^2) Space O(1)
	
	Note that if input elements are all positive, then we can use maximum sliding window to achieve O(n) complexity
*/
public static int minimumDeleteBothSides(int[] array) {
	if(array == null || array.length == 0) return -1;
	int minDeletion = Integer.MAX_VALUE;
	for(int start = 0; start < array.length; start++) {
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, lastEnd = -1;
		for(int end = start; end < array.length; end++) {
			min = Math.min(min, array[end]);
			max = Math.max(max, array[end]);
			if(min * (long) min > max) {
				lastEnd = end;
			}
		}
		if(lastEnd != -1) minDeletion = Math.min(minDeletion, array.length - (lastEnd - start + 1));
	}
	return minDeletion == Integer.MAX_VALUE ? -1 : minDeletion;
}

/*
	The naive way above actually takes a lot of duplicate calculations.

*/

