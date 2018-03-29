/**
 * 
 */
package tools;

/**
 * @author ar-weichang.chen
 * @create-time 2015/02/16 11:23:48
 */
public class FlushLeftFormat {
	public String flushLeft(char c, long length, String content) {
		String str = "";
		long cl = 0;
		String cs = "";
		if (content.length() > length) {
			str = content;
		}
		else {
			for (int i = 0; i < length - content.length(); i++) {
				cs = cs + c;
			}
		}
		str = content + cs;
		return str;
	}

}
