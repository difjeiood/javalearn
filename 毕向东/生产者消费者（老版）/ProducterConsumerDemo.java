/*
生产者，消费者。

多生产者，多消费者的问题。
if判断标记，只有一次，会导致不该运行的线程运行了。出现了数据错误的情况。
while判断标记，解决了线程获取执行权后，是否要运行！

notify:只能唤醒一个线程，如果本方唤醒了本方，没有意义。而且while判断标记+notify会导致死锁。
notifyAll解决了本方线程一定会唤醒对方线程的问题。


*/

//资源
class Resource {
	private String name;
	private int count = 1;
	private boolean flag = false;
	
	public synchronized void set(String name) {
		while(flag) {
			try{this.wait();}catch(InterruptedException e){}
		}
		
		this.name = name + count;
		count++;
		System.out.println(Thread.currentThread().getName()+",,,,,生产者，，，"  + this.name);
		flag = true;
		notifyAll();
		
	}
	
	public synchronized void out(){
		while(!flag) {
			try{this.wait();}catch(InterruptedException e){}
		}
		System.out.println(Thread.currentThread().getName() + ",,,,,,,消费者,,,," + this.name);
		flag = false;
		notifyAll();
		
	}
}



//生产者
class Producter implements Runnable {
	private Resource r;
	Producter(Resource r) {
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
class ProducterConsumerDemo {
	public static void main(String[] args) {
		//创建资源
		Resource r = new Resource();
		//创建任务
		Producter por = new Producter(r);
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







