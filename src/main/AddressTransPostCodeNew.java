/**
 * 
 */
package main;

import ioHandle.IOhandle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tools.SpliteNumAndAlpha;
import Processing.AddressFormate;
import Processing.AddressTranslator;

/**
 * @author ar-weichang.chen
 * @create-time 2015/02/16 14:34:22
 */
public class AddressTransPostCodeNew {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// postCode格式化生成标准格式
		ArrayList<String> blackAddreArrayList = new ArrayList<String>();
		ArrayList<String> postCodeArrayList = new ArrayList<String>();

		String InputPath = "D:\\Address_Transform_PostCode\\JapaneseAddressWithAlphabet.txt";
		String OutputPath = "D:\\Address_Transform_PostCode\\AlphabetAddrWithFormalPost.txt";

		String OutputPathResult = "D:\\Address_Transform_PostCode\\Result1.txt";

		IOhandle iOhandle = new IOhandle();
		BufferedReader br = iOhandle.FileReader(InputPath);
		BufferedWriter bw = iOhandle.FileWriter(OutputPath);
		BufferedWriter bwSmallZone = iOhandle.FileWriter(OutputPathResult);
		String ReadLine = "";
		ArrayList<String> originalArrayList = new ArrayList<String>();

		while ((ReadLine = br.readLine()) != null) {
			ReadLine = ReadLine.replace("			", "	");
			String[] splite = ReadLine.split("	");
			String postCode1 = new DecimalFormat("000").format(Integer.parseInt(splite[1]));
			String postCode2 = new DecimalFormat("0000").format(Integer.parseInt(splite[2]));
			SpliteNumAndAlpha spliteNumAndAlpha = new SpliteNumAndAlpha();
			String str = spliteNumAndAlpha.splite(splite[3]);
			System.out.println(str);
			// String str = splite[3];
			blackAddreArrayList.add(str);
			postCodeArrayList.add(postCode1 + postCode2);
			originalArrayList.add(ReadLine);
			bw.write(postCode1 + postCode2 + "	" + str);
			bw.newLine();
			bw.flush();

		}

		ArrayList<String> addressArrayList = blackAddreArrayList;

		// 把右边地名词典读入到map里
		BufferedReader brPostAddress = iOhandle.FileReader("D:\\Address_Transform_PostCode\\KEN_ALL_ROME.txt");
		Map<String, String> postAddresMap = new TreeMap<String, String>();
		Map<String, ArrayList<String>> postRomajiMap = new TreeMap<String, ArrayList<String>>();
		while ((ReadLine = brPostAddress.readLine()) != null) {
			ArrayList<String> dicArrayList = new ArrayList<String>();
			ReadLine = ReadLine.replaceAll("\"", "");
			String[] splite = ReadLine.split(",");
			postAddresMap.put(splite[0], splite[1] + splite[2] + splite[3]);
			String ken = splite[4].toLowerCase();
			String ku = splite[5].toLowerCase();
			String cho = splite[6].toLowerCase();
			if (!ken.contains(" ")) {
				if (!dicArrayList.contains(ken)) {
					dicArrayList.add(ken);
				}
			}
			else {
				String[] splite1 = ken.split(" ");
				for (int i = 0; i < splite1.length; i++) {
					if (!dicArrayList.contains(splite1[i])) {
						dicArrayList.add(splite1[i]);
					}
				}
			}
			if (!ku.contains(" ")) {
				if (!dicArrayList.contains(ku)) {
					dicArrayList.add(ku);
				}
			}
			else {
				String[] splite1 = ku.split(" ");
				for (int i = 0; i < splite1.length; i++) {
					if (!dicArrayList.contains(splite1[i])) {
						dicArrayList.add(splite1[i]);
					}
				}
			}
			if (!cho.contains(" ")) {
				if (!dicArrayList.contains(cho)) {
					dicArrayList.add(cho);
				}
			}
			else {
				String[] splite1 = cho.split(" ");
				for (int i = 0; i < splite1.length; i++) {
					if (!dicArrayList.contains(splite1[i])) {
						dicArrayList.add(splite1[i]);
					}
				}
			}
			postRomajiMap.put(splite[0], dicArrayList);

		}

