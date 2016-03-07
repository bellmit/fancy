/**
 * Project Desc:
 * Project Name:TestExample
 * File Name:ForTest.java
 * Package Name:util
 * Date:2015-5-8上午11:55:08
 * Copyright (c) 2015, zhgo116.com All Rights Reserved.
 *
*/
package util;

import java.math.BigDecimal;
import java.util.Date;

import util.ExportUtil2.Novel;

/**
 * ClassName:ForTest <br/>
 * Date:     2015-5-8 上午11:55:08 <br/>
 * @author   caosheng
 */
public class ForTest
{

	protected class Novel
	{

		private String name;

		private String author;

		private BigDecimal price;

		private Date date;

		/**
		 * Creates a new instance of Novel.
		 *
		 */
		public Novel()
		{
		}

		public Novel getInstant()
		{
			Novel novel = this;
			{
				return novel;
			}
		}

		/**
		 * Creates a new instance of Novel.
		 *
		 * @param string
		 * @param string2
		 * @param date
		 */
		public Novel(String name, String author, BigDecimal price)
		{
			this.name = name;
			this.author = author;
			this.price = price;
		}

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public String getAuthor()
		{
			return author;
		}

		public void setAuthor(String author)
		{
			this.author = author;
		}

		public BigDecimal getPrice()
		{
			return price;
		}

		public void setPrice(BigDecimal price)
		{
			this.price = price;
		}

		public Date getDate()
		{
			return date;
		}

		public void setDate(Date date)
		{
			this.date = date;
		}
	}
	public static void main(String[] args)
	{
		for (int i =1; i <=4; i++) {
			if (i == 2) {
//				break;
				continue;
			}
			System.out.println(i);
		}
		System.out.println("继续");
	}
}
