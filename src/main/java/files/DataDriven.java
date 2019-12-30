package files;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {
	@SuppressWarnings("deprecation")
	public ArrayList<String> getData(String TestcaseName,String sheetName) throws IOException{
		ArrayList<String> al=new ArrayList<String>();
		
	    FileInputStream fis=new FileInputStream("//home//v-swati.patil//Documents//Testdata.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fis);  //it accepts fis arg
		
		int sheet=wb.getNumberOfSheets();
		for(int i=0;i<sheet;i++) {
			if(wb.getSheetName(i).equalsIgnoreCase(sheetName));
				XSSFSheet Sheet=wb.getSheetAt(i);
			//Identify testcases column by scanning first row
				Iterator<Row> rows=Sheet.iterator();
				Row firstRow=rows.next();
				Iterator<Cell> cell=firstRow.cellIterator();
				int k=0;
				int column=0;
				while(cell.hasNext()) {
					
					Cell value=cell.next();
					if(value.getStringCellValue().equalsIgnoreCase("Testcases")) {
						column=k;		
					}
					k++;
				}
		//System.out.println("Cell present at index "+column);
		
		//once col identified then  scan entire testcases column and srch for test2 row
		  while(rows.hasNext()) {
			  Row r=rows.next();
			if(r.getCell(column).getStringCellValue().equalsIgnoreCase(TestcaseName)) {
				Iterator<Cell> ce=r.cellIterator();
				while(ce.hasNext()) {
					Cell c=ce.next();
					if(c.getCellTypeEnum()==CellType.STRING) {
					
					al.add(c.getStringCellValue());
				}
			else {
				//to convert numeric data to string as al is of string type
				al.add(NumberToTextConverter.toText(c.getNumericCellValue()));
		}
			}
		  
		  }
		
			}
		}
		return al;
		
  
	}
		
	
public static void main(String[] args) throws IOException {
		
      }

}


