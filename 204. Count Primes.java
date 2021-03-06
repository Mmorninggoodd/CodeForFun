/*
Description:

Count the number of prime numbers less than a non-negative number, n.

Credits:
Special thanks to @mithmatt for adding this problem and creating all test cases.

Hint:

Let's start with a isPrime function. To determine if a number is prime, we need to check if it is not divisible by any number less than n. The runtime complexity of isPrime function would be O(n) and hence counting the total prime numbers up to n would be O(n2). Could we do better?

As we know the number must not be divisible by any number > n / 2, we can immediately cut the total iterations half by dividing only up to n / 2. Could we still do better?

Let's write down all of 12's factors:

2 × 6 = 12
3 × 4 = 12
4 × 3 = 12
6 × 2 = 12
As you can see, calculations of 4 × 3 and 6 × 2 are not necessary. Therefore, we only need to consider factors up to √n because, if n is divisible by some number p, then n = p × q and since p ≤ q, we could derive that p ≤ √n.

Our total runtime has now improved to O(n1.5), which is slightly better. Is there a faster approach?

public int countPrimes(int n) {
   int count = 0;
   for (int i = 1; i < n; i++) {
      if (isPrime(i)) count++;
   }
   return count;
}

private boolean isPrime(int num) {
   if (num <= 1) return false;
   // Loop's ending condition is i * i <= num instead of i <= sqrt(num)
   // to avoid repeatedly calling an expensive function sqrt().
   for (int i = 2; i * i <= num; i++) {
      if (num % i == 0) return false;
   }
   return true;
}
The Sieve of Eratosthenes is one of the most efficient ways to find all prime numbers up to n. But don't let that name scare you, I promise that the concept is surprisingly simple.


Sieve of Eratosthenes: algorithm steps for primes below 121. "Sieve of Eratosthenes Animation" by SKopp is licensed under CC BY 2.0.

We start off with a table of n numbers. Let's look at the first number, 2. We know all multiples of 2 must not be primes, so we mark them off as non-primes. Then we look at the next number, 3. Similarly, all multiples of 3 such as 3 × 2 = 6, 3 × 3 = 9, ... must not be primes, so we mark them off as well. Now we look at the next number, 4, which was already marked off. What does this tell you? Should you mark off all multiples of 4 as well?

4 is not a prime because it is divisible by 2, which means all multiples of 4 must also be divisible by 2 and were already marked off. So we can skip 4 immediately and go to the next number, 5. Now, all multiples of 5 such as 5 × 2 = 10, 5 × 3 = 15, 5 × 4 = 20, 5 × 5 = 25, ... can be marked off. There is a slight optimization here, we do not need to start from 5 × 2 = 10. Where should we start marking off?

In fact, we can mark off multiples of 5 starting at 5 × 5 = 25, because 5 × 2 = 10 was already marked off by multiple of 2, similarly 5 × 3 = 15 was already marked off by multiple of 3. Therefore, if the current number is p, we can always mark off multiples of p starting at p2, then in increments of p: p2 + p, p2 + 2p, ... Now what should be the terminating loop condition?

It is easy to say that the terminating loop condition is p < n, which is certainly correct but not efficient. Do you still remember Hint #3?

Yes, the terminating loop condition can be p < √n, as all non-primes ≥ √n must have already been marked off. When the loop terminates, all the numbers in the table that are non-marked are prime.

The Sieve of Eratosthenes uses an extra O(n) memory and its runtime complexity is O(n log log n). For the more mathematically inclined readers, you can read more about its algorithm complexity on Wikipedia.

public int countPrimes(int n) {
   boolean[] isPrime = new boolean[n];
   for (int i = 2; i < n; i++) {
      isPrime[i] = true;
   }
   // Loop's ending condition is i * i < n instead of i < sqrt(n)
   // to avoid repeatedly calling an expensive function sqrt().
   for (int i = 2; i * i < n; i++) {
      if (!isPrime[i]) continue;
      for (int j = i * i; j < n; j += i) {
         isPrime[j] = false;
      }
   }
   int count = 0;
   for (int i = 2; i < n; i++) {
      if (isPrime[i]) count++;
   }
   return count;
}

*/

public class Solution {
	
	/* 27ms 70%
		A regular method
	*/
	public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {
                count++;
                for (int j = i; j < n; j += i) {
                    notPrime[j] = true;
                }
            }
        }
        
        return count;
    }
	
	/* 13ms 97%
		One optimized solution.
		
		First count all even numbers as prime numbers.
		(So we need to skip all even number in the following)
		Then iterate over [3,n] (1 is not prime, and skip 2).
		For each number, if it is flagged as not a prime, then it means all multiples of it must be flagged before, we can skip it.
		If it is prime number, then we looks for all its multiples starting from i*i (because p*i where p < i should be flagged before), if one its multiple is flagged as prime before, then we reduce count and flag it as not prime (we assume this odd number was prime before, and include into n/2).
	*/
    public int countPrimes(int n) {
        if(n < 3) return 0;
        boolean[] notPrime = new boolean[n];
        int count = n / 2;    // first count all even numbers as prime numbers
        for(int i = 3; i * i < n; i += 2) {  // skip even numbers
            if(notPrime[i]) continue;        // If it is not prime, then can skip it, because all multiples of it must be flagged before
            for(int j = i * i; j < n; j += 2 * i) {  // skip even numbers, start from i*i because < i * i should be flagged before
                if(!notPrime[j]) {   // reduce count when this odd number is prime
                    --count;
                    notPrime[j] = true;
                }
            }
        }
        return count;
    }
}

/*
	How to generate prime number in O(1) space
*/
public static boolean isPrime(long n) {
	if(n < 2) return false;
	if(n == 2 || n == 3) return true;
	if(n % 2 == 0 || n % 3 == 0) return false;
	long sqrtN = (long) Math.sqrt(n) + 1;
	for(int i = 6; i <= sqrtN; i += 6) {
		if(n % (i - 1) == 0 || n % (i + 1) == 0) return true;
	}
	return false;
}
/*
	Follow up: Write a prime number iterator.
	A efficient way is to store some prime numbers we already found.
*/
class PrimeIterator {
	List<Integer> primes;
	int current;
	PrimeIterator() {
		primes = new ArrayList<>();
		primes.add(2);
		primes.add(3);
		current = 1;
	}
	int next() {
		current++;
		if(current < 4) {
			return current;
		}
		if(current % 2 == 0) current++;
		while(true) {
			for(int prime : primes) {
				if(current % prime == 0) {
					current += 2;
					break;
				}
				if(prime * prime >= current) {
					primes.add(current);
					return current;
				}
			}
		}
	}
}