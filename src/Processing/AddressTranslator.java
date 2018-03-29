/**
 * 
 */
package Processing;

import ioHandle.IOhandle;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import tools.MapSort;
import tools.MapSortDouble;
import main.AddressTrans;

/**
 * @author ar-weichang.chen
 * @create-time 2015/02/03 15:04:45
 */
public class AddressTranslator {

	public Set<String> Translator(String Address) {
		// 创建本类对象
		AddressTranslator addressTrans = new AddressTranslator();
		IOhandle ioOhandle = new IOhandle();
		// 读取AddressDic到map
		String InputPathAddressDic = "D:\\Address_Transform\\segment\\AddressDic.txt";
		String ReadLine = "";
		Map<String, Integer> AddressDicMap = new HashMap<String, Integer>();
		try {
			BufferedReader brAddressDic = ioOhandle.FileReader(InputPathAddressDic);
			while ((ReadLine = brAddressDic.readLine()) != null) {
				String[] splite = ReadLine.split(" ");
				if (!AddressDicMap.keySet().contains(splite[0])) {
					AddressDicMap.put(splite[0], Integer.parseInt(splite[1]));
				}

			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 移除数字
		String regex = "[0-9]";
		Address = Address.replaceAll(regex, "");
		// 多个空格合并一个空格
		String regexBlank = "[' ']+";
		Address = Address.replaceAll(regexBlank, "\\+");

		// 把ken，ku 和to前面的权值进行调整
		String[] splite1 = Address.split("\\+");
		for (int i = 0; i < splite1.length - 1; i++) {
			if (AddressDicMap.keySet().contains(splite1[i])) {
				if ((AddressDicMap.get(splite1[i]) == 100 || AddressDicMap.get(splite1[i]) == 50) && (splite1[i + 1].equals("ken") || splite1[i + 1].equals("to") || splite1[i + 1].equals("ku"))) {
					AddressDicMap.put(splite1[i], AddressDicMap.get(splite1[i]) + 1);
				}
			}
		}

		// 将Address中的单元按照权值进行排序

		Address = addressTrans.sort(Address, AddressDicMap);
		System.out.println(Address);

		// 切分address进入单元
		ArrayList<String> addressUnitArrayList = new ArrayList<String>();
		String[] spliteAddress = Address.split(" ");
		// 把address单元读入到arraylist
		for (int i = 0; i < spliteAddress.length; i++) {
			if (!spliteAddress[i].isEmpty()) addressUnitArrayList.add(spliteAddress[i]);
		}

		// 把AddressSequen.txt读入到字典中
		String InputPath = "D:\\Address_Transform\\AddressSequen.txt";

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
		Set<String> addressSet = addDicMap.keySet();
		for (int j = 0; j < addressUnitArrayList.size(); j++) {
			String AddressUnit = addressUnitArrayList.get(j);
			int n = addressSet.size();
			Set<String> tempSet = addressTrans.AddressIter(AddressUnit, addressSet);
			while (tempSet.size() != n) {
				n = tempSet.size();
				tempSet = addressTrans.AddressIter(AddressUnit, addressSet);
			}
			addressSet = tempSet;

		}
		if (addressSet.size() != 1) {
			addressSet = addressTrans.addressScoreSorted(addressSet, addressUnitArrayList, AddressDicMap);
		}

		return addressSet;
	}

	// 给返回的多结果进行排序，选择得分最高的
	public Set<String> addressScoreSorted(Set<String> addressSet, ArrayList<String> addressUnitArrayList, Map<String, Integer> AddressDicMap) {

		// // 创建编辑距离对象
		// EditDistance editDistance = new EditDistance();

		// 创建cosine距离
		CosineDistance cosineDistance = new CosineDistance();

		// 创建map存储距离得分
		Map<String, Double> addressSortMap = new HashMap<String, Double>();

		Iterator it = addressSet.iterator();
		Set<String> SonAddressSet = new HashSet<String>();
		while (it.hasNext()) {
			Double Score = 0.0;
			String Address = (String) it.next();
			// 绝对匹配
			String[] splite = Address.split(" ");
			if (splite[splite.length - 1].length() > 3) {
				StringBuilder lastWord1 = new StringBuilder();
				for (int j = 0; j < splite[splite.length - 1].length(); j++) {
					lastWord1.append(splite[splite.length - 1].charAt(j) + " ");
				}
				for (int i = 0; i < addressUnitArrayList.size(); i++) {
					double scoreTemp = 0.0;
					if (addressUnitArrayList.get(i).length() > 3) {
						StringBuilder AddresUnit = new StringBuilder();
						for (int j = 0; j < addressUnitArrayList.get(i).length(); j++) {
							AddresUnit.append(addressUnitArrayList.get(i).charAt(j) + " ");
						}
						if (AddressDicMap.get(addressUnitArrayList.get(i)) == null || AddressDicMap.get(addressUnitArrayList.get(i)) == 10) {
							scoreTemp = cosineDistance.CosineDistance(AddresUnit.toString(), lastWord1.toString());
							// System.out.println(AddresUnit.toString()+":"+
							// lastWord1.toString()+ ":"+ Score);
							if (Double.isNaN(Score) || scoreTemp < 0.0) {
								Score += 0.0;
							}
							else {
								Score += scoreTemp;
							}
						}

					}

				}
			}
			else {
				StringBuilder lastWord = new StringBuilder();
				for (int j = 0; j < splite[splite.length - 2].length(); j++) {
					lastWord.append(splite[splite.length - 2].charAt(j) + " ");
				}
				for (int i = 0; i < addressUnitArrayList.size(); i++) {
					double scoreTemp = 0.0;
					if (addressUnitArrayList.get(i).length() > 3) {
						StringBuilder AddresUnit = new StringBuilder();
						for (int j = 0; j < addressUnitArrayList.get(i).length(); j++) {
							AddresUnit.append(addressUnitArrayList.get(i).charAt(j) + " ");
						}

						if (AddressDicMap.get(addressUnitArrayList.get(i)) == null || AddressDicMap.get(addressUnitArrayList.get(i)) == 10) {
							scoreTemp = cosineDistance.CosineDistance(AddresUnit.toString(), lastWord.toString());
							// System.out.println(AddresUnit.toString()+":"+
							// lastWord.toString()+ ":"+ Score);
							if (Double.isNaN(Score) || scoreTemp < 0.8) {
								Score += 0.0;
							}
							else {
								Score += scoreTemp;
							}
						}

					}

				}
			}
			if (Double.isNaN(Score)) {
				addressSortMap.put(Address, 0.8);
			}
			else {
				addressSortMap.put(Address, Score);
			}

			// System.out.println(Address + ":" + Score);

		}
		// 按照Value进行排序
		List<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>();
		list.addAll(addressSortMap.entrySet());
		MapSortDouble mapSortDouble = new MapSortDouble();
		Collections.sort(list, mapSortDouble);
		Set<String> returnSet = new HashSet<String>();
		// Iterator it1 = list.iterator();
		// while (it1.hasNext()) {
		// Entry<String, Double> temp = (Entry<String, Double>) it1.next();
		// System.out.println(temp.getKey() + ":" + temp.getValue());
		// }
		if (list.size() >= 3) {
			returnSet.add(list.get(0).getKey());
			returnSet.add(list.get(1).getKey());
			returnSet.add(list.get(2).getKey());
			return returnSet;
		}
		else {
			returnSet.add(list.get(0).getKey());
			return returnSet;
		}

	}

	public Set<String> AddressIter(String AddressUnit, Set<String> addressSet) {

		Iterator it = addressSet.iterator();
		Set<String> SonAddressSet = new HashSet<String>();
		while (it.hasNext()) {
			String Address = (String) it.next();
			// 绝对匹配
			String[] splite = Address.split(" ");
			for (int i = 0; i < splite.length; i++) {
				if (splite[i].equals(AddressUnit)) {
					SonAddressSet.add(Address);
					break;
				}
			}

		}
		if (SonAddressSet.isEmpty()) {
			System.out.println(AddressUnit + ":" + addressSet.size());
			return addressSet;
		}
		else {
			System.out.println(AddressUnit + ":" + SonAddressSet.size());
			return SonAddressSet;
		}
	}

	public String sort(String Address, Map dicMap) {
		Map<String, Integer> sortedDicMap = new HashMap<String, Integer>();
		String[] spliteAddress = Address.split("\\+");
		for (int i = 0; i < spliteAddress.length; i++) {
			if (!dicMap.containsKey(spliteAddress[i])) {
				sortedDicMap.put(spliteAddress[i], 0);
			}
			else {
				sortedDicMap.put(spliteAddress[i], Integer.parseInt(dicMap.get(spliteAddress[i]).toString()));
			}

		}
		// 按照Value进行排序
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>();
		list.addAll(sortedDicMap.entrySet());
		MapSort mapSort = new MapSort();
		Collections.sort(list, mapSort);
		StringBuilder sb = new StringBuilder();
		for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
			Entry<String, Integer> temp = it.next();
			sb.append(temp.getKey().toString() + " ");
		}

		return sb.toString();
	}

}
