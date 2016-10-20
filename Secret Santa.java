/*
	Everyone give out one gift, and receive one gift.
	
	No one can give the gift himself.
	
	Generate a random solution for this.
	
	string name = {mary, alice, mike}; 
	output： mary -> mike mike -> alice, alice -> marry 
	Wrong output：marry ->mike, mike -> marry, alice -> alice

*/

/*
	Just can randomly shuffle the array, then each one give gift to next person.
	
	Naive shuffle: each time choose one person from remaining persons.
*/
public static List<String> secretSanta(String[] names) {
	if(names == null || names.length <= 1) throw new IllegalArgumentException("Invalid Input.");
	Random random = new Random();
	ArrayList<String> res = new ArrayList<>();
	for(int i = 0; i < names.length; i++) {
		int j = i + random.nextInt(names.length - i);
		String tmp = names[i];
		names[i] = names[j];
		names[j] = tmp;
		if(i != 0) res.add(names[i - 1] + " -> " + names[i]);
	}
	return res;
}
