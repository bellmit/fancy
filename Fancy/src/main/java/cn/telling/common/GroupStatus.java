/**
 * 
 */
package cn.telling.common;

/**
 * @author zhushd
 *
 */
public enum GroupStatus {
	   waitPulish(1,"待发布"),inProcess(2,"活动中"),finished(3,"已结束"),clothes(4,"已结案"),intime(5,"抢购中"),del(6,"已删除");
	   private int index;
	   private String name;
	   
	   private GroupStatus(int curIndex,String curName)
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
	   
	   
	   public static GroupStatus valueOf(int value)
	   {
		   
		   switch(value)
		   {
		     case 1:return waitPulish;
		     case 2:return inProcess;
		     case 3:return finished;	
		     case 4:return clothes;
		     case 5:return intime;
		     case 6:return del;
		     default:
		    	 return waitPulish;		   
		   }
	   }
}
