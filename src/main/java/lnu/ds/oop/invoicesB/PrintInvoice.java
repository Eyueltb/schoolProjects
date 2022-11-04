package lnu.ds.oop.invoicesB;


/**
 * Task- The task in general is to printout invoice for the order.
 * An invoice describes the charges for a set of products in certain quantities.
 * Q1- Improve this assignment by separating presentation layer and logic
 * for instance, add InvoiceFormatter class and remove all format()
 *  Which means Invoice and line item no longer represent format() method
 * Q2- Improve by applying MV or MVC for this assignment
 * Q3- Improve by Add dates, taxes,and (invoice and customer number)
 * Q4- Store information in file, then Json then database
 */
public class PrintInvoice {

    public static void main(String[] args) {
        Customer eyu = new Customer("Sköndal", "Herbert Widmans Vag", "Stockholm", "Sweden", "12844");
        Invoice invoiceEyu = new Invoice(eyu);

        invoiceEyu.add(new Product("T-shirt", 23.45), 3);
        invoiceEyu.add(new Product("Java Books", 123.45), 5);
        invoiceEyu.add(new Product("Samsung Mobile", 2003.45), 1);
        System.out.println(invoiceEyu.format());
    }
}
