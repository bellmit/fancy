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
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
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
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.telling.shop.service.IShopService;
import cn.telling.shop.vo.ProductSub;
import cn.telling.shop.vo.ProductVo;
import cn.telling.utils.LuceneCommon;
import cn.telling.utils.StringHelperTools;

@ContextConfiguration(locations = "classpath:spring-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class LuceneUtil6 {
	public static String flag = "1";
	private Analyzer any = new StandardAnalyzer(Version.LUCENE_44);
	private IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_44, any);

	private IndexWriter iwriter1 = null;
	private IndexWriter iwriter2 = null;

	IndexSearcher isearcher = null;

	private File file1 = new File("d:\\file1");
	private File file2 = new File("d:\\file2");

	private Directory dire = null;
	@Autowired
	LuceneCommon util;
	@Autowired
	private IShopService shopService;

	public void addProductIndex1() {
		iwriter1 = getIndexWrite1();
		try {
			iwriter1.deleteAll();

			// List<ProductVo> prodLs = shopService.getProduct();
			// for (int i = 0; i < prodLs.size(); i++) {
			// ProductVo pv = prodLs.get(i);
			// addProductVo(pv, iwriter1);
			// }
			addIndex(iwriter1);
			System.out.println("================索引1添加完毕");
			iwriter1.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addProductIndex2() {
		iwriter2 = getIndexWrite2();
		try {
			iwriter2.deleteAll();
			// List<ProductVo> prodLs = shopService.getProduct();
			// for (int i = 0; i < prodLs.size(); i++) {
			// ProductVo pv = prodLs.get(i);
			// addProductVo(pv, iwriter2);
			// }
			addIndex(iwriter2);
			System.out.println("================索引2添加完毕");
			iwriter2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public IndexWriter getIndexWrite1() {
		try {
			if (iwriter1 == null) {
				dire = getDirectory1();
			}
			iwriter1 = new IndexWriter(dire, config);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return iwriter1;
	}

	public IndexWriter getIndexWrite2() {
		try {
			if (iwriter2 == null) {
				dire = getDirectory2();
			}
			iwriter2 = new IndexWriter(dire, config);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return iwriter2;
	}

	public void schedule() {
		if (flag.equals("1")) {// /初始化添加索引
			System.out.println("=======================首次切换索引1");
			addProductIndex1();
			flag = "2";
		} else if (flag.equals("2")) {
			addProductIndex2();
			flag = "3";
			System.out.println("=======================切换到索引2");
			
		} else if (flag.equals("3")) {
			addProductIndex1();
			flag = "2";
			System.out.println("=======================切换索引1");
		}

	}

	@Test
	public void test() throws InterruptedException {
		System.out.println("开始执行");
	
		while (true) {
			try {
				queryProduct();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Thread.sleep(60000);
		}
	}

	public List<ProductIndex> getProdColle() {
		List<ProductVo> prodLs = shopService.getProduct();
		// List<ProductSub> prodSubLs=shopService.getProSub();
		// List<ProductSub> hjhLs=shopService.getHJHProSub();
		List<ProductIndex> piList = new ArrayList<ProductIndex>();
		for (int i = 0; i < prodLs.size(); i++) {
			ProductIndex pi = new ProductIndex();
			List<ProductSub> psList = new ArrayList<ProductSub>();
			ProductVo pv = prodLs.get(i);
			// List<ProductSub>psLs=new ArrayList<ProductSub>();
			if (pv.getSaleType().equals("2")) {

				psList = shopService.getHJHProSub(pv.getProductId());
				// for (int j = 0; j < hjhLs.size(); j++) {
				//
				// ProductSub ps=hjhLs.get(j);
				// if (ps.getSpProductId().equals(pv.getProductId())) {
				// psLs.add(ps);
				// }
				// }
				// psList=psLs;

			} else {
				psList = shopService.getProSub(pv.getProductId());
				// for (int j = 0; j < prodSubLs.size(); j++) {
				//
				// ProductSub ps=prodSubLs.get(j);
				// if (ps.getSpProductId().equals(pv.getProductId())) {
				// psLs.add(ps);
				//
				// }
				// }
				// psList=psLs;
			}
			if (psList.size() == 0) {
				continue;
			}
			pi.setPsLs(psList);
			pi.setPv(pv);
			piList.add(pi);
		}
		System.out.println("一共遍历了主表" + prodLs.size() + "次=========产品集合表共有：" + piList.size());
		return piList;
	}

	private void addIndex(IndexWriter iw) throws IOException {
		List<ProductIndex> piLs = getProdColle();
		for (int i = 0; i < piLs.size(); i++) {
			ProductIndex pi = piLs.get(i);
			ProductVo pv = pi.getPv();
			List<ProductSub> psList = pi.getPsLs();
			for (int j = 0; j < psList.size(); j++) {
				ProductSub ps = psList.get(j);
				if (ps.getSpProductId().equals(pv.getProductId())) {
					addProductSub(ps, iw);
				}
			}
			addProductVo(pv, iw);
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
	private boolean checkHJHEqualsIndex(List<ProductSub> S1, List<ProductSub> S2) throws Exception {
		if (S2.size() == 0)// S2是索引List
			return true;
		if (S1.size() != S2.size())
			return false;
		for (int j = 0; j < S1.size(); j++) {
			ProductSub subVo = S1.get(j);
			ProductSub indexVo = S2.get(j);
			System.out.println("=========" + subVo.getSpProductId());
			if (!StringHelperTools.nvl(subVo.getSaId()).equals(StringHelperTools.nvl(indexVo.getSaId().toString()))
					|| !StringHelperTools.nvl(subVo.getSpId()).equals(StringHelperTools.nvl(indexVo.getSpId()))
					|| !StringHelperTools.nvl(subVo.getSupplyId()).equals(StringHelperTools.nvl(indexVo.getSupplyId()))
					|| !StringHelperTools.nvl(subVo.getSupplyName()).equals(StringHelperTools.nvl(indexVo.getSupplyName()))
					|| !StringHelperTools.nvl(subVo.getAreaId()).equals(StringHelperTools.nvl(indexVo.getAreaId()))
					|| !StringHelperTools.nvl(subVo.getCustomerId()).equals(StringHelperTools.nvl(indexVo.getCustomerId()))
					|| !(subVo.getPriceretailonline().compareTo(indexVo.getPriceretailonline()) == 0)
					|| !(subVo.getOverplusNumber().compareTo(indexVo.getOverplusNumber()) == 0) || !(subVo.getHits().compareTo(indexVo.getHits()) == 0)
					|| !(subVo.getSaleqty().compareTo(indexVo.getSaleqty()) == 0)) {
				return false;
			}
		}
		return true;
	}

	public synchronized Directory getDirectory1() {
		try {

			if (file1.exists()) {
				dire = FSDirectory.open(file1);
			} else {
				dire = new SimpleFSDirectory(file1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dire;
	}

	public synchronized Directory getDirectory2() {
		try {
			if (file2.exists()) {
				dire = FSDirectory.open(file2);
			} else {
				dire = new SimpleFSDirectory(file2);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dire;
	}

	public synchronized IndexSearcher getIndexSearch() {
		try {
			if (flag.equals("1") || flag.equals("2")) {
				dire = getDirectory1();
				System.out.println("=====================IndexReader使用索引1读取");
			} else if (flag.equals("3")) {
				dire = getDirectory2();
				System.out.println("=====================IndexReader使用索引2读取");
			}

			DirectoryReader ireader = DirectoryReader.open(dire);
			isearcher = new IndexSearcher(ireader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isearcher;
	}

	public void queryProduct() throws Exception {
		
		isearcher = getIndexSearch();
		String[] multiFields = { "productName","brandName" };
		Query query1 = new TermQuery(new Term("productId","11111111"));
		
		MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_44, multiFields, any);
		Query query = parser.parse("联想");
		TopDocs docs = isearcher.search(query, null, 10);// 查找
		ScoreDoc[] hits = docs.scoreDocs;
		SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("", "");
		Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));
		highlighter.setTextFragmenter(new SimpleFragmenter(33));
		System.out.println("Searched " + hits.length + " documents.");
		for (int i = 0; i < hits.length; i++) {
			Document hitDoc = isearcher.doc(hits[i].doc);
			String[] scoreExplain = isearcher.explain(query, hits[i].doc).toString().split(" ", 2);
			String scores = scoreExplain[0];
			System.err.println("score:" + scores);
			String value = hitDoc.get("productName");
			TokenStream tokenStream = any.tokenStream(value, new StringReader(value));
			String str1 = highlighter.getBestFragment(tokenStream, value);
			System.err.println(str1);
		}
	}

	public void delteIndex(String pId) throws Exception {
		iwriter1.deleteDocuments(new Term("productId", pId));
		iwriter1.deleteDocuments(new Term("spProductId", pId));
		iwriter1.commit();
		System.out.println("=========================删除产品索引" + pId);
	}

	public static void main(String[] args) {
		Long time=System.currentTimeMillis();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long e=System.currentTimeMillis();
		System.out.println((e-time)/1000);
		
	}
	/**
	 * 
	 * @param
	 * @return
	 * @author caosheng
	 * @version V1.0
	 */
	private void addProductVo(ProductVo pv, IndexWriter iw) throws IOException {
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
		iw.addDocument(prodDoc);
		iw.commit();
	}

	/**
	 * 
	 * @param
	 * @return
	 * @author caosheng
	 * @version V1.0
	 */
	private void addProductSub(ProductSub ps, IndexWriter iw) throws IOException {
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
		iw.addDocument(doc1);
	}

	public boolean isEqualsByProductSub(ProductSub sb, Document doc1) {
		if (!StringHelperTools.nvl(sb.getSpProductId()).equals(StringHelperTools.nvl(doc1.get("spProductId")))
				|| !StringHelperTools.nvl(sb.getSpId()).equals(StringHelperTools.nvl(doc1.get("spId")))
				|| !StringHelperTools.nvl(sb.getSaId()).toString().equals(StringHelperTools.nvl(doc1.get("saId")))
				|| !StringHelperTools.nvl(sb.getSupplyId()).equals(StringHelperTools.nvl(doc1.get("supplyId")))
				|| !StringHelperTools.nvl(sb.getCustomerId()).equals(StringHelperTools.nvl(doc1.get("customerId")))
				|| !StringHelperTools.nvl(sb.getShopName()).equals(StringHelperTools.nvl(doc1.get("shopName")))
				|| !StringHelperTools.nvl(sb.getAreaId()).equals(StringHelperTools.nvl(doc1.get("areaId")))
				|| !StringHelperTools.nvl(sb.getSupplyName()).equals(StringHelperTools.nvl(doc1.get("supplyName")))
				|| !StringHelperTools.nvl(sb.getPriceretailonline()).equals(StringHelperTools.nvl(doc1.get("priceretailonline")))
				|| !StringHelperTools.nvl(sb.getOverplusNumber()).equals(StringHelperTools.nvl(doc1.get("overplusNumber")))
				|| !StringHelperTools.nvl(sb.getHits()).equals(StringHelperTools.nvl(doc1.get("hits")))
				|| !StringHelperTools.nvl(sb.getOnshelftime()).equals(StringHelperTools.nvl(doc1.get("onshelftime")))
				|| !StringHelperTools.nvl(sb.getSaleqty()).equals(StringHelperTools.nvl(doc1.get("saleqty")))) {
			return false;
		}
		return true;

	}

	public boolean isEqualsByProduct(ProductVo pv, Document doc) {
		if (!StringHelperTools.nvl(pv.getProductName()).equals(StringHelperTools.nvl(doc.get("productName")))
				|| !StringHelperTools.nvl(pv.getPicturePath()).equals(StringHelperTools.nvl(doc.get("picturePath")))
				|| !StringHelperTools.nvl(pv.getBrandName()).toString().equals(StringHelperTools.nvl(doc.get("brandName")))
				|| !StringHelperTools.nvl(pv.getProductPattern()).equals(StringHelperTools.nvl(doc.get("productPattern")))
				|| !StringHelperTools.nvl(pv.getMemory()).equals(StringHelperTools.nvl(doc.get("memory")))
				|| !StringHelperTools.nvl(pv.getColor()).equals(StringHelperTools.nvl(doc.get("color")))
				|| !StringHelperTools.nvl(pv.getCategory()).equals(StringHelperTools.nvl(doc.get("category")))
				|| !StringHelperTools.nvl(pv.getNetwork()).equals(StringHelperTools.nvl(doc.get("network")))
				|| !StringHelperTools.nvl(pv.getScreen()).equals(StringHelperTools.nvl(doc.get("screen")))
				|| !StringHelperTools.nvl(pv.getSysname()).equals(StringHelperTools.nvl(doc.get("system")))
				|| !StringHelperTools.nvl(pv.getSaleType()).equals(StringHelperTools.nvl(doc.get("saletype")))) {
			return false;
		}
		return true;
	}
}
