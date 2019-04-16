/*
	请使用LinkedList模拟堆栈和队列
	
	堆栈：先进后出  FILO
	队列：先进先出  FIFO
*/

import java.util.LinkedList;

//队列
class Queue_1 {
	private LinkedList link;
	
	public Queue_1(){
		link = new LinkedList();
	}
	
	public void myAdd( Object obj) {
		link.offerLast(obj);
	}
	
	public Object myGet() {
		return link.pollFirst();
	}
	
	public boolean isNull() {
		return link.isEmpty();
	}
	
}
//栈
class Stack_1 {
	private LinkedList link;
	
	public Stack_1(){
		link = new LinkedList();
	}
	
	public void myAdd( Object obj) {
		link.offerLast(obj);
	}
	
	public Object myGet() {
		return link.pollLast();
	}
	
	public boolean isNull() {
		return link.isEmpty();
	}
	
}

class LinkedListDemo {
	public static void main(String[] args) {
		//创建队列对象
		Queue_1 q = new Queue_1();
		//创建栈对象
		Stack_1 s = new Stack_1();
		//队列添加元素
		q.myAdd("111");
		q.myAdd("222");
		q.myAdd("333");
		q.myAdd("444");
		//栈添加元素
		s.myAdd("111");
		s.myAdd("222");
		s.myAdd("333");
		s.myAdd("444");
		
		//出队列 111 222 333 444
		while(!q.isNull()) {
			System.out.println(q.myGet());
		}
		
		//出栈 444 333 222 111
		while(!s.isNull()) {
			System.out.println(s.myGet());
		}
	}
}
















