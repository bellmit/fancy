package cn.telling.common.uitl;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import cn.telling.utils.StringHelperTools;
/**
 * 数值格式化公共类
 */
public class CmnUtFormat {
	private static DecimalFormat df1 = new DecimalFormat("###,###,###,##0");
	private static DecimalFormat df2 = new DecimalFormat("###0");
	private static DecimalFormat df3 = new DecimalFormat("###0.00");
	private static DecimalFormat df4 = new DecimalFormat("###,###,###,##0.00");
	private static DecimalFormat df5 = new DecimalFormat("###0.0000");
    private static DecimalFormat df6 = new DecimalFormat("###,###,###,##0.00");
		
	/**
	 * 数值格式化
	 * @param parm 数值
	 * @return ###,###,###,##0
	 */
	public static String FormatDecimal(BigDecimal parm) {
		if (parm == null) {
			return "";
		}
		return df1.format(parm);
	}

	/**
	 * 数值格式化
	 * @param parm 数值
	 * @return ###,###,###,##0
	 */
	public static String FormatDecimal(Integer parm) {
		if (parm == null) {
			return "";
		}
		return df1.format(parm);
	}

	/**
	 * 数值格式化
	 * @param parm 数值
	 * @return ###,###,###,##0
	 */
	public static String FormatDecimal(long parm) {
		return df1.format(parm);
	}

	/**
	 * 数值格式化
	 * @param parm 数值
	 * @return ###,###,###,##0
	 */
	public static String FormatDecimal(String parm) {
		if (parm == null || parm.equals("")) {
			return "";
		}
		return FormatDecimal(new BigDecimal(parm));
	}

	/**
	 * 数值格式化
	 * @param parm 数值
	 * @return ###0
	 */
	public static String FormatInteger(BigDecimal parm) {
		if (parm == null) {
			return "";
		}
		return df2.format(parm);
	}

	/**
	 * 数值格式化
	 * @param parm 数值
	 * @return ###0
	 */
	public static String FormatInteger(Integer parm) {
		if (parm == null) {
			return "";
		}
		return df2.format(parm);
	}

	/**
	 * 数值格式化
	 * @param parm 数值
	 * @return ###0
	 */
	public static String FormatInteger(long parm) {
		return df2.format(parm);
	}

	/**
	 * 数值格式化
	 * @param parm 数值
	 * @return ###0
	 */
	public static String FormatInteger(String parm) {
		if (parm == null || parm.equals("")) {
			return "";
		}
		return FormatInteger(new BigDecimal(parm));
	}

	/**
	 * 数值格式化
	 * @param parm 数值
	 * @return ###0
	 */
	public static String FormatInteger0(String parm) {
		if (parm == null || parm.equals("")) {
			return "0";
		}
		return FormatInteger(new BigDecimal(parm));
	}

	/**
	 * 金额格式化
	 * @param parm 金额
	 * @return ###0.00
	 */
	public static String formatMoney(BigDecimal parm) {
		if (parm == null) {
			return "";
		}
		BigDecimal result = formatBigDecimal(parm,2,BigDecimal.ROUND_HALF_UP);
		return df3.format(result);
	}
	
	public static String formatMoney_negative(BigDecimal parm) {
		if (parm == null) {
			return "";
		}
		BigDecimal result = formatBigDecimal(parm,2,BigDecimal.ROUND_HALF_UP);
		return "-"+df3.format(result);
	}
    
    /**
     * 金额格式化
     * @param parm 金额
     * @return ##,#0.00
     */
    public static String formatMoney2(BigDecimal parm) {
        if (parm == null) {
            return "";
        }
        BigDecimal result = formatBigDecimal(parm,2,BigDecimal.ROUND_HALF_UP);
        return df6.format(result);
    }
	/**
	 * 金额格式化
	 * @param parm 金额
	 * @return ###0.00
	 */
	public static String formatMoneyWithComma(BigDecimal parm) {
		if (parm == null) {
			return "";
		}
		return formatDecimalFraction2(parm);
	}
	/**
	 * 金额格式化
	 * @param parm 金额
	 * @return ###0.00
	 */
	public static String formatMoney(long parm) {
		return df3.format(parm);
	}
    
