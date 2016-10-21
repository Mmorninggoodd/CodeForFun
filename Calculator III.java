/*
	Now give you a expression contains + - * / ( ), return the value.

*/

/*
	A simple way is just to implement Calculator II, then go to next level.
	
	But we need to make index pass by reference, because we need to take care of cases:
	
	((1+8) * (2-4) + (3 + (2) / 3)) + 1
*/
public static double calculator(String s) {
	return calculator(s, new int[1]);
}
public static double calculator(String s, int[] index) {
	double preNum = 0, curNum = 0;
	int op = 0, sign = 1;  // op: 0: nothing, 1: *, -1: /
	for(;index[0] < s.length(); index[0]++) {
		char c = s.charAt(index[0]);
		if(c == '(' || Character.isDigit(c)) {  // new number
			double nextNum = 0;
			if(c != '(') {
				while(index[0] < s.length() && Character.isDigit(s.charAt(index[0]))) {
					nextNum = 10 * nextNum + s.charAt(index[0]++) - '0';
				}
				index[0]--;
			}
			else {   // next level
				index[0]++;
				nextNum = calculator(s, index);
			}
			curNum = op == 0 ? nextNum : op == 1 ? curNum * nextNum : curNum / nextNum;
			op = 0;
		}
		else if(c == '+' || c == '-') {
			preNum += curNum * sign;
			sign = c == '+' ? 1 : -1;
			curNum = 0;
		}
		else if(c == '*' || c == '/') {
			op = c == '*' ? 1 : -1;
		}
		else if(c == ')') {
			index[0]++;
			break;
		}
	}
	return preNum + curNum * sign;
}