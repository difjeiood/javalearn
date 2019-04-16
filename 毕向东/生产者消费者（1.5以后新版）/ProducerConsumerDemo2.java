/*
jdk1.5以后将同步和锁封装成了对象。 
并将操作锁的隐式方式定义到了该对象中，
将隐式动作变成了显示动作。

Lock接口： 出现替代了同步代码块或者同步函数。将同步的隐式锁操作变成现实锁操作。
同时更为灵活。可以一个锁上加上多组监视器。
lock():获取锁。
unlock():释放锁，通常需要定义finally代码块中。


Condition接口：出现替代了Object中的wait notify notifyAll方法。
			将这些监视器方法单独进行了封装，变成Condition监视器对象。
			可以任意锁进行组合。
await();
signal();
signalAll();



*/

import java.util.concurrent.locks.*;//导包


//资源
class Resource {
	private String name;
	private int count = 1;
	private boolean flag = false;
	
	//创建一个锁
	Lock lock = new ReentrantLock();

	//通过已有锁获取该锁上的监视器对象
	Condition producer_con = lock.newCondition();
	Condition consumer_con = lock.newCondition();
	
	public void set(String name) {
		lock.lock();//上锁
		try {
			while(flag) {
				try{producer_con.await();}catch(InterruptedException e){}
			}
			
			this.name = name + count;
			count++;
			System.out.println(Thread.currentThread().getName()+",,,,,生产者，，，"  + this.name);
			flag = true;
			consumer_con.signal();
		}finally {
			lock.unlock();//释放锁
		}
		
	}
	
	public void out(){
		lock.lock();//上锁
		try {
			while(!flag) {
				try{consumer_con.await();}catch(InterruptedException e){}
			}
			System.out.println(Thread.currentThread().getName() + ",,,,,,,消费者,,,," + this.name);
			flag = false;
			producer_con.signal();
		}finally{
			lock.unlock();
		}
		
	}
}



//生产者
class Producer implements Runnable {
	private Resource r;
	Producer(Resource r) {
		this.r = r;
	}
	
	public void run() {
		while(true) {
			r.set("烤鸭");
		}
	}
	
}


//消费者
class Consumer implements Runnable {
	private Resource r;
	Consumer(Resource r) {
		this.r = r;
	}
	
	public void run() {
		while(true) {
			r.out();
		}
		
	}
}


//主函数
class ProducerConsumerDemo2 {
	public static void main(String[] args) {
		//创建资源
		Resource r = new Resource();
		//创建任务
		Producer por = new Producer(r);
		Consumer con = new Consumer(r);
		//创建线程
		Thread t0 = new Thread(por);
		Thread t1 = new Thread(por);
		Thread t2 = new Thread(con);
		Thread t3 = new Thread(con);
		
		//开启线程
		t0.start();
		t1.start();
		t2.start();
		t3.start();

	}	
}







