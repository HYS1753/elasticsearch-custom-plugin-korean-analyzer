package elasticsearch.custom.plugin.builder;

import elasticsearch.custom.plugin.analysis.ChoSeongTokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;

public class ChoSeongTokenFilterBuilder extends AbstractTokenFilterFactory {
    private final boolean removeAllSingleLetter;
    private final boolean removeSingleJaeum;
    private final boolean removeSingleMoeum;

    public ChoSeongTokenFilterBuilder(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(name, settings);
        // 한글자 단독 한글 제거 여부 (default = false)
        // ( ex. 안녕ㅎㅏ세요 -> (true) ㅇㄴㅅㅇ, (false) ㅇㄴㅎㅏㅅㅇ )
        this.removeAllSingleLetter =  settings.getAsBoolean("remove_all_single_letter", false);
        // 한글자 단독 자음 제거 여부 (default = false)
        // ( ex. 안녕ㅎㅏ세요 -> (true) ㅇㄴㅏㅅㅇ, (false) ㅇㄴㅎㅏㅅㅇ )
        this.removeSingleJaeum =  settings.getAsBoolean("remove_single_jaeum", false);
        // 한글자 단독 모음 제거 여부 (default = false)
        // ( ex. 안녕ㅎㅏ세요 -> (true) ㅇㄴㅎㅅㅇ, (false) ㅇㄴㅎㅏㅅㅇ )
        this.removeSingleMoeum =  settings.getAsBoolean("remove_single_moeum", false);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        boolean removeJaeom = false;
        boolean removeMoeum = false;
        if (this.removeAllSingleLetter) {
            removeJaeom = true;
            removeMoeum = true;
        } else {
            removeJaeom = this.removeSingleJaeum;
            removeMoeum = this.removeSingleMoeum;
        }
        return new ChoSeongTokenFilter(tokenStream, removeJaeom, removeMoeum);
    }
}
