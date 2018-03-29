/**
 * 
 */
package Processing;

import ioHandle.IOhandle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.Normalizer;

/**
 * @author ar-weichang.chen
 * @create-time 2015/01/28 11:01:43
 */
public class AddressClassification {

	public void classify(String inputPath) {

		IOhandle iOhandle = new IOhandle();
		String InputPath = inputPath;
		String OutputPathJanKanji = "D:\\Address_Transform_PostCode\\JapaneseAddressWithKanji.txt";
		String OutputPathJanAlpha = "D:\\Address_Transform_PostCode\\JapaneseAddressWithAlphabet.txt";
		String OutputPathAbroadKanji = "D:\\Address_Transform_PostCode\\AbroadAddressWithKanji.txt";
		String OutputPathAbroadAlpha = "D:\\Address_Transform_PostCode\\AbroadAddressWithAlphabet.txt";
		String address = "";
		String readLine = "";
		KanjiHiraChecker kanjiHiraChecker = new KanjiHiraChecker();
		try {
			BufferedReader br = iOhandle.FileReader(InputPath);
			BufferedWriter bwJanKanji = iOhandle.FileWriter(OutputPathJanKanji);
			BufferedWriter bwJanAlpha = iOhandle.FileWriter(OutputPathJanAlpha);
			BufferedWriter bwAbroadKanji = iOhandle.FileWriter(OutputPathAbroadKanji);
			BufferedWriter bwAbroadAlpha = iOhandle.FileWriter(OutputPathAbroadAlpha);
			while ((readLine = br.readLine()) != null) {
				address = Normalizer.normalize(readLine, Normalizer.Form.NFKC);
				// 找到所有包含汉字或者假名的地址，判断这些地址是日本还是海外
				if (kanjiHiraChecker.check(address) == 1) {
					// 判断汉字中的海外或者日本
					if (address.contains("中國") || address.contains("中国") || address.contains("台湾") || address.contains("国外") || address.contains("韓国") || address.contains("China")) {
						bwAbroadKanji.write(address);
						bwAbroadKanji.newLine();
						bwAbroadKanji.flush();
					}
					else {
						bwJanKanji.write(address);
						bwJanKanji.newLine();
						bwJanKanji.flush();
					}

				}
				else {
					// 所有纯字母的地址中，属于日本或者国外

					if (address.contains("Japan") || address.contains("japan")) {
						bwJanAlpha.write(address);
						bwJanAlpha.newLine();
						bwJanAlpha.flush();

					}
					else {
						bwAbroadAlpha.write(address);
						bwAbroadAlpha.newLine();
						bwAbroadAlpha.flush();

					}
				}

			}
			
			bwAbroadAlpha.close();
			bwAbroadKanji.close();
			bwJanKanji.close();
			bwJanAlpha.close();
			br.close();

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
