package uk.axone.devintest.exceptions;

public class ItemOutOfStockException extends Exception {
    public ItemOutOfStockException  (String message){
        super(message);
    }
}
