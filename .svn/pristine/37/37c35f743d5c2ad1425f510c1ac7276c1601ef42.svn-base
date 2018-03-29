/**
 * 
 */
package tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ar-weichang.chen
 * @create-time 2015/02/16 15:53:19
 */
public class SpliteNumAndAlpha {
	public String splite(String str) {

		if (str.contains("-")) {
			str = str.replace("-", "9911199");
		}
		if (str.contains(".")) {
			str = str.replace(".", "9009");
		}

		Pattern p = Pattern.compile("[(\\d[a-zA-Z])[([a-zA-Z]\\d)][\\d][a-zA-Z]]+");
		Matcher m = p.matcher(str);
		String Str = "";
		while (m.find()) {
			if (m.group().matches("\\d+\\b|[a-zA-Z]+\\b")) {
				Str = Str + m.group() + " ";
			}
			else {
				p = Pattern.compile("\\d+|[a-zA-Z]+");
				Matcher mm = p.matcher(m.group());
				while (mm.find()) {
					Str = Str + mm.group() + " ";
				}
			}
		}
		Str = Str.replace("9911199", "-");
		Str = Str.replace("9009", ".");
		return Str;

	}

}
