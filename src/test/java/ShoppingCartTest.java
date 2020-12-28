import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import uk.axone.devintest.exceptions.ItemNotFoundException;
import uk.axone.devintest.exceptions.ItemOutOfStockException;

import java.io.IOException;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ShoppingCartTest {

    Item it2 = new Item(1002, "Samsung s10", "Samsung galaxy flagmanship", 40.0);
    Item it1 = new Item(1001, "Iphone12", "A great all around phone", 20.0);
    Item it3 = new Item(1003, "InvalidItem", "Invalid Item Description", 20.0);
    ShoppingCart shp;
    Inventory inv;

    public ShoppingCartTest() throws IOException {
        this.shp=new ShoppingCart();

    }


    @Test
  public void Aadd_valid_Items_tocart_checkTotal() throws IOException, ItemNotFoundException, ItemOutOfStockException {

      shp.addToCart(it1);
      shp.addToCart(it2,4);
      int expectedResult=180;
      Assert.assertEquals(expectedResult,shp.calculateTotalCost());
  }
  @Test
      public void Badd_Items_Checkout_toReduce_Stocklevels() throws ItemNotFoundException, ItemOutOfStockException {
      shp.addToCart(it2,4);
      shp.checkout();
      Integer stockTotal=shp.inv.getItemStock(it2);
      Integer stockAfterSale=  shp.inv.getItemStock(it2);
      Integer expectedResult=stockAfterSale;

     Assert.assertEquals(expectedResult,stockTotal);
  }
    @Test(expected=ItemNotFoundException.class)
public void Cadd_Invalid_data_togenerateException() throws ItemOutOfStockException, ItemNotFoundException {
        shp.addToCart(it3);
    }
    @Test(expected=ItemOutOfStockException.class)
    public void Dadd_item_notavilable_togenerateException() throws ItemOutOfStockException, ItemNotFoundException {
        shp.addToCart(it2,15);
    }
}
