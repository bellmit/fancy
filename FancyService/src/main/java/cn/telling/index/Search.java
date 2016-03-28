package cn.telling.index;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Component;

import cn.telling.common.vo.PageVo;
import cn.telling.product.dao.IProductDao;
import cn.telling.product.vo.AttrTypeVo;
import cn.telling.product.vo.ControlVo;
import cn.telling.product.vo.ProductAttrVo;
import cn.telling.product.vo.ProductDetailVo;
import cn.telling.product.vo.ProductRetnVo;
import cn.telling.product.vo.ProductSortVo;
import cn.telling.shop.dao.IShopSearchDao;
import cn.telling.shop.vo.ShopInfoVo;
import cn.telling.shop.vo.ShopProductVo;

import com.chenlb.mmseg4j.analysis.ComplexAnalyzer;

/**
 * 搜索工具包
 * @ClassName: splitWord
 * 
 * @author xingle
 * @date 2015-7-28 上午10:11:33
 */
@Component
public class Search {
//	private static Logger logger = Logger.getLogger(Search.class);
	
	private static Analyzer analyzer = new ComplexAnalyzer("D:\\mmseg4j\\data");
	private IndexReader indexReader = null;
	private IndexReader indexShopReader = null;
	private IndexSearcher searcher = null;
	private IndexSearcher shopsearcher = null;
	
	@Resource
	private IProductDao productDao;
	
	@Resource
	private IShopSearchDao shopSearchDao;
	
	@Resource
	Util util;
	
