/**
 * 
 * Project Name:myweb-service
 * File Name:Menu.java
 * Package Name:cn.fancy.menu
 * Date:2015-7-15
 *
 */

package cn.telling.menu.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * ClassName:Menu <br/>
 * 
 * @author caosheng
 */
public class Menu implements Serializable {

	/**
	 * serialVersionUID:TODO
	 * 
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 7584501914508712933L;
	private BigDecimal id;
	private String name;
	private BigDecimal menupId;
	private String description;
	private String pageUrl;
	private BigDecimal type;
	private BigDecimal state;
	private Date createTime;
	private BigDecimal sortfiled;
	private Menu pm;
	
	private List<Menu>  children;   //孩子节点的List
	
	public Menu getPm() {
		return pm;
	}

	
	public void setPm(Menu pm) {
		this.pm = pm;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	

	public BigDecimal getId() {
        return id;
    }


    public void setId(BigDecimal id) {
        this.id = id;
    }


    public BigDecimal getMenupId() {
        return menupId;
    }


    public void setMenupId(BigDecimal menupId) {
        this.menupId = menupId;
    }


    public BigDecimal getType() {
        return type;
    }


    public void setType(BigDecimal type) {
        this.type = type;
    }


    public BigDecimal getState() {
        return state;
    }


    public void setState(BigDecimal state) {
        this.state = state;
    }


    public BigDecimal getSortfiled() {
        return sortfiled;
    }


    public void setSortfiled(BigDecimal sortfiled) {
        this.sortfiled = sortfiled;
    }


    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    public List<Menu> getChildren() {
        return children;
    }
    public void setChildren(List<Menu> children) {
        this.children = children;
    }
    
     //添加孩子的方法

    public void addChild(Menu node){
       if(this.children == null){
           children= new ArrayList<Menu>();
           children.add(node);
       }else{
           children.add(node);
       }
          
    }
}
