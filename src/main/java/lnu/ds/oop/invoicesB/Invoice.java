package lnu.ds.oop.invoicesB;

import java.util.ArrayList;

/** Describes an invoice for a set of purchased product */
final public class Invoice {
    //For relational table we need Invoice #
    private Customer customer;
    private ArrayList<LineItem> items;


    public Invoice(Customer customer) {
        items = new ArrayList<>();
        this.customer = customer;
    }

    //Add a charge for a product to this invoice
    public void add(Product product, int quantity) {
        items.add(new LineItem(quantity, product));
    }

    //Format the invoice
    public String format() {
        String r = "       I N V O I C E\n\n"
                + customer.format()
                + String.format("\n\n%-30s%8s%5s%8s\n", "Description", "Price", "Qty", "Total");

        for(LineItem item : items)
        {
            r = r + item.format() + "\n";
        }

        r = r + String.format("\nAMOUNT DUE: $%8.2f", getAmountDue());

        return r;
    }
    private double getAmountDue() {
        return items.stream().map(LineItem::getTotalPrice).reduce(0.0, Double::sum);
    }
}
