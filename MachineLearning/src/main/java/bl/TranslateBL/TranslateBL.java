package bl.TranslateBL;

import blservice.TranslateBLService;

/**
 * Created by 朱应山 on 2017/7/14.
 */
public class TranslateBL implements TranslateBLService {
    Jython jython=new Jython();

    @Override
    public String translate(String oriLanguage, Translation translation) {
        return jython.runPythonTrans(oriLanguage,translation);
    }
}

