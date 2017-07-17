package bl;

import blservice.TranslateBLService;

/**
 * Created by 朱应山 on 2017/7/14.
 */
public class TranslateBL implements TranslateBLService {
    Jython jython=new Jython();
    @Override
    public String translate(String oriLanguage) {
        return jython.runPythonTrans(oriLanguage);
    }
}
