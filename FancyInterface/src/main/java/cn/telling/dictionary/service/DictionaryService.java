package cn.telling.dictionary.service;

import java.math.BigDecimal;
import java.util.List;

import cn.telling.common.pager.PageVo;
import cn.telling.dictionary.vo.DictionaryConditionVo;
import cn.telling.dictionary.vo.DictionaryTypeVo;
import cn.telling.dictionary.vo.DictionaryVo;
import cn.telling.dictionary.vo.SelectVo;

public interface DictionaryService {
	/**
	 * 分页搜索总条数
	 * @param type
	 * @return
	 */
	public int getDictionaryTypeTotalCountForPage(String type);
	/**
	 * 分页展示字典类型
	 * @param pageVo
	 * @param type
	 * @return
	 */
	public List<DictionaryTypeVo> getDictionaryTypeByPage(PageVo pageVo,String type);
	/**
	 * 添加字典类型
	 * @param vo
	 * @param userid
	 * @return
	 */
	public int addDictionaryType(DictionaryTypeVo vo,BigDecimal userid);
	/**
	 * 删除记录
	 * @param typeValue
	 * @param userid
	 * @return
	 */
	public int deleteDictionaryType(String typeValue,BigDecimal userid);
	/**
	 * 更新记录
	 * @param vo
	 * @param userid
	 * @return
	 */
	public int updateDictionaryType(DictionaryTypeVo vo,BigDecimal userid);
	/**
	 * 取得总条数，用于计算字典类型的key
	 * @return
	 */
	public int getDictionaryTypeTotalCount();
	/**
	 * 获取字典总条数，计算字典的id和key
	 * @return
	 */
	public int getDictionaryTotalCount();
	/**
	 * 根据id获取字典类型，id为空取全部类型
	 * @param id
	 * @return
	 */
	public List<DictionaryTypeVo> getDictionaryType(String id);
	public List<DictionaryVo> getDictionaryByPage(PageVo pageVo,DictionaryConditionVo vo);
	public int getDicitonaryTotalCountForPage(DictionaryConditionVo vo);
	public int insertDictionary(DictionaryVo vo);
	public int deleteDictionary(String id);
	public void modifyDictionary(DictionaryVo vo);
	public DictionaryVo getDictionaryById(String id);
	public boolean queryDictionaryType(String total);
	public boolean queryDictionaryIsAble(String dictionaryTypeKey,
			String dictionaryValue);
	
	/**
	 * @Description：根据字段类型查询字典项
	 * @author: 高兵(gaobing03@chinatelling.com)
	 * @Package: com.dictionary.dao
	 * @return: List<DictionaryVo>
	 * @date: 下午02:58:49
	 * @param type
	 * @return
	 */
	public List<SelectVo> getDictionaryByType(
			String type) ;
}
