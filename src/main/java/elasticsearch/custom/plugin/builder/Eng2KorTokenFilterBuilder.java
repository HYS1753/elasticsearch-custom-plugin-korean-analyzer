package elasticsearch.custom.plugin.builder;

import elasticsearch.custom.plugin.analysis.Eng2KorTokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;
import org.elasticsearch.index.analysis.Analysis;

public class Eng2KorTokenFilterBuilder extends AbstractTokenFilterFactory {
    private final boolean convertSingleKoreanLetter;

    public Eng2KorTokenFilterBuilder(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(name, settings);
        // 한글자 자,모음 변환 여부 (default = true)
        // ( ex. dkssudgg -> (true) 안녕ㅎㅎ, (false) 안녕gg )
        this.convertSingleKoreanLetter =  settings.getAsBoolean("convert_single_korean_letter", true);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new Eng2KorTokenFilter(tokenStream, this.convertSingleKoreanLetter);
    }
}
