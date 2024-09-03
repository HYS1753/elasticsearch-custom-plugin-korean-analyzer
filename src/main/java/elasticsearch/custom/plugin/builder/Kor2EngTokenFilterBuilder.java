package elasticsearch.custom.plugin.builder;

import elasticsearch.custom.plugin.analysis.JamoTokenFilter;
import elasticsearch.custom.plugin.analysis.Kor2EngTokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;

public class Kor2EngTokenFilterBuilder extends AbstractTokenFilterFactory {
    private final boolean convertSingleKoreanLetter;

    public Kor2EngTokenFilterBuilder(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(name, settings);
        // 한글자 자,모음 변환 여부 (default = true)
        // ( ex. 안녕ㅎㅎ -> (true) dkssudgg, (false) dkssudㅎㅎ )
        this.convertSingleKoreanLetter =  settings.getAsBoolean("convert_single_korean_letter", true);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new Kor2EngTokenFilter(tokenStream, this.convertSingleKoreanLetter);
    }
}