		String InputPathDictionary = "D:\\Address_Transform\\AddressSequen.txt";
		BufferedReader brDictionary = iOhandle.FileReader(InputPathDictionary);
		Map<String, String> addDicMap = new HashMap<String, String>();
		while ((ReadLine = brDictionary.readLine()) != null) {
			String[] splite = ReadLine.split(":");
			addDicMap.put(splite[0], splite[1]);
		}
		// 移除解析的字符串中的包含
		AddressTranslator addressTranslator = new AddressTranslator();
		for (int i = 0; i < addressArrayList.size(); i++) {
			if (postAddresMap.get(postCodeArrayList.get(i)) == null) {
				AddressFormate addressFormate = new AddressFormate();
				String temp = addressFormate.Format(addressArrayList.get(i));
				Set<String> addressSet = addressTranslator.Translator(temp);
				Iterator it = addressSet.iterator();
				Set<String> set = new HashSet<String>();
				if (!it.hasNext()) {
					set.add(addDicMap.get(addressSet.toString().substring(1, addressSet.toString().length() - 1)));
				}
				else {
					while (it.hasNext()) {
						String key = (String) it.next();
						set.add(addDicMap.get(key));
						// System.out.println(ReadLine + "+" +
						// addDicMap.get(key));
					}
				}
				// 通过地址获取邮编 和地址
				Iterator it1 = postAddresMap.keySet().iterator();
				String postCode = "";
				String AddressTemp = set.toString().substring(1, set.toString().length() - 1).replace(" ", "");
				while (it1.hasNext()) {
					String key = (String) it1.next();
					// System.out.println(postAddresMap.get(key));
					if (postAddresMap.get(key).equals(AddressTemp)) {
						postCode = key;
						break;
					}
				}
				StringBuilder sb = new StringBuilder();
				String[] splite = addressArrayList.get(i).toLowerCase().split(" ");
				System.out.println(postRomajiMap.get(postCode));
				ArrayList<String> dicArrayListUnit = postRomajiMap.get(postCode);
				for (int j = 0; j < splite.length; j++) {
					// System.out.println(splite[j]);
					int flag = 0;
					for (int k = 0; k < dicArrayListUnit.size(); k++) {
						if (splite[j].contains(dicArrayListUnit.get(k))) {
							flag++;
							break;
						}
					}
					if (flag == 0) {
						String[] splite2 = sb.toString().split(" ");
						int flag1 = 0;
						for (int k = 0; k < splite2.length; k++) {
							if (splite2[k].equals(splite[j])) flag1++;
						}
						if (flag1 == 0) {
							sb.append(splite[j] + " ");
						}

					}
				}

				// bwSmallZone.write("<" + originalArrayList.get(i) + ">" +
				// "<NA>" + "<" +postCode +" "+ set.toString().substring(1,
				// set.toString().length()-1) + " "+ sb+">");
				bwSmallZone.write(postCode + " " + set.toString().substring(1, set.toString().length() - 1) + ":::" + sb);
				bwSmallZone.newLine();
				bwSmallZone.flush();
				continue;
			}
			System.out.println(postAddresMap.get(postCodeArrayList.get(i)));
			StringBuilder sb = new StringBuilder();
			String[] splite = addressArrayList.get(i).toLowerCase().split(" ");
			System.out.println(postRomajiMap.get(postCodeArrayList.get(i)));
			ArrayList<String> dicArrayListUnit = postRomajiMap.get(postCodeArrayList.get(i));
			for (int j = 0; j < splite.length; j++) {
				// System.out.println(splite[j]);
				int flag = 0;
				for (int k = 0; k < dicArrayListUnit.size(); k++) {
					if (splite[j].contains(dicArrayListUnit.get(k))) {
						flag++;
						break;
					}
				}
				if (flag == 0) {
					String[] splite2 = sb.toString().split(" ");
					int flag1 = 0;
					for (int k = 0; k < splite2.length; k++) {
						if (splite2[k].equals(splite[j])) flag1++;
					}
					if (flag1 == 0) {
						sb.append(splite[j] + " ");
					}

				}
			}
			bwSmallZone.write(postCodeArrayList.get(i) + " " + postAddresMap.get(postCodeArrayList.get(i)) + ":::" + sb);
			bwSmallZone.newLine();
			bwSmallZone.flush();
		}

		// 按要求把 数字-数字-数字的地址放到最前面

		String InputPathResult1 = "D:\\Address_Transform_PostCode\\Result1.txt";
		String OutputPathResult1Format = "D:\\Address_Transform_PostCode\\Result1Format.txt";
		BufferedReader brFormat = iOhandle.FileReader(InputPathResult1);
		BufferedWriter bwFormat = iOhandle.FileWriter(OutputPathResult1Format);
		int num = 0;
		while ((ReadLine = brFormat.readLine()) != null) {
			ReadLine = ReadLine.replace(" chome", "-");
			ReadLine = ReadLine.replace(" chou", "-");
			if (ReadLine.contains(":::")) {
				String[] Splite = ReadLine.split(":::");
				String address = Splite[0];
				String smallZone = Splite[1];
				String[] SpliteSmallZone = smallZone.split(" ");
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < SpliteSmallZone.length; i++) {
					Pattern p = Pattern.compile("[0-9]-");
					Matcher m = p.matcher(SpliteSmallZone[i]);
					Pattern p1 = Pattern.compile("[0-9]-[0-9]");
					Matcher m1 = p1.matcher(SpliteSmallZone[i]);
					if (m.find() && !m1.find()) {
						sb.append(SpliteSmallZone[i] + " ");
					}
				}
				for (int i = 0; i < SpliteSmallZone.length; i++) {
					Pattern p = Pattern.compile("[0-9]-[0-9]");
					Matcher m = p.matcher(SpliteSmallZone[i]);
					if (m.find()) {
						sb.append(SpliteSmallZone[i] + " ");
					}
				}
				for (int i = 0; i < SpliteSmallZone.length; i++) {
					if (!SpliteSmallZone[i].toString().contains("-")) {
						sb.append(SpliteSmallZone[i] + " ");
					}
				}
				String temp = sb.toString().replace("japan", "");
				bwFormat.write("<" + originalArrayList.get(num) + ">" + "<" + address + " " + temp + ">" + "<NA>");
				bwFormat.newLine();
				bwFormat.flush();
				num++;
			}
			else {
				bwFormat.write(ReadLine);
				bwFormat.newLine();
				bwFormat.flush();
				num++;
			}

		}

	}

}
