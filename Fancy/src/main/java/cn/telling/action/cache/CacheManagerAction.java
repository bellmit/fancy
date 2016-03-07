package cn.telling.action.cache;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.telling.areas.service.IAreaInfoService;
import cn.telling.areas.vo.AreaInfoVo;
import cn.telling.dictionary.service.DictionaryService;
import cn.telling.dictionary.vo.SelectVo;

import com.danga.MemCached.MemCachedClient;

@Controller
public class CacheManagerAction {

	@Resource
	private IAreaInfoService areaInfoService;

	@Resource
	private MemCachedClient memClient;
	
	@Resource
	private DictionaryService dictionaryService;

	@RequestMapping(value = "/cache/cacheRefresh", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String cacheInit() {
		System.out.println("刷新缓存开始…………");
		intiAllAreaInfo();
		System.out.println("刷新缓存结束…………");
		return "success";
	}
	
	/**
	 * @Description：加载所有省市县信息
	 * @author: 高兵(gaobing03@chinatelling.com)
	 * @Package: com.tools.cache
	 * @return: void
	 * @date: 上午09:38:51
	 */
	private void intiAllAreaInfo(){
		List<AreaInfoVo> lst = this.areaInfoService.queryManageProvinceAreaByUserId(1+"@"+true);
		if(lst!=null&&lst.size()>0){
			memClient.replace("proviceAreaCache", lst);//把省的数据加载到缓存
			for(AreaInfoVo vo:lst){
				List<AreaInfoVo> cityAreaLst=this.areaInfoService.queryManageAreaByUserIdAndAreaid(1+"@"+true,
						vo.getAreaId()+"");
				if(cityAreaLst!=null&&cityAreaLst.size()>0){
					memClient.replace(vo.getAreaId()+"AreaCache", cityAreaLst);//把市的数据加载到缓存
					for(AreaInfoVo cityArea:cityAreaLst){
						List<AreaInfoVo> disAreaLst=this.areaInfoService.queryManageAreaByUserIdAndAreaid(1+"@"+true,
								cityArea.getAreaId()+"");
						if(disAreaLst!=null&&disAreaLst.size()>0){
							memClient.replace(cityArea.getAreaId()+"AreaCache", disAreaLst);//把区县的数据加载到缓存
						}
					}
				}
			}
		}
		List<SelectVo> channel_type=new ArrayList<SelectVo>();
		channel_type=dictionaryService.getDictionaryByType("channel_type");
		memClient.replace("channel_type",channel_type);//更新缓存
	}
	
}
