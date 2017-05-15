package cn.fancy.network.async;

/**   
 * @Title: CallBackTask.java 
 * @Package cn.fancy.network.async 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2017年3月14日 下午8:42:51 
 * @version V1.0   
 */
public class CallBackTask {
    private CallBackBody body;
 
    public CallBackTask(CallBackBody body) {
        this.body = body;
    }
 
    protected void start(final Object context) {
        final Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    body.execute(context);
                } catch (Exception e) {
                    e.printStackTrace();
                    body.onFailure(context);
                }
                body.onSuccess(context);
            }
        });
        t.start();
    }
}