/*

The API: int read4(char *buf) reads 4 characters at a time from a file.
The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
Note:
The read function will only be called once for each test case.

*/

public class Solution extends Reader4 {
	/*
		Use a tmp array to store intermediate result
	*/
    public int read(char[] buf, int n) {
		int sum = 0;
		char[] tmp = new char[4];
		while(sum < n) {
			int cur = read4(tmp);
			if(cur == 0) break;
			for(int i = 0; i < cur && sum < n; i++) buf[sum++] = tmp[i];
		} 
		return sum;
    }
}