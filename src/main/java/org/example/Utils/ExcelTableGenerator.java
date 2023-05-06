package org.example.Utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.DataClasses.CollectionBenchmarkRuns;
import org.example.UseCases.UseCase;

import java.util.Map;

public class ExcelTableGenerator
{
    public void generate(Collection<CollectionBenchmarkRuns> useCase, String name) throws IOException
    {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet(name);
        sheet.setColumnWidth(0, 25*256);
        sheet.setColumnWidth(1, 15*256);
        sheet.setColumnWidth(2, 15*256);
        sheet.setColumnWidth(3, 15*256);

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Collection");
        headerRow.createCell(1).setCellValue("Elemente");
        headerRow.createCell(2).setCellValue("Laufzeit");
        headerRow.createCell(3).setCellValue("Einheit");
        setHeaderCellStyle(headerRow, workbook);

        int rowIndex = 1;
        for(var collection : useCase)
        {
            for(var run : collection.allRuns)
            {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(collection.methodName);
                row.createCell(1).setCellValue(run.elementCount);
                row.createCell(2).setCellValue(run.score);
                row.createCell(3).setCellValue(run.scoreUnit);
            }
            Row row = sheet.createRow(rowIndex++);
        }

        var excelName = "./resultTables/" + name + ".xlsx";
        FileOutputStream outputStream = new FileOutputStream(excelName);
        workbook.write(outputStream);
        workbook.close();
    }

    private void setHeaderCellStyle(Row headerRow, Workbook workbook) {
        XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
        style.setFillForegroundColor(new XSSFColor(new byte[]{(byte)128, (byte)128, (byte)128}, null));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        style.setFont(font);
        for (Cell cell : headerRow) {
            cell.setCellStyle(style);
        }
    }
}
