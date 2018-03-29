/**
 * 
 */
package main;

import ioHandle.IOhandle;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author ar-weichang.chen
 * @create-time 2015/02/03 13:25:26
 */
public class AddressTrans {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String Address = " kamiyabecho u rockrest no 302 yokohama totsuka kanagawa japan ";
		// 移除数字
		String regex = "[0-9]";
		Address = Address.replaceAll(regex, "");
		// 多个空格合并一个空格
		String regexBlank = "[' ']+";
		Address = Address.replaceAll(regexBlank, " ");
		ArrayList<String> addressUnitArrayList = new ArrayList<String>();
		String[] spliteAddress = Address.split(" ");
		AddressTrans addressTrans = new AddressTrans();
		// 把address单元读入到arraylist
		for (int i = spliteAddress.length - 1; i > 0; i--) {
			addressUnitArrayList.add(spliteAddress[i]);
		}

		// 把AddressSequen.txt读入到字典中
		IOhandle ioOhandle = new IOhandle();
		String InputPath = "D:\\Address_Transform\\AddressSequen.txt";
		String ReadLine = "";
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
		if (addressSet.size() > 50) {
			System.out.println("can not find exact address");
		}
		else {
			Iterator it = addressSet.iterator();
			while(it.hasNext()){
				String key = (String) it.next();
				System.out.println(addDicMap.get(key));
			}
			
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

}
