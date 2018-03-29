/**
 * 
 */
package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * @author mangohero1985
 * @create-time Aug 6, 2013 1:25:14 PM
 */
public class InfoSeek {

	public String GoogleResultReturn(String Keyword) throws UnsupportedEncodingException {

		URL url = null;
		String start = "1";
		Keyword = URLEncoder.encode(Keyword, "utf-8");
		try {
			// url = new
			// URL("http://ajax.googleapis.com/ajax/services/search/web?v=1.0&hl=ja&rsz=large&q="+
			// Keyword + "&start=" + start);
			//url = new URL("http://openapi.baidu.com/public/2.0/bmt/translate?client_id=GtG4ybkjGdhjw7yWZyPBbtmQ&q="+Keyword+"&from=en&to=jp");
			url = new URL("http://fanyi.baidu.com/transapi?from=en&to=jp&query="+Keyword);

		}
		
		catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		URLConnection connection = null;
		StringBuilder builder = new StringBuilder();
		String builderStr = "";
		String line;
		BufferedReader reader = null;
		try {
			// 发送请求，读取查询结果
			connection = url.openConnection();
			// connection.addRequestProperty("Referer",
			// "http://www.mysite.com/index.html");
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			builderStr = builder.toString();
          //  System.out.println(builderStr);

		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		/*
		 * q q=Paris%20Hilton 该参数提供了传递到搜索器的查询或搜索表达式。 v v=1.0
		 * 此参数可提供协议版本号。目前唯一有效的值为 1.0。 rsz? rsz=small 该可选参数提供了应用程序要接收的结果数。值
		 * small表示较小的结果集大小或 4 个结果。值 large 表示较大的结果集大小或 8 个结果。如果没有提供此参数，将假定值为
		 * small。 hl? hl=fr 此可选参数提供了提出请求的应用程序的宿主语言。如果未提供此参数，系统将根据
		 * Accept-Language http标头的值选择一个值。如果此标题未显示，将假定值为 en。 key? key=your-key
		 * 此可选参数提供了应用程序的密钥。如果指定了此参数
		 * ，则此密钥必须是与您的网站（已通过传递的参考标头进行确认）关联的有效密钥。提供密钥的优点在于，我
		 * 们可以在您的应用程序出错时识别并联系您。如果没有密钥，我们仍会采取相同的适当措施，但是我们将无法联系您。强烈建议您最好提供一个密钥。
		 * start? start=4 该可选参数提供了第一个搜索结果的起始索引。每个成功的响应都包含了一个 cursor
		 * 对象（请参见下文），该对象包括一个 pages 的数组。页面的 start 属性可以用作该参数的有效值。
		 */
		return builderStr;
	}
}
