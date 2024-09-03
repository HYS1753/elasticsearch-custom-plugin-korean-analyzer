package elasticsearch.custom.plugin.analysis;

import elasticsearch.custom.plugin.analysis.converter.Kor2EngConverter;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;

public class Kor2EngTokenFilter extends TokenFilter {

    private CharTermAttribute termAtt;
    private Kor2EngConverter converter;

    public Kor2EngTokenFilter(TokenStream input, boolean convertSingleKoreanLetter) {
        super(input);
        this.termAtt = addAttribute(CharTermAttribute.class);
        this.converter = new Kor2EngConverter(convertSingleKoreanLetter);
    }

    @Override
    public boolean incrementToken() throws IOException {

        if (this.input.incrementToken()) {
            CharSequence parsedData = converter.convert(termAtt.toString());
            termAtt.setEmpty();
            termAtt.append(parsedData);

            return true;
        }

        return false;
    }
}
