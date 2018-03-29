/**
 * 
 */
package main;

import ioHandle.CSVreader;
import ioHandle.ExcelReader;
import ioHandle.IOhandle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import Processing.AddressClassification;
import Processing.AddressTranslator;
import Processing.KanjiHiraChecker;

/**
 * @author ar-weichang.chen
 * @create-time 2015/01/28 10:04:12
 */
public class AddressTransMain {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// 地址分类成四个文件
		// String inputPath ="D:\\Address_Transform\\address.txt";
		// AddressClassification addressClassification = new
		// AddressClassification();
		// addressClassification.classify(inputPath);

		// // 测试读取csv文件
		// CSVreader csVreader = new CSVreader();
		//
		// ArrayList<String> ar =
		// csVreader.reader("D:\\Address_Transform\\KEN_ALL_ROME.CSV", 5);
		// System.out.println();

		// 把AddressSequen.txt读入到字典中
		IOhandle ioOhandle = new IOhandle();
		String InputPath = "D:\\Address_Transform\\AddressSequen.txt";
		String OutputPath = "D:\\Address_Transform\\AddressTransform.txt";
		String InputPathOriginal = "D:\\Address_Transform\\JapaneseAddressWithAlphabet.txt";
		ArrayList<String> textArrayList = new ArrayList<String>();
		BufferedReader brOriginal = ioOhandle.FileReader(InputPathOriginal);
		BufferedWriter bw = ioOhandle.FileWriter(OutputPath);
		String ReadLine = "";

		while ((ReadLine = brOriginal.readLine()) != null) {
			textArrayList.add(ReadLine);
		}
		Map<String, String> addDicMap = new HashMap<String, String>();
		try {
			BufferedReader br = ioOhandle.FileReader(InputPath);
			while ((ReadLine = br.readLine()) != null) {
				String[] splite = ReadLine.split(":");
				addDicMap.put(splite[0], splite[1]);
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String InputPathAddress = "D:\\Address_Transform\\JapanAddressAlphaProceed.txt";
		Set<String> addressSet = new HashSet<String>();
		AddressTranslator addressTranslator = new AddressTranslator();
		try {
			int i = 0;
			BufferedReader br = ioOhandle.FileReader(InputPathAddress);
			while ((ReadLine = br.readLine()) != null) {
				System.out.println(ReadLine);
				addressSet = addressTranslator.Translator(ReadLine);
				if (addressSet.size() > 50) {
					bw.write(textArrayList.get(i) + ": CAN NOT FIND EXACT ADRRESS");
					bw.newLine();
					bw.flush();
					i++;
				}
				else {
					Iterator it = addressSet.iterator();
					Set<String> set = new HashSet<String>();
					while (it.hasNext()) {
						String key = (String) it.next();
						set.add(addDicMap.get(key));
						System.out.println(ReadLine + "+" + addDicMap.get(key));
					}

					bw.write(textArrayList.get(i) + ":" + set);
					bw.newLine();
					bw.flush();
					i++;
				}
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
