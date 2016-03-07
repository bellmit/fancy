/**
 * Project Desc:
 * Project Name:TestExample
 * File Name:Java_Base1.java
 * Package Name:util
 * Date:2015-5-12下午3:42:43
 * Copyright (c) 2015, zhgo116.com All Rights Reserved.
 *
*/
package util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName:Java_Base1 <br/>
 * Date:     2015-5-12 下午3:42:43 <br/>
 * @author   caosheng
 */
public class Java_Base1
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
		int[] images = { 1, 2, 3, 4, 5 };
		int currentImage = 0;
		for (int i = 0; i < images.length; ++i) {
			int c = images[currentImage++ % images.length];
			System.out.println(c);
		}
	}
}
