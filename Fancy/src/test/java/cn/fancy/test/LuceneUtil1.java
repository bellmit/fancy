///**
// * 
// * Project Name:myweb-web
// * File Name:LuceneUtil.java
// * Package Name:cn.fancy.test
// * Date:2015-7-27
// *
// */
//
//package cn.fancy.test;
//
//import java.io.File;
//import java.io.StringReader;
//import java.util.List;
//
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.TokenStream;
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.document.TextField;
//import org.apache.lucene.index.DirectoryReader;
//import org.apache.lucene.index.IndexReader;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.IndexWriterConfig;
//import org.apache.lucene.index.IndexWriterConfig.OpenMode;
//import org.apache.lucene.index.Term;
//import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.search.ScoreDoc;
//import org.apache.lucene.search.TermQuery;
//import org.apache.lucene.search.TopDocs;
//import org.apache.lucene.search.highlight.Highlighter;
//import org.apache.lucene.search.highlight.QueryScorer;
//import org.apache.lucene.search.highlight.SimpleFragmenter;
//import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.FSDirectory;
//import org.apache.lucene.util.Version;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.fancy.action.shop.service.IShopService;
//import com.fancy.action.shop.vo.ProductSub;
//import com.fancy.action.shop.vo.ProductVo;
//
//@ContextConfiguration(locations = "classpath:applicationContext.xml")
//@RunWith(SpringJUnit4ClassRunner.class)
//@SuppressWarnings("deprecation")
//public class LuceneUtil1 {
//	private final static String filePath = "d:\\test1";
//
//	private IndexWriter iwriter = null;
//	private IndexReader indexReader = null;
//	Analyzer any = new StandardAnalyzer(Version.LUCENE_44);
//	@Autowired
//	private IShopService shopService;
//
//	@Test
//	public void test() throws Exception
//	{
//
//		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_44, any);
//		config.setOpenMode(OpenMode.CREATE);
//		iwriter = new IndexWriter(FSDirectory.open(new File(filePath)), config);
//
//		List<ProductVo> pLs = shopService.getProduct();
//		List<ProductSub> txLs = shopService.getTongxun();
//		List<ProductSub> prLs = shopService.getProvince();
//		// /省包
//		for (int i = 0; i < pLs.size(); i++)
//		{
//			Document doc = new Document();
//			ProductVo pv = pLs.get(i);
//			for (int k = 0; k < prLs.size(); k++)
//			{
//				Document doc1 = new Document();
//				ProductSub pr = prLs.get(k);
//				if (pv.getProductId().equals(pr.getSpProductId()))
//				{
//					doc1.add(new Field("spProductId", pr.getSpProductId(), TextField.TYPE_STORED));
//					doc1.add(new Field("spId", pr.getSpId(), TextField.TYPE_STORED));
//					doc1.add(new Field("supplyId", pr.getSupplyId(), TextField.TYPE_STORED));
//					doc1.add(new Field("supplyName", pr.getSupplyName(), TextField.TYPE_STORED));
//					doc1.add(new Field("shopName", pr.getShopName(), TextField.TYPE_STORED));
//					doc1.add(new Field("priceretailonline", pr.getPriceretailonline(), TextField.TYPE_STORED));
//					doc1.add(new Field("overplusNumber", pr.getOverplusNumber(), TextField.TYPE_STORED));
//					doc1.add(new Field("hits", pr.getHits(), TextField.TYPE_STORED));
//					doc1.add(new Field("onshelftime", pr.getOnshelftime(), TextField.TYPE_STORED));
//					iwriter.addDocument(doc1);
//					System.out.println(doc1.getFields());
//				}
//			}
//	
//			// 好机汇
//			for (int j = 0; j < txLs.size(); j++)
//			{
//				ProductSub pr = txLs.get(j);
//				if (pv.getProductId().equals(pr.getSpProductId()))
//				{
//					Document doc1 = new Document();
//					doc1.add(new Field("spProductId", pr.getSpProductId(), TextField.TYPE_STORED));
//					doc1.add(new Field("spId", pr.getSpId(), TextField.TYPE_STORED));
//					doc1.add(new Field("supplyId", pr.getSupplyId(), TextField.TYPE_STORED));
//					doc1.add(new Field("supplyName", pr.getSupplyName(), TextField.TYPE_STORED));
//					doc1.add(new Field("shopName", pr.getShopName(), TextField.TYPE_STORED));
//					doc1.add(new Field("priceretailonline", pr.getPriceretailonline(), TextField.TYPE_STORED));
//					doc1.add(new Field("overplusNumber", pr.getOverplusNumber(), TextField.TYPE_STORED));
//					doc1.add(new Field("hits", pr.getHits(), TextField.TYPE_STORED));
//					doc1.add(new Field("onshelftime", pr.getOnshelftime(), TextField.TYPE_STORED));
//					iwriter.addDocument(doc1);
//
//				}
//			}
//			doc.add(new Field("productId", pv.getProductId(), TextField.TYPE_STORED));
//			doc.add(new Field("productName", pv.getProductName(), TextField.TYPE_STORED));
//			doc.add(new Field("picturePath", pv.getPicturePath(), TextField.TYPE_STORED));
//			doc.add(new Field("BrandName", pv.getBrandName(), TextField.TYPE_STORED));
//			doc.add(new Field("memory", pv.getMemory(), TextField.TYPE_STORED));
//			doc.add(new Field("sysname", pv.getSysname(), TextField.TYPE_STORED));
//			iwriter.addDocument(doc);
//			System.out.println(doc.getFields());
//		}
//		iwriter.commit();
//		iwriter.close();
//		te();
//	}
//
//	public void te() throws Exception
//	{
//		updateArticleForIndex();
//		Analyzer analyzer = new org.apache.lucene.analysis.standard.StandardAnalyzer(Version.LUCENE_44);
//		Directory directory = FSDirectory.open(new File(filePath));
//		DirectoryReader ireader = DirectoryReader.open(directory);
//		IndexSearcher isearcher = new IndexSearcher(ireader);
//		String[] multiFields =
//		{ "productName" ,"supplyName","shopName"};
//		MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_44, multiFields, analyzer);
//		Query query = parser.parse("天语");
//		TopDocs docs = isearcher.search(query, null, 10);// 查找
//		ScoreDoc[] hits = docs.scoreDocs;
//		SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("", "");
//		Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));
//		highlighter.setTextFragmenter(new SimpleFragmenter(33));
//		System.out.println("Searched " + hits.length + " documents.");
//		for (int i = 0; i < hits.length; i++)
//		{
//			Document hitDoc = isearcher.doc(hits[i].doc);
//			String[] scoreExplain = isearcher.explain(query, hits[i].doc).toString().split(" ", 2);
//			String scores = scoreExplain[0];
//			System.err.println("score:" + scores);
//			String value = hitDoc.get("productName");
//			TokenStream tokenStream = analyzer.tokenStream(value, new StringReader(value));
//			String str1 = highlighter.getBestFragment(tokenStream, value);
//			System.err.println(str1);
//		}
//		ireader.close();
//		directory.close();
//
//	}
//
//	public void updateArticleForIndex() throws Exception
//	{
//		Directory directory = FSDirectory.open(new File(filePath));
//		DirectoryReader ireader = DirectoryReader.open(directory);
//		IndexSearcher searcher = new IndexSearcher(ireader);
//		Analyzer any = new StandardAnalyzer(Version.LUCENE_44);
//		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_44, any);
//		List<ProductVo> pLs = shopService.getProduct();
//
//		Document doc = new Document();
//		indexReader = IndexReader.open(directory);
//		int docLength = indexReader.numDocs();
//		System.err.println("索引里一共有" + docLength + "篇文档");
//		// int as=aList.size();
//		// if (docLength > aList.size())
//		// {
//		// // 删除
//		// int c = docLength - aList.size();
//		// for (int j = 0; j < c; j++)
//		// {
//		// Document docc = indexReader.document(++as);
//		// System.out.println(docc.get("id") + "==" + docc.get("title"));
//		// // iwriter.deleteDocuments(new Term(""));
//		// }
//		// }
//		for (int j = 0; j < pLs.size(); j++)
//		{
//			ProductVo pv = pLs.get(j);
//			Document docc = indexReader.document(j);
//			Term t = new Term("productId", pv.getProductId());// 把数据库的id到索引里查询
//			TopDocs hits = searcher.search(new TermQuery(t), 2);
//			System.out.println("==========把数据库的id到索引里查询到" + hits.totalHits + "条");
//
//		}
//		indexReader.close();
//
//		// Term t = new Term("id", "2");
//		// Query tq = new TermQuery(t);
//		// TopDocs hits = searcher.search(tq, 200);
//		// System.out.println("=========" + hits.totalHits);
//		// ScoreDoc[] scoreDocs = hits.scoreDocs;
//		// for (int i = 0; i < hit s.totalHits; i++)
//		// {
//		// Document doc = searcher.doc(scoreDocs[i].doc);
//		// System.out.println(doc.get("id"));
//		// }
//		// Document doc = new Document();
//		// doc.add(new Field("id", "2", TextField.TYPE_STORED));
//		// doc.add(new Field("title", "我是123", TextField.TYPE_STORED));
//		// doc.add(new Field("content", "1234123", TextField.TYPE_STORED));
//		// iwriter = new IndexWriter(FSDirectory.open(new File(filePath)), config);
//		// iwriter.updateDocument(t, doc);
//		// iwriter.close();
//		// System.out.println("更新成功");
//	}
//
//}
