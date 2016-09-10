package cn.telling.common;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import cn.telling.common.Pager.PageVo;


/***
 * 
 * Dao基类，所有Dao必须继承本类
 * 
 * @author guihui
 * @update fanhy
 * @version 1.0
 * @since Nov 18, 2014
 */
@SuppressWarnings("deprecation")
public class CommonBaseDao {

    // 记录日志
    protected final Logger logger = Logger.getLogger(this.getClass());

    protected static final String TABLENAME = "table_name";

    protected static final String TABLEWHERE = "table_where";

    @Resource
    protected JdbcTemplate jdbcTemplate;


    protected Integer queryForInt(String sql, Object[] params) {
        logger.debug("查询总数:" + logSQL(sql, params));
        return jdbcTemplate.queryForInt(sql, params);
    }

    protected <T> List<T> queryForList(String sql, Object[] objects, Class<T> cla) {
        logger.debug("查询单个数量:" + logSQL(sql, objects));
        return jdbcTemplate.queryForList(sql, objects, cla);
    }

    protected Map<String, Object> queryForMap(String sql, Object[] obj) {
        logger.debug("查询map:" + logSQL(sql, obj));
        return jdbcTemplate.queryForMap(sql, obj);
    }

    protected <T> T queryForObject(String sql, RowMapper<T> rowMapper) {

        logger.debug("查询单个:" + logSQL(sql, null));
        List<T> results = jdbcTemplate.query(sql, rowMapper);

        if (results != null && results.size() > 0) {
            return results.get(0);
        } else {
            return null;
        }
    }

    protected <T> T queryForObject(String sql, Object[] args, RowMapper<T> rowMapper) {

        logger.debug("查询单个:" + logSQL(sql, args));
        List<T> results = jdbcTemplate.query(sql, args, rowMapper);

        if (results != null && results.size() > 0) {
            return results.get(0);
        } else {
            return null;
        }
    }

    protected <T> List<T> query(String sql, RowMapper<T> rowMapper) {
        logger.debug("查询列表:" + logSQL(sql, null));
        List<T> results = jdbcTemplate.query(sql, rowMapper);
        return results;
    }

    protected List<Map<String, Object>> query(String sql, Object... args) {
        logger.debug("查询列表:" + logSQL(sql, null));
        return jdbcTemplate.queryForList(sql, args);
    }

    protected <T> List<T> query(String sql, Object[] args, RowMapper<T> rowMapper) {
        logger.debug("查询列表:" + logSQL(sql, args));
        List<T> results = jdbcTemplate.query(sql, args, rowMapper);

        return results;
    }

    protected List<Map<String, Object>> queryByPage(String sql, PageVo page, Object... args) {
        String sqlCount = getTotalPageSql(sql);
        logger.debug("查询总数:" + logSQL(sqlCount, args));
        int totalCount = jdbcTemplate.queryForInt(sqlCount, args);
        page.setTotalCount(totalCount);
        String sqlByPage = getPageDatas(page, sql);
        logger.debug("查询分页:" + logSQL(sqlByPage, args));
        return jdbcTemplate.queryForList(sqlByPage, args);
    }

    /**
     * 分页查询，不带参数
     * 
     * @param sql
     *            原始SQL语句
     * @param page
     *            分页
     * @param rowMapper
     *            自动注入映射
     * @return
     */
    protected <T> List<T> queryByPage(String sql, PageVo page, RowMapper<T> rowMapper) {
        String sqlCount = getTotalPageSql(sql);
        logger.debug("查询总数:" + logSQL(sqlCount, null));
        int totalCount = jdbcTemplate.queryForInt(sqlCount);
        page.setTotalCount(totalCount);
        String sqlByPage = getOraclePageDatas(page, sql);
        logger.debug("查询分页:" + logSQL(sqlByPage, null));
        List<T> results = jdbcTemplate.query(sqlByPage, rowMapper);
        return results;
    }

    protected <T> List<T> queryByPage(String sql, PageVo page, Object[] args, Class<T> cla) {
        String sqlCount = getTotalPageSql(sql);
        int totalCount = jdbcTemplate.queryForInt(sqlCount, args);
        logger.debug("查询总数:" + logSQL(sqlCount, args));
        page.setTotalCount(totalCount);
        String sqlByPage = getPageDatas(page, sql);
        logger.debug("查询分页:" + logSQL(sqlByPage, args));
        List<T> results = jdbcTemplate.queryForList(sql, args, cla);
        return results;
    }

