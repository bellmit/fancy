package cn.telling.common.vo;

import java.io.Serializable;


/**
 * all pager function needed information
 * 
 * @author guohui
 */
public class PageVo implements Serializable {

    /**
     * <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -6811595319510596301L;
    private int totalCount; // 总条数
    private int totalPage; // 总页数
    private int pageNow; // 当前页数
    private int messageForPage; // 每页多少条
    private int pageStartCount; // 当页起始数
    private int pageEndCount; // 当页结束数

    /**
     * @return the pageStartCount
     */
    public int getPageStartCount() {
        return pageStartCount;
    }

    /**
     * @param pageStartCount the pageStartCount to set
     */
    public void setPageStartCount(int pageStartCount) {
        this.pageStartCount = pageStartCount;
    }

    /**
     * @return the pageEndCount
     */
    public int getPageEndCount() {
        return pageEndCount;
    }

    /**
     * @param pageEndCount the pageEndCount to set
     */
    public void setPageEndCount(int pageEndCount) {
        this.pageEndCount = pageEndCount;
    }

    /**
     * @return the totalCount
     */
    public int getTotalCount() {

        return totalCount;
    }

    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;

        if (this.pageNow > 0 && this.totalCount > 0) {
            this.countFunction();
        }
    }

    /**
     * @return the totalPage
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * @param totalPage the totalPage to set
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * @return the pageNow
     */
    public int getPageNow() {
        if (pageNow <= 0) {
            pageNow = 1;
        }
        return pageNow;
    }

    /**
     * @param pageNow the pageNow to set
     */
    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;

        if (this.pageNow > 0 && this.totalCount > 0) {
            this.countFunction();
        }
    }

    /**
     * @return the messageForPage
     */
    public int getMessageForPage() {
        if (messageForPage == 0) {
            this.messageForPage = 10;
        }
        return messageForPage;
    }

    /**
     * @param messageForPage the messageForPage to set
     */
    public void setMessageForPage(int messageForPage) {
        this.messageForPage = messageForPage;

        if (this.pageNow > 0 && this.totalCount > 0) {
            this.countFunction();
        }
    }

    private void countFunction() {
        this.pageStartCount = (this.pageNow - 1) * this.messageForPage + 1;
        this.pageEndCount = (this.pageNow * this.messageForPage);
        if (this.pageEndCount > this.totalCount) {
            this.pageEndCount = this.totalCount;
        }
    }

}
