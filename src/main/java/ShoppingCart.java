import uk.axone.devintest.exceptions.ItemNotFoundException;
import uk.axone.devintest.exceptions.ItemOutOfStockException;

import java.io.IOException;
import java.util.*;

public class ShoppingCart {
    private static final Map<Item, Integer> cartItems = new HashMap<Item, Integer>();
    //Integer quantity;
    private Item it = new Item();
    Inventory inv;


    public ShoppingCart() throws IOException {
        this.inv = new Inventory();
    }

    public boolean addToCart(Item it) throws ItemNotFoundException, ItemOutOfStockException {
        this.it = it;
        if ((cartItems.size()) == 0 && (inv.validateItem(it) && inv.getItemStock(it) >= 1)) {
            cartItems.put(it, 1);
            System.out.println("When Cart is Empty with 1st method : " + cartItems);
            return true;
        } else if (cartItems.containsKey(it)) {
            Integer quantityIncrease = cartItems.get(it) + 1;
            if (inv.getItemStock(it) >= quantityIncrease) {
                cartItems.put(it, quantityIncrease);
                System.out.println("When Cart is has item with 1st method : " + cartItems);
                return true;
            } else {
                throw new ItemOutOfStockException("Item Out of stock Exception");
            }
        } else if (!cartItems.containsKey(it) && (inv.validateItem(it))) {
            cartItems.put(it, 1);
            System.out.println("When Cart is not Empty with 1st method: " + cartItems);
            return true;
        } else {
            throw new ItemOutOfStockException("Item Out of stock Exception");
        }
    }

    public boolean addToCart(Item it, int quantity) throws ItemNotFoundException, ItemOutOfStockException {
        this.it = it;
        if ((cartItems.size()) == 0 && (inv.validateItem(it) && inv.getItemStock(it) >= quantity)) {
            cartItems.put(it, quantity);
            System.out.println("when adding item with quantity in 2nd method" + " " + cartItems);
            return true;
        } else if (cartItems.containsKey(it) && (inv.validateItem(it))) {
            Integer newQuantity = cartItems.get(it) + quantity;
            if (inv.getItemStock(it) >= newQuantity) {
                cartItems.put(it, newQuantity);
                System.out.println("when adding quantity for the item already in cart are in 2nd method " + cartItems);
                return true;
            } else {
                throw new ItemOutOfStockException("Item Out of stock Exception");
            }
        } else if (!cartItems.containsKey(it) && (inv.validateItem(it) && inv.getItemStock(it) >= quantity)) {
            cartItems.put(it, quantity);
            System.out.println("when quantity is added with new item in 2nd method  " + cartItems);
            return true;
        } else {
            throw new ItemOutOfStockException("Item Out of stock Exception");
        }
    }

    public boolean removeFromCart(Item it) throws ItemNotFoundException {
        this.it = it;
        if (cartItems.containsKey(it) && cartItems.get(it) > 1) {
            Integer quantityreduction = cartItems.get(it) - 1;

            cartItems.put(it, quantityreduction);
            System.out.println("Cartitems after removing" + cartItems);
            return true;


        } else if (cartItems.containsKey(it) && cartItems.get(it) == 1) {
            cartItems.remove(it);
            System.out.println("cartlist  when only  quantity is avilable" + cartItems);
            return true;
        } else {
            throw new ItemNotFoundException("Item not found Exception in stock");
        }
    }


    public long calculateTotalCost() {
        long totalprice = 0;
        if (cartItems.size() != 0) {
            for (Map.Entry<Item, Integer> listEntry : cartItems.entrySet()) {
                double price = listEntry.getKey().getPrice();
                long priceperItem = (long) price * listEntry.getValue();
                totalprice = priceperItem + totalprice;
            }
        }
        System.out.println("total value of cart" + totalprice);
        return totalprice;
    }

    public Item[] getCartContents() {
        Item[] cartContent = cartItems.keySet().toArray(new Item[cartItems.size()]);
        String cartContentlist = Arrays.toString(cartContent);
        System.out.println(cartContentlist);
        return cartContent;

    }

    public void checkout() throws ItemNotFoundException {
        if (cartItems.size() != 0) {
            for (Map.Entry<Item, Integer> listentry : cartItems.entrySet()) {
                inv.reduceStock(listentry.getKey(), listentry.getValue());
              //  System.out.println("Items after checkout");
            }
        }
    }


    public static void main(String[] args) throws IOException, ItemNotFoundException, ItemOutOfStockException {
        ShoppingCart shp = new ShoppingCart();
        // ArrayList<Item> itemlist = new ArrayList<Item>();
        Item it1 = new Item(1002, "Samsung s10", "Samsung galaxy flagmanship", 40.0);
        Item it2 = new Item(1001, "Iphone12", "A great all around phone", 20.0);
        Item it3 = new Item(1003, "test", "Test Description", 20.0);
        System.out.println("1 " + shp.addToCart(it1));
        // System.out.println("1 "  + shp.addToCart(it3));
        //System.out.println("2" + shp.addToCart(it1));
        // System.out.println("2" + shp.addToCart(it2));
        // //System.out.println("2" + shp.addToCart(it2));
        // System.out.println("1 " + shp.addToCart(it1, 1));
        //  System.out.println("3" + shp.addToCart(it2, 7));
        //  System.out.println("3" + shp.addToCart(it3, 5));
        //System.out.println("4" + shp.removeFromCart(it2));
        // System.out.println("4" + shp.removeFromCart(it1));
        //System.out.println("5"+ shp.removeFromCart(it1));
        System.out.println("total price of thecart is" + shp.calculateTotalCost());
        System.out.println("Cart items in an arrary are " + shp.getCartContents());
        shp.checkout();
        // System.out.println("6" +cartItems);

    }
}

