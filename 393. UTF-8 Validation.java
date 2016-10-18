/*

A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:

For 1-byte character, the first bit is a 0, followed by its unicode code.
For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
This is how the UTF-8 encoding would work:

   Char. number range  |        UTF-8 octet sequence
      (hexadecimal)    |              (binary)
   --------------------+---------------------------------------------
   0000 0000-0000 007F | 0xxxxxxx
   0000 0080-0000 07FF | 110xxxxx 10xxxxxx
   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
Given an array of integers representing the data, return whether it is a valid utf-8 encoding.

Note:
The input is an array of integers. Only the least significant 8 bits of each integer is used to store the data. This means each integer represents only 1 byte of data.

Example 1:

data = [197, 130, 1], which represents the octet sequence: 11000101 10000010 00000001.

Return true.
It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.
Example 2:

data = [235, 140, 4], which represented the octet sequence: 11101011 10001100 00000100.

Return false.
The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.
The next byte is a continuation byte which starts with 10 and that's correct.
But the second continuation byte does not start with 10, so it is invalid.

*/

/*
	A simple implementation.

*/
public boolean validUtf8(int[] data) {
	int nByte = 0;
	for(int cur : data) {
		if(nByte == 0) {
			if((cur >>> 7) == 0) nByte = 0;
			else if((cur >>> 3) == 0b11110) nByte = 3;
			else if((cur >>> 4) == 0b1110) nByte = 2;
			else if((cur >>> 5) == 0b110) nByte = 1;
			else return false;
		}
		else if((cur >>> 6) == 0b10) nByte--;
		else return false;
	}
	return nByte == 0;
}
	
	
public class Solution {
    int[] masks = new int[]{128, 192, 224, 240, 248};  // 0b10000000, 0b11000000, 0b11100000, 0b111100000, 0b11111000
    public boolean validUtf8(int[] data) {
        for(int i = 0; i < data.length;) {
            int nByte = 0;
			if((data[i] & masks[0]) == 0) nByte = 1;  // for 1 byte
            for(int j = 1; j < 4; j++) {   			  // for 2-4 bytes
                if(((data[i] ^ masks[j]) & masks[j + 1]) == 0) {
                    nByte = j + 1;
                    break;
                }
            }
            if(nByte == 0 || data.length < i + nByte) return false;
            for(int j = 1; j < nByte; j++) {
                if(((data[i + j] ^ masks[0]) & masks[1]) != 0) return false;
            }
            i += nByte;
        }
        return true;
    }
}