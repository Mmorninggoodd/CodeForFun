/*
	Paste - 1
	
	Given a CSV file where each row contains the name of a city and its state separated by a comma.
	
	Your task is to replace the newlines in the file with semicolons.
	
	For example:
	Albany, N.Y.
	Albuquerque, N.M.
	
	->
	
	Albany, N.Y.;Albuquerque, N.M.

*/

/*
	O(n) time O(1) space
	
	But if the length of one line is very long, we can only print certain length each time.
*/
public static void main(String[] args0 {
	Scanner sc = new Scanner(System.in);
	while(sc.hasNextLine()) {
		System.out.print(sc.nextLine());
		if(sc.hasNextLine()) System.out.print(";");
	}
}