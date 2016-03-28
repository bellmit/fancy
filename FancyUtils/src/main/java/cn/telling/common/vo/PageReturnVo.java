package cn.telling.common.vo;

import java.io.Serializable;
import java.util.List;


/**
 * PageReturnVo
 * 
 * @author guohui
 */
public class PageReturnVo<T> implements Serializable {

	/**
	 * <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 3690148420360458804L;
	private int totalCount; // 总条数
	private List<T> resultList; // 结果列表;

	/**
	 * @return totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount totalCount
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return resultList
	 */
	public List<T> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList resultList
	 */
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

}
