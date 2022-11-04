package lnu.ds.oop.invoicesB;

import lombok.AllArgsConstructor;
import lombok.Data;

/** Describe a product with a description and a price */

@Data @AllArgsConstructor
final class Product {
    //For relational table we need product_code
    private String description;
    private double price;
}
