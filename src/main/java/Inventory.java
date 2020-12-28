import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sun.media.sound.InvalidDataException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import uk.axone.devintest.exceptions.ItemNotFoundException;

public class Inventory {
    private static final String excelPath = ".\\Datafiles\\StockData.xlsx";//private static final
    private static final String sheetName = "Stock1";

    private Map<Item, Integer> stock = new HashMap<Item, Integer>();

    public Inventory() throws IOException {
        load();

    }

    private void load() throws IOException {
        try{
        XSSFWorkbook workbook = new XSSFWorkbook(excelPath);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int lastRow = sheet.getLastRowNum();
        for (int r = 0; r <= lastRow; r++) {
            XSSFRow row = sheet.getRow(r);
            Cell valueCell = row.getCell(4);
            Cell keyItemCode = row.getCell(0);
            Cell keyItemName = row.getCell(1);
            Cell keyItemDescription = row.getCell(2);
            Cell keyItemPrice = row.getCell(3);
            Item it = new Item();//TRY TO USE PARAMETERISD CONSTROUCTOR
            it.setItemCode((int) keyItemCode.getNumericCellValue());
            it.setItemName(keyItemName.getStringCellValue());
            it.setItemDescription(keyItemDescription.getStringCellValue());
            it.setPrice(keyItemPrice.getNumericCellValue());
            int quantity = (int) (valueCell.getNumericCellValue());
            stock.put(it, quantity);

        }}
        catch (InvalidDataException invalidDataException){
            throw new InvalidDataException("Invalid data format");
        }
    }


    public boolean reduceStock(Item it, Integer quantity) throws ItemNotFoundException {
        Integer currentQuantity = stock.get(it);
        if (currentQuantity >= quantity) {
            currentQuantity = currentQuantity - quantity;
            stock.put(it, currentQuantity);
            System.out.println("quantity after reducing from stock for the Item is  " + currentQuantity );
            return true;
        } //else if (currentQuantity == 0) {
        else  {
            throw new ItemNotFoundException("Item not found Exception in stock");
        }
    }

    public int getItemStock(Item it) throws ItemNotFoundException {
        if (stock.containsKey(it)) {
            Integer currentItemStock = stock.get(it);
            //System.out.println("Items avilable in the current stock are" + "  " + currentItemStock);
            return currentItemStock;
        } else {
            throw new ItemNotFoundException("Item not found Exception in stock");
        }
    }
    public boolean validateItem(Item it) throws ItemNotFoundException {
        if (stock.containsKey(it)) {
            return true;
        } else {
            throw new ItemNotFoundException("Item not found Exception in stock");
        }
        }

    public Map<Integer, String> getItemCatalouge(Item it) {
        Set<Item> itemcodenname = stock.keySet();
        Map<Integer, String> getItemCatalougeMap = new HashMap<Integer, String>();//insertion not required
        for (Item itnn : itemcodenname) {
            Integer newItemcode = (itnn.getItemCode());
            String newItemname = itnn.getItemName();
            getItemCatalougeMap.put(newItemcode, newItemname);
        }
        // System.out.println("Item in the catalouge are" + "  " + (getItemCatalougeMap));
        return getItemCatalougeMap;
    }


    public static void main(String[] args) throws IOException, ItemNotFoundException {
        Inventory itn = new Inventory();
        // itn.load();
        Item it = new Item(1002, "Samsung s10", "Samsung galaxy flagmanship", 40.0);
        itn.reduceStock(it, 11);
        itn.getItemStock(it);
        itn.validateItem(it);
        itn.getItemCatalouge(it);


    }
}

