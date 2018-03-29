/**
 * 
 */
package ioHandle;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * @author ar-weichang.chen
 * @create-time 2015/02/02 13:16:41
 */
public class ExcelReader {

	public void ReadFromColumn(String InputPath,Integer column) {

		int i;
		Sheet sheet;
		Workbook book;
		Cell cell1;
		try {
			// t.xls为要读取的excel文件名
			book = Workbook.getWorkbook(new File(InputPath));

			// 获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
			sheet = book.getSheet(0);
			// // 获取左上角的单元格
			// cell1 = sheet.getCell(0, 0);
			// System.out.println("标题：" + cell1.getContents());

			i = 1;
			while (true) {
				// 获取每一行的单元格
				cell1 = sheet.getCell(column-1, i);// （列，行）
				if ("".equals(cell1.getContents()) == true) // 如果读取的数据为空
				break;
				System.out.println(cell1.getContents());
				i++;
			}
			book.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	
	}

}
