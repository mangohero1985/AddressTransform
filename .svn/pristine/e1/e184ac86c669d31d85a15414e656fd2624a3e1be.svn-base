/**
 * 
 */
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ioHandle.IOhandle;

/**
 * @author ar-weichang.chen
 * @create-time 2015/02/04 15:50:19
 */
public class testNomeaning {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		IOhandle iOhandle = new IOhandle();
		String InputPath1 = "D:\\Address_Transform\\AddressDic.txt";
		String InputPath2 = "D:\\Address_Transform\\dic10.txt";
		String OutputPath = "D:\\Address_Transform\\dic10New.txt";
		BufferedReader br1 = iOhandle.FileReader(InputPath1);
		BufferedReader br2 = iOhandle.FileReader(InputPath2);
		BufferedWriter bw = iOhandle.FileWriter(OutputPath);

		ArrayList<String> ar1 = new ArrayList<String>();
		ArrayList<String> ar2 = new ArrayList<String>();

		String readString = "";
		while ((readString = br1.readLine()) != null) {
			ar1.add(readString.substring(0, readString.indexOf(" ")));

		}
		while ((readString = br2.readLine()) != null) {
			ar2.add(readString.substring(0, readString.indexOf(" ")));

		}

			for (int j = 0; j < ar2.size(); j++) {

				if(!ar1.contains(ar2.get(j))){
					bw.write(ar2.get(j)+" "+10);
					bw.newLine();
					bw.flush();
				}
			}

		
	}

}
