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
import java.io.StringReader;
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
import cn.telling.shop.vo.Article;
import cn.telling.utils.StringHelperTools;


@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@SuppressWarnings("deprecation")
public class LuceneUtil {
	private final static String filePath = "d:\\test1";

	private IndexWriter iwriter = null;
	private IndexReader indexReader = null;

	@Autowired
	private IShopService shopService;

	@Test
	public void test() throws Exception
	{
		IndexWriter.unlock(FSDirectory.open(new File(filePath)));
		Analyzer any = new StandardAnalyzer(Version.LUCENE_44);
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_44, any);
		config.setOpenMode(OpenMode.CREATE);
		iwriter = new IndexWriter(FSDirectory.open(new File(filePath)), config);
//		List<Article> sList = shopService.getShops();
//		for (Article si : sList)
//		{
//			Document document = new Document();
//			document.add(new Field("id", si.getId(), TextField.TYPE_STORED));
//			document.add(new Field("title", si.getTitle(), TextField.TYPE_STORED));
//			document.add(new Field("content", StringHelperTools.nvl(si.getContent()), TextField.TYPE_STORED));
//			document.add(new Field("del_flag", StringHelperTools.nvl(si.getDel_flag()), TextField.TYPE_STORED));
//			iwriter.addDocument(document);
//			iwriter.commit();
//		}
//		iwriter.close();
		 te();
		update();
	}

	public void update() throws Exception
	{
		Directory directory = FSDirectory.open(new File(filePath));
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(ireader);
		Analyzer any = new StandardAnalyzer(Version.LUCENE_44);
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_44, any);
		List<Article> aList = shopService.getShops();

		indexReader = IndexReader.open(directory);
		int docLength = indexReader.numDocs();
		TopDocs td = searcher.search(new TermQuery(new Term("id", "2")), 2);
		ScoreDoc[] c = td.scoreDocs;
		System.err.println(c[0].doc);
		Document doc = searcher.doc(c[0].doc);
		System.out.println(doc.getFields());
		
		Document ddd=indexReader.document(1);
		System.out.println(ddd.getFields());
	}

	public void te() throws Exception
	{
		updateArticleForIndex();
		Analyzer analyzer = new org.apache.lucene.analysis.standard.StandardAnalyzer(Version.LUCENE_44);
		Directory directory = FSDirectory.open(new File(filePath));
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);
		String[] multiFields =
		{ "title", "content" };
		MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_44, multiFields, analyzer);
		Query query = parser.parse("我是");
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
			String value = hitDoc.get("title");
			TokenStream tokenStream = analyzer.tokenStream(value, new StringReader(value));
			String str1 = highlighter.getBestFragment(tokenStream, value);
			System.err.println(str1);
		}
		ireader.close();
		directory.close();

	}

	public void updateArticleForIndex() throws Exception
	{
		Directory directory = FSDirectory.open(new File(filePath));
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(ireader);
		Analyzer any = new StandardAnalyzer(Version.LUCENE_44);
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_44, any);
		List<Article> aList = shopService.getShops();

		Document doc = new Document();
		indexReader = IndexReader.open(directory);
		int docLength = indexReader.numDocs();
		System.err.println("索引里一共有" + docLength + "篇文档");

		// int as=aList.size();
		// if (docLength > aList.size())
		// {
		// // 删除
		// int c = docLength - aList.size();
		// for (int j = 0; j < c; j++)
		// {
		// Document docc = indexReader.document(++as);
		// System.out.println(docc.get("id") + "==" + docc.get("title"));
		// // iwriter.deleteDocuments(new Term(""));
		// }
		// }
		for (int j = 0; j < aList.size(); j++)
		{
			Article article = aList.get(j);
			Document docc = indexReader.document(j);
			Term t = new Term("id", article.getId());// 把数据库的id到索引里查询
			TopDocs hits = searcher.search(new TermQuery(t), 3);
			System.out.println("==========把数据库的id到索引里查询到" + hits.totalHits + "条");
			if (hits.totalHits > 0)
			{
				if (!StringHelperTools.nvl(docc.get("title")).equals(article.getTitle()))
				{
					doc.add(new Field("id", docc.get("id"), TextField.TYPE_STORED));
					doc.add(new Field("title", article.getTitle(), TextField.TYPE_STORED));
					doc.add(new Field("content", article.getContent(), TextField.TYPE_STORED));
					doc.add(new Field("del_flag", StringHelperTools.nvl(article.getDel_flag()), TextField.TYPE_STORED));
//					iwriter = new IndexWriter(FSDirectory.open(new File(filePath)), config);
					iwriter.updateDocument(new Term("id", docc.get("id")), doc);
					iwriter.commit();
					System.out.println("更新成功,内容为：" + doc.get("title"));
				}
				if (StringHelperTools.nvl(article.getDel_flag()).equals("0"))
				{
					iwriter = new IndexWriter(FSDirectory.open(new File(filePath)), config);
					iwriter.deleteDocuments(new Term("id", article.getId()));
					System.out.println("删除索引，id是:" + article.getId());
					iwriter.commit();
					iwriter.close();
				}
			} else
			{
				System.out.println("================添加");
				doc.add(new Field("id", article.getId(), TextField.TYPE_STORED));
				doc.add(new Field("title", article.getTitle(), TextField.TYPE_STORED));
				doc.add(new Field("content", article.getContent(), TextField.TYPE_STORED));
//				iwriter = new IndexWriter(FSDirectory.open(new File(filePath)), config);
				iwriter.addDocument(doc);
				iwriter.commit();
				iwriter.close();
				System.out.println("添加成功,内容为：" + doc.get("title"));
			}
		}
		indexReader.close();

		// Term t = new Term("id", "2");
		// Query tq = new TermQuery(t);
		// TopDocs hits = searcher.search(tq, 200);
		// System.out.println("=========" + hits.totalHits);
		// ScoreDoc[] scoreDocs = hits.scoreDocs;
		// for (int i = 0; i < hit s.totalHits; i++)
		// {
		// Document doc = searcher.doc(scoreDocs[i].doc);
		// System.out.println(doc.get("id"));
		// }
		// Document doc = new Document();
		// doc.add(new Field("id", "2", TextField.TYPE_STORED));
		// doc.add(new Field("title", "我是123", TextField.TYPE_STORED));
		// doc.add(new Field("content", "1234123", TextField.TYPE_STORED));
		// iwriter = new IndexWriter(FSDirectory.open(new File(filePath)), config);
		// iwriter.updateDocument(t, doc);
		// iwriter.close();
		// System.out.println("更新成功");
		
		
	}

}
