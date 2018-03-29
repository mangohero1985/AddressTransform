/**
 * 
 */
package ioHandle;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author ar-weichang.chen
 * @create-time 2015/02/02 13:26:12
 */
public class CSVreader {

	public ArrayList<String> reader(String InputPath, Integer column) {

		IOhandle iOhandle = new IOhandle();
		String ReadLine = "";
		ArrayList<String> ar = new ArrayList<String>();

		try {
			BufferedReader br = iOhandle.FileReader(InputPath);
			while ((ReadLine = br.readLine()) != null) {

				String[] splite =ReadLine.split(",");
				if(!ar.contains(splite[column-1])){
					ar.add(splite[column-1]);
				}
			}

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ar;
	}

}
