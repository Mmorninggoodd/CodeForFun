/*
	Insert Delete GetRandom O(1) - Duplicates allowed

	Almost the same as 380
	The probability of each element being returned is linearly related to the number of same value the collection contains.
 
*/

public class RandomizedCollection {
    Map<Integer, Set<Integer>> map;
    ArrayList<Integer> list;
    Random random;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean exsited = map.containsKey(val);
        if(!exsited) map.put(val, new HashSet<>());
        map.get(val).add(list.size());
        list.add(val);
        return !exsited;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        int lastIndex = list.size() - 1, lastVal = list.get(lastIndex);
        Set<Integer> targetSet = map.get(val), lastSet = map.get(lastVal);
        if(lastVal == val) {  // be careful here
            targetSet.remove(lastIndex);
        }
        else {
            int index = targetSet.iterator().next();
            list.set(index, lastVal);
            lastSet.remove(lastIndex);
            lastSet.add(index);
            targetSet.remove(index);
        }
        list.remove(lastIndex);
        if(targetSet.isEmpty()) map.remove(val);
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        int r = random.nextInt(list.size());
        return list.get(r);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */