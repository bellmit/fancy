package thread;

/**
 * Created by Fan on 2016/8/29.
 * 在OutPutTest.java 基础上代码优化
 * wait;
 * notifyAll();
 * 
 * 都使用在同步中，因为要对持有的监视器(锁)的线程操作
 * 所以要使用在同步中，因为只有同步才具有锁
 * 
 * 为什么这些方法要放在Object类中呢
 * 因为这些方法在操作同步线程时,都必须要标识它们锁操作的线程只有的锁
 * 也就是说 等待和唤醒必须是同一个锁
 */
public class OutPutOptTest {
	public static void main(String[] args) {
		Resource3 r=new Resource3();
//		Input3 i=new Input3(r);
//		Out3 out=new Out3(r);
//		Thread t=new Thread(i);
//		Thread to=new Thread(out);
//		t.start();
//		to.start();
		new Thread(new Input3(r)).start();;
		new Thread(new Out3(r)).start();;
	}
}
class Input3 implements  Runnable{
    private Resource3 r;
    Input3(Resource3 r) {
        this.r = r;
    }
    @Override
    public void run() {
        int x=0;
            while (true){
                   if (x==0){
                       r.set("nike", "man");
                   }else{
                	   r.set("lili", "male");
                   }
                   x=(x+1)%2;
            }
    }
}

class Out3 implements Runnable{
	 private Resource3 r;

	 Out3(Resource3 r) {
	        this.r = r;
	    }
	
	@Override
	public void run() {
		while (true) {
			r.get();
		}
	}

}

class Resource3{
	/*
	 * 需求：存一个 取一个  不会存在连续存，连续取的问题
	 * */
     static boolean flag =false;//false取没了  true已经存了  
    private String name;
    private String sex;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}

	public synchronized void set(String name, String sex) {
			
		if (flag)
			try {this.wait();} catch (InterruptedException e) {e.printStackTrace();}
		this.name = name;
		this.sex = sex;
		flag=true;
		this.notify();
	}

	public synchronized void get() {
		if (!flag)
			try {this.wait();} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Resource3 [name=" + name + ", sex=" + sex + "]");
		flag=false;
		this.notify();
	}
    
	
}
