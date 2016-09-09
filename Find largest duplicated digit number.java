/*
	Suppose you can duplicate one digit of a given number, please give the largest one you can generated.
	
	e.g. 11234 -> 112344
		 1652 -> 16652

*/

/*
	Search from the least significant digit, find the last digit that start descending, i.e. find the last local maxima.
	(input - input % dup) * 10 is left part,
	(input % (10 * dup)) is right part.
*/
public static int duplicateNumber(int input) {
	int dup = 1, copy = input;
	for(int i = 1, pre = -1; copy != 0; i *= 10) {  // find duplicate index
		int digit = copy % 10;
		copy /= 10;
		if(digit > pre) dup = i;
		pre = digit;
	}
	return (input - input % dup) * 10 + (input % (10 * dup));
}
