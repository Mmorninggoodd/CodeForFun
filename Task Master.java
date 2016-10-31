/*
	Task Master
	Given n tasks, two arrays: a[] after-tasks, b[] before-tasks. Return maximum tasks you can complete.
	
	b[i] need to be completed before a[i]. (but a[i] can be completed without completing b[i] before, but you cannot complete b[i] latter), i.e. this dependency relationship is one-way.
	
	For example,
	n = 2
	a[] = {}
	b[] = {}
	
	Return 2. Because there is not any dependency.
	
	n = 2
	a[] = {1,2}
	b[] = {2,1}
	
	Return 1. Although two dependencies are conflict, but you can perform task 2 at index 0. But when you arrive at index 1, you cannot perform task 1, because task 2 is already done, you cannot complete task 1.

*/