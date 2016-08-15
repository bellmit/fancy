package lucene;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.stereotype.Component;

import com.chenlb.mmseg4j.analysis.ComplexAnalyzer;
import com.chenlb.mmseg4j.analysis.CutLetterDigitFilter;

@Component
public class SearchTest
{
  private Analyzer analyzer = new ComplexAnalyzer(SearchTest.class.getResource("/").getPath() + "/mmseg4j/data");

  public static void main(String[] args) {
    SearchTest searchTest=new SearchTest();
    searchTest.splitWordBymmseg4j1("iphone6s");
}
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
    return ls;
  }
  }