import bl.TranslateBL.TranslateBL;
import bl.TranslateBL.Translation;
import blservice.TranslateBLService;
import org.junit.Test;

/**
 * Created by 朱应山 on 2017/7/17.
 */

public class TranslateBLServiceTest {
    @Test
    public void translate() throws Exception {
        TranslateBLService bl=new TranslateBL();
        String result=bl.translate("美国", Translation.CHSTOEN);
        System.out.println(result);
    }
    @Test
    public void translateCHS()throws Exception{
        TranslateBLService bl=new TranslateBL();
        String result=bl.translate("1245632", Translation.ENTOCHS);
        System.out.print(result);
    }
}
