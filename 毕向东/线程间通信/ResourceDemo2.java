/*
�̵߳ȴ����ѻ���
*/

//��Դ��
class Resource {
	private String name;
	private String sex;
	private boolean flag = false;
	
	//��Ҫͬͬ������
	public synchronized void set(String name, String sex){
		if (flag) {
			//�ȴ�����Ҫ�׳��쳣���Ȳ�������
			try{wait();}catch(InterruptedException e){}
		}
		this.name = name;
		this.sex = sex;
		flag = true;
		this.notify();
	}
	//��Ҫͬͬ������
	public synchronized void out() {
		if (!flag){
			//�ȴ�����Ҫ�׳��쳣���Ȳ�������
			try{wait();}catch(InterruptedException e){}
		}
		System.out.println(name+",,,,,,,,,"+sex);
		flag = false;
		this.notify();
	}
	
	
}

//����
class Input implements Runnable {

	//ȷ��ͬһ������
	Resource r;
	Input(Resource r ) {
		this.r = r;
	}
	public void run() {
		int x = 0;
		while(true) {
			if (x == 0) {
				r.set("zhangsan","nan");
			}else {
				r.set("lisi","nv");
			}
			x = (x+1)%2;//��x=0 1�л��ֱ����벻ͬ��ֵ 
		}
	}
}

//���
class Output implements Runnable {
	//ȷ��ͬһ������
	Resource r;
	Output(Resource r ) {
		this.r = r;
	}
	public void run() {
		while (true) {
			r.out();
		}
	}
}


class ResourceDemo2 {
	
	public static void main(String[] args){
		//������Դ����
		Resource r = new Resource();
		//�����߳�����
		Input in = new Input(r);
		Output ou = new Output(r);
		
		//�����߳�
		Thread t1 = new Thread(in);
		Thread t2 = new Thread(ou);
		
		//�����̡߳�
		t1.start();
		t2.start();
		
		
	}
}










