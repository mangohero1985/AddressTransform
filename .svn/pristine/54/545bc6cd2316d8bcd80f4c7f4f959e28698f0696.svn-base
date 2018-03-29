/**
 * 
 */
package Processing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ar-weichang.chen
 * @create-time 2015/01/28 10:14:28
 */
public class KanjiHiraChecker {

	public int check(String address) {

		// 检查地址中是否包含汉字，平假名，片假名
		String regEx = "[^\\p{InHiragana}\\p{InKatakana}\\p{InCJKUnifiedIdeographs}+]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(address);
		if (!m.replaceAll("").trim().isEmpty()) {
			return 1;
		}
		else {
			return 0;
		}

	}

}
