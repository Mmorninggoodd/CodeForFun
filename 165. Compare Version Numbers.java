/*
Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37

*/


public class Solution {
	/*
		Each time compare one level's version numbers.
		
		Note that:
			1.1 == 1.1.0
			1.1 < 1.1.0.1
	*/
    public int compareVersion(String version1, String version2) {
        int i1 = 0, i2 = 0;
        while(i1 < version1.length() || i2 < version2.length()) {
            int j1 = findNextDot(version1, i1); // exclusive end of current version
            int j2 = findNextDot(version2, i2);
            int v1 = i1 >= version1.length() ? 0 : Integer.valueOf(version1.substring(i1, j1));
            int v2 = i2 >= version2.length() ? 0 : Integer.valueOf(version2.substring(i2, j2));
            if(v1 != v2) return Integer.compare(v1, v2);
            i1 = j1 + 1;
            i2 = j2 + 1;
        }
        return 0;
    }
    private int findNextDot(String s, int i) {
        while(i < s.length() && s.charAt(i) != '.') i++;
        return i;
    }
}