    /**
     * 分页查询，带参数
     * 
     * @param sql
     *            原始SQL语句
     * @param page
     *            分页
     * @param args
     *            参数列表
     * @param rowMapper
     *            自动注入映射
     * @return
     */
    protected <T> List<T> queryByPage(String sql, PageVo page, Object[] args, RowMapper<T> rowMapper) {
        String sqlCount = getTotalPageSql(sql);
        logger.debug("查询总数:" + logSQL(sqlCount, args));
        int totalCount = jdbcTemplate.queryForInt(sqlCount, args);
        page.setTotalCount(totalCount);
        String sqlByPage = getPageDatas(page, sql);
        logger.debug("查询分页:" + logSQL(sqlByPage, args));
        List<T> results = jdbcTemplate.query(sqlByPage, args, rowMapper);
        return results;
    }

    protected void getCaller() {
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        for (StackTraceElement ste : stack) {
            if ((ste.getClassName().indexOf(getClass().getName())) != -1) {
                logger.info("called by " + ste.getClassName() + "." + ste.getMethodName() + "/" + ste.getFileName());
            }
        }
    }

    protected <T> T execute(CallableStatementCreator csc, CallableStatementCallback<T> action) {
        T result = jdbcTemplate.execute(csc, action);
        return result;
    }

    /**
     * @return jdbcTemplate
     */
    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    /**
     * @param jdbcTemplate
     *            jdbcTemplate
     */
    protected void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private String getPageDatas(PageVo page, String sql) {
        // 拼装分页sql
        StringBuffer sb = new StringBuffer();
        sb.append(sql);
        sb.append(" LIMIT ");
        sb.append((page.getPageNow() - 1) * page.getMessageForPage());
        sb.append(",");
        sb.append(page.getMessageForPage());
        return sb.toString();
    }
    
    /**
     * 
     * @Description: TODO set oracle pager sql
     * @param       PageVo,  sql
     * @return      sql with right interval
     * @exception   null
     * @see         null
     * @author      liu fei
     * @date 2011-11-11 下午3:05:32 
     * @version V1.0
     */
    public static String getOraclePageDatas(PageVo pageVo, String sql) {
        // 拼装分页sql
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT * FROM (SELECT ROWNUM row_, t.* FROM (");
        sb.append(sql);
        sb.append(") t ) WHERE row_ <=");
        sb.append(pageVo.getPageNow()*pageVo.getMessageForPage());
        sb.append(" AND row_ >=");
        sb.append((pageVo.getPageNow()-1)*pageVo.getMessageForPage()+1);
        return sb.toString();
    }

    private String getTotalPageSql(String sql) {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT count(1) FROM (");
        sb.append(sql);
        sb.append(") t");

        return sb.toString();
    }

    /**
     * 日志打印JDBCTemplate的SQL
     * 
     * @param sql
     * @param args
     * @return 返回实际执行sql
     */
    protected String logSQL(String sql, Object args[]) {
        if (args == null)
            return sql;
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                String param = args[i].toString().replace("$", "\\$");
                sql = sql.replaceFirst("\\?", param.length() == 0 ? param : "\\'" + param + "'");
            } else {
                sql = sql.replaceFirst("\\?", "null");
            }
        }
        return sql;
    }

    /**
     * 编辑表的字段值
     * 
     * @param tableName
     * @param fieldNameArray
     * @param fieldValArray
     * @param whereFiledNameArray
     * @param whereFieldValArray
     */
    public void setFieldVal(String tableName, String[] fieldNameArray, Object[] fieldValArray, String[] whereFiledNameArray, Object[] whereFieldValArray) {

        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE " + tableName + " SET ");
        for (String fieldName : fieldNameArray) {

            sql.append(fieldName + "=?,");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" WHERE 1=1");
        for (String whereFieldName : whereFiledNameArray) {

            sql.append(" AND " + whereFieldName + "=?");
        }

        List<Object> paramList = new ArrayList<Object>();
        for (Object fieldVal : fieldValArray) {

            paramList.add(fieldVal);
        }
        for (Object whereFieldVal : whereFieldValArray) {

            paramList.add(whereFieldVal);
        }

    }
}