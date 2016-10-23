/*

解释了一下题意：
公司有一个CEO，是Employee类，然后每个Employee有可能有很多direct reports。 
现在公司里有两个人发生争执，这个时候只能找他们两个人的least important common boss来解决。
问怎么找？ 
第一个想法就是找lowest common ancestor。问他是不是每个人最多只能有两个direct reports，二叉树做多了。
他说不是，可能很多。

*/

class Employee {
	int id;
	List<Employee> directReports;
	Employee(int id, List<Employee> reports) {
		this.id = id;
		this.directReports = reports;
	}
}
class Company {
	Employee ceo;
	Company(Employee ceo) {
		this.ceo = ceo;
	}
	Employee LIMCB(int id1, int id2) {
		return LIMCB(this.ceo, id1, id2);
	}
	Employee LIMCE(Employee cur, int id1, int id2) {
		if(cur == null || cur.id == id1 || cur.id == id2) return cur;
		Employee boss = null;
		for(Employee next : cur.directReports) {
			Employee tmp = LIMCB(next, id1, id2);
			if(boss != null && tmp != null) return cur; // common ancestor
			if(tmp != null) boss = tmp;
		}
		return boss;
	}
}