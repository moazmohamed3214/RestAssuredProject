package excelData;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
public class readExcelData {
    XSSFSheet sheet;
    XSSFWorkbook workbook;
    public Object id;
    public Object userName;
    public Object password;
    public readExcelData(String path,String sheetName)
    {
        try
        {
            workbook=new XSSFWorkbook(new File(path));
            sheet=workbook.getSheet(sheetName);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
    public void getCellData()
    {
        DataFormatter format=new DataFormatter();
        id=format.formatCellValue(sheet.getRow(1).getCell(0));
        userName=format.formatCellValue(sheet.getRow(1).getCell(1));
        password=format.formatCellValue(sheet.getRow(1).getCell(2));

    }

}
