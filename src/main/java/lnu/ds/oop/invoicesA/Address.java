package lnu.ds.oop.invoicesA;

import lombok.AllArgsConstructor;
import lombok.Data;

/** Describe the mailing address */

@Data @AllArgsConstructor
final public class Address {
    private String name;
    private String street;
    private String city;
    private String state;
    private  String zip;
    //Format the invoice
    public String format() {
        return String.format("%s%n%n%s, %s - %s", name, street, city,state, zip);
    }
}
