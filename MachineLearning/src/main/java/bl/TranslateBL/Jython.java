package bl.TranslateBL;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import java.io.File;
import java.io.IOException;

/**
 * Created by 朱应山 on 2017/7/14.
 */
public class Jython {
    public String runPythonTrans(String oriLanguage,Translation translation) {
        String pythonPath="";
        switch (translation){
            case CHSTOEN:
                pythonPath="MachineLearning/src/main/python/CHSTOEN.py";
                break;
            case ENTOCHS:
                pythonPath="MachineLearning/src/main/python/ENTOCHS.py";
                break;
            default:
                return "failed";
        }
        File directory = new File("");//设定为当前文件夹
        try {
            System.out.println(directory.getCanonicalPath());//获取标准的路径
            System.out.println(directory.getAbsolutePath());//获取绝对路径
        } catch (IOException e) {
            e.printStackTrace();
        }
        String tarLanguage="";
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile(pythonPath);
        PyFunction func = (PyFunction) interpreter.get("translate", PyFunction.class);
        PyObject pyobj = func.__call__(new PyString(oriLanguage));
        tarLanguage=pyobj.toString();
        return tarLanguage;
    }

}
