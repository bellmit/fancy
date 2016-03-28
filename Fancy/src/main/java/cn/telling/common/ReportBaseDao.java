package cn.telling.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

public class ReportBaseDao {
	@Autowired
	@Qualifier("report-template")
	protected JdbcTemplate jdbcTemplate;
}
