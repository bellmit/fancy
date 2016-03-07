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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
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
public class LuceneUtil5 {
	private final static String filePath = "d:\\test1";

	private Analyzer any = new StandardAnalyzer(Version.LUCENE_44);
	private IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_44, any);

	private IndexWriter iwriter = null;
	
	private IndexReader indexReader = null;
	@Autowired
	private IShopService shopService;

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
			if (psList.size() <= 0) {
				continue;
			}
			pi.setPsLs(psList);
			pi.setPv(pv);
			piList.add(pi);
		}
		System.out.println("一共遍历了主表" + prodLs.size() + "次=========产品集合表共有：" + piList.size());
		return piList;
	}

	private void addIndex() throws IOException {
		List<ProductIndex> piLs = getProdColle();
		for (int i = 0; i < piLs.size(); i++) {
			ProductIndex pi = piLs.get(i);
			ProductVo pv = pi.getPv();
			List<ProductSub> psList = pi.getPsLs();
			for (int j = 0; j < psList.size(); j++) {
				ProductSub ps = psList.get(j);
				if (ps.getSpProductId().equals(pv.getProductId())) {
					addProductSub(ps);
					System.out.println("============" + ps.getSpProductId() + "=========" + ps.getSaId() + "====" + ps.getSaleqty());
				}
			}
			addProductVo(pv);
			System.out.println("=====" + pv.getProductId() + "========" + pv.getProductName());
		}
	}

	
	public void getProductIndex(){
		
		List<ProductVo> pLs = shopService.getProduct();
		for (int i = 0; i < pLs.size(); i++) {
			ProductVo pv=pLs.get(i);
			try {
				addProductVo(pv);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	

	/*** 添加索引 **/
	@Test
	public void testIndex() throws Exception {
		iwriter = new IndexWriter(FSDirectory.open(new File(filePath)), config);
		// iwriter.deleteAll();
		// addIndex();
		newUpdateIndex();
		// iwriter.close();
	}

	public void newUpdateIndex() {
		try {
			Directory directory = FSDirectory.open(new File(filePath));
			DirectoryReader ireader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(ireader);
			indexReader = IndexReader.open(directory);
			System.err.println("索引里所有文档:" + indexReader.maxDoc());
			System.err.println("索引里有效文档:" + indexReader.numDocs());
			System.err.println("删除文档个数：" + indexReader.numDeletedDocs());
			List<ProductIndex> piLs = getProdColle();
			int maxdoc = indexReader.maxDoc();

			for (int i = 0; i < maxdoc; i++) {
				Document doc = indexReader.document(i);
				String productid = doc.get("productId") == null ? doc.get("spProductId") : doc.get("productId");
				int count = 0;
				for (int j = 0; j < piLs.size(); j++) {
					ProductIndex pi = piLs.get(j);
					ProductVo pv = pi.getPv();
					if (pv.getProductId().toString().equals(productid)) {
						count++;
						break;
					}
				}
				if (count == 0) {
					delteIndex(productid);
				}
			}

			for (int j = 0; j < piLs.size(); j++) {
				List<ProductSub> S2 = new ArrayList<ProductSub>();
				ProductIndex pi = piLs.get(j);
				ProductVo pv = pi.getPv();
				String pId = pv.getProductId().toString();
				TopDocs td = searcher.search(new TermQuery(new Term("productId", pId)), 1);
				if (td.totalHits > 0) {
					List<ProductSub> psLs = pi.getPsLs();
					TopDocs doc = searcher.search(new TermQuery(new Term("spProductId", pId)), 1);
					doc = searcher.search(new TermQuery(new Term("spProductId", pId)), doc.totalHits == 0 ? 1 : doc.totalHits);
					ScoreDoc[] docs = doc.scoreDocs;
					for (int i = 0; i < docs.length; i++) {
						Document doc1 = searcher.doc(docs[i].doc);
						ProductSub pst = new ProductSub();
						pst.setSpProductId(new BigDecimal(doc1.get("spProductId")));
						pst.setSpId(new BigDecimal(StringHelperTools.nvl(doc1.get("spId"))));
						pst.setSaId(new BigDecimal(StringHelperTools.nvl(doc1.get("saId"))));
						pst.setSupplyId(new BigDecimal(StringHelperTools.nvl(doc1.get("supplyId"))));
						pst.setCustomerId(new BigDecimal(StringHelperTools.nvl(doc1.get("customerId"))));
						pst.setShopName(StringHelperTools.nvl(doc1.get("shopName")));
						pst.setAreaId(new BigDecimal(StringHelperTools.nvl(doc1.get("areaId"))));
						pst.setSupplyName(StringHelperTools.nvl(doc1.get("supplyName")));
						pst.setPriceretailonline(new BigDecimal(StringHelperTools.nvl(doc1.get("priceretailonline"))));
						pst.setOverplusNumber(new BigDecimal(StringHelperTools.nvl(doc1.get("overplusNumber"))));
						pst.setHits(new BigDecimal(StringHelperTools.nvl(doc1.get("hits"))));
						pst.setOnshelftime(StringHelperTools.nvl(doc1.get("onshelftime")));
						pst.setSaleqty(new BigDecimal(StringHelperTools.nvl(doc1.get("saleqty"))));
						S2.add(pst);
					}
					if (!checkHJHEqualsIndex(psLs, S2)) {
						System.out.println("=================比较索引不相等，删除索引，添加索引");
						delteIndex(pId);
						updateIndex(pi);
					} else if (!isEqualsByProduct(pv, searcher.doc(td.scoreDocs[0].doc))) {
						delteIndex(pId);
						updateIndex(pi);
					}
				} else {
					updateIndex(pi);
				}
			}
			iwriter.close();
			indexReader.close();
			indexReader = IndexReader.open(directory);
			System.err.println("索引里所有文档:" + indexReader.maxDoc());
			System.err.println("索引里有效文档:" + indexReader.numDocs());
			System.err.println("删除文档个数：" + indexReader.numDeletedDocs());
			indexReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**** 更新索引--- ***/
	public void updateIndex(ProductIndex pi) throws IOException {
		List<ProductSub> psLs = pi.getPsLs();
		ProductVo pv = pi.getPv();
		for (int i = 0; i < psLs.size(); i++) {
			ProductSub ps = psLs.get(i);
			if (ps.getSpProductId().equals(pv.getProductId())) {
				addProductSub(ps);
				System.out.println("================更新索引====子表主表" + pv.getProductId());
			}
		}
		addProductVo(pv);
		System.out.println("================更新索引====添加主表" + pv.getProductId());
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

	public void delteIndex(String pId) throws Exception {
		iwriter.deleteDocuments(new Term("productId", pId));
		iwriter.deleteDocuments(new Term("spProductId", pId));
		iwriter.commit();
		System.out.println("=========================删除产品索引" + pId);
	}

	/**
	 * 
	 * @param
	 * @return
	 * @author caosheng
	 * @version V1.0
	 */
	private void addProductVo(ProductVo pv) throws IOException {
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
	private void addProductSub(ProductSub ps) throws IOException {
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
