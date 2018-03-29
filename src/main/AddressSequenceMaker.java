/**
 * 
 */
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import ioHandle.IOhandle;

/**
 * @author ar-weichang.chen
 * @create-time 2015/02/02 15:56:40
 */
public class AddressSequenceMaker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		IOhandle iOhandle = new IOhandle();
		String InputPath = "D:\\Address_Transform\\KEN_ALL_ROME.txt";
		String OutputPath ="D:\\Address_Transform\\AddressSequen.txt";
		String ReadLine = "";
		Map<String, String> addMap = new TreeMap<String, String>();
		try {
			BufferedReader br = iOhandle.FileReader(InputPath);
			BufferedWriter bw = iOhandle.FileWriter(OutputPath);
		
			while ((ReadLine = br.readLine()) != null) {
				ReadLine = new String(ReadLine.getBytes(), "utf-8");
				ReadLine = ReadLine.replace("\"", "");
				String[] splite = ReadLine.split(",");
				String Key = splite[4].toLowerCase()+" "+splite[5].toLowerCase()+" "+splite[6].toLowerCase();
				String Value = splite[1]+" "+splite[2]+" "+splite[3];
				addMap.put(Key, Value);
			}
			 Iterator<String> it = addMap.keySet().iterator();
			 while(it.hasNext()){
				 String Key = it.next();
				 String Value = addMap.get(Key);
				 
				 bw.write(Key+":"+Value);
				 bw.newLine();
				 bw.flush();
			 }
			 
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
