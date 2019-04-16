/*
�����ߣ������ߡ�

�������ߣ��������ߵ����⡣
if�жϱ�ǣ�ֻ��һ�Σ��ᵼ�²������е��߳������ˡ����������ݴ���������
while�жϱ�ǣ�������̻߳�ȡִ��Ȩ���Ƿ�Ҫ���У�

notify:ֻ�ܻ���һ���̣߳�������������˱�����û�����塣����while�жϱ��+notify�ᵼ��������
notifyAll����˱����߳�һ���ỽ�ѶԷ��̵߳����⡣


*/

//��Դ
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
		System.out.println(Thread.currentThread().getName()+",,,,,�����ߣ�����"  + this.name);
		flag = true;
		notifyAll();
		
	}
	
	public synchronized void out(){
		while(!flag) {
			try{this.wait();}catch(InterruptedException e){}
		}
		System.out.println(Thread.currentThread().getName() + ",,,,,,,������,,,," + this.name);
		flag = false;
		notifyAll();
		
	}
}



//������
class Producter implements Runnable {
	private Resource r;
	Producter(Resource r) {
		this.r = r;
	}
	
	public void run() {
		while(true) {
			r.set("��Ѽ");
		}
	}
	
}


//������
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


//������
class ProducterConsumerDemo {
	public static void main(String[] args) {
		//������Դ
		Resource r = new Resource();
		//��������
		Producter por = new Producter(r);
		Consumer con = new Consumer(r);
		//�����߳�
		Thread t0 = new Thread(por);
		Thread t1 = new Thread(por);
		Thread t2 = new Thread(con);
		Thread t3 = new Thread(con);
		
		//�����߳�
		t0.start();
		t1.start();
		t2.start();
		t3.start();

	}	
}







