package cn.fancy.network.async;

/**   
 * @Title: CallBack.java 
 * @Package cn.fancy.network 
 * @Description: (描述该文件做什么) 
 * @author 操圣
 * @date 2017年3月14日 下午8:41:32 
 * @version V1.0   
 */
public abstract class CallBackBody {
	 
    void onSuccess(Object context) {
        System.out.println("onSuccess");
    }
 
    void onFailure(Object context) {
        System.out.println("onFailure");
    }
 
    abstract void execute(Object context) throws Exception;
}