	public synchronized IndexReader getIndexReader(){		
			try {
				if("1".equals(Util.lastUpdadeFlg) || "".equals(Util.lastUpdadeFlg)){
					indexReader = DirectoryReader.open(FSDirectory.open(util.getIndexFile1()));
					System.out.println("获取 产品索引文件1  的 indexReader");
				}else if("2".equals(Util.lastUpdadeFlg)){
					indexReader = DirectoryReader.open(FSDirectory.open(util.getIndexFile2()));
					System.out.println("获取 产品索引文件 2  的 indexReader");
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		return indexReader;
	}
	
	public synchronized IndexReader getIndexShopReader(){		
		try {
			if("1".equals(Util.lastUpdadeShopFlg) || "".equals(Util.lastUpdadeShopFlg)){
				indexShopReader = DirectoryReader.open(FSDirectory.open(util.getIndexFile3()));
				System.out.println("获取 店铺索引文件1  的 indexReader");
			}else if("2".equals(Util.lastUpdadeShopFlg)){
				indexShopReader = DirectoryReader.open(FSDirectory.open(util.getIndexFile4()));
				System.out.println("获取 店铺索引文件 2  的 indexReader");
			}				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	return indexShopReader;
}
	
	private List<String> splitWordBymmseg4j2(String text){
		List<String> ls = new ArrayList<String>();
		System.out.println();
		System.out.println("【"+text+"】"+"Mmseg4j拆分的结果是：");
		try {			
			TokenStream ts = analyzer.tokenStream("", new StringReader(text));
			ts.reset();
			while(ts.incrementToken()){  
	            CharTermAttribute ta = ts.getAttribute(CharTermAttribute.class);  
	            System.out.print("【" + ta.toString() + "】"); 
	            ls.add(ta.toString());
	         }

			ts.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
		return ls;
	}
	
	/**
	 * TODO
	 * @param inputTxt
	 * @param page
	 * @param areaid
	 * @param specificationidLs
	 * @param userid
	 * @param attrLs
	 * @param minPrice
	 * @param maxPrice
	 * @param sortName
	 * @param rand
	 * @param isup
	 * @return
	 * @author xingle
	 * @data 2015-8-13 下午5:20:45
	 */
	public ProductRetnVo searchProduct1(String inputTxt, 
			PageVo page, String areaid, List<ProductAttrVo> specificationidLs,
			BigDecimal userid, List<AttrTypeVo> attrLs, BigDecimal minPrice,
			BigDecimal maxPrice, String sortName, String rand, String isup,ControlVo isControl) {
		long start = System.currentTimeMillis();
		long end1 = System.currentTimeMillis();
		ProductRetnVo retn = new ProductRetnVo();
		indexReader = getIndexReader();
		searcher = new IndexSearcher(indexReader);
		try {
			BooleanQuery booleanQuery = new BooleanQuery();
			if ((null!= attrLs && attrLs.size() > 0) || (null!= specificationidLs && specificationidLs.size()> 0)) {
				// 精确查找
				int m = 0;
				if(null!= attrLs && attrLs.size() > 0){
					m = attrLs.size();
				}
				if(null!= specificationidLs && specificationidLs.size()> 0){
					m = m+1;
				}				
				String[] s3 = new String[m];
				String[] f3 = new String[m];
				Occur[] b3 = new Occur[m];
				if(null!=attrLs && attrLs.size()>0){
					for (int i = 0; i < attrLs.size(); i++) {
						String type = attrLs.get(i).getType();
						String value = attrLs.get(i).getValue();
						s3[i] = value;
						f3[i] = "";
						if ("1".equals(type)) {
							f3[i] = "brand";
							b3[i] = Occur.SHOULD;
						} else if ("2".equals(type)) {
							f3[i] = "productpattern";
							b3[i] = Occur.MUST;
						} else if ("3".equals(type)) {
							f3[i] = "color";
							b3[i] = Occur.MUST;
						}						
					}
				}
				
				if (null!= specificationidLs && specificationidLs.size() > 0) {
					String parametervalue = "";
					for (int j = 0; j < specificationidLs.size(); j++) {
						parametervalue = parametervalue
								+ specificationidLs.get(j).getParametervalue()
								+ " ";
					}
					s3[m - 1] = parametervalue;
					f3[m - 1] = "productAttribute";
					b3[m - 1] = Occur.MUST;					
				}
				Query query = MultiFieldQueryParser.parse(s3, f3, b3, analyzer);
				booleanQuery.add(query, Occur.MUST);

			} else {
				// 搜索框输入查找
				String[] fields = new String[] { "productName", "productpattern" };
				List<String> splitLs2 = this.splitWordBymmseg4j2(inputTxt);
				for (int i = 0; i < splitLs2.size(); i++) {
					MultiFieldQueryParser queryParser = new MultiFieldQueryParser(
							fields, analyzer);
					String value = splitLs2.get(i);
					queryParser.setAllowLeadingWildcard(true);
					Query query = queryParser.parse("*" + value + "*");
					booleanQuery.add(query, Occur.MUST);
				}
				fields = new String[] { "brand" };
				for (int i = 0; i < splitLs2.size(); i++) {
					MultiFieldQueryParser queryParser = new MultiFieldQueryParser(
							fields, analyzer);
					String value = splitLs2.get(i);
					queryParser.setAllowLeadingWildcard(true);
					Query query = queryParser.parse(value);
					booleanQuery.add(query, Occur.SHOULD);
				}
			}
			if(null == inputTxt || "".equals(inputTxt)){
				minPrice = new BigDecimal(0.001);
			}
			if (null != minPrice && minPrice.compareTo(new BigDecimal(0)) != 0) {
				NumericRangeQuery<Long> priceMinQuery = NumericRangeQuery
						.newLongRange("minprice", minPrice.longValue(), null,
								true, true);
				booleanQuery.add(priceMinQuery, Occur.MUST);
			}
			if (null != maxPrice && maxPrice.compareTo(new BigDecimal(0)) != 0) {
				NumericRangeQuery<Long> priceMaxQuery = NumericRangeQuery
						.newLongRange("maxprice", null, maxPrice.longValue(),
								true, true);
				booleanQuery.add(priceMaxQuery, Occur.MUST);
			}
			if (null != sortName && !"".equals(sortName)) {
				Query query = new TermQuery(new Term("category", sortName));
				booleanQuery.add(query, Occur.MUST);
			}
			
			// int docLength = indexReader.numDocs();
			TopDocs topDocs = searcher.search(booleanQuery, 10, Sort.RELEVANCE);
			int totalCount = topDocs.totalHits; // 搜索结果总数量
			
			if (totalCount > 0) {
				List<String> pls = new ArrayList<String>();
				// 全部搜索到的产品
				topDocs = searcher.search(booleanQuery, totalCount,
						Sort.RELEVANCE);
				end1 = System.currentTimeMillis();
				ScoreDoc[] scoreDocs = topDocs.scoreDocs;

				for (int i = 0; i < scoreDocs.length; i++) {
					int doc = scoreDocs[i].doc;
					Document document = searcher.doc(doc);
					pls.add(document.get("productId"));
				}
				// 过滤区域,指定客户id 等后有效产品id列表
				retn = getFilterProductLs(pls, areaid, userid, page, rand, isup,isControl);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end2 = System.currentTimeMillis();     
		System.out.println("第一次索引查询用时间："+(end1-start)+"毫秒");
		System.out.println("返回分页结果查询后一共用时间："+(end2-start)+"毫秒");
		
//		System.out.println();
//		System.out.println();
//		if(null!=retn && retn.getProLs().size()>0){
//			for (int i = 0; i < retn.getProLs().size(); i++) {
//				ProductDetailVo vo = retn.getProLs().get(i);
//				System.out.println(vo.getHjhtype() + "     **" + vo.getProductid()
//						+ "      **" + vo.getProductname() + "      **" + vo.getBrandname()
//						+ "     **" + vo.getColor() + "     **" + vo.getProductpattern()
//						+ "     **" + vo.getSupplyname() + "     **said:" + vo.getSaId()
//						+ "     **spid:" + vo.getSpId() + "      **库存："
//						+ vo.getOverplusnumber() + "     **价格："
//						+ vo.getPriceretailonline() + "      **销量：" + vo.getSalenum()
//						+ "     **上架时间：" + vo.getOnshelftime() + "     **关注："
//						+ vo.getHits());
//			}
//		}
//		
//		System.out.println();
//		System.out.println();
		return retn;
	}

	/**
	 * 过滤有效的产品
	 * @param pls
	 * @param areaid
	 * 	区域限制
	 * @param userid
	 * 	执行客户userid
	 * @return
	 * @author xingle
	 * @data 2015-8-11 上午11:28:13
	 */
		ProductRetnVo getFilterProductLs(List<String> pls,
			String areaid,BigDecimal userid,PageVo page,String rand,String isup,ControlVo isControl) {
		List<String> areaLs = new ArrayList<String>();
		if(null!=areaid&&!"".equals(areaid)){
			areaLs = productDao.getAreaLs(new BigDecimal(areaid));
		}
		ProductRetnVo retn = new ProductRetnVo();
		List<ProductSortVo> sortLs = productDao.getProductSort(pls,areaLs,userid,isControl);
		// 按类目id升序
		if(null!=sortLs && sortLs.size()>0){
			Collections.sort(sortLs, new Comparator<ProductSortVo>() {
				@Override
				public int compare(ProductSortVo arg0, ProductSortVo arg1) {
					return arg0.getSortId().compareTo(arg1.getSortId());
				}
			});
		}		
		List<ProductDetailVo> proLs =productDao.getFilterProductLs(pls,areaLs,userid,page,rand,isup,isControl);
		retn.setProLs(proLs);
		retn.setSortLs(sortLs);
		int total = 0;
		if(null!=sortLs && sortLs.size()>0){
			for(int i = 0;i<sortLs.size();i++){
				total += sortLs.get(i).getNum().intValue();
			}
		}		
		retn.setTotal(total);
		return retn;
	}

	/**
	 * TODO
	 * @param searchShop
	 * @param page
	 * @param areaIdLs
	 * @param areaid
	 * @param shopType
	 * @param goodRate
	 * @param isBuy
	 * @param userid
	 * @return
	 * @author xingle
	 * @data 2015-8-25 下午4:49:30
	 */
	public List<ShopInfoVo> searchShop(String searchShop, PageVo page,
			List<String> areaIdLs, String areaid,String shopType, BigDecimal goodRate,
			boolean isBuy,BigDecimal userid,String rand,String isup) {
	
		
		indexReader = getIndexShopReader();
		shopsearcher = new IndexSearcher(indexReader);
		
		List<ShopInfoVo> ls = new ArrayList<ShopInfoVo>();
		String[] fields = new String[] { "shopname","shopIntro" };
		MultiFieldQueryParser queryParser = new MultiFieldQueryParser(fields, analyzer);
		BooleanQuery booleanQuery = new BooleanQuery();
		try {			
			if(null!=searchShop && !"".equals(searchShop)){
				Query query = queryParser.parse(searchShop);
				booleanQuery.add(query, Occur.MUST);					
				if(!"".equals(shopType)){
					NumericRangeQuery<Long> leverQuery = null;
					if("1".equals(shopType)){//金冠店
						leverQuery = NumericRangeQuery.newLongRange("shoplever",
								new BigDecimal(500001).longValue(), null, true,true);					
					}
					else if("2".equals(shopType)){//皇冠店
						leverQuery = NumericRangeQuery.newLongRange("shoplever",
								new BigDecimal(10001).longValue(), new BigDecimal(500001).longValue(), true,false);	
					}
					else if("3".equals(shopType)){//钻级店
						leverQuery = NumericRangeQuery.newLongRange("shoplever",
								new BigDecimal(251).longValue(), new BigDecimal(10001).longValue(), true,false);	
					}
					else if("4".equals(shopType)){//心级店
						leverQuery = NumericRangeQuery.newLongRange("shoplever",
								null, new BigDecimal(251).longValue(), true,false);	
					}
					booleanQuery.add(leverQuery, Occur.MUST);
				}			
			}
			else{
				goodRate = new BigDecimal(0);
			}
			
			if(null!=goodRate){
				NumericRangeQuery<Long> goodRateQuery = NumericRangeQuery.newLongRange("goodrate",
						goodRate.longValue(), null, true,true);		
				booleanQuery.add(goodRateQuery, Occur.MUST);
			}
			@SuppressWarnings("unused")
            List<String> splitLs2 = this.splitWordBymmseg4j2(searchShop);
			TopDocs topDocs = shopsearcher.search(booleanQuery, 10, Sort.RELEVANCE);
			int totalCount = topDocs.totalHits; // 搜索结果总数量
			if(totalCount>0){
				List<String> shopls = new ArrayList<String>();
				topDocs = shopsearcher.search(booleanQuery, totalCount,
						Sort.RELEVANCE);
				@SuppressWarnings("unused")
                long end1 = System.currentTimeMillis();
				ScoreDoc[] scoreDocs = topDocs.scoreDocs;
				for (int i = 0; i < scoreDocs.length; i++) {
					int doc = scoreDocs[i].doc;
					Document document = shopsearcher.doc(doc);
					shopls.add(document.get("shopid"));
				}
//				System.out.println("搜索店铺一共用时间："+(end1-start)+"毫秒");
				System.out.println("搜索店铺索引（未过滤前）一共："+shopls.size()+"个");
				ls = getFilterShop(shopls,page,areaid,isBuy,userid, rand, isup,areaIdLs);
				@SuppressWarnings("unused")
                long end2 = System.currentTimeMillis();
//				System.out.println("过滤后店铺一共用时间："+(end2-start)+"毫秒");
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
		
		if(null!=ls && ls.size()>0){
			for (int i = 0; i < ls.size(); i++) {
				ShopInfoVo shop = ls.get(i);
				@SuppressWarnings("unused")
                String goodrate = "";
				Query query1 = new TermQuery(new Term("shopid", shop.getShopid()));
				try {
					TopDocs d = shopsearcher.search(query1, 10);
					int n = d.scoreDocs[0].doc;
					Document doc = shopsearcher.doc(n);
					goodrate = doc.get("goodrate");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
//		for (int i = 0; i < ls.size(); i++) {
//			ShopInfoVo shop = ls.get(i);
//			String goodrate = "";
//			Query query1 = new TermQuery(new Term("shopid", shop.getShopid()));
//			try {
//				TopDocs d = shopsearcher.search(query1, 10);
//				int n = d.scoreDocs[0].doc;
//				Document doc = shopsearcher.doc(n);
//				goodrate = doc.get("goodrate");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			System.out.println("--------------");
//			System.out.println("shopid:" + shop.getShopid() + "  ***店铺名称： "
//					+ shop.getShopname() + "    **店铺所在地id:"
//					+ shop.getSupplyAreaId() + "   **供应商id:" + shop.getSupplyid()
//					+ "    **销量：" + shop.getSalenum() + " **好评率"
//					+ goodrate+"   **店铺等级："+shop.getShoplever());
//		}
		return ls;
	}


	/**
	 * TODO
	 * @param shopls
	 * @param page
	 * @param areaid
	 * @param isBuy
	 * @param userid
	 * @param rand
	 * @param isup
	 * @param areaIdLs
	 * @return
	 * @author xingle
	 * @data 2015-8-26 下午6:35:55
	 */
	private List<ShopInfoVo> getFilterShop(List<String> shopls, PageVo page,
			String areaid, boolean isBuy,BigDecimal userid,String rand,String isup,List<String> areaIdLs) {
		long start1 = System.currentTimeMillis();
		List<ShopInfoVo> shopLs = shopSearchDao.getShopInfoLs(shopls,page,isBuy,userid,areaid,rand,isup,areaIdLs);
		long end1 = System.currentTimeMillis();
		System.out.println("过滤所有店铺，用时："+(end1-start1)+"ms");
		
		long start2 = System.currentTimeMillis();
		List<ShopProductVo> proLs = new ArrayList<ShopProductVo>();
		if(shopLs.size()>0){
			proLs = shopSearchDao.getShopProductLs(shopLs);	
		}		
		long end2 = System.currentTimeMillis();
		System.out.println("查询店铺所有产品，用时："+(end2-start2)+"ms");
		
		long start3 = System.currentTimeMillis();
		for(int i =0;i<shopLs.size();i++){
			List<ShopProductVo> shopProLs = new ArrayList<ShopProductVo>();
			String id = shopLs.get(i).getShopid();
			for(int j = 0;j<proLs.size();j++){
				if(id.equals(proLs.get(j).getShopid())){
					shopProLs.add(proLs.get(j));
				}
			}
			shopLs.get(i).setShopProLs(shopProLs);
		}
		long end3 = System.currentTimeMillis();
		System.out.println("封装店铺以及店铺产品，用时："+(end3-start3)+"ms");
		return shopLs;
	}
	
	
}
