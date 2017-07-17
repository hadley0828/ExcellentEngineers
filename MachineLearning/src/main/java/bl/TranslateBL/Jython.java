package bl.TranslateBL;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

/**
 * Created by 朱应山 on 2017/7/14.
 */
public class Jython {
    public String runPythonTrans(String oriLanguage,Translation translation) {
        String pythonPath="";
        switch (translation){
            case CHSTOEN:
                pythonPath="src/main/python/CHSTOEN.py";
                break;
            case ENTOCHS:
                pythonPath="src/main/python/ENTOCHS.py";
                break;
            default:
                return "failed";
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
