package elasticsearch.custom.plugin;

import elasticsearch.custom.plugin.builder.ChoSeongTokenFilterBuilder;
import elasticsearch.custom.plugin.builder.Eng2KorTokenFilterBuilder;
import elasticsearch.custom.plugin.builder.JamoTokenFilterBuilder;
import elasticsearch.custom.plugin.builder.Kor2EngTokenFilterBuilder;
import org.elasticsearch.index.analysis.CharFilterFactory;
import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.index.analysis.TokenizerFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.HashMap;
import java.util.Map;

public class KoreanAnalyzerPlugin extends Plugin implements AnalysisPlugin {

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> getTokenFilters() {
        Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> filters = new HashMap<>();

        // 1. 한글 초성 분리 필터
        filters.put("choseong_filter", ChoSeongTokenFilterBuilder::new);
        // 2. 한글 자음,모음 분리(자소 분리) 필터
        filters.put("jamo_filter", JamoTokenFilterBuilder::new);
        // 3. 한글 -> 영문 변환 필터
        filters.put("kor2eng_filter", Kor2EngTokenFilterBuilder::new);
        // 4. 영문 -> 한글 변환 필터
        filters.put("eng2kor_filter", Eng2KorTokenFilterBuilder::new);

        return filters;
    }
}
