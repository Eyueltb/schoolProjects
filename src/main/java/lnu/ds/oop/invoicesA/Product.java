package lnu.ds.oop.invoicesA;

import lombok.AllArgsConstructor;
import lombok.Data;

/** Describe a product with a description and a price */

@Data @AllArgsConstructor
final class Product {
    private String description;
    private double price;
}
