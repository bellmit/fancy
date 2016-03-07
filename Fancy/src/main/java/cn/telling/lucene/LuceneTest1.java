package cn.telling.lucene;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import com.chenlb.mmseg4j.analysis.ComplexAnalyzer;
import com.chenlb.mmseg4j.analysis.CutLetterDigitFilter;


/**
 * @Title: LuceneTest1.java
 * @Package cn.fancy.test
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2016-2-14 下午3:39:59
 * @version V1.0
 */
public class LuceneTest1 {
    private Analyzer analyzer = new ComplexAnalyzer(LuceneTest1.class.getResource("/").getPath()
                    + "/mmseg4j/data");

    private List<String> splitWordBymmseg4j1(String text)
    {
      List<String> ls = new ArrayList<String>();

      if ((text != null) && (!"".equals(text))) {
        System.out.println("--Mmseg4j拆分的结果是：");
        try {
          TokenStream ts = this.analyzer.tokenStream("", new StringReader(text));
          Analyzer.TokenStreamComponents ts1 = new Analyzer.TokenStreamComponents(
            (Tokenizer)ts, new CutLetterDigitFilter(ts));
          ts = ts1.getTokenStream();
          ts.reset();
          while (ts.incrementToken()) {
            CharTermAttribute ta = 
              (CharTermAttribute)ts
              .getAttribute(CharTermAttribute.class);

            System.out.print("【" + ta.toString() + "】");
            ls.add(ta.toString());
          }
          ts.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      System.out.println();
      return ls;
    }

    public static void main(String[] args) {
        LuceneTest1 search = new LuceneTest1();
        search.splitWordBymmseg4j1("iphone6s");
        System.out.println(LuceneTest1.class.getResource("/").getPath()); 
      
    }
}
