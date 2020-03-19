package ExcelFile;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel implements Controlador{

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private TreeMap<String, Object[]> data;
	
	private String str1;
	private String str2;
	private String str3;
	
	public Excel(String string, String string2, String string3) {
		str1 = string;
		str2 = string2;	
		str2 = string3;
	}
	public Excel() {
		
	}
	
	public String getStr1() {
		return str1;
	}
	public void setStr1(String str1) {
		this.str1 = str1;
	}
	public String getStr2() {
		return str2;
	}
	public void setStr2(String str2) {
		this.str2 = str2;
	}
	public String getStr3() {
		return str3;
	}
	public void setStr3(String str3) {
		this.str3 = str3;
	}
	
	@Override
	public void abrirExcel() {
		
		//Blank workbook
        workbook = new XSSFWorkbook(); 
         System.out.println("Teste");
        //Create a blank sheet
        sheet = workbook.createSheet("Nomes");		
	}

	@Override
	public void escreverExcel() {
		// TODO Auto-generated method stub
		data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {str1});
        data.put("2", new Object[] {str2});
        data.put("3", new Object[] {str3});
        
        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String) {
                    cell.setCellValue((String)obj);
               }
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("ListaNomes.xlsx"));
            workbook.write(out);
            out.close();        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
	}

	@Override
	public void lerExcel() {
		// TODO Auto-generated method stub
		ArrayList<String> nomes = new ArrayList<String>();
		
		try
        {
            FileInputStream file = new FileInputStream(new File("ListaNomes.xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            workbook = new XSSFWorkbook(file);
 
            //Get first/desired sheet from the workbook
            sheet = workbook.getSheetAt(0);
 
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) 
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                 
                while (cellIterator.hasNext()) 
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellTypeEnum()) 
                    {
                        case STRING:
                        	nomes.add(cell.getStringCellValue());
                            break;
					default:
						break;
                    }
                }
            }
            file.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
		//TODO usar assertEquals()
		assertEquals(nomes.get(0), data.get("1")[0]);
		assertEquals(nomes.get(1), data.get("2")[0]);
		assertEquals(nomes.get(2), data.get("3")[0]);
	}
}
