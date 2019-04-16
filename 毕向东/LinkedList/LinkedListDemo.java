/*
	��ʹ��LinkedListģ���ջ�Ͷ���
	
	��ջ���Ƚ����  FILO
	���У��Ƚ��ȳ�  FIFO
*/

import java.util.LinkedList;

//����
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
//ջ
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
		//�������ж���
		Queue_1 q = new Queue_1();
		//����ջ����
		Stack_1 s = new Stack_1();
		//�������Ԫ��
		q.myAdd("111");
		q.myAdd("222");
		q.myAdd("333");
		q.myAdd("444");
		//ջ���Ԫ��
		s.myAdd("111");
		s.myAdd("222");
		s.myAdd("333");
		s.myAdd("444");
		
		//������ 111 222 333 444
		while(!q.isNull()) {
			System.out.println(q.myGet());
		}
		
		//��ջ 444 333 222 111
		while(!s.isNull()) {
			System.out.println(s.myGet());
		}
	}
}
















