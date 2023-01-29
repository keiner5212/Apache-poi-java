package Proc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFReader.SheetIterator;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.model.Styles;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

@SuppressWarnings({"java:S106", "java:S4823", "java:S1192"})
public class XLSX2CSV {

    private class SheetToCSV implements SheetContentsHandler {

        public ArrayList<ArrayList<String>> tabla = new ArrayList<>();
        private boolean firstCellOfRow;
        private int currentRow = -1;
        private int currentCol = -1;
        private int fila = -1;

        private void outputMissingRows(int number) {
            for (int i = 0; i < number; i++) {
                for (int j = 0; j < minColumns; j++) {
                    tabla.get(fila).add("None");
                }
            }
        }

        @Override
        public void startRow(int rowNum) {
//          If there were gaps, output the missing rows
            outputMissingRows(rowNum - currentRow - 1);
            // Prepare for this row
            firstCellOfRow = true;
            currentRow = rowNum;
            currentCol = -1;
        }

        @Override
        public void endRow(int rowNum) {
            for (int i = currentCol; i < minColumns; i++) {
                tabla.get(fila).add("None");
            }
        }

        @Override
        public void cell(String cellReference, String formattedValue, XSSFComment comment) {
            if (firstCellOfRow) {
                fila++;
                tabla.add(new ArrayList<>());
                firstCellOfRow = false;
            }

            if (cellReference == null) {
                cellReference = new CellAddress(currentRow, currentCol).formatAsString();
            }

            int thisCol = (new CellReference(cellReference)).getCol();
            int missedCols = thisCol - currentCol - 1;
            for (int i = 0; i < missedCols; i++) {
                tabla.get(fila).add("None");
            }

            if (formattedValue == null) {
                return;
            }

            currentCol = thisCol;

            tabla.get(fila).add(formattedValue);
        }
    }

    private final OPCPackage xlsxPackage;

    private final int minColumns;

    public XLSX2CSV(OPCPackage pkg, int minColumns) {
        this.xlsxPackage = pkg;
        this.minColumns = minColumns;
    }

    public void processSheet(Styles styles, SharedStrings strings, SheetContentsHandler sheetHandler, InputStream sheetInputStream) throws IOException, SAXException {
        DataFormatter formatter = new DataFormatter(true);
        InputSource sheetSource = new InputSource(sheetInputStream);
        try {
            XMLReader sheetParser = XMLHelper.newXMLReader();
            ContentHandler handler = new XSSFSheetXMLHandler(styles, null, strings, sheetHandler, formatter, false);
            sheetParser.setContentHandler(handler);
            sheetParser.parse(sheetSource);
        } catch (ParserConfigurationException e) {
            System.out.println("processSheet " + e.getMessage());
        }
    }

    public void save(ArrayList<ArrayList<String>> tabla) {
        try {
            FileOutputStream fout = new FileOutputStream("src\\main\\java\\temp\\tabla.data");
            try (ObjectOutputStream writeStream = new ObjectOutputStream(fout)) {
                writeStream.writeObject(tabla);
            }
            System.out.println("success...");
        } catch (IOException e) {
            System.out.println("save " + e.getMessage());
        }
    }

    public void process(int sheet) throws IOException, OpenXML4JException, SAXException {
        ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(this.xlsxPackage);
        XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);
        StylesTable styles = xssfReader.getStylesTable();
        SheetIterator iter = (SheetIterator) xssfReader.getSheetsData();
        System.out.println(".");
        InputStream stream = null;
        for (int i = 0; i <= sheet; i++) {
            try {
                stream = iter.next();
            } catch (Exception ex) {
                System.out.println("process " + ex.getMessage());
            }
        }
        String sheetName = iter.getSheetName();
        System.out.println(sheetName + " [index=" + sheet + "]");
        SheetToCSV aux = new SheetToCSV();
        processSheet(styles, strings, aux, stream);
        save(aux.tabla);
    }

    public void getSheets(JComboBox<String> names) throws IOException, OpenXML4JException, SAXException {
        XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);
        SheetIterator iter = (SheetIterator) xssfReader.getSheetsData();
        int index = 0;
        System.out.println();
        while (iter.hasNext()) {
            try (InputStream stream = iter.next()) {
                String sheetName = iter.getSheetName();
                names.addItem(sheetName);
                System.out.println(sheetName + " [index=" + index + "]");
            }
            ++index;
        }
    }
}
