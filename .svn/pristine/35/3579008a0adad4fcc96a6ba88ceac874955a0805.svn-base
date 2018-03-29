package main;

import ioHandle.IOhandle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URLEncoder;

import tools.InfoSeek;
import tools.TranslateMode;
import Processing.BaiduTranslator;

import com.google.gson.Gson;

public class TestBaiduTranslator {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		BaiduTranslator baiduTranslator = new BaiduTranslator();

		IOhandle iOhandle = new IOhandle();
		String InputPath = "D:\\Address_Transform\\JapaneseAddressWithAlphabet.txt";
		String OutputPath = "D:\\Address_Transform\\BaiduJanAddressFromAlpha.txt";

		BufferedReader br = iOhandle.FileReader(InputPath);
		BufferedWriter bw = iOhandle.FileWriter(OutputPath);
		String ReadLine = "";
		while ((ReadLine = br.readLine()) != null) {
			
			String dst = baiduTranslator.translate(ReadLine);
			System.out.println(dst);
			if(!dst.isEmpty()){
				bw.write(dst);
				bw.newLine();
				bw.flush();	
			}else{
				bw.write("null");
				bw.newLine();
				bw.flush();
			}
			
		}

		

	}

}