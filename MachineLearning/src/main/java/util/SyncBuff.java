package util;

public class SyncBuff {
    private String buff;
    private String output;
    private SyncBuff(){
        buff = "";
        output = "";
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

    public boolean hasOutput() {
        return output.length() > 0;
    }

    public String getOutput() {
        String t;
        synchronized (this) {
            t = output;
            output = "";
        }
        return t;
    }

    public void setOutput(String s) {
        synchronized (this) {
            output = s;
        }
    }
}
