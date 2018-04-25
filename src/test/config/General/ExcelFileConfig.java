package test.config.General;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Viral on 11/26/2016.
 */
public class ExcelFileConfig {


    public static void main(String[] args) {

        ExcelFileConfig excel = new ExcelFileConfig();

        System.out.println(excel.getRowFromExcel("C:\\Users\\admin\\Desktop", "DEC-16_2_2.xls", "clinic", 1));


    }

    public Sheet loadExcelFile(String filePath, String fileName, String sheetName) {

        File file = new File(filePath + "\\" + fileName);

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Workbook workbook = null;

        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        if (fileExtensionName.equals(".xlsx")) {
            try {
                workbook = new XSSFWorkbook(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (fileExtensionName.equals(".xls")) {
            try {
                workbook = new HSSFWorkbook(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Sheet sheet = workbook.getSheet(sheetName);
        return sheet;
    }

    public Row getRowFromExcel(String filePath, String fileName, String sheetName, int rowNo) {

        Sheet sheet = loadExcelFile(filePath, fileName, sheetName);

        Row row = sheet.getRow(rowNo);

        return row;
    }

    public Cell getCellFromExcel(String filePath, String fileName, String sheetName, int rowNo, int columnNo) {

        Sheet sheet = loadExcelFile(filePath, fileName, sheetName);

        Cell cell = sheet.getRow(rowNo).getCell(columnNo);

        return cell;
    }


}
