import org.apache.poi.ss.usermodel.Cell;

import java.util.Map;
import java.util.Set;

public class Item {

    private Integer itemCode;
    private String itemName;
    private String itemDescription;
    private double price;

    public Item(Integer itemCode, String itemName, String itemDescription, double price) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.price = price;
    }

    public Item() {

    }
    //  public Item(Item [] it){

    // }


    public Integer getItemCode() {
        return itemCode;
    }

    public void setItemCode(Integer itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (Double.compare(item.price, price) != 0) return false;
        if (!itemCode.equals(item.itemCode)) return false;
        if (!itemName.equals(item.itemName)) return false;
        return itemDescription.equals(item.itemDescription);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = itemCode.hashCode();
        result = 31 * result + itemName.hashCode();
        result = 31 * result + itemDescription.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", price=" + price +
                '}';
    }
}
