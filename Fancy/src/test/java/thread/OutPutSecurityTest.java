package thread;

/**
 * Created by Fan on 2016/8/29.
 * 
 * 演示了：输入姓名完成后被其他线程抢走导致执行权被输出抢走
 * 
 * 从而出现姓名为女的性别却是男
 */
public class OutPutSecurityTest {
	public static void main(String[] args) {
		Resource2 r=new Resource2();
		Input2 i=new Input2(r);
		Out2 out=new Out2(r);
		Thread t=new Thread(i);
		Thread to=new Thread(out);
		t.start();
		to.start();
	}
}
class Input2 implements  Runnable{
    private Resource2 r;
    Input2(Resource2 r) {
        this.r = r;
    }
    @Override
    public void run() {
        int x=0;
            while (true){
                   if (x==0){
                       r.name="mke";
                       	r.sex="man";
                   }else{
                	   	r.name="丽丽";
                	   	r.sex="male";
                   }
                   x=(x+1)%2;
            }
    }
}

class Out2 implements Runnable{
	 private Resource2 r;

	 Out2(Resource2 r) {
	        this.r = r;
	    }
	
	@Override
	public void run() {
		while (true) {
				System.out.println(r.name+"..."+r.sex);
		}
	}

}

class Resource2{
    protected static boolean flag =false;
    protected String name;
    protected String sex;
}
