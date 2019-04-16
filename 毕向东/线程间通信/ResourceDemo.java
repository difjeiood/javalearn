/*
线程等待唤醒机制(注释编码错误)
*/

//资源类
class Resource {
	private String name;
	private String sex;
	private boolean flag = false;
	
	//需要同同步锁，
	public synchronized void set(String name, String sex){
		if (flag) {
			//等待机制要抛出异常，先不做处理
			try{wait();}catch(InterruptedException e){}
		}
		this.name = name;
		this.sex = sex;
		flag = true;
		this.notify();
	}
	//需要同同步锁，
	public synchronized void out() {
		if (!falg){
			//等待机制要抛出异常，先不做处理
			try{wait();}catch(InterruptedException e){}
		}
		System.out.println(name+",,,,,,,,,"+sex);
		flag = false;
		this.notify();
	}
	
	
}

//输入
class Input implements Runnable {

	//确保同一个对象
	Resource r;
	Input(Resource r ) {
		this.r = r;
	}
	public void run() {
		int x = 0;
		if (x == 0) {
			r.set("zhangsan","nan");
		}else {
			r.set("lisi","nv");
		}
		x = (x++)%2;//让x=0 1切换分别输入不同的值 
	}
}

//输出
class Output implements Runnable {
	//确保同一个对象
	Resource r;
	Output(Resource r ) {
		this.r = r;
	}
	public void run() {
		r.out();
	}
}


class ResourceDemo {
	
	public static void main(String[] args){
		//创建资源对象
		Resource r = new Resource();
		//创建线程任务
		Input in = new Input(r);
		Output ou = new Output(r);
		
		//创建线程
		Thread t1 = new Thread(in);
		Thread t2 = new Thread(ou);
		
		//开启线程、
		t1.start();
		t2.start();
		
		
	}
}










