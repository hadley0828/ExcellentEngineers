import bl.TranslateBL;
import blservice.TranslateBLService;
import org.junit.Test;

/**
 * Created by 朱应山 on 2017/7/17.
 */

public class TranslateBLServiceTest {
    @Test
    /**
     * 翻译结果的测试
     */
    public void translate() throws Exception {
        TranslateBLService bl=new TranslateBL();
        String result=bl.translate("1245632");
        System.out.print(result);
    }
}
