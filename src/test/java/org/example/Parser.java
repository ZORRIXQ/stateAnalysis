package org.example;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Parser {
    private final String fileName;

    Parser(String fileName) {
        this.fileName = fileName;
    }

    public LinkedHashMap<String, LinkedHashMap<String, ArrayList<Integer>>> parseTable() throws IOException {
        LinkedHashMap<String, LinkedHashMap<String, ArrayList<Integer>>> map = new LinkedHashMap<>();

        FileInputStream file = new FileInputStream("test.xlsx");
        XSSFWorkbook workBook = new XSSFWorkbook(file);
        Sheet sheet = workBook.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        String tempUser = new String();
        LinkedHashMap<String, ArrayList<Integer>> tempMap = new LinkedHashMap<>();

        while (it.hasNext()) {
            StringBuilder tempString = new StringBuilder();
            int tempInt;
            ArrayList<Integer> arrList = new ArrayList<>();
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();

            while (cells.hasNext()) {
                Cell cell = cells.next();
                switch (cell.getCellType()) {
                    case NUMERIC:
                        tempInt = (int) cell.getNumericCellValue();
                        arrList.add(tempInt);
                        break;
                    case STRING:
                        if (cell.getStringCellValue().startsWith("User")) {
                            if (!tempUser.isEmpty() && !tempMap.isEmpty()) {
                                map.put(tempUser, new LinkedHashMap<>(tempMap));
                                tempMap.clear();
                            }
                            tempUser = cell.getStringCellValue();
                        } else {
                            tempString.append(cell.getStringCellValue());
                        }
                        break;
                }
            }
            if (!tempUser.isEmpty() && !tempString.toString().isEmpty()) {
                tempMap.put(tempString.toString(), arrList);
            }

            tempString.delete(0, tempString.length());
        }

        if (!tempUser.isEmpty() && !tempMap.isEmpty()) {
            map.put(tempUser, new LinkedHashMap<>(tempMap));
        }
        return map;
    }
}