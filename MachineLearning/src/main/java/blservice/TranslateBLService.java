package blservice;

/**
 * Created by 朱应山 on 2017/7/14.
 */
public interface TranslateBLService {
    /**
     * 翻译的方法，在这之中调用jython
     * @param oriLanguage 原始语言
     * @return  翻译的目标的语言
     */
    public String translate(String oriLanguage);
}
