package Processing;
 
import java.net.URLEncoder;
 


import tools.InfoSeek;
import tools.TranslateMode;
import tools.TraslateModeFromOther;

import com.google.gson.Gson;

 
public class BaiduTranslator {
 
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		String dst=translate("1974-1-24-101 Oya, Minuma-Ku Saitama Saitama Japan");
//		System.out.println(dst);
//		
//	}
	public String translate(String source)
	{
		String api_url;
		try {
			
			InfoSeek infoSeek = new InfoSeek();
			String json=infoSeek.GoogleResultReturn(source);
			Gson gson=new Gson();
			TraslateModeFromOther translateMode=gson.fromJson(json, TraslateModeFromOther.class);
			
			if(translateMode!=null&&translateMode.getData()!=null&&translateMode.getData().size()==1)
			{
				return translateMode.getData().get(0).getDst();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}