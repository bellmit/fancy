/**
 * 
 */
package cn.telling.common.enums.GroupBuy;

/**
 * @author zhushd
 *
 */
public enum GroupBuyStatus {
   waitPulish(1,"待发布"),inProcess(2,"活动中"),waitAudit(3,"待审核"),success(4,"团购成功"),failure(5,"团购失败"),intime(6,"抢购中"),finish(7,"已结束"),del(8,"已删除");
   private int index;
   private String name;
   
   private GroupBuyStatus(int curIndex,String curName)
   {
	    index=curIndex;
	    name=curName;
   }
   
   public int index()
   {
	   return index;
   }
   
   public String text()
   {
	   return name;
   }
   
   
   public static GroupBuyStatus valueOf(int value)
   {
	   
	   switch(value)
	   {
	     case 1:return waitPulish;
	     case 2:return inProcess;
	     case 3:return waitAudit;
	     case 4:return success;
	     case 5:return failure;
	     case 6:return intime;
	     case 7:return finish;
	     case 8:return del;
	     default:
	    	 return waitPulish;		   
	   }
   }
   
	
}
