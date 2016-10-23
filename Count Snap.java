/*
	Count one person's snap, and sort.
设计一个API, 返回所有朋友看的snap 从高到低的snap, 所有数据结构自己构造，我就想了个Person的class, 里面有friend List, 以及一个hashMap统计各个snap的观看纪录，然后就是bfs, 再一个大的hashmap统计了

最后加了个Node class {private int snapId, private int count}, 用Collections.sort出结果
*/

class Person {
	int id;
	List<Person> friends = new ArrayList<>();
	Map<Integer, Integer> snapViewCounts = new HashMap<>();
	Person(int id) {this.id = id;}
}
public static List<Integer> getSnaps(Person person) {
	Map<Integer, Integer> counts = new HashMap<>();
	for(Person friend : person.friends) {
		for(Map.Entry<Integer, Integer> count : friend.snapViewCounts.entrySet()) {
			counts.put(count.getKey(), counts.getOrDefault(count.getKey(), 0) + count.getValue());
		}
	}
	List<Map.Entry<Integer, Integer>> sortedEntryList = new ArrayList<>(counts.entrySet());
	Collections.sort(sortedEntryList, ((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue())));
	List<Integer> res = new ArrayList<>();
	for(Map.Entry<Integer, Integer> entry : sortedEntryList) {
		res.add(entry.getKey());
	}
	return res;
}