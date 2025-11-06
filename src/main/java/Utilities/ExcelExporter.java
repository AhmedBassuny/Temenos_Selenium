package Utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class ExcelExporter {


    private static final String[] HEADERS = {
            "title", "givenName", "fullName", "shortName", "gender",
            "mnemonic", "sector", "txn_num", "currency",
            "accountOfficer", "target", "industry", "customerStatus",
            "nationality", "residence", "gbStreet", "acc_num",
            "creator", "author"
    };

    /**
     * Export one customer into Excel, appending if file already exists.
     */
    public static void exportMap(Map<String, String> customer, String filename,
                                 String creatorUser, String authorUser) {
        try {
            Workbook workbook;
            Sheet sheet;

            //  Ensure folder exists
            java.nio.file.Path outDir = Paths.get("test-output");
            if (!outDir.toFile().exists()) {
                outDir.toFile().mkdirs();
            }
            File file = outDir.resolve(filename + ".xlsx").toFile();

            if (file.exists()) {
                // Open existing workbook
                try (FileInputStream fis = new FileInputStream(file)) {
                    workbook = new XSSFWorkbook(fis);
                }
                sheet = workbook.getSheetAt(0);
            } else {
                // Create new workbook
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Customers");

                // Write headers
                Row header = sheet.createRow(0);
                for (int i = 0; i < HEADERS.length; i++) {
                    header.createCell(i).setCellValue(HEADERS[i]);
                }
            }

            // Append new row
            int rowNum = sheet.getLastRowNum() + 1;
            Row row = sheet.createRow(rowNum);

            for (int i = 0; i < HEADERS.length; i++) {
                String key = HEADERS[i];
                String value = "";

                if (key.equals("creator") && creatorUser != null) {
                    value = creatorUser;
                } else if (key.equals("author") && authorUser != null) {
                    value = authorUser;
                } else {
                    value = customer.getOrDefault(key, "");
                    if (key.equals("currency") && value.isEmpty()) {
                        value = "USD"; // default currency
                    }
                }
                row.createCell(i).setCellValue(value);
            }

            // Auto-size columns
            for (int i = 0; i < HEADERS.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Save back to file
            try (FileOutputStream out = new FileOutputStream(file)) {
                workbook.write(out);
            }
            workbook.close();

            System.out.println(" Excel updated: " + file.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
