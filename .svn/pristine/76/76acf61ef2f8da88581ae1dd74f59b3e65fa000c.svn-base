/**
 * 
 */
package Processing;

import ioHandle.IOhandle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import tools.CallPy;

/**
 * @author ar-weichang.chen
 * @create-time 2015/02/18 10:36:44
 */
public class AddressFormate {

	public String Format(String address) {
		IOhandle iOhandle = new IOhandle();
		// 读取AddressDic到map
		String InputPathAddressDic = "D:\\Address_Transform\\AddressDic.txt";
		String ReadLine = "";
		Map<String, Double> AddressDicMap = new HashMap<String, Double>();
		try {
			BufferedReader brAddressDic = iOhandle.FileReader(InputPathAddressDic);
			while ((ReadLine = brAddressDic.readLine()) != null) {
				String[] splite = ReadLine.split(" ");
				if (!AddressDicMap.keySet().contains(splite[0])) {
					AddressDicMap.put(splite[0], Double.parseDouble(splite[1]));
				}

			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// String InputPath =
		// "D:\\Address_Transform\\JapaneseAddressWithAlphabet.txt";
		// String OutputPath =
		// "D:\\Address_Transform\\JapanAddressAlphaProceed.txt";
		String pyPath = "D:\\Address_Transform\\segment\\segment.py";
		String funcName = "segmentWithProb";
		CallPy callPy = new CallPy();
		// 判断一个非数字和非字母
		String regex = "[^a-zA-Z0-9]";
		// 多个空格合并一个空格
		String regexBlank = "[' ']+";
		// try {
		// BufferedReader br = iOhandle.FileReader(InputPath);
		// BufferedWriter bw = iOhandle.FileWriter(OutputPath);
		// while ((ReadLine = br.readLine()) != null) {
		ReadLine = address.replaceAll(regex, " ");
		// String Result = callPy.callWithPara(pyPath, funcName,
		// ReadLine);
		// Result = Result.replaceAll(regex, " ");
		// Result = Result.replaceAll(regexBlank, " ");
		// bw.write(Result);
		// bw.newLine();
		// bw.flush();
		// 先整体解析一遍
		String Result = callPy.callWithPara(pyPath, funcName, ReadLine);
		Result = Result.replaceAll(regex, " ");
		String Result1 = Result.replaceAll(regexBlank, " ");
		// 再单独解析
		String[] splite = ReadLine.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < splite.length; i++) {
			java.util.Iterator<String> it = AddressDicMap.keySet().iterator();
			while (it.hasNext()) {
				String temp = it.next();
				if (splite[i].contains(temp)) {
					sb.append(callPy.callWithPara(pyPath, funcName, splite[i]) + " ");
					break;
				}
			}

		}

		String Result2 = sb.toString().replaceAll(regex, " ");
		Result2 = Result2.replaceAll(regexBlank, " ");
		System.out.println(Result2);
		System.out.println(Result1 + " " + Result2);
		// bw.write(Result1 + " " + Result2);
		// bw.newLine();
		// bw.flush();

		return Result1+Result2;
	}

	// }
	// catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
}
