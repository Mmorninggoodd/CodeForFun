/*

class Throttler {
      int qps;
      public Throttler(int qps) {
      }
      pubilc boolean allowAccess() {
      }
}
给这么一个class实现rate limiter，allowAccess()返回的事当前时间的access能不能被批准
举个栗子：
qps是2
request1 time 0.0 return true;
request2 time 0.5 return true;
request3 time 0.6 return false;

*/


class Throttler {
	int qps;   // max requests per second
	Deque<Double> requests = new ArrayDeque<>();
	public Throttler(int qps) {
		this.qps = qps;
	}
	pubilc boolean allowAccess(double time) {
		while(!requests.isEmpty() && requests.peekFirst() - 1 > time) {  // poll out all requests out of time
			requests.pollFirst();
		}
		if(requests.size() >= this.qps) return false;  // return false when current request reach qps
		requests.offer(time);   // accept this request
		return true;
	}
}