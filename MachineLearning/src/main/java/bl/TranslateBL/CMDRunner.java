package bl.TranslateBL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

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
                    filePath="/Users/loohaze/Documents/SummerCourse/ExcellentEngineers/MachineLearning/src/main/python/translate.py "+ oriLanguage;
                    break;
                case ENTOCHS:
                    filePath="src\\main\\python\\ENTOCHS.py "+oriLanguage;
                    break;
            }
            //System.out.println(new File(filePath).exists());//user.dir指定了当前的路径
            System.out.println("python3.6 "+filePath);

            Runtime rt = Runtime.getRuntime();
            Process pr;
//            pr = rt.exec("source activate python3");
            pr = rt.exec("python3.6 " + filePath);

            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream(), Charset.forName("utf-8")));
            BufferedReader error = new BufferedReader(new InputStreamReader(pr.getErrorStream()));


            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }


            String errorline;
            while ((errorline = error.readLine()) != null){
                System.out.println(errorline);
            }


            in.close();
            System.out.println("end");
        }catch (Exception e){
            e.printStackTrace();
        }
        return tarLanguage;
    }
}
