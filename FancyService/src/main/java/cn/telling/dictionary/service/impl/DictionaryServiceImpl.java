package cn.telling.dictionary.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.telling.common.Pager.PageVo;
import cn.telling.dictionary.dao.DictionaryDao;
import cn.telling.dictionary.service.DictionaryService;
import cn.telling.dictionary.vo.DictionaryConditionVo;
import cn.telling.dictionary.vo.DictionaryTypeVo;
import cn.telling.dictionary.vo.DictionaryVo;
import cn.telling.dictionary.vo.SelectVo;

@Service("DictionaryService")
public class DictionaryServiceImpl implements DictionaryService {
	@Autowired
	@Qualifier("DictionaryDao")
	private DictionaryDao dictionaryDao;

	@Override
	public int getDictionaryTypeTotalCountForPage(String type) {
		return dictionaryDao.getDictionaryTypeTotalCountForPage(type);
	}

	@Override
	public List<DictionaryTypeVo> getDictionaryTypeByPage(PageVo pageVo,
			String type) {
		return dictionaryDao.getDictionaryTypeByPage(pageVo, type);
	}

	@Override
	@Transactional(value = "txManager", isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public int addDictionaryType(DictionaryTypeVo vo, BigDecimal userid) {
		return dictionaryDao.addDictionaryType(vo, userid);
	}

	@Override
	@Transactional(value = "txManager", isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public int deleteDictionaryType(String typeValue, BigDecimal userid) {
		return dictionaryDao.deleteDictionaryType(typeValue, userid);
	}

	@Override
	@Transactional(value = "txManager", isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public int updateDictionaryType(DictionaryTypeVo vo, BigDecimal userid) {
		return dictionaryDao.updateDictionaryType(vo, userid);
	}

	@Override
	public int getDictionaryTypeTotalCount() {
		return dictionaryDao.getDictionaryTypeTotalCount();
	}

	@Override
	public int getDictionaryTotalCount() {
		return dictionaryDao.getDictionaryTotalCount();
	}

	@Override
	public List<DictionaryVo> getDictionaryByPage(PageVo pageVo,
			DictionaryConditionVo vo) {
		return dictionaryDao.getDictionaryByPage(pageVo, vo);
	}

	@Override
	public int getDicitonaryTotalCountForPage(DictionaryConditionVo vo) {
		return dictionaryDao.getDicitonaryTotalCountForPage(vo);
	}

	@Override
	@Transactional(value = "txManager", isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public int insertDictionary(DictionaryVo vo) {
		int t=this.getDictionaryTotalCount();
		vo.setDictionaryId(new BigDecimal(t));
		vo.setDictionaryKey(String.valueOf(t));
		return dictionaryDao.insertDictionary(vo);
	}

	@Override
	@Transactional(value = "txManager", isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public int deleteDictionary(String id) {
		return dictionaryDao.deleteDictionary(id);
	}
	/**
	 * 先删后增
	 * @param vo
	 */
	@Override
	public void modifyDictionary(DictionaryVo vo) {
		this.deleteDictionary(vo.getDictionaryId().toString());
		int t=this.getDictionaryTotalCount();
		vo.setDictionaryId(new BigDecimal(t));
		this.insertDictionary(vo);
	}
	public DictionaryVo getDictionaryById(String id){
		return dictionaryDao.getDictionaryById(id);
	}
	/**
	 * 根据id获取字典类型，id为空取全部类型
	 * @param id
	 * @return
	 */
	public List<DictionaryTypeVo> getDictionaryType(String id){
		return dictionaryDao.getDictionaryType(id);
	}

	@Override
	public boolean queryDictionaryType(String total) {
		// TODO Auto-generated method stub
		return dictionaryDao.queryDictionaryType(total);
	}

	@Override
	public boolean queryDictionaryIsAble(String dictionaryTypeKey,
			String dictionaryValue) {
		// TODO Auto-generated method stub
		return dictionaryDao.queryDictionaryIsAble(dictionaryTypeKey,
				dictionaryValue);
	}

	@Override
	public List<SelectVo> getDictionaryByType(String type) {
		// TODO Auto-generated method stub
		return dictionaryDao.getDictionaryByType(type);
	}
}
