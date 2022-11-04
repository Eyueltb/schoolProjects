package lnu.ds.oop.invoicesA;

/**
 * Task- The task in general is to printout invoice for the order.
 * An invoice describes the charges for a set of products in certain quantities.
 * Q1- Improve by adding customer information->
 * Assume that a customer order product from store.Invoice are generated to list the items
 * and quantity....
 * Q2- Improve this assignment by separating presentation layer and logic
 * for instance, add InvoiceFormatter class and remove all format()
 *  Which means Invoice and line item no longer represent format() method
 * Q3- Improve by applying MV or MVC for this assignment
 * Q4- Improve by Add dates, taxes,and (invoice and customer number)
 * Q5- Store information in file, then Json then database
 */
public class PrintInvoice {

    public static void main(String[] args) {
        Address eyuAddress = new Address("Sköndal", "Herbert Widmans Vag", "Stockholm", "Sweden", "12844");
        Invoice eyuInvoice = new Invoice(eyuAddress);
        eyuInvoice.add(new Product("T-shirt", 23.45), 3);
        eyuInvoice.add(new Product("Java Books", 123.45), 5);
        eyuInvoice.add(new Product("Samsung Mobile", 2003.45), 1);
        System.out.println(eyuInvoice.format());
    }
}
