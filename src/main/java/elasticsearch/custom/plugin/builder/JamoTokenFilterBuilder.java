package elasticsearch.custom.plugin.builder;

import elasticsearch.custom.plugin.analysis.JamoTokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;

public class JamoTokenFilterBuilder extends AbstractTokenFilterFactory {

    public JamoTokenFilterBuilder(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(name, settings);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new JamoTokenFilter(tokenStream);
    }
}
