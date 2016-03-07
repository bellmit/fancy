/**
 * 
 * Project Name:myweb-web
 * File Name:LuceneUtil.java
 * Package Name:cn.fancy.test
 * Date:2015-7-27
 *
 */

package cn.fancy.test;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.telling.shop.service.IShopService;
import cn.telling.shop.vo.ProductSub;
import cn.telling.shop.vo.ProductVo;
import cn.telling.utils.StringHelperTools;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@SuppressWarnings("deprecation")
public class LuceneUtil4 {
	private final static String filePath = "d:\\test1";

	private Analyzer any = new StandardAnalyzer(Version.LUCENE_44);
	private IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_44, any);

	private IndexWriter iwriter = null;
	private IndexReader indexReader = null;
	@Autowired
	private IShopService shopService;

	@Test
	public void test() throws Exception
	{
		iwriter = new IndexWriter(FSDirectory.open(new File(filePath)), config);
		iwriter.deleteAll();
		addIndex();

		/*** 使用无序遍历 **/
//		
//		 BigDecimal b = new BigDecimal(0); for (int k = 0; k < prLs.size(); k++) {
//		 /** 加省包 */
//		
//		 ProductSub pr = prLs.get(k); for (int i = 0; i < pLs.size(); i++) {
//		 
//		 ProductVo pv = pLs.get(i);// 主表
//		 
//		 if (pv.getProductId().equals(pr.getSpProductId())) {
//		 
//		 b = new BigDecimal(pv.getProductId().toString()); System.out.println("==========" + b); } if (!b.equals(new
//		 BigDecimal(0)) && !b.equals(pr.getSpProductId())) { System.out.println("加主表"); b = new BigDecimal(0); } }
//		
//		 }
		 

		te();
		iwriter.close();

	}

	/**
	 * 
	 * @param
	 * @return
	 * @author caosheng
	 * @version V1.0
	 */
	private void addIndex() throws IOException
	{
		List<ProductVo> prodLs = shopService.getProduct();
		List<ProductSub> hjhLs = shopService.getTongxun();
		List<ProductSub> provLs = shopService.getProvince();

		/** 有序遍历 */
		BigDecimal b = new BigDecimal(0);
		String flag="";
		for (int i = 0; i < prodLs.size(); i++)
		{
			ProductVo pv = prodLs.get(i);// 主表
			for (int k = 0; k < provLs.size(); k++)
			{
				ProductSub pr = provLs.get(k);
				if (pv.getProductId().equals(pr.getSpProductId()))
				{
					b = pv.getProductId();
					addProductSub(pr);
					flag="1";
					System.out.println("加子表====" + pr.getSpProductId());
				}
				
				if ((!b.equals(new BigDecimal(0)) && !b.equals(pr.getSpProductId()))||flag.equals("1")&&k==provLs.size()-1&&prodLs.size()-1==i)
				{
					addProductVo(pv);
					System.out.println("====加省包主表表" + pv.getProductId());
					b = new BigDecimal(0);
					flag="";
				}
			}

			for (int j = 0; j < hjhLs.size(); j++)
			{
				ProductSub pr = hjhLs.get(j);
				if (pr.getSpProductId().equals(pv.getProductId()))
				{
					addProductSub(pr);
					System.out.println("====" + pr.getSpProductId());
					addProductVo(pv);
					System.out.println("====加好机汇子表" + pr.getSpProductId());
				}
			}
		}
	}

	public void updateIndex() throws Exception
	{
		Directory directory = FSDirectory.open(new File(filePath));
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(ireader);
		indexReader = IndexReader.open(directory);
		System.err.println("索引里所有文档:" + indexReader.maxDoc());
		System.err.println("索引里有效文档:" + indexReader.numDocs());
		System.err.println("删除文档个数：" + indexReader.numDeletedDocs());

		List<ProductVo> prodLs = shopService.getProduct();
		List<ProductSub> hjhLs = shopService.getTongxun();
		List<ProductSub> provLs = shopService.getProvince();

		BigDecimal b = new BigDecimal(0);
		int maxdoc = indexReader.maxDoc();
		for (int j = 0; j < maxdoc; j++)
		{
			Document doc = indexReader.document(j);
			String productid = doc.get("productId") == null ? doc.get("spProductId") : doc.get("productId");
			if (productid.equals("26013"))
			{
				System.out.println("====================================索引里删没删掉" + productid);
			}
			/******************************删除**/
			int countHjh = 0;
			for (int i = 0; i < prodLs.size(); i++)
			{
				ProductVo pv = prodLs.get(i);
				String pId = pv.getProductId().toString();

				if (pId.equals(productid))
				{
					for (int k = 0; k < hjhLs.size(); k++)
					{
						ProductSub ps = hjhLs.get(k);
						if (ps.getSpProductId().toString().equals(productid))
						{
							countHjh++;
							break;
						}
					}
					if (countHjh == 0)
					{
						for (int t = 0; t < provLs.size(); t++)
						{
							ProductSub ps = provLs.get(t);
							if (ps.getSpProductId().toString().equals(productid))
							{
								countHjh++;
								break;
							}
						}
					}
				}
			}
			if (countHjh == 0)
			{
				System.out.println("同步索引--删除索引文档编号=================" + j);
				delteIndex(productid);
			}
		}
		// 判断索引子表与主表更新
		for (int i = 0; i < prodLs.size(); i++)
		{
			List<ProductSub> S2 = new ArrayList<ProductSub>();
			ProductVo pv = prodLs.get(i);
			BigDecimal productId = pv.getProductId();
			TopDocs tI = searcher.search(new TermQuery(new Term("productId", productId.toString())), 1);
			if (tI.totalHits > 0)
			{
				TopDocs td = searcher.search(new TermQuery(new Term("spProductId", productId.toString())), 1);
				td = searcher.search(new TermQuery(new Term("spProductId", productId.toString())),
						td.totalHits == 0 ? 1 : td.totalHits);
				ScoreDoc[] sd = td.scoreDocs;
				for (int j = 0; j < sd.length; j++)
				{
					Document doc1 = searcher.doc(sd[j].doc);
					ProductSub pst = new ProductSub();
					pst.setSpProductId(new BigDecimal(doc1.get("spProductId")));
					pst.setSpId(new BigDecimal(doc1.get("spId")));
					pst.setSaId(new BigDecimal(doc1.get("saId")));
					pst.setSupplyId(new BigDecimal(doc1.get("supplyId")));
					pst.setCustomerId(new BigDecimal(doc1.get("customerId")));
					pst.setShopName(doc1.get("shopName"));
					pst.setAreaId(new BigDecimal(doc1.get("areaId")));
					pst.setSupplyName(doc1.get("supplyName"));
					pst.setPriceretailonline(new BigDecimal(doc1.get("priceretailonline")));
					pst.setOverplusNumber(new BigDecimal(doc1.get("overplusNumber")));
					pst.setHits(new BigDecimal(doc1.get("hits")));
					pst.setOnshelftime(doc1.get("onshelftime"));
					pst.setSaleqty(new BigDecimal(doc1.get("saleqty")));
					S2.add(pst);
				}

				List<ProductSub> S1 = new ArrayList<ProductSub>();
				/** 省包 */
				for (int k = 0; k < provLs.size(); k++)
				{
					ProductSub pr = provLs.get(k);
					if (pr.getSpProductId().equals(productId))
					{
						ProductSub pst = new ProductSub();
						pst.setSpProductId(pr.getSpProductId());
						pst.setSpId(pr.getSpId());
						pst.setSaId(pr.getSaId());
						pst.setSupplyId(pr.getSupplyId());
						pst.setCustomerId(pr.getCustomerId());
						pst.setShopName(pr.getShopName());
						pst.setAreaId(pr.getAreaId());
						pst.setSupplyName(pr.getSupplyName());
						pst.setPriceretailonline(pr.getPriceretailonline());
						pst.setOverplusNumber(pr.getOverplusNumber());
						pst.setHits(pr.getHits());
						pst.setOnshelftime(pr.getOnshelftime());
						pst.setSaleqty(pr.getSaleqty());
						S1.add(pst);
					}
				}

				List<ProductSub> S3 = new ArrayList<ProductSub>();
				for (int j = 0; j < hjhLs.size(); j++)
				{
					ProductSub pr = hjhLs.get(j);
					if (pr.getSpProductId().equals(productId))
					{
						ProductSub pst = new ProductSub();
						pst.setSpProductId(pr.getSpProductId());
						pst.setSpId(pr.getSpId());
						pst.setSaId(pr.getSaId());
						pst.setSupplyId(pr.getSupplyId());
						pst.setCustomerId(pr.getCustomerId());
						pst.setShopName(pr.getShopName());
						pst.setAreaId(pr.getAreaId());
						pst.setSupplyName(pr.getSupplyName());
						pst.setPriceretailonline(pr.getPriceretailonline());
						pst.setOverplusNumber(pr.getOverplusNumber());
						pst.setHits(pr.getHits());
						pst.setOnshelftime(pr.getOnshelftime());
						pst.setSaleqty(pr.getSaleqty());
						S3.add(pst);
					}
				}

				if (S1.size() > 0)
				{
					System.out.println("========================判断省包与索引是否相同=====================");
					boolean fla = checkHJHEqualsIndex(S1, S2); // S2是索引
					if (!fla)
					{
						delteIndex(productId.toString());
						for (int k = 0; k < provLs.size(); k++)// 省包
						{
							ProductSub pr = provLs.get(k);
							if (productId.equals(pr.getSpProductId()))
							{
								b = productId;
								addProductSub(pr);
								System.out.println("加上子表pid====" + pr.getSpProductId() + "===" + pr.getSaId());
							}

							if (!b.equals(new BigDecimal(0)) && !b.equals(pr.getSpProductId()))
							{
								addProductVo(pv);
								System.out.println("==不相等的时候更新添加了主表=========加省包主表表" + pv.getProductId());
								b = new BigDecimal(0);
							}
						}
					}
				}

				if (S3.size() > 0)
				{
					System.out.println("========================判断好机汇与索引是否相同=====================");
					boolean cc = checkHJHEqualsIndex(S3, S2);
					if (!cc)
					{ 
						delteIndex(productId.toString());
						for (int k = 0; k < hjhLs.size(); k++)
						{
							ProductSub pr = hjhLs.get(k);
							if (productId.equals(pr.getSpProductId()))
							{
								b = productId;
								addProductSub(pr);
								System.out.println("==不相等的时候更新添加了子表=====" + pr.getSpProductId());
							}

							if (!b.equals(new BigDecimal(0)) && !b.equals(pr.getSpProductId()))
							{
								addProductVo(pv);
								System.out.println("====不相等的时候更新添加了主表======加省包主表表" + pv.getProductId());
								b = new BigDecimal(0);
							}
						}
					}
				}
				/************* 判断主表 **************/

				boolean fl = isEqualsByProduct(pv, searcher.doc(tI.scoreDocs[0].doc));
				if (!fl)
				{
					delteIndex(productId.toString());
					for (int k = 0; k < provLs.size(); k++)
					{
						ProductSub pr = provLs.get(k);
						if (productId.equals(pr.getSpProductId()))
						{
							b = productId;
							addProductSub(pr);
							System.out.println("==不相等的时候更新添加了子表=====" + pr.getSpProductId());
						}

						if (!b.equals(new BigDecimal(0)) && !b.equals(pr.getSpProductId()))
						{
							addProductVo(pv);
							System.out.println("====不相等的时候更新添加了主表======加省包主表表" + pv.getProductId());
							b = new BigDecimal(0);
						}
					}
					for (int j = 0; j < hjhLs.size(); j++)
					{
						ProductSub pr = hjhLs.get(j);
						if (pr.getSpProductId().equals(productId))
						{
							addProductSub(pr);
							System.out.println("====" + pr.getSpProductId());
							addProductVo(pv);
							System.out.println("====加好机汇子表" + pr.getSpProductId());
						}
					}
					
					
				}

			} else
			{
				for (int k = 0; k < provLs.size(); k++)
				{
					ProductSub pr = provLs.get(k);
					if (productId.equals(pr.getSpProductId()))
					{
						b = pv.getProductId();
						addProductSub(pr);
						System.out.println("====新增省包子表" + pr.getSpProductId());
					}

					if (!b.equals(new BigDecimal(0)) && !b.equals(pr.getSpProductId()))
					{
						addProductVo(pv);
						System.out.println("====新增省包产品ID" + pv.getProductId());
						b = new BigDecimal(0);
					}
				}

				for (int j = 0; j < hjhLs.size(); j++)
				{
					ProductSub pr = hjhLs.get(j);
					if (pr.getSpProductId().equals(productId))
					{
						addProductSub(pr);
						System.out.println("====" + pr.getSpProductId());
						addProductVo(pv);
						System.out.println("====加好机汇子表" + pr.getSpProductId());
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param
	 * @return
	 * @author caosheng
	 * @version V1.0
	 * @throws Exception
	 */
	private boolean checkHJHEqualsIndex(List<ProductSub> S1, List<ProductSub> S2) throws Exception
	{
		if (S2.size() == 0)// S2是索引List
		{
			return true;
		}

		if (S1.size() != S2.size())
		{
			return false;
		}
		for (int j = 0; j < S1.size(); j++)
		{
			ProductSub subVo = S1.get(j);
			ProductSub indexVo = S2.get(j);
			System.out.println("=======================================" + subVo.getSpProductId());
			if (!StringHelperTools.nvl(subVo.getSaId()).equals(StringHelperTools.nvl(indexVo.getSaId().toString()))
					|| !StringHelperTools.nvl(subVo.getSpId()).equals(StringHelperTools.nvl(indexVo.getSpId()))
					|| !StringHelperTools.nvl(subVo.getSupplyId()).equals(StringHelperTools.nvl(indexVo.getSupplyId()))
					|| !StringHelperTools.nvl(subVo.getSupplyName()).equals(
							StringHelperTools.nvl(indexVo.getSupplyName()))
					|| !StringHelperTools.nvl(subVo.getAreaId()).equals(StringHelperTools.nvl(indexVo.getAreaId()))
					|| !StringHelperTools.nvl(subVo.getCustomerId()).equals(
							StringHelperTools.nvl(indexVo.getCustomerId()))
					|| !(subVo.getPriceretailonline().compareTo(indexVo.getPriceretailonline()) == 0)
					|| !(subVo.getOverplusNumber().compareTo(indexVo.getOverplusNumber()) == 0)
					|| !(subVo.getHits().compareTo(indexVo.getHits()) == 0)
					|| !(subVo.getSaleqty().compareTo(indexVo.getSaleqty()) == 0))
			{
				return false;
			}
		}
		return true;
	}

	public void delteIndex(String pId) throws Exception
	{
		iwriter.deleteDocuments(new Term("productId", pId));
		iwriter.deleteDocuments(new Term("spProductId", pId));
		iwriter.commit();
		System.out.println("=========================删除产品索引" + pId);
	}

	public void te() throws Exception
	{
		updateIndex();
		Analyzer analyzer = new org.apache.lucene.analysis.standard.StandardAnalyzer(Version.LUCENE_44);
		Directory directory = FSDirectory.open(new File(filePath));
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);
		String[] multiFields =
		{ "productName" };
		MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_44, multiFields, analyzer);
		Query query = parser.parse("酷派");
		TopDocs docs = isearcher.search(query, null, 10);// 查找
		ScoreDoc[] hits = docs.scoreDocs;
		SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("", "");
		Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));
		highlighter.setTextFragmenter(new SimpleFragmenter(33));
		System.out.println("Searched " + hits.length + " documents.");
		for (int i = 0; i < hits.length; i++)
		{
			Document hitDoc = isearcher.doc(hits[i].doc);
			String[] scoreExplain = isearcher.explain(query, hits[i].doc).toString().split(" ", 2);
			String scores = scoreExplain[0];
			System.err.println("score:" + scores);
			String value = hitDoc.get("productName");
			TokenStream tokenStream = analyzer.tokenStream(value, new StringReader(value));
			String str1 = highlighter.getBestFragment(tokenStream, value);
			System.err.println(str1);
		}
		ireader.close();
		directory.close();

	}

	/**
	 * 
	 * @param
	 * @return
	 * @author caosheng
	 * @version V1.0
	 */
	private void addProductVo(ProductVo pv) throws IOException
	{
		Document prodDoc = new Document();
		prodDoc.add(new Field("productId", pv.getProductId().toString(), TextField.TYPE_STORED));
		prodDoc.add(new Field("productName", StringHelperTools.nvl(pv.getProductName()), TextField.TYPE_STORED));
		prodDoc.add(new Field("picturePath", StringHelperTools.nvl(pv.getPicturePath()), TextField.TYPE_STORED));
		prodDoc.add(new Field("brandName", StringHelperTools.nvl(pv.getBrandName()), TextField.TYPE_STORED));
		prodDoc.add(new Field("memory", StringHelperTools.nvl(pv.getMemory()), TextField.TYPE_STORED));
		prodDoc.add(new Field("color", StringHelperTools.nvl(pv.getColor()), TextField.TYPE_STORED));
		prodDoc.add(new Field("category", StringHelperTools.nvl(pv.getCategory()), TextField.TYPE_STORED));
		prodDoc.add(new Field("network", StringHelperTools.nvl(pv.getNetwork()), TextField.TYPE_STORED));
		prodDoc.add(new Field("screen", StringHelperTools.nvl(pv.getScreen()), TextField.TYPE_STORED));
		prodDoc.add(new Field("system", StringHelperTools.nvl(pv.getSysname()), TextField.TYPE_STORED));
		prodDoc.add(new Field("saletype", StringHelperTools.nvl(pv.getSaleType()), TextField.TYPE_STORED));
		prodDoc.add(new Field("productPattern", StringHelperTools.nvl(pv.getProductPattern()), TextField.TYPE_STORED));
		iwriter.addDocument(prodDoc);
		iwriter.commit();
	}

	/**
	 * 
	 * @param
	 * @return
	 * @author caosheng
	 * @version V1.0
	 */
	private void addProductSub(ProductSub ps) throws IOException
	{
		Document doc1 = new Document();
		doc1.add(new Field("spProductId", ps.getSpProductId().toString(), TextField.TYPE_STORED));
		doc1.add(new Field("spId", StringHelperTools.nvl(ps.getSpId()), TextField.TYPE_STORED));
		doc1.add(new Field("saId", StringHelperTools.nvl(ps.getSaId()), TextField.TYPE_STORED));
		doc1.add(new Field("supplyId", StringHelperTools.nvl(ps.getSupplyId()), TextField.TYPE_STORED));
		doc1.add(new Field("customerId", StringHelperTools.nvl(ps.getCustomerId()), TextField.TYPE_STORED));
		doc1.add(new Field("shopName", StringHelperTools.nvl(ps.getShopName()), TextField.TYPE_STORED));
		doc1.add(new Field("areaId", StringHelperTools.nvl(ps.getAreaId()), TextField.TYPE_STORED));
		doc1.add(new Field("supplyName", StringHelperTools.nvl(ps.getSupplyName()), TextField.TYPE_STORED));
		doc1.add(new Field("priceretailonline", StringHelperTools.nvl(ps.getPriceretailonline()), TextField.TYPE_STORED));
		doc1.add(new Field("overplusNumber", StringHelperTools.nvl(ps.getOverplusNumber()), TextField.TYPE_STORED));
		doc1.add(new Field("hits", StringHelperTools.nvl(ps.getHits()), TextField.TYPE_STORED));
		doc1.add(new Field("onshelftime", StringHelperTools.nvl(ps.getOnshelftime()), TextField.TYPE_STORED));
		doc1.add(new Field("saleqty", StringHelperTools.nvl(ps.getSaleqty()), TextField.TYPE_STORED));
		iwriter.addDocument(doc1);
	}

	public boolean isEqualsByProductSub(ProductSub sb, Document doc1)
	{
		if (!StringHelperTools.nvl(sb.getSpProductId()).equals(StringHelperTools.nvl(doc1.get("spProductId")))
				|| !StringHelperTools.nvl(sb.getSpId()).equals(StringHelperTools.nvl(doc1.get("spId")))
				|| !StringHelperTools.nvl(sb.getSaId()).toString().equals(StringHelperTools.nvl(doc1.get("saId")))
				|| !StringHelperTools.nvl(sb.getSupplyId()).equals(StringHelperTools.nvl(doc1.get("supplyId")))
				|| !StringHelperTools.nvl(sb.getCustomerId()).equals(StringHelperTools.nvl(doc1.get("customerId")))
				|| !StringHelperTools.nvl(sb.getShopName()).equals(StringHelperTools.nvl(doc1.get("shopName")))
				|| !StringHelperTools.nvl(sb.getAreaId()).equals(StringHelperTools.nvl(doc1.get("areaId")))
				|| !StringHelperTools.nvl(sb.getSupplyName()).equals(StringHelperTools.nvl(doc1.get("supplyName")))
				|| !StringHelperTools.nvl(sb.getPriceretailonline()).equals(
						StringHelperTools.nvl(doc1.get("priceretailonline")))
				|| !StringHelperTools.nvl(sb.getOverplusNumber()).equals(
						StringHelperTools.nvl(doc1.get("overplusNumber")))
				|| !StringHelperTools.nvl(sb.getHits()).equals(StringHelperTools.nvl(doc1.get("hits")))
				|| !StringHelperTools.nvl(sb.getOnshelftime()).equals(StringHelperTools.nvl(doc1.get("onshelftime")))
				|| !StringHelperTools.nvl(sb.getSaleqty()).equals(StringHelperTools.nvl(doc1.get("saleqty"))))
		{
			return false;
		}
		return true;

	}

	public boolean isEqualsByProduct(ProductVo pv, Document doc)
	{

		if (!StringHelperTools.nvl(pv.getProductName()).equals(StringHelperTools.nvl(doc.get("productName")))
				|| !StringHelperTools.nvl(pv.getPicturePath()).equals(StringHelperTools.nvl(doc.get("picturePath")))
				|| !StringHelperTools.nvl(pv.getBrandName()).toString()
						.equals(StringHelperTools.nvl(doc.get("brandName")))
				|| !StringHelperTools.nvl(pv.getProductPattern()).equals(
						StringHelperTools.nvl(doc.get("productPattern")))
				|| !StringHelperTools.nvl(pv.getMemory()).equals(StringHelperTools.nvl(doc.get("memory")))
				|| !StringHelperTools.nvl(pv.getColor()).equals(StringHelperTools.nvl(doc.get("color")))
				|| !StringHelperTools.nvl(pv.getCategory()).equals(StringHelperTools.nvl(doc.get("category")))
				|| !StringHelperTools.nvl(pv.getNetwork()).equals(StringHelperTools.nvl(doc.get("network")))
				|| !StringHelperTools.nvl(pv.getScreen()).equals(StringHelperTools.nvl(doc.get("screen")))
				|| !StringHelperTools.nvl(pv.getSysname()).equals(StringHelperTools.nvl(doc.get("system")))
				|| !StringHelperTools.nvl(pv.getSaleType()).equals(StringHelperTools.nvl(doc.get("saletype"))))
		{
			return false;
		}
		return true;

	}
}
