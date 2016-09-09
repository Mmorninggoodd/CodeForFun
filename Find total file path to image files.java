/*
You are given a listing of directories and files in a file system. Each directory and file has a name, which is a non-empty
string consisting of alphanumerical characters. Additionally, the name of each file contains a single dot character; the part
of the name starting with the dot is called the extension. Directory names do not contain any dots. All the names are case-
sensitive.
Each entry is listed on a separate line. Every directory is followed by the listing of its contents indented by one space
character. The contents of the root directory are not indented.
Here is a sample listing:
dir1
 dir11
 dir12
  picture.jpeg
  dir121
  file1.txt
dir2
 file2.gif
We have three files (picture.jpeg, file1.txt and file2.gif) and six directories (dir1, dir11, dir12, dir121, dir2 and the
root directory). Directory dir12 contains two files (picture.jpeg and file1.txt) and an empty directory (dir121). The root
directory contains two directories (dir1 and dir2).
The absolute path of a directory is a string containing the names of directories which have to be traversed (from the root
directory) to reach the directory, separated by slash characters. For example, the absolute path to the directory dir121 is
dir1/dir12/dir121". Note that there is no "drive letter", such as "C: ", and each absolute path starts with a slash. The
absolute path of a root directory is The image files are the files with extensions .jpeg, .png or .gif (and only these extensions). 
We are only interested in directories that directly contain image files (that is, without looking in their subdirectories). 
We want to find the total length
of the absolute paths to all the directories that directly contain at least one image file. For example, in the file system
described above there are two directories that directly contain image files: "/dir1/dir12" and "/dir2". Directory "/dir1"
doesn't directly contain any image files. The total length of the absolute paths "/dir1/dir12" and "/dir2" is 11 + 5 = 16.
Write a function:

int solution(char *S);

that, given a string S consisting of N characters which contains the listing of a file system, returns the total of lengths (in
characters) modulo 1,000,000,007 of the absolute paths to all the directories directly containing at least one image file. For
example, given the sample listing shown above, the function should return 16, as explained above. If there are no image
files in the listing, the function should return 0.
Assume that:
N is an integer within the range [1..3,000,000];
string S consists only of alphanumerical characters (a-z and/or A-Z and/or 0-9), spaces, dots (.) and end-
of-line characters;
string S is a correct listing of a file system contents.

Complexity:
expected worst-case time complexity is 0(N);
expected worst-case space complexity is 0(N) (not counting the storage required for input arguments).
*/

/*
	Use two stacks to keep track of different states of current level.
	found -- keep track of whether this folder was found a image file before
	levels -- keep track of the length of each level (including current level regardless of current level is a regular file or folder)
	curLen -- record current file path length (including current file name)
	indents -- record current indent level
	total -- result we want, total length of all paths to image files
*/
public static int totalLength(String s) {
	final int prime = 1000000007;
	int total = 0, curLen = 0, indents = -1;
	Deque<Boolean> found = new ArrayDeque<>();
	Deque<Integer> levels = new ArrayDeque<>();
	String[] lines = s.split("\n");
	for(String line : lines) {
		int i = 0;
		while(line.charAt(i) == ' ') i++;  // find current indents
		if(i > indents) {	// deeper level
			found.push(false);
			levels.push(line.length() - i + 1);  // add length of "/curfilepath"
			curLen += levels.peek();
		}
		else if(i == indents){    // same level
			curLen -= levels.pop();
			levels.push(line.length() - i + 1);
			curLen += levels.peek();
		}
		while(i < indents) {   // go to upper level, pop out (can be multiple levels)
			found.pop();
			curLen -= levels.pop();
			indents--;
		}
		curLen = (curLen + prime) % prime; // prevent negative and overflow
		indents = i;   // update indents
		if(!found.peek() && (line.contains(".jpeg") || line.contains(".png") || line.contains(".gif"))) {
			found.pop();
			found.push(true);
			total += levels.size() == 1 ? 1 : (curLen - levels.peek());  // take care root folder case
		}
		total = (total + prime) % prime;   // prevent negative and overflow
	}
	return total;
}