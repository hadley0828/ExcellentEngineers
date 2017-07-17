package bl;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import java.io.File;

/**
 * Created by 朱应山 on 2017/7/14.
 */
public class Jython {
    public String runPythonTrans(String oriLanguage) {
        String tarLanguage="";
        PythonInterpreter interpreter = new PythonInterpreter();
        String pythonPath="src/main/python/test.py";
        interpreter.execfile(pythonPath);
        PyFunction func = (PyFunction) interpreter.get("translate", PyFunction.class);
        PyObject pyobj = func.__call__(new PyString(oriLanguage));
        tarLanguage=pyobj.toString();
        return tarLanguage;
    }

}
