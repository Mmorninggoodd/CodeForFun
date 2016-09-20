/*

An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example: 

Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true 

1) [“dog”]; isUnique(“dig”);   
//False, because “dig” has the same abbreviation with “dog" and “dog” is already in the dictionary. It’s not unique.

2) [“dog”, “dog"]; isUnique(“dog”);  
//True, because “dog” is the only word that has “d1g” abbreviation.

3) [“dog”, “dig”]; isUnique(“dog”);   
//False, because if we have more than one word match to the same abbreviation, this abbreviation will never be unique.


*/


/*
	Use a HashMap to store (abbreviation, original string) pair
	Check two conditions:
	(1) If abbreviation exists
	(2) If original string is the same as this word
*/
public class ValidWordAbbr {
    HashMap<String, String> map;
    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<String, String>();
        for(String str:dictionary){
            String abbr = getAbbr(str);
            // If there is more than one string belong to the same abbr
            // then the abbr will be invalid, we set the value to ""
            if(map.containsKey(abbr)){
                if(!map.get(abbr).equals(str)){
                    map.put(abbr, "");
                }
            }
            else{
                map.put(abbr, str);
            }
        }
    }

    public boolean isUnique(String word) {
        return !map.containsKey(getAbbr(word))||map.get(getAbbr(word)).equals(word);
    }
    
    String getAbbr(String str){
        if(str.length()<=2) return str;
        return str.charAt(0)+Integer.toString(str.length()-2)+str.charAt(str.length()-1);
    }
}