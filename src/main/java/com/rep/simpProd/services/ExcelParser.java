package com.rep.simpProd.services;

import com.rep.simpProd.entity.Product;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Service
public class ExcelParser {

    public Map<String, Object> readAndParseExcelFile(File file) throws IOException {
        List<Product> products = new ArrayList<>();
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);

        Sheet sheet = workbook.getSheetAt(0);
        boolean topIsCheked = false;
        Map<Integer, Boolean> mapOfTops = new HashMap<>();
        Map<String, Object> parsedResult = new HashMap<>();
        int topsCounter = 1;
        int productFieldCounter = 0;
        boolean hasError = false;
        parsedResult.put("hasError", hasError);
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            if (hasError) {
                break;
            }
            Row row = rowIterator.next();

            if (row.getCell(0).getCellType() == CellType.BLANK) {
                if (productFieldCounter == 0 & !topIsCheked) {
                    parsedResult.put("hasError", true);
                    parsedResult.put("error", "invalid file structure");
                }
                break;
            }

            Product product = new Product();
            productFieldCounter = 0;
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (topIsCheked) {
                    String nProd = cell.getStringCellValue();
                    if (cell.getCellType() == CellType.BLANK) {
                        break;
                    }
                    productFieldCounter++;
                    try {
                        setValueToProduct(cell, product, productFieldCounter);
                    } catch (Exception e) {
                        parsedResult.put("hasError", true);
                        parsedResult.put("error", e.getMessage());
                        return parsedResult;
                    }

                    if (productFieldCounter == 3) {
                        products.add(product);
                        break;
                    }
                } else {
                    checkTopOfExFile(cell, mapOfTops, topsCounter);
                    topsCounter++;
                    if (topsCounter >= 4) {
                        int topsRes =
                                (int) mapOfTops.entrySet().stream().filter(entry -> entry.getValue().equals(true))
                                        .count();

                        if (topsRes == 3) {
                            topIsCheked = true;
                            break;
                        } else {
                            parsedResult.put("error", "wrong format of table");
                            hasError = true;
                            parsedResult.put("hasError", hasError);
                            break;
                        }

                    }
                }


            }


        }

        parsedResult.put("parsedList", products);
        return parsedResult;
    }

    private void checkTopOfExFile(Cell cell, Map<Integer, Boolean> mapOfTops, int topsCounter) {
        if (cell == null) {
            mapOfTops.put(topsCounter, false);
        } else {
            if (cell.getCellType() == CellType.STRING) {
                String value = cell.getStringCellValue().toLowerCase();
                switch (value) {
                    case "name":
                        mapOfTops.put(topsCounter, true);
                        break;
                    case "articul":
                        mapOfTops.put(topsCounter, true);
                        break;
                    case "price":
                        mapOfTops.put(topsCounter, true);
                        break;
                    default:
                        mapOfTops.put(topsCounter, false);
                }
            } else {
                mapOfTops.put(topsCounter, false);
            }
        }

    }

    private void setValueToProduct(Cell cell, Product product, int productFieldCounter) {
        switch (cell.getCellType()) {
            case STRING:
                if (productFieldCounter == 1) {
                    product.setName(cell.getStringCellValue());
                } else if (productFieldCounter == 2) {
                  double price =  Double.valueOf(cell.getStringCellValue());
                  product.setPrice(price);
                } else if (productFieldCounter == 3) {
                    product.setArticul(cell.getStringCellValue());
                }
                break;
            case NUMERIC:
                product.setPrice(cell.getNumericCellValue());
                break;

        }
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

}
