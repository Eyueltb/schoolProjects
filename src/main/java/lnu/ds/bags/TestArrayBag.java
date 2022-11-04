package lnu.ds.bags;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;

public class TestArrayBag {
    public static void main(String[] args) {

        Item[] items = {
                new Item("Milk", 8.10, 10),
                new Item("Egg", 40, 2),
                new Item("Tomato", 10, 2),
                new Item("Bread", 3, 10)
        };

        IBag<Item> shoppingCart = new ArrayBag<>();

        Arrays.stream(items).forEach(item-> shoppingCart.add(item));
        // sum price by quantity
        System.out.println(" Sum = " + Arrays.stream(items).mapToDouble(i -> i.price * i.quantity).sum());

        while (!shoppingCart.isEmpty())
            System.out.println(shoppingCart.remove());

        System.out.println(shoppingCart.isEmpty());
    }
}


@Data
@AllArgsConstructor
final class Item {
    String name;
    double price;
    int quantity;
}
