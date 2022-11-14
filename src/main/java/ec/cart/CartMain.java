package ec.cart;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class CartMain {
    public static void main(String[] args) {

        ItemService itemService = new ItemService();
        Item item1 = itemService.createItem("983-773", "Limon", "Best lemon", 100, 20);
        Item item2 = itemService.createItem("778-773", "Banana", "best banana", 50, 2000);

        //search
        String searchString = "a";
        Stream<Item> foundItems = itemService.searchForItems(searchString);
        foundItems.forEach(item -> System.out.println("Found" + item));

        //get item by id
        itemService.getItemById(1).stream().forEach(System.out::print);

        // shopping cart
        ShoppingCartService shoppingCartService = new ShoppingCartService(itemService);

        ShoppingCart shoppingCart = shoppingCartService.createShoppingCart();
        shoppingCartService.addItemToShoppingCart(shoppingCart.getId(), item1.getId());
        shoppingCartService.addItemToShoppingCart(shoppingCart.getId(), item2.getId());
        shoppingCartService.addItemToShoppingCart(shoppingCart.getId(), item1.getId());

        shoppingCart.getRows().forEach(shoppingCartRow -> System.out.println("Main.main "+shoppingCartRow));

        //shopping cart change quantity
        shoppingCartService.plusOneToShoppingCartItem(shoppingCart.getId(), item1.getId());
        shoppingCartService.plusOneToShoppingCartItem(shoppingCart.getId(), item2.getId());

        shoppingCart.getRows().forEach(shoppingCartRow -> System.out.println("Main.main "+shoppingCartRow));

        System.out.println("Main.main --------- shopping cart remove row --------------");

        shoppingCartService.removeItemFromShoppingCart(shoppingCart.getId(), item1.getId());

        shoppingCart.getRows().forEach(shoppingCartRow -> System.out.println("Main.main "+shoppingCartRow));
    }
}

interface IItemService {
    Item createItem(String itemNo, String itemText, String description, double price, double quantity); // Item createItem(Item item);
    Optional<Item> getItemById(long itemId);
    Stream<Item> searchForItems(String searchString);
}

interface IShoppingCartService {
    ShoppingCart createShoppingCart();
    Optional<ShoppingCart> getShoppingCartById(long shoppingCartId);
    LineItem addItemToShoppingCart(long shoppingCartId, long itemId);
    LineItem plusOneToShoppingCartItem(long shoppingCartId, long itemId);
    void removeItemFromShoppingCart(long shoppingCartId, long itemId);
}

@Data
class ItemService implements IItemService {
    private long itemIdCounter = 0;
    private List<Item> items = new LinkedList<>();

    public Item createItem(String itemNo, String itemText, String description, double price, double quantity) {
        Item item = new Item(nextItemId(), itemNo, itemText, description, price, quantity);
        items.add(item);
        return item;
    }

    @Override
    public Optional<Item> getItemById(long itemId) {
        return items.stream().filter(item -> item.getId() == itemId).findAny();
    }

    @Override
    public Stream<Item> searchForItems(String searchString) {
        return items.stream().filter(item -> checkItem(searchString, item));
    }

    private boolean checkItem(String searchString, Item item) {
        if (item.getItemNo().equals(searchString))
            return true;
        if (item.getItemText().toLowerCase().contains(searchString))
            return true;
        if (item.getDescription().toLowerCase().contains(searchString))
            return true;
        return false;
    }

    private long nextItemId() {
        return itemIdCounter++;
    }
}

@Data @AllArgsConstructor
class Item {
    long id;
    String itemNo;
    String itemText;
    String description;
    double price;
    double quantity;
}

@Data
class ShoppingCartService implements IShoppingCartService{
    private long shoppingCartIdCounter = 0;
    private long shoppingCartRowIdCounter = 0;
    private List<ShoppingCart> shoppingCarts = new LinkedList<>();

    private ItemService itemService;

    public ShoppingCartService(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public ShoppingCart createShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart(shoppingCartIdCounter++);
        shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }

    @Override
    public Optional<ShoppingCart> getShoppingCartById(long shoppingCartId) {
        return shoppingCarts.stream()
                .filter(shoppingCart -> shoppingCart.getId() == shoppingCartId)
                .findAny();
    }

    @Override
    public LineItem addItemToShoppingCart(long shoppingCartId, long itemId) {
        return getShoppingCartAndItem(shoppingCartId, itemId).addItemToShoppingCart();
    }

    @Override
    public LineItem plusOneToShoppingCartItem(long shoppingCartId, long itemId) {
        return getShoppingCartAndItem(shoppingCartId, itemId).plusOneToShoppingCartItem();
    }

    @Override
    public void removeItemFromShoppingCart(long shoppingCartId, long itemId) {
        getShoppingCartAndItem(shoppingCartId, itemId).removeItemFromShoppingCart();
    }

    private ShoppingCartAndItem getShoppingCartAndItem(long shoppingCartId, long itemId) {
        ShoppingCart shoppingCart = getShoppingCartById(shoppingCartId)
                .orElseThrow(() -> new RuntimeException("Not found"));
        Item item = itemService.getItemById(itemId)
                .orElseThrow(() -> new RuntimeException("Not found"));
        return new ShoppingCartAndItem(shoppingCart, item);
    }

    @Data @AllArgsConstructor
    private class ShoppingCartAndItem {
        ShoppingCart shoppingCart;
        Item item;

        public LineItem addItemToShoppingCart() {
            LineItem shoppingCartRow = new LineItem(shoppingCartRowIdCounter++, item);
            shoppingCart.addRow(shoppingCartRow);
            return shoppingCartRow;
        }

        public LineItem plusOneToShoppingCartItem() {
            return shoppingCart.plusOneForRowWithItem(item);
        }

        public void removeItemFromShoppingCart() {
            shoppingCart.removeItem(item);
        }
    }
}


@Data
class ShoppingCart {
    private long id;
    private List<LineItem> rows = new LinkedList<>();

    public ShoppingCart(long id) {
        this.id = id;
    }

    public void addRow(LineItem lineItem) {
        Item item = lineItem.getItem();
        if (getRowByItem(item).isPresent()) {
            lineItem.plusOne();
        } else {
            rows.add(lineItem);
        }
    }

    public void removeItem(Item item) {
        getRowByItem(item)
                .ifPresentOrElse(
                        rows::remove, // shoppingCartRow -> rows.remove(shoppingCartRow),
                        () -> {
                            throw new RuntimeException("Not found");
                        }
                );
    }

    public LineItem plusOneForRowWithItem(Item item) {
        LineItem row = getRowByItem(item)
                .orElseThrow(() -> new RuntimeException("Not found"));
        row.plusOne();
        return row;
    }

    private Optional<LineItem> getRowByItem(Item item) {
        return rows.stream().filter(lineItem -> lineItem.getItem().equals(item)).findAny();
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", rows=" + rows +
                '}';
    }
}

@Data
class LineItem {
    private long id;
    private Item item;
    private double quantity;
    private double totalPrice;

    public LineItem(long id, Item item) {
        this.id = id;
        this.item = item;
        this.quantity = 1;
        this.totalPrice = calculateTotalPrice(item.getPrice(), this.quantity);
    }

    public void plusOne() {
        this.quantity++;
        this.totalPrice = calculateTotalPrice(item.getPrice(), this.quantity);
    }

    private static double calculateTotalPrice(double price, double quantity) {
        return price * quantity;
    }

    @Override
    public String toString() {
        return "CartRow{" +
                "id=" + id +
                ", item=" + item +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
