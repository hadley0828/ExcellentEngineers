package bl.TranslateBL;

import blservice.TranslateBLService;
import util.SyncBuff;

/**
 * Created by 朱应山 on 2017/7/14.
 */
public class TranslateBL implements TranslateBLService {
    //CMDRunner runner=new CMDRunner();
    private SyncBuff syncBuff = SyncBuff.getInstance();

    @Override
    public String translate(String oriLanguage, Translation translation) {

        syncBuff.setBuff(oriLanguage);
        String result;
        while (true) {
            if (syncBuff.hasOutput()) {
                result = syncBuff.getOutput();
                break;
            }
        }
        return result;
//        return runner.cmdrun(oriLanguage,translation);
        //return jython.runPythonTrans(oriLanguage,translation);
    }
}

