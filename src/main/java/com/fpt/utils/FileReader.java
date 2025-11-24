package com.fpt.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

public class FileReader {
    public Object[][] readData(File file, String sheetName){
    try (Workbook wb = new XSSFWorkbook(file)) { // wb will auto close
        Sheet sheet = wb.getSheet(sheetName);
        int startRow = sheet.getFirstRowNum();
        int endRow = sheet.getLastRowNum();
        int max = 0;
        Object[][] res = new Object[endRow - startRow + 1][];
        for (int i = startRow; i <= endRow; i++) {
            Row row = sheet.getRow(i);
            Object[] rowObject = new Object[row.getLastCellNum()];
            // first row in data must be correct data (full value in necessary cell)
            // define max cell, because some cells is blank and j cannot iterator
            max = Math.max(row.getLastCellNum(), max);
            for (int j = 0; j < max; j++) { //if j < getLastCell, if cell is blank so j cannot increase
                Cell cell = row.getCell(j);
                if (cell == null) {
                    rowObject[j] = "";
                } else {
                    rowObject[j] = cell.toString();
                }
            }
            // in case row does not start at 0, res still start at 0
            res[i - startRow] = rowObject;
        }
        return res;
    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (InvalidFormatException e) {
        throw new RuntimeException(e);
    }
    }

}
