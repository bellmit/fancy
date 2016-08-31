package thread;

/**
 * Created by Fan on 2016/8/29.
 * 单纯的实现需求（未优化代码）
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
public class OutPutTest {
	public static void main(String[] args) {
		Resource r=new Resource();
		Input i=new Input(r);
		Out out=new Out(r);
		Thread t=new Thread(i);
		Thread to=new Thread(out);
		t.start();
		to.start();
	}
}
class Input implements  Runnable{
    private Resource r;
    Input(Resource r) {
        this.r = r;
    }
    @Override
    public void run() {
        int x=0;
            while (true){
                synchronized (r){
                	if (r.flag)
						try {r.wait();} catch (InterruptedException e) {e.printStackTrace();}
                   if (x==0){
                       r.name="mke";
                       	r.sex="man";
                   }else{
                	   	r.name="丽丽";
                	   	r.sex="male";
                   }
                   x=(x+1)%2;
                   r.flag=true;
                   r.notify();
                }
            }
    }
}

class Out implements Runnable{
	 private Resource r;

	 Out(Resource r) {
	        this.r = r;
	    }
	
	@Override
	public void run() {
		while (true) {
			synchronized (r) {
				if (!r.flag)
					try {r.wait();} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println(r.name+"..."+r.sex);
				r.flag=false;
				r.notify();
				
			}
		}
	}

}

class Resource{
    protected boolean flag =false;
     String name;
     String sex;
}
