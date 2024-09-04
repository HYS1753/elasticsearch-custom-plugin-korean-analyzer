package elasticsearch.custom.plugin.analysis;

import elasticsearch.custom.plugin.analysis.converter.Eng2KorConverter;
import elasticsearch.custom.plugin.analysis.parser.KoreanJamoParser;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.payloads.TokenOffsetPayloadTokenFilter;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;

public class Eng2KorTokenFilter extends TokenFilter {

    private CharTermAttribute termAtt;
    private Eng2KorConverter converter;

    public Eng2KorTokenFilter(TokenStream input, boolean convertSingleKoreanLetter) {
        super(input);
        this.termAtt = addAttribute(CharTermAttribute.class);
        this.converter = new Eng2KorConverter(convertSingleKoreanLetter);
    }

    @Override
    public boolean incrementToken() throws IOException {

        if (this.input.incrementToken()) {
            CharSequence parsedData = converter.convert(termAtt.toString());
            termAtt.setEmpty().append(parsedData);

            return true;
        }

        return false;
    }
}
