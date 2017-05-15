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
import java.util.Collections;
import java.util.Comparator;
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
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
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

@ContextConfiguration(locations = "classpath:spring-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@SuppressWarnings("deprecation")
public class LuceneUtil3 {
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
		config.setOpenMode(OpenMode.CREATE);
		iwriter = new IndexWriter(FSDirectory.open(new File(filePath)), config);

		List<ProductVo> pLs = shopService.getProduct();
		List<ProductSub> txLs = shopService.getTongxun();
		List<ProductSub> prLs = shopService.getProvince();

		for (int i = 0; i < pLs.size(); i++)
		{
			ProductVo pv = pLs.get(i);

			for (int j = 0; j < txLs.size(); j++)
			{
				ProductSub pr = txLs.get(j);
				if (pv.getProductId().equals(pr.getSpProductId()))
				{
					addProductSub(pr); // 好机汇
				}
			}
			for (int k = 0; k < prLs.size(); k++)
			{
				ProductSub pr = prLs.get(k);
				if (pv.getProductId().equals(pr.getSpProductId()))
				{
					addProductSub(pr);// 添加省包
				}
			}
			addProductVo(pv);
		}
		iwriter.commit();
		te();
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
		Query query = parser.parse("天语");
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

	public void delteIndex(String pId) throws Exception
	{
		iwriter.deleteDocuments(new Term("productId", pId));
		iwriter.deleteDocuments(new Term("spProductId", pId));
		iwriter.commit();
	}

	public void updateIndex() throws Exception
	{
		Directory directory = FSDirectory.open(new File(filePath));
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(ireader);
		indexReader = IndexReader.open(directory);
		int docLength = indexReader.numDocs();
		System.err.println("索引里一共有" + docLength + "篇文档");
		List<ProductVo> pLs = shopService.getProduct();
		List<ProductSub> txLs = shopService.getTongxun();
//		List<ProductSub> sbLs = shopService.getProvince();
		for (int i = 0; i < pLs.size(); i++)
		{
			ProductVo pv = pLs.get(i);
			BigDecimal productId = pv.getProductId();
			TopDocs td = searcher.search(new TermQuery(new Term("productId", productId.toString())), 1);
			Document doc = searcher.doc(td.scoreDocs[0].doc);
			int hit = td.totalHits;
			if (hit > 0)
			{
				if (!isEqualsByProduct(pv, doc))
				{
					System.out.println("主表数据更改-更新");
					delteIndex(productId.toString());
					addProductVo(pv);// 更新产品主表
					continue;
				}
				// 判断子表更新
				TopDocs td1 = searcher.search(new TermQuery(new Term("spProductId", productId.toString())), 6);
				ScoreDoc[] sdl = td1.scoreDocs;
				List<ProductSub> txL = new ArrayList<ProductSub>();
				for (int j = 0; j < sdl.length; j++)
				{
				
					Document doc1 = searcher.doc(sdl[j].doc);

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
					txL.add(pst);

				}
				if (txL.size()!=txLs.size())
				{
					return;
				}
				Collections.sort(txLs, new Comparator<ProductSub>() {
					@Override
					public int compare(ProductSub arg0, ProductSub arg1) {
						return arg0.getSaId().compareTo(arg1.getSaId());
					}
				});
				Collections.sort(txL, new Comparator<ProductSub>() {
					@Override
					public int compare(ProductSub arg0, ProductSub arg1) {
						return arg0.getSaId().compareTo(arg1.getSaId());
					}
				});
				for (int j = 0; j < txL.size(); j++)
				{
					int count=0;
					ProductSub indexVo=txL.get(j);
					ProductSub subVo=txLs.get(j);
					if (!StringHelperTools.nvl(subVo.getSaId()).equals(StringHelperTools.nvl(indexVo.getSaId().toString()))
							|| !StringHelperTools.nvl(subVo.getSpId()).equals(StringHelperTools.nvl(indexVo.getSpId()))
							|| !StringHelperTools.nvl(subVo.getSupplyId()).equals(StringHelperTools.nvl(indexVo.getSupplyId()))
							|| !StringHelperTools.nvl(subVo.getSupplyName()).equals(StringHelperTools.nvl(indexVo.getSupplyName()))
							|| !StringHelperTools.nvl(subVo.getAreaId()).equals(StringHelperTools.nvl(indexVo.getAreaId()))
							|| !StringHelperTools.nvl(subVo.getCustomerId()).equals(StringHelperTools.nvl(indexVo.getCustomerId()))
							|| !(subVo.getPriceretailonline().compareTo(indexVo.getPriceretailonline())==0)
							|| !(subVo.getOverplusNumber().compareTo(indexVo.getOverplusNumber())==0)
							|| !(subVo.getHits().compareTo(indexVo.getHits())==0)
							|| !(subVo.getSaleqty().compareTo(indexVo.getSaleqty())==0))
					{
						delteIndex(subVo.getSpProductId().toString());
						addProductSub(subVo);
						count++;
						System.out.println("更新省包------------------"+count+"次");
					}
				}
				

			}
		}
		iwriter.commit();
		iwriter.close();
		indexReader.close();
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
		doc1.add(new Field("spId", StringHelperTools.nvl(ps.getSpId().toString()), TextField.TYPE_STORED));
		doc1.add(new Field("saId", StringHelperTools.nvl(ps.getSaId().toString()), TextField.TYPE_STORED));
		doc1.add(new Field("supplyId", StringHelperTools.nvl(ps.getSupplyId().toString()), TextField.TYPE_STORED));
		doc1.add(new Field("customerId", StringHelperTools.nvl(ps.getCustomerId().toString()), TextField.TYPE_STORED));
		doc1.add(new Field("shopName", StringHelperTools.nvl(ps.getShopName()), TextField.TYPE_STORED));
		doc1.add(new Field("areaId", StringHelperTools.nvl(ps.getAreaId().toString()), TextField.TYPE_STORED));
		doc1.add(new Field("supplyName", StringHelperTools.nvl(ps.getSupplyName()), TextField.TYPE_STORED));
		doc1.add(new Field("priceretailonline", StringHelperTools.nvl(ps.getPriceretailonline()), TextField.TYPE_STORED));
		doc1.add(new Field("overplusNumber", StringHelperTools.nvl(ps.getOverplusNumber().toString()),
				TextField.TYPE_STORED));
		doc1.add(new Field("hits", StringHelperTools.nvl(ps.getHits().toString()), TextField.TYPE_STORED));
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
