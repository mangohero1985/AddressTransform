/**
 * 
 */
package main;

import ioHandle.CSVreader;
import ioHandle.IOhandle;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author ar-weichang.chen
 * @create-time 2015/02/02 13:36:53
 */
public class AddressDicMaker {
	public static void main(String[] args) {

		String OutputPath = "D:\\Address_Transform\\AddressDic.txt";

		CSVreader csVreader = new CSVreader();
		// 读取县名
		ArrayList<String> arPrefecture = csVreader.reader("D:\\Address_Transform\\KEN_ALL_ROME.CSV", 5);
		// 读取市名和区名
		ArrayList<String> arCityAndDistrict = csVreader.reader("D:\\Address_Transform\\KEN_ALL_ROME.CSV", 6);
		// 读取町名
		ArrayList<String> arCho = csVreader.reader("D:\\Address_Transform\\KEN_ALL_ROME.CSV", 7);

		AddressDicMaker addressDicMaker = new AddressDicMaker();
		addressDicMaker.TextFormat(arPrefecture, OutputPath, 100);
		addressDicMaker.TextFormat(arCityAndDistrict, OutputPath, 50);
		addressDicMaker.TextFormat(arCho, OutputPath, 10);
	}

	public void TextFormat(ArrayList<String> ar, String OutputPath, Integer Weight) {

		IOhandle iOhandle = new IOhandle();
		ArrayList<String> arWODupli = new ArrayList<String>();
		try {
			BufferedWriter bw = iOhandle.FileWriterContinue(OutputPath);
			for (int i = 0; i < ar.size(); i++) {
				String temp = ar.get(i).toLowerCase();
				if (temp.contains("\"")) {
					temp = temp.replace("\"", "");
				}
				if (temp.contains("(")) {

					temp = temp.substring(0, temp.lastIndexOf("("));

				}
				if (temp.contains("-")) {
					temp = temp.replace("-", " ");
				}
				if (temp.contains(" ")) {
					String[] splite = temp.toString().split(" ");
					for (int j = 0; j < splite.length; j++) {
						if (!arWODupli.contains(splite[j])) {
							arWODupli.add(splite[j]);
						}
					}
				}
				else {
					if (!arWODupli.contains(temp)) {
						arWODupli.add(temp);
					}
				}

			}
			for (int k = 0; k < arWODupli.size(); k++) {

				if (!arWODupli.get(k).isEmpty()) {
					bw.write(arWODupli.get(k) + " " + Weight);
					bw.newLine();
					bw.flush();
				}

			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
