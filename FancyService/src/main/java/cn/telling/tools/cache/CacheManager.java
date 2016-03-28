package cn.telling.tools.cache;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.telling.areas.service.IAreaInfoService;
import cn.telling.areas.vo.AreaInfoVo;
import cn.telling.dictionary.service.DictionaryService;
import cn.telling.dictionary.vo.SelectVo;

import com.danga.MemCached.MemCachedClient;

public class CacheManager {
	private static Logger logger = Logger.getLogger(CacheManager.class);

	private MemCachedClient memClient;
	
	private IAreaInfoService areaInfoService;
	
	private DictionaryService dictionaryService;
	
	public void initCache(){
		logger.debug("缓存加载开始");
		/*this.intiAllAreaInfo();*/
		logger.debug("缓存加载结束");
	}
	
	/**
	 * @Description：加载所有省市县信息
	 * @author: 高兵(gaobing03@chinatelling.com)
	 * @Package: com.tools.cache
	 * @return: void
	 * @date: 上午09:38:51
	 */
	@SuppressWarnings("unused")
    private void intiAllAreaInfo(){
		List<AreaInfoVo> lst = this.areaInfoService.queryManageProvinceAreaByUserId(1+"@"+true);
		if(lst!=null&&lst.size()>0){
			memClient.add("proviceAreaCache", lst);//把省的数据加载到缓存
			for(AreaInfoVo vo:lst){
				List<AreaInfoVo> cityAreaLst=this.areaInfoService.queryManageAreaByUserIdAndAreaid(1+"@"+true,
						vo.getAreaId()+"");
				if(cityAreaLst!=null&&cityAreaLst.size()>0){
					memClient.add(vo.getAreaId()+"AreaCache", cityAreaLst);//把市的数据加载到缓存
					for(AreaInfoVo cityArea:cityAreaLst){
						List<AreaInfoVo> disAreaLst=this.areaInfoService.queryManageAreaByUserIdAndAreaid(1+"@"+true,
								cityArea.getAreaId()+"");
						if(disAreaLst!=null&&disAreaLst.size()>0){
							memClient.add(cityArea.getAreaId()+"AreaCache", disAreaLst);//把区县的数据加载到缓存
						}
					}
				}
			}
		}
		List<SelectVo> channel_type=new ArrayList<SelectVo>();
		channel_type=dictionaryService.getDictionaryByType("channel_type");
		memClient.replace("channel_type",channel_type);//更新缓存
	}

	public MemCachedClient getMemClient() {
		return memClient;
	}

	public void setMemClient(MemCachedClient memClient) {
		this.memClient = memClient;
	}

	public IAreaInfoService getAreaInfoService() {
		return areaInfoService;
	}

	public void setAreaInfoService(IAreaInfoService areaInfoService) {
		this.areaInfoService = areaInfoService;
	}

	public DictionaryService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}
}
