/*

Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.
Notes:
You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).

*/

class MyQueue {
	
	/* 91ms 99.87%
		Only move elements of new stack to oldStack when it is needed (pop or peek and oldStack is empty)
	*/
    Deque<Integer> newStack = new ArrayDeque<>();
    Deque<Integer> oldStack = new ArrayDeque<>();
    // Push element x to the back of queue.
    public void push(int x) {
        newStack.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        if(oldStack.isEmpty()) moveToOld();
        oldStack.pop();
    }

    // Get the front element.
    public int peek() {
        if(oldStack.isEmpty()) moveToOld();
        return oldStack.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return newStack.isEmpty() && oldStack.isEmpty();
    }
    private void moveToOld() {
        while(!newStack.isEmpty()) oldStack.push(newStack.pop());
    }
}