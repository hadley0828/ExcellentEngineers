package util;

public class SyncBuff {
    private String buff;
    private SyncBuff(){
        buff = "";
    }
    private static final SyncBuff instance = new SyncBuff();

    public static SyncBuff getInstance() {
        return instance;
    }

    public String getBuff() {
        String t;
        synchronized (this) {
            t = buff;
            buff = "";
        }
        return t;
    }

    public void setBuff(String s) {
        synchronized (this) {
            buff = s;
        }
    }

    public boolean hasBuff() {
        return buff.length() > 0;
    }
}
