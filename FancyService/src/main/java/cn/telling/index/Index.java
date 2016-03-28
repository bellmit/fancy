package cn.telling.index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

import cn.telling.product.dao.IProductDao;
import cn.telling.product.vo.ProductInfoVo;
import cn.telling.shop.vo.ShopInfoVo;
import cn.telling.utils.StringHelperTools;

import com.chenlb.mmseg4j.analysis.ComplexAnalyzer;

/**
 * 
 * @ClassName: Index
 *
 * @author xingle
 * @date 2015-7-30 下午6:07:47
 */
public class Index {
	private static Logger logger = Logger.getLogger(Index.class);
	private Analyzer analyzer = new ComplexAnalyzer("D:\\mmseg4j\\data");
	private Directory directory = null;
	private static IndexWriter indexWriter = null;	//产品索引
	private static IndexWriter indexWriter2 = null;	//产品索引
	private static IndexWriter indexWriter3 = null; //店铺索引
	private static IndexWriter indexWriter4 = null;	//店铺索引
	
	@Resource
	private IProductDao productDao;
	
	@Resource
	Util util;
	
	public synchronized IndexWriter getIndexWriter(){
		if(null==indexWriter){
			System.out.println("获取对象IndexWriter1");
			try {
				if(util.getIndexFile1().exists()){
					directory = FSDirectory.open(util.getIndexFile1()); 								
				}
				else {
					directory = new SimpleFSDirectory(util.getIndexFile1());					
				}
				indexWriter = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_4_10_1, analyzer));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return indexWriter;
	}
	
	public synchronized IndexWriter getIndexWriter2(){
		if(null==indexWriter2){
			System.out.println("获取对象IndexWriter2");
			try {
				if(util.getIndexFile2().exists()){
					directory = FSDirectory.open(util.getIndexFile2()); 					
				}
				else{
					directory = new SimpleFSDirectory(util.getIndexFile2());
				}
				indexWriter2 = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_4_10_1, analyzer));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return indexWriter2;
	}
	
	public synchronized IndexWriter getIndexShopWriter(){
		if(null==indexWriter3){
			System.out.println("获取对象IndexWriter3");
			try {
				if(util.getIndexFile3().exists()){
					directory = FSDirectory.open(util.getIndexFile3()); 					
				}
				else{
					directory = new SimpleFSDirectory(util.getIndexFile3());
				}
				indexWriter3 = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_4_10_1, analyzer));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return indexWriter3;
	}
	
	public synchronized IndexWriter getIndexShopWriter2(){
		if(null==indexWriter4){
			System.out.println("获取对象IndexWriter4");
			try {
				if(util.getIndexFile4().exists()){
					directory = FSDirectory.open(util.getIndexFile4()); 					
				}
				else{
					directory = new SimpleFSDirectory(util.getIndexFile4());
				}
				indexWriter4 = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_4_10_1, analyzer));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return indexWriter4;
	}

	/**
	 * TODO
	 * @return
	 * @author xingle
	 * @data 2015-8-7 上午11:08:10
	 */
	public boolean initailProduct(){
		boolean flag = false;
		if("".equals(Util.lastUpdadeFlg)){
			flag = this.initialProductIndex1();	
			if(flag){
				Util.lastUpdadeFlg = "1";
				return flag;	
			}						
		}
		else if("1".equals(Util.lastUpdadeFlg)){
			flag = this.initialProductIndex2();
			if(flag){
				Util.lastUpdadeFlg = "2";
				return flag;
			}			
		}else if("2".equals(Util.lastUpdadeFlg)){
			flag = this.initialProductIndex1();
			if(flag){
				Util.lastUpdadeFlg = "1";
				return flag;
			}			
		}
		return flag;
	}
	
