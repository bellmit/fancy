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

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.telling.shop.service.IShopService;

@ContextConfiguration(locations = "classpath:spring-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@SuppressWarnings("deprecation")
public class LuceneUtilTest {
	private final static String filePath = "d:\\test2";

	private IndexWriter iwriter = null;
	private IndexReader indexReader = null;
	@Autowired
	private IShopService shopService;

	@Test
	public void test() throws Exception {

		Analyzer any = new StandardAnalyzer(Version.LUCENE_44);
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_44, any);
		Directory directory = FSDirectory.open(new File(filePath));
		config.setOpenMode(OpenMode.CREATE);
		iwriter = new IndexWriter(FSDirectory.open(new File(filePath)), config);
		iwriter.deleteAll();

		Document doc = new Document();
		doc.add(new Field("name", "java", TextField.TYPE_STORED));
		doc.add(new Field("title", "doc", TextField.TYPE_STORED));
		iwriter.addDocument(doc);
		iwriter.commit();
		indexReader = IndexReader.open(directory);
		int docLength = indexReader.numDocs();
		System.out.println("****************************************"
				+ docLength);
		iwriter.deleteDocuments(new TermQuery(new Term("name", "java")));
		iwriter.commit();
		int c = indexReader.numDocs();
		System.out.println("****************************************" + c);
		for (int i = 0; i < docLength; i++) {
			Document dd = indexReader.document(i);
			System.out.println("****************************************");
			System.out.println(dd.get("name"));
		}

	}

}
