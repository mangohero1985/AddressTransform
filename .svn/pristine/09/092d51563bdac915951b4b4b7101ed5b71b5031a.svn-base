/**
 * 
 */
package tools;

import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

/**
 * @author ar-weichang.chen
 * @create-time     2015/02/03   11:08:50   
 */
public class CallPy {
	public String callWithPara(String pyPath,String funcName,String parameter){
		
		PythonInterpreter interpreter = new PythonInterpreter();
		
		interpreter.execfile(pyPath);
		PyFunction func = (PyFunction) interpreter.get(funcName, PyFunction.class);

		String Address = parameter;
		PyObject pyobj = func.__call__(new PyString(Address));
		return pyobj.toString();
	}

}