	/**
	 * TODO
	 * @return
	 * @author xingle
	 * @data 2015-8-24 上午11:04:20
	 */
	public boolean initailShop(){
		boolean flag = false;
		if("".equals(Util.lastUpdadeShopFlg)){
			flag = this.initialShopIndex1();	
			if(flag){
				Util.lastUpdadeShopFlg = "1";
				return flag;	
			}						
		}
		else if("1".equals(Util.lastUpdadeShopFlg)){
			flag = this.initialShopIndex2();
			if(flag){
				Util.lastUpdadeShopFlg = "2";
				return flag;
			}			
		}else if("2".equals(Util.lastUpdadeShopFlg)){
			flag = this.initialShopIndex1();
			if(flag){
				Util.lastUpdadeShopFlg = "1";
				return flag;
			}			
		}
		return flag;
	}
	
	public boolean updateProductSaleNum(){
		productDao.updateProductSaleNum();
		productDao.updatesSupplyProductSaleNum();
		return true;
	}

	/**
	 * TODO
	 * @return
	 * @author xingle
	 * @data 2015-8-24 上午11:05:07
	 */
	private boolean initialShopIndex1() {
		List<ShopInfoVo> ls = getShopIndexLs();
		logger.debug("*****************初始化店铺索引 1 开始 *********************");
		indexWriter3 = this.getIndexShopWriter();
		try {
			indexWriter3.deleteAll();
			for(int i = 0;i<ls.size();i++){
				Document doc = new Document();
				ShopInfoVo shopVo = ls.get(i);
				doc.add(new StringField("shopid",shopVo.getShopid(),Store.YES));
				doc.add(new TextField("shopname", shopVo.getShopname(),Store.YES));
				doc.add(new StringField("supplyid",shopVo.getSupplyid().toString(),Store.YES));
				doc.add(new StringField("selleruserid",shopVo.getSelleruserid().toString(),Store.YES));
				doc.add(new TextField("supplyAreaId",shopVo.getSupplyAreaId().toString(),Store.YES));
				doc.add(new StringField("shoplogo",StringHelperTools.nvl(shopVo.getShoplogo()),Store.YES));
				doc.add(new TextField("shopIntro",StringHelperTools.nvl(shopVo.getShopIntro()),Store.YES));
				doc.add(new LongField("goodrate",shopVo.getGoodrate().longValue(),Store.YES));
				doc.add(new LongField("shoplever",shopVo.getShoplever().longValue(),Store.YES));
				indexWriter3.addDocument(doc);	
			}
			indexWriter3.commit();
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		logger.debug("*****************初始化店铺索引 1 结束 **********,一共"+ls.size()+"条");
		return true;
	}


	/**
	 * TODO
	 * @return
	 * @author xingle
	 * @data 2015-8-24 上午11:06:05
	 */
	private boolean initialShopIndex2() {
		List<ShopInfoVo> ls = getShopIndexLs();
		logger.debug("*****************初始化店铺索引 2开始 *********************");
		indexWriter4 = this.getIndexShopWriter2();
		try {
			indexWriter4.deleteAll();
			for(int i = 0;i<ls.size();i++){
				Document doc = new Document();
				ShopInfoVo shopVo = ls.get(i);
				doc.add(new StringField("shopid",shopVo.getShopid(),Store.YES));
				doc.add(new TextField("shopname", shopVo.getShopname(),Store.YES));
				doc.add(new StringField("supplyid",shopVo.getSupplyid().toString(),Store.YES));
				doc.add(new StringField("selleruserid",shopVo.getSelleruserid().toString(),Store.YES));
				doc.add(new TextField("supplyAreaId",shopVo.getSupplyAreaId().toString(),Store.YES));
				doc.add(new StringField("shoplogo",StringHelperTools.nvl(shopVo.getShoplogo()),Store.YES));
				doc.add(new TextField("shopIntro",StringHelperTools.nvl(shopVo.getShopIntro()),Store.YES));
				doc.add(new LongField("goodrate",shopVo.getGoodrate().longValue(),Store.YES));
				doc.add(new LongField("shoplever",shopVo.getShoplever().longValue(),Store.YES));
				indexWriter4.addDocument(doc);	
			}
			indexWriter4.commit();
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		logger.debug("*****************初始化店铺索引 2 结束 **********,一共"+ls.size()+"条");
		return true;
	}
	
	/**
	 * TODO
	 * @return
	 * @author xingle
	 * @data 2015-8-24 上午11:19:09
	 */
	private List<ShopInfoVo> getShopIndexLs() {		
		return productDao.getShopIndexLs();
	}


	/**
	 * TODO
	 * @return
	 * @author xingle
	 * @data 2015-8-6 上午9:20:07
	 */
	public boolean initialProductIndex1(){
		List<ProductInfoVo> ls = getProductIndexLs1();
		logger.debug("*****************初始产品索引 1 开始 *********************");
		indexWriter = this.getIndexWriter();
		try {
			indexWriter.deleteAll();
			for(int i =0;i<ls.size();i++){
				Document doc = new Document();
				ProductInfoVo MainVo = ls.get(i);
				doc.add(new StringField("productId",MainVo.getProductId().toString(),Store.YES));
				TextField productName= new TextField("productName", StringHelperTools.nvl(MainVo.getProductName()), Store.YES);
				//productName.setBoost((float) 2.0);
				doc.add(productName);
				doc.add(new StringField("picturepath1", StringHelperTools.nvl(MainVo.getPicturepath1()),Store.YES));
				TextField brand= new TextField("brand", StringHelperTools.nvl(MainVo.getBrand()),Store.YES);
				brand.setBoost((float) 1.5);
				doc.add(brand);				
				TextField productpattern= new TextField("productpattern", StringHelperTools.nvl(MainVo.getProductpattern()),Store.YES);
				productpattern.setBoost((float) 1.5);
				doc.add(productpattern);
				doc.add(new TextField("color", StringHelperTools.nvl(MainVo.getColor()),Store.YES));
				TextField color= new TextField("color", StringHelperTools.nvl(MainVo.getColor()),Store.YES);
				color.setBoost((float) 0.7);
				doc.add(color);
				
				doc.add(new StringField("category", StringHelperTools.nvl(MainVo.getCategory()),Store.YES));
				doc.add(new TextField("productAttribute", StringHelperTools.nvl(MainVo.getProductAttribute()),Store.YES));				
				doc.add(new StringField("saletype", StringHelperTools.nvl(MainVo.getSaletype()),Store.YES));
				doc.add(new LongField("minprice", MainVo.getMinprice().longValue(),Store.YES));
				doc.add(new LongField("maxprice", MainVo.getMaxprice().longValue(),Store.YES));
				indexWriter.addDocument(doc);	
			}
			indexWriter.commit();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		logger.debug("*****************初始化产品索引 1 结束 **********,一共"+ls.size()+"条主产品");
		return true;
	}
	
	/**
	 * TODO
	 * @return
	 * @author xingle
	 * @data 2015-8-7 下午1:23:48
	 */
	public boolean initialProductIndex2(){
		List<ProductInfoVo> ls = getProductIndexLs1();
		logger.debug("*****************初始化产品索引 2 开始 *********************");	
		
		indexWriter2 = this.getIndexWriter2();
		try {
			indexWriter2.deleteAll();
			for(int i =0;i<ls.size();i++){
				Document doc = new Document();
				ProductInfoVo MainVo = ls.get(i);
				doc.add(new StringField("productId",MainVo.getProductId().toString(),Store.YES));
				TextField productName= new TextField("productName", StringHelperTools.nvl(MainVo.getProductName()), Store.YES);
				//productName.setBoost((float) 2.0);
				doc.add(productName);
				
				doc.add(new StringField("picturepath1", StringHelperTools.nvl(MainVo.getPicturepath1()),Store.YES));
				TextField brand= new TextField("brand", StringHelperTools.nvl(MainVo.getBrand()),Store.YES);
				brand.setBoost((float) 1.5);
				doc.add(brand);
				TextField productpattern= new TextField("productpattern", StringHelperTools.nvl(MainVo.getProductpattern()),Store.YES);
				productpattern.setBoost((float) 1.5);
				doc.add(productpattern);
				
				doc.add(new TextField("color", StringHelperTools.nvl(MainVo.getColor()),Store.YES));
				TextField color= new TextField("color", StringHelperTools.nvl(MainVo.getColor()),Store.YES);
				color.setBoost((float) 0.7);
				doc.add(color);				
				doc.add(new StringField("category", StringHelperTools.nvl(MainVo.getCategory()),Store.YES));
				doc.add(new TextField("productAttribute", StringHelperTools.nvl(MainVo.getProductAttribute()),Store.YES));				
				doc.add(new StringField("saletype", StringHelperTools.nvl(MainVo.getSaletype()),Store.YES));
				doc.add(new LongField("minprice", MainVo.getMinprice().longValue(),Store.YES));
				doc.add(new LongField("maxprice", MainVo.getMaxprice().longValue(),Store.YES));
				indexWriter2.addDocument(doc);	
			}
			indexWriter2.commit();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		logger.debug("*****************初始化产品索引 2 结束 ************,一共"+ls.size()+"条主产品");
		return true;
	}
	
	/**
	 * 获取所有产品数据
	 * @return
	 * @author xingle
	 * @data 2015-8-12 上午9:07:07
	 */
	private List<ProductInfoVo> getProductIndexLs1(){
		Util.i = 0;
		long start = System.currentTimeMillis();
	    final int total = 10;
		List<FutureTask<List<ProductInfoVo>>> futureTasks = new ArrayList<FutureTask<List<ProductInfoVo>>>();
		// 线程池 初始化 个线程
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		List<ProductInfoVo> reLs = new ArrayList<ProductInfoVo>();
		for (int i = 0; i < total; i++) {

			final int cnt = i;
			Callable<List<ProductInfoVo>> callable = new Callable<List<ProductInfoVo>>() {
				@Override
				public List<ProductInfoVo> call() throws Exception {
					// 执行任务
					List<ProductInfoVo> ls = productDao.getProductMainSegLs(total, cnt);
					return ls;
				}
			};
			// 创建一个异步任务
			FutureTask<List<ProductInfoVo>> futureTask = new FutureTask<List<ProductInfoVo>>(callable);
			futureTasks.add(futureTask);
			executorService.submit(futureTask);
		}
		
		for (FutureTask<List<ProductInfoVo>> futureTask : futureTasks) {          
                try {
					List<ProductInfoVo> ls = futureTask.get();
	                if(null!=ls){
	                	reLs.addAll(ls);
	                }                  
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
            
        }
		executorService.shutdown();
		long end = System.currentTimeMillis();     
		System.out.println("一共使用时间："+(end-start)/1000+"s");
		
		return reLs;
	}


	/**
	 * 
	 * @param mainVo
	 * @return
	 * @author xingle
	 * @data 2015-8-6 上午10:42:34
	 */
	public boolean addProductIndex1(ProductInfoVo mainVo){
		indexWriter = this.getIndexWriter();
		Document doc = new Document();
		doc.add(new StringField("productId",mainVo.getProductId().toString(),Store.YES));
		TextField productName = new TextField("productName", StringHelperTools.nvl(mainVo.getProductName()), Store.YES);
		productName.setBoost((float) 1.5);
		doc.add(productName);
		doc.add(new StringField("picturepath1", StringHelperTools.nvl(mainVo.getPicturepath1()),Store.YES));
		doc.add(new TextField("brand", StringHelperTools.nvl(mainVo.getBrand()),Store.YES));
		doc.add(new TextField("productpattern", StringHelperTools.nvl(mainVo.getProductpattern()),Store.YES));
		doc.add(new TextField("color", StringHelperTools.nvl(mainVo.getColor()),Store.YES));
		doc.add(new StringField("category", StringHelperTools.nvl(mainVo.getCategory()),Store.YES));
		doc.add(new TextField("productAttribute", StringHelperTools.nvl(mainVo.getProductAttribute()),Store.YES));			
		doc.add(new StringField("saletype", StringHelperTools.nvl(mainVo.getSaletype()),Store.YES));				
		doc.add(new StringField("doctype", "poi",Store.YES));		
		try {
			indexWriter.addDocument(doc);
			indexWriter.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return true;
	}


}
