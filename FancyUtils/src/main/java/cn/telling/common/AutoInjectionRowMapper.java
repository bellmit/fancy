package cn.telling.common;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.RowMapper;


public class AutoInjectionRowMapper<T> implements RowMapper<T> {

	private Class<T> mappedClass;

	public AutoInjectionRowMapper(Class<T> mappedClass) {
		this.mappedClass = mappedClass;
	}

	@Override
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		T vo = BeanUtils.instantiate(this.mappedClass);
		AutoInjection.Rs2Vo(rs, vo);
		return vo;
	}

}
