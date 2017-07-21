package bl.TranslateBL;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * Created by 朱应山 on 2017/7/20.
 */
public class CMDRunner {
    /**
     * 使用runtime实现调用python，支持多种调用python包
     */
    public String cmdrun(String oriLanguage, Translation translation){
        System.out.println("begin"+oriLanguage);
        String tarLanguage="";
        try {
            String filePath="";
            switch (translation){
                case CHSTOEN:
                    filePath="src\\main\\python\\CHSTOEN.py "+oriLanguage;
                    break;
                case ENTOCHS:
                    filePath="src\\main\\python\\ENTOCHS.py "+oriLanguage;
                    break;
            }
            //System.out.println(new File(filePath).exists());//user.dir指定了当前的路径
            System.out.println("python "+filePath);
            Process pr = Runtime.getRuntime().exec("python "+filePath);
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(pr.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
            System.out.println("end");
        }catch (Exception e){
            e.printStackTrace();
        }
        return tarLanguage;
    }
}
