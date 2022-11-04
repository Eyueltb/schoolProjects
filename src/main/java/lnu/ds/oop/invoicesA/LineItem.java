package lnu.ds.oop.invoicesA;

import lombok.AllArgsConstructor;
import lombok.Data;

/** Describes the quantity of an item to purchase */

@Data @AllArgsConstructor
final public class LineItem {
    private  int quantity;
    private Product product;

    /** Computes the total cost */
    double getTotalPrice(){
        return product.getPrice() * quantity;
    }
    //Format the invoice
    public String format() {
        return String.format("%-30s%8.2f%5d%8.2f", product.getDescription(), product.getPrice(), quantity, getTotalPrice());
    }
}
