/**
 * 
 */
package main;

import ioHandle.IOhandle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.python.antlr.PythonParser.return_stmt_return;

import tools.CallPy;
import tools.FlushLeftFormat;
import Processing.AddressClassification;

/**
 * @author ar-weichang.chen
 * @create-time 2015/02/16 10:20:58
 */
public class AddressTransPostCode {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// int i =25;
		// System.out.println(new DecimalFormat("0000").format(i));

		// 地址按文字或字母分类
		// String inputPath =
		// "D:\\Address_Transform_PostCode\\black_address_eng.txt";
		// AddressClassification addressClassification = new
		// AddressClassification();
		// addressClassification.classify(inputPath);

		// postCode格式化生成标准格式
		ArrayList<String> blackAddreArrayList = new ArrayList<String>();
		ArrayList<String> postCodeArrayList = new ArrayList<String>();

		String InputPath = "D:\\Address_Transform_PostCode\\JapaneseAddressWithAlphabet.txt";
		String OutputPath = "D:\\Address_Transform_PostCode\\AlphabetAddrWithFormalPost.txt";
		IOhandle iOhandle = new IOhandle();
		BufferedReader br = iOhandle.FileReader(InputPath);
		BufferedWriter bw = iOhandle.FileWriter(OutputPath);
		String ReadLine = "";
		while ((ReadLine = br.readLine()) != null) {
			ReadLine = ReadLine.replace("			", "	");
			String[] splite = ReadLine.split("	");
			String postCode1 = new DecimalFormat("000").format(Integer.parseInt(splite[1]));
			String postCode2 = new DecimalFormat("0000").format(Integer.parseInt(splite[2]));
			blackAddreArrayList.add(splite[3]);
			postCodeArrayList.add(postCode1 + postCode2);
			bw.write(postCode1 + postCode2 + "	" + splite[3]);
			bw.newLine();
			bw.flush();

		}

		// 地址分词成基本单元
		// 读取AddressDic到map
		String InputPathAddressDic = "D:\\Address_Transform\\AddressDic.txt";
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
		// 输出区以下的地址到文件
		String OutputPathNarrowZone = "D:\\Address_Transform_PostCode\\NarrowZoneAddreess.txt";
		String pyPath = "D:\\Address_Transform\\segment\\segment.py";
		String funcName = "segmentWithProb";
		CallPy callPy = new CallPy();
		// 判断一个非数字和非字母
		String regex = "[^a-zA-Z0-9]";
		// 多个空格合并一个空格
		String regexBlank = "[' ']+";
		String ResultSequence = "";
		ArrayList<String> addressArrayList = new ArrayList<String>();
		try {
			// BufferedReader br = iOhandle.FileReader(InputPath);
			BufferedWriter bwNarrowZone = iOhandle.FileWriter(OutputPathNarrowZone);
			Iterator it1 = blackAddreArrayList.iterator();
			while (it1.hasNext()) {
				String address = it1.next().toString();
				ReadLine = address;
				ReadLine = ReadLine.replaceAll(regex, " ");
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
				ResultSequence = Result1 + " " + Result2;
				addressArrayList.add(ResultSequence);

			}

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		// 移除字典中已经包含的text，获得剩下的text
		// 把字典读入到arralist里

		String InputPathAddressDic1 = "D:\\Address_Transform_PostCode\\AddressDic.txt";
		ArrayList<String> dicArrayList = new ArrayList<String>();

		try {
			BufferedReader brAddressDic = iOhandle.FileReader(InputPathAddressDic1);
			while ((ReadLine = brAddressDic.readLine()) != null) {
				dicArrayList.add(ReadLine);

			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 把右边地名词典读入到map里
		BufferedReader brPostAddress = iOhandle.FileReader("D:\\Address_Transform_PostCode\\KEN_ALL_ROME.txt");
		Map<String, String> postAddresMap = new TreeMap<String, String>();
		while ((ReadLine = brPostAddress.readLine()) != null) {
			ReadLine = ReadLine.replaceAll("\"", "");
			String[] splite = ReadLine.split(",");
			postAddresMap.put(splite[0], splite[1] + splite[2] + splite[3]);

		}
		String OutputPathResult = "D:\\Address_Transform_PostCode\\Result.txt";
		BufferedWriter bwSmallZone = iOhandle.FileWriter(OutputPathResult);
		// 移除解析的字符串中的包含
		for (int i = 0; i < addressArrayList.size(); i++) {
			System.out.println(postAddresMap.get(postCodeArrayList.get(i)));
			StringBuilder sb = new StringBuilder();
			String[] splite = addressArrayList.get(i).split(" ");
			for (int j = 0; j < splite.length; j++) {
				// System.out.println(splite[j]);
				int flag = 0;
				for (int k = 0; k < dicArrayList.size(); k++) {
					if (splite[j].contains(dicArrayList.get(k))) {
						flag++;
						break;
					}
				}
				if (flag == 0) {
					if (!sb.toString().contains(splite[j])) sb.append(splite[j] + " ");
				}
			}
			bwSmallZone.write(postAddresMap.get(postCodeArrayList.get(i)) + "---" + sb);
			bwSmallZone.newLine();
			bwSmallZone.flush();
		}
	}

}