    /**
     * 金额格式化
     * @param parm 金额
     * @return ##,#0.00
     */
    public static String formatMoney2(long parm) {
        return df6.format(parm);
    }

	/**
	 * 金额格式化
	 * @param parm 金额
	 * @return ###0.00
	 */
	public static String formatMoney(String parm) {
		if (parm == null || parm.trim().equals("")) {
			return "0.00";
		}

		return formatMoney(new BigDecimal(parm));
	}
    
    /**
     * 金额格式化
     * @param parm 金额
     * @return ##,#0.00
     */
    public static String formatMoney2(String parm) {
        if (parm == null || parm.trim().equals("")) {
            return "0.00";
        }

        return formatMoney2(new BigDecimal(parm));
    }
	/**
	 * 金额格式化
	 * @param parm 金额
	 * @return ###0.00
	 */
	public static String formatMoneyWithComma(String parm) {
		if (parm == null || parm.trim().equals("")) {
			return "0.00";
		}

		return formatDecimalFraction2(new BigDecimal(parm));
	}

	/**
	 * 数值格式化
	 * @param dblParam 数值
	 * @return ###,###,###,##0.00
	 */
	public static String formatDecimalFraction2(double dblParam) {
		return df4.format(dblParam);
	}

	/**
	 * 数值格式化
	 * @param bdParam 数值
	 * @return ###,###,###,##0.00
	 */
	public static String formatDecimalFraction2(BigDecimal bdParam) {

		if (bdParam == null) {
			return "";
		}
		BigDecimal result = formatBigDecimal(bdParam,2,BigDecimal.ROUND_HALF_UP);
		return df4.format(result);
	}

	/**
	 * 把java.sql.Timestamp格式时间 转成 yyyy-mm-dd hh:mm:ss 字符串
	 * 
	 * @param time 时间 Timestamp
	 * @return 时间 yyyy-mm-dd hh:mm:ss
	 */
	public static String processSendTime(java.sql.Timestamp time) {
		return (time == null) ? null : time.toString().substring(0, 19);
	}

	/**
	 * 把汇率格式化为4位小数
	 * @param parm
	 * @return 格式化的汇率
	 */
	public static String formatRate(BigDecimal parm) {
		if (parm == null) {
			return "";
		}
		parm = formatBigDecimal(parm,4,BigDecimal.ROUND_HALF_UP);
		return df5.format(parm);
	}

	/**
	 * 转换BigDecimal为Integer
	 * 
	 * @param parm BigDecimal数据
	 * @return Integerl数据
	 */
	public static Integer toInteger(BigDecimal parm) {
		if (parm == null) {
			return null;
		} else {
			return new Integer(parm.intValue());
		}
	}
	

    /**
     * @param convertBigDecimal
     * @return
     * @author madb
     */
    public static String formatBigDecimal(Object convertBigDecimal){
        DecimalFormat format=new DecimalFormat("######.##");
        String resultPara="0";
        if(convertBigDecimal!=null && !convertBigDecimal.toString().equals("")){
        	BigDecimal result = formatBigDecimal(new BigDecimal(convertBigDecimal.toString()),2,BigDecimal.ROUND_HALF_UP);
            resultPara=format.format(result);
        }
        return resultPara;
    }
    
    /**
     * 对数据进行精确格式化，四舍五入
     * @param val 源数据
     * @param scale 精确的位数
     * @param roundingMode 舍入方式
     * @return
     */
    public static BigDecimal formatBigDecimal(BigDecimal val,int scale,int roundingMode){
    	BigDecimal result =  val.setScale(scale,roundingMode);
    	
    	return result;
    }
    
    /**
     * 格式化金额，四舍五入，保留两位小数
     * @param val
     * @param scale
     * @return
     */
    public static String formateMoney3(String val,int scale){
    	if(StringHelperTools.nvl(val).equals("") || scale < 0){
    		return "0.00";
    	}
    	BigDecimal result = formatBigDecimal(new BigDecimal(val),2,BigDecimal.ROUND_HALF_UP);
    	return df6.format(result);
    }
}
