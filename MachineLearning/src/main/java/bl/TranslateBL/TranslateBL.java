package bl.TranslateBL;

import blservice.TranslateBLService;

/**
 * Created by 朱应山 on 2017/7/14.
 */
public class TranslateBL implements TranslateBLService {
    CMDRunner runner=new CMDRunner();

    @Override
    public String translate(String oriLanguage, Translation translation) {
        return runner.cmdrun(oriLanguage,translation);
        //return jython.runPythonTrans(oriLanguage,translation);
    }
}

