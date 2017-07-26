package bl.CommentBL;

import blservice.CommentBLService;
import util.SyncBuff;

/**
 * Created by 朱应山 on 2017/7/25.
 */
public class CommentBLServiceImpl implements CommentBLService {
    private SyncBuff syncBuff = SyncBuff.getInstance();
    @Override
    public Comment comment(String comment) {
        syncBuff.setBuff(comment);
        String result;
        while (true) {
            if (syncBuff.hasOutput()) {
                result = syncBuff.getOutput();
                break;
            }
        }
        return Comment.valueOf(result);
    }
}
