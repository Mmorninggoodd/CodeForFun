/*
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.

*/
public class Solution extends Reader4 {
	/*
		Use a buffer counter to record extra chars
	*/
	private int bufIndex = 0, int curRead = 0;
	private char[] tmp = new char[4];
    public int read(char[] buf, int n) {
		int sum = 0;
		while(sum < n) {
			if(bufIndex == 0) curRead = read4(tmp);   // Only when tmp is reset, we read new data
			if(curRead == 0) break;   // End of file, just break
			while(bufIndex < curRead && sum < n) buf[sum++] = tmp[bufIndex++];  // transfer needed data
			if(bufIndex == curRead) bufIndex = 0;  // transfer all buffered data, can reset to receive new data
		} 
		return sum;
    }
}