package elasticsearch.custom.plugin.analysis;

import elasticsearch.custom.plugin.analysis.parser.KoreanJamoParser;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;

public class JamoTokenFilter extends TokenFilter {

    private CharTermAttribute termAtt;
    private KoreanJamoParser parser;

    public JamoTokenFilter(TokenStream input) {
        super(input);
        this.termAtt = addAttribute(CharTermAttribute.class);
        this.parser = new KoreanJamoParser();
    }

    /**
     * 한글 자모 Parser를 이용해 토큰을 파싱하고 Term을 구한다.
     * @return
     * @throws IOException
     */
    @Override
    public boolean incrementToken() throws IOException {

        if (this.input.incrementToken()) {
            CharSequence parsedData = parser.parse(termAtt.toString());
            termAtt.setEmpty();
            termAtt.append(parsedData);

            return true;
        }

        return false;
    }
}